package dangeon.model.map.field.random;

import main.res.Image_Dungeon_Name;
import main.res.Image_MapTip;
import dangeon.util.R;

public class 博麗中結界 extends Base_Map_Random {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final 博麗中結界 ME = new 博麗中結界();

	private 博麗中結界() {
		super();
	}

	@Override
	public int defaultItemNumber() {
		return new R().nextInt(2) + 2;
	}

	@Override
	public String getClassName() {
		return "博麗中結界";
	}

	@Override
	protected Image_Dungeon_Name getImageDungeonName() {
		return Image_Dungeon_Name.hakureinaka_kekkai;
	}

	@Override
	public Image_MapTip getMapTip() {
		return Image_MapTip.地霊殿;
	}

	@Override
	public int getMaxFloor() {
		return 10;
	}

	@Override
	public int getSaisenParcent() {
		return 50;
	}

	@Override
	public int getTrapDefaultValue() {
		// 50F相当のトラップ数
		return getTrapDefaultValue(50);
	}
}
