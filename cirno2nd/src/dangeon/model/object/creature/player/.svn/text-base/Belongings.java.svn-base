package dangeon.model.object.creature.player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import main.res.SE;
import main.util.Show;
import main.util.半角全角コンバーター;
import test.FirstBelongings;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.Base_Map;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Base_Device;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.disc.Base_Disc;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印邪;
import dangeon.model.object.artifact.item.food.Food;
import dangeon.model.object.artifact.item.grass.Base_Grass;
import dangeon.model.object.artifact.item.pot.Base_Pot;
import dangeon.model.object.artifact.item.ring.Ring;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.artifact.trap.Base_Trap;

/**
 * 所持アイテム管理クラス
 */
public class Belongings {
	public static final int MAX = 30;
	public static final int POCKET_MAX = 0;
	private static ArrayList<Base_Artifact> items = new ArrayList<Base_Artifact>(
			getMax() + POCKET_MAX);

	/**
	 * 最初に所持しているアイテム
	 */
	static {
		FirstBelongings.done(items);
	}

	// TODO ターンが経過するか アイテムを使用するでclear
	// TODO もとに戻す
	private static ArrayList<Base_Artifact> list_deposited = new ArrayList<Base_Artifact>();

	public static void allRemove() {
		items.clear();
	}

	public static void breaking(Base_Artifact... as) {
		list_deposited.clear();
		boolean flag_curse_play = false;
		int count = 0;
		for (int i = 0; i < as.length; i++) {
			Base_Artifact a = as[i];
			if (!a.isMobile()) {
				Message.set(a.getColoredName(), "は固定されていて破棄できなかった");
			} else if (!a.isEnchantedAndCursed()) {
				count++;
				list_deposited.add(a);
				remove(a);
			} else {
				flag_curse_play = true;
				Message.set(a.getColoredName(), "は呪われていて外せなかった");
			}
		}
		if (count > 0) {
			Message.set(半角全角コンバーター.半角To全角数字(count), "コのアイテムを捨てた");
		} else if (flag_curse_play) {
			SE.SYSTEM_CURSE.play();
		}
	}

	/**
	 * 
	 * @param index
	 *            -1を与えると足元のアーティファクトを渡す。
	 * @return 足元に何もない場合はnull。deviceもnull
	 */
	public synchronized static Base_Artifact get(int index) {
		if (index == -1) {
			for (Base_Artifact a : MapList.getListArtifact()) {
				if (a.isPlayerOn())
					if (!a.isHidden() && !(a instanceof Base_Device)) {
						return a;
					} else {
						return null;
					}
			}
			return null;
		}
		return items.get(index);
	}

	public static Arrow getArrow(Class<? extends Base_Artifact> clazz) {
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a.getClass().equals(clazz) && !a.isMerchant()) {
				return (Arrow) a;
			}
		}
		return null;
	}

	public static ArrayList<Arrow> getArrows(
			Class<? extends Base_Artifact> clazz) {
		ArrayList<Arrow> list = new ArrayList<Arrow>();
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a.getClass().equals(clazz)) {
				list.add((Arrow) a);
			}
		}
		return list;
	}

	public static ArrayList<Base_Artifact> getDeepCopy(
			List<Base_Artifact> items2) {
		ArrayList<Base_Artifact> l = new ArrayList<Base_Artifact>();
		for (Base_Artifact object : items2) {
			l.add(object);
		}
		return l;
	}

	/**
	 * 
	 * @return 足元にあるartifact。こちらはdeviceも含む
	 */
	public synchronized static Base_Artifact getFoot() {
		for (Base_Artifact a : MapList.getListArtifact()) {
			if (a.isPlayerOn())
				return a;
		}
		return null;
	}

	public static int getItemIndex(Base_Artifact a) {
		return items.indexOf(a);
	}

	public static ArrayList<Base_Artifact> getListFreezeItems() {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : items) {
			if (a.isFrozen()) {
				list.add(a);
			}
		}
		return list;
	}

	public static ArrayList<Base_Artifact> getListItemNoEnchant() {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : items) {
			if (!Enchant.isEnchanted(a)) {
				list.add(a);
			}
		}
		return list;
	}

	public static ArrayList<Base_Artifact> getListItems() {
		return items;
	}

	public static List<Base_Artifact> getListItems_except(Base_Artifact... as) {
		List<Base_Artifact> list = getDeepCopy(items);
		for (Base_Artifact a : as) {
			list.remove(a);
		}
		Base_Artifact a = get(-1);
		if (a != null) {
			list.add(a);
		}
		for (Iterator<Base_Artifact> iterator = list.iterator(); iterator
				.hasNext();) {
			Base_Artifact _a = iterator.next();
			if (!(_a instanceof Base_Item)) {
				iterator.remove();
			}
		}
		return list;
	}

	public static ArrayList<Base_Artifact> getListItems_exceptPocket() {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>(getMax());
		for (int i = 0; i < items.size(); i++) {
			if (i == getMax())
				break;
			list.add(items.get(i));
		}
		return list;
	}

	public static ArrayList<Base_Artifact> getListItems_includingFoot() {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : getListItems())
			list.add(a);
		Base_Artifact a = getFoot();
		if (a != null && !(a instanceof Base_Device))
			list.add(a);
		return list;
	}

	public static int getMax() {
		Base_Map bm = PresentField.get();
		if (bm == null) {
			return MAX;
		} else {
			return bm.getBelongingsMax();
		}
	}

	public static ArrayList<Base_Artifact> getPocket() {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>(POCKET_MAX);
		for (int i = getMax(); i < getMax() + POCKET_MAX && i < items.size(); i++)
			list.add(items.get(i));
		return list;
	}

	public synchronized static int getSize() {
		return items.size();
	}

	public static boolean isEmpty() {
		return items.isEmpty();
	}

	public static boolean isMax() {
		return items.size() >= getMax() + POCKET_MAX;
	}

	public static boolean isOver30() {
		return items.size() > getMax();
	}

	public static boolean isOverMax() {
		return items.size() > getMax() + POCKET_MAX;
	}

	public static boolean isSearchItem(String nam) {
		for (Base_Artifact a : items) {
			if (a.getClass().getSimpleName().matches(nam)) {
				return true;
			}
		}
		return false;
	}

	public synchronized static boolean pickUpItem(Base_Artifact artifact) {
		if (items.size() < getMax() + POCKET_MAX) {
			artifact.doWhenPickuped();
			if (items.size() < getMax()) {
				Message.set(artifact.getColoredName().concat("を拾った"));
			} else {
				Message.set(artifact.getColoredName().concat(
						"を" + Color.ORANGE + "ポケット" + Color.WHITE + "にしまった"));
			}
			TaskOnMapObject.addTrapRemoveTask(artifact);
			SE.SYSTEM_PICKUP.play();
			items.add(artifact);
			印邪.setInfomation();
			return true;
		} else {
			Message.set(artifact.getColoredName(), "は持ち物が一杯で拾えなかった", "");
			return false;
		}
	}

	/**
	 * 交換の時に呼ばれる
	 * 
	 * @param artifact
	 * @param i
	 * @return 拾ったか否か
	 */
	public synchronized static boolean pickUpItem(Base_Artifact artifact, int i) {
		Boolean is_not_to_add_list = artifact.exchange();
		if (is_not_to_add_list != null && !is_not_to_add_list) {
			artifact.doWhenPickuped();
			items.add(i, artifact);
		}
		return is_not_to_add_list == null || !is_not_to_add_list;
	}

	public synchronized static void remove(Base_Artifact a) {
		if (items.contains(a)) {
			items.remove(a);
			if (a.isEnchantedNow())
				Enchant.forceToRemove(a);
		} else {
			if (MapList.getListArtifact().contains(a)) {
				MapList.removeArtifact(a);
			} else {
				// 射撃系　敵が投げたり　弓矢を放ったとき
			}
		}
	}

	public synchronized static void remove(int i) {
		items.remove(i);
	}

	public static void removePockets() {
		int size = items.size();
		for (int i = 0; i < size - getMax(); i++) {
			items.remove(getMax());
		}
	}

	public static void replace(Base_Artifact a1, Base_Artifact a2) {
		int i = getItemIndex(a1);
		int j = getItemIndex(a2);
		items.set(i, a2);
		items.set(j, a1);
		Message.set(a1.getColoredName(), "と", a2.getColoredName(), "の位置を入れ替えた");

	}

	public synchronized static void setItems(Base_Artifact a) {
		items.add(a);
	}

	public static void setItems(Base_Artifact a, int index) {
		try {
			items.add(index, a);
		} catch (Exception e) {
			items.add(a);
		}
	}

	public static void setListItems(List<Base_Artifact> list) {
		items = new ArrayList<Base_Artifact>(list);
	}

	public static ArrayList<Base_Artifact> sort() {
		return items = sort(items, 30);
	}

	public static ArrayList<Base_Artifact> sort(List<Base_Artifact> items) {
		ArrayList<Base_Artifact> sort = new ArrayList<Base_Artifact>(
				items.size());
		Enchant[] es = Enchant.values();
		for (Enchant e : es) {
			for (Base_Artifact a : items) {
				if (e.isEquals(a)) {
					sort.add(a);
				}
			}
			sortReduceSortedItems(sort, items);
		}
		for (Base_Artifact a : items) {
			if (EnchantArrow.isEnchant(a)) {
				sort.add(a);
				break;
			}
		}
		Class<?> cs[] = { Base_Device.class, SpellCard.class, Arrow.class,
				Ring.class, Food.class, Base_Grass.class, Scrool.class,
				Staff.class, Base_Pot.class, Base_Disc.class,
				Base_Artifact.class, Base_Trap.class };
		for (Class<?> c : cs) {
			for (int i = 0; i < 3; i++) {
				for (Base_Artifact a : items) {
					if (sort.contains(a)) {
						continue;
					}
					if (c.isInstance(a)) {
						if (i == 0 && !a.isPerfectCheked()) {
							continue;
						} else if (i == 1 && !a.isStaticCheked()) {
							continue;
						}
						ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
						for (Base_Artifact _a : items) {
							if (sort.contains(_a)) {
								continue;
							}
							if (a.getClass().equals(_a.getClass())) {
								list.add(_a);
							}
						}
						Collections.sort(list);
						for (Base_Artifact _a : list) {
							sort.add(_a);
						}
					}
				}
				sortReduceSortedItems(sort, items);
			}
		}
		if (items.size() != 0) {
			System.out.println("listに残ってしまったアイテムの数：" + items.size());
			System.out.println("listに登録されている名前：" + items.size());
			for (Base_Artifact a : items) {
				System.out.println(a);
			}
			Show.showErrorMessageDialog("Sortがうまくいってないです");
		}
		return sort;
	}

	public static ArrayList<Base_Artifact> sort(List<Base_Artifact> items,
			int MAX) {
		ArrayList<Base_Artifact> overs;
		if (Belongings.getSize() > MAX) {
			int size = items.size() - MAX;
			overs = new ArrayList<Base_Artifact>(size);
			for (int i = 0; i < size; i++) {
				overs.add(items.get(MAX));
				items.remove(MAX);
			}
			overs = sort(overs);
		} else {
			overs = null;
		}
		ArrayList<Base_Artifact> sort = sort(items);
		if (overs != null) {
			for (Base_Artifact a : overs) {
				sort.add(a);
			}
		}
		return sort;
	}

	private static void sortReduceSortedItems(List<Base_Artifact> sort,
			List<Base_Artifact> items) {
		for (Base_Artifact a : sort) {
			items.remove(a);
		}
	}
}
