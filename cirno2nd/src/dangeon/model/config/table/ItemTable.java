package dangeon.model.config.table;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;

import connection.sv_cl.SocketHolder;
import dangeon.model.config.Config;
import dangeon.model.config.table.ItemDetail.RANK;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.food.Food;
import dangeon.model.object.artifact.item.food.奇跡のおにぎり;
import dangeon.model.object.artifact.item.food.腐ったおにぎり;
import dangeon.model.object.artifact.item.scrool.切れ端;
import dangeon.model.object.creature.npc.second.Takarabako;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.util.Flag;
import dangeon.util.R;
import main.util.FileReadSupporter;
import main.util.Show;

public class ItemTable {
	private static HashMap<Class<?>, Integer> map = new HashMap<Class<?>, Integer>();

	public static Class<?> createClazz(String package_package_classname) {
		try {
			return Class.forName("dangeon.model.object.artifact."
					.concat(package_package_classname));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Base_Artifact createObject(Point p, Class<?> c) {
		Constructor<?> con;
		try {
			if (c == null) {
				return new 腐ったおにぎり(p);
			}
			if (Food.class.isAssignableFrom(c) && BonusConductor.守矢神()) {
				return new 奇跡のおにぎり(p);
			} else if (Arrow.class.isAssignableFrom(c)) {
				con = c.getConstructor(Point.class, boolean.class);
				Object obj = con.newInstance(p, true);
				if (obj instanceof Base_Artifact) {
					return (Base_Artifact) obj;
				}
			}
			con = c.getConstructor(Point.class);
			Object obj = con.newInstance(p);
			if (obj instanceof Base_Artifact) {
				return (Base_Artifact) obj;
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void createTresureBox() {
		MapList.addEnemy(new Takarabako(MassCreater.getTrapIP()));
	}

	/**
	 * チェック用
	 */
	private static void detailParcent() {
		int c1 = 0;
		double count = 0;
		for (ItemDetail id : ItemDetail.values()) {
			c1 += id.parcent_value;
		}
		for (ItemDetail id : ItemDetail.values()) {
			for (Class<?> c : id.category_list) {
				double d = ((double) id.rank_map.get(c).PARCENT / (double) id.parcent_value)
						* ((double) id.category_parcent / (double) 100) * 100;
				count += d;
				BigDecimal b = new BigDecimal(d);
			}
			count = 0;
		}
	}

	/**
	 * チェック用
	 */
	private static void getMap() {
		int count = 0;
		int c1 = 0;
		for (ItemDetail id : ItemDetail.values()) {
			for (Class<?> c : id.category_list) {
				if (map.get(c) != null) {
					count += map.get(c);
				}
			}
			c1 += count;
			count = 0;
		}
		detailParcent();
	}

	public static int getMerchantValue(Base_Item base_Item) {
		switch (ItemDetail.getRank(base_Item)) {
		case S:
			return 60;
		case A:
			return 25;
		case B:
			return 4;
		case C:
			return 2;
		case D:
			return 1;
		case N:
		default:
			return 10;
		}
	}

	public static int getRank(Base_Artifact base_Item) {
		switch (ItemDetail.getRank(base_Item)) {
		case S:
			return 5;
		case A:
			return 4;
		case B:
			return 3;
		case C:
			return 2;
		case D:
			return 1;
		case N:
		default:
			return 1;
		}
	}

	public static int getRankForSort(Base_Artifact base_Item) {
		switch (ItemDetail.getRank(base_Item)) {
		case S:
			return 5;
		case A:
			return 4;
		case B:
			return 3;
		case C:
			return 2;
		case D:
			return 1;
		case N:
		default:
			return 6;
		}
	}

	public static String getRank_String(Base_Artifact a) {
		RANK r = ItemDetail.getRank(a);
		switch (r) {
		case S:
			return "Ｓ";
		case A:
			return "Ａ";
		case B:
			return "Ｂ";
		case C:
			return "Ｃ";
		case D:
			return "Ｄ";
		case N:
		default:
			return "＊";
		}
	}

	public static Base_Artifact itemReturn(Point p,
			boolean only_rare___or___perfectedlly_random) {
		return createObject(p,
				ItemDetail.getItem(only_rare___or___perfectedlly_random));
	}

	public static Base_Artifact itemReturn(Point p, ItemDetail id) {
		return selectArtifact(p, id);
	}

	/**
	 * 何も指定されない場合はテストフィールドのテーブルを参照する
	 */
	private static void itemTableCreate() {
		InputStreamReader read;
		try {
			read = FileReadSupporter.readUTF8("res/table/テストフィールド"
					.concat(".csv"));
			// read = FileReadSupporter.readUTF8("res/itemTable/".concat(
			// PresentField.get().getName()).concat(".txt"));
			itemTableCreate(new BufferedReader(read));
		} catch (FileNotFoundException e) {
			Show.showCriticalErrorMessageDialog("ItemTableが見つかりません@FileReader-ItemTable");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void itemTableCreate(BufferedReader bf) {
		String str = "";
		try {
			int percent = 0;
			String category = null;
			boolean roop = true;
			while ((str = bf.readLine()) != null) {
				str = str.trim();
				if (str.startsWith("TRAP")) {
					break;
				}
				if (roop) {
					if (str.startsWith("ITEM")) {
						roop = false;
					}
					continue;
				} else {
					if (str.isEmpty()) {
						break;
					}
				}
				;
				if (str.startsWith("category")) {
					String[] s = str.split("\\.");
					category = s[1].split("\t")[0];
					String weight = str.split("\t")[1];
					if (weight.isEmpty()) {
						percent = 0;
					} else {
						percent = Integer.valueOf(weight);
					}
					ItemDetail.setPercent(category, percent);
					continue;
				}
				String[] detail = str.split("\t");
				String item_name = detail[0];
				String rank = detail[1];
				Class<?> c = returnClass(item_name, category);
				ItemDetail.setList(category, rank, c);
			}
			bf.close();
			ItemDetail.start();
		} catch (IOException e) {
			Show.showErrorMessageDialog(e);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("arrayIndex[" + str + "]");
		}
	}

	private static void itemTableCreate(String dun_name) {
		InputStreamReader read;
		try {
			read = FileReadSupporter.readUTF8("res/table/".concat(dun_name)
					.concat(".csv"));
			// read = FileReadSupporter.readUTF8("res/itemTable/".concat(
			// PresentField.get().getName()).concat(".txt"));
			itemTableCreate(new BufferedReader(read));
		} catch (FileNotFoundException e) {
			Show.showCriticalErrorMessageDialog("ItemTableが見つかりません@FileReader-ItemTable");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static StringBuffer randomTableCreate() {
		InputStreamReader read;
		StringBuffer sb = new StringBuffer();
		try {
			read = FileReadSupporter.readUTF8("res/table/table.csv");
			sb = randomTableCreate(new BufferedReader(read));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return sb;
	}

	private static StringBuffer randomTableCreate(BufferedReader br) {
		String str;
		boolean _flag = false;
		StringBuffer sb = new StringBuffer("ITEM\n");
		try {
			while ((str = br.readLine()) != null) {
				str = str.trim();
				if (str.isEmpty()) {
					continue;
				}
				if (str.startsWith("ENEMY") || str.startsWith("TRAP")) {
					_flag = false;
					continue;
				}
				if (str.startsWith("ITEM")) {
					_flag = true;
					continue;
				}
				if (!_flag) {
					continue;
				}
				if (str.startsWith("category")) {
					sb.append(str);
					sb.append("\n");
					continue;
				}
				String[] cut = str.split("\t");
				sb.append(cut[0].trim());
				sb.append("\t");
				sb.append(ItemDetail.RANK.values()[R.ran(ItemDetail.RANK
						.values().length)]);
				sb.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}

	public static Base_Artifact returnBaseArtifactSetPoint(
			String package_package_classname, Point p) {
		try {
			Class<?> clazz = Class.forName("dangeon.model.object.artifact."
					.concat(package_package_classname));
			return createObject(p, clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Class<?> returnClass(String str, String category) {
		category = category.trim();
		try {
			Class<?> clazz = Class
					.forName("dangeon.model.object.artifact.item.".concat(
							category.concat(".")).concat(str));
			return clazz;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Base_Artifact selectArtifact(Point p, ItemDetail id) {
		if (id == null && BonusConductor.フラワーマスター()) {
			if (new R().is(25)) {
				id = ItemDetail.GRASS;
			}
		}
		if (p == null) {
			p = MassCreater.getItemIP();
		}
		Base_Artifact a = createObject(p, ItemDetail.getItem(id));
		if (a != null)
			return a.defaultCurse();
		else {
			if (id == null) {
				new Exception("ItemTableが存在しません@ItemTable.selectArtifact")
						.printStackTrace();
				// Show.showErrorMessageDialog("ここではアイテムが生成できません");
				return new 腐ったおにぎり(p);
			} else {
				Show.showErrorMessageDialog(id + "は存在しません（代わりに腐ったおにぎりを生成します）");
			}
			return new 腐ったおにぎり(p);
		}
	}

	public static boolean setItemInMonsterHouse(Point p) {
		if (p.x == Mass.nullpo.X) {
			return false;
		}
		MapList.addArtifact(selectArtifact(p, null));
		return true;
	}

	private static void setMap(Class<?> a) {
		int i = 0;
		while (true) {
			i++;
			if (map.get(a) == null) {
				map.put(a, i);
				break;
			} else {
				if (map.get(a) >= i) {
					continue;
				} else {
					map.put(a, i);
					break;
				}
			}
		}
	}

	public static void setOnMapInNormalFirst() {
		int try_numbers = Flag.isFlagItemPlus() ? 2 : 1;
		for (int count = 0; count < try_numbers; count++) {
			int ran = PresentField.get().defaultItemNumber();
			if (SocketHolder.isConnected()) {
				Base_Map_Random random_map = PresentField.get().getRandomMap();
				if (random_map != null) {
					ran -= random_map.dangerLv(MapList.getFloor())
							- (Config.getFate() - 1);
				}
			}
			if (ran > 0) {
				int hosei = 3 - MapList.getFloor();
				if (hosei > 0) {
					ran += hosei;
				}
			}
			for (int i = 0; i < ran; i++) {
				Base_Artifact a = selectArtifact(null, null);
				MapList.addArtifact(a);
			}
			// page期待値が1.65
			// 各階層2にしたい
			// →出現期待度:1.2ちょっと
			// Ex = 1*0.7 + 2*0.7*x + 3*0.7*x*x +・・・
			// = 0.7Σk*x^(k-1)
			//
			double parcent = 70;
			double device = 0.30;
			while (new R().is((int) parcent)) {
				切れ端 a = new 切れ端(MassCreater.getItemIP());
				MapList.addArtifact(a);
				parcent *= device;
			}
		}
		int r = (PresentField.get().getShopParcent() + PresentField.get()
				.getSaisenParcent()) * 2 / 3;
		if (new R().is(r)) {
			ItemTable.createTresureBox();
		}
	}

	private static void tableReset() {
		ItemDetail.resetDate();
	}

	/**
	 * チェック用
	 */
	public static void test() {
		for (int i = 0; i < 1000; i++) {
			setMap(ItemDetail.getItem(null));
		}
		getMap();
	}

	public static void testCreate() {
		tableReset();
		itemTableCreate();
	}

	public static void testCreate(BufferedReader table) {
		tableReset();
		itemTableCreate(table);
	}

	public static void testCreate(String str) {
		tableReset();
		itemTableCreate(str);
	}

}
