package dangeon.model.object.artifact.item.bullet;

import main.res.BulletImage;
import main.res.SE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.util.ThunderDamage;

public class 雷弾 extends Base_Bullet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	final Base_Creature C;

	final int damage;

	public 雷弾(Base_Creature c, int _d) {
		super(c, "雷弾");
		C = c;
		damage = _d;
		SE.THUNDER.play();
	}

	@Override
	public BulletImage getBulletImage() {
		return BulletImage.thunder_shot;
	}

	@Override
	public int getShadow() {
		return 0;
	}

	@Override
	public void itemHit(final Base_Creature c, final Base_Creature c2) {
		ThunderDamage.thunderDamage(this, c2, c, damage);
	}
}
