package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Player;

public class 印超不幸 extends EnchantSpecial {

	public static boolean effect() {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.絶)) {
			Player.me.expCashReset();
			return true;
		}
		return false;
	}
}
