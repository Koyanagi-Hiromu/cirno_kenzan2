package dungeon_creater.second_ver.item_table;

import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dangeon.model.object.artifact.ArtifactData;
import dungeon_creater.second_ver.FileControl;

public class ItemTableGraphics extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static ItemTableGraphics ME = new ItemTableGraphics();

	int first_x = 10;

	int first_y = 90;
	int one_width = 150;
	int one_height = 18;
	int button_first_x = first_x + one_width;
	int one_button_width = 50;
	int one_button_height = 18;
	int on_off_button_width = 18;
	int rank_button_first_x = button_first_x + on_off_button_width + 5;
	int y_max = 38;
	String YES = "Y";
	public ArrayList<JButton> on_off = new ArrayList<JButton>();
	public ArrayList<JButton> rank = new ArrayList<JButton>();
	JLabel dungeon = new JLabel();
	public Color Y_COLOR = Color.DARK_GRAY;

	public Color NO_COLOR = Color.WHITE;

	public Color[] RANK_COLOR = { Color.RED, Color.ORANGE, Color.YELLOW,
			Color.GREEN, Color.BLUE };

	String[] rank_name = { "S", "A", "B", "C", "D" };

	ItemTableGraphics() {
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		setJLabelAndButton();
		setDungeon();
	}

	public String getRank(String str) {
		for (JButton j : rank) {
			if (j.getActionCommand().matches(str)) {
				return j.getText();
			}
		}
		return "S";
	}

	private Color getRankColor(String str) {
		int count = 0;
		for (String _str : rank_name) {
			if (_str.matches(str)) {
				break;
			}
			count++;
		}
		return RANK_COLOR[count];
	}

	public boolean isOn(String str) {
		for (JButton j : on_off) {
			if (j.getActionCommand().matches(str)) {
				return j.getText().matches(YES);
			}
		}
		return false;
	}

	public void setDungeon() {
		JButton y_n = null;
		JButton _rank = null;
		for (ArtifactData ad : ArtifactData.values()) {
			for (String str : ad.items) {
				for (JButton _b : on_off) {
					if (_b.getActionCommand().matches(str)) {
						y_n = _b;
						break;
					}
				}
				for (JButton _b : rank) {
					if (_b.getActionCommand().matches(str)) {
						_rank = _b;
						break;
					}
				}
				setSwitchJButton(str, y_n, _rank,
						FileControl.item_table.containsName(str));
			}
		}
		dungeon.setText(FileControl.getFileName());
		repaint();
	}

	private void setJButton(String str, int x, int y) {
		boolean boo = FileControl.item_table.containsName(str);
		JButton b = new JButton("Y");
		b.setActionCommand(str);
		b.addActionListener(ItemTableListener.ME);
		Insets is = b.getMargin();
		is.left = 0;
		is.right = 0;
		is.top = 0;
		is.bottom = 0;
		b.setMargin(is);
		b.setBounds(button_first_x + (one_width * x) + (one_button_width * x),
				first_y + (one_height * y), on_off_button_width, one_height);
		JButton b1 = new JButton("S");
		b1.addActionListener(ItemTableListener.ME);
		b1.setActionCommand(str);
		b1.setMargin(is);
		b1.setBounds(rank_button_first_x + (one_width * x)
				+ (one_button_width * x), first_y + (one_height * y),
				on_off_button_width, one_height);
		setSwitchJButton(str, b, b1, boo);
		on_off.add(b);
		rank.add(b1);
		add(b);
		add(b1);
	}

	private void setJLabel(String str, int x, int y) {
		JLabel test = new JLabel(str);
		test.setBounds(first_x + (one_width * x) + (one_button_width * x),
				first_y + (one_height * y), one_width, one_height);
		add(test);
	}

	private void setJLabelAndButton() {
		dungeon.setBounds(10, 10, 150, 15);
		dungeon.setText(FileControl.getFileName());
		add(dungeon);
		int x = 0;
		int y = 0;
		boolean isCard = true;
		for (ArtifactData ad : ArtifactData.values()) {
			if (isCard) {
				if (!ad.name().matches("CARD")) {
					isCard = false;
					x++;
					y = 0;
				}
			}
			for (String str : ad.items) {
				if (y == y_max) {
					x++;
					y = 0;
				}
				setJLabel(str, x, y);
				setJButton(str, x, y);
				y++;
				ArtifactData.getObject(str);
				// setJButton(str, x, y);
			}
			y++;
		}

	}

	private void setSwitchJButton(String str, JButton jb, JButton rank,
			boolean b) {
		if (b) {
			jb.setBackground(Y_COLOR);
			jb.setText("Y");
			rank.setText(FileControl.item_table.get(str).RANK);
			rank.setBackground(getRankColor(FileControl.item_table.get(str).RANK));
		} else {
			jb.setBackground(NO_COLOR);
			jb.setText("N");
			rank.setBackground(RANK_COLOR[0]);
			rank.setText("S");
		}
	}
}
