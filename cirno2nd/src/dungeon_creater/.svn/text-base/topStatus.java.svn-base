package dungeon_creater;

import java.util.ArrayList;
import java.util.List;

import dangeon.model.config.table.EnemyTable;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class topStatus {
	enum STATUS {
		HP, STR, DEF, EXP
	}

	public static topStatus ME = new topStatus();
	private List<Base_Enemy> list = new ArrayList<Base_Enemy>();
	public List<String> hp_list = new ArrayList<String>();
	public List<String> str_list = new ArrayList<String>();
	public List<String> def_list = new ArrayList<String>();

	public List<String> exp_list = new ArrayList<String>();

	private void addList(List<String> LIST, STATUS status) {
		List<Base_Enemy> _list = new ArrayList<Base_Enemy>();
		while (true) {
			int i = 0;
			int j = 0;
			Base_Enemy b = null;
			Base_Enemy e = null;
			for (Base_Enemy em : list) {
				i = status(em, status);
				b = em;
				if (i >= j) {
					if (check(_list, b)) {
						continue;
					}
					j = i;
					e = em;
				}
			}
			if (e == null) {
				break;
			}
			_list.add(e);
			LIST.add(status.toString().concat(String.valueOf(j)).concat(":")
					.concat("[")
					+ e.getLV() + "]".concat(e.getClass().getSimpleName()));
		}
	}

	private boolean check(List<Base_Enemy> _list, Base_Enemy em) {
		for (Base_Enemy e : _list) {
			if (e.equals(em)) {
				return true;
			}
		}
		return false;
	}

	public String getString(List<String> list) {
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			sb.append(str.concat("\r\n"));
		}
		return sb.toString();
	}

	public void reRoad() {
		list.clear();
		str_list.clear();
		def_list.clear();
		exp_list.clear();
		sta();
	}

	// public topStatus() {
	// for (int i = 1; i <= 3; i++) {
	// for (String str : MonsterList.monsters) {
	// System.out.println(str);
	// list.add(EnemyTable.returnBaseEnemy(str, i));
	// }
	// }
	// addList(hp_list, STATUS.HP);
	// addList(str_list, STATUS.STR);
	// addList(def_list, STATUS.DEF);
	// addList(exp_list, STATUS.EXP);
	// }
	public void sta() {
		for (int i = 1; i <= 3; i++) {
			for (String str : MonsterList.monsters) {
				if (str.matches("")) {
					continue;
				}
				list.add(EnemyTable.returnBaseEnemy(str, i));
			}
		}
		addList(hp_list, STATUS.HP);
		addList(str_list, STATUS.STR);
		addList(def_list, STATUS.DEF);
		addList(exp_list, STATUS.EXP);
		TableStarter.ME.a_hp.setText(getString(hp_list));
		TableStarter.ME.a_str.setText(getString(str_list));
		TableStarter.ME.a_def.setText(getString(def_list));
		TableStarter.ME.a_exp.setText(getString(exp_list));
	}

	private int status(Base_Enemy em, STATUS status) {
		switch (status) {
		case HP:
			return em.getMAX_HP();
		case STR:
			return em.getMAX_STR();
		case DEF:
			return em.getMAX_DEF();
		case EXP:
			return em.getENEMY_EXP();
		default:
			return 0;
		}
	}

}
