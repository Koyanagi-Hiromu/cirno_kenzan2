package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class 印小 extends EnchantSpecial {
	static ENCHANT_SIMBOL sim = ENCHANT_SIMBOL.小;

	public static void effect(Base_Creature creature) {
		if (!enchantSimbolAllCheck(sim)) {
			return;
		}
		if (creature.getHP() <= 0) {
			return;
		}
		((Base_Enemy) creature).changeSize(-1);
	}
}
