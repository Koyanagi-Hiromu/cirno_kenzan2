package dangeon.model.map.field.random;

import java.util.HashMap;

import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import dangeon.model.map.MapList;
import dangeon.model.map.field.Base_Map;
import dangeon.model.map.field.town.map.EternalHouse;
import dangeon.model.map.field.town.map.UnderGroundHouse;
import dangeon.util.R;

public class 隕石異変 extends Base_Map_Random {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final 隕石異変 ME = new 隕石異変();

	private 隕石異変() {
		super();
	}

	@Override
	protected void addSpecialFloor(HashMap<Integer, Base_Map> hash) {
		hash.put(9, new EternalHouse());
		hash.put(19, new UnderGroundHouse());
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(3) + 3;
	}

	@Override
	public String getClassName() {
		return "隕石異変";
	}

	@Override
	protected Image_Dungeon_Name getImageDungeonName() {
		return Image_Dungeon_Name.inseki_ihen;
	}

	@Override
	public Image_MapTip getMapTip() {
		int f = MapList.getFloor();
		if (f < 5) {
			return Image_MapTip.草原;
		} else if (f < 8) {
			return Image_MapTip.紅魔館;
		} else if (f < 10) {
			return Image_MapTip.永遠亭;
		} else if (f < 15) {
			return Image_MapTip.マヨヒガ;
		} else if (f < 19) {
			return Image_MapTip.木造校舎;
		} else if (f < 24) {
			return Image_MapTip.地霊殿_水;
		} else if (f < 27) {
			return Image_MapTip.地霊殿;
		} else if (f < 29) {
			return Image_MapTip.地霊殿_水;
		} else {
			return Image_MapTip.草原;
		}
	}

	@Override
	public int getMaxFloor() {
		return 30;
	}

	@Override
	public boolean isHaraheru() {
		return true;
	}

	@Override
	public boolean isLightful() {
		if (MapList.getFloor() == 9) {
			return false;
		}
		return MapList.getFloor() < 10;
	}
}
