package dangeon.model.object.creature.player.shop;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import main.util.DIRECTION;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.HiddenDevice;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.food.Food;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.artifact.item.food.奇跡のおにぎり;
import dangeon.model.object.creature.enemy.__店主;
import dangeon.model.object.creature.enemy.朱鷺子;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.util.R;
import dangeon.view.detail.MiniMap;

public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;
	private __店主 tenshu;
	private ArrayList<Base_Item> item_list;
	private final Room ROOM;

	public Shop(Room r, Point p) {
		ROOM = r;
		int lv;
		int floor = MapList.getFloor();
		if (floor < 20) {
			lv = 1;
		} else if (floor < 44) {
			lv = 2;
		} else {
			lv = 3;
		}
		tenshu = new 朱鷺子(p, lv, this);
		for (Point entrance : r.getListEntrance()) {
			for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
				Point q = d.getFrontPoint(entrance.getLocation());
				if (MassCreater.getRoom(q) == r) {
					MapList.addArtifact(new HiddenDevice(q));
				}
			}
		}
		item_list = new ArrayList<Base_Item>();
		Point center = new Point(r.X + r.W / 2, r.Y + r.H / 2);
		int number = new R().nextInt(6) + 3;
		int index = 1;
		for (DIRECTION d : DIRECTION.values()) {
			Point q = d.getFrontPoint(center.getLocation());
			if (MapList.getArtifact(q) != null)
				continue;
			Base_Artifact a = ItemTable.itemReturn(q, false);
			if (a == null)
				a = new 大きなおにぎり(q);
			if (BonusConductor.守矢神() && a instanceof Food) {
				a = new 奇跡のおにぎり(q);
			}
			Base_Item item;
			if (a instanceof Base_Item) {
				item = ((Base_Item) a);
				item.setMerchant(true);
			} else {
				continue;
			}
			MapList.addArtifact(item);
			item_list.add(item);
			if (++index == number) {
				break;
			}
		}
	}

	public ArrayList<Base_Item> getItemList() {
		return item_list;
	}

	public __店主 getTenshu() {
		return tenshu;
	}

	public boolean preGoOut() {
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			if (Player.me.isMovable(d)) {
				if (!MassCreater
						.getMass(
								d.getFrontPoint(Player.me.getMassPoint()
										.getLocation())).isShop()) {
					tenshu.preGoOut();
					return true;
				}
			}
		}
		return false;
	}

	public void release() {
		for (Base_Artifact a : item_list) {
			a.setMerchant(false);
		}
		ROOM.setHoly(false);
		MiniMap.reset();
	}

	public void setTenshu(__店主 tenshu) {
		this.tenshu = tenshu;
	}
}
