package dangeon.model.map;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import main.res.BGM;
import main.res.SE;
import main.util.DIRECTION;
import main.util.FrameShaker;
import dangeon.controller.TurnSystemController;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Base_Device;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.food.Food;
import dangeon.model.object.artifact.item.food.奇跡のおにぎり;
import dangeon.model.object.artifact.item.food.腐ったおにぎり;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.ゆっくり霊夢;
import dangeon.model.object.creature.enemy.わかさぎ姫;
import dangeon.model.object.creature.enemy.物部布都;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.util.ObjectPoint;
import dangeon.util.R;
import dangeon.view.anime.Base_Anime;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;
import dangeon.view.detail.View_Sider;
import dangeon.view.util.WithinOutofScreen;

/**
 * マップ内にあるオブジェクト管理クラス
 * 
 * @author k
 * 
 */
public class MapList implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static List<Base_Enemy> list_room_in_enemy = new ArrayList<Base_Enemy>();
	private static List<Base_Creature> list_room_in_creature = new ArrayList<Base_Creature>();
	private static List<Base_Creature> list_around_creature = new ArrayList<Base_Creature>();
	private static List<Base_Enemy> list_enemy = new ArrayList<Base_Enemy>();
	private static List<Base_Artifact> list_artifact = new ArrayList<Base_Artifact>();
	public static MapList ME = new MapList();

	private static final int MAX_MASS = 50;

	private static final int ENEMY_MAX = 30;

	private static boolean check_room[][] = new boolean[MAX_MASS][MAX_MASS];

	public static Point stairs_point;

	private static boolean flag_sheef_start = false;

	private static boolean flag_holy_in = false, flag_shop_in = false;
	private static ArrayList<Mass> huto_waters;
	private static boolean next_floor_asc = true;
	private static ArrayList<Mass> leef_task = new ArrayList<Mass>();

	public static void addAroundCreature(Point p) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0)
					continue;
				if (MapList.getCreature(p.x + i, p.y + j) != null) {
					list_around_creature.add(MapList.getCreature(p.x + i, p.y
							+ j));
				}
			}
		}
	}

	public synchronized static void addArtifact(Base_Artifact af) {
		if (af == null) {
			return;
		} else if (list_artifact.contains(af)) {
			// マミゾウのせいでコメントアウト
			// Show.showErrorMessageDialog("既に登録されてある" + af +
			// "がマップに登録されようとしました。");
		} else {
			if (af instanceof Base_Trap) {
				if (getArtifact(af.getMassPoint()) != null)
					return;
			}
			Mass mass = MassCreater.getMass(af.getMassPoint());
			mass.setUnWarning();
			if (BonusConductor.守矢神() && af instanceof Food
					&& !(af instanceof 奇跡のおにぎり)) {
				Base_Item a = new 奇跡のおにぎり((Food) af);
				list_artifact.add(a);
				return;
			} else if (mass.WATER) {
				if (!af.waterAction())
					return;
			}
			list_artifact.add(af);
		}
	}

	/**
	 * 
	 * @param em
	 * @return !isEnemyMax() && water...
	 */
	public synchronized static boolean addEnemy(Base_Enemy em) {
		if (isEnemyMax()) {
			return false;
		}
		if (MassCreater.getMass(em.getMassPoint()).WATER && !em.isWatering())
			return false;
		em.setSize();
		list_enemy.add(em);
		return true;
	}

	public static void addRoomInCreature(int x, int y) {
		Mass[][] mass = MassCreater.getMass();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0)
					continue;
				if (check_room[x + i][y + j])
					continue;
				if (mass[x + i][y + j].ROAD || !mass[x + i][y + j].WALKABLE) {
					check_room[x + i][y + j] = true;
					continue;
				} else if (MapList.getCreature(x + i, y + j) != null) {
					check_room[x + i][y + j] = true;
					list_room_in_creature
							.add(MapList.getCreature(x + i, y + j));
					continue;
				} else if (mass[x + i][y + j].ROOM) {
					check_room[x + i][y + j] = true;
					addRoomInCreature(x + i, y + j);
				}
			}
		}
	}

	/**
	 * この座標の部屋のエネミーを登録する
	 * 
	 * @param x
	 * @param y
	 */
	public static void addRoomInEnemy(int x, int y) {
		// Mass[][] mass = MassCreater.getMass();
		// for (int i = -1; i <= 1; i++) {
		// for (int j = -1; j <= 1; j++) {
		// if (i == 0 && j == 0)
		// continue;
		// if (check_room[x + i][y + j])
		// continue;
		// if (mass[x + i][y + j].ROAD || !mass[x + i][y + j].WALKABLE) {
		// check_room[x + i][y + j] = true;
		// continue;
		// } else if (MapList.getEnemy(x + i, y + j) != null) {
		// check_room[x + i][y + j] = true;
		// list_room_in_enemy.add(MapList.getEnemy(x + i, y + j));
		// continue;
		// } else if (mass[x + i][y + j].ROOM) {
		// check_room[x + i][y + j] = true;
		// addRoomInEnemy(x + i, y + j);
		// }
		// }
		// }
	}

	public static void ascFloor() {
		ME.floor++;
	}

	public static void awaken() {
		for (Base_Enemy em : getListEnemy()) {
			CONDITION.conditionRecovery(em, CONDITION.仮眠);
			if (!EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.忍)) {
				CONDITION.conditionRecovery(em, CONDITION.特殊仮眠);
				CONDITION.conditionRecovery(em, CONDITION.安心);
			}
			for (CONDITION con : em.getConditionRemoveTask()) {
				em.getConditionList().remove(con);
			}
			em.getConditionRemoveTask().clear();
		}
	}

	public static void check_mass_clear() {
		for (int i = 0; i < MAX_MASS; i++) {
			for (int j = 0; j < MAX_MASS; j++) {
				check_room[i][j] = false;
			}
		}
	}

	/**
	 * 
	 * @param p
	 * @return MonsterHouse => true
	 */
	public static boolean checkSpecialRoom(Point p) {
		// if (Player.me.isJumping()) {
		// return false;
		// }
		Mass m = MassCreater.getMass(p);
		if (flag_shop_in && Player.me.shop != null) {
			if (!m.isShop()) {
				flag_shop_in = false;
				Player.me.shop.getTenshu().goOutMessage();
			} else {
				Player.me.shop.preGoOut();
			}
		}
		if (flag_holy_in && Player.me.saisen != null && !m.isHoly()) {
			Player.me.saisen.goOut();
			flag_holy_in = false;
		}
		Room r = MassCreater.getIP().getRoom(p);
		int lv = isHutoChan(r);
		if (huto_waters != null && lv == -1)
			hutoOut();
		if (r == null) {
			return false;
		}
		if (r.flag_monster_house) {
			r.flag_monster_house = false;
			performMonsterHosue();
			return true;
		}
		if (!flag_shop_in) {
			if (m.isShop()) {
				flag_shop_in = true;
				if (Player.me.shop != null) {
					Player.me.shop.getTenshu().shopInMessage();
				}
			}
		}
		if (!flag_holy_in) {
			if (m.isHoly()) {
				flag_holy_in = true;
				if (Player.me.saisen != null) {
					Message.set("御殿に足を踏み入れた");
				}
			}
		}
		if (huto_waters == null && lv != -1)
			hutoIN(r, lv);
		MapList.checkWater();
		return false;
	}

	public static void checkWater() {
		List<Base_Creature> list = getListCreature();
		list.add(Player.me);
		for (Iterator<Mass> iterator = leef_task.iterator(); iterator.hasNext();) {
			Mass _M = iterator.next();
			if (!MapList.isCreature(_M.getPoint())) {
				_M.setWaterLeefOn(true);
				iterator.remove();
			}
		}
		for (Base_Creature c : list) {
			Mass m = MassCreater.getMass(c.getMassPoint());
			if (m.WATER_LEEF && !leef_task.contains(m)) {
				leef_task.add(m);
				if (m.FLOWER && c instanceof Player) {
					c.heal(null, c.getMAX_HP());
				}
			}
			if (!c.isAnimating()) {
				if (m.WATER)
					c.waterAction(m);
				else if (c instanceof わかさぎ姫) {
					SE.ATTACK_WATER.play();
					Message.set(c.getColoredName(), "は地上では活動できない！");
					c.chengeHP_NoEffect(-999);
				}
			}

		}
	}

	public static void clear() {
		flag_shop_in = false;
		list_enemy.clear();
		list_artifact.clear();
	}

	public static void desFloor() {
		ME.floor--;
	}

	public static void gatherCheckedItems() {
		ArrayList<Base_Item> list = new ArrayList<Base_Item>();
		for (Base_Artifact a : list_artifact) {
			if (a instanceof Base_Item && ((Base_Item) a).isPickCheked())
				list.add((Base_Item) a);
		}
		int count = 0;
		boolean flag_max = false;
		for (Base_Item a : list) {
			if (!ItemFall.isAbleToFall_AroundPlayer()) {
				flag_max = true;
				break;
			}
			count++;
			ItemFall.setItemFallPoint_NoMessage(Player.me.getMassPoint()
					.getLocation(), a);
		}
		if (count > 0) {
			SE.WARP_INSTANT.play();
			Message.set("アイテムを集めた");
			TurnSystemController.callMeToStartEnemyTurn();
			if (flag_max) {
				View_Sider.setInformation("！対象が多すぎて集めきれていない！");
			}
		} else {
			Message.set("床がいっぱいでアイテムを集められなかった");
		}

	}

	public static ArrayList<Base_Enemy> getAnyEnemyInScreen() {
		ArrayList<Base_Enemy> list = new ArrayList<Base_Enemy>();
		for (Base_Enemy e : list_enemy) {
			Point p = ObjectPoint.getScreenPointRelPlayer(e);
			int x = p.x;
			int y = p.y;
			if (!WithinOutofScreen.isOutside(x, y, e.getImage().getWidth(null),
					e.getImage().getHeight(null))) {
				list.add(e);
			}
		}
		return list;
	}

	public synchronized static Base_Artifact getArtiface(int index) {
		return list_artifact.get(index);
	}

	public synchronized static Base_Artifact getArtiface(int x, int y) {
		for (Base_Artifact a : list_artifact) {
			if (a.getMassPoint().x == x && a.getMassPoint().y == y) {
				return a;
			}
		}
		return null;
	}

	public static Base_Artifact getArtiface_exceptME(Point p,
			Base_Artifact artifact) {
		for (Base_Artifact a : list_artifact)
			if (a.getMassPoint().x == p.x && a.getMassPoint().y == p.y) {
				if (a != artifact) {
					return a;
				}
			}
		return null;
	}

	public static Base_Artifact getArtiface_exceptME_Trap(Point p,
			Base_Artifact artifact) {
		for (Base_Artifact a : list_artifact)
			if (a.getMassPoint().x == p.x && a.getMassPoint().y == p.y) {
				if (a != artifact && !(a instanceof Base_Trap)) {
					return a;
				}
			}
		return null;
	}

	/**
	 * 
	 * @param p
	 * @return 座標pの位置にあるartifactを返す
	 */
	public static Base_Artifact getArtifact(Point p) {
		for (Base_Artifact a : list_artifact)
			if (a.getMassPoint().x == p.x && a.getMassPoint().y == p.y)
				return a;
		return null;
	}

	public static Base_Creature getCreature(int x, int y) {
		return getCreature(new Point(x, y));
	}

	/**
	 * @param p
	 * @return 主人公かNPCかエネミーかnull
	 */
	public static Base_Creature getCreature(Point p) {
		if (Player.me.getMassPoint().x == p.x
				&& Player.me.getMassPoint().y == p.y) {
			return Player.me;
		}
		return getEnemy(p);
		// for (Base_NPC npc : list_npc)
		// if (npc.getMassPoint().x == p.x && npc.getMassPoint().y == p.y)
		// return npc;
	}

	public synchronized static Base_Enemy getEnemy(int index) {
		return list_enemy.get(index);
	}

	public synchronized static Base_Enemy getEnemy(int x, int y) {
		for (Base_Enemy e : list_enemy) {
			if (e.getMassPoint().x == x && e.getMassPoint().y == y) {
				return e;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param p
	 * @return 座標pの位置にあるenemyを返す
	 */
	public synchronized static Base_Enemy getEnemy(Point p) {
		for (Base_Enemy e : list_enemy)
			if (e.getMassPoint().x == p.x && e.getMassPoint().y == p.y)
				return e;
		return null;
	}

	public static boolean getFlagSheef() {
		return flag_sheef_start;
	}

	public static int getFloor() {
		return ME.floor;
	}

	/**
	 * プレイヤーの目の前のエネミーを返す
	 */
	public static Base_Enemy getFrontEnemy() {
		Point p = Player.me.getMassPoint();
		switch (Player.me.getDirection()) {
		case UP:
			if (MapList.getEnemy(p.x, p.y - 1) != null)
				return MapList.getEnemy(p.x, p.y - 1);
			else
				break;
		case DOWN:
			if (MapList.getEnemy(p.x, p.y + 1) != null)
				return MapList.getEnemy(p.x, p.y + 1);
			else
				break;
		case LEFT:
			if (MapList.getEnemy(p.x - 1, p.y) != null)
				return MapList.getEnemy(p.x - 1, p.y);
			else
				break;
		case RIGHT:
			if (MapList.getEnemy(p.x + 1, p.y) != null)
				return MapList.getEnemy(p.x + 1, p.y);
			else
				break;
		case UP_LEFT:
			if (MapList.getEnemy(p.x - 1, p.y - 1) != null)
				return MapList.getEnemy(p.x - 1, p.y - 1);
			else
				break;
		case UP_RIGHT:
			if (MapList.getEnemy(p.x + 1, p.y - 1) != null)
				return MapList.getEnemy(p.x + 1, p.y - 1);
			else
				break;
		case DOWN_LEFT:
			if (MapList.getEnemy(p.x - 1, p.y + 1) != null)
				return MapList.getEnemy(p.x - 1, p.y + 1);
			else
				break;
		case DOWN_RIGHT:
			if (MapList.getEnemy(p.x + 1, p.y + 1) != null)
				return MapList.getEnemy(p.x + 1, p.y + 1);
			else
				break;
		}
		return null;
	}

	public static List<Base_Artifact> getListAroundArtifact(Point p) {
		List<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				Base_Artifact c = MapList.getArtiface(p.x + i, p.y + j);
				if (c != null) {
					list.add(c);
				}
			}
		}
		return list;
	}

	public static List<Base_Creature> getListAroundCreature() {
		return list_around_creature;
	}

	public static ArrayList<Base_Creature> getListAroundCreature(Point p) {
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			Point _P = p.getLocation();
			_P.translate(d.X, d.Y);
			if (MapList.getCreature(_P) != null) {
				list.add(MapList.getCreature(_P));
			}
		}
		return list;
	}

	public static List<Base_Enemy> getListAroundEnemy(Point p) {
		List<Base_Enemy> list = new ArrayList<Base_Enemy>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				Base_Creature c = MapList.getCreature(p.x + i, p.y + j);
				if (c != null) {
					if (c instanceof Base_Enemy && !(c instanceof Base_NPC)) {
						list.add((Base_Enemy) c);
					}
				}
			}
		}
		return list;
	}

	public synchronized static List<Base_Artifact> getListArtifact() {
		return list_artifact;
	}

	/**
	 * 
	 * @return Playerは除く
	 */
	public static List<Base_Creature> getListCreature() {
		int sum = list_enemy.size();
		List<Base_Creature> l = new ArrayList<Base_Creature>(sum);
		for (Base_Creature c : list_enemy) {
			l.add(c);
		}
		// for (Base_Creature c : list_npc) {
		// l.add(c);
		// }
		return l;
	}

	public synchronized static List<Base_Enemy> getListEnemy() {
		return list_enemy;
	}

	public synchronized static List<Base_Artifact> getListItem() {
		List<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : list_artifact) {
			if (a instanceof Base_Item) {
				list.add(a);
			}
		}
		return list;
	}

	public synchronized static List<Base_Enemy> getListNpc() {
		List<Base_Enemy> list = new ArrayList<Base_Enemy>();
		for (Base_Enemy em : getListEnemy()) {
			if (em instanceof Base_NPC) {
				list.add(em);
			}
		}
		return list;
	}

	public static List<Base_Creature> getListRoomInCreature() {
		return list_room_in_creature;
	}

	public static List<Base_Enemy> getListRoomInEnemy() {
		return list_room_in_enemy;
	}

	public static Base_Enemy getNonActionedEnemu() {
		for (Base_Enemy enemy : MapList.getListEnemy()) {
			if (!enemy.enemy_actioned) {
				return enemy;
			}
		}
		return null;
	}

	public synchronized static Base_NPC getNpc(Point p) {
		for (Base_Enemy npc : getListEnemy()) {
			if (npc instanceof Base_NPC) {
				if (npc.getMassPoint().x == p.x && npc.getMassPoint().y == p.y) {
					return (Base_NPC) npc;
				}
			} else {
				continue;
			}
		}
		return null;
		// for (Base_NPC npc : getListNpc()) {
		// if (npc.getMassPoint().x == p.x && npc.getMassPoint().y == p.y) {
		// return npc;
		// } else
		// continue;
		// }
		// return null;
	}

	private static int getPermittedValue(Mass m) {
		if (m.WATER || m.WATER_FROZEN)
			return 1;
		else if (!m.WALKABLE)
			return 2;
		else if (m.ROAD
				|| getArtifact(new Point(m.X, m.Y)) instanceof Base_Trap)
			return 1;
		else if ((MassCreater.getRoom(m.X, m.Y) == null) != m.ROAD) {
			System.out.println("NOT_ROAD_MISMATCH");
		}
		return 0;
	}

	private static int getPermittedValue(Mass m, DIRECTION d) {
		return getPermittedValue(MassCreater.getMass(m.getPoint(), d));
	}

	public static Stairs getStairs() {
		for (Base_Artifact a : list_artifact) {
			if (a instanceof Stairs) {
				return (Stairs) a;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param p
	 * @return ８箇所をチェック
	 */
	public static ArrayList<Base_Creature> getSurroundedCreature(Point p) {
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			Base_Creature c = getCreature(p.x + d.X, p.y + d.Y);
			if (c != null) {
				list.add(c);
			}
		}
		return list;
	}

	/**
	 * 
	 * @param p
	 * @return 9箇所をチェック
	 */
	public static ArrayList<Base_Creature> getSurroundedCreature_IncludingMyself(
			Point p) {
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		for (DIRECTION d : DIRECTION.values()) {
			Base_Creature c = getCreature(p.x + d.X, p.y + d.Y);
			if (c != null) {
				list.add(c);
			}
		}
		return list;
	}

	public static Base_Trap getTrap(Point p) {
		if (getArtifact(p) != null) {
			if (getArtifact(p) instanceof Base_Trap) {
				return (Base_Trap) getArtifact(p);
			}
		}
		return null;
	}

	private static void hutoIN(Room r, int lv) {
		huto_waters = r.setHuto(lv);
	}

	private static void hutoOut() {
		for (Iterator<Mass> iterator = huto_waters.iterator(); iterator
				.hasNext();) {
			iterator.next().setWaterWithAnimation(false, !iterator.hasNext());
		}
		Message.set("秘術から解放された");
		huto_waters = null;
	}

	public static boolean isAnyEnemyInScreen() {
		for (Base_Enemy e : list_enemy) {
			if (isAnyEnemyInScreen_Check(e)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isAnyEnemyInScreen_Check(Base_Enemy e) {
		if (e == null)
			return false;
		if (e instanceof Base_NPC)
			return false;
		if (e.isPlayerSide())
			return false;
		CONDITION[] cs = { CONDITION.睡眠, CONDITION.暗闇, CONDITION.安心,
				CONDITION.仮眠, CONDITION.特殊仮眠, CONDITION.麻痺, CONDITION.影縫い };
		for (CONDITION condition : cs) {
			if (e.conditionCheck(condition))
				return false;
		}
		Point p = ObjectPoint.getScreenPointRelPlayer(e);
		int x = p.x;
		int y = p.y;
		if (!WithinOutofScreen.isOutside(x, y, e.getImage().getWidth(null), e
				.getImage().getHeight(null))) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return チルノの視界内に倍速エネミーがいたらtrue
	 */
	public static boolean isAnyEnemyStatusQuickInScreen() {
		for (Base_Enemy e : getAnyEnemyInScreen()) {
			if (e.conditionCheck(CONDITION.倍速)
					|| e.conditionCheck(CONDITION.三倍速)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return true if any object is at the point
	 */
	public static boolean isAnything(int x, int y) {
		return isArtifact(x, y) || isCreature(x, y);
	}

	public static boolean isArtifact(int x, int y) {
		return getArtifact(new Point(x, y)) != null;
	}

	public static boolean isCheckedItem() {
		for (Base_Artifact a : list_artifact) {
			if (a instanceof Base_Item)
				if (((Base_Item) a).isPickCheked())
					return true;
		}
		return false;
	}

	public static boolean isCreature(int x, int y) {
		return getCreature(x, y) != null;
	}

	public static boolean isCreature(Point point) {
		return isCreature(point.x, point.y);
	}

	public static boolean isCreatureAnimating() {
		if (Player.me.isAnimating())
			return true;
		// for (Base_Enemy enemy : MapList.getListEnemy()) {
		// if (enemy.isMoving()) {
		// return false;
		// }
		// }
		return !isEnemyAnimatigEnded();
	}

	/**
	 * 
	 * @return enemyやアイテムがアニメイティングならfalse
	 */
	public static boolean isEnemyAnimatigEnded() {
		for (Base_Enemy enemy : MapList.getListEnemy()) {
			if (enemy.isAnimating()) {
				enemy.isAnimating();
				return false;
			}
		}
		for (Base_Artifact a : MapList.getListArtifact()) {
			if (a.isAnimating()) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEnemyMax() {
		return list_enemy.size() >= ENEMY_MAX;
	}

	// public static List<Base_Enemy> getListAroundEnemy() {
	// return list_around_enemy;
	// }

	private static int isHutoChan(Room r) {
		if (r == null)
			return -1;
		for (Base_Enemy em : MapList.getListEnemy()) {
			if (em instanceof 物部布都 && em.isSkillActive()) {
				em.whereIsPlayer();
				if (em.player_is_in_sight)
					return em.getLV();
			}
		}
		// for (Iterator<Base_Enemy> iterator = MapInSelect
		// .getRoomOrRoadInEnemyList(); iterator.hasNext();) {
		// Base_Creature c = iterator.next();
		// if (c instanceof 物部布都 && c.isSkillActive()) {
		//
		// return c.getLV();
		// }
		// }
		return -1;
	}

	public static boolean isNeiboringToRoad(int x, int y) {
		for (DIRECTION d : DIRECTION.values_onlyBasic4()) {
			if (MassCreater.getMass(x + d.X, y + d.Y).ROAD) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNextFloorUpOrDown() {
		return next_floor_asc;
	}

	public static Base_Enemy isSearchEnemyInClass(String str) {
		for (Base_Enemy em : list_enemy) {
			if (em.getClass().getSimpleName().matches(str)) {
				return em;
			}
		}
		return null;
	}

	private static boolean isTrapOrWaterPermitted(ArrayList<Mass> checked,
			ArrayList<Mass> around, final Mass me) {
		if (around.isEmpty())
			return true;
		if (isTrapOrWaterPermitted(around.get(0), around.get(0), checked,
				around, me)) {
			around.remove(0);
			return isTrapOrWaterPermitted(checked, around, me);
		} else {
			return false;
		}
	}

	public static boolean isTrapOrWaterPermitted(int x, int y) {
		// if (getPermittedValue(MassCreater.getMass(x, y)) != 0)
		// return false;
		if (!MassCreater.getMass(x, y).WALKABLE)
			return true;
		if (isNeiboringToRoad(x, y))
			return false;
		Base_Artifact a = MapList.getArtiface(x, y);
		if (a != null && a instanceof Base_Device)
			return false;
		ArrayList<Mass> checked = new ArrayList<Mass>();
		ArrayList<Mass> around = new ArrayList<Mass>(4);
		Mass me = MassCreater.getMass(x, y);
		checked.add(Mass.nullpo);
		checked.add(me);
		for (DIRECTION d : DIRECTION.values_onlyBasic4()) {
			Mass m = MassCreater.getMass(x + d.X, y + d.Y);
			// checked.add(m);
			if (getPermittedValue(m) != 0)
				around.add(m);
		}
		return isTrapOrWaterPermitted(checked, around, me);
	}

	private static boolean isTrapOrWaterPermitted(Mass now, final Mass START,
			ArrayList<Mass> checked, ArrayList<Mass> around, final Mass CHECK) {
		checked.add(now);
		int v = getPermittedValue(now);
		DIRECTION[] ds;
		if (v == 1) {
			ds = DIRECTION.values_onlyBasic4();
		} else if (v == 2) {
			boolean wall_in_wall = true;
			for (DIRECTION d : DIRECTION.values_onlyBasic4()) {
				if (getPermittedValue(MassCreater.getMass(now.X + d.X, now.Y
						+ d.Y)) != 2) {
					wall_in_wall = false;
					break;
				}
			}
			if (wall_in_wall)
				return true;
			ds = DIRECTION.values_exceptNeatral();
			// ds = DIRECTION.values_onlyBasic4();
		} else {
			return true;
		}
		for (DIRECTION d : ds) {
			Mass mass = MassCreater.getMass(now.X + d.X, now.Y + d.Y);
			// if (around.contains(mass)) {
			// return false;
			// } else
			if (mass == CHECK) {
				if (!isTrapOrWaterPermitted(now, START, CHECK))
					return false;
				else
					continue;
			}
			if (!checked.contains(mass)) {
				if (!isTrapOrWaterPermitted(mass, START, checked, around, CHECK)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isTrapOrWaterPermitted(Mass end, Mass start,
			Mass check) {
		DIRECTION d_s = DIRECTION.getDirection(check.getPoint(),
				start.getPoint());
		DIRECTION d_e = DIRECTION
				.getDirection(check.getPoint(), end.getPoint());
		int dx = Math.abs(end.X - start.X);
		int dy = Math.abs(end.Y - start.Y);
		if (end == start)
			return true;
		else if (dx == 1 || dy == 1) {
			if (dx == 0 || dy == 0)
				return true;
			else {
				if (getPermittedValue(end) == 2
						|| getPermittedValue(start) == 2)
					return true;
				if (getPermittedValue(check, DIRECTION.getSum(d_s, d_e)) != 0)
					return true;
				else {
					for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
						if (d == d_s || d == d_e
								|| d == DIRECTION.getSum(d_s, d_e))
							continue;
						if (isTrapOrWaterPermitted_isWakable(d, check))
							return false;
					}
					return true;
				}
			}
		} else {
			// d_s = d_s.getNeiboringDirection(2);
			// d_e = d_e.getNeiboringDirection(2);
			// boolean left = getPermittedValue(check, d_s) != 0;
			// boolean right = getPermittedValue(check, d_e) != 0;
			// boolean[] f = new boolean[] { left, right };
			int c = 0;
			for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
				if (isTrapOrWaterPermitted_isWakable(d, check))
					c++;
			}
			return c < 2;
			// if (getPermittedValue(end) == 1 && getPermittedValue(start) == 1)
			// {
			// int i = 0;
			// for (DIRECTION d : new DIRECTION[] { d_s, d_e }) {
			// if (getPermittedValue(check, d) == 1) {
			// f[i] = f[i]
			// && getPermittedValue(check,
			// d.getNeiboringDirection()) != 0;
			// f[i] = f[i]
			// && getPermittedValue(check,
			// d.getDeNeiboringDirection()) != 0;
			// }
			// i++;
			// }
			// }
			// return f[0] || f[1];
		}
	}

	public static boolean isTrapOrWaterPermitted(Point p) {
		return isTrapOrWaterPermitted(p.x, p.y);
	}

	private static boolean isTrapOrWaterPermitted_isWakable(DIRECTION d,
			Mass goal) {
		return isTrapOrWaterPermitted_isWakable(
				MassCreater.getMass(goal.getPoint(), d), goal);
	}

	private static boolean isTrapOrWaterPermitted_isWakable(Mass start,
			Mass goal) {
		if (!start.WALKABLE)
			return false;
		DIRECTION d = DIRECTION.getDirection(start.getPoint(), goal.getPoint());
		if (DIRECTION.isBias(d)) {
			if (getPermittedValue(start, d.getNeiboringDirection()) == 2)
				return false;
			if (getPermittedValue(start, d.getDeNeiboringDirection()) == 2)
				return false;
		}
		return true;
	}

	public static void judgement() {
		Player.me.saisen = null;
		Player.me.shop = null;
		FrameShaker.doneStrongly();
		String[] msg = { "あなたの行為は真っ黒です！", "因果応報天罰覿面！", "行きますよ小町！",
				"あ～らら、面倒なことするねぇ", "やれやれ、お仕事の時間だねぇ" };
		Message.setConcatFlag(false);
		Message.set(msg[(int) Math.floor(new R().nextDouble() * msg.length)]);
		SE.SYSTEM_MONSTER_HOUSE.play();
		Player.me.setAnimation(new Base_Anime(Player.me) {
			@Override
			protected boolean draw(Graphics2D g) {
				Player.me.startDamaging();
				return isFrameLessThan(30);
			}
		});
		BGM.dorobo.play(100);
		MapList.setFlagSheef(true);
		EnemyTable.setFlagSheefFirstEnemy();
	}

	public static void load(MapList ml) {
		clear();
		ME = ml;
	}

	public static void nextFloor() {
		if (next_floor_asc) {
			ascFloor();
		} else {
			desFloor();
			setNextFloor(false);
		}
	}

	public static void performMonsterHosue() {
		boolean flag_nobody = true;
		for (Base_Enemy em : MapList.getListEnemy()) {
			if (MassCreater.isPlayerInTheSameRoom(em.getMassPoint())) {
				flag_nobody = false;
				break;
			}
		}
		Player.me.setCondition(CONDITION.透視, 0);
		SE.SYSTEM_MONSTER_HOUSE.play();
		if (flag_nobody) {
			int item_count = 0;
			for (Base_Artifact a : MapList.getListArtifact()) {
				if (a instanceof Base_Item
						&& MassCreater.isPlayerInTheSameRoom(a.getMassPoint())) {
					item_count++;
				}
			}
			if (item_count > 5) {
				FrameShaker.doneNormaly();
				Message.set("アイテムハウスだ！");
			} else {
				FrameShaker.doneSoftly();
				Message.set("モンスターハウスだった！");
			}
		} else {
			awaken();
			FrameShaker.doneStrongly();
			MassCreater.flag_neglect_bgm = true;
			if (PresentField.get().SMH == null) {
				Message.set("モンスターハウスだ！！");
				BGM.TakeAChance.play(100);
			} else {
				Message.set(PresentField.get().SMH.name().concat("だ！！"));
				BGM.black.play(100);
			}
			Player.me.setAnimation(new Base_Anime(Player.me) {
				@Override
				protected boolean draw(Graphics2D g) {
					Player.me.startDamaging();
					return isFrameLessThan(30);
				}
			});
		}
	}

	// public static void setRandomTrap(Point p) {
	// if (MapList.getArtifact(p) == null) {
	// addArtifact(Base_Trap.trapList(p));
	// }
	// }

	private static Base_Enemy randomEnemy(Point p) {
		Base_Enemy em = EnemyTable.getRandomEnemy(0);
		if (em == null) {
			em = new ゆっくり霊夢(p, 1);
		}
		em.setMassPoint(p);
		return em;
	}

	public synchronized static void removeArtifact(Base_Artifact af) {
		list_artifact.remove(af);
	}

	public synchronized static void removeEnemy(Base_Enemy em) {
		list_enemy.remove(em);
	}

	public static void resetHutoChan() {
		huto_waters = null;
	}

	public static void setFlagSheef(boolean b) {
		flag_sheef_start = b;
	}

	public static void setFloor(int f) {
		ME.floor = f;
	}

	public static void setNextFloor(boolean asc) {
		next_floor_asc = asc;
	}

	public static void setTrapOnMap(Point p, Base_Artifact a) {
		if (MapList.getArtifact(p) == null) {
			addArtifact(a);
		}
	}

	/**
	 * Point p の周囲にエネミーをint値召喚する
	 * 
	 * @param p
	 */
	public static void summonEnemy(Point p, int value, final Base_Enemy em,
			CONDITION... C) {
		if (em != null) {
			for (CONDITION c : C) {
				em.setCondition(c, 0);
			}
		}
		List<Point> list_blank = new ArrayList<Point>();
		Point point = new Point();
		int count = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				point.x = p.x + i;
				point.y = p.y + j;
				if (MassCreater.getMass(point).WALKABLE
						&& MapList.getCreature(point) == null
						&& Player.me.getMassPoint() != point) {
					list_blank.add(new Point(point.x, point.y));
				}
			}
		}
		Random ran = new R();
		if (list_blank.size() <= value) {
			for (Point blank_point : list_blank) {
				if (value <= count)
					return;
				if (em == null) {
					final Base_Enemy e = randomEnemy(blank_point);
					for (CONDITION c : C) {
						e.setCondition(c, 0);
					}
					e.flag_2nd_move = e.flag_3rd_move = e.enemy_actioned = true;
					addEnemy(e);
					MainMap.addEffect(new DoronEffect(e.getMassPoint(),
							new Task() {
								/**
						 *
						 */
								private static final long serialVersionUID = 1L;

								@Override
								public void work() {
								}
							}));
				} else {
					// FIXME enemy_actioned=true;
					em.setMassPoint(blank_point);
					addEnemy(em);
					MainMap.addEffect(new DoronEffect(em.getMassPoint(),
							new Task() {
								/**
						 *
						 */
								private static final long serialVersionUID = 1L;

								@Override
								public void work() {
								}
							}));
				}
				count++;
			}
		} else {
			while (true) {
				if (value <= count) {
					return;
				}
				int selection = ran.nextInt(list_blank.size());
				if (MapList.getEnemy(list_blank.get(selection)) == null) {
					if (em == null) {
						final Base_Enemy e = randomEnemy(list_blank
								.get(selection));
						for (CONDITION c : C) {
							e.setCondition(c, 0);
						}
						e.enemy_actioned = true;
						for (CONDITION c : C) {
							e.setCondition(c, 0);
						}
						addEnemy(e);
						MainMap.addEffect(new DoronEffect(e.getMassPoint(),
								new Task() {
									/**
							 *
							 */
									private static final long serialVersionUID = 1L;

									@Override
									public void work() {
									}
								}));
					} else {
						// FIXME enemy_actioned=true;
						em.setMassPoint(list_blank.get(selection));
						addEnemy(em);
						MainMap.addEffect(new DoronEffect(em.getMassPoint(),
								new Task() {
									/**
							 *
							 */
									private static final long serialVersionUID = 1L;

									@Override
									public void work() {
									}
								}));
					}
					count++;
				}
			}
		}
	}

	public static void water(Base_Artifact af) {
		if (af != null && !af.getListComposition().contains(ENCHANT_SIMBOL.金)) {
			if (af instanceof SpellCard) {
				af.setForgeValue(-1);
			} else if (af instanceof Food && !(af instanceof 腐ったおにぎり)) {
				Base_Item a = new 腐ったおにぎり(af.getMassPoint());
				a.flag_pick_checked = ((Food) af).flag_pick_checked;
				MapList.removeArtifact(af);
				MapList.addArtifact(a);
			} else if (af instanceof Base_Trap) {
				MapList.removeArtifact(af);
			}
		}
	}

	private int floor = 0;

	private MapList() {
	}

	public MapList(MapList mp) {
		floor = mp.floor;
	}

}
