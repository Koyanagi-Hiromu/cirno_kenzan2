package dangeon.model.map.field.random;

import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_MapTip;
import dangeon.model.config.Config;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MapList;
import dangeon.model.map.field.Base_Map;
import dangeon.model.map.field.town.map.UnderGroundHouse;
import dangeon.util.R;

public class 風穴旅行 extends Base_Map_Random {
	private static final long serialVersionUID = 1L;

	public 風穴旅行() {
		super(Difficulty.Easy, 0, null);
	}

	@Override
	public int dangerLv(int floor) {
		int plus = (getEnemySize() == 0) ? 1 : 0;
		return super.dangerLv(floor) + plus;
	}

	@Override
	public int defaultItemNumber() {
		if (MapList.getFloor() <= 2)
			return 7;
		if (Difficulty.Easy.is(this)) {
			return new R().nextInt(4) + 4;
		} else {
			return new R().nextInt(4) + 3;
		}
	}

	@Override
	public BGM getBGM() {
		if (MapList.getFloor() > 19)
			return BGM.kagura_satori;
		else
			return BGM.doukutsu;
	}

	@Override
	public String getClassName() {
		return "風穴旅行";
	}

	@Override
	public int getEnemySize() {
		if (MapList.getFloor() < getFloor_BetweenSmallBig())
			return -1;
		else
			return 0;
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		list.add("地霊殿への道です");
		list.add("モンスターハウスの確率が大変高いです");
		list.add("また低層階では小さい敵が出現します");
	}

	private int getFloor_BetweenSmallBig() {
		switch (Config.getFate()) {
		case 0:
			return 16;
		case 1:
			return 13;
		case 2:
			return 10;
		default:
			return 0;
		}
	}

	@Override
	public int[] getGouseiFloor() {
		return new int[] { 19 };
	}

	@Override
	public Image_MapTip getMapTip() {
		if (MapList.getFloor() > 19)
			return Image_MapTip.地霊殿;
		else
			return Image_MapTip.地霊殿_水;
	}

	@Override
	public int getMaxFloor() {
		if (Difficulty.Easy.is(this)) {
			return 30;
		} else {
			return 30;
		}
	}

	@Override
	public int getMonsterHouse() {
		if (MapList.getFloor() < 3)
			return 0;
		if (MapList.getFloor() < 5)
			return 100;
		if (MapList.getFloor() > 19)
			return 100;
		return 50;
	}

	@Override
	public int getSaisenParcent() {
		return 0;
	}

	@Override
	public int getShopParcent() {
		return 0;
	}

	@Override
	public StoryManager getStoryManager_ClearFlag() {
		return StoryManager.風穴旅行clear;
	}

	@Override
	public StoryManager getStoryManager_FirstFlag() {
		return StoryManager.風穴旅行挑戦ok;
	}

	@Override
	public boolean isLightful() {
		return false;
	}

	@Override
	public Base_Map returnTown() {
		return new UnderGroundHouse();
	}
}
