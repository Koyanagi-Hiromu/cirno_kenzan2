package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.util.DIRECTION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 印超人 extends EnchantSpecial {
	public static void effect(Base_Creature target) {
		if (!enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.超人)) {
			return;
		}
		if (parcentCheck(40, ENCHANT_SIMBOL.超人)) {
			DIRECTION d = Player.me.converDirection(target.getMassPoint());
			MapInSelect.吹き飛ばし(Player.me, "殴り飛ばして", target, d, 10, 5);
		}
		// attack(new Point(Player.me.getMassPoint().x
		// - Player.me.getDirection().X, Player.me.getMassPoint().y
		// - Player.me.getDirection().Y));
	}
}
