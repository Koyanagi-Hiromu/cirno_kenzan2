package dungeon_creater.second_ver;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dungeon_creater.second_ver.Listener_Table.TableMenuListener;

public class TableMainFrame extends JFrame {
	public static TableMainFrame MAIN = new TableMainFrame();

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	// ウィンドウサイズなどのパラメータ郡
	int main_panel_x = 1024;
	int main_panel_y = 850;

	Container main_panel = getContentPane();
	JMenuBar menu_bar = null;

	public String name_file_open = "csvを開く";

	public String name_file_save = "csvを保存する";

	public String name_file_save_other_name = "csvを別名で保存する";

	// public void paint(Graphics g) {
	// System.out.println("a");
	// super.paint(g);
	// }

	public String sort_hp = "HP";
	public String sort_str = "STR";
	public String sort_def = "DEF";
	public String sort_exp = "EXP";
	public String enemy_table = "enemy_table";
	public String item_table = "item_table";

	public TableMainFrame() {
		setTitle("チル見２テーブルクリエイター");
		setSize(main_panel_x, main_panel_y);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainPanelAddContents();
		setUi();
	}

	public void mainPanelAddContents() {
		setMenu();
	}

	/**
	 * メニューをセットする
	 */
	private void setMenu() {
		menu_bar = new JMenuBar();
		setJMenuBar(menu_bar);
		// メニュー作成
		JMenu file = new JMenu("File");
		JMenu sort = new JMenu("sort");
		JMenu switch_menu = new JMenu("switch");
		// メニューアイテム作成
		JMenuItem enemy = new JMenuItem(enemy_table);
		JMenuItem item = new JMenuItem(item_table);
		JMenuItem file_open = new JMenuItem(name_file_open);
		JMenuItem file_save = new JMenuItem(name_file_save);
		JMenuItem file_save_name = new JMenuItem(name_file_save_other_name);
		JMenuItem sort_h = new JMenuItem(sort_hp);
		JMenuItem sort_s = new JMenuItem(sort_str);
		JMenuItem sort_d = new JMenuItem(sort_def);
		JMenuItem sort_e = new JMenuItem(sort_exp);
		// メニューアイテムにアクションを付加する
		enemy.addActionListener(TableMenuListener.ME);
		item.addActionListener(TableMenuListener.ME);
		file_open.addActionListener(TableMenuListener.ME);
		file_save.addActionListener(TableMenuListener.ME);
		file_save_name.addActionListener(TableMenuListener.ME);
		sort_h.addActionListener(TableMenuListener.ME);
		sort_s.addActionListener(TableMenuListener.ME);
		sort_d.addActionListener(TableMenuListener.ME);
		sort_e.addActionListener(TableMenuListener.ME);
		// メニューにメニューアイテムを付加する
		switch_menu.add(enemy);
		switch_menu.add(item);
		file.add(file_open);
		file.add(file_save);
		file.add(file_save_name);
		sort.add(sort_h);
		sort.add(sort_s);
		sort.add(sort_d);
		sort.add(sort_e);
		// メニューバーにメニューを付加する
		menu_bar.add(file);
		menu_bar.add(sort);
		menu_bar.add(switch_menu);
	}

	private void setUi() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
