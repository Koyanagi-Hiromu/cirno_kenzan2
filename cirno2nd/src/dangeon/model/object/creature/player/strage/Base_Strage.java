package dangeon.model.object.creature.player.strage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.util.Show;
import dangeon.model.config.Config;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;

public abstract class Base_Strage implements Serializable {

	private static final long serialVersionUID = 1L;
	protected final HashMap<Integer, Base_Artifact> LIST_ITEM;
	protected Base_Strage me;
	protected final int ROW, COL;
	public final int SIZE;

	public Base_Strage(int row, int col) {
		ROW = row;
		COL = col;
		SIZE = ROW * COL;
		LIST_ITEM = new HashMap<Integer, Base_Artifact>(SIZE);
		clear();
	}

	public void add(Base_Artifact a, int row, int col) {
		Enchant.forceToRemove(a);
		LIST_ITEM.put(getIndex(row, col), a);
	}

	public synchronized void add_sort(Base_Artifact a) {
		addLast(a);
		sort();
	}

	public void addLast(Base_Artifact a) {
		Enchant.forceToRemove(a);
		for (int i = 0; i < SIZE; i++) {
			if (LIST_ITEM.get(i) != null) {
				continue;
			} else {
				LIST_ITEM.put(i, a);
				return;
			}
		}
		Show.showErrorMessageDialog("Error@Base_Strage.addLast");
	}

	public void clear() {
		for (int i = 0; i < SIZE; i++) {
			LIST_ITEM.put(i, null);
		}
	}

	public boolean contrains(Base_Artifact a) {
		return LIST_ITEM.containsValue(a);
	}

	public Base_Artifact get(int i) {
		return LIST_ITEM.get(i);
	}

	public Base_Artifact get(int row, int col) {
		return LIST_ITEM.get(getIndex(row, col));
	}

	public int getEmptyNumber() {
		int num = 0;
		for (int i = 0; i < SIZE; i++) {
			if (LIST_ITEM.get(i) == null) {
				num++;
			}
		}
		return num;
	}

	public int getFilledNumber() {
		return SIZE - getEmptyNumber();
	}

	protected int getIndex(int row, int col) {
		int coord = row * COL + col;
		if (coord >= SIZE) {
			Show.showCriticalErrorMessageDialog("Error@Base_Strage.getIndex");
		}
		return coord;
	}

	public ArrayList<Base_Artifact> getList() {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>(SIZE);
		for (Base_Artifact a : LIST_ITEM.values())
			if (a != null)
				list.add(a);
		return list;
	}

	public String getSaveURL() {
		StringBuilder sb = new StringBuilder();
		sb.append("save/");
		sb.append(Config.getSaveIndex());
		sb.append("/base_strage.save");
		return sb.toString();
	}

	public boolean isMax() {
		return !LIST_ITEM.containsValue(null);
	}

	public Object load() {
		String url = getSaveURL();
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(url));
			Object obj = ois.readObject();
			ois.close();
			return obj;
		} catch (Exception e) {
		}
		return null;
	}

	public void remove(Base_Artifact a) {
		int i;
		for (i = 0; i < SIZE; i++) {
			if (a.equals(LIST_ITEM.get(i))) {
				LIST_ITEM.put(i, null);
				break;
			}
		}
		for (; i < SIZE - 1; i++) {
			LIST_ITEM.put(i, LIST_ITEM.get(i + 1));
		}
		LIST_ITEM.put(SIZE - 1, null);
	}

	public void save() {
		try {
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(new FileOutputStream(getSaveURL()));
			oos.writeObject(this);
			oos.close();
		} catch (Exception e) {
			Show.showCriticalErrorMessageDialog(e);
		}
	}

	public void sort() {
		List<Base_Artifact> items = new ArrayList<Base_Artifact>(SIZE);
		for (int i = 0; i < SIZE; i++) {
			Base_Artifact a = LIST_ITEM.get(i);
			if (a != null) {
				items.add(a);
			}
		}
		items = Belongings.sort(items);
		System.out.println(items);
		clear();
		for (int i = 0; i < items.size(); i++) {
			Base_Artifact a = items.get(i);
			LIST_ITEM.put(i, a);
		}
	}

}
