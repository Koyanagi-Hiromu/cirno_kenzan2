package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import main.res.SE;
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
public class 倍速の杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "倍速の杖";
	public static final String item_name_sound = "はいそくのつえ";

	public 倍速の杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(Base_Creature c) {
		SE.STATUS_SPEEDY.play();
		int t;
		if (Player.me.equals(c)
				&& EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
						ENCHANT_SIMBOL.魔)) {
			t = -1;
		} else {
			t = 0;
		}
		c.setCondition(CONDITION.倍速, t);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "倍速状態の敵は二回行動をする。更に倍速状態がかかると、三回行動になる。……後ろだ";
	}
}