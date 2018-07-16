package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Player;

public class 印薬草 extends EnchantSpecial {
	private static int heal = 4;

	public static void attackEffect() {
		if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.薬)) {
			Player.me.chengeHP_NoEffect(eirin());
		}
	}

	public static void deffenceEffect() {
		if (enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.薬)) {
			Player.me.chengeHP_NoEffect(eirin());
		}
	}

	private static int eirin() {
		return EnchantSpecial.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.医) ? heal + 2
				: heal;
	}
}
