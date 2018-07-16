package dangeon.model.map.field.town;

import main.res.Image_Dungeon_Name;
import dangeon.model.map.field.Base_Map;

public abstract class Base_TownMap extends Base_Map {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected Base_TownMap(String map_name) {
		super("specialMap", map_name);
	}

	@Override
	protected Image_Dungeon_Name getImageDungeonName() {
		return null;
	}

	@Override
	protected boolean isBGMDemandedToPlay() {
		return true;
	}

	@Override
	public boolean isHaraheru() {
		return false;
	}

	@Override
	public boolean isLightful() {
		return true;
	}

	@Override
	public final boolean isMiniMapAvaible() {
		return false;
	}

}
