package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;

public class 印連 extends EnchantSpecial {

	private static boolean flag = false;

	public static boolean effect() {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.連)) {
			return true;
		}
		return false;
		// Player.me.additionalAttackYoumu();
	}
}
