package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

/**
 * 杖
 * 
 * @author Administrator
 * 
 */
public class 回復の杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "回復の杖";
	public static final String item_name_sound = "かいふくのつえ";

	public 回復の杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(Base_Creature c) {
		if (c.conditionCheck(CONDITION.倍速) || c.conditionCheck(CONDITION.三倍速))
			Medal.倍速の敵に回復の杖を振った.addCount();
		for (CONDITION condition : c.getConditionList()) {
			CONDITION.conditionRecovery(c, condition);
		}
		for (CONDITION con : c.getConditionRemoveTask()) {
			c.getConditionList().remove(con);
		}
		c.getConditionRemoveTask().clear();
		int t;
		if (Player.me.equals(c)
				&& EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
						ENCHANT_SIMBOL.魔)) {
			t = 50;
		} else {
			t = 100;
		}
		c.heal(used_creature, t);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "状態異常はあらゆるものをリセットする。倍速の敵に振ってやろう。なお幽霊っぽい敵も普通に回復します。";
	}

}