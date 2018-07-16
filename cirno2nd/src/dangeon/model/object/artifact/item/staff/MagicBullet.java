package dangeon.model.object.artifact.item.staff;

import main.res.BulletImage;
import main.res.SE;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.bullet.Base_Bullet;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

public class MagicBullet extends Base_Bullet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public final Staff STAFF;
	private final Base_Creature CREATURE;

	/**
	 * 主人公による魔法弾
	 * 
	 * @author k
	 * 
	 */
	protected MagicBullet(Staff s) {
		this(s, Player.me);
	}

	protected MagicBullet(Staff s, Base_Creature c) {
		super(c, "魔法弾");
		STAFF = s;
		CREATURE = c;
	}

	@Override
	public BulletImage getBulletImage() {
		return BulletImage.MagicBullet;
	}

	@Override
	public int getShadow() {
		return 0;
	}

	@Override
	public boolean isHitToItem() {
		return STAFF.isMagicHitToItem();
	}

	@Override
	public boolean isNeglectiveForWall() {
		return STAFF.isNeglectiveForWall_Magic();
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		STAFF.direction = direction;
		STAFF.effectHitCheck(c, CREATURE);
	}

	@Override
	public void itemHitCheck(boolean ento, Base_Creature c, Base_Artifact a) {
		STAFF.itemHitCheck(ento, c, a);
	}

	@Override
	public void itemThrow(Base_Creature c) {
		this.itemThrow(c, HowToThrow.MAGIC);
	}

	@Override
	public void itemThrow(Base_Creature c, HowToThrow how) {
		SE.SYSTEM_MAGIC.play();
		if (c instanceof Player) {
			Player.me.setThrowing(this, Player.me.getDirection());
		}
		super.itemThrow(c, how, Math.max(MassCreater.WIDTH, MassCreater.HEIGHT));
	}

}
