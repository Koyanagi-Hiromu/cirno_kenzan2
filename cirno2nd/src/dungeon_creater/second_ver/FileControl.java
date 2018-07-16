package dungeon_creater.second_ver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.util.Show;
import dangeon.model.config.table.EnemyDetail;
import dangeon.model.object.artifact.ArtifactData;
import dungeon_creater.second_ver.item_table.ItemTableGraphics;
import dungeon_creater.second_ver.item_table.SubItem;

public class FileControl {
	static File FILE;
	static FileFilter csv_filter = new FileNameExtensionFilter("CSVファイル", "csv");
	static JFileChooser csv_chooser = addFilter(new JFileChooser("./"));;
	static JFileChooser chooser = new JFileChooser("./");;

	public static HashMap<Integer, ArrayList<SubBaseCreature>> map = new HashMap<Integer, ArrayList<SubBaseCreature>>();

	public static SubItem item_table = new SubItem();

	static JFileChooser addFilter(JFileChooser _chooser) {
		_chooser.addChoosableFileFilter(csv_filter);
		return _chooser;
	}

	public static String getFileName() {
		if (FILE == null) {
			readALLTable(new File("./res/table/チルノの旅.csv"));
		}
		return FILE.getName();
	}

	private static StringBuffer getStringWriteEnemyTable(BufferedReader bf) {
		int Y = 0;
		String str;
		StringBuffer sb = new StringBuffer();
		String category = "";
		boolean flag_enemy = false;
		boolean flag_item = false;
		int count = 0;
		try {
			while ((str = bf.readLine()) != null) {
				str = str.trim();
				if (str.startsWith("ITEM")) {
					flag_enemy = false;
					flag_item = true;
					sb.append(str.trim());
					sb.append("\n");
					continue;
				} else if (str.startsWith("TRAP")) {
					flag_item = false;
					sb.append(str.trim());
					sb.append("\n");
					continue;
				} else if (str.startsWith("ENEMY")) {
					flag_enemy = true;
					sb.append(str.trim());
					sb.append("\n");
					continue;
				}
				if (flag_item) {
					if (str.startsWith("category")) {
						String[] _s = str.split("\\.");
						category = _s[1];
						sb.append(str.trim());
						sb.append("\n");
					} else {
						if (category.isEmpty()) {
							continue;
						}
						for (ArtifactData ad : ArtifactData.values()) {
							if (!category.matches(ad.getCategory())) {
								continue;
							}
							for (String _str : ad.items) {
								if (!ItemTableGraphics.ME.isOn(_str)) {
									continue;
								}
								sb.append(_str);
								sb.append("\t");
								sb.append(ItemTableGraphics.ME.getRank(_str));
								sb.append("\n");
							}
						}
						category = "";
					}
					continue;
				}
				if (!flag_enemy) {
					sb.append(str.trim());
					sb.append("\n");
				} else {
					count++;
					if (count > DungeonLevelControl.getDungeonMaxLevel()) {
						continue;
					}
					sb.append(count);
					sb.append("F");
					for (SubBaseCreature sbc : map.get(count)) {
						sb.append("\t");
						sb.append(sbc.C.getClass().getSimpleName());
						sb.append(",");
						sb.append(sbc.C.getLV());
						sb.append(",");
						sb.append(sbc.parcent);
					}
					sb.append("\n");
				}
			}
			bf.close();
		} catch (FileNotFoundException e) {
			Show.showCriticalErrorMessageDialog("EnemyTableが見つかりません@FileReader-EnemyTable");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}

	private static void itemTable(String str) {
		if (str.startsWith("category")) {
			return;
		}
		String[] item_sp = str.split("\t");
		item_table.add(new SubItem(item_sp[0], item_sp[1]));
	}

	public static void openAllFiles() {
		// int select = chooser.showOpenDialog(TableMainFrame.MAIN.main_panel);
		// if (select == JFileChooser.APPROVE_OPTION) {
		// FILE = chooser.getSelectedFile();
		// System.out.println(FILE.getName());
		// ReadingImage.testRead();
		// }
	}

	public static void openFiles() {
		int select = csv_chooser.showOpenDialog(TableMainFrame.MAIN.main_panel);
		if (select == JFileChooser.APPROVE_OPTION) {
			readALLTable(csv_chooser.getSelectedFile());
			ItemTableGraphics.ME.setDungeon();
		}
	}

	private static void readALLTable(File file) {
		FILE = file;
		try {
			readTable(new BufferedReader(new FileReader(file)));
			SetObject.setEnemyListDungeonLevel();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void readTable(BufferedReader bf) {
		map.clear();
		item_table.clear();
		boolean item = false;
		int Y = 0;
		try {
			String str;
			while ((str = bf.readLine()) != null) {
				ArrayList<SubBaseCreature> list = new ArrayList<SubBaseCreature>();
				if (str.startsWith("ITEM")) {
					item = true;
					continue;
				}
				if (str.startsWith("TRAP")) {
					break;
				}
				if (str.startsWith("ENEMY")) {
					continue;
				}
				str = str.trim();
				if (str.isEmpty()) {
					break;
				}
				if (item) {
					itemTable(str);
					continue;
				}
				list.clear();
				Y++;
				String[] floor = str.split("F\t");
				String[] strAry = floor[1].split("\t");
				for (int i = 0; i < strAry.length; i++) {
					String[] detail = strAry[i].split(",");
					String enemy_name = detail[0];
					int level = Integer.parseInt(detail[1]);
					int parcent = Integer.parseInt(detail[2]);
					list.add(new SubBaseCreature(new EnemyDetail(level,
							parcent, enemy_name).getBaseCreature(), parcent));
				}
				map.put(Y, list);
			}
			DungeonLevelControl.setDungeonMaxLevel(Y);
			bf.close();
		} catch (FileNotFoundException e) {
			Show.showCriticalErrorMessageDialog("EnemyTableが見つかりません@FileReader-EnemyTable");
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public static void saveFiles() {
		int select = csv_chooser.showSaveDialog(TableMainFrame.MAIN.main_panel);
		if (select == JFileChooser.APPROVE_OPTION) {
			FILE = csv_chooser.getSelectedFile();
			switch (TableScene.CURRENT) {
			case ENEMY_TABLE:
				writeEnemyTable();
				break;
			case ITEM_TABLE:
				writeItemTable();
				break;
			}
		}
	}

	public static void setMap(int level, ArrayList<SubBaseCreature> _list) {
		map.remove(level);
		map.put(level, _list);
	}

	public static void writeEnemyTable() {
		try {
			StringBuffer sb = getStringWriteEnemyTable(new BufferedReader(
					new FileReader(FILE)));
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					FILE)));
			pw.print(sb.toString());
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeItemTable() {
		try {
			StringBuffer sb = getStringWriteEnemyTable(new BufferedReader(
					new FileReader(FILE)));
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					FILE)));
			pw.print(sb.toString());
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
