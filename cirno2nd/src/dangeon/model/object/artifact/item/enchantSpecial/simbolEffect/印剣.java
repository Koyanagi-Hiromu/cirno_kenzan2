package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.controller.ThrowingItem;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Player;
import main.res.SE;
import main.util.DIRECTION;

public class 印剣 extends EnchantSpecial {

	public static boolean effect(ThrowingItem ti) {
		if (!enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.剣)) {
			return false;
		}
		if (DIRECTION.isNeiborOrSame(Player.me.direction.getReverse(), ti.A.direction)) {
			SE.ATTACK_SWORD.play();
			return true;
		}
		return false;
	}
}
