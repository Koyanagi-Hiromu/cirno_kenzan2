package dangeon.model.map.field.random;

import java.awt.Point;
import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_MapTip;
import dangeon.model.config.Config;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.artifact.item.pot.かき氷器;
import dangeon.model.object.artifact.item.ring.萃香の頑強リボン;
import dangeon.util.R;

public class 二撃必殺 extends Base_Map_Random {
	private static final long serialVersionUID = 1L;

	public 二撃必殺() {
		super(Difficulty.Normal, 0, null);
	}

	@Override
	public int dangerLv(int floor) {
		if (floor > 30) {
			return 3;
		}
		return Config.getFate();
	}

	@Override
	public int defaultItemNumber() {
		// int f = MapList.getFloor();
		if (firstEasyFloor()) {
			return new R().nextInt(4) + 3;
		} else {
			return new R().nextInt(4) + 2;
		}
	}

	private boolean firstEasyFloor() {
		return MapList.getFloor() <= 7;
	}

	@Override
	public BGM getBGM() {
		int floor = MapList.getFloor();
		if (floor < 31) {
			return BGM.SAKADuKi;
		} else if (floor < 36) {
			return BGM.frozen_night;
		} else {
			return BGM.kanpyo_ch_bbcc;
		}
	}

	@Override
	public String getClassName() {
		return "二撃必殺";
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		list.add("二撃で倒し倒れるダンジョンです");
		list.add("最初から最後まで気が抜けません");
		list.add("初めに霊夢のリボンが支給されます");
		list.add("大切に扱いましょう");
	}

	@Override
	public int[] getGouseiFloor() {
		return new int[] {};
	}

	@Override
	public Image_MapTip getMapTip() {
		int floor = MapList.getFloor();
		if (Difficulty.Lunatic.is(this)) {
			if (floor == 31)
				BGM.frozen_night.play();
			if (floor == 47)
				BGM.kanpyo_ch_bbcc.play();
			if (floor < 25) {
				return Image_MapTip.地霊殿_水;
			} else if (floor <= 30) {
				return Image_MapTip.地霊殿;
			} else if (floor < 36) {
				return Image_MapTip.stars;
			} else if (floor < 41) {
				return Image_MapTip.命蓮寺;
			} else if (floor < 46) {
				return Image_MapTip.地霊殿_水;
			} else if (floor == 46) {
				return Image_MapTip.stars;
			} else if (floor < 51) {
				return Image_MapTip.地霊殿;
			}
			return Image_MapTip.地霊殿;
		} else {
			return Image_MapTip.地霊殿;
		}
	}

	@Override
	public int getMaxFloor() {
		if (Difficulty.Lunatic.is(this)) {
			return 50;
		} else {
			return 30;
		}
	}

	@Override
	public int getMonsterHouse() {
		// if (Switch.test) {
		// return 10000;
		// }
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
		// if (Switch.test) {
		// return 10000;
		// }
		if (firstEasyFloor()) {
			return 13;
		} else {
			return 5;
		}
	}

	@Override
	public StoryManager getStoryManager_ClearFlag() {
		return StoryManager.二撃必殺clear;
	}

	@Override
	public StoryManager getStoryManager_FirstFlag() {
		return StoryManager.二撃必殺挑戦ok;
	}

	@Override
	protected Base_Artifact[] initItems(Point p) {
		int size = 4 - getDIFFICULTY().index;
		if (size <= 2)
			size = 2;
		return new Base_Artifact[] { new 大きなおにぎり(p), new 萃香の頑強リボン(p),
				new かき氷器(p, size) };
	}

	@Override
	public boolean is2GEKI() {
		return true;
	}

	@Override
	public boolean isLightful() {
		return firstEasyFloor();
	}

	@Override
	public boolean isWarningEnemy() {
		return false;
	}

}
