package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.res.SE;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;

public class 印剣 extends EnchantSpecial {

	public static boolean effect() {
		if (!enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.剣)) {
			return false;
		}
		if (parcentCheck(40, ENCHANT_SIMBOL.剣)) {
			SE.ATTACK_SWORD.play();
			return true;
		}
		return false;
	}
}
