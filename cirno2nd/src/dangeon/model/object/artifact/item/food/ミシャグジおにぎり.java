package dangeon.model.object.artifact.item.food;

import java.awt.Color;
import java.awt.Point;

import main.res.SE;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.R;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

public class ミシャグジおにぎり extends Food {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "腐ったおにぎり";

	public ミシャグジおにぎり(Point p) {
		super(p, item_name);
		sim = ENCHANT_SIMBOL.腐;
	}

	private void effect() {
		MainMap.addEffect(new GoodBadEffect(false));
		switch (new R().nextInt(4) + 1) {
		case 1:
			Message.set(Player.me.getColoredName(),
					"は体が蝕まれた$" + Color.ORANGE.toString() + "（HP半減＋邪法状態）"
							+ Color.WHITE.toString());
			Damage.damageNoMessage(this, "神に祟られて倒れた", null, Player.me,
					Player.me.getHP() / 2 + 1);
			Player.me.setCondition(CONDITION.邪法, 0);
			SE.SYSTEM_CURSE.play();
			break;
		case 2:
			Message.set(Player.me.getColoredName(),
					"は錯乱した$" + Color.ORANGE.toString() + "（混乱状態＋発狂）"
							+ Color.WHITE.toString());
			Player.me.setCondition(CONDITION.混乱, 0);
			if (EnchantSpecial
					.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.瞳)) {
			} else {
				TaskOnMapObject.setFlagSkipPlayerTurn(true);
			}
			SE.STATUS_PIYOPIYO.play();
			break;
		case 3:
			Message.set(Player.me.getColoredName(),
					"は猛烈にだるくなった$" + Color.ORANGE.toString() + "（睡眠状態＋鈍足状態）"
							+ Color.WHITE.toString());
			Player.me.setCondition(CONDITION.睡眠, 0);
			Player.me.setCondition(CONDITION.鈍足, 0);
			SE.STATUS_SLOW.play();
			SE.STATUS_SLEEP.play();
			break;
		default:
			Message.set(Player.me.getColoredName(), "の日頃の行いがよくて何も起こらなかった");
		}
	}

	// test
	@Override
	protected void foodUse() {
		if (Player.me.getMAX_SATIETY() <= Player.me.getSATIETY()) {
			Player.me.setMAX_SATIETY(Player.me.getMAX_SATIETY() + 5);
			Player.me.chengeSatiety(Player.me.getMAX_SATIETY());
			SE.IKAKUTYO.play();
			Message.set("最大満腹度が５上がった");
		} else {
			int plus_satiety = 110;
			Player.me.chengeSatiety(plus_satiety);
			Message.set("満腹度が１１０回復した");
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
}
