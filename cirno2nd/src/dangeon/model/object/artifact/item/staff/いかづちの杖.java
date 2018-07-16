package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.ThunderDamage;

public class いかづちの杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "いかづちの杖";
	public static final String item_name_sound = "いかつちのつえ";
	private static int damage = 30;

	public いかづちの杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(Base_Creature c) {
		int dam = damage;
		if (Player.me.equals(c)
				&& EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
						ENCHANT_SIMBOL.魔)) {
			dam /= 2;
		}
		ThunderDamage.thunderDamage(used_creature, used_creature, c, dam);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "電気は伝播するので、気をつけよう";
	}

}