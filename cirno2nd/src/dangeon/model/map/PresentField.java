package dangeon.model.map;

import dangeon.model.map.field.Base_Map;

public class PresentField {
	private static Base_Map present_field;

	public static Base_Map get() {
		return present_field;
	}

	public static boolean isRandomField() {
		return present_field.isRandomField();
	}

	public static void setPresentField(Base_Map bfm) {
		present_field = bfm;
	}
}
