package dangeon.model.map.field.random;

import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_MapTip;
import dangeon.latest.scene.event.story.Event_Scene5;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MapList;
import dangeon.model.map.field.random.bossmap.BossMap_Sakasa;
import dangeon.model.map.field.special.map.BossMap;
import dangeon.util.R;
import dangeon.util.Switch;

public class 逆ヶ島 extends Base_Map_Random {
	private static final long serialVersionUID = 1L;

	public 逆ヶ島() {
		super(Difficulty.Normal, 0, StoryManager.逆ヶ島clear.hasFinished() ? null
				: 20);
	}

	@Override
	public int defaultItemNumber() {
		if (Difficulty.Normal.is(this)) {
			return new R().nextInt(4) + 2;
		} else {
			return new R().nextInt(2) + 2;
		}
	}

	@Override
	public BGM getBGM() {
		return BGM.kanpyo_ch2_hexa;
	}

	@Override
	public BossMap getBossMap() {
		return new BossMap_Sakasa(this);
	}

	@Override
	public String getClassName() {
		return "逆ヶ島";
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		list.add("鬼人正邪が願って出来たダンジョンです");
		list.add("時々大きな敵が出現します");
		list.add("アイテムを持ち込んで挑むべし");
		if (StoryManager.大妖精救出へ.hasFinished()) {
			list.add("鬼人正邪を懲らしめました");
		} else {
			list.add("鬼人正邪を懲らしめよう");
		}
	}

	@Override
	protected long getFixedRandom() {
		return System.nanoTime() / 1000000l;
	}

	@Override
	public int[] getGoldFloor() {
		return new int[] { 11 };
	}

	@Override
	public int[] getGouseiFloor() {
		return new int[] {};
	}

	@Override
	public Image_MapTip getMapTip() {
		int f = MapList.getFloor();
		if (f <= 8) {
			return Image_MapTip.命蓮寺;
		} else if (f <= 18) {
			return Image_MapTip.木造校舎;
		} else if (f <= 23) {
			return Image_MapTip.地霊殿_水;
		} else {
			return Image_MapTip.地霊殿;
		}
	}

	@Override
	public int getMaxFloor() {
		return 25;
	}

	@Override
	public int getMonsterHouse() {
		if (MapList.getFloor() != 1)
			return super.getMonsterHouse();
		else
			return 0;
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
		if (Switch.test) {
			return 10000;
		}
		return super.getShopParcent();
	}

	@Override
	public int getSMH_Percent() {
		if (getDIFFICULTY().isMoreThan(Difficulty.Normal)) {
			if (MapList.getFloor() > 10 && MapList.getFloor() < 22)
				return SMH_NORMAL / 2;
		}
		return 0;
	}

	@Override
	public StoryManager getStoryManager_ClearFlag() {
		return StoryManager.逆ヶ島clear;
	}

	@Override
	public StoryManager getStoryManager_FirstFlag() {
		return StoryManager.逆ヶ島挑戦ok;
	}

	@Override
	public void goToBossMap() {
		if (!getStoryManager_ClearFlag().hasFinished()) {
			new Event_Scene5(getBossMap());
		} else {
			super.goToBossMap();
		}
	}

	@Override
	public boolean isBossMap() {
		return super.isBossMap();
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
