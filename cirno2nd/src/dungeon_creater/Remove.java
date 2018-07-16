package dungeon_creater;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Remove {
	public static void textAllRemove() {
		List<JTextField> list = new ArrayList<JTextField>();
		List<JLabel> l_list = new ArrayList<JLabel>();
		JComboBox combo = null;
		for (JPanel p : TableStarter.ME.enemy_list) {
			list.clear();
			l_list.clear();
			for (Component com : TableStarter.ME.panel_map.get(p)) {
				if (com instanceof JComboBox) {
					combo = (JComboBox) com;
				}
				if (com instanceof JTextField) {
					list.add((JTextField) com);
				}
				if (com instanceof JLabel) {
					l_list.add((JLabel) com);
				}
			}
			for (JTextField jf : list) {
				jf.setText("");
			}
			l_list.get(3).setText("HP :");
			l_list.get(4).setText("力 :");
			l_list.get(5).setText("守 :");
			l_list.get(6).setText("経験 :");
			combo.setSelectedIndex(0);
		}
	}

	public static void textRemove(ActionEvent e) {
		List<JTextField> list = new ArrayList<JTextField>();
		List<JLabel> l_list = new ArrayList<JLabel>();
		JComboBox combo = null;
		boolean flag = false;
		for (JPanel p : TableStarter.ME.enemy_list) {
			list.clear();
			l_list.clear();
			for (Component com : TableStarter.ME.panel_map.get(p)) {
				if (com instanceof JComboBox) {
					combo = (JComboBox) com;
				}
				if (com instanceof JTextField) {
					list.add((JTextField) com);
				}
				if (com instanceof JLabel) {
					l_list.add((JLabel) com);
				}
				if (com.equals(e.getSource())) {
					flag = true;
				}
			}
			if (flag) {
				break;
			}
		}
		for (JTextField jf : list) {
			jf.setText("");
		}
		l_list.get(3).setText("HP :");
		l_list.get(4).setText("力 :");
		l_list.get(5).setText("守 :");
		l_list.get(6).setText("経験 :");
		combo.setSelectedIndex(0);
	}
}
