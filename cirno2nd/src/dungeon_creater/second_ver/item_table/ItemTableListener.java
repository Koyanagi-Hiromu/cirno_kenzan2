package dungeon_creater.second_ver.item_table;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ItemTableListener implements ActionListener {
	static ItemTableListener ME = new ItemTableListener();

	String[] rank = { "S", "A", "B", "C", "D" };

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if (ItemTableGraphics.ME.on_off.contains(e.getSource())) {
			on_off_button((JButton) e.getSource());
		}
		if (ItemTableGraphics.ME.rank.contains(e.getSource())) {
			rank_button((JButton) e.getSource());
		}
	}

	private void on_off_button(JButton b) {
		String Y_N = b.getText().matches("Y") ? "N" : "Y";
		b.setBackground(Y_N.matches("Y") ? Color.DARK_GRAY : Color.WHITE);
		b.setText(Y_N);
	}

	private void rank_button(JButton b) {
		String current_rank = b.getText();
		int count = 0;
		for (String str : rank) {
			count++;
			if (current_rank.matches(str)) {
				if (str.matches("D")) {
					count = 0;
				}
				b.setText(rank[count]);
				b.setBackground(ItemTableGraphics.ME.RANK_COLOR[count]);
				break;
			}
		}
	}
}
