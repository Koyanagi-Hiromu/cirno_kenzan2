package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;

public class 印戯 extends EnchantSpecial {
	public static int effect(int i) {
		if (!EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.戯)) {
			return i;
		}
		return 0;
	}

	public static boolean isEnchant() {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.戯)) {
			return true;
		}
		return false;
	}

}
