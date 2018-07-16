package cirno_question.maptool;

public enum MassCategory {
	ROOM("部屋", "　"), ROAD("通路", "☆"), WALKABLE("壁", "壁"), WATER("水路", "水"), UNBREAKWALKABLE(
			"不壊壁", "■");
	public static MassCategory getMassCategoryMatchPanelName(String str) {
		for (MassCategory mc : MassCategory.values()) {
			if (str.matches(mc.PANEL_ONE_NAME)) {
				return mc;
			}
		}
		return null;
	}

	// , ENEMY("敵"), ITEM("物"), TRAP(
	// "罠")
	private final String PANEL_NAME;

	public final String PANEL_ONE_NAME;

	// private MassCategory(String obj) {
	// PANEL_NAME = "オブジェクト";
	// PANEL_ONE_NAME = obj;
	// }

	// public boolean isObject() {
	// return PANEL_NAME.matches("オブジェクト");
	// }

	private MassCategory(String str, String str2) {
		PANEL_NAME = str;
		PANEL_ONE_NAME = str2;
	}

	public String getMassOneName() {
		return PANEL_ONE_NAME;
	}
}
