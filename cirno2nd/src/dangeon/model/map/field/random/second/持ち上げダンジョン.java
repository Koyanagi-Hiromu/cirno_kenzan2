package dangeon.model.map.field.random.second;

import java.util.ArrayList;

import main.res.BGM;
import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.first.special.Scene_Menu_First_Bring;
import dangeon.latest.system.KeyHolder;
import dangeon.model.map.MapList;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public class 持ち上げダンジョン extends Base_Map_Random {
	private static final long serialVersionUID = 1L;
	public static final 持ち上げダンジョン ME = new 持ち上げダンジョン();

	protected 持ち上げダンジョン() {
		super();
	}

	@Override
	public Base_Scene createOwnMenuScene(KeyHolder kH, Base_View cURRENT_VIEW) {
		return new Scene_Menu_First_Bring(kH, cURRENT_VIEW);
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(3) + 3;
	}

	@Override
	public ArrayList<Base_Artifact> firstItems() {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		list.add(new 大きなおにぎり(Player.me.getMassPoint()));
		return list;
	}

	@Override
	public BGM getBGM() {
		return BGM.to_shin6b;
	}

	@Override
	public String getClassName() {
		return "持ち上げダンジョン";
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

}
