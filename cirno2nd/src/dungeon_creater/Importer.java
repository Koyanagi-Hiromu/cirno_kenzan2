package dungeon_creater;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dangeon.model.config.table.EnemyDetail;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class Importer {
	public static void actionImporter() {
		TableFileReader.tableRead();
		Remove.textAllRemove();
		int hp = 0;
		int str = 0;
		int def = 0;
		int exp = 0;
		int enemies = 0;
		List<Base_Enemy> enemy = TableFileReader.List;
		List<JPanel> p = TableStarter.ME.enemy_list;
		List<JTextField> list = new ArrayList<JTextField>();
		List<JLabel> list_l = new ArrayList<JLabel>();
		int i = 0;
		for (Base_Enemy em : enemy) {
			list.clear();
			list_l.clear();
			System.out.println(em);
			JPanel panel = p.get(i);
			for (Component c : TableStarter.ME.panel_map.get(panel)) {
				if (c instanceof JComboBox) {
					((JComboBox) c).setSelectedItem(em.getClass()
							.getSimpleName());
				}
				if (c instanceof JTextField) {
					list.add((JTextField) c);
				}
				if (c instanceof JLabel) {
					list_l.add((JLabel) c);
				}
			}
			list.get(0).setText(
					String.valueOf(EnemyDetail.matches(em.getClass()
							.getSimpleName(), em.getLV()).LV));
			list.get(1).setText(
					String.valueOf(EnemyDetail.matches(em.getClass()
							.getSimpleName(), em.getLV()).PARCENT));
			hp += em.getMAX_HP();
			list_l.get(3).setText(
					"HP :".concat(" ".concat(String.valueOf(em.getMAX_HP()))));
			str += em.getMAX_STR();
			list_l.get(4).setText(
					"力 :".concat(" ".concat(String.valueOf(em.getMAX_STR()))));
			def += em.getMAX_DEF();
			list_l.get(5).setText(
					"守 :".concat(" ".concat(String.valueOf(em.getMAX_DEF()))));
			exp += em.getENEMY_EXP();
			list_l.get(6).setText(
					"経験".concat(" ".concat(String.valueOf(em.getENEMY_EXP()))));
			i++;
		}
		enemies = i;
		int str_ave = str / enemies;
		int def_ave = def / enemies;
		int hp_ave = hp / enemies;
		int exp_ave = exp / enemies;

		TableStarter.ME.f_ave.setText(TableFileReader.dungeon_name
				+ TableFileReader.LEVEL + "階 モンスター数[" + enemies + "] HP平均["
				+ hp_ave + "] STR平均[" + str_ave + "] DEF平均[" + def_ave
				+ "] EXP平均[" + exp_ave + "] ");
	}

	public static void copyImporter(List<Base_Enemy> enemy) {
		Remove.textAllRemove();
		int hp = 0;
		int str = 0;
		int def = 0;
		int exp = 0;
		int enemies = 0;
		List<JPanel> p = TableStarter.ME.enemy_list;
		List<JTextField> list = new ArrayList<JTextField>();
		List<JLabel> list_l = new ArrayList<JLabel>();
		int i = 0;
		for (Base_Enemy em : enemy) {
			list.clear();
			list_l.clear();
			System.out.println(em);
			JPanel panel = p.get(i);
			for (Component c : TableStarter.ME.panel_map.get(panel)) {
				if (c instanceof JComboBox) {
					((JComboBox) c).setSelectedItem(em.getClass()
							.getSimpleName());
				}
				if (c instanceof JTextField) {
					list.add((JTextField) c);
				}
				if (c instanceof JLabel) {
					list_l.add((JLabel) c);
				}
			}
			list.get(0).setText(
					String.valueOf(EnemyDetail.matches(em.getClass()
							.getSimpleName(), em.getLV()).LV));
			list.get(1).setText(
					String.valueOf(EnemyDetail.matches(em.getClass()
							.getSimpleName(), em.getLV()).PARCENT));
			hp += em.getMAX_HP();
			list_l.get(3).setText(
					"HP :".concat(" ".concat(String.valueOf(em.getMAX_HP()))));
			str += em.getMAX_STR();
			list_l.get(4).setText(
					"力 :".concat(" ".concat(String.valueOf(em.getMAX_STR()))));
			def += em.getMAX_DEF();
			list_l.get(5).setText(
					"守 :".concat(" ".concat(String.valueOf(em.getMAX_DEF()))));
			exp += em.getENEMY_EXP();
			list_l.get(6).setText(
					"経験".concat(" ".concat(String.valueOf(em.getENEMY_EXP()))));
			i++;
		}
		enemies = i;
		int str_ave = str / enemies;
		int def_ave = def / enemies;
		int hp_ave = hp / enemies;
		int exp_ave = exp / enemies;

		TableStarter.ME.f_ave.setText(TableFileReader.dungeon_name
				+ TableFileReader.LEVEL + "階 モンスター数[" + enemies + "] HP平均["
				+ hp_ave + "] STR平均[" + str_ave + "] DEF平均[" + def_ave
				+ "] EXP平均[" + exp_ave + "] ");
	}
}
