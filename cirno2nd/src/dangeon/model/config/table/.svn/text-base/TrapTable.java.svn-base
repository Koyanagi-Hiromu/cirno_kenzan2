package dangeon.model.config.table;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.util.DIRECTION;
import main.util.FileReadSupporter;
import main.util.Show;
import dangeon.model.map.InitialPlacement.Room;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.util.R;

public class TrapTable {
	private static HashMap<Class<?>, Integer> trap_map = new HashMap<Class<?>, Integer>();
	private static List<Class<?>> list_trap_table = new ArrayList<Class<?>>();
	private static int total_value = 0;

	/**
	 * @param value
	 *            配置する数
	 * @param a
	 *            指定するトラップ nullならばランダム
	 */
	public static void addMapList(int value, Base_Artifact a) {
		if (BonusConductor.冒険家()) {
			if (a == null) {
				dangerousStair();
				value *= 2;
			}
			for (int i = 0; i < value; i++) {
				if (a == null) {
					trapSelect();
				} else {
					MapList.setTrapOnMap(a.getMassPoint(), a);
				}
			}
		} else {
			if (a == null) {
				for (int i = 0; i < value; i++) {
					setWarningPoint(MassCreater.getTrapIP());
				}
				dangerousStair();
			} else {
				for (int i = 0; i < value; i++) {
					MapList.setTrapOnMap(a.getMassPoint(), a);
				}
			}
		}

	}

	public static void createMonsterHouse(Room r, int number) {
		if (BonusConductor.冒険家()) {
			for (int i = 0; i < number * 2; i++) {
				trapSelect();
			}
		} else {
			for (int i = 0; i < number; i++) {
				setWarningPoint(MassCreater.getTrapIP(r));
			}
		}
	}

	private static Base_Artifact createObject(Point p, Class<?> c) {
		Constructor<?> con;
		try {
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

	public static void createTrapTable(BufferedReader bf) {
		trap_map.clear();
		list_trap_table.clear();
		total_value = 0;
		try {
			String str;
			boolean roop = true;
			while ((str = bf.readLine()) != null) {
				if (roop) {
					if (str.startsWith("TRAP")) {
						roop = false;
					}
					continue;
				}
				String[] detail = str.split("\t");
				String trap_name = detail[0];
				Integer parcent = Integer.valueOf(detail[1]);
				Class<?> c = returnClass(trap_name);
				total_value += parcent;
				trap_map.put(c, parcent);
				list_trap_table.add(c);
			}
			bf.close();
		} catch (FileNotFoundException e) {
			Show.showCriticalErrorMessageDialog("ItemTableが見つかりません@FileReader-ItemTable");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// try {
		// String str;
		// boolean roop = true;
		// while ((str = bf.readLine()) != null) {
		// String[] detail = str.split(",");
		// String trap_name = detail[0];
		// Integer parcent = Integer.valueOf(detail[1]);
		// Class<?> c = returnClass(trap_name);
		// total_value += parcent;
		// trap_map.put(c, parcent);
		// list_trap_table.add(c);
		// }
		// bf.close();
		// } catch (FileNotFoundException e) {
		// Show.showCriticalErrorMessageDialog("ItemTableが見つかりません@FileReader-ItemTable");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	private static void dangerousStair() {
		if (PresentField.get().isDangerous()) {
			ArrayList<Stairs> array = new ArrayList<Stairs>(5);
			for (Base_Artifact stair : MapList.getListArtifact()) {
				if (stair instanceof Stairs) {
					array.add((Stairs) stair);
				}
			}
			for (Base_Artifact stair : array) {
				Point p = stair.getMassPoint();
				setWarningPoint(p);
			}
		}
	}

	private static boolean isTrapUnableToExist_Apparently(Point p) {
		Mass m = MassCreater.getMass(p);
		if (m.equals(Mass.nullpo) || !m.WALKABLE) {
			return true;
		}
		if (m.WATER || m.WATER_LEEF) {
			return true;
		}
		Base_Artifact a = MapList.getArtifact(p);
		if (a != null && a.isVisible()) {
			return true;
		}
		return false;
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
		StringBuffer sb = new StringBuffer("TRAP\n");
		try {
			while ((str = br.readLine()) != null) {
				str = str.trim();
				if (str.isEmpty()) {
					continue;
				}
				if (str.startsWith("ENEMY") || str.startsWith("ITEM")) {
					_flag = false;
					continue;
				}
				if (str.startsWith("TRAP")) {
					_flag = true;
					continue;
				}
				if (!_flag) {
					continue;
				}
				String[] cut = str.split("\t");
				sb.append(cut[0].trim());
				sb.append("\t");
				sb.append(R.ran(100) + 1);
				sb.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}

	private static Class<?> returnClass(String str) {
		try {
			Class<?> clazz = Class
					.forName("dangeon.model.object.artifact.trap.".concat(str));
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

	public static void setOnMap() {
		addMapList(PresentField.get().getTrapDefaultValue(), null);
	}

	public static void setOnMap(Point p) {
		trapSelect(p);
	}

	private static void setWarningPoint(Point p) {
		boolean small = new R().is(80);
		final int parcent = small ? 40 : 20;
		boolean flag_less_one = true;
		if (small) {
			for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
				Point target = d.getFrontPoint(p.getLocation());
				Mass m = MassCreater.getMass(target);
				if (m.ROOM) {
					if (isTrapUnableToExist_Apparently(target)) {
						continue;
					}
					flag_less_one = false;
					m.setWarningSmall(false);
					if (new R().is(parcent)) {
						trapSelect(target);
						m.setWarningSmall(false);
					}
				}
			}
		} else {
			for (DIRECTION d : DIRECTION.values_onlyBasic4()) {
				Point target = p.getLocation();
				while (true) {
					Mass m = MassCreater.getMass(d.getFrontPoint(target));
					if (m.ROOM) {
						if (isTrapUnableToExist_Apparently(target)) {
							continue;
						}
						flag_less_one = false;
						m.setWarningLarge(false);
						if (new R().is(parcent)) {
							trapSelect(target);
							m.setWarningLarge(false);
						}
					} else {
						break;
					}
				}
			}
		}
		if (!flag_less_one) {
			if (small) {
				MassCreater.getMass(p).setWarningSmall(true);
			} else {
				MassCreater.getMass(p).setWarningLarge(true);
			}
		}
	}

	private static void trapSelect() {
		int i = 0;
		if (total_value == 0) {
			return;
		}
		int select = new R().nextInt(total_value) + 1;
		for (Class<?> clazz : list_trap_table) {
			i += trap_map.get(clazz);
			if (select <= i) {
				Point p = MassCreater.getTrapIP();
				if (MapList.isTrapOrWaterPermitted(p))
					MapList.addArtifact(createObject(p, clazz));
				break;
			}
		}
	}

	public static boolean trapSelect(Point p) {
		if (isTrapUnableToExist_Apparently(p)) {
			return false;
		}
		int i = 0;
		int select = new R().nextInt(total_value) + 1;
		for (Class<?> clazz : list_trap_table) {
			i += trap_map.get(clazz);
			if (select <= i) {
				if (MapList.isTrapOrWaterPermitted(p))
					MapList.addArtifact(createObject(p, clazz));
				return true;
			}
		}
		return true;
	}

}
