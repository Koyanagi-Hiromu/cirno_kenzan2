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
public class 封印の杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "封印の杖";
	public static final String item_name_sound = "ふういんのつえ";

	public 封印の杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(Base_Creature c) {
		SE.STATUS_SEAL.play();
		int t;
		if (Player.me.equals(c)
				&& EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
						ENCHANT_SIMBOL.魔)) {
			t = -1;
		} else {
			t = 0;
		}
		c.setCondition(CONDITION.封印, t);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "封印状態の敵は特殊能力が使用できなくなる。また封印状態以外の状態が解除され状態異常にかからなくなる。チルノがこの杖を振られるとクチナシ状態になる。";
	}

}