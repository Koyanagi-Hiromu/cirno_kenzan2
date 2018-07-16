package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;

public class 印面 extends EnchantSpecial {
	public static boolean effect(Base_Creature c) {
		if (!EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.面)) {
			return false;
		}
		for (CONDITION con : c.getConditionList()) {
			if (con.isGood()) {
				return true;
			}
		}
		return false;
	}
}