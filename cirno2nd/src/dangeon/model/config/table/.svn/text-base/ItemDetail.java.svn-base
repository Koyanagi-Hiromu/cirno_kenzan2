package dangeon.model.config.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.disc.Disc;
import dangeon.model.object.artifact.item.food.Food;
import dangeon.model.object.artifact.item.grass.Base_Grass;
import dangeon.model.object.artifact.item.pot.Base_Pot;
import dangeon.model.object.artifact.item.ring.Ring;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.util.R;

public enum ItemDetail {
	CARD("spellcard", SpellCard.class), RING("ring", Ring.class), FOOD("food",
			Food.class), ARROW("arrow", Arrow.class), SCROOL("scrool",
			Scrool.class), GRASS("grass", Base_Grass.class), STAFF("staff",
			Staff.class), DISC("disc", Disc.class), POT("pot", Base_Pot.class);
	static enum RANK {
		S(2), A(5), B(30), C(60), D(100), N(0);
		int PARCENT;

		private RANK(int i) {
			PARCENT = i;
		}
	}

	static Class<?> getItem(boolean rare) {
		if (rare) {
			ArrayList<Class<?>> list = new ArrayList<Class<?>>();
			for (ItemDetail id : ItemDetail.values()) {
				id.setRareIntoList(list);
			}
			if (list.isEmpty()) {
				return null;
			} else {
				return list.get(new R().nextInt(list.size()));
			}
		} else if (category_parcent_sum > 0) {
			int value = 0;

			int category_select = new R().nextInt(category_parcent_sum) + 1;
			for (ItemDetail id : category_values) {
				value += id.category_parcent;
				if (value >= category_select) {
					return id.itemPerfectedllyRandom();
				}
			}
			return null;
		} else {
			return null;
		}
	}

	static Class<?> getItem(ItemDetail ID) {
		int value = 0;
		if (category_parcent_sum == 0)
			return null;
		int category_select = new R().nextInt(category_parcent_sum) + 1;
		for (ItemDetail id : category_values) {
			if (ID != null) {
				if (!ID.equals(id)) {
					continue;
				} else {
					return id.item();
				}
			} else {
				value += id.category_parcent;
			}
			if (value >= category_select) {
				return id.item();
			}
		}
		return null;
	}

	private static void setCategoryParcentValue() {
		category_parcent_sum = 0;
		for (ItemDetail id : category_values) {
			category_parcent_sum += id.category_parcent;
		}
	}

	private static void setCategoryValues() {
		for (ItemDetail id : values()) {
			if (!id.category_list.isEmpty()) {
				category_values.add(id);
			}
		}
	}

	static void setList(String category, String rank, Class<?> c) {
		for (ItemDetail id : values()) {
			if (category.matches(id.category)) {
				id.set(rank, c);
				return;
			}
		}
	}

	public static void setPercent(String category, int percent) {
		for (ItemDetail id : values()) {
			if (id.category.matches(category)) {
				id.category_parcent = percent;
				return;
			}
		}
	}

	static void start() {
		for (ItemDetail id : values()) {
			id.setParcentValue();
		}
		setCategoryValues();
		setCategoryParcentValue();

	}

	public int parcent_value;
	private Class<? extends Base_Artifact> category_class;

	private String category;

	int category_parcent;

	private static int category_parcent_sum;

	List<Class<?>> category_list = new ArrayList<Class<?>>();

	HashMap<Class<?>, RANK> rank_map = new HashMap<Class<?>, RANK>();

	public HashMap<RANK, List<Class<?>>> map = new HashMap<RANK, List<Class<?>>>();

	private static ArrayList<ItemDetail> category_values = new ArrayList<ItemDetail>();

	/**
	 * @param a
	 * @return 入手出来ないアイテムはnullを返す。例：オンバシラ
	 */
	public static ItemDetail getCategory(Base_Artifact a) {
		for (ItemDetail id : values()) {
			if (id.category_class.isInstance(a)) {
				return id;
			}
		}
		return null;
	}

	static RANK getRank(Base_Artifact a) {
		for (ItemDetail id : ItemDetail.values()) {
			for (Class<?> c : id.category_list) {
				if (c.isInstance(a)) {
					return id.rank_map.get(c);
				}
			}
		}
		return RANK.N;
	}

	public static void resetDate() {
		for (ItemDetail id : values()) {
			id.parcent_value = 0;
			id.category_list.clear();
			id.rank_map.clear();
			id.map.clear();
		}
		category_parcent_sum = 0;
		category_values.clear();
	}

	ItemDetail(String str, Class<? extends Base_Artifact> c) {
		category = str;
		category_class = c;
	}

	private Class<?> item() {
		int c_value = 0;
		int select = new R().nextInt(parcent_value) + 1;
		for (Class<?> c : category_list) {
			c_value += rank_map.get(c).PARCENT;
			if (c_value >= select) {
				return c;
			}
		}
		return null;
	}

	private Class<?> itemPerfectedllyRandom() {
		ArrayList<Class<?>> category_list = new ArrayList<Class<?>>();
		for (Class<?> class1 : this.category_list) {
			if (rank_map.get(class1).PARCENT > 0) {
				category_list.add(class1);
			}
		}
		if (category_list.isEmpty())
			return null;
		else
			return category_list.get(new R().nextInt(category_list.size()));
	}

	private void set(String str, Class<?> c) {
		category_list.add(c);
		if (str.matches("S")) {
			rank_map.put(c, RANK.S);
		} else if (str.matches("A")) {
			rank_map.put(c, RANK.A);
		} else if (str.matches("B")) {
			rank_map.put(c, RANK.B);
		} else if (str.matches("C")) {
			rank_map.put(c, RANK.C);
		} else if (str.matches("D")) {
			rank_map.put(c, RANK.D);
		} else {
			rank_map.put(c, RANK.N);
		}
	}

	private void setParcentValue() {
		parcent_value = 0;
		for (Class<?> c : category_list) {
			parcent_value += rank_map.get(c).PARCENT;
		}
	}

	private void setRareIntoList(ArrayList<Class<?>> list) {
		for (Class<?> c : category_list) {
			switch (rank_map.get(c)) {
			case A:
				list.add(c);
				list.add(c);
				list.add(c);
			case S:
				list.add(c);
				list.add(c);
			default:
				break;
			}
		}
	}

}
