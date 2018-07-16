package dangeon.model.map.field.random;

import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import dangeon.model.map.MapList;
import dangeon.util.R;

public class テストフィールド extends Base_Map_Random {
	private static final long serialVersionUID = 1L;
	public static final テストフィールド ME = new テストフィールド();

	protected テストフィールド() {
		super();
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(1);
	}

	@Override
	public String getClassName() {
		return "テストフィールド";
	}

	@Override
	public int[] getGouseiFloor() {
		return new int[] { 15 };
	}

	@Override
	protected Image_Dungeon_Name getImageDungeonName() {
		return Image_Dungeon_Name.tei_no_mondai;
	}

	@Override
	public Image_MapTip getMapTip() {
		int f = MapList.getFloor();
		if (f < 3) {
			return Image_MapTip.木造校舎;
		} else if (f < 4) {
			return Image_MapTip.紅魔館;
		} else if (f < 5) {
			return Image_MapTip.マヨヒガ;
		} else if (f < 6) {
			return Image_MapTip.木造校舎;
		} else if (f < 7) {
			return Image_MapTip.地霊殿_水;
		} else if (f < 8) {
			return Image_MapTip.永遠亭;
		} else if (f < 9) {
			return Image_MapTip.地霊殿_水;
		} else {
			return Image_MapTip.地霊殿;
		}
	}

	@Override
	public int getMaxFloor() {
		return 1;
	}

	@Override
	public int getSaisenParcent() {
		return 0;
	}

	@Override
	public boolean isInsane() {
		return true;
	}

}
