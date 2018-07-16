package dangeon.model.map.field.random;

import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import dangeon.model.map.MapList;
import dangeon.util.R;

public class てゐの問題 extends Base_Map_Random {
	private static final long serialVersionUID = 1L;
	public static final てゐの問題 ME = new てゐの問題();

	protected てゐの問題() {
		super();
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(3) + 3;
	}

	@Override
	public String getClassName() {
		return "てゐの問題";
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
		return 10;
	}

	@Override
	public int getSaisenParcent() {
		return 20;
	}

}
