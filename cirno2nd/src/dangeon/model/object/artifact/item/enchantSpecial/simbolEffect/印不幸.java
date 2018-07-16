package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;

public class 印不幸 extends EnchantSpecial {
	public static void attackEffect(Base_Creature creature) {
		if (creature.getLV() == 2 || creature.getLV() == 3) {
			int unhappy = 12;
			if (!enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.不幸)) {
				return;
			}
			if (parcentCheck(unhappy, ENCHANT_SIMBOL.不幸)) {
				creature.addLV(-1);
			}

		}
	}

	public static void deffenceEffect(Base_Creature creature) {
		if (creature.getLV() == 2 || creature.getLV() == 3) {
			int unhappy = 12;
			if (!enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.不幸)) {
				return;
			}
			if (parcentCheck(unhappy, ENCHANT_SIMBOL.不幸)) {
				creature.addLV(-1);
			}
		}
	}
}
