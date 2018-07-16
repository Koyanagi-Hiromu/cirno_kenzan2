package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.res.SE;
import dangeon.controller.ThrowingItem;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.enemy.ルーミア;
import dangeon.model.object.creature.player.Player;

public class 印食 extends EnchantSpecial {
	public static boolean effect(ThrowingItem throwingItem) {
		if (!enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.食)) {
			return false;
		}
		if (parcentCheck(30, ENCHANT_SIMBOL.食)) {
			Player.me.setStandEffect(ルーミア.class, throwingItem.A.getDirection()
					.getReverse());
			SE.ATTACK_EAT.play();
			Player.me.chengeSatiety(10);
			return true;
		}
		return false;
	}

	ENCHANT_SIMBOL sim = ENCHANT_SIMBOL.食;
}
