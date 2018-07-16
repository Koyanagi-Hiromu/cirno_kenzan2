package dangeon.model.object.artifact.item.bullet;

import java.awt.Image;

import main.res.BulletImage;
import main.util.DIRECTION;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.creature.Base_Creature;

public abstract class Base_Bullet extends Base_Item {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int anime = 0;
	protected final Base_Creature LANCHER;

	protected Base_Bullet(Base_Creature c, String name) {
		super(c.getMassPoint().getLocation(), name, 0, null);
		LANCHER = c;
		this.direction = c.direction;
	}

	@Override
	protected final int enchantDefence(boolean b, Base_Creature creature,
			int damage) {
		return 0;
	}

	public abstract BulletImage getBulletImage();

	@Override
	public final Image getImage(DIRECTION direction) {
		anime++;
		int k = anime / 3 % getBulletImage().ANIME;
		return getBulletImage().getImage(direction, k);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return null;
	}

	@Override
	public boolean isAbleToCurse() {
		return false;
	}

	@Override
	public final int itemEnchantPower(STATUS status) {
		return 0;
	}

	@Override
	public final boolean itemUse() {
		return false;
	}

}
