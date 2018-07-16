package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

public class 氷結草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "感電草";
	public static final String item_name_sound = "かんてんそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.冴;

	public 氷結草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	private int cold(Base_Creature c) {
		if (isFrozen()) {
			return c.getMAX_HP();
		} else if (this.isCold()) {
			return 100;
		} else {
			return 50;
		}
	}

	private void conditoin() {
		if (isFrozen()) {
			MainMap.addEffect(new GoodBadEffect(true));
			SE.STATUS_MEGUSURI.play();
			Player.me.setCondition(CONDITION.回復, 50);
		} else if (isCold()) {
			MainMap.addEffect(new GoodBadEffect(true));
			SE.STATUS_MEGUSURI.play();
			Player.me.setCondition(CONDITION.回復, 50);
		} else {
		}

	}

	@Override
	protected void effect(Base_Creature c) {
		c.heal(Player.me, cold(c));
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "感電し草";
	}

	@Override
	public boolean grassUse() {
		if (Player.me.getHP() < Player.me.getMAX_HP()) {
			int h = cold(Player.me);
			// Message.set("HPが", 半角全角コンバーター.半角To全角数字(h), "ポイント回復した");
			Player.me.chengeHP(this, null, h);
		} else {
			int max_heal;
			if (isFrozen()) {
				max_heal = 5;
			} else if (isCold()) {
				max_heal = 3;
			} else {
				max_heal = 1;
			}
			if (EnchantSpecial
					.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.医)) {
				max_heal = max_heal + 1;
			}
			Message.set(new String[] { "最大HPが", max_heal + "ポイント増えた" });
			Player.me.addMAX_HP(max_heal);
			Player.me.chengeHP(null, null, max_heal);
		}
		conditoin();
		return false;
	}

}
