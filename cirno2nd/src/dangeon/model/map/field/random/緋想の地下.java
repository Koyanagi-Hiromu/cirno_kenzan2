package dangeon.model.map.field.random;

import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_MapTip;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MapList;
import dangeon.util.R;

public class 緋想の地下 extends Base_Map_Random {
	private static final long serialVersionUID = 1L;

	public 緋想の地下() {
		super(Difficulty.Normal, 2, 0);
	}

	@Override
	public int defaultItemNumber() {
		if (Difficulty.Normal.is(this)) {
			return new R().nextInt(4) + new R().nextInt(2) + 2;
		} else {
			return new R().nextInt(4) + 2;
		}
	}

	@Override
	public BGM getBGM() {
		int f = MapList.getFloor();
		if (Difficulty.Lunatic.is(this) && f >= 40) {
			if (f == 40) {
				BGM.kanpyo_ch2_bbcc.play();
			}
			return BGM.kanpyo_ch2_bbcc;
		}
		return BGM.to_hisob2;
	}

	@Override
	public String getClassName() {
		return "緋想の地下";
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		list.add("緋想の剣によって作られたダンジョンだよ");
		list.add("アイテムを持ち込めないし");
		list.add("落ちているアイテムも未識別だよ");
		list.add("ボスはいないから終盤は");
		list.add("アイテムを惜しまず進んでね");
	};

	@Override
	public int[] getGouseiFloor() {
		return new int[] { 14, 29 };
	}

	@Override
	public Image_MapTip getMapTip() {
		int f = MapList.getFloor();
		if (f <= 5) {
			return Image_MapTip.草原_秋;
		} else if (f <= 11) {
			return Image_MapTip.マヨヒガ;
		} else if (f <= 14) {
			return Image_MapTip.紅魔館;
		} else if (f <= 24) {
			return Image_MapTip.永遠亭;
		} else if (f <= 29) {
			return Image_MapTip.木造校舎;
		} else if (f <= 35) {
			return Image_MapTip.紅魔館;
		} else if (f <= 40) {
			return Image_MapTip.地霊殿_水;
		} else if (f <= 45) {
			return Image_MapTip.地霊殿;
		} else {
			return Image_MapTip.草原;
		}
	}

	@Override
	public int getMaxFloor() {
		return 50;
	}

	@Override
	public int getMonsterHouse() {
		return super.getMonsterHouse();
	}

	@Override
	public int getSaisenParcent() {
		if (Difficulty.Normal.is(this)) {
			return 10;
		} else {
			return 5;
		}
	}

	@Override
	public int getShopParcent() {
		return super.getShopParcent();
	}

	@Override
	public int getSMH_Percent() {
		if (MapList.getFloor() < 40) {
			return SMH_NORMAL;
		} else {
			return 0;
		}
	}

	@Override
	public StoryManager getStoryManager_ClearFlag() {
		return StoryManager.緋想の地下clear;
	}

	@Override
	public StoryManager getStoryManager_FirstFlag() {
		return StoryManager.緋想の地下挑戦ok;
	}

	@Override
	public int getTrapDefaultValue() {
		return super.getTrapDefaultValue();
	}

	@Override
	public boolean isLightful() {
		return false;
	}

	@Override
	public boolean isWarningEnemy() {
		return true;
	}
}
