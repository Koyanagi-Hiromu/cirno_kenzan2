package dangeon.model.map.field.random.second;

import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_MapTip;
import dangeon.model.config.Config;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MapList;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.util.R;

public class 賢将裏の洞窟 extends Base_Map_Random {
	private static final long serialVersionUID = 1L;

	public 賢将裏の洞窟() {
		super(Difficulty.Normal, 0, 0);
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(3) + 3;
	}

	@Override
	public int getBelongingsMax() {
		if (Config.getFate() == 2) {
			return 20;
		} else {
			return 30;
		}
	}

	@Override
	public BGM getBGM() {
		int f = MapList.getFloor();
		if (f <= 24) {
			return BGM.kanpyo_ch2_fairy;
		} else {
			return BGM.kanpyo_ch2_bbcc;
		}
	}

	@Override
	public String getClassName() {
		return "賢将裏の洞窟";
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		list.add("持っているアイテムが全部発揮するし");
		list.add("罠も味方なすっごいダンジョン！");
		list.add("しかも常時透視状態だよ");
	}

	@Override
	public int[] getGouseiFloor() {
		return new int[] {};
	}

	@Override
	public Image_MapTip getMapTip() {
		int f = MapList.getFloor();
		if (f <= 3) {
			return Image_MapTip.草原_秋;
		} else if (f <= 7) {
			return Image_MapTip.命蓮寺;
		} else if (f <= 13) {
			return Image_MapTip.マヨヒガ;
		} else if (f <= 18) {
			return Image_MapTip.地霊殿_水;
		} else if (f <= 21) {
			return Image_MapTip.永遠亭;
		} else if (f <= 24) {
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
		return 2;
	}

	@Override
	public int getShopParcent() {
		return 2;
	}

	@Override
	public StoryManager getStoryManager_ClearFlag() {
		return StoryManager.賢将裏の洞窟ok;
	}

	@Override
	public StoryManager getStoryManager_FirstFlag() {
		return StoryManager.賢将裏の洞窟clear;
	}

	@Override
	public int getTrapDefaultValue() {
		return super.getTrapDefaultValue() + 1;
	}

	@Override
	public boolean isAllEnchantDungeon() {
		return true;
	}

	@Override
	protected boolean isBGMDemandedToPlay() {
		int f = MapList.getFloor();
		if (f == 25) {
			return true;
		} else {
			return super.isBGMDemandedToPlay();
		}
	}

	@Override
	public boolean isEnemyEnchantDungeon() {
		return false;
	}

	@Override
	protected boolean isFixFloor() {
		if (MapList.getFloor() > 20) {
			return new R().nextInt(100) < 40;
		} else {
			return new R().nextInt(100) < 20;
		}
	}

	@Override
	public boolean isInsane() {
		return MapList.getFloor() >= 22;
	}

	@Override
	public boolean isLightful() {
		int f = MapList.getFloor();
		return f < 4;
	}

	@Override
	public boolean isLvUpedEnemy() {
		return true;
	}

	@Override
	public boolean isTouShiDungeon() {
		return true;
	}

	@Override
	public boolean isTrapMasterDungeon() {
		return true;
	}

	@Override
	public boolean isWarningEnemy() {
		return true;
	}

}
