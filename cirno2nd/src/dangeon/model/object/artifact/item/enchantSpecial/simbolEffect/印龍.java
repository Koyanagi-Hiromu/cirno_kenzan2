package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.bullet.ドラゴンブレス;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.紅美鈴のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.ThunderDamage;

public class 印龍 extends EnchantSpecial {
	public static void effect() {
		effect_fire();
		effect_thunder();
	}

	public static void effect_fire() {
		if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.龍)) {
			if (Player.me.getMAX_HP() == Player.me.getHP()) {
				if (MapInSelect.getFrontEnemyFromCreature(Player.me) != null) {
					if (Player.me.attackWalkableCheck(Player.me.getMassPoint(),
							Player.me.direction)) {
						return;
					}
				}
				if (MapInSelect.getStraightHitCreature(
						Player.me.getDirection(), Player.me.getMassPoint(), 0) == null) {
					return;
				}
				fire();
			}
		}
	}

	public static void effect_thunder() {
		if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.雷)) {
			if (MapInSelect.getFrontEnemyFromCreature(Player.me) != null) {
				return;
			}
			Room r = MassCreater.getPlayerRoom();
			if (r != null) {
				for (Point p : r.getListAllMassPointInRoom()) {
					if (MassCreater.getMass(p).WATER) {
						for (DIRECTION d : DIRECTION.values()) {
							Point point = new Point(p.x + d.X, p.y + d.Y);
							if (MapList.isCreature(point)) {
								thunder(p);
								return;
							}
						}
					}
				}
			}
		}
	}

	private static void fire() {
		int lv = Player.me.getLV();
		int damage = (Player.me.getLV() / 20 + 1) * 10;
		if (lv < 20) {
			damage = 10;
		} else if (lv < 30) {
			damage = 15;
		} else if (lv < 40) {
			damage = 17;
		} else if (lv < 50) {
			damage = 21;
		} else if (lv < 60) {
			damage = 25;
		} else if (lv < 70) {
			damage = 30;
		} else if (lv < 80) {
			damage = 35;
		} else if (lv < 99) {
			damage = 42;
		} else {
			damage = 70;
		}

		damage *= SetEnchantCard.isSetCard(紅美鈴のカード.class) ? 2 : 1;
		new ドラゴンブレス(Player.me, damage).itemThrow(Player.me, HowToThrow.MAGIC,
				Math.max(MassCreater.WIDTH, MassCreater.HEIGHT));
	}

	private static void thunder(Point p) {
		ThunderDamage.thunderDamage(Player.me, Player.me, p, 25);
	}
}
