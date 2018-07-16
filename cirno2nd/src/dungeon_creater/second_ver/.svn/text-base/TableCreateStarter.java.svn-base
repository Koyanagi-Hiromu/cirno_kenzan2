package dungeon_creater.second_ver;

import dangeon.model.config.table.EnemyTable;
import dangeon.util.Switch;
import dungeon_creater.second_ver.item_table.ItemTableGraphics;

public class TableCreateStarter {

	public static void main(String[] args) {
		EnemyTable.creater_flag = true;
		Switch.create = true;
		TableMainFrame.MAIN.setVisible(true);
		// TableMainFrame.MAIN.main_panel.add(EnemyTableGraphicsPanel.ME);
		// TableMainFrame.MAIN.main_panel.addMouseListener(TableListener.ME);
		// TableMainFrame.MAIN.main_panel.addMouseMotionListener(TableListener.ME);
		ChangeTable.chengeTable();
		TableMainFrame.MAIN.main_panel.add(ItemTableGraphics.ME);
		TableMainFrame.MAIN.setVisible(true);
	}
}
