package dangeon.model.map;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import main.util.DIRECTION;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.config.table.ItemTable;
import dangeon.model.config.table.SpecialMonsterHouse;
import dangeon.model.config.table.TrapTable;
import dangeon.model.config.table.黄金の賽銭箱テーブル;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.WarpDevice;
import dangeon.model.object.creature.npc.守矢賽銭箱;
import dangeon.model.object.creature.npc.賽銭箱;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.model.object.creature.player.shop.Shop;
import dangeon.util.R;

public class SpecialRoom {
	private static int getMonsterHouseParcent() {
		if (PresentField.get().SPECIAL_NUMBER < 0) {
			// 基本値6%
			return PresentField.get().getMonsterHouse();
		}
		if (PresentField.get().getMonsterHouse() == 0)
			return 0;
		switch (PresentField.get().SPECIAL_NUMBER) {
		case 0:
		case 1:
			return 100;
		default:
			return 50;
		}
	}

	private static Room getRoom(int min_size) {
		int i = 100;
		List<Room> list = MassCreater.getIP().getRoomList();
		if (list.size() < 3) {
			if (list.size() == 1) {
				return null;
			}
			for (Room r : list) {
				if (MassCreater.getMass(r.X + r.W / 2, r.Y + r.H / 2).isShop()) {
					return null;
				}
			}
		}
		Room r = null;
		while (i > 0) {
			i--;
			r = MassCreater.getIP().getRoom();
			if (r.flag_water_room || r.flag_monster_house) {
				continue;
			}
			if (!MassCreater.getMass(r.X + r.W / 2, r.Y + r.H / 2).WALKABLE) {
				continue;
			}
			if (MassCreater.getMass(r.X + r.W / 2, r.Y + r.H / 2).isShop()) {
				continue;
			}
			if (r.W < min_size || r.H < min_size) {
				continue;
			}
			if (r.W % 2 == 1 && r.H % 2 == 1) {
				break;
			}
		}
		if (i == 0) {
			return null;
		} else {
			return r;
		}
	}

	private static void GoldShop() {
		if (黄金の賽銭箱テーブル.isExist()) {
			int i = 1000;
			Random r = new R();
			while (i > 0) {
				i--;
				int x = r.nextInt(MassCreater.WIDTH), y = r
						.nextInt(MassCreater.HEIGHT);
				Point p = new Point(x, y);
				Mass m = MassCreater.getMass(p);
				if (!m.WALKABLE && !m.ROOM && m.DIGGABLE) {
					for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
						Mass m2 = MassCreater.getMass(d.getFrontPoint(p
								.getLocation()));
						if (m2.WALKABLE) {
							m.setObjectInWall(new 守矢賽銭箱(p, 黄金の賽銭箱テーブル
									.getItemList(p)));
							return;
						}
					}
				}
			}
		}
	}

	private static void island() {
		int UKIJIMA_KAKURITUS = 5;
		if (BonusConductor.風水師_浮島４倍()) {
			UKIJIMA_KAKURITUS *= 4;
		}
		for (Room r : MassCreater.getIP().getRoomList()) {
			if (r.W < 6 || r.H < 6)
				continue;
			Random rnd = new R();
			if (rnd.nextInt(100) < UKIJIMA_KAKURITUS) {
				System.out.println("checks");
				int w = r.W - 4;
				if (w > 4)
					w = w % 2 == 0 ? 4 : 3;
				int h = r.H - 4;
				if (h > 4)
					h = h % 2 == 0 ? 4 : 3;
				w = (r.W - w - 2) / 2;
				h = (r.H - h - 2) / 2;
				int left = r.X + w;
				int right = r.X + r.W - w;
				int top = r.Y + h;
				int bottom = r.Y + r.H - h;
				boolean f = false;
				int rate = 150;
				for (int x = left; x < right; x++) {
					for (int y = top; y < bottom; y++) {
						Mass m = MassCreater.getMass(x, y);
						m.setIsLand(true);
						if (x == left || x == right - 1 || y == top
								|| y == bottom - 1) {
							m.setNonFrozenWater();
						} else {
							Base_Artifact default_artifact = MapList
									.getArtiface(x, y);
							if (f) {
								if (default_artifact == null) {
									if (new R().nextInt(100) < rate) {
										rate = rate * 50 / 100;
										ItemTable
												.setItemInMonsterHouse(new Point(
														x, y));
									}
								}
							} else {
								f = !f;
								if (default_artifact != null) {
									MapList.removeArtifact(default_artifact);
								}
								MapList.addArtifact(new WarpDevice(new Point(x,
										y)));
								r.flag_water_room = true;
							}
						}
					}
				}
				break;
			}
		}
	}

	private static void MonsterHouse() {
		if (!MonsterHouseCheck()) {
			return;
		}
		Player.me.setFlag_next_monster_house(false);
		Room r = MassCreater.getIP().getRoom();
		if (r.flag_water_room || r.getSize() < 10)
			return;
		setMonsterHouseThisRoom(r);
	}

	private static boolean MonsterHouseCheck() {
		return Player.me.isFlag_next_monster_house()
				|| getMonsterHouseParcent() >= new R().nextInt(100) + 1;
	}

	public static void Saisen(List<Base_Artifact> list) {
		if (!new R().is(PresentField.get().getSaisenParcent())) {
			return;
		}
		Player.me.saisen = null;
		Room r = getRoom(3);
		if (r == null) {
			return;
		} else {
			r.setHoly(true);
			if (list != null) {
				Player.me.saisen = new 賽銭箱(r, list);
			} else {
				Player.me.saisen = new 賽銭箱(r);
			}
			TrapTable.setOnMap(Player.me.saisen.getMassPoint());
			MapList.addEnemy(Player.me.saisen);
		}
	}

	public static void set() {
		island();
		waterHazard();
		MonsterHouse();
		Shop();
		Saisen(null);
		GoldShop();
		MassCreater.retakeMassSet();
	}

	public static void setMonsterHouseThisRoom(Room r) {
		int number = new R().nextInt(5) + 13;
		if (number > r.getSize() * 6 / 10) {
			number = r.getSize() * 6 / 10;
		}
		if (new R().is(PresentField.get().getSMH_Percent())) {
			// PresentField.get().SMH = SpecialMonsterHouse.爆炎地帯;
			PresentField.get().SMH = SpecialMonsterHouse.values()[new R()
					.nextInt(SpecialMonsterHouse.getAllNumber())];
		} else {
			PresentField.get().SMH = null;
		}
		// boolean flag_secret = R.ran(100) < 10;
		for (int i = 0; i < number; i++) {
			Point _P = MassCreater.getIP().getMonsterPoint(r);
			// 失敗
			// if (flag_secret && r.getListEntrance().contains(_P))
			// continue;
			EnemyTable.setMonsterHouse(_P, PresentField.get().SMH);
		}
		if (PresentField.get().isItemDrop()) {
			number = new R().nextInt(4) + 5;
			for (int i = 0; i <= number; i++) {
				if (!ItemTable.setItemInMonsterHouse(MassCreater.getIP()
						.getMonsterHouseArtifactPoint(r))) {
					break;
				}
			}
		}
		number = new R().nextInt(r.getSize() / 4) / 4 + 1 + r.getSize() / 10;
		int size = MassCreater.getIP().getRoomList().size();
		if (size == 1) {
			if (number > 10)
				number = 10;
		} else {
			if (number > 5)
				number = 5;
		}
		TrapTable.createMonsterHouse(r, number);
		r.flag_monster_house = true;
	}

	private static void Shop() {
		if (!new R().is(PresentField.get().getShopParcent())) {
			return;
		}
		Player.me.shop = null;
		Room r = getRoom(3);
		if (r == null || r.getEntrance() == null) {
			return;
		} else {
			Point p = null, entrance = r.getEntrance();
			for (DIRECTION d : DIRECTION.values_onlyNANAME4()) {
				Point p2 = d.getFrontPoint(entrance.getLocation());
				if (MassCreater.getMass(p2).ROOM) {
					p = p2;
					break;
				}
			}
			if (p == null)
				return;
			r.setShop(true);
			Player.me.shop = new Shop(r, p);
			MapList.addEnemy(Player.me.shop.getTenshu());
		}
	}

	private static void waterHazard() {
		int KAKURITUS = 5;
		if (BonusConductor.風水師_水場３倍()) {
			KAKURITUS *= 3;
		}
		for (Room r : MassCreater.getIP().getRoomList()) {
			if (r.flag_water_room)
				continue;
			Random rnd = new R();
			int rd = rnd.nextInt(100);
			if (rd > KAKURITUS)
				continue;
			waterHazard(r, (rd < KAKURITUS / 2) ? r.getSize()
					* (rnd.nextInt(6) + 1) / 10 : rnd.nextInt(4) + 1);
		}
	}

	public static void waterHazard(Room r, Integer try_number) {
		if (try_number == null)
			try_number = r.getSize() * 4 / 10;
		for (int j = 0; j < try_number; j++) {
			Point p = MassCreater.getTrapIP(r);
			if (MapList.isTrapOrWaterPermitted(p))
				MassCreater.getMass(p).setWater(true);
		}
		r.flag_water_room = true;
	}
}
