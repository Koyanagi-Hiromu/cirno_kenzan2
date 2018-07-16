package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.蘇我屠自古のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.ThunderDamage;

public class 印雷 extends EnchantSpecial {

	public static void effect(Base_Creature creature) {
		int effect = 25;
		if (!enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.電)) {
			return;
		}
		if (parcentCheck(SetEnchantCard.isSetCard(蘇我屠自古のカード.class) ? effect * 2
				: effect, ENCHANT_SIMBOL.電)) {
			ThunderDamage.thunderDamage(Player.me, Player.me, creature, 15);
		}
	}
}
