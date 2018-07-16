package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.射命丸文のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Player;

public class 印避 extends EnchantSpecial {
	private static int plus = 25;

	public static void effect() {
		if (SetEnchantCard.isSetCard(射命丸文のカード.class)) {
			if (parcentCheck(1, ENCHANT_SIMBOL.身躱し)) {
				Player.me.setCondition(CONDITION.倍速, 20);
			}
		}
	}
}
