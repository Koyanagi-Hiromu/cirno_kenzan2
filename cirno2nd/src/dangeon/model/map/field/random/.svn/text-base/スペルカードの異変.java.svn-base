package dangeon.model.map.field.random;

import java.util.ArrayList;

import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.artifact.item.pot.カード合成の壷;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public class スペルカードの異変 extends Base_Map_Random {
	private static final long serialVersionUID = 1L;
	public static final スペルカードの異変 ME = new スペルカードの異変();

	protected スペルカードの異変() {
		super();
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(3) + 3;
	}

	@Override
	public ArrayList<Base_Artifact> firstItems() {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		list.add(new 大きなおにぎり(Player.me.getMassPoint()));
		list.add(new カード合成の壷(Player.me.getMassPoint()));
		return list;
	}

	@Override
	public String getClassName() {
		return "スペルカードの異変";
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

}
