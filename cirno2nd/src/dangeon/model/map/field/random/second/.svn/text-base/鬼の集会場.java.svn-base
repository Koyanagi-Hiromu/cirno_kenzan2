package dangeon.model.map.field.random.second;

import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import dangeon.model.map.MapList;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.creature.player.Player;

public class 鬼の集会場 extends Base_Map_Random {
	private static final long serialVersionUID = 1L;
	public static final 鬼の集会場 ME = new 鬼の集会場();

	protected 鬼の集会場() {
		super();
	}

	@Override
	public int defaultItemNumber() {
		return 0;
	}

	@Override
	public ArrayList<Base_Artifact> firstItems() {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		list.add(new 大きなおにぎり(Player.me.getMassPoint()));
		return list;
	}

	@Override
	public BGM getBGM() {
		return BGM.kanpyo_ch_bbcc;
	}

	@Override
	public String getClassName() {
		return "鬼の集会場";
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
		return 10;
	}

	@Override
	public int getSaisenParcent() {
		return 0;
	}

	@Override
	public int getTrapDefaultValue() {
		return getTrapDefaultValue(MapList.getFloor());
	}

	@Override
	public boolean isItemDrop() {
		return false;
	}

}
