package dungeon_creater;

import java.util.List;

import dangeon.model.config.table.EnemyTable;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class TableFileReader {
	public static TableFileReader ME = new TableFileReader();
	public static List<Base_Enemy> List;
	public static int LEVEL = 1;
	public static String dungeon_name = "慧音の最終問題";

	// public TableFileReader() {
	// TableStarter.ME.table.setText(dungeon_name);
	// EnemyTable.creater_flag = true;
	// if (TableStarter.ME.dungeon_level.getText().length() != 0) {
	// LEVEL = Integer.parseInt(TableStarter.ME.dungeon_level.getText());
	// }
	// List = EnemyTable.getListEnemyTable(LEVEL, dungeon_name);
	// }

	public static void tableRead() {
		dungeon_name = (String) TableStarter.ME.combo.getSelectedItem();
		TableStarter.ME.table.setText(dungeon_name);
		EnemyTable.creater_flag = true;
		if (TableStarter.ME.dungeon_level.getText().length() != 0) {
			LEVEL = Integer.parseInt(TableStarter.ME.dungeon_level.getText());
		}
		List = EnemyTable.getListEnemyTable(LEVEL, dungeon_name);
	}

	public static List<Base_Enemy> tableRead(int LV, String NAME) {
		EnemyTable.creater_flag = true;
		return EnemyTable.getListEnemyTable(LV, NAME);
	}
}
