package dangeon.model.object.creature.enemy;

import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.Battle.Battle;
import dangeon.util.Damage;

public class 星熊勇儀 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 星熊勇儀(Point p, int Lv) {
		super(p, Lv);
	}

	private void effect() {
		final Base_Enemy e = this;
		startAttack(new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Damage.enemyCriticalAttack(e);
			}
		});
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Battle.setCriticalFlag();
		Player.me.normalAttack();
		return true;
	}

	@Override
	protected boolean specialAttack() {
		if (attack_possible()) {
			effect();
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (attack_possible()) {
			direction = converDirection(Player.me.getMassPoint());
			if (isSpecialParcent()) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void upDate_NormalAttack() {
		super.upDate_NormalAttack();
		attack_No = attaking_frame / 3;
		if (attack_No > 4)
			attack_No = 4;
	}

}
