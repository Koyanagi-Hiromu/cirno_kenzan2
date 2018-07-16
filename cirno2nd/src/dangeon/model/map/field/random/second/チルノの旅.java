package dangeon.model.map.field.random.second;

import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import dangeon.model.map.MapList;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.util.R;

public class チルノの旅 extends Base_Map_Random {
	private static final long serialVersionUID = 1L;
	public static final チルノの旅 ME = new チルノの旅();

	protected チルノの旅() {
		super(Difficulty.Easy, 0, null);
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(3) + 3;
	}

	@Override
	public BGM getBGM() {
		return BGM.kanpyo_ch2_hexa;
	}

	@Override
	public String getClassName() {
		return "チルノの旅";
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		list.add("緋想の剣によって作られたダンジョンです");
		list.add("マップや敵、アイテムの配置を変えずに");
		list.add("１Fからやり直せます");
		list.add("反省がそのまま活かせるダンジョンです");
	}

	@Override
	protected long getFixedRandom() {
		return System.nanoTime() / 1000000l;
	}

	@Override
	public int getGoldSaisenParcent() {
		return 100;
	}

	@Override
	public int[] getGouseiFloor() {
		return new int[] { 9, 19 };
	}

	@Override
	protected Image_Dungeon_Name getImageDungeonName() {
		return Image_Dungeon_Name.keine_no_saishuumondai;
	}

	@Override
	public Image_MapTip getMapTip() {
		int f = MapList.getFloor();
		if (f < 5) {
			return Image_MapTip.草原_秋;
		} else if (f < 8) {
			return Image_MapTip.紅魔館;
		} else if (f < 10) {
			return Image_MapTip.永遠亭;
		} else if (f < 15) {
			return Image_MapTip.マヨヒガ;
		} else if (f == 15) {
			return Image_MapTip.木造校舎;
		} else if (f < 19) {
			return Image_MapTip.草原;
		} else if (f < 24) {
			return Image_MapTip.地霊殿_水;
		} else if (f < 27) {
			return Image_MapTip.地霊殿;
		} else if (f < 29) {
			return Image_MapTip.地霊殿_水;
		} else {
			return Image_MapTip.草原_秋;
		}
	}

	@Override
	public int getMaxFloor() {
		return 30;
	}

	@Override
	public int getSaisenParcent() {
		return 20;
	}

	@Override
	public int getTrapDefaultValue() {
		return getTrapDefaultValue(MapList.getFloor());
	}

	@Override
	public boolean isEnemyEnchantDungeon() {
		return false;
	}

	@Override
	public boolean isLightful() {
		int f = MapList.getFloor();
		if (f > 15 && f < 19) {
			return true;
		}
		return f < 9;
	}
}
