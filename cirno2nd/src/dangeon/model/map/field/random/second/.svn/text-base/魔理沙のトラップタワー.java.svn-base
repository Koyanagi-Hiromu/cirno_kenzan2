package dangeon.model.map.field.random.second;

import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_MapTip;
import dangeon.model.config.Config;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MapList;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.util.R;

public class 魔理沙のトラップタワー extends Base_Map_Random {
	private static final long serialVersionUID = 1L;

	public 魔理沙のトラップタワー() {
		super(Difficulty.Normal, 0, 0);
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(2) + 2;
	}

	@Override
	public int getBelongingsMax() {
		return 30;
	}

	@Override
	public BGM getBGM() {
		if (Config.getFate() == 2) {
			int f = MapList.getFloor();
			if (f >= 25) {
				return BGM.kanpyo_ch2_bbcc;
			} else if (f >= 21) {
				return BGM.The_boundary_of_the_world_wob;
			} else {
				return BGM.Strength;
			}

		} else {
			return BGM.Strength;
		}
	}

	@Override
	public String getClassName() {
		return "魔理沙のトラップタワー";
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		list.add("落ちているアイテムがちょっと少ない代わりに");
		list.add("いつもはにっくき罠を利用することが出来ます");
	}

	@Override
	public int[] getGouseiFloor() {
		return new int[] { 5, 9, 14, 20, 24 };
		// return new int[] { 14, 44, 74 };
	}

	@Override
	public Image_MapTip getMapTip() {
		int f = MapList.getFloor();
		if (f <= 5) {
			return Image_MapTip.永遠亭;
		} else if (f <= 11) {
			return Image_MapTip.マヨヒガ;
		} else if (f <= 17) {
			return Image_MapTip.紅魔館;
		} else if (f <= 24) {
			if (Config.getFate() == 2 && f >= 21) {
				return Image_MapTip.スキマ;
			} else {
				return Image_MapTip.命蓮寺;
			}
		} else if (f <= 27) {
			return Image_MapTip.地霊殿_水;
		} else {
			return Image_MapTip.地霊殿;
		}
	}

	@Override
	public int getMaxFloor() {
		return 30;
	}

	@Override
	public int getSaisenParcent() {
		return 0;
	}

	@Override
	public int getShopParcent() {
		if (MapList.getFloor() < 10) {
			return 20;
		} else {
			return 2;
		}
	}

	@Override
	public StoryManager getStoryManager_ClearFlag() {
		return StoryManager.トラップタワーok;
	}

	@Override
	public StoryManager getStoryManager_FirstFlag() {
		return StoryManager.トラップタワーclear;
	}

	@Override
	public int getTrapDefaultValue() {
		return super.getTrapDefaultValue() + 2;
	}

	@Override
	protected boolean isBGMDemandedToPlay() {
		return super.isBGMDemandedToPlay();
		// int f = MapList.getFloor();
		// if (f == 1) {
		// return true;
		// } else if (Config.getFate() == 2 && (f == 25 || f == 21)) {
		// return true;
		// } else {
		// }
	}

	@Override
	public boolean isEnemyEnchantDungeon() {
		return false;
	}

	@Override
	protected boolean isFixFloor() {
		if (MapList.getFloor() > 20) {
			return new R().nextInt(100) < 20;
		} else {
			return new R().nextInt(100) < 10;
		}
	}

	@Override
	public boolean isLightful() {
		int f = MapList.getFloor();
		return f < 4 || f == 25 || f == 27;
	}

	@Override
	public boolean isLvUpedEnemy() {
		return false;
	}

	@Override
	public boolean isTrapMasterDungeon() {
		return true;
	}

	@Override
	public boolean isWarningEnemy() {
		return false;
	}

}
