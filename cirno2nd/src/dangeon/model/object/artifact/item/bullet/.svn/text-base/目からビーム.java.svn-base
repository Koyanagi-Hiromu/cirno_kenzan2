package dangeon.model.object.artifact.item.bullet;

import main.res.BulletImage;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.util.Damage;

public class 目からビーム extends Base_Bullet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 目からビーム(Base_Creature c) {
		super(c, "ビーム");
	}

	@Override
	public BulletImage getBulletImage() {
		return BulletImage.eye_beam;
	}

	@Override
	public int getShadow() {
		return 0;
	}

	@Override
	public void itemHit(Base_Creature deffece, Base_Creature attack) {
		if (attack instanceof Base_Enemy) {
			Damage.EtoPandE_ArrowAttack(attack,
					attack.getName().concat("の目から出たビームに打ち抜かれた"), attack,
					deffece);
		} else {
			Damage.PtoE_ArrowAttack(this, attack, deffece, attack.getSTR());
		}
	}
}
