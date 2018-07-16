package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.古明地さとりのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;

public class 印見切り extends EnchantSpecial {
	private static int plus = 25;

	public static int effect() {
		if (enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.見切り)) {
			return SetEnchantCard.isSetCard(古明地さとりのカード.class) ? plus * 2 : plus;
		}
		return 0;
	}
}
