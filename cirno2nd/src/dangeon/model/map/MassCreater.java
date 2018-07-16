package dangeon.model.map;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

import main.Second_Firster;
import main.res.SE;
import main.util.DIRECTION;
import main.util.FileReadSupporter;
import main.util.Show;
import dangeon.controller.DangeonScene;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.condition.Nap;
import dangeon.model.config.Config;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.config.table.ItemTable;
import dangeon.model.config.table.TrapTable;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.map.field.Base_Map;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.special.map.BossMap;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.FakeStairs;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.trap.落とし穴の罠;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.npc.慧音NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.constant.MAP;
import dangeon.view.detail.MainMap;
import dangeon.view.detail.MiniMap;

public class MassCreater implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static MassCreater ME;

	private static boolean spot;

	/**
	 * 大部屋
	 */
	public static void breakIntoLargeRoom() {
		for (Mass ms[] : mass) {
			for (Mass m : ms) {
				if (!(m.X == 0 || m.X == MassCreater.WIDTH - 1 || m.Y == 0 || m.Y == MassCreater.HEIGHT - 1)) {
					if (!m.ROOM) {
						m.setChecked(true);
						m.ROOM = true;
					}
					m.ROAD = false;
					m.WATER = false;
					m.WATER_LEEF = false;
					m.WALKABLE = true;
					m.setVisible(false);
					m.tellBroken();
				}
			}
		}
		for (Room r : ip.getRoomList()) {
			if (r.flag_monster_house) {
				MapList.performMonsterHosue();
			}
		}
		ip = new InitialPlacement();
		retakeMassSet();
	}

	public static boolean dig(Point p) {
		return dig(p, false);
	}

	public static boolean dig(Point p, boolean no_msg) {
		Mass mass = getMass(p);
		if (mass.DIGGABLE) {
			mass.setDigged(true);
			return true;
		} else if (!no_msg) {
			Message.set("ここは掘れない");
		}
		return false;
	}

	public static void frozenWater() {
		frozenWater(false);
	}

	public static void frozenWater(boolean not_frozen_deep_water) {
		boolean iced = false;
		for (Mass mass : getMasses()) {
			if (mass.WATER_NOT_FROZEN && not_frozen_deep_water) {
				continue;
			}
			mass.setFrozen(true);
			iced = true;
		}
		MassCreater.retakeMassSet();

		if (iced
				&& EnchantSpecial.enchantSimbolAllCheck(CASE.ALL,
						ENCHANT_SIMBOL.冴)) {
			SE.LIGHT_ON.play();
			Scene_Action.getMe().tellRestStop();
			Player.me.setCondition(CONDITION.透視, 40);
		}
	}

	public static void frozenWater(Room r) {
		if (r == null)
			return;
		boolean iced = false;
		for (Mass mass : getMass(r)) {
			boolean massIced = mass.WATER_FROZEN;
			if (!massIced) {
				mass.setFrozen(true);
				iced = true;
			}
		}
		MassCreater.retakeMassSet();

		if (iced
				&& EnchantSpecial.enchantSimbolAllCheck(CASE.ALL,
						ENCHANT_SIMBOL.冴)) {
			SE.LIGHT_ON.play();
			Scene_Action.getMe().tellRestStop();
			Player.me.setCondition(CONDITION.透視, 40);
		}
	}

	public static String getHackName() {
		return hack_name;
	}

	public static InitialPlacement getIP() {
		return ip;
	}

	public static Point getItemIP() {
		return ip.getItemPoint();
	}

	public static Mass[][] getMass() {
		return mass;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return (ArrayIndexOutOfBoundsException => Mass.nullpo
	 */
	public static Mass getMass(int x, int y) {
		try {
			return mass[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			return Mass.nullpo;
		}
	}

	public static Mass getMass(Point p) {
		try {
			return mass[p.x][p.y];
		} catch (ArrayIndexOutOfBoundsException e) {
		} catch (NullPointerException e) {
		}
		return Mass.nullpo;
	}

	public static Mass getMass(Point point, DIRECTION d) {
		return getMass(point.x + d.X, point.y + d.Y);
	}

	public static Mass[] getMass(Room r) {
		ArrayList<Mass> list = new ArrayList<Mass>(r.getSize());
		for (int x = r.X; x < r.X + r.W; x++) {
			for (int y = r.Y; y < r.Y + r.H; y++) {
				list.add(getMass(x, y));
			}
		}
		return list.toArray(new Mass[0]);
	}

	private static Mass[] getMasses() {
		Mass[] masses = new Mass[mass.length * mass[0].length];
		int k = 0;
		for (int i = 0; i < mass.length; i++) {
			for (int j = 0; j < mass[0].length; j++) {
				masses[k++] = mass[i][j];
			}
		}
		return masses;
	}

	public static ArrayList<Mass> getMasses_NotSpotValid(int x, int y) {
		ArrayList<Mass> list = new ArrayList<Mass>();
		for (int i = -4; i <= 4; i++) {
			for (int j = -3; j <= 3; j++) {
				list.add(MassCreater.getMass(x + i, y + j));
			}
		}
		return list;
	}

	public static Mass getPlayerMass() {
		return getMass(Player.me.getMassPoint());
	}

	public static Point getPlayerPoint() {
		return ip.getPlayerPoint();
	}

	public static Room getPlayerRoom() {
		return ip.getRoom(Player.me.getMassPoint());
	}

	public static Room getRoom(int x, int y) {
		if (ip == null)

			ip = new InitialPlacement();
		return ip.getRoom(x, y);
	}

	public static Room getRoom(Point p) {
		return getRoom(p.x, p.y);
	}

	public static Point getStairsIP() {
		return ip.getStairsPoint();
	}

	public static Point getTrapIP() {
		return ip.getTrapPoint();
	}

	public static Point getTrapIP(Room r) {
		return ip.getTrapPoint(r);
	}

	public static Point getWarpPoint(Base_Enemy em) {
		if (em != null) {
			em.RoomInRootClear();
		}
		return getIP().getCreaturePoint();
	}

	/**
	 * the sight from 2 to 1.<br/>
	 * Notice that it is not always that one see another and the one see first
	 * one.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param range
	 * @return
	 */
	public static boolean is1InTheSightFrom2(int x1, int y1, int x2, int y2,
			int range) {
		Room r = ip.getRoom(x2, y2);
		if (r != null) {
			if (r.isThePointInExtentdedThis(x1, y1)) {
				return true;
			}
		}
		return (Math.abs(x2 - x1) <= range && Math.abs(y2 - y1) <= range);
	}

	/**
	 * the sight from 2 to 1.<br/>
	 * Notice that it is not always that one see another and the one see first
	 * one.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param range
	 * @return
	 */
	public static boolean is1InTheSightFrom2(int x1, int y1, int x2, int y2,
			int r1, int r2) {
		Room r = ip.getRoom(x2, y2);
		if (r != null) {
			if (r.isThePointInExtentdedThis(x1, y1)) {
				return true;
			}
		}
		return (Math.abs(x2 - x1) <= r1 && Math.abs(y2 - y1) <= r2);
	}

	/**
	 * 主人公から見える出入り口
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean isEntrance(int x, int y) {
		Room r = getRoom(x, y);
		return r != null && ip.getRoom(Player.me.getMassPoint()).equals(r)
				&& r.isEntrance(x, y);
	}

	public static boolean isMovedWithStep() {
		return ME.MOVE_BY_STEP;
	}

	public static boolean isPlayerInRoom() {
		return ip.getRoom(Player.me.getMassPoint()) != null;
	}

	public static boolean isPlayerInTheSameRoom(int x, int y) {
		try {
			return ip.getRoom(Player.me.getMassPoint())
					.equals(ip.getRoom(x, y));
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * 
	 * @param p
	 * @return 入り口は無効
	 */
	public static boolean isPlayerInTheSameRoom(Point p) {
		try {
			return ip.getRoom(Player.me.getMassPoint()).equals(ip.getRoom(p));
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * Notice that it is not always that one see another and the one see first
	 * one.
	 * 
	 * @see MassCreater#isThePointInThePlayerSight(Point, int)
	 */
	public static boolean isPlayerInTheSightFromThePoint(int x, int y, int range) {
		return is1InTheSightFrom2(Player.me.getMassPoint().x,
				Player.me.getMassPoint().y, x, y, range);
	}

	/**
	 * Notice that it is not always that one see another and the one see first
	 * one.
	 * 
	 * @see MassCreater#isThePointInThePlayerSight(Point, int)
	 */
	public static boolean isPlayerInTheSightFromThePoint(Point p, int range) {
		return isPlayerInTheSightFromThePoint(p.x, p.y, range);
	}

	/**
	 * 
	 * @param p
	 * @param p2
	 * @return
	 */
	public static boolean isPointInTheSameRoom(Point source, Point target) {
		return isPointInTheSameRoomInEntrance(source, target);
	}

	/**
	 * @param source
	 * @param target
	 *            このこが通路にいてもtrue
	 * @return
	 */
	public static boolean isPointInTheSameRoomInEntrance(Point source,
			Point target) {
		try {
			// TODO 合ってるのこれ
			if (ip.getRoomInEntrance(target).equals(ip.getRoom(source)))
				return true;
		} catch (NullPointerException e) {
		}
		for (DIRECTION d : DIRECTION.values()) {
			if (target.equals(d.getFrontPoint(source.getLocation())))
				return true;
		}
		return false;
	}

	public static boolean isPointOutOfDangeon(int x, int y) {
		return x < 1 || x >= dangeon_width - 1 || y < 1
				|| y >= dangeon_height - 1;

	}

	public static boolean isPointOutOfDangeon(Point p) {
		return isPointOutOfDangeon(p.x, p.y);
	}

	public static boolean isSpotValid() {
		return spot && !Player.me.conditionCheck(CONDITION.蛍);
	}

	public static boolean isStandMass(Point p) {
		try {
			if (mass[p.x][p.y].ROOM || mass[p.x][p.y].ROAD) {
				for (Base_Creature c : MapList.getListCreature()) {
					if (c.getMassPoint().equals(p)) {
						return false;
					}
				}
				return true;
			}
			return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	public static boolean isTheObjectInsideOfScreen(Base_MapObject o) {
		int dif_x = Player.me.getMassPoint().x - o.getMassPoint().x;
		int dif_y = Player.me.getMassPoint().y - o.getMassPoint().y;
		dif_x = Math.abs(dif_x);
		dif_y = Math.abs(dif_y);
		return dif_x > MAP.TILE_HORIZON_INNER_CENTER_ID + 1
				|| dif_y > MAP.TILE_VERTICAL_INNER_MIDDLE_ID + 1;

	}

	/*
	 * public synchronized static void drawMainMap_Objects(Graphics2D g) { for
	 * (Base_Artifact a : Map.getListArtifact()) { MainMap.drawMapObject(g,
	 * a.getMassPoint(), a.getImage()); } for(NPC_abstract npc :
	 * Map.getListNpc()){ MainMap.drawMapObject(g, npc.getMassPoint(),
	 * npc.getImage()); } boolean flag = false; for (Base_Enemy e :
	 * Map.getListEnemy()) { MainMap.drawMapObject(g, e); boolean f =
	 * e.upDate_drawThread(); flag = flag||f; }
	 * MainSystem.flag_wait_for_enemy_drawing = flag; playerDelt(); }
	 */
	/**
	 * Player > Walk
	 */
	/*
	 * public static void playerDelt(){ Point delt = Player.me.getDelt();
	 * if(delt.x==0&&delt.y==0) return; int x=0,y=0; if(delt.x > 0) x=-1; else
	 * if(delt.x < 0)x=1; if(delt.y > 0) y=-1; else if(delt.y < 0) y=1;
	 * Player.me.getDelt().translate(x*MAP.MoveSpeed,y*MAP.MoveSpeed);
	 * if(delt.x==0&&delt.y==0){ MainSystem.flag_moving_map=false;
	 * keyAccepter.upDate(); }
	 * 
	 * }
	 */

	/**
	 * Notice that it is not always that one see another and the one see first
	 * one.
	 * 
	 * @see MassCreater#isThePointInThePlayerSight(Point, int)
	 */
	public static boolean isThePointInThePlayerSight(int x, int y, int range) {
		return is1InTheSightFrom2(x, y, Player.me.getMassPoint().x,
				Player.me.getMassPoint().y, range);
	}

	/**
	 * プレイヤーから見てその座標pが視界内ならtrue。<br>
	 * 以下range=1の事例（＠：主人公　＊：座標ｐ）
	 * 
	 * <pre>
	 * ーーーーーーーーーーーーーーーーーーーーーーーー
	 * 壁壁＠壁壁壁
	 * 壁ＲＲＲＲ壁
	 * 壁ＲＲＲ＊壁
	 * 
	 * 主人公が通路内なので周囲１マスすか見えない
	 * => false
	 * ーーーーーーーーーーーーーーーーーーーーーーーー
	 * 壁壁Ｐ壁壁壁
	 * 壁＊ＲＲＲ壁
	 * 壁ＲＲＲ＠壁
	 * 
	 * 同じ部屋内
	 * => true
	 * ーーーーーーーーーーーーーーーーーーーーーーーー
	 * 壁壁＊壁壁壁
	 * 壁ＲＲＲＲ壁
	 * 壁ＲＲＲ＠壁
	 * 
	 * 部屋の周囲１マスまでが視界内
	 * => true
	 * ーーーーーーーーーーーーーーーーーーーーーーーー
	 * 壁壁Ｐ壁壁壁
	 * ＊ＲＲＲＲ壁
	 * 壁ＲＲＲ＠壁
	 * 
	 * 壁も「周囲１マス」に入る
	 * => true
	 * ーーーーーーーーーーーーーーーーーーーーーーーー
	 * </pre>
	 * 
	 */
	public static boolean isThePointInThePlayerSight(Point p, int range) {
		return isThePointInThePlayerSight(p.x, p.y, range);
	}

	public static boolean isThePointInThePlayerSight(Point p, int r1, int r2) {
		return is1InTheSightFrom2(p.x, p.y, Player.me.getMassPoint().x,
				Player.me.getMassPoint().y, r1, r2);
	}

	public static boolean isWalk(Point p) {
		// System.out.println(p);
		return (getMass(p).ROOM || getMass(p).ROAD)
				&& MapList.getCreature(p) == null;
	}

	/**
	 * 壁抜けは考慮に入れていない
	 * 
	 * @param c
	 * @param point
	 * @return
	 */
	public static boolean isWalkableFor(Base_Creature c, Point point) {
		if (MapList.isCreature(point))
			return false;
		Mass mass = MassCreater.getMass(point);
		if (!mass.WALKABLE)
			return false;
		if (!c.isWatering()) {
			if (mass.WATER)
				return false;
		}
		return true;
	}

	public static void load(MassCreater me) {
		ME = me;
		ME.createMapByDoor();
	}

	/**
	 * マスの再描画 つるはしなどで壁を掘った際に呼ばれる
	 */
	public static void retakeMassSet() {
		ip.extendRoom();
		for (Mass[] m2 : mass) {
			for (Mass m : m2) {
				m.setWallTileNo();
			}
		}
		MainMap.requestForUpdate();
	}

	public static void saveFuckinMap() {
		if (Show.showConfirmDialog("現在のマップをfuckinしますか？") != 0)
			return;
		try {
			File file = new File("fukin_map.txt");
			java.io.FileWriter filewriter = new java.io.FileWriter(file, true);
			filewriter.write("【");
			filewriter.write(new Date().toString());
			filewriter.write("】\t");
			filewriter.write(Second_Firster.ME.getTitle());
			filewriter.write("\r\n");
			filewriter.close();
			Show.showInformationMessageDialog("fuckin_mapに現在のマップを登録しました。");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ミニマップを全てめくる
	 */
	public static void setAllMassOnMiniMap() {
		for (Mass[] m2 : mass) {
			for (Mass m : m2) {
				if (m.WALKABLE) {
					MiniMap.addPicture(m);
				}
			}
		}
	}

	public static void setExplotionMass(Point p) {
		setExsetExplotionMass_private(p);
		retakeMassSet();
	}

	public static void setExplotionMass(Point... ps) {
		for (Point p : ps) {
			setExsetExplotionMass_private(p);
		}
		retakeMassSet();
	}

	private static void setExsetExplotionMass_private(Point p) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				getMass(p.x + i, p.y + j).setDigged(true);
			}
		}
	}

	public static void setFallTrap(int value) {
		for (int i = 0; i <= value; i++) {
			TaskOnMapObject.addTrapSetTask(new 落とし穴の罠(ip.getTrapPoint()));
		}
	}

	public static void setHackName(String name) {
		hack_name = name;
	}

	/**
	 * 「明かり」にする
	 */
	public static void setLight() {
		setAllMassOnMiniMap();
		spot = false;
	}

	public static boolean setSpot(boolean b) {
		return spot = b;
	}

	/**
	 * ターンエンド時処理
	 */
	public static void stepEnd() {
		for (Mass[] m2 : getMass())
			for (Mass m : m2)
				m.stepEnd();
	}

	public static void turnCount() {
		if (isPlayerInRoom()) {
			getPlayerRoom().addCount();
		}
	}

	public final Point NEXT_INI_POINT;

	public final static int WIDTH = 40, HEIGHT = 30;

	private static Mass[][] mass;

	private static InitialPlacement ip;

	private static int dangeon_width, dangeon_height;

	private static String hack_name = null;

	// private boolean warning() {
	// TODO_ 初期部屋内に危険分子がいる場合false
	// for (Room r : ip.getRoomList())
	// if (r.flag_monster_house)
	// return true;
	// for (Base_Creature c : MapList.getListCreature())
	// if (c.isFlagWarning()
	// && !MassCreater.isPlayerInTheSameRoom(c.getMassPoint()))
	// return true;
	// return false;
	// }

	public static Point getCreatureIp() {
		return ip.getCreaturePoint();
	}

	public static Point getMonsterPoint(Room r) {
		return ip.getMonsterPoint(r);
	}

	public boolean MOVE_BY_STEP;

	public static boolean flag_neglect_bgm;

	public static final int X_MIN = 0, Y_MIN = 0;

	/**
	 * Field = random
	 */
	public MassCreater() {
		NEXT_INI_POINT = null;
		MOVE_BY_STEP = true;
		ME = this;
	}

	public MassCreater(Base_Map pf, boolean step) {
		this(pf, pf.getEntrancePoint(), step);
	}

	public MassCreater(Base_Map pf, Point p, boolean step) {
		PresentField.setPresentField(pf);
		NEXT_INI_POINT = p;
		MOVE_BY_STEP = step;
		ME = this;
	}

	/**
	 * 1Fを生成するときに呼ぶ
	 * 
	 * @see 慧音NPC
	 * @param unchecked_level
	 * <br/>
	 *            0=>未識別なし（全識別）<br/>
	 *            1=>消費未識別（装備識別済み）<br/>
	 *            2=>装備も未識別（全未識別）<br/>
	 */
	public void createFirstMap(int unchecked_level) {
		Config.setReimuChecked(false);
		Player.me.resetPlayingMilliTime();
		createItemTable();
		// ItemTable.testCreate(PresentField.get().getClassName());
		Checker.init(unchecked_level);
		MapList.setFloor(0);
		R.next();
		createMap();
		Message.clearRecord();
	}

	public void createItemTable() {
		ItemTable.testCreate(getTable(1, hack_name));
	}

	public void createItemTableNoDungeon() {
		ItemTable.testCreate();
	}

	public void createMap() {
		if (MOVE_BY_STEP) {
			new StairScene(this);
		} else {
			createMapByDoor();
		}
	}

	/**
	 * 扉からの移動
	 */
	private void createMapByDoor() {
		DangeonScene.setScene(DangeonScene.STAIRS);
		setNewMap();
		DangeonScene.setScene(DangeonScene.DANGEON);
	}

	private BufferedReader getTable(Base_Map base_Map) {
		try {
			String name = base_Map.getCSVName();
			URL url = this.getClass().getResource(name);
			return new BufferedReader(new InputStreamReader(url.openStream(),
					"UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private BufferedReader getTable(int index_0_enemy__1_item__2_trap,
			String... hack) {
		return getTable(PresentField.get());
	}

	public void initBossMap(int enemy_number, Base_Map_Random bmr) {
		EnemyTable.tableCreate(getTable(bmr));
		TrapTable.createTrapTable(getTable(bmr));
		TrapTable.setOnMap();
		ItemTable.setOnMapInNormalFirst();
		EnemyTable.enemyTable(enemy_number, !PresentField.get()
				.isForcedToMakeEnemy());
		EnchantSpecial.setBake();
	}

	private void initialPlacement_FixedField() {
		if (NEXT_INI_POINT != null) {
			Player.me.setMassPoint(NEXT_INI_POINT);
		} else if (PresentField.get().getEntrancePoint() != null) {
			Player.me.setMassPoint(PresentField.get().getEntrancePoint());
		} else {
			Show.showErrorMessageDialog("固定フィールドはgetEntrancePointを指定して下さい");
			Player.me.setMassPoint(ip.getPlayerPoint());
		}

		for (ListIterator<Base_MapObject> i = PresentField.get()
				.getIterator_ObjectList(); i.hasNext();) {
			Base_MapObject o = i.next();
			if (o instanceof Base_Artifact) {
				MapList.addArtifact((Base_Artifact) o);
			} else if (o instanceof Base_NPC) {
				MapList.addEnemy((Base_NPC) o);
			} else if (o instanceof Base_Enemy) {
				MapList.addEnemy((Base_Enemy) o);
			}
			i.remove();
		}
		if (PresentField.get() instanceof BossMap) {
			((BossMap) PresentField.get()).initBossMap(this);
		}
	}

	private void initialPlacement_RandomField() {
		Player.me.setMassPoint(new Point(Mass.nullpo.X, Mass.nullpo.Y));
		EnemyTable.tableCreate(getTable(0, hack_name));
		TrapTable.createTrapTable(getTable(2, hack_name));
		SpecialRoom.set();
		MapList.addArtifact(new Stairs(getStairsIP()));
		if (PresentField.get().isInsane()) {
			MapList.addArtifact(new FakeStairs(getStairsIP()));
		}
		TrapTable.setOnMap();
		ItemTable.setOnMapInNormalFirst();
		EnemyTable.enemyTable(0);
		Player.me.setMassPoint(ip.getPlayerPoint());
		if (MapList.checkSpecialRoom(Player.me.getMassPoint())) {
			Nap.開幕();
		}
		EnchantSpecial.setBake();
	}

	private void selectInitialPlacement() {
		ip = new InitialPlacement();
		flag_neglect_bgm = false;
		if (PresentField.isRandomField()) {
			initialPlacement_RandomField();
		} else {
			initialPlacement_FixedField();
		}
		if (!flag_neglect_bgm) {
			PresentField.get().playBGM();
		}
	}

	void setNewMap() {
		MapList.clear();
		MapList.setFlagSheef(false);
		if (Player.me.saisen != null) {
			Player.me.saisen.release();
			Player.me.saisen = null;
		}
		if (Player.me.shop != null) {
			Player.me.shop.release();
			Player.me.shop = null;
		}
		MapList.resetHutoChan();
		CONDITION.conditionAllClear(Player.me);
		try {
			BufferedReader bf = new BufferedReader(
					FileReadSupporter.readUTF8Map());
			int x;
			int y = 0;
			final String FIRST_LINE = bf.readLine();
			String line = FIRST_LINE;
			x = FIRST_LINE.length();
			while (line != null) {
				if (line.length() != x) {
					Show.showCriticalErrorMessageDialog(
							"マップの列が行と行で変化しています\n文字化けしていないか確認下さい", String
									.valueOf(x).concat(FIRST_LINE), String
									.valueOf(line.length()).concat(line));
				}
				y++;
				line = bf.readLine();
			}
			bf.close();
			dangeon_width = x;
			dangeon_height = y;
			bf = new BufferedReader(FileReadSupporter.readUTF8Map());
			mass = new Mass[x][y];
			for (int i = 0; i < y; i++) {
				line = bf.readLine();
				for (int j = 0; j < x; j++) {
					mass[j][i] = new Mass(j, i, line.charAt(j));
				}
			}
			bf.close();
			for (Mass[] m2 : mass) {
				for (Mass m : m2) {
					m.setWallTileNo();
				}
			}
			spot = !PresentField.get().isLightful();
			selectInitialPlacement();
			MainMap.requestForUpdate();
			if (PresentField.isRandomField()) {
				Player.me.performNewMap();

			}
		} catch (IOException e) {
			Show.showCriticalErrorMessageDialog(e);
		}
	}

}
