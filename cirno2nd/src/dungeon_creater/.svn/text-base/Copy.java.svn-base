package dungeon_creater;

public class Copy {
	private static String dungeon_name;
	private static int dungeon_level;

	public static void addCopy() {
		Importer.copyImporter(TableFileReader.tableRead(dungeon_level,
				dungeon_name));
	}

	public static void getCopy() {
		dungeon_level = TableFileReader.LEVEL;
		dungeon_name = TableFileReader.dungeon_name;
	}
}
