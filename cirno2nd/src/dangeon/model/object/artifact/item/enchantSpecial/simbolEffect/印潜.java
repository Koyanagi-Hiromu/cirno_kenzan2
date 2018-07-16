package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.R;

public class 印潜 extends EnchantSpecial {
	private static boolean around(Base_Enemy em) {
		Point p = em.getMassPoint();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!MassCreater.getMass(p.x + i, p.y + j).WALKABLE) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean attack() {
		List<Base_Enemy> list = new ArrayList<Base_Enemy>();
		for (Base_Enemy em : MapList.getListEnemy()) {
			if (em instanceof Base_NPC) {
				continue;
			}
			if (em.getHP() <= 0) {
				continue;
			}
			if (MassCreater.isPlayerInTheSameRoom(em.getMassPoint())) {
				list.add(em);
			}
		}
		if (list.isEmpty()) {
			return false;
		}
		List<Base_Enemy> list2 = new ArrayList<Base_Enemy>();
		for (Base_Enemy em : list) {
			if (around(em)) {
				list2.add(em);
			}
		}
		if (list2.isEmpty()) {
			return false;
		}
		Damage.normalAttack(Player.me, list2.get(new R().nextInt(list2.size())));
		return true;
	}

	public static void effect() {
		Point p = Player.me.getMassPoint().getLocation();
		if (!MassCreater.getMass(p).ROOM) {
			return;
		}
		DIRECTION d = Player.me.getDirection();
		p.translate(d.X, d.Y);
		if (!MassCreater.getMass(p).WALKABLE) {
			attack();
		} else {
			// 壁がなかった場合
			if (EnchantSpecial
					.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.式)) {
				p.translate(d.X, d.Y);
				if (!MassCreater.getMass(p).WALKABLE) {
					attack();
				}
			}
		}
	}

	private static boolean isCreature(Point p) {
		Base_Creature c = MapList.getCreature(p);
		return c != null;
	}

	private static boolean water_around(Base_Enemy em) {
		Point p = em.getMassPoint();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!MassCreater.getMass(p.x + i, p.y + j).WATER) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean water_attack() {
		List<Base_Enemy> list = new ArrayList<Base_Enemy>();
		for (Base_Enemy em : MapList.getListEnemy()) {
			if (em instanceof Base_NPC) {
				continue;
			}
			if (em.getHP() <= 0) {
				continue;
			}
			if (MassCreater.isPlayerInTheSameRoom(em.getMassPoint())) {
				for (DIRECTION d : DIRECTION.values()) {
					Point p = em.getMassPoint().getLocation();
					if (MassCreater.getMass(d.getFrontPoint(p)).WATER) {
						list.add(em);
						break;
					}
				}
			}
		}
		if (list.isEmpty()) {
			return false;
		}
		boolean flag = false;
		for (Base_Enemy em : list) {
			if (water_around(em)) {
				if (!flag)
					Message.set("水を通して攻撃した！");
				Damage.normalAttack(Player.me, em);
				flag = true;
			}
		}
		return flag;
	}

	public static void water_effect() {
		Point p = Player.me.getMassPoint().getLocation();
		if (!MassCreater.getMass(p).ROOM) {
			return;
		}
		DIRECTION d = Player.me.getDirection();
		p.translate(d.X, d.Y);
		if (MassCreater.getMass(p).WATER) {
			if (!isCreature(p))
				water_attack();
		} else {
			// 水がなかった場合
			if (EnchantSpecial
					.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.式)) {
				p.translate(d.X, d.Y);
				if (MassCreater.getMass(p).WATER && !isCreature(p))
					water_attack();
			}
		}
	}
}
