package dungeon_creater.second_ver.Listener_Table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dungeon_creater.second_ver.ChangeTable;
import dungeon_creater.second_ver.FileControl;
import dungeon_creater.second_ver.TableMainFrame;
import dungeon_creater.second_ver.TableScene;
import dungeon_creater.second_ver.readGraphics.SortImage;

public class TableMenuListener implements ActionListener {
	public static TableMenuListener ME = new TableMenuListener();

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.matches(TableMainFrame.MAIN.name_file_open)) {
			FileControl.openFiles();
		} else if (command
				.matches(TableMainFrame.MAIN.name_file_save_other_name)) {
			FileControl.saveFiles();
		} else if (command.matches(TableMainFrame.MAIN.name_file_save)) {
			FileControl.writeEnemyTable();
		} else if (command.matches(TableMainFrame.MAIN.sort_str)) {
			SortImage.setSort(SortImage.STR);
		} else if (command.matches(TableMainFrame.MAIN.sort_def)) {
			SortImage.setSort(SortImage.DEF);
		} else if (command.matches(TableMainFrame.MAIN.sort_exp)) {
			SortImage.setSort(SortImage.EXP);
		} else if (command.matches(TableMainFrame.MAIN.sort_hp)) {
			SortImage.setSort(SortImage.HP);
		} else if (command.matches(TableMainFrame.MAIN.enemy_table)) {
			TableScene.setTableScene(TableScene.ENEMY_TABLE);
			ChangeTable.chengeTable();
		} else if (command.matches(TableMainFrame.MAIN.item_table)) {
			TableScene.setTableScene(TableScene.ITEM_TABLE);
			ChangeTable.chengeTable();
		}

	}
}
