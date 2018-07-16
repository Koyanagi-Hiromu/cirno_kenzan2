package dungeon_creater.second_ver.item_table;

import java.util.ArrayList;

/**
 * 使い方が変なので注意
 * 
 * @author rottiti
 * 
 */
public class SubItem {
	public ArrayList<SubItem> list = new ArrayList<SubItem>();
	public final String NAME;
	public String RANK;

	public SubItem() {
		NAME = "";
		RANK = "";
	}

	public SubItem(String nAME, String rANK) {
		NAME = nAME;
		RANK = rANK;
	}

	public void add(SubItem si) {
		list.add(si);
	}

	public void clear() {
		list.clear();
	}

	public boolean containsName(String name) {
		for (SubItem si : list) {
			if (si.NAME.matches(name)) {
				return true;
			}
		}
		return false;
	}

	public SubItem get(String name) {
		for (SubItem si : list) {
			if (si.NAME.matches(name)) {
				return si;
			}
		}
		return null;
	}

	public ArrayList<SubItem> getList() {
		return list;
	}
}
