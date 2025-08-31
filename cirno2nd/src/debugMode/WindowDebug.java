package debugMode;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import dangeon.model.object.artifact.ArtifactData;
import dangeon.util.Switch;
import debugMode.ListenerDebug.ListenerPopupMenuItem;
import debugMode.ListenerDebug.MainListenerDebug;

public class WindowDebug extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final int first_width = 10;

	private final int second_width = 50;
	private final int first_height = 10;
	private final int one_height = 40;
	public static JLabel death_l = new JLabel("ON");
	public static JLabel walk_l = new JLabel("ON");

	public static JPopupMenu items = new JPopupMenu();

	public WindowDebug() {
		if (!Switch.test) {
			return;
		}
		addButton();
		setLayout(null);
		setTitle("デバッグウィンドウ");
		setSize(300, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void addButton() {
		int current_y = 0;
		current_y += first_height;
		{
			// HPが0にならないフラグ。
			death_l.setBounds(first_height, current_y, 50, 30);
			add(death_l);
			JButton death = new JButton("死なない");
			death.addActionListener(MainListenerDebug.ME);
			death.setBounds(second_width, current_y, 150, 30);
			add(death);
		}
		current_y += one_height;
		{
			walk_l.setBounds(first_height, current_y, 50, 30);
			add(walk_l);
			JButton walk = new JButton("壁抜け");
			walk.addActionListener(MainListenerDebug.ME);
			walk.setBounds(second_width, current_y, 150, 30);
			add(walk);
		}
		current_y += one_height;
		{
			JButton set = new JButton("次のフロアへ");
			set.setBounds(second_width, current_y, 150, 30);
			set.addActionListener(MainListenerDebug.ME);
			add(set);
		}
		current_y += one_height;
		{
			// アイテムを追加する
			addPopupItem();
			JButton popup = new JButton("アイテムを追加");
			popup.setBounds(second_width, current_y, 150, 30);
			popup.addMouseListener(MainListenerDebug.ME);
			add(popup);
		}
		
		MainListenerDebug.ME.RefreshText();
	}

	private void addPopupItem() {
		// カデゴリ項目
		JMenu card = new JMenu("カード");
		JMenu grass = new JMenu("草");
		JMenu scroll = new JMenu("本");
		JMenu ring = new JMenu("リボン");
		addPopupMenuItem("CARD", card);
		addPopupMenuItem("GRASS", grass);
		addPopupMenuItem("SCROLL", scroll);
		addPopupMenuItem("RING", ring);
		// アイテム項目
		// 追加
		items.add(card);
		items.add(grass);
		items.add(scroll);
		items.add(ring);
	}

	private void addPopupMenu(JMenu category, JMenu on_the_menu) {
		Font font = new Font(Font.SERIF, Font.CENTER_BASELINE, 12);
		on_the_menu.setFont(font);
		on_the_menu.addActionListener(ListenerPopupMenuItem.ME);
		category.add(on_the_menu);
	}

	private void addPopupMenuItem(JMenu category, String str) {
		JMenuItem item = new JMenuItem(str);
		Font font = new Font(Font.SERIF, Font.CENTER_BASELINE, 12);
		item.setFont(font);
		item.addActionListener(ListenerPopupMenuItem.ME);
		category.add(item);
	}

	private void addPopupMenuItem(String category_string, JMenu category_menu) {
		int count = 0;
		int count_max = 26;
		int page = 0;
		JMenu current_menu = category_menu;
		ArrayList<JMenu> next_menu = new ArrayList<JMenu>();
		String next = "次へ";
		for (ArtifactData ad : ArtifactData.values()) {
			if (ad.name().matches(category_string)) {
				page = ad.items.length / count_max;
				for (int i = 0; i < page; i++) {
					JMenu menu = new JMenu(next);
					next_menu.add(menu);
				}
				if (!next_menu.isEmpty()) {
					addPopupMenu(current_menu, next_menu.get(0));
					for (int i = 1; i < next_menu.size(); i++) {
						addPopupMenu(next_menu.get(i - 1), next_menu.get(i));
					}
				}
				for (String item_name : ad.items) {
					count++;
					addPopupMenuItem(current_menu, item_name);
					if (count >= count_max) {
						if (!next_menu.isEmpty()) {
							current_menu = next_menu.get(0);
							next_menu.remove(0);
							count = 0;
						}
					}
				}
				return;
			}
		}
	}
}
