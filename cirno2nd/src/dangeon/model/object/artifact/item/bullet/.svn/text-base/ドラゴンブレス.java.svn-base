package dangeon.model.object.artifact.item.bullet;

import main.res.BulletImage;
import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印炎;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.view.anime.FireEffect;
import dangeon.view.detail.MainMap;

public class ドラゴンブレス extends Base_Bullet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	final Base_Creature C;

	final int damage;

	public ドラゴンブレス(Base_Creature c, int _d) {
		super(c, "火球");
		C = c;
		damage = _d;
		SE.ATTACK_FIRE.play();
	}

	@Override
	public BulletImage getBulletImage() {
		return BulletImage.fireball;
	}

	@Override
	public int getShadow() {
		return 0;
	}

	@Override
	public void itemHit(final Base_Creature c, Base_Creature c2) {
		MainMap.addEffect(new FireEffect(c, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
			}

			@Override
			protected void work(int frame) {
				if (frame == 6) {
					int dam = damage;
					dam = c.damagedWithFire(dam);
					if (dam > 0)
						if (c.equals(Player.me)) {
							Damage.damage(C, "炎に焼かれ", null, C, c,
									印炎.effect(dam));
						} else {
							Damage.damage(C, "炎に焼かれ", null, C, c, dam);
						}
				}
			}
		}), true);
	}
}
