package dangeon.model.object.creature.npc;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import main.res.CHARA_IMAGE;
import main.util.FileReadSupporter;

public abstract class Base_Dungeon_NPC extends Base_NPC {

	private static final long serialVersionUID = 1L;

	public Base_Dungeon_NPC() {
		super(new Point(), "", null, false);
	}

	public Base_Dungeon_NPC(Point p, String name, CHARA_IMAGE c, boolean move) {
		super(p, name, c, move);
	}

	protected ArrayList<File> getFiles(String original_name, String head) {
		String table_folder[] = { "enemyTable/", "itemTable/", "trapTable/" };
		final ArrayList<File> final_list = new ArrayList<File>();
		for (int i = 0; i < table_folder.length; i++) {
			String fold = table_folder[i];
			String name = "res/".concat(fold);
			File[] files = FileReadSupporter.readURL(name).listFiles();
			ArrayList<File> list = new ArrayList<File>();
			for (File file : files) {
				if (!file.getName().matches(original_name)) {
					if (file.getName().startsWith(head)) {
						list.add(file);
					}
				}
			}
			if (i == 0) {
				for (File file : list) {
					final_list.add(file);
				}
			} else {
				for (Iterator<File> iterator = list.iterator(); iterator
						.hasNext();) {
					File file = iterator.next();
					if (!list.contains(file)) {
						iterator.remove();
					}
				}
			}
		}
		return final_list;
	}

	public String[] getSelects(String original_name, String head) {
		ArrayList<File> list = getFiles(original_name, head);
		String arr[] = new String[list.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i).getName();
			arr[i] = arr[i].substring(0, arr[i].lastIndexOf("txt") - 1)
					.substring(1);
		}
		return arr;
	}
}
