package dangeon.model.map.field.random;

import main.res.Image_Dungeon_Name;
import dangeon.util.R;

public class 最強への道 extends 慧音の最終問題 {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final 最強への道 ME = new 最強への道();

	protected 最強への道() {
		super();
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(2) + 2;
	}

	@Override
	public String getClassName() {
		return "最強への道";
	}

	@Override
	public int[] getGouseiFloor() {
		return new int[] { 10, 20, 45, 80 };
	}

	@Override
	protected Image_Dungeon_Name getImageDungeonName() {
		return Image_Dungeon_Name.saikyou_no_michi;
	}

	@Override
	public int getSaisenParcent() {
		return 10;
	}

}
