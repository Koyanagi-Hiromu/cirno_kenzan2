package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.food.ナツメおにぎり;
import dangeon.model.object.artifact.item.spellcard.ミスティアのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.Base_Creature;

public class 印鰻 extends EnchantSpecial {
	public static boolean effect(Base_Creature c) {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.鰻)) {
			if (parcentCheck(SetEnchantCard.isSetCard(ミスティアのカード.class) ? 6 : 3,
					ENCHANT_SIMBOL.鰻)) {
				ItemFall.itemFall(c.getMassPoint(),
						new ナツメおにぎり(c.getMassPoint()));
				return true;
			}
		}
		return false;
	}
}
