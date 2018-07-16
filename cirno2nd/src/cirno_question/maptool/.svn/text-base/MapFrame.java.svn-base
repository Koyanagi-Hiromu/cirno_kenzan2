package cirno_question.maptool;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import cirno_question.MapToolMusic;
import cirno_question.MusicButton;
import cirno_question.SetOnMapObject;
import cirno_question.frame.MapPanel;
import cirno_question.maptool.map_button.ButtonClear;
import cirno_question.maptool.map_button.ButtonEnter;
import cirno_question.maptool.map_button.ButtonExit;
import cirno_question.maptool.map_button.ButtonExport;
import cirno_question.maptool.map_button.ButtonImport;
import cirno_question.maptool.map_button.ButtonObject;
import cirno_question.maptool.map_button.ButtonRoad;
import cirno_question.maptool.map_button.ButtonRoom;
import cirno_question.maptool.map_button.ButtonUnBreakWalkable;
import cirno_question.maptool.map_button.ButtonWalkable;
import cirno_question.maptool.map_button.WaterRoadButton;

public class MapFrame extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 5538711280916469953L;

	public static MapFrame ME = new MapFrame();
	private String[] bounds_x = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	public static int frame_x = 850;
	public static int frame_y = 500;
	public final static int MAX_X = 600;
	public final static int MAX_Y = 450;
	public final static int fill_max = 15;
	public JPanel main_p = new JPanel();
	public MapPanel map_p = new MapPanel();
	public JPanel select_p = new JPanel();
	public JPanel detail_p = new JPanel();
	public JLabel clip_board = new JLabel("null");
	public JTextArea detail_txt = new JTextArea();
	public JPanel combo_p = new JPanel();
	public JPanel device = new JPanel();
	public JPanel walkable = new JPanel();
	public JPanel road = new JPanel();
	public JComboBox bounds_box = new JComboBox(bounds_x);
	public JPanel file_p = new JPanel();
	public JPanel clear_p = new JPanel();
	public JPanel music_p = new JPanel();
	public JComboBox music_combo = new JComboBox(MapToolMusic.ME.getPlayList());
	public JComboBox clear_box = new JComboBox(new String[] { "全消去", "敵",
			"アイテム", "トラップ" });

	public void frameCreate() {
		setTitle("マップツール");
		setSize(frame_x, frame_y);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// public LineBorder border = new LineBorder(Color.BLACK, 1, true);

	//
	// public void paint(Graphics g) {
	// g.drawLine(100, 100, 150, 150);
	// }

	public void mapFrameStarter() {
		setPanel();
		setSelectPanel();
		frameCreate();
	}

	// String[] ss = new String[] { "一", "二", "三" };
	public void setComboBox() {
	}

	public void setDetailText(Point p) {
		MapMass mass = MainMap.ME.getMass(p);

		if (mass == null) {
			return;
		}
		String detail = "";
		if (mass.getListMapObject().isEmpty()) {
			detail_txt.setText("");
			return;
		}
		for (SetOnMapObject mo : mass.getListMapObject()) {
			for (String s : mo.getObjectDetail()) {
				detail = detail.concat(s).concat("\n");
			}
			detail = detail.concat("\n");
		}
		detail_txt.setText(detail);
	}

	public void setMapTitle(String str) {
		setTitle("マップツール ".concat(str));
	}

	public void setMusicPanel() {
		int g_x = 0;
		int g_y = 0;
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		music_p.setLayout(layout);
		music_combo.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 10));
		// music_combo.setSelectedIndex(1);
		JButton play = new JButton("再生");
		JButton stop = new JButton("停止");
		JButton asc = new JButton("＋");
		JButton dce = new JButton("－");
		play.setActionCommand("再生");
		stop.setActionCommand("停止");
		asc.setActionCommand("アップ");
		dce.setActionCommand("ダウン");
		play.addActionListener(MusicButton.ME);
		stop.addActionListener(MusicButton.ME);
		asc.addActionListener(MusicButton.ME);
		dce.addActionListener(MusicButton.ME);
		int x = 200;
		music_combo.setPreferredSize(new Dimension(x, 25));
		music_combo.setMaximumSize(new Dimension(x, 25));
		play.setMargin(new Insets(0, 0, 0, 0));
		stop.setMargin(new Insets(0, 0, 0, 0));
		asc.setMargin(new Insets(0, 0, 0, 0));
		dce.setMargin(new Insets(0, 0, 0, 0));
		play.setPreferredSize(new Dimension(x / 4, 25));
		play.setMaximumSize(new Dimension(x / 4, 25));
		stop.setPreferredSize(new Dimension(x / 4, 25));
		stop.setMaximumSize(new Dimension(x / 4, 25));
		asc.setPreferredSize(new Dimension(x / 4, 25));
		asc.setMaximumSize(new Dimension(x / 4, 25));
		dce.setPreferredSize(new Dimension(x / 4, 25));
		dce.setMaximumSize(new Dimension(x / 4, 25));
		gbc.gridx = g_x;
		gbc.gridy = g_y;
		gbc.gridwidth = 4;
		layout.setConstraints(music_combo, gbc);
		gbc.gridwidth = 1;
		g_y++;
		gbc.gridx = g_x;
		gbc.gridy = g_y;
		layout.setConstraints(play, gbc);
		g_x++;
		gbc.gridx = g_x;
		gbc.gridy = g_y;
		layout.setConstraints(stop, gbc);
		g_x++;
		gbc.gridx = g_x;
		gbc.gridy = g_y;
		layout.setConstraints(asc, gbc);
		g_x++;
		gbc.gridx = g_x;
		gbc.gridy = g_y;
		layout.setConstraints(dce, gbc);
		music_p.add(music_combo);
		music_p.add(play);
		music_p.add(stop);
		music_p.add(asc);
		music_p.add(dce);
	}

	public void setPanel() {
		GridBagLayout gl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		main_p.setLayout(gl);
		gbc.insets = new Insets(10, 10, 10, 10);
		map_p.setPreferredSize(new Dimension(MAX_X + 1, MAX_Y + 1));
		select_p.setPreferredSize(new Dimension(200, MAX_Y + 1));
		map_p.setMaximumSize(new Dimension(MAX_X + 1, MAX_Y + 1));
		select_p.setMaximumSize(new Dimension(200, MAX_Y + 1));
		map_p.addMouseMotionListener(MapToolMouseListener.ME);
		map_p.addMouseListener(MapToolMouseListener2.ME);

		gl.setConstraints(map_p, gbc);
		gbc.gridx = 1;
		gl.setConstraints(select_p, gbc);
		main_p.add(map_p);
		main_p.add(select_p);
		main_p.addMouseWheelListener(MapMouseWheel.ME);
		add(main_p);
	}

	public void setRoadPanel() {

	}

	public void setSelectPanel() {
		GridBagLayout gl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 4, 0);
		select_p.setLayout(gl);
		int x = 150;
		int y = 25;

		int gx = 0;
		int gy = 0;

		JButton jb = new JButton("選択オブジェクト設置");
		jb.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 10));
		JButton jb1 = new JButton("部屋");

		JButton jb4 = new JButton("消");
		JButton imp_p = new JButton("開く");
		JButton exp_p = new JButton("保存");
		JButton enter = new JButton("入口");
		JButton exit = new JButton("出口");
		device.add(enter);
		device.add(exit);
		clear_p.add(jb4);
		clear_p.add(clear_box);
		combo_p.add(new JLabel("範囲x"));
		combo_p.add(bounds_box);
		file_p.add(exp_p);
		file_p.add(imp_p);
		detail_txt.setEditable(false);
		detail_p.add(detail_txt);
		jb.addActionListener(new ButtonObject());
		jb1.addActionListener(new ButtonRoom());

		jb4.addActionListener(new ButtonClear());
		enter.addActionListener(new ButtonEnter());
		exit.addActionListener(new ButtonExit());
		imp_p.addActionListener(new ButtonImport());
		exp_p.addActionListener(new ButtonExport());
		detail_p.setPreferredSize(new Dimension(x, 70));
		detail_p.setMaximumSize(new Dimension(x, 70));
		music_p.setPreferredSize(new Dimension(200, 50));
		music_p.setMaximumSize(new Dimension(200, 50));
		detail_txt.setPreferredSize(new Dimension(x, 70));
		detail_txt.setMaximumSize(new Dimension(x, 70));
		clip_board.setPreferredSize(new Dimension(x, 20));
		clip_board.setMaximumSize(new Dimension(x, 20));
		jb.setPreferredSize(new Dimension(x, y));
		jb.setMaximumSize(new Dimension(x, y));
		jb1.setPreferredSize(new Dimension(x, y));
		jb1.setMaximumSize(new Dimension(x, y));

		jb4.setPreferredSize(new Dimension(x - 100, y - 5));
		jb4.setMaximumSize(new Dimension(x - 100, y - 5));
		combo_p.setPreferredSize(new Dimension(x, y));
		combo_p.setMaximumSize(new Dimension(x, y));
		file_p.setPreferredSize(new Dimension(x, y));
		file_p.setMaximumSize(new Dimension(x, y));
		clear_p.setPreferredSize(new Dimension(x, y));
		clear_p.setMaximumSize(new Dimension(x, y));
		bounds_box.setPreferredSize(new Dimension(x - 90, 20));
		bounds_box.setMaximumSize(new Dimension(x - 90, 20));
		imp_p.setPreferredSize(new Dimension(x - 90, 20));
		imp_p.setMaximumSize(new Dimension(x - 90, 20));
		exp_p.setPreferredSize(new Dimension(x - 90, 20));
		exp_p.setMaximumSize(new Dimension(x - 90, 20));
		exit.setPreferredSize(new Dimension(x - 90, 20));
		exit.setMaximumSize(new Dimension(x - 90, 20));
		enter.setPreferredSize(new Dimension(x - 90, 20));
		enter.setMaximumSize(new Dimension(x - 90, 20));
		clear_box.setPreferredSize(new Dimension(x - 70, y - 5));
		clear_box.setMaximumSize(new Dimension(x - 70, y - 5));

		gbc.gridx = gx;
		gbc.gridy = gy;
		gy++;
		gl.setConstraints(music_p, gbc);
		gbc.gridx = gx;
		gbc.gridy = gy;
		gy++;
		gl.setConstraints(clip_board, gbc);
		gbc.insets = new Insets(0, 0, 10, 0);
		gbc.gridx = gx;
		gbc.gridy = gy;
		gy++;
		gl.setConstraints(detail_p, gbc);
		gbc.gridx = gx;
		gbc.gridy = gy;
		gy++;
		gl.setConstraints(jb, gbc);
		gbc.gridx = gx;
		gbc.gridy = gy;
		gy++;
		gl.setConstraints(jb1, gbc);
		gbc.gridx = gx;
		gbc.gridy = gy;
		gy++;
		gl.setConstraints(walkable, gbc);
		gbc.gridx = gx;
		gbc.gridy = gy;
		gy++;
		gl.setConstraints(road, gbc);
		gbc.gridx = gx;
		gbc.gridy = gy;
		gy++;
		gl.setConstraints(device, gbc);
		gbc.gridx = gx;
		gbc.gridy = gy;
		gy++;
		gl.setConstraints(combo_p, gbc);
		gbc.gridx = gx;
		gbc.gridy = gy;
		gy++;
		gl.setConstraints(clear_p, gbc);
		gbc.gridx = gx;
		gbc.gridy = gy;
		gy++;
		gl.setConstraints(file_p, gbc);
		setMusicPanel();
		setWalkablePanel();
		select_p.add(clip_board);
		select_p.add(combo_p);
		select_p.add(detail_p);
		select_p.add(file_p);
		select_p.add(device);
		select_p.add(clear_p);
		select_p.add(music_p);
		select_p.add(jb);
		select_p.add(jb1);
		select_p.add(walkable);
		select_p.add(road);

	}

	public void setWalkablePanel() {
		int x = 150;
		int y = 25;
		walkable.setPreferredSize(new Dimension(x, y));
		walkable.setMaximumSize(new Dimension(x, y));
		road.setPreferredSize(new Dimension(x, y));
		road.setMaximumSize(new Dimension(x, y));
		x = 70;
		y = 20;
		JButton jb2 = new JButton("壁");
		JButton jb3 = new JButton("通路");
		jb2.addActionListener(new ButtonWalkable());
		jb3.addActionListener(new ButtonRoad());
		jb2.setPreferredSize(new Dimension(x, y));
		jb2.setMaximumSize(new Dimension(x, y));
		jb3.setPreferredSize(new Dimension(x, y));
		jb3.setMaximumSize(new Dimension(x, y));
		JButton unBreakWalkableButton = new JButton("不壊壁");
		unBreakWalkableButton.setMargin(new Insets(0, 0, 0, 0));
		JButton waterRoadButton = new JButton("水路");
		unBreakWalkableButton.addActionListener(new ButtonUnBreakWalkable());
		waterRoadButton.addActionListener(new WaterRoadButton());
		waterRoadButton.setPreferredSize(new Dimension(x, y));
		waterRoadButton.setMaximumSize(new Dimension(x, y));
		unBreakWalkableButton.setPreferredSize(new Dimension(x, y));
		unBreakWalkableButton.setMaximumSize(new Dimension(x, y));
		walkable.add(jb2);
		walkable.add(unBreakWalkableButton);
		road.add(jb3);
		road.add(waterRoadButton);
	}
}
