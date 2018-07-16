package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.伊吹萃香のカード;
import dangeon.model.object.artifact.item.spellcard.星熊勇儀のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;

public class 印会心 extends EnchantSpecial {
	public static boolean effect() {
		int critical = 50;
		if (enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.会心)) {
			if (parcentCheck(critical, ENCHANT_SIMBOL.会心)) {
				return true;
			}
		}
		int oni = 25;
		if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.鬼)) {
			if (parcentCheck(SetEnchantCard.isSetCard(星熊勇儀のカード.class) ? oni * 2
					: oni, ENCHANT_SIMBOL.鬼)) {
				return true;
			}
		}
		int mitsu = 25;
		if (SetEnchantCard.isSetCard(伊吹萃香のカード.class)) {
			if (parcentCheck(mitsu, ENCHANT_SIMBOL.酔)) {
				return true;
			}
		}
		if (印邪.critical()) {
			return true;
		}
		return false;
	}
}
