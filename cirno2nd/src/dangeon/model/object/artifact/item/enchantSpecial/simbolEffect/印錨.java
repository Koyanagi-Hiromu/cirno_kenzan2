package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.bullet.アンカー;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;

public class 印錨 extends EnchantSpecial {
	public static void effect() {
		if (parcentCheck(20, ENCHANT_SIMBOL.イカリ)) {
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
				new アンカー(Player.me).itemThrow(Player.me, HowToThrow.BREAK);
			}
		}
	}
}
