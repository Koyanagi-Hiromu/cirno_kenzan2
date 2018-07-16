package dungeon_creater;

public enum DungeonDate {
	慧音の最終問題(99), 最強への道(99), 隕石異変(30), 博麗中結界(10), てゐの問題(10), てゐの異変(30), 命蓮寺の隠し大穴(
			30), 命蓮寺の隠し穴(30), 鬼の集会場(10), 鬼の宴会場(10), 大妖精の巣窟(30), 大会用(50);
	private final int DUNGEON_LEVEL;

	private static final String[] DUNGEON_DATE = { "慧音の最終問題", "隕石異変", "最強への道",
			"博麗中結界", "てゐの問題", "てゐの異変", "命蓮寺の隠し穴", "命蓮寺の隠し大穴", "鬼の宴会場", "鬼の集会場",
			"大妖精の巣窟", "大会用" };

	public static DungeonDate dungeonDateIsMatch(String str) {
		for (DungeonDate date : values()) {
			if (date.toString().equals(str)) {
				return date;
			}
		}
		return null;
	}

	public static String[] getListStringName() {
		return DUNGEON_DATE;
	}

	private DungeonDate(int LEVEL) {
		DUNGEON_LEVEL = LEVEL;
	}

	public int dungeonMaxLevel() {
		return DUNGEON_LEVEL;
	}
}
