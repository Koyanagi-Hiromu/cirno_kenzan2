package dangeon.model.map.field.random;

import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import dangeon.model.map.MapList;
import dangeon.util.R;

public class 慧音の最終問題 extends Base_Map_Random {
	private static final long serialVersionUID = 1L;
	public static final 慧音の最終問題 ME = new 慧音の最終問題();

	protected 慧音の最終問題() {
		super();
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(3) + 3;
	}

	@Override
	public String getClassName() {
		return "慧音の最終問題";
	}

	@Override
	public int[] getGouseiFloor() {
		return new int[] { 9, 19, 44, 79 };
	}

	@Override
	protected Image_Dungeon_Name getImageDungeonName() {
		return Image_Dungeon_Name.keine_no_saishuumondai;
	}

	@Override
	public Image_MapTip getMapTip() {
		int f = MapList.getFloor();
		if (f < 9) {
			return Image_MapTip.木造校舎;
		} else if (f < 19) {
			return Image_MapTip.紅魔館;
		} else if (f < 31) {
			return Image_MapTip.マヨヒガ;
		} else if (f < 41) {
			return Image_MapTip.木造校舎;
		} else if (f < 58) {
			return Image_MapTip.永遠亭;
		} else if (f < 70) {
			return Image_MapTip.命蓮寺;
		} else if (f < 87) {
			return Image_MapTip.地霊殿_水;
		} else {
			return Image_MapTip.地霊殿;
		}
	}

	@Override
	public int getMaxFloor() {
		return 99;
	}

	@Override
	public int getSaisenParcent() {
		return 20;
	}

	@Override
	public int getSMH_Percent() {
		return SMH_NORMAL;
	}

}
