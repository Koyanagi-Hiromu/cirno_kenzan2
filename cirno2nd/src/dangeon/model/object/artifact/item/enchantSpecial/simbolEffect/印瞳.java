package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.イナバのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.Base_Creature;

public class 印瞳 extends EnchantSpecial {
	public static void effect(Base_Creature c) {
		if (SetEnchantCard.isSetCard(イナバのカード.class)) {
			if (parcentCheck(20, ENCHANT_SIMBOL.瞳)) {
				c.setCondition(CONDITION.混乱, 0);
			}
		}
	}

	int parcent = 0;
}
