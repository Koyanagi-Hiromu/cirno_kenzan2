package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.三月精のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;

public class 印罠師 extends EnchantSpecial {
	public static boolean effect() {
		if (enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.罠)) {
			return true;
		}
		if (enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.ワナ)) {
			return true;
		}
		if (PresentField.get().isTrapMasterDungeon()) {
			return true;
		}
		if (SetEnchantCard.isSetCard(三月精のカード.class)) {
			return true;
		}
		return false;
	}
}
