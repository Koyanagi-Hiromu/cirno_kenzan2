package dungeon_creater;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.util.FileReadSupporter;

public class TableWriter {

	public static void textExport(StringBuffer plus) {
		List<JTextField> list = new ArrayList<JTextField>();
		JComboBox combo = null;
		boolean conti = false;
		for (JPanel p : TableStarter.ME.enemy_list) {

			list.clear();
			for (Component com : TableStarter.ME.panel_map.get(p)) {
				if (com instanceof JComboBox) {
					combo = (JComboBox) com;
					if (combo.getSelectedIndex() <= 0) {
						conti = true;
						break;
					}
				}
				if (com instanceof JTextField) {
					list.add((JTextField) com);
				}
			}
			if (conti) {
				conti = false;
				continue;
			}
			if (!TableStarter.ME.enemy_list.get(0).equals(p)) {
				plus.append("	");
			}
			String cs = (String) combo.getSelectedItem();
			System.out.println(cs);
			plus.append(cs);
			for (JTextField jf : list) {
				plus.append(",");
				plus.append(jf.getText());
			}
		}
	}

	public static void writer() {
		StringBuffer plus = new StringBuffer();
		textExport(plus);
		InputStreamReader read;
		StringBuffer sb = new StringBuffer();
		try {
			read = FileReadSupporter.readUTF8("res/enemyTable/".concat(
					TableFileReader.dungeon_name).concat(".txt"));
			BufferedReader bf = new BufferedReader(read);
			int Y = 0;
			String str;
			while ((str = bf.readLine()) != null) {
				Y++;
				if (Y != TableFileReader.LEVEL) {
					if (Y != 99) {
						sb.append(str + "\r\n");
					} else {
						sb.append(str);
					}
					continue;
				} else {
					if (Y != 99) {
						sb.append(plus.toString() + "\r\n");
					} else {
						sb.append(plus.toString());
					}
				}
			}
			// pw.write(sb.toString());
			// pw.close();
			bf.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file = new File("res/enemyTable/".concat(
				TableFileReader.dungeon_name).concat(".txt"));
		try {
			FileWriter fw = new FileWriter(file, false);
			fw.write(sb.toString());
			System.out.println("書き込みました");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
