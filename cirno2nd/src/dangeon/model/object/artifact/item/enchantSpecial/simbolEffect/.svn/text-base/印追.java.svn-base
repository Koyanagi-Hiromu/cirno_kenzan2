package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.awt.Point;

import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.一輪雲山のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;

public class 印追 extends EnchantSpecial {
	private static void attack(Base_Enemy e) {
		if (e.getHP() < 1) {
			return;
		}
		Player.me.additionalAttack();
	}

	public static void effect() {
		if (parcentCheck(SetEnchantCard.isSetCard(一輪雲山のカード.class) ? 40 : 20,
				ENCHANT_SIMBOL.追)) {
			if (!Player.me.attackWalkableCheck(Player.me.getMassPoint(),
					Player.me.getDirection())) {
				return;
			}
			Base_Enemy e = MapList.getEnemy(new Point(
					Player.me.getMassPoint().x + Player.me.getDirection().X,
					Player.me.getMassPoint().y + Player.me.getDirection().Y));
			if (e != null && !(e instanceof Base_NPC)) {
				attack(e);
			}
		}
	}
}
