package dangeon.model.object.artifact.device;

import java.awt.Point;

import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.Base_Creature;

public abstract class Base_Device extends Base_Artifact {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected Base_Device(Point p, String name, int level,
			int composition_number,
			dangeon.model.object.artifact.Base_Artifact.ITEM_CASE item_case,
			GROW_RATE grow_rate, boolean mobile) {
		super(p, name, 0, item_case, mobile);
		visible = true;
	}

	@Override
	protected final int enchantDefence(boolean b, Base_Creature creature,
			int damage) {
		return 0;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return null;
	}

	@Override
	public boolean isDevice() {
		return true;
	}

	@Override
	public final int itemEnchantPower(STATUS status) {
		return 0;
	}

	@Override
	public final void itemHit(Base_Creature c, Base_Creature c2) {
		ItemFall.itemFall(this);
		// MapList.addArtifact(this);
	}

	@Override
	public boolean itemUse() {
		walkOnAction();
		return false;
	}

	@Override
	public void itemUseThis() {
		walkOnAction();
	}

}
