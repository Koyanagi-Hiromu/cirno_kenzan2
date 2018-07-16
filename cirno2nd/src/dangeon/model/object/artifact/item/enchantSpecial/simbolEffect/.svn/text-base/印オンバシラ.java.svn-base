package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;

public class 印オンバシラ extends EnchantSpecial {
	public static void effect() {
		if (parcentCheck(40, ENCHANT_SIMBOL.オンバシラ)) {
			DIRECTION d = Player.me.getDirection();
			if (!Player.me.attackWalkableCheck(Player.me.getMassPoint(), d)) {
				return;
			}
			Point p = new Point(Player.me.getMassPoint().x + d.X,
					Player.me.getMassPoint().y + d.Y);
			if (MapList.getEnemy(p) != null
					&& !(MapList.getEnemy(p) instanceof Base_NPC)) {
				if (MapList.getEnemy(p).getHP() <= 0) {
					return;
				}
				Player.me.additionalThrow();
			}
		}
		// attack(new Point(Player.me.getMassPoint().x
		// - Player.me.getDirection().X, Player.me.getMassPoint().y
		// - Player.me.getDirection().Y));
	}
}
