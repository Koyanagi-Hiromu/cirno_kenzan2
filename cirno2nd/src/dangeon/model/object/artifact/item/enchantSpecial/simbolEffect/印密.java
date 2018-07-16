package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;

public class 印密 extends EnchantSpecial {
	private static boolean flag = false;

	public static void effect() {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.酔)) {
			setFlag(true);
		}
	}

	public static boolean isFlag() {
		return flag;
	}

	public static void setFlag(boolean b) {
		flag = b;
	}
}
