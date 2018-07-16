package dungeon_creater.second_ver.readGraphics;

import java.util.ArrayList;

import dangeon.util.R;
import dungeon_creater.second_ver.SetObject;
import dungeon_creater.second_ver.SubBaseCreature;

public class SortImage {
	public static int current_sort = 1;
	public final static int HP = 0;
	public final static int STR = 1;
	public final static int DEF = 2;
	public final static int EXP = 3;
	public final static int SPECIAL = 4;
	public final static int NORMAL = 5;
	private static boolean flag = false;

	private static void quickSort(ArrayList<SubBaseCreature> list,
			ArrayList<SubBaseCreature> back_list) {
		ArrayList<SubBaseCreature> small = new ArrayList<SubBaseCreature>();
		ArrayList<SubBaseCreature> big = new ArrayList<SubBaseCreature>();
		int base = 0;
		SubBaseCreature _c = list.get(R.ran(list.size()));
		switch (current_sort) {
		case HP:
			base = _c.C.getHP();
			break;
		case STR:
			base = _c.C.getSTR();
			break;
		case DEF:
			base = _c.C.getDEF();
			break;
		case EXP:
			base = _c.C.getENEMY_EXP();
			break;
		}
		int _i = 0;
		for (SubBaseCreature c : list) {
			switch (current_sort) {
			case HP:
				_i = c.C.getHP();
				break;
			case STR:
				_i = c.C.getSTR();
				break;
			case DEF:
				_i = c.C.getDEF();
				break;
			case EXP:
				_i = c.C.getENEMY_EXP();
				break;
			}
			if (base <= _i) {
				big.add(c);
			} else {
				small.add(c);
			}
		}

		if (big.isEmpty()) {
			if (sameStatus(small)) {
				for (SubBaseCreature c : small) {
					back_list.add(c);
				}
				return;
			}
		}
		if (small.isEmpty()) {
			if (sameStatus(big)) {
				for (SubBaseCreature c : big) {
					back_list.add(c);
				}
				return;
			}
		}
		if (!big.isEmpty()) {
			quickSort(big, back_list);
		}
		if (!small.isEmpty()) {
			quickSort(small, back_list);
		}
	}

	public static boolean sameStatus(ArrayList<SubBaseCreature> list) {
		int count = 1;
		if (list.size() <= count) {
			return true;
		}
		int base = -1;
		int _i = 0;
		for (SubBaseCreature c : list) {
			switch (current_sort) {
			case HP:
				_i = c.C.getHP();
				break;
			case STR:
				_i = c.C.getSTR();
				break;
			case DEF:
				_i = c.C.getDEF();
				break;
			case EXP:
				_i = c.C.getENEMY_EXP();
				break;
			}
			if (base == -1) {
				base = _i;
			}
		}
		if (base == _i) {
			return true;
		}
		return false;
	}

	public static void setSort(int i) {
		current_sort = i;
		if (i == NORMAL) {
			SetObject.enemy_list = SetObject.base_enemy_list;
			return;
		}
		ArrayList<SubBaseCreature> list = new ArrayList<SubBaseCreature>();
		quickSort(SetObject.enemy_list, list);
		SetObject.enemy_list = list;
	}

	public static void sort() {
		if (flag) {
			flag = false;
			sortEnemyImage();
		}
	}

	public static void sortEnemyImage() {
		switch (current_sort) {
		case HP:
			break;
		case STR:
			break;
		case DEF:
			break;
		case EXP:
			break;
		case NORMAL:
			ReadingImage.setListImage(null);
			break;
		}
	}
}
