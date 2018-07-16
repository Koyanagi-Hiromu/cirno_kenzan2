package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.artifact.item.spellcard.朱鷺子のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Enchant;

public class 印鴇 extends EnchantSpecial {
	private static int plus = 5;
	static ENCHANT_SIMBOL sim = ENCHANT_SIMBOL.鴇;

	public static int effect() {
		if (enchantSimbolAllCheck(CASE.ALL, sim)) {
			for (Base_Artifact a : Enchant.getEnchantList()) {
				if (a instanceof Scrool) {
					return SetEnchantCard.isSetCard(朱鷺子のカード.class) ? plus * 2
							: plus;
				}
			}
		}
		return 0;
	}
}
