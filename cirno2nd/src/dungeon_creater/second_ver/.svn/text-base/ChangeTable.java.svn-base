package dungeon_creater.second_ver;

import dungeon_creater.second_ver.Listener_Table.TableListener;
import dungeon_creater.second_ver.item_table.ItemTableGraphics;

public class ChangeTable {
	public static void chengeEnemyTable() {
		TableMainFrame.MAIN.main_panel.add(EnemyTableGraphicsPanel.ME);
		TableMainFrame.MAIN.main_panel.addMouseListener(TableListener.ME);
		TableMainFrame.MAIN.main_panel.addMouseMotionListener(TableListener.ME);
		TableMainFrame.MAIN.main_panel.validate();
		TableMainFrame.MAIN.main_panel.repaint();
	}

	public static void chengeItemTable() {
		TableMainFrame.MAIN.main_panel.add(ItemTableGraphics.ME);
		TableMainFrame.MAIN.main_panel.validate();
		TableMainFrame.MAIN.main_panel.repaint();
	}

	public static void chengeTable() {
		remove();
		switch (TableScene.CURRENT) {
		case ENEMY_TABLE:
			chengeEnemyTable();
			break;
		case ITEM_TABLE:
			chengeItemTable();
			break;
		}
	}

	private static void remove() {
		if (TableScene.BEFORE == null) {
			return;
		}
		System.out.println(TableScene.BEFORE);
		switch (TableScene.BEFORE) {
		case ENEMY_TABLE:
			TableMainFrame.MAIN.main_panel.removeAll();
			TableMainFrame.MAIN.main_panel
					.removeMouseListener(TableListener.ME);
			TableMainFrame.MAIN.main_panel
					.removeMouseMotionListener(TableListener.ME);
			break;
		case ITEM_TABLE:
			TableMainFrame.MAIN.main_panel.removeAll();
			break;
		}
	}
}
