package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.util.ArrayList;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.grass.Base_Grass;
import dangeon.model.object.artifact.item.spellcard.風見幽香のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Enchant;

public class 印華 extends EnchantSpecial {
	public static int effect(int i) {
		return SetEnchantCard.isSetCard(風見幽香のカード.class) ? 印ダメージUP.damage20(i)
				: 印ダメージUP.damage15(i);
	}

	public static boolean effectCheck() {
		if (!enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.華)) {
			return false;
		}
		ArrayList<Enchant> list = Enchant.getListANY();
		if (list.isEmpty() || list.size() < 2) {
			return false;
		}
		for (Enchant e : list) {
			if (!(e.getEnchant() instanceof Base_Grass)) {
				return false;
			}
		}
		return parcentCheck(50, ENCHANT_SIMBOL.華);
	}
}
