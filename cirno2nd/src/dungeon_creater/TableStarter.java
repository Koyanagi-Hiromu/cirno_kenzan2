package dungeon_creater;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
 * 
 * 実装予定機能
 * 
 * 前、次の階層のエネミーをコピーする 現在の階層のエネミーをクリップに保存する エネミーを選択した時に即時反映
 * 
 * 
 * 
 * 
 * @author Administrator
 * 
 */
public class TableStarter extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static TableStarter ME;

	public static void main(String args[]) {
		ME = new TableStarter();
		Importer.actionImporter();
		topStatus.ME.sta();
	}

	public List<JPanel> enemy_list = new ArrayList<JPanel>();

	public HashMap<JPanel, List<Component>> panel_map = new HashMap<JPanel, List<Component>>();

	public JTextField dungeon_level;

	public JTextField f_ave;
	public JComboBox combo;
	public JLabel table;
	public JPanel top_status = new JPanel();
	public JTextArea a_hp = new JTextArea(18, 25);
	public JTextArea a_str = new JTextArea(18, 25);
	public JTextArea a_def = new JTextArea(18, 25);
	public JTextArea a_exp = new JTextArea(18, 25);

	public TableStarter() {
		setCompornent();
		setTitle(TestFrame.VER_NAME);
		setSize(TestFrame.WIDTH, TestFrame.HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void enemyStatusPanel(JPanel p2, GridBagLayout gl,
			GridBagLayout gl2, GridBagConstraints glc, GridBagConstraints glc2,
			int grid_y) {
		JButton jb3 = new JButton("消");
		jb3.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 10));
		jb3.setPreferredSize(new Dimension(20, 20));
		jb3.setMargin(new Insets(0, 0, 0, 0));
		jb3.addActionListener(Lister.ME);
		jb3.setActionCommand(Lister.ACTION.CLEAR.toString());
		JLabel jl4 = new JLabel("名 :");
		JLabel jl5 = new JLabel("LV :");
		JLabel jl6 = new JLabel("出率 :");
		JLabel jl7 = new JLabel("HP :");
		JLabel jl8 = new JLabel("力 :");
		JLabel jl9 = new JLabel("守 :");
		JLabel jl10 = new JLabel("経験 :");
		JComboBox comb = new JComboBox(MonsterList.monsters);
		comb.setMaximumRowCount(15);
		comb.setBackground(Color.white);
		JTextField f_level = new JTextField(TestFrame.SPACE);
		JTextField f_parcent = new JTextField(TestFrame.SPACE);
		int x = 0;
		setGrid(gl2, jl4, glc2, new Point(x, 0), 0.0d, 0.0d, new Point(1, 1),
				GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHWEST,
				new Insets(0, 5, 0, 5));
		x++;
		setGrid(gl2, comb, glc2, new Point(x, 0), 0.0d, 0.0d, new Point(1, 1),
				GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHWEST,
				new Insets(0, 5, 1, 5));
		x++;
		setGrid(gl2, jl5, glc2, new Point(x, 0), 0.0d, 0.0d, new Point(1, 1),
				GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHWEST,
				new Insets(0, 5, 0, 5));
		x++;
		setGrid(gl2, f_level, glc2, new Point(x, 0), 0.0d, 0.0d,
				new Point(1, 1), GridBagConstraints.NONE,
				GridBagConstraints.NORTHWEST, new Insets(0, 5, 0, 5));
		x++;
		setGrid(gl2, jl6, glc2, new Point(x, 0), 0.0d, 0.0d, new Point(1, 1),
				GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHWEST,
				new Insets(0, 5, 0, 5));
		x++;
		setGrid(gl2, f_parcent, glc2, new Point(x, 0), 0.0d, 0.0d, new Point(1,
				1), GridBagConstraints.NONE, GridBagConstraints.NORTHWEST,
				new Insets(0, 5, 0, 5));
		x++;
		setGrid(gl2, jl7, glc2, new Point(x, 0), 0.0d, 0.0d, new Point(1, 1),
				GridBagConstraints.NONE, GridBagConstraints.NORTHWEST,
				new Insets(0, 5, 0, 5));
		x++;
		setGrid(gl2, jl8, glc2, new Point(x, 0), 0.0d, 0.0d, new Point(1, 1),
				GridBagConstraints.NONE, GridBagConstraints.NORTHWEST,
				new Insets(0, 5, 0, 5));
		x++;
		setGrid(gl2, jl9, glc2, new Point(x, 0), 0.0d, 0.0d, new Point(1, 1),
				GridBagConstraints.NONE, GridBagConstraints.NORTHWEST,
				new Insets(0, 5, 0, 5));
		x++;
		setGrid(gl2, jl10, glc2, new Point(x, 0), 0.0d, 0.0d, new Point(1, 1),
				GridBagConstraints.NONE, GridBagConstraints.NORTHWEST,
				new Insets(0, 5, 0, 5));
		x++;
		setGrid(gl2, jb3, glc2, new Point(x, 0), 1.0d, 0.0d, new Point(1, 1),
				GridBagConstraints.NONE, GridBagConstraints.NORTHEAST,
				new Insets(0, 5, 0, 5));
		setGrid(gl, p2, glc, new Point(0, grid_y), 1.0d, 0.0d,
				new Point(1, 10), GridBagConstraints.HORIZONTAL,
				GridBagConstraints.NORTHWEST, new Insets(0, 5, 0, 5));
		p2.add(comb);
		p2.add(jb3);
		p2.add(jl4);
		p2.add(jl5);
		p2.add(jl6);
		p2.add(jl7);
		p2.add(jl8);
		p2.add(jl9);
		p2.add(jl10);
		p2.add(f_level);
		p2.add(f_parcent);
		List<Component> list = new ArrayList<Component>();
		list.add(comb);
		list.add(jb3);
		list.add(jl4);
		list.add(jl5);
		list.add(jl6);
		list.add(jl7);
		list.add(jl8);
		list.add(jl9);
		list.add(jl10);
		list.add(f_level);
		list.add(f_parcent);
		panel_map.put(p2, list);
	}

	private void setCompornent() {
		JPanel p = new JPanel();
		GridBagLayout gl = new GridBagLayout();
		p.setLayout(gl);
		GridBagConstraints glc = new GridBagConstraints();
		JLabel jl1 = new JLabel("モンスターテーブル作成補助ツール");
		jl1.setBackground(Color.gray);
		jl1.setForeground(Color.white);
		jl1.setOpaque(true);
		jl1.setHorizontalAlignment(SwingConstants.CENTER);

		table = new JLabel(TableFileReader.dungeon_name);
		table.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel jl2 = new JLabel("階層 :");
		JLabel jl3 = new JLabel("F");
		f_ave = new JTextField();
		JButton jb1 = new JButton("出力");
		jb1.addActionListener(Lister.ME);
		jb1.setActionCommand(Lister.ACTION.IMPORTER.toString());
		dungeon_level = new JTextField(TestFrame.SPACE);
		dungeon_level.setText("1");
		jb1.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 10));
		jb1.setPreferredSize(new Dimension(30, 20));
		jb1.setMargin(new Insets(5, 0, 5, 0));
		JButton plus = new JButton("+1");
		plus.addActionListener(Lister.ME);
		plus.setActionCommand(Lister.ACTION.p1.toString());
		plus.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 10));
		plus.setPreferredSize(new Dimension(30, 20));
		plus.setMargin(new Insets(5, 0, 5, 0));
		JButton minus = new JButton("-1");
		minus.addActionListener(Lister.ME);
		minus.setActionCommand(Lister.ACTION.m1.toString());
		minus.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 10));
		minus.setPreferredSize(new Dimension(30, 20));
		minus.setMargin(new Insets(5, 0, 5, 0));
		JButton jb2 = new JButton("変換");
		jb2.addActionListener(Lister.ME);
		jb2.setActionCommand(Lister.ACTION.EXPORT.toString());

		// combo = new JComboBox(TableFileReader.DUNGEON_DATE);
		combo = new JComboBox(DungeonDate.getListStringName());
		combo.setBackground(Color.white);
		JScrollPane hp_s = setTextArea(a_hp, topStatus.ME.hp_list);
		JScrollPane str_s = setTextArea(a_str, topStatus.ME.str_list);
		JScrollPane def_s = setTextArea(a_def, topStatus.ME.def_list);
		JScrollPane exp_s = setTextArea(a_exp, topStatus.ME.exp_list);

		JButton jb4 = new JButton("読み込み");
		jb4.addActionListener(Lister.ME);
		jb4.setActionCommand(Lister.ACTION.DUNGEON.toString());

		JButton copy = new JButton("写");
		copy.addActionListener(Lister.ME);
		copy.setActionCommand(Lister.ACTION.COPY.toString());
		copy.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 10));
		copy.setPreferredSize(new Dimension(30, 20));
		copy.setMargin(new Insets(5, 0, 5, 0));

		JButton paest = new JButton("貼");
		paest.addActionListener(Lister.ME);
		paest.setActionCommand(Lister.ACTION.PAEST.toString());
		paest.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 10));
		paest.setPreferredSize(new Dimension(30, 20));
		paest.setMargin(new Insets(5, 0, 5, 0));

		int grid_y = 0;

		setGrid(gl, jl1, glc, new Point(0, grid_y), 1.0d, 0.0d, new Point(1,
				TestFrame.MAX_GRID_X), GridBagConstraints.HORIZONTAL,
				GridBagConstraints.NORTH, new Insets(0, 0, 0, 0));
		grid_y++;
		setGrid(gl, jl2, glc, new Point(0, grid_y), 0.0d, 0.0d,
				new Point(1, 1), GridBagConstraints.NONE,
				GridBagConstraints.NORTHWEST, new Insets(5, 5, 5, 5));
		setGrid(gl, dungeon_level, glc, new Point(1, grid_y), 0.0d, 0.0d,
				new Point(1, 1), GridBagConstraints.NONE,
				GridBagConstraints.NORTHWEST, new Insets(5, 5, 5, 5));
		setGrid(gl, jl3, glc, new Point(2, grid_y), 0.0d, 0.0d,
				new Point(1, 1), GridBagConstraints.NONE,
				GridBagConstraints.NORTHWEST, new Insets(5, 5, 5, 5));
		setGrid(gl, jb1, glc, new Point(3, grid_y), 0.0d, 0.0d,
				new Point(1, 1), GridBagConstraints.NONE,
				GridBagConstraints.NORTHWEST, new Insets(7, 5, 0, 5));
		setGrid(gl, plus, glc, new Point(4, grid_y), 0.0d, 0.0d,
				new Point(1, 1), GridBagConstraints.NONE,
				GridBagConstraints.NORTHWEST, new Insets(7, 5, 0, 5));
		setGrid(gl, minus, glc, new Point(5, grid_y), 0.0d, 0.0d, new Point(1,
				1), GridBagConstraints.NONE, GridBagConstraints.NORTHWEST,
				new Insets(7, 5, 0, 5));
		setGrid(gl, copy, glc, new Point(6, grid_y), 0.0d, 0.0d,
				new Point(1, 1), GridBagConstraints.NONE,
				GridBagConstraints.NORTHWEST, new Insets(7, 5, 0, 5));
		setGrid(gl, paest, glc, new Point(7, grid_y), 1.0d, 0.0d, new Point(1,
				1), GridBagConstraints.NONE, GridBagConstraints.NORTHWEST,
				new Insets(7, 5, 0, 5));

		setGrid(gl, table, glc, new Point(8, grid_y), 0.0d, 0.0d, new Point(1,
				1), GridBagConstraints.NONE, GridBagConstraints.NORTHEAST,
				new Insets(5, 5, 5, 5));
		setGrid(gl, combo, glc, new Point(9, grid_y), 0.0d, 0.0d, new Point(1,
				1), GridBagConstraints.NONE, GridBagConstraints.NORTHEAST,
				new Insets(1, 5, 5, 5));
		setGrid(gl, jb4, glc, new Point(10, grid_y), 0.0d, 0.0d,
				new Point(1, 1), GridBagConstraints.NONE,
				GridBagConstraints.NORTHEAST, new Insets(1, 5, 5, 5));
		grid_y++;

		GridBagLayout gl2 = new GridBagLayout();
		GridBagConstraints glc2 = new GridBagConstraints();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();
		JPanel p9 = new JPanel();

		enemy_list.add(p2);
		enemy_list.add(p3);
		enemy_list.add(p4);
		enemy_list.add(p5);
		enemy_list.add(p6);
		enemy_list.add(p7);
		enemy_list.add(p8);
		enemy_list.add(p9);

		for (JPanel panel : enemy_list) {
			grid_y++;
			panel.setLayout(gl2);
			enemyStatusPanel(panel, gl, gl2, glc, glc2, grid_y);
		}
		grid_y++;
		setGrid(gl, f_ave, glc, new Point(0, grid_y), 1.0d, 0.0d, new Point(1,
				TestFrame.MAX_GRID_X), GridBagConstraints.HORIZONTAL,
				GridBagConstraints.NORTHWEST, new Insets(15, 5, 5, 5));
		grid_y++;

		GridBagLayout gl3 = new GridBagLayout();
		GridBagConstraints glc3 = new GridBagConstraints();

		top_status.setLayout(gl3);

		setGrid(gl3, hp_s, glc3, new Point(0, 0), 0.0d, 0.0d, new Point(1, 1),
				GridBagConstraints.NONE, GridBagConstraints.NORTHWEST,
				new Insets(5, 5, 5, 5));
		setGrid(gl3, str_s, glc3, new Point(1, 0), 0.0d, 0.0d, new Point(1, 1),
				GridBagConstraints.NONE, GridBagConstraints.NORTHWEST,
				new Insets(5, 5, 5, 5));
		setGrid(gl3, def_s, glc3, new Point(2, 0), 0.0d, 0.0d, new Point(1, 1),
				GridBagConstraints.NONE, GridBagConstraints.NORTHWEST,
				new Insets(5, 5, 5, 5));
		setGrid(gl3, exp_s, glc3, new Point(3, 0), 1.0d, 0.0d, new Point(1, 1),
				GridBagConstraints.NONE, GridBagConstraints.NORTHWEST,
				new Insets(5, 5, 5, 5));
		setGrid(gl, top_status, glc, new Point(0, grid_y), 1.0d, 0.0d,
				new Point(1, TestFrame.MAX_GRID_X), GridBagConstraints.NONE,
				GridBagConstraints.NORTHWEST, new Insets(5, 5, 5, 5));

		top_status.add(hp_s);
		top_status.add(str_s);
		top_status.add(def_s);
		top_status.add(exp_s);

		grid_y++;
		setGrid(gl, jb2, glc, new Point(0, grid_y), 1.0d, 1.0d, new Point(1,
				TestFrame.MAX_GRID_X), GridBagConstraints.NONE,
				GridBagConstraints.SOUTH, new Insets(5, 5, 5, 5));
		p.add(table);
		p.add(jl1);
		p.add(jl2);
		p.add(jl3);
		p.add(jb1);
		p.add(jb2);
		p.add(jb4);
		p.add(f_ave);
		p.add(plus);
		p.add(minus);
		p.add(copy);
		p.add(paest);
		p.add(combo);
		p.add(top_status);
		p.add(dungeon_level);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		p.add(p6);
		p.add(p7);
		p.add(p8);
		p.add(p9);
		add(p);

	}

	/**
	 * @param base
	 * @param com
	 * @param gbc
	 * @param p
	 *            グリッド位置
	 * @param wx
	 *            セル跨ぎx,デフォルトで1
	 * @param wy
	 *            セル跨ぎy
	 * @param pw
	 *            セル大きさ対比
	 * @param FILL
	 *            component表示幅
	 * @param ANCHOR
	 *            component表示位置
	 */
	private void setGrid(GridBagLayout base, Component com,
			GridBagConstraints gbc, Point p, double wx, double wy, Point pw,
			int FILL, int ANCHOR, Insets INSET) {
		gbc.gridx = p.x;
		gbc.gridy = p.y;
		gbc.weightx = wx;
		gbc.weighty = wy;
		gbc.gridheight = pw.x;
		gbc.gridwidth = pw.y;
		gbc.fill = FILL;
		gbc.anchor = ANCHOR;
		gbc.insets = INSET;
		base.setConstraints(com, gbc);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
	}

	private JScrollPane setTextArea(JTextArea ta, List<String> list) {
		ta.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		ta.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 11));
		// ta.setPreferredSize(new Dimension(143, 250));
		// ta.setText(topStatus.ME.getString(list));
		JScrollPane sp = new JScrollPane(ta,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return sp;
	}
}
