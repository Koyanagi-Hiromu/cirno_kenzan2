package dangeon.model.map.field.random.second;

import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import dangeon.model.map.MapList;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.util.R;

public class チルノ見参 extends Base_Map_Random {
	private static final long serialVersionUID = 1L;
	public static final チルノ見参 ME = new チルノ見参();

	protected チルノ見参() {
		super(Difficulty.Hard, 1, null);
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(3) + 3;
	}

	@Override
	public BGM getBGM() {
		return BGM.to_shin6b;
	}

	@Override
	public String getClassName() {
		return "チルノ見参";
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		list.add("チルノ見参！");
	}

	@Override
	protected long getFixedRandom() {
		return 4;
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
		return 15;
	}

	@Override
	public int getTrapDefaultValue() {
		return getTrapDefaultValue(MapList.getFloor());
	}

	@Override
	public boolean isEnemyEnchantDungeon() {
		return false;
	}
}
