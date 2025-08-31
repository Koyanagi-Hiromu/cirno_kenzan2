package dangeon.model.map;

import java.awt.Point;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印罠師;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.anime.DoronEffect;
import dangeon.view.constant.MAP;
import dangeon.view.detail.MainMap;
import dangeon.view.detail.MiniMap;
import main.res.SE;
import main.util.DIRECTION;
import main.util.Show;

public class InitialPlacement implements Serializable {
	public class Room implements Serializable {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public final int X, Y, W, H;
		boolean flag_monster_house = false, flag_water_room;
		private boolean flag_drawed = false;
		private int stay = 0;

		public final RoundRectangle2D.Double LIGHT;

		public boolean HOLY, SHOP;

		Room(int x, int y) {
			ArrayList<Mass> list = new ArrayList<Mass>();
			checkRoom(x, y, list, new DIRECTION[] { DIRECTION.DOWN,
					DIRECTION.UP, DIRECTION.LEFT, DIRECTION.RIGHT });
			int max_x, max_y, min_x, min_y;
			max_x = max_y = 0;
			min_x = MassCreater.WIDTH;
			min_y = MassCreater.HEIGHT;
			for (Mass m : list) {
				if (max_x < m.X) {
					max_x = m.X;
				}
				if (min_x > m.X) {
					min_x = m.X;
				}
				if (max_y < m.Y) {
					max_y = m.Y;
				}
				if (min_y > m.Y) {
					min_y = m.Y;
				}
			}
			X = min_x;
			Y = min_y;
			W = max_x - min_x + 1;
			H = max_y - min_y + 1;
			LIGHT = new RoundRectangle2D.Double(0, 0, MAP.TILE_SIZE * (W + 2)
					- MAP.TILE_SIZE / 2, MAP.TILE_SIZE * (H + 2)
					- MAP.TILE_SIZE / 2, MAP.TILE_SIZE, MAP.TILE_SIZE);
			for (x = X; x < X + W; x++) {
				for (y = Y; y < Y + H; y++) {
					unchecked_mass[x][y] = false;
				}
			}
		}

		Room(Room r) {
			int dx = 0, dy = 0, dw = 0, dh = 0;
			boolean flag = true;
			for (int i = 0; i < r.W; i++) {
				if (!MassCreater.getMass(r.X + i, r.Y - 1).WALKABLE) {
					flag = false;
					break;
				}
			}
			if (flag) {
				dy = -1;
				dh = 1;
			} else {
				flag = true;
				for (int i = 0; i < r.W; i++) {
					if (!MassCreater.getMass(r.X + i, r.Y + r.H).WALKABLE) {
						flag = false;
						break;
					}
				}
				if (flag) {
					dh = 1;
				} else {
					flag = true;
					for (int i = 0; i < r.H; i++) {
						if (!MassCreater.getMass(r.X - 1, r.Y + i).WALKABLE) {
							flag = false;
							break;
						}
					}
					if (flag) {
						dx = -1;
						dw = 1;
					} else {
						flag = true;
						for (int i = 0; i < r.H; i++) {
							if (!MassCreater.getMass(r.X + r.W, r.Y + i).WALKABLE) {
								flag = false;
								break;
							}
						}
						if (flag) {
							dw = 1;
						}
					}
				}
			}
			X = r.X + dx;
			Y = r.Y + dy;
			W = r.W + dw;
			H = r.H + dh;
			flag_drawed = false;
			flag_monster_house = r.flag_monster_house;
			LIGHT = new RoundRectangle2D.Double(MAP.TILE_SIZE / 2,
					MAP.TILE_SIZE / 2, MAP.TILE_SIZE * (W + 1), MAP.TILE_SIZE
							* (H + 1), MAP.TILE_SIZE, MAP.TILE_SIZE);
			for (int x = X; x < X + W; x++) {
				for (int y = Y; y < Y + H; y++) {
					Mass mass = MassCreater.getMass(x, y);
					mass.ROOM = true;
					mass.ROAD = false;
				}
			}
		}

		public void addCount() {
			if (PresentField.get().isDungeon()) {
				int MAX = 40;
				if (stay != MAX) {
					if (stay < MAX)
						stay++;
					return;
				}
				stay++;
				SE.LIGHT_ON.play();
				for (int x = X; x < X + W; x++) {
					for (int y = Y; y < Y + H; y++) {
						MassCreater.getMass(x, y).setChecked(true);
						final Base_Artifact a = MapList.getArtiface(x, y);
						if (a != null)
							trap(a);
					}
				}
				MiniMap.reset();
			}
		}

		private void checkRoom(int x, int y, ArrayList<Mass> list,
				DIRECTION[] ds) {
			Mass m = MassCreater.getMass(x, y);
			if (!list.contains(m) && m.ROOM) {
				list.add(m);
				for (DIRECTION d : ds) {
					checkRoom(x + d.X, y + d.Y, list, ds);
				}
			}
		}

		public void draw() {
			for (int x = X; x < X + W; x++) {
				for (int y = Y; y < Y + H; y++) {
					MiniMap.addPicture(MassCreater.getMass(x, y));
				}
			}
			flag_drawed = true;
		}

		public Point getEntrance() {
			ArrayList<Point> list = new ArrayList<Point>();
			for (int x = X + 1; x < X + W - 1; x++) {
				if (MassCreater.getMass(x, Y - 1).WALKABLE) {
					list.add(new Point(x, Y - 1));
				}
				if (MassCreater.getMass(x, Y + H).WALKABLE) {
					list.add(new Point(x, Y + H));
				}
			}
			for (int y = Y + 1; y < Y + H - 1; y++) {
				if (MassCreater.getMass(X - 1, y).WALKABLE) {
					list.add(new Point(X - 1, y));
				}
				if (MassCreater.getMass(X + W, y).WALKABLE) {
					list.add(new Point(X + W, y));
				}
			}
			if (list.size() == 0) {
				return null;
			}
			return list
					.get((int) Math.floor(new R().nextDouble() * list.size()));
		}

		public int getEntranceNumber() {
			int i = 0;
			for (int x = X + 1; x < X + W - 1; x++) {
				if (MassCreater.getMass(x, Y - 1).WALKABLE) {
					i++;
				}
				if (MassCreater.getMass(x, Y + H).WALKABLE) {
					i++;
				}
			}
			for (int y = Y + 1; y < Y + H - 1; y++) {
				if (MassCreater.getMass(X - 1, y).WALKABLE) {
					i++;
				}
				if (MassCreater.getMass(X + W, y).WALKABLE) {
					i++;
				}
			}
			return i;
		}

		/**
		 * 部屋内のマスを全て取得
		 * 
		 * @return
		 */
		public ArrayList<Point> getListAllMassPointInRoom() {
			ArrayList<Point> list = new ArrayList<Point>();
			for (int x = X; x < X + W; x++) {
				for (int y = Y; y < Y + H; y++) {
					if (MassCreater.getMass(x, y).ROOM) {
						list.add(new Point(x, y));
					}
				}
			}
			if (list.size() == 0) {
				return null;
			}
			return list;
		}

		public List<Point> getListEntrance() {
			ArrayList<Point> list = new ArrayList<Point>();
			for (int x = X; x < X + W; x++) {
				if (MassCreater.getMass(x, Y - 1).WALKABLE) {
					list.add(new Point(x, Y - 1));
				}
				if (MassCreater.getMass(x, Y + H).WALKABLE) {
					list.add(new Point(x, Y + H));
				}
			}
			for (int y = Y; y < Y + H; y++) {
				if (MassCreater.getMass(X - 1, y).WALKABLE) {
					list.add(new Point(X - 1, y));
				}
				if (MassCreater.getMass(X + W, y).WALKABLE) {
					list.add(new Point(X + W, y));
				}
			}
			return list;
		}

		public List<Point> getListEntrance_Huto() {
			ArrayList<Point> list = new ArrayList<Point>();
			for (int x = X - 1; x < X + W + 1; x++) {
				if (MassCreater.getMass(x, Y - 1).WALKABLE) {
					list.add(new Point(x, Y - 1));
				}
				if (MassCreater.getMass(x, Y + H).WALKABLE) {
					list.add(new Point(x, Y + H));
				}
			}
			for (int y = Y - 1; y < Y + H + 1; y++) {
				if (MassCreater.getMass(X - 1, y).WALKABLE) {
					list.add(new Point(X - 1, y));
				}
				if (MassCreater.getMass(X + W, y).WALKABLE) {
					list.add(new Point(X + W, y));
				}
			}
			return list;
		}

		public ArrayList<Point> getListNoCreatureMassPointInRoom() {
			ArrayList<Point> list = new ArrayList<Point>();
			for (int x = X; x < X + W; x++) {
				for (int y = Y; y < Y + H; y++) {
					if (MassCreater.getMass(x, y).ROOM
							&& MapList.getCreature(x, y) == null) {
						list.add(new Point(x, y));
					}
				}
			}
			return list;
		}

		public int getSize() {
			return W * H;
		}

		private int getWeight() {
			return (int) Math.round(Math.sqrt(getSize()));
		}

		private boolean isAbleToExtend() {
			boolean flag = true;
			for (int i = 0; i < W; i++) {
				if (!MassCreater.getMass(X + i, Y - 1).WALKABLE) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return true;
			}
			flag = true;
			for (int i = 0; i < W; i++) {
				if (!MassCreater.getMass(X + i, Y + H).WALKABLE) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return true;
			}
			flag = true;
			for (int i = 0; i < H; i++) {
				if (!MassCreater.getMass(X - 1, Y + i).WALKABLE) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return true;
			}
			flag = true;
			for (int i = 0; i < H; i++) {
				if (!MassCreater.getMass(X + W, Y + i).WALKABLE) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return true;
			}
			return false;
		}

		public boolean isDrawed() {
			return flag_drawed;
		}

		public boolean isEntrance(int x, int y) {
			boolean b = false;
			int signal = 0;
			if (x == X || x == X + W - 1) {
				signal = x == X ? -1 : 1;
				b = MassCreater.getMass(x + signal, y).WALKABLE;
			}
			if (y == Y || y == Y + H - 1) {
				signal = y == Y ? -1 : 1;
				b = b || MassCreater.getMass(x, y + signal).WALKABLE;
			}
			return b;
		}

		private boolean isLappedOver(Room r) {
			if (this.equals(r)) {
				return false;
			}
			if (X < r.X && r.X < X + W) {
				if (Y < r.Y && r.Y < Y + H) {
					return true;
				}
				if (Y < r.Y + r.H && r.Y + r.H < Y + H) {
					return true;
				}
			}
			if (X < r.X + r.W && r.X + r.W < X + W) {
				if (Y < r.Y && r.Y < Y + H) {
					return true;
				}
				if (Y < r.Y + r.H && r.Y + r.H < Y + H) {
					return true;
				}
			}
			if (X < r.X && r.X + r.W < X + W) {
				if (r.Y < Y && Y + H < r.Y + r.H) {
					return true;
				}
			}
			if (r.X < X && X + W < r.X + r.W) {
				if (Y < r.Y && r.Y + r.H < Y + H) {
					return true;
				}
			}
			return false;
		}

		public boolean isNewThePointInExtentdedThis(int x, int y) {
			return X <= x && x < X + W && Y <= y && y < Y + H;
		}

		public boolean isThePointInExtentdedThis(int x, int y) {
			return X - 1 <= x && x < X + W + 1 && Y - 1 <= y && y < Y + H + 1;
		}

		public boolean isThePointInExtentdedThis(Point p) {
			return isThePointInExtentdedThis(p.x, p.y);
		}

		public boolean isThePointInThis(int x, int y) {
			return X <= x && x < X + W && Y <= y && y < Y + H;
		}

		public boolean isThePointInThis(Point p) {
			return isThePointInThis(p.x, p.y);
		}

		public void setHoly(boolean b) {
			for (int x = X; x < X + W; x++) {
				for (int y = Y; y < Y + H; y++) {
					MassCreater.getMass(x, y).setHoly(b);
				}
			}
		}

		public ArrayList<Mass> setHuto(int lv) {
			ArrayList<Mass> list = new ArrayList<Mass>();
			if (lv == 4) {
				for (int i = X; i < X + W; i++)
					for (int j = Y; j < Y + H; j++) {
						Base_Artifact a = MapList.getArtiface(i, j);
						if (a != null && (a.isMerchant() || a.isPunishment())) {
							continue;
						}
						if (!MapList.isCreature(i, j))
							list.add(MassCreater.getMass(i, j));
					}
			} else {
				for (Point p : getListEntrance_Huto()) {
					Base_Artifact a = MapList.getArtifact(p);
					if (a != null && (a.isMerchant() || a.isPunishment())) {
						continue;
					}
					list.add(MassCreater.getMass(p));
				}
			}
			for (Iterator<Mass> iterator = list.iterator(); iterator.hasNext();) {
				iterator.next()
						.setWaterWithAnimation(true, !iterator.hasNext());
			}
			if (!list.isEmpty()) {
				Message.set("秘術によって閉じ込められた");
			}
			return list;
		}

		public void setShop(boolean b) {
			for (int x = X; x < X + W; x++) {
				for (int y = Y; y < Y + H; y++) {
					MassCreater.getMass(x, y).setShop(b);
				}
			}
		}

		private void trap(final Base_Artifact a) {
			if (a instanceof Base_Trap) {
				if (!a.isVisible()) {
					if (!Player.me.conditionCheck(CONDITION.目薬)
							&& !印罠師.effect()) {
						MainMap.addEffect(new DoronEffect(a.getMassPoint(),
								new Task() {
									/**
							 *
							 */
									private static final long serialVersionUID = 1L;

									@Override
									public void work() {
										a.setVisible(true);
									}

									@Override
									protected void work(int frame) {
										if (frame == 4) {
											a.setVisible(true);
										}
									}
								}, false, true), true);
					} else {
						a.setVisible(true);
					}
				}
			}
		}
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private boolean[][] unchecked_mass;
	private final int SUM;
	private List<Room> room_list = new ArrayList<Room>();

	InitialPlacement() {
		Mass[][] mass = MassCreater.getMass();
		unchecked_mass = new boolean[mass.length][mass[0].length];
		for (int x = 0; x < mass.length; x++)
			for (int y = 0; y < mass[x].length; y++)
				unchecked_mass[x][y] = true;
		for (int x = 0; x < mass.length; x++)
			for (int y = 0; y < mass[x].length; y++) {
				if (mass[x][y].ROOM && unchecked_mass[x][y])
					room_list.add(new Room(x, y));
			}
		int sum = 0;
		for (Room r : room_list)
			sum += r.getWeight();
		SUM = sum;

	}

	void extendRoom() {
		while (extendRoom1Mass())
			;
	}

	private boolean extendRoom1Mass() {
		List<Room> list = new ArrayList<Room>();
		for (Room r : room_list) {
			if (r.isAbleToExtend()) {
				list.add(r);
			}
		}
		if (list.size() > 0) {
			HashMap<Room, Room> map = new HashMap<Room, Room>(list.size());
			for (Room r : list) {
				map.put(r, new Room(r));
			}
			for (Room r : list) {
				room_list.remove(r);
				room_list.add(map.get(r));
			}
			for (Room r : room_list) {
				for (Room _r : room_list) {
					if (r.isLappedOver(_r)) {
					}
				}
			}
		}
		return list.size() > 0;
	}

	Point getCreaturePoint() {
		int i = 10000;
		Point p = null;
		while (i-- > 0) {
			p = getRoomPoint();
			if (!MassCreater.getMass(p).WALKABLE) {
				continue;
			}
			if (MassCreater.getMass(p).WATER) {
				continue;
			}
			if (MapList.getCreature(p) != null) {
				continue;
			}
			if (MassCreater.isPlayerInRoom()
					&& MassCreater.isPlayerInTheSameRoom(p)) {
				if (room_list.size() > 1) {
					continue;
				}
			}
			if (Math.abs(p.x - Player.me.getMassPoint().x) > MAP.TILE_HORIZON_INNER_NUMBER / 2
					|| Math.abs(p.y - Player.me.getMassPoint().y) > MAP.TILE_VERTICAL_INNER_NUMBER / 2) {
				return p;
			} else {
				if (room_list.size() == 1 && room_list.get(0).getSize() < 100) {
					return p;
				}
			}
		}
		Show.showErrorMessageDialog("while文から抜けられませんでした@InitialPlacement.getCreaturePoint()");
		return p;
	}

	Point getItemPoint() {
		int i = 10000;
		Point p = null;
		while (i > 0) {
			i--;
			p = getRoomPoint();
			if (!MassCreater.getMass(p).WALKABLE)
				continue;
			if (MassCreater.getMass(p).WATER)
				continue;
			if (MapList.isArtifact(p.x, p.y))
				continue;
			if (MassCreater.getMass(p).isShop())
				continue;
			return p;
		}
		Show.showErrorMessageDialog("while文から抜けられませんでした@InitialPlacement.getArtifactPoint() ");
		return p;
	}

	public ArrayList<Point> getListRoomPoint(Room r) {
		// System.out.println(Player.me.getMassPoint());
		ArrayList<Point> list = new ArrayList<Point>();
		if (r == null) return list;
		for (int x = 0; x <= r.W - 1; x++) {
			for (int y = 0; y <= r.H - 1; y++) {
				list.add(new Point(r.X + x, r.Y + y));
			}
		}
		return list;
	}

	Point getMonsterHouseArtifactPoint(Room r) {
		int i = 10000;
		Point p = null;
		while (i > 0) {
			i--;
			p = getRandomRoomPoint(r);
			if (MapList.getArtifact(p) == null
					&& MassCreater.getMass(p).WALKABLE) {
				return p;
			}
		}
		// Show.showErrorMessageDialog("while文から抜けられませんでした@InitialPlacement.getArtifactPoint() ");
		return new Point(Mass.nullpo.X, Mass.nullpo.Y);
	}

	Point getMonsterHouseTrapPoint(Room r) {
		int i = 1000;
		DIRECTION ds[] = { DIRECTION.UP, DIRECTION.DOWN, DIRECTION.LEFT,
				DIRECTION.RIGHT };
		Point p = null;
		label: while (i > 0) {
			i--;
			p = getRandomRoomPoint(r);
			for (DIRECTION d : ds) {
				if (MassCreater.getMass(p.x + d.X, p.y + d.Y).ROAD) {
					// 通路と隣接しているところを除外
					continue label;
				}
			}
			if (MapList.getArtifact(p) == null
					&& MassCreater.getMass(p).WALKABLE) {
				return p;
			}
		}
		// Show.showErrorMessageDialog("while文から抜けられませんでした@InitialPlacement.getArtifactPoint() ");
		return new Point(Mass.nullpo.X, Mass.nullpo.Y);
	}

	public Point getMonsterPoint(Room r) {
		ArrayList<Point> list = new ArrayList<Point>();
		for (Point p : getListRoomPoint(r)) {
			if (MassCreater.isWalk(p)) {
				list.add(p);
			}
		}
		if (list.isEmpty()) {
			return Mass.nullpo.null_point;
		}
		return list.get(R.ran(list.size()));
	}

	Point getMonsterPoint_MonsterHouse(Room r) {
		int i = 10000;
		Point p = null;
		while (i > 0) {
			i--;
			p = getRandomRoomPoint(r);
			if (MapList.getCreature(p) != null
					&& MassCreater.getMass(p).WALKABLE)
				continue;
			return p;
		}
		Show.showErrorMessageDialog("while文から抜けられませんでした@InitialPlacement.getMonsterPoint_MonsterHouse");
		return p;
	}

	/**
	 * 
	 * @return 部屋のサイズを加味せず、初期部屋を等確率で抽選
	 */
	Point getPlayerPoint() {
		int i = 10000;
		int x = 0, y = 0;
		Random ran = new R();
		while (i > 0) {
			i--;
			int rnd = ran.nextInt(room_list.size());
			Room r = room_list.get(rnd);
			x = ran.nextInt(r.W) + r.X;
			y = ran.nextInt(r.H) + r.Y;
			Mass m = MassCreater.getMass(x, y);
			if (MapList.getCreature(x, y) != null)
				continue;
			if (!m.WALKABLE)
				continue;
			if (m.WATER)
				continue;
			if (m.isShop())
				continue;
			if (m.isHoly())
				continue;
			if (m.isIsLand())
				continue;
			return new Point(x, y);
		}
		Show.showErrorMessageDialog("while文から抜けられませんでした\n主人公の初期位置が決められません\n考えられる原因：部屋が小さすぎた");
		return new Point(x, y);
	}

	Point getRandomRoomPoint(Room r) {
		int x = 0, y = 0;
		x = new R().nextInt(r.W) + r.X;
		y = new R().nextInt(r.H) + r.Y;
		return new Point(x, y);
	}

	Room getRoom() {
		int rnd = new R().nextInt(SUM);
		int sum = 0;
		for (Room r : room_list) {
			sum += r.getWeight();
			if (rnd < sum)
				return r;
		}
		Show.showErrorMessageDialog("ぬるぽ@InitialPoint.getRoom()");
		return null;
	}

	Room getRoom(int x, int y) {
		for (Room r : room_list) {
			if (r.X <= x && x < r.X + r.W && r.Y <= y && y < r.Y + r.H) {
				return r;
			}
		}
		return null;
	}

	Room getRoom(Point p) {
		return getRoom(p.x, p.y);
	}

	Room getRoomInEntrance(Point p) {
		// System.out.println("check");
		for (Room r : room_list) {
			if (r.X - 1 <= p.x && p.x < r.X + r.W + 1 && r.Y - 1 <= p.y
					&& p.y < r.Y + r.H + 1) {
				return r;
			}
		}
		// System.out.println("check-getNull");
		return null;
	}

	public List<Room> getRoomList() {
		return room_list;
	}

	private Point getRoomPoint() {
		int x = 0, y = 0;
		Room r = getRoom();
		x = new R().nextInt(r.W) + r.X;
		y = new R().nextInt(r.H) + r.Y;
		return new Point(x, y);
	}

	/**
	 * 通路と隣接しているところを除外
	 * 
	 * @return
	 */
	private Point getRoomPoint_WithoutEntrance() {
		return getRoomPoint_WithoutEntrance(null);
	}

	/**
	 * 通路と隣接しているところを除外
	 * 
	 * @return
	 */
	private Point getRoomPoint_WithoutEntrance(Room r) {
		boolean rnd = r == null;
		int i = 10000;
		int x = 0, y = 0;
		DIRECTION ds[] = { DIRECTION.UP, DIRECTION.DOWN, DIRECTION.LEFT,
				DIRECTION.RIGHT };
		label: while (i > 0) {
			i--;
			if (rnd)
				r = getRoom();
			x = new R().nextInt(r.W) + r.X;
			y = new R().nextInt(r.H) + r.Y;
			for (DIRECTION d : ds) {
				if (MassCreater.getMass(x + d.X, y + d.Y).ROAD) {
					// 通路と隣接しているところを除外
					continue label;
				}
			}
			return new Point(x, y);
		}
		Show.showErrorMessageDialog("while文から抜けられませんでした@InitialPlacement.getPlayerPoint");
		return new Point(x, y);
	}

	public Point getStairsPoint() {
		int i = 10000;
		while (i-- > 0) {
			Point p = MassCreater.getItemIP();
			Mass mass = MassCreater.getMass(p);
			if (!mass.isHoly() && !mass.isShop() && !mass.isIsLand()) {
				if (mass.WALKABLE && !mass.WATER_NOT_FROZEN && !mass.WATER
						&& !mass.WATER_LEEF) {
					return p;
				}
			}
		}
		Show.showErrorMessageDialog("while文から抜けられませんでした@InitialPlacement.getTrapPoint() ");
		return getItemPoint();
	}

	public Point getTrapPoint() {
		return getTrapPoint(null);
	}

	public Point getTrapPoint(Room r) {
		int i = 10000;
		Point p = null;
		while (i > 0) {
			i--;
			p = getRoomPoint_WithoutEntrance(r);
			if (!MassCreater.getMass(p).WALKABLE)
				continue;
			if (MassCreater.getMass(p).WATER)
				continue;
			Base_Artifact a = MapList.getArtifact(p);
			if (a != null) {
				continue;
			}
			return p;
		}
		Show.showErrorMessageDialog("while文から抜けられませんでした@InitialPlacement.getTrapPoint(r) ");
		return p;
	}

}
