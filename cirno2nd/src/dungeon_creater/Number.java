package dungeon_creater;

public class Number {
	public static void up(int plus) {
		int i = Integer.parseInt(TableStarter.ME.dungeon_level.getText());
		if (i + plus <= 0) {
			i = 1;
		} else {
			if (i + plus > DungeonDate.dungeonDateIsMatch(
					TableFileReader.dungeon_name).dungeonMaxLevel()) {
				return;
			}
			i += plus;
		}
		TableStarter.ME.dungeon_level.setText(String.valueOf(i));
		Importer.actionImporter();
	}
}
