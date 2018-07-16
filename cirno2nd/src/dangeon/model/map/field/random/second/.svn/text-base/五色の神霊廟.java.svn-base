package dangeon.model.map.field.random.second;

import java.awt.Color;
import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import dangeon.model.config.Config;
import dangeon.model.config.StoryManager;
import dangeon.model.map.MapList;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.util.R;

public class 五色の神霊廟 extends Base_Map_Random {
	private static final long serialVersionUID = 1L;

	public 五色の神霊廟() {
		super(Difficulty.Easy, 0, 10);
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(3) + 2;
	}

	@Override
	public int getBelongingsMax() {
		return super.getBelongingsMax() - Config.getFate() * 10;
	}

	@Override
	public BGM getBGM() {
		return BGM.to_shin6b;
	}

	@Override
	public String getClassName() {
		return "五色の神霊廟";
	}

	@Override
	protected void getExn_Warning(ArrayList<String> list) {
		list.add("持っているアイテムの印が全て適用されます");
		list.add("たくさんカードが落ちているので");
		list.add("お気に入りのカードを探してみよう");
		if (Config.getFate() == 1) {
			list.add(Color.ORANGE.toString() + "※アイテムが２０コまでしか持てません(Normal)※");
		} else if (Config.getFate() == 2) {
			list.add(Color.ORANGE.toString() + "※アイテムが１０コまでしか持てません(Hard)※");
		}
	}

	@Override
	public int[] getGouseiFloor() {
		return new int[] {};
	}

	@Override
	protected Image_Dungeon_Name getImageDungeonName() {
		return Image_Dungeon_Name.keine_no_saishuumondai;
	}

	@Override
	public Image_MapTip getMapTip() {
		int f = MapList.getFloor();
		if (f < 4) {
			return Image_MapTip.草原_秋;
		} else if (f < 12) {
			return Image_MapTip.紅魔館;
		} else if (f < 21) {
			return Image_MapTip.木造校舎;
		} else if (f < 28) {
			return Image_MapTip.草原;
		} else {
			return Image_MapTip.命蓮寺;
		}
	}

	@Override
	public int getMaxFloor() {
		return 30;
	}

	@Override
	public int getSaisenParcent() {
		return 4;
	}

	@Override
	public int getShopParcent() {
		return 0;
	}

	@Override
	public int getSMH_Percent() {
		return 0;
	}

	@Override
	public StoryManager getStoryManager_ClearFlag() {
		return StoryManager.五色の神霊廟clear;
	}

	@Override
	public StoryManager getStoryManager_FirstFlag() {
		return StoryManager.五色の神霊廟挑戦ok;
	}

	@Override
	public int getTrapDefaultValue() {
		return getTrapDefaultValue(MapList.getFloor());
	}

	@Override
	public boolean isAllEnchantDungeon() {
		return true;
	}

	@Override
	public boolean isEnemyEnchantDungeon() {
		return false;
	}

	@Override
	public boolean isLightful() {
		int f = MapList.getFloor();
		return f < 4;
	}

	@Override
	public boolean isWarningEnemy() {
		return false;
	}

}
