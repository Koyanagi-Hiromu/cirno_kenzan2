package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;

public class 印飛 extends EnchantSpecial {
	public static void attackEffect(Base_Creature creature) {
		if (!enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.飛)) {
			return;
		}
		flying(false, creature);
	}

	public static void deffenceEffect(Base_Creature creature) {
		if (!enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.飛)) {
			return;
		}
		flying(false, creature);
	}

	/**
	 * 高飛び
	 * 
	 * @param player
	 * @param count
	 * @param creature
	 */
	private static void flying(boolean player, Base_Creature creature) {
		int fly = 10;
		if (player) {
		} else {
			if (parcentCheck(fly, ENCHANT_SIMBOL.飛)) {
				creature.setMassPoint_Jump(MassCreater.getWarpPoint(null));
			}
		}
	}
}
