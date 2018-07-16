package dangeon.model.object.artifact.item.bullet;

import main.res.BulletImage;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;

public class 御柱 extends Base_Bullet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 御柱(Base_Creature c) {
		super(c, "御柱");
	}

	@Override
	public BulletImage getBulletImage() {
		return BulletImage.御柱;
	}

	@Override
	public int getShadow() {
		return 0;
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		if (c2 instanceof Player) {
			if (c != c2) {
				Damage.PtoE_ArrowAttack(this, c2, c, 12);
			} else {
				Damage.PtoE_ArrowAttack(this, c2, c, -9999);
			}
		} else {
			Damage.normalAttack(c2, c);
		}
	}
}
