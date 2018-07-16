package dangeon.util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.util.DIRECTION;
import main.util.半角全角コンバーター;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印炎;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.action.Tumble;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.ExplosionEffect;
import dangeon.view.detail.MainMap;

public class MapInSelect {
	public static void around8Damage(Point p, Object obj, String str, int damage) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (MapList.getEnemy(p.x + i, p.y + j) != null) {
					damage(obj, str, MapList.getEnemy(p.x + i, p.y + j), damage);
				}
			}
		}
	}

	/**
	 * 引数のポイントの周囲の空白マスのリストを貸せス 水路を除外する場合は引数にfalseを追加
	 */
	public static List<Point> aroundEmptyCheck(Point p) {
		List<Point> list = new ArrayList<Point>();
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			Point point = new Point(p.x + d.X, p.y + d.Y);
			if (MapList.getCreature(point) != null) {
				continue;
			}
			if (!MassCreater.getMass(point).WALKABLE) {
				continue;
			}
			if (MassCreater.getMass(point).WATER) {
				continue;
			}
			list.add(point);
		}
		return list;
	}

	/**
	 * 引数のポイントの周囲の空白マスのリストを貸せス
	 */
	public static List<Point> aroundEmptyCheck(Point p, boolean water_ng) {
		List<Point> list = new ArrayList<Point>();
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			Point point = new Point(p.x + d.X, p.y + d.Y);
			if (MapList.getCreature(point) != null) {
				continue;
			}
			if (!MassCreater.getMass(point).WALKABLE) {
				continue;
			}
			if (water_ng && MassCreater.getMass(point).WATER) {
				continue;
			}
			list.add(point);
		}
		return list;
	}

	public static boolean aroundInEnemyChengeCondition(CONDITION c, int time) {
		boolean flag = false;
		for (Base_Creature em : MapList.getListAroundCreature(Player.me
				.getMassPoint())) {
			flag = true;
			em.setCondition(c, time);
		}
		return flag;
	}

	private static CONDITION conditionSelect(CONDITION c) {
		if (c == null) {
			return CONDITION.conditionSelect(null);
		} else {
			return c;
		}
	}

	/**
	 * 
	 * @param p
	 * @param value
	 * @param level
	 */
	public static void createEnemy(Point p, int value, int level,
			boolean actioned) {
		List<Point> list = aroundEmptyCheck(p);
		if (list.isEmpty()) {
			return;
		}
		for (int i = 0; i < value; i++) {
			if (list.size() == i) {
				break;
			}
			EnemyTable.setOnMapPointAndLevel(list.get(i), level, actioned);
		}
	}

	public static void damage(Object obj, String str, Base_Creature em,
			int damage) {
		Message.set(new String[] { em.getColoredName().concat("に")
				+ 半角全角コンバーター.半角To全角数字(damage) + "のダメージを与えた" });
		if (obj instanceof Base_Creature) {
			Damage.damageNoMessage(obj, str, (Base_Creature) obj, em, damage);
		} else {
			Damage.damageNoMessage(obj, str, Player.me, em, damage);
		}
	}

	public static void damage(Object obj, String str, Base_Enemy em, int damage) {
		if (obj instanceof Base_Creature) {
			Damage.damage(obj, null, str, (Base_Creature) obj, em, damage);
		} else {
			Damage.damage(obj, null, str, Player.me, em, damage);
		}
	}

	/**
	 * フロアの敵にダメージを与える
	 * 
	 * @param damage
	 * @param at
	 */
	public static void damageIcy_toAllEnemies(Object obj, String str, int damage) {
		if (damage <= 0)
			damage = 1;
		List<Base_Enemy> list = new ArrayList<Base_Enemy>();
		// for (Iterator<Base_Enemy> i = list.iterator(); i.hasNext();) {
		// damage(i.next(), damage, at);
		// }
		for (Base_Enemy em : MapList.getListEnemy()) {
			if (!(em instanceof Base_NPC))
				list.add(em);
		}
		for (Base_Enemy c : list) {
			damage(obj, str, c, damage * (c.isIce() ? -1 : 1));
			if (c.conditionCheck(CONDITION.炎上)) {
				c.setConditionRemoveTask(CONDITION.炎上);
			}
		}
	}

	/**
	 * 敵一体に効果エフェクトをかける
	 */
	public static void effectGraphics(Base_Creature c) {

	}

	public static void explosion(final Point p) {
		MainMap.addEffect(new ExplosionEffect(p, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				explosion_effect(p);
				MassCreater.setExplotionMass(p);
			}
		}), true);
	}

	/**
	 * 爆発をポイントを中心に起す
	 */
	public static void explosion_effect(Point... ps) {
		for (Point point : ps) {
			explosion_effect(point);
		}
		MassCreater.setExplotionMass(ps);
	}

	/**
	 * 爆発をポイントを中心に起す
	 */
	private static void explosion_effect(Point p) {
		List<Base_Creature> list = MapList
				.getSurroundedCreature_IncludingMyself(p);
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				Base_Artifact a = MapList.getArtiface(p.x + i, p.y + j);
				if (a != null && a instanceof Base_Item) {
					TaskOnMapObject.addMapItemRemoveTask(a);
				}
			}
		}
		for (Base_Creature c : list) {
			if (!c.getMassPoint().equals(p)) {
				Message.set(c.getColoredName(), "は爆発に巻き込まれた");
			}
			if (c instanceof Player) {
				印炎.expEffect();
			} else if (c.isFire()) {
				c.damagedWithFire(0);
			} else {
				c.setBombedAway();
			}
		}
	}

	public static void explotionDamage(Point p, Base_Creature c, String str,
			int damage) {
		int DAMAGE;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				Base_Creature passive = MapList.getCreature(p.x + i, p.y + j);
				if (passive != null) {
					DAMAGE = passive.damagedWithFire(damage);
					if (passive instanceof Player) {
						DAMAGE = 印炎.expDamege(DAMAGE);
					}
					Damage.damage(c, null, str, c,
							MapList.getCreature(p.x + i, p.y + j), DAMAGE);
				}
			}
		}
	}

	public static Base_Enemy getEntoStraight() {
		return null;
	}

	public static Base_Enemy getFrontEnemyFromCreature(Base_Creature c) {
		return getFrontEnemyFromPoint(c.getMassPoint().x + c.getDirection().X,
				c.getMassPoint().y + c.getDirection().Y);
	}

	public static Base_Enemy getFrontEnemyFromPoint(int x, int y) {
		return MapList.getEnemy(x, y);
	}

	public static Base_Creature getHitCreature吹き飛ばし(final Object obj,
			final String str, final Base_Creature target, final DIRECTION d,
			final int range) {
		if (target instanceof Player) {
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
					ENCHANT_SIMBOL.イカリ)) {
				return null;
			}
		}
		Point p = target.getMassPoint().getLocation();
		Base_Creature c = null;
		int i = 0;
		while (true) {
			i++;
			if (!isRootWalkCheck(p, d) && !target.isAbleToIgnoreWalls()) {
				c = MapList.getCreature(p.x + d.X, p.y + d.Y);
				break;
			}
			p.x += d.X;
			p.y += d.Y;
			c = MapList.getCreature(p);
			if (c != null) {
				p.x -= d.X;
				p.y -= d.Y;
				break;
			}
			if (i != 0 && i >= range) {
				break;
			}
		}
		return c;
	}

	public static ArrayList<Base_Creature> getList8DirectionAttackHitCreature(
			Point p, int length) {
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		for (DIRECTION dir : DIRECTION.get8Direction()) {
			list.add(getStraightAttackHitCreature(dir, p, length));
		}
		return list;
	}

	public static ArrayList<Point> getListAroundEmptyMass(Point p) {
		ArrayList<Point> list = new ArrayList<Point>();
		for (DIRECTION d : DIRECTION.get8Direction()) {
			Point p2 = new Point(p.x + d.X, p.y + d.Y);
			if (isEmptyPoint(p2)) {
				list.add(p2);
			}
		}
		return list;
	}

	public static List<Base_Creature> getListAroundInCreature(Point p) {
		List<Base_Creature> list = new ArrayList<Base_Creature>();
		list = MapList.getListAroundCreature(p);

		return list;
	}

	public static ArrayList<Base_Creature> getListCreatureOnTheStraight8Direction(
			Point p, int length) {
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		for (DIRECTION d : DIRECTION.get8Direction()) {
			for (Base_Creature _c : getListStraightHitCreature(d, p, length)) {
				list.add(_c);
			}
		}
		return list;
	}

	public static ArrayList<Base_Creature> getListCreatureOnTheStraight8Direction_OnlyFirst(
			Point p, int length) {
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		for (DIRECTION d : DIRECTION.get8Direction()) {
			Base_Creature e = getListStraightHitCreature_OnlyFirst(d, p, length);
			if (e != null)
				list.add(e);
		}
		return list;
	}

	public static ArrayList<Base_Artifact> getListRoomInArtifact(Point p) {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : MapList.getListArtifact()) {
			if (MassCreater.isPointInTheSameRoomInEntrance(
					Player.me.getMassPoint(), a.getMassPoint())) {
				list.add(a);
			}
		}
		return list;
	}

	public static ArrayList<Base_Creature> getListRoomInCreatureAtPoint(Point p) {
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>(MapList
				.getListEnemy().size());
		for (Base_Creature c : MapList.getListCreature()) {
			if (MassCreater.isPointInTheSameRoomInEntrance(p, c.getMassPoint())) {
				list.add(c);
			}
		}
		return list;
	}

	/**
	 * 
	 * @return エントランスも含む
	 */
	public static List<Base_Enemy> getListRoomInEnemy() {
		List<Base_Enemy> list = new ArrayList<Base_Enemy>(MapList
				.getListEnemy().size());
		for (Base_Enemy e : MapList.getListEnemy()) {
			if (MassCreater.isPointInTheSameRoomInEntrance(
					Player.me.getMassPoint(), e.getMassPoint())) {
				list.add(e);
			}
		}
		return list;
	}

	public static ArrayList<Base_MapObject> getListRoomInObject(Point p) {
		ArrayList<Base_MapObject> list = new ArrayList<Base_MapObject>();
		for (Base_Enemy em : getListRoomInEnemy()) {
			list.add(em);
		}
		for (Base_Artifact a : getListRoomInArtifact(p)) {
			list.add(a);
		}
		return list;
	}

	public static List<Base_Artifact> getListRoomOrRoadInArtifact() {
		if (MassCreater.getMass(Player.me.getMassPoint()).ROOM) {
			return roomInArtifact();
		} else {
			return MapList.getListAroundArtifact(Player.me.getMassPoint());
		}
	}

	/**
	 * 部屋または通路のクリチャーリストを返す
	 * 
	 * @param bs
	 * @return
	 */
	public static List<Base_Creature> getListRoomOrRoadInCreature(
			Base_Creature bs) {
		if (MassCreater.getMass(bs.getMassPoint()).ROOM) {
			return getListRoomInCreatureAtPoint(bs.getMassPoint());
		}
		if (MassCreater.getMass(bs.getMassPoint()).ROAD) {
			return getListAroundInCreature(bs.getMassPoint());
		}
		return null;
	}

	public static List<Base_Enemy> getListRoomOrRoadInEnemy() {
		if (MassCreater.getMass(Player.me.getMassPoint()).ROOM) {
			return roomInEnemy();
		} else {
			return MapList.getListAroundEnemy(Player.me.getMassPoint());
		}
	}

	public static List<Base_Creature> getListStraightHitCreature(DIRECTION dir,
			Point p, int length) {
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		Point point = new Point(p);
		if (length == 0) {
			while (true) {
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					list.add(MapList.getCreature(point));
					continue;
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		} else {
			for (int i = 0; i < length; i++) {
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					list.add(MapList.getCreature(point));
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		}
		return list;
	}

	public static List<Base_Creature> getListStraightHitCreature(DIRECTION dir,
			Point p, int length, boolean b) {
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		Point point = p.getLocation();
		if (length == 0) {
			while (true) {
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					list.add(MapList.getCreature(point));
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		} else {
			for (int i = 0; i < length; i++) {
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					list.add(MapList.getCreature(point));
				}
				if (!MassCreater.getMass(point).WALKABLE && !b) {
					break;
				}
			}
		}
		return list;
	}

	public static Base_Creature getListStraightHitCreature_OnlyFirst(
			DIRECTION dir, Point p, int length) {
		Point point = new Point(p);
		if (length == 0) {
			while (true) {
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					return MapList.getCreature(point);
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		} else {
			for (int i = 0; i < length; i++) {
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					return MapList.getCreature(point);
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		}
		return null;
	}

	/**
	 * 部屋内の敵にダメージを与えるときなどに呼ぶ<br/>
	 * 通路時は隣接のをげっと<br/>
	 * 視点は主人公<br/>
	 */
	public static Iterator<Base_Enemy> getRoomOrRoadInEnemyList() {
		if (MassCreater.getMass(Player.me.getMassPoint()).ROOM) {
			return roomInEnemy().iterator();
		} else {
			return MapList.getListAroundEnemy(Player.me.getMassPoint())
					.iterator();
		}

	}

	/**
	 * 直線状の敵を返す
	 * 
	 * @param dir
	 * @param p
	 * @param length
	 * @return
	 */
	public static Base_Creature getStraightAttackHitCreature(DIRECTION dir,
			Point p, int length) {
		Point point = p.getLocation();
		if (length == 0) {
			while (true) {
				if (!isRootWalkableCheck(point, dir)) {
					break;
				}
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					return MapList.getCreature(point);
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		} else {
			for (int i = 0; i < length; i++) {
				if (!isRootWalkableCheck(point, dir)) {
					break;
				}
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					return MapList.getCreature(point);
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		}
		return null;
	}

	/**
	 * 直線状の敵を返す
	 * 
	 * @param dir
	 * @param p
	 * @param length
	 * @return
	 */
	public static Base_Creature getStraightHitCreature(DIRECTION dir, Point p,
			int length) {
		Point point = p.getLocation();
		if (length == 0) {
			while (true) {
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					return MapList.getCreature(point);
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		} else {
			for (int i = 0; i < length; i++) {
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					return MapList.getCreature(point);
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		}
		return null;
	}

	public static Base_MapObject getStraightHitObject(DIRECTION dir, Point p,
			int length) {
		Point point = p.getLocation();
		if (length == 0) {
			while (true) {
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					return MapList.getCreature(point);
				}
				if (MapList.getArtifact(point) != null) {
					return MapList.getArtifact(point);
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		} else {
			for (int i = 0; i < length; i++) {
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					return MapList.getCreature(point);
				}
				if (MapList.getArtifact(point) != null) {
					return MapList.getArtifact(point);
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		}
		return null;
	}

	public static Point getStraightHitPoint(DIRECTION dir, Point p, int length) {
		Point point = p;
		if (length == 0) {
			while (true) {
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					break;
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		} else {
			for (int i = 0; i < length; i++) {
				point.x += dir.X;
				point.y += dir.Y;
				if (MapList.getCreature(point) != null) {
					break;
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		}
		return point;
	}

	public static Base_Creature getStraightMoveCheck(DIRECTION dir, Point p,
			int length) {
		Point point = p.getLocation();
		if (length == 0) {
			while (true) {
				if (moveCheckPoint(point, dir)) {
					point.x += dir.X;
					point.y += dir.Y;
					return MapList.getCreature(point);
				}
				point.x += dir.X;
				point.y += dir.Y;
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		} else {
			for (int i = 0; i < length; i++) {
				if (moveCheckPoint(point, dir)) {
					point.x += dir.X;
					point.y += dir.Y;
					return MapList.getCreature(point);
				}
				point.x += dir.X;
				point.y += dir.Y;
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		}
		return null;
	}

	public static boolean isAroundEmptyPoint(Point p) {
		for (DIRECTION d : DIRECTION.get8Direction()) {
			if (isEmptyPoint(new Point(p.x + d.X, p.y + d.Y))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isCreatureOnStraight(DIRECTION dir, Base_Creature c,
			Point p, int range) {
		Point point = p.getLocation();
		if (range == 0) {
			while (true) {
				point.x += dir.X;
				point.y += dir.Y;
				Base_Creature c2 = MapList.getCreature(point);
				if (c2 != null) {
					if (c2 == c) {
						return true;
					}
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		} else {
			for (int i = 0; i < range; i++) {
				point.x += dir.X;
				point.y += dir.Y;
				Base_Creature c2 = MapList.getCreature(point);
				if (c2 != null) {
					if (c2 == c) {
						return true;
					}
				}
				if (!MassCreater.getMass(point).WALKABLE) {
					break;
				}
			}
		}
		return false;
	}

	/**
	 * sourceの直線上にtargetが存在するかチェック 壁は貫通しない
	 * 
	 * @param source
	 * @param target
	 * @param range
	 * @return
	 */
	public static boolean isCreatureOnTheStraightAllDirection(
			Base_Creature source, Base_Creature target, int range) {
		for (DIRECTION d : DIRECTION.get8Direction()) {
			if (getListStraightHitCreature(d, source.getMassPoint(), range)
					.contains(target)) {
				return true;
			}
		}
		return false;
	}

	private static Base_Creature isCreatureOrNull(Object obj) {
		return obj instanceof Base_Creature ? (Base_Creature) obj : null;
	}

	public static boolean isEmptyArtifactPoint(Point p) {
		if (MassCreater.getMass(p).ROOM || MassCreater.getMass(p).ROAD) {
			if (MapList.getArtifact(p) == null) {
				return true;
			} else {
				System.out.println("ari");
			}
		} else {
			System.out.println("ariaa");
		}
		return false;
	}

	// werayによる変更
	// public static List<Base_Enemy> getListRoomInEnemy() {
	// List<Base_Enemy> list = new ArrayList<Base_Enemy>();
	// MapList.addRoomInEnemy(Player.me.getMassPoint().x,
	// Player.me.getMassPoint().y);
	//
	// list = MapList.getListRoomInEnemy();
	// MapList.check_mass_clear();
	// MapList.getListRoomInEnemy().clear();
	// return list;
	// }

	public static boolean isEmptyBackPoint(Base_Creature c) {
		Point p = c.getMassPoint();
		DIRECTION d = c.getDirection();
		return isEmptyPoint(new Point(p.x - d.X, p.y - d.Y));
	}

	public static boolean isEmptyPoint(Point p) {
		if (MassCreater.getMass(p).ROOM || MassCreater.getMass(p).ROAD) {
			if (MapList.getCreature(p) == null) {
				return true;
			}
		}
		return false;
	}

	public static boolean isRootWalkableCheck(Point p, DIRECTION dir) {
		if (!MassCreater.getMass(p.x + dir.X, p.y + dir.Y).WALKABLE) {
			return false;
		}
		if (dir.NUM % 2 == 0) {
			return true;
		}
		for (DIRECTION d : dir.getNeiboringDirections3()) {
			if (!MassCreater.getMass(p.x + d.X, p.y + d.Y).WALKABLE) {
				return false;
			}
		}
		return true;
	}

	public static boolean isRootWalkCheck(Point p, DIRECTION dir) {
		if (!MassCreater.getMass(p.x + dir.X, p.y + dir.Y).WALKABLE) {
			return false;
		}
		if (dir.NUM % 2 == 0) {
			return MapList.getCreature(p.x + dir.X, p.y + dir.Y) == null;
		}
		for (DIRECTION d : dir.getNeiboringDirections3()) {
			if (!MassCreater.getMass(p.x + d.X, p.y + d.Y).WALKABLE) {
				return false;
			}
		}
		return true;
	}

	public static boolean MapMaxPoint(Point p) {
		if (p.x == MassCreater.WIDTH - 1 || p.x == MassCreater.Y_MIN + 1
				|| p.y == MassCreater.HEIGHT - 1
				|| p.y == MassCreater.Y_MIN + 1) {
			return true;
		}
		return false;
	}

	private static boolean moveCheckPoint(Point p, DIRECTION dir) {
		if (dir.NUM % 2 != 0) {
			if (!moveToWalkableCheckPoint(p, dir)) {
				return false;
			}
		} else {
			if (!MassCreater.getMass(new Point(p.x + dir.X, p.y + dir.Y)).WALKABLE) {
				return false;
			}
		}
		p = new Point(p.x + dir.X, p.y + dir.Y);
		if (MapList.getCreature(p) != null) {
			return true;
		}
		return false;
	}

	private static boolean moveToWalkableCheckPoint(Point p, DIRECTION d) {
		List<DIRECTION> list = DIRECTION.neiberDirection(d);
		if (!MassCreater.getMass(new Point(p.x + list.get(0).X, p.y
				+ list.get(0).Y)).WALKABLE) {
			return false;
		}
		Point first = p;
		Point second = p;
		first = new Point(first.x + list.get(1).X, first.y + list.get(1).Y);
		second = new Point(second.x + list.get(2).X, second.y + list.get(2).Y);
		if (MassCreater.getMass(first).WALKABLE
				&& MassCreater.getMass(second).WALKABLE) {
			return true;
		}
		return false;
	}

	private static void road(Object obj, String str, int damage) {
		Point p;
		p = Player.me.getMassPoint();
		for (Base_Creature em : MapList.getListAroundEnemy(p)) {
			damage(obj, str, (Base_Enemy) em, damage);
		}
	}

	private static void roadToPoint(Point p, Object obj, String str, int damage) {
		for (Base_Creature em : MapList.getListAroundEnemy(p)) {
			damage(obj, str, (Base_Enemy) em, damage);
		}
	}

	private static void room(Object obj, String str, int damage) {
		for (Base_Enemy em : roomInEnemy()) {
			damage(obj, str, em, damage);
		}
	}

	private static List<Base_Artifact> roomInArtifact() {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Base_Artifact em : MapList.getListArtifact()) {
			if (MassCreater.isPointInTheSameRoomInEntrance(
					Player.me.getMassPoint(), em.getMassPoint())) {
				list.add(em);
			}
		}
		return list;
	}

	/**
	 * 20130102最新
	 * 
	 * @param p
	 * @param c
	 * @param time
	 */
	public static void roomInChengeConditionAtPoint(Point p, CONDITION c,
			int time) {
		if (!MassCreater.getMass(p).ROOM) {
			for (Base_Creature _c : getListAroundInCreature(p)) {
				_c.setCondition(c, time);
			}
			return;
		}
		for (Base_Creature bs : MapList.getListCreature()) {
			if (MassCreater
					.isPointInTheSameRoomInEntrance(p, bs.getMassPoint())) {
				bs.setCondition(c, time);
			}
		}
	}

	public static void roomInCreatureChengeCondition(Base_Creature bs,
			CONDITION c, int time) {
		if (MassCreater.getMass(Player.me.getMassPoint()).ROOM) {
			MapList.addRoomInCreature(bs.getMassPoint().x, bs.getMassPoint().y);
			if (MapList.getListRoomInCreature().isEmpty()) {
				roomInCreatureChengeCondition2(true);
				return;
			} else {
				for (Base_Creature creature : MapList.getListRoomInCreature()) {
					CONDITION con = conditionSelect(c);
					creature.setCondition(conditionSelect(con), 0);
				}
				roomInCreatureChengeCondition2(true);
			}
		} else if (MassCreater.getMass(bs.getMassPoint()).ROAD) {
			MapList.addAroundCreature(bs.getMassPoint());
			if (MapList.getListAroundCreature().isEmpty()) {
				roomInCreatureChengeCondition2(false);
				return;
			}
			for (Base_Creature creature : MapList.getListAroundCreature()) {
				CONDITION con = conditionSelect(c);
				creature.setCondition(conditionSelect(con), 0);
			}
			roomInCreatureChengeCondition2(false);
		}
	}

	private static void roomInCreatureChengeCondition2(boolean room) {
		if (room) {
			MapList.getListRoomInCreature().clear();
			MapList.check_mass_clear();
		} else {
			MapList.getListAroundCreature().clear();
		}
	}

	private static List<Base_Enemy> roomInEnemy() {
		ArrayList<Base_Enemy> list = new ArrayList<Base_Enemy>();
		for (Base_Enemy em : MapList.getListEnemy()) {
			if (em instanceof Base_NPC) {
				continue;
			}
			if (MassCreater.isPointInTheSameRoomInEntrance(
					Player.me.getMassPoint(), em.getMassPoint())) {
				list.add(em);
			}
		}
		return list;
	}

	private static List<Base_Enemy> roomInEnemyToPoint(Point p) {
		ArrayList<Base_Enemy> list = new ArrayList<Base_Enemy>();
		for (Base_Enemy em : MapList.getListEnemy()) {
			if (em instanceof Base_NPC) {
				continue;
			}
			if (MassCreater
					.isPointInTheSameRoomInEntrance(p, em.getMassPoint())) {
				list.add(em);
			}
		}
		return list;
	}

	/**
	 * @param c
	 *            ランダムコンディションならばnull
	 * @param time
	 */
	public static void roomOrRoadInEnemyChengeCondition(Point p, CONDITION c,
			int time) {
		if (MassCreater.getMass(p).ROOM) {
			for (Base_Enemy em : MapList.getListEnemy()) {
				if (MassCreater.isPointInTheSameRoomInEntrance(p,
						em.getMassPoint())) {
					em.setCondition(conditionSelect(c), time);
				}
			}
		} else if (MassCreater.getMass(p).ROAD) {
			for (Base_Creature em : MapList.getListAroundCreature(p)) {
				em.setCondition(conditionSelect(c), time);
			}
		} else {
			System.out.println("checks-else");
		}
	}

	/**
	 * 部屋内の敵にダメージを与える 通路時、隣接の敵にダメージを与える
	 */
	public static void roomOrRoadInEnemyDamade(Object obj, String str,
			int damage) {
		if (MassCreater.getMass(Player.me.getMassPoint()).ROOM) {
			room(obj, str, damage);
		} else {
			road(obj, str, damage);
		}
	}

	/**
	 * 部屋内の敵にダメージを与える 通路時、隣接の敵にダメージを与える
	 */
	public static void roomOrRoadInEnemyDamadeToPoint(Point p, Object obj,
			String str, int damage) {
		if (MassCreater.getMass(p).ROOM) {
			roomToPoint(p, obj, str, damage);
		} else {
			roadToPoint(p, obj, str, damage);
		}
	}

	private static void roomToPoint(Point p, Object obj, String str, int damage) {
		for (Base_Enemy em : roomInEnemyToPoint(p)) {
			damage(obj, str, em, damage);
		}
	}

	public static void 吹き飛ばし(final Object obj, final String str,
			final Base_Creature target, final DIRECTION d, final int range,
			final int damage) {
		吹き飛ばし(obj, str, target, d, range, damage, null);
	}

	/**
	 * 吹き飛ばした後の処理。ぶつかってダメージが必要ならばTaskに入れる必要有り
	 * 
	 * @param obj
	 * @param str
	 * @param target
	 * @param d
	 * @param range
	 * @param damage
	 * @param task
	 */
	public static void 吹き飛ばし(final Object obj, final String str,
			final Base_Creature target, final DIRECTION d, final int range,
			final int damage, final Task task) {

		if (target instanceof Player) {
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
					ENCHANT_SIMBOL.イカリ)) {
				Damage.damage(obj, null, "吹き飛ばされて倒れた", isCreatureOrNull(obj),
						target, damage);
				return;
			}
		}
		Point p = target.getMassPoint().getLocation();
		Base_Creature c = null;
		int i = 0;
		while (true) {
			i++;
			if (!isRootWalkCheck(p, d) && !target.isAbleToIgnoreWalls()) {
				c = MapList.getCreature(p.x + d.X, p.y + d.Y);
				break;
			}
			p.x += d.X;
			p.y += d.Y;
			c = MapList.getCreature(p);
			if (c != null) {
				p.x -= d.X;
				p.y -= d.Y;
				break;
			}
			if (i != 0 && i >= range) {
				break;
			}
		}
		target.startDamaging();
		if (c != null) {
			c.startDamaging();
		}
		final Base_Creature final_C = c;
		target.setMassPoint_WalkLike(p, 3, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				if (damage != 0) {
					if (final_C != null) {
						Damage.damage(obj, null, str, isCreatureOrNull(obj),
								final_C, damage);
						Damage.damage(obj, null, str, isCreatureOrNull(obj),
								target, damage);
					} else {
						Damage.damage(obj, null, str, isCreatureOrNull(obj),
								target, damage);
					}
				}
				if (task != null)
					task.work();
			}
		});
	}

	public static void 転ばし(final Object obj, final String str,
			final Base_Creature target, final DIRECTION d, final int range,
			final int damage) {
		転ばし(obj, str, target, d, range, damage, 2);
	}

	public static void 転ばし(final Object obj, final String str,
			final Base_Creature target, final DIRECTION d, final int range,
			final int damage, final int max) {
		if (target instanceof Player) {
			if (Belongings.isSearchItem("転ばぬ先の杖")) {
				target.startDamaging();
				return;
			}
			if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.イカリ)) {
				target.startDamaging();
				Damage.damage(obj, null, str, isCreatureOrNull(obj), target,
						damage);
				return;
			}
		}
		Point p = target.getMassPoint().getLocation();
		// target.direction = d;
		Base_Creature c = null;
		int i = 0;
		while (true) {
			i++;
			if (!isRootWalkCheck(p, d) && !target.isAbleToIgnoreWalls()) {
				c = MapList.getCreature(p.x + d.X, p.y + d.Y);
				break;
			}
			p.x += d.X;
			p.y += d.Y;
			c = MapList.getCreature(p);
			if (c != null) {
				p.x -= d.X;
				p.y -= d.Y;
				break;
			}
			if (i != 0 && i >= range) {
				break;
			}
		}
		target.startDamaging();
		if (c != null) {
			c.startDamaging();
		}
		final Base_Creature final_C = c;
		target.setMassPoint_WalkLike(p, 3, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				if (final_C != null) {
					Damage.damage(obj, null, str, isCreatureOrNull(obj),
							final_C, damage);
					Damage.damage(obj, null, str, isCreatureOrNull(obj),
							target, damage);
				} else {
					Damage.damage(obj, null, str, isCreatureOrNull(obj),
							target, damage);
				}
				Tumble.tumble(target, d, 0, max);
			}
		});
	}

	// static
	private MapInSelect() {
	}

}
