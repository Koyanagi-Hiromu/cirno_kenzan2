package dangeon.model.config.table;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import main.res.CHARA_IMAGE;
import main.util.DIRECTION;
import main.util.FileReadSupporter;
import main.util.Show;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.random.テストフィールド;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.わかさぎ姫;
import dangeon.model.object.creature.enemy.人形;
import dangeon.model.object.creature.enemy.四季映姫ヤマザナドゥ;
import dangeon.model.object.creature.enemy.小野塚小町;
import dangeon.model.object.creature.npc.Abstract_NPC;
import dangeon.model.object.creature.npc.慧音NPC;
import dangeon.model.object.creature.npc.敵確認NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.util.Switch;

public class EnemyTable {

	public static List<EnemyDetail> list_enemy_table = new ArrayList<EnemyDetail>();
	private static final int make_enemy_turn = 40;
	private static int make_enemy_turn_count = 0;
	private static final int sheef_enemy_turn = 10;

	public static ArrayList<CHARA_IMAGE> current_use_creature = new ArrayList<CHARA_IMAGE>();

	private static int enemy_max_parcent = 5;

	public static boolean creater_flag = false;

	/**
	 * Map生成時
	 * 
	 * @param value
	 *            エネミーを出現させる数
	 */
	public static void enemyTable(int value) {
		setOnMap(value == 0 ? new R().nextInt(7) + 6 : value, true);
	}

	/**
	 * 特殊Map生成時
	 * 
	 * @param value
	 *            エネミーを出現させる数
	 */
	public static void enemyTable(int value, boolean kamin) {
		Base_Enemy e = setOnMap(value == 0 ? new R().nextInt(7) + 6 : value,
				kamin);
		if (e.conditionCheck(CONDITION.仮眠)) {
			e.setCondition(CONDITION.特殊仮眠, 0);
		}
	}

	private static void enemyTableCreate(BufferedReader bf) {
		list_enemy_table.clear();
		// テスト用
		if (PresentField.get().getMapName().matches("テストフィールド")) {
			Base_Creature c = 敵確認NPC.getC();
			list_enemy_table.add(new EnemyDetail(c.getLV(), 100, c.getClass()
					.getSimpleName()));
		} else {
			try {
				String str;
				int Y = 0;
				while ((str = bf.readLine()) != null) {
					if (str.startsWith("ITEM")) {
						break;
					}
					if (str.startsWith("ENEMY")) {
						continue;
					}
					str = str.trim();
					if (str.isEmpty()) {
						break;
					}
					Y++;
					if (Y != MapList.getFloor()) {
						continue;
					}
					String s = str.substring(str.indexOf("\t") + 1,
							str.length());
					String[] strAry = s.split("\t");
					for (int i = 0; i < strAry.length / 3; i++) {
						int j = i * 3;
						String enemy_name = strAry[j];
						if (enemy_name.isEmpty())
							continue;
						int level = Integer.parseInt(strAry[j + 1]);
						int parcent = Integer.parseInt(strAry[j + 2]);
						list_enemy_table.add(new EnemyDetail(level, parcent,
								enemy_name));
					}
				}
			} catch (FileNotFoundException e) {
				Show.showCriticalErrorMessageDialog("EnemyTableが見つかりません@FileReader-EnemyTable");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * テーブルクリエイター用
	 */
	private static void enemyTableCreater(int y, String names) {
		list_enemy_table.clear();
		InputStreamReader read;
		try {
			read = FileReadSupporter.readUTF8("res/enemyTable/".concat(names)
					.concat(".txt"));
			BufferedReader bf = new BufferedReader(read);
			String str;
			int Y = 0;
			while ((str = bf.readLine()) != null) {
				Y++;
				if (Y != y)
					continue;
				String[] strAry = str.split("\t");
				for (int i = 0; i < strAry.length; i++) {
					String[] detail = strAry[i].split(",");
					String enemy_name = detail[0];
					int level = Integer.parseInt(detail[1]);
					int parcent = Integer.parseInt(detail[2]);
					list_enemy_table.add(new EnemyDetail(level, parcent,
							enemy_name));
				}
			}
			bf.close();
		} catch (FileNotFoundException e) {
			Show.showCriticalErrorMessageDialog("EnemyTableが見つかりません@FileReader-EnemyTable");
		} catch (Exception e) {
			// System.out.println(e);
		}
	}

	public static List<Base_Enemy> getListEnemyTable(int y, String names) {
		enemyTableCreater(y, names);
		List<Base_Enemy> list = new ArrayList<Base_Enemy>();
		for (EnemyDetail e : list_enemy_table) {
			if (ねだやしチェック(e)) {
				continue;
			}
			list.add(returnBaseEnemy(e.CLASS, e.LV));
		}
		return list;
	}

	public static Base_Enemy getRandomEnemy(int level) {
		return parcent(level);
	}

	private static int isFlagSheef() {
		return MapList.getFlagSheef() ? sheef_enemy_turn : make_enemy_turn;
	}

	/**
	 * 
	 * @param list_a
	 * @param b
	 *            仮眠状態
	 */
	private static void kamin(Base_Enemy e) {
		if (new R().is(e.isKamin())) {
			e.setConditionList(CONDITION.仮眠, 0);
		}
	}

	public static void makeEnemyTurn() {
		if (!PresentField.get().isForcedToMakeEnemy()) {
			if (!MassCreater.isMovedWithStep()) {
				return;
			}
			if (!PresentField.get().isHaraheru()) {
				return;
			}
			if (PresentField.get() instanceof テストフィールド && Config.isTest()) {
				return;
			}
			if (!PresentField.get().isRandomField()) {
				return;
			}
		}

		make_enemy_turn_count++;
		if (make_enemy_turn_count >= isFlagSheef()) {
			make_enemy_turn_count = 0;
			if (MapList.getFlagSheef()) {
				MapList.addEnemy(sheefEnemy(false));
			} else {
				setOnMap(1, !PresentField.get().isForcedToMakeEnemy());
			}
		}
	}

	public static List<Base_Enemy> nue() {
		List<Base_Enemy> list = new ArrayList<Base_Enemy>();
		for (EnemyDetail e : list_enemy_table) {
			if (e.CLASS.matches("封獣ぬえ")) {
				continue;
			}
			list.add(returnBaseEnemy(e.CLASS, e.LV));
		}
		return list;
	}

	private static Base_Enemy parcent(int level) {
		return parcent(level, null);
	}

	private static Base_Enemy parcent(int level, Point p) {
		int parcent_value = 0;
		int parcent = parcentageValue();
		if (parcent <= 0) {
			return null;
		}
		parcent = new R().nextInt(parcent) + 1;
		EnemyDetail _e = null;
		for (EnemyDetail e : list_enemy_table) {
			parcent_value += e.PARCENT;
			if (parcent_value >= parcent) {
				if (ねだやしチェック(e)) {
					if (_e == null) {
						continue;
					} else {
						e = _e;
					}
				}
				if (level == 0) {
					System.out.println(e.CLASS);
					return returnBaseEnemy(e.CLASS, e.LV, p);
				} else {
					return returnBaseEnemy(e.CLASS, level, p);
				}
			}
			_e = e;
		}
		// エネミーテーブルに何も居なかった場合。
		return new 人形(MassCreater.getCreatureIp(), 1);
	}

	private static int parcentageValue() {
		int denominator = 0;
		for (EnemyDetail e : list_enemy_table) {
			denominator += e.PARCENT;
		}
		return denominator;
	}

	public static StringBuffer randomTableCreate(int max_level) {
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		for (CHARA_IMAGE img : CHARA_IMAGE.values()) {
			if (img.name().matches("arrow") || img.name().matches("船")
					|| img.name().matches("キスメ")) {
				continue;
			}
			current_use_creature.add(img);
		}
		StringBuffer sb = new StringBuffer("ENEMY\n");
		for (int i = 1; i <= max_level; i++) {
			list.clear();
			sb.append(i);
			sb.append("F");
			int max_enemy_size = R.ran(8);
			for (int j = 0; j <= max_enemy_size; j++) {
				String name = current_use_creature.get(
						R.ran(current_use_creature.size())).name();
				int level = R.ran(3) + 1;
				Base_Creature _c = returnBaseEnemySetPoint(name, level,
						new Point(0, 0));
				if (!list.isEmpty() && list.contains(_c)) {
					continue;
				}
				list.add(_c);
				sb.append("\t");
				sb.append(name);
				sb.append(",");
				sb.append(level);
				sb.append(",");
				sb.append(R.ran(enemy_max_parcent) + 1);
			}
			sb.append("\n");
		}
		return sb;
	}

	public static void resetEnemyTurn() {
		make_enemy_turn_count = 0;
	}

	public static Base_Enemy returnBaseEnemy(int level, Point p, Class<?> clazz) {
		return returnBaseEnemy("", level, p, clazz);
	}

	public static Base_Enemy returnBaseEnemy(String str, int level) {
		return returnBaseEnemy(str, level, null);
	}

	public static Base_Enemy returnBaseEnemy(String str, int level, Point p) {
		try {
			return returnBaseEnemy(str, level, p,
					Class.forName("dangeon.model.object.creature.enemy."
							.concat(str)));
		} catch (ClassNotFoundException e) {
		}
		if (p == null)
			p = MassCreater.getCreatureIp();
		return new Abstract_NPC(CHARA_IMAGE.ゆっくり霊夢, "バグだよ", p.x, p.y, false,
				"エネミーエラーだよ$【" + str + "】");
	}

	private static Base_Enemy returnBaseEnemy(String str, int level, Point p,
			Class<?> clazz) {
		try {
			Constructor<?> con = clazz.getConstructor(Point.class, int.class);
			Object obj;
			if (p == null) {
				p = !creater_flag ? MassCreater.getCreatureIp() : new Point(0,
						0);
				if (str.matches("わかさぎ姫")) {
					if (!Switch.create) {
						while (!MassCreater.getMass(p).WATER) {
							if (MapList.isTrapOrWaterPermitted(p)) {
								MassCreater.getMass(p).forceToWater(true);
								MassCreater.retakeMassSet();
								break;
							}
							p = MassCreater.getCreatureIp();
						}
					}
				}
			} else {
				if (str.matches("わかさぎ姫")) {
					Base_Artifact a = MapList.getArtifact(p);
					if (a != null) {
						MapList.removeArtifact(a);
					}
					if (MapList.isTrapOrWaterPermitted(p)) {
						MassCreater.getMass(p).forceToWater(true);
						MassCreater.retakeMassSet();
						if (a != null && a instanceof Base_Item) {
							MapList.addArtifact(a);
						}
					} else {
						if (a != null) {
							MapList.addArtifact(a);
						}
						return null;
					}
				}

			}
			obj = con.newInstance(p, level);
			if (obj instanceof Base_Enemy) {
				Base_Enemy em = (Base_Enemy) obj;
				if (em instanceof わかさぎ姫) {
					((わかさぎ姫) em).resetFlagInWater(true);
				}
				em.setDirection(DIRECTION.DOWN);
				return em;
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
		if (p == null)
			p = MassCreater.getCreatureIp();
		return new Abstract_NPC(CHARA_IMAGE.ゆっくり霊夢, "バグだよ", p.x, p.y, false,
				"エネミーエラーだよ$【" + str + "】");
	}

	public static Base_Enemy returnBaseEnemySetPoint(String str, int level,
			Point p) {
		Constructor<?> con;
		try {
			Class<?> clazz = Class
					.forName("dangeon.model.object.creature.enemy.".concat(str));
			con = clazz.getConstructor(Point.class, int.class);
			Object obj;
			obj = con.newInstance(p, level);
			if (obj instanceof Base_Enemy) {
				Base_Enemy em = (Base_Enemy) obj;
				em.setDirection(DIRECTION.DOWN);
				return em;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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

	public static void setFlagSheefFirstEnemy() {
		if (MapList.isEnemyMax()) {
			Medal.泥棒した時エネミー数が上限に達していた.addCount();
			return;
		}
		Base_Enemy e = sheefEnemy(true).setFlagWarning();
		MapList.addEnemy(e);
		for (int i = 0; i <= 15; i++) {
			MapList.addEnemy(sheefEnemy(false));
		}
	}

	public static Base_Enemy setMonsterHouse(Point p, SpecialMonsterHouse smh) {
		if (MapList.getCreature(p) != null) {
			return null;
		}
		if (!MassCreater.getMass(p).WALKABLE) {
			return null;
		}
		Base_Enemy e = null;
		if (smh == null || smh == SpecialMonsterHouse.ちびでかハウス) {
			e = parcent(0, p);
			if (e == null)
				return null;
			e.enemy_actioned = false;
			e.setConditionList(CONDITION.特殊仮眠, 0);
			MapList.addEnemy(e);
			if (smh == SpecialMonsterHouse.ちびでかハウス) {
				if (new R().nextBoolean())
					e.changeSize(1);
				if (new R().nextBoolean())
					e.changeSize(2);
				else
					e.changeSize(-1);
			}
		} else {
			String[] names = smh.getNames();
			e = returnBaseEnemy(names[new R().nextInt(names.length)],
					PresentField.get().getSMH_EnemyLV(), p);
			if (e == null)
				return null;
			e.setLV_NoMessage(e.getDeConvertedLV(e.getLV()));
			e.enemy_actioned = false;
			e.setConditionList(CONDITION.特殊仮眠, 0);
			MapList.addEnemy(e);
		}
		return e;
	}

	/**
	 * エネミー生成
	 * 
	 * @param value
	 *            生成するエネミーの数
	 * @return
	 */
	private static Base_Enemy setOnMap(int value, boolean kamin) {
		Base_Enemy e = null;
		for (int i = 0; i < value; i++) {
			e = parcent(0);
			if (e == null)
				return null;
			if (kamin) {
				kamin(e);
			}
			MapList.addEnemy(e);
		}
		return e;
	}

	/**
	 * エネミーを発生させる
	 * 
	 * @param point
	 * @param level
	 * @return
	 */
	public static boolean setOnMapPointAndLevel(Point point, int level,
			boolean actioned) {
		if (MapList.getCreature(point) != null) {
			return false;
		}
		if (!MassCreater.getMass(point).WALKABLE) {
			return true;
		}
		Base_Enemy e = parcent(level);
		e.setMassPoint(point);
		e.enemy_actioned = actioned;
		MapList.addEnemy(e);
		return true;
	}

	public static boolean setOnMapPointAndLevelAndCondition(Point point,
			int level, boolean actioned, CONDITION con, int time) {
		if (MapList.getCreature(point) != null) {
			return false;
		}
		if (!MassCreater.getMass(point).WALKABLE) {
			return true;
		}
		Base_Enemy e = parcent(level);
		e.setMassPoint(point);
		e.enemy_actioned = actioned;
		e.setConditionList(con, time);
		MapList.addEnemy(e);
		return true;
	}

	public static boolean setOnMapPointAndLevelIntoString(Point point,
			int level, boolean actioned, String str) {
		if (MapList.getCreature(point) != null) {
			return false;
		}
		if (!MassCreater.getMass(point).WALKABLE) {
			return true;
		}
		Base_Enemy e = returnBaseEnemySetPoint(str, level, point);
		e.enemy_actioned = actioned;
		// e.setConditionList(con, time);
		MapList.addEnemy(e);
		return true;
	}

	private static Base_Enemy sheefEnemy(boolean flag) {
		int koma_lv, eiki_lv, floor = MapList.getFloor();
		if (floor < 20) {
			koma_lv = 1;
			eiki_lv = 1;
		} else if (floor < 44) {
			koma_lv = 2;
			eiki_lv = 1;
		} else if (floor < 80) {
			koma_lv = 3;
			eiki_lv = 2;
		} else {
			koma_lv = 4;
			eiki_lv = 2;
		}
		if (flag) {
			eiki_lv++;
			if (flag && new R().is(10)) {
				eiki_lv++;
			}
		}
		if (PresentField.get().is2GEKI()) {
			eiki_lv++;
		}
		if (eiki_lv > 4)
			eiki_lv = 4;
		if (flag || new R().nextInt(2) == 1) {
			四季映姫ヤマザナドゥ e = new 四季映姫ヤマザナドゥ(MassCreater.getCreatureIp(), eiki_lv);
			if (flag)
				e.setFlagWarning();
			return e;
		} else {
			return new 小野塚小町(MassCreater.getCreatureIp(), koma_lv);
		}
	}

	public static void tableCreate(BufferedReader bufferedReader) {
		enemyTableCreate(bufferedReader);
	}

	public static void testest() {
		MapList.addEnemy(new 慧音NPC(MassCreater.getCreatureIp()));
	}

	// public static void write(String str) {
	// File file = new File("test.txt");
	// try {
	// FileWriter fw = new FileWriter(file, true);
	// fw.write(str);
	// fw.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	public static void ねだやし(Base_Creature c) {
		Player.me.Nedayashi = c;
	}

	public static void ねだやしRelease() {
		Player.me.Nedayashi = new 人形(new Point(0, 0), 1);
	}

	public static boolean ねだやしチェック(Base_Creature c) {
		if (Player.me.Nedayashi.getClass().getSimpleName()
				.matches(c.getClass().getSimpleName())) {
			return true;
		}
		return false;
	}

	public static boolean ねだやしチェック(EnemyDetail ed) {
		if (Player.me.Nedayashi.getClass().getSimpleName().matches(ed.CLASS)) {
			return true;
		}
		return false;
	}

}
