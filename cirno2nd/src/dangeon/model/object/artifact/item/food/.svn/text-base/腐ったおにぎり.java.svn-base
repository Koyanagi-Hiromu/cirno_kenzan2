package dangeon.model.object.artifact.item.food;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.resistant.Poison;
import dangeon.util.Damage;
import dangeon.util.R;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

public class 腐ったおにぎり extends Food {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "腐ったおにぎり";

	public 腐ったおにぎり(Point p) {
		super(p, item_name);
		sim = ENCHANT_SIMBOL.腐;
	}

	private void effect() {
		MainMap.addEffect(new GoodBadEffect(false));
		Damage.damageNoMessage(this, "お腹を下して倒れた", null, Player.me, 5);
		switch (new R().nextInt(3) + 1) {
		case 1:
			Poison.effect(Player.me, 1, false, false);
			break;
		case 2:
			Message.set(Player.me.getColoredName(), "は足どりが重くなった");
			if (!Player.me.setCondition(CONDITION.鈍足, 0)) {
				SE.STATUS_SLOW.play();
			}
			break;
		case 3:
			Message.set(Player.me.getColoredName(), "は猛烈に眠くなった");
			Player.me.setCondition(CONDITION.睡眠, 0);
			SE.STATUS_SLEEP.play();
			break;
		case 4:
			Message.set(Player.me.getColoredName(), "は混乱した");
			if (!Player.me.setCondition(CONDITION.混乱, 0)) {
				SE.STATUS_PIYOPIYO.play();
			}
			break;
		}
	}

	// test
	@Override
	protected void foodUse() {
		if (Player.me.getMAX_SATIETY() <= Player.me.getSATIETY()) {
			Player.me.setMAX_SATIETY(Player.me.getMAX_SATIETY() + 1);
			Player.me.chengeSatiety(Player.me.getMAX_SATIETY());
			SE.IKAKUTYO.play();
			Message.set("最大満腹度が１上がった");
		} else {
			int plus_satiety = 30;
			Player.me.chengeSatiety(plus_satiety);
			Message.set("満腹度が３０回復した");
		}
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.死)) {
			Message.set("ゥ ン ま あ あ ～ い っ");
		} else {
			effect();
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "正しくは腐ったかのように見えるおにぎり。食べると悪いことが起こる。どうしてもこれを食べなくてはいけない時は周囲に敵がいないときに食べよう。";
	}

	@Override
	public boolean waterAction() {
		return true;
	}
}
