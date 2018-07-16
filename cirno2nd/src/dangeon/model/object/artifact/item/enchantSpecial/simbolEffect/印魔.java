package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.アリスのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;

public class 印魔 extends EnchantSpecial {
	public static int effect(int damage) {
		if (!enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.魔)) {
			return damage;
		}
		return SetEnchantCard.isSetCard(アリスのカード.class) ? damage / 4
				: damage / 2;
	}
}
