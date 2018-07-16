package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;

public class 印温 extends EnchantSpecial {
	public static int effect() {
		int freeze_turn = 100;
		int special_freeze_turn = 15;
		if (enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.温)) {
			return special_freeze_turn;
		}
		return freeze_turn;
	}
}
