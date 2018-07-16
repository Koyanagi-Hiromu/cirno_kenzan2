package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.map.PresentField;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.きもけーねのカード;
import dangeon.model.object.artifact.item.spellcard.上白沢慧音のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;

public class 印教 extends EnchantSpecial {
	public static int effect(int exp) {

		if (enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.幸せ草)) {
			exp += (exp * 10 + 99) / 100;
		}
		if (enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.天使)) {
			exp += (exp * 20 + 99) / 100;
		}
		exp += SetEnchantCard.isSetCard(きもけーねのカード.class) ? exp * 25 / 100 : 0;
		if (enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.教)) {
			exp += SetEnchantCard.isSetCard(上白沢慧音のカード.class) ? exp * 50 / 100
					: exp * 25 / 100;
		}
		if (PresentField.isRandomField()) {
			exp = exp
					* ((Base_Map_Random) PresentField.get())
							.expRate_From1To100() / 100;
		}
		return exp;
	}
}
