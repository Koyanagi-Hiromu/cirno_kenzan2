package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.player.Player;

public class 印回復 {

	public static void selfHeal() {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.死)) {
			int heal = Player.me.getMAX_HP() * 10 / 300;
			if (heal < 5)
				heal = 5;
			if (Player.me.getHP() < Player.me.getMAX_HP()) {
				Player.me.heal(Player.me, heal);
			}
		}
	}
}
