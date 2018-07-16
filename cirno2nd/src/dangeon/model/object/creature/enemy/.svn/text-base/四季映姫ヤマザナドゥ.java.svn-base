package dangeon.model.object.creature.enemy;

import java.awt.Point;

import dangeon.controller.ThrowingItem;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;

public class 四季映姫ヤマザナドゥ extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 四季映姫ヤマザナドゥ(Point p, int Lv) {
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
	protected void init1() {
		setHalfWalk();
	}

	@Override
	protected void init2() {
	}

	@Override
	protected void init3() {
		set2timesWalk();
	}

	@Override
	protected void init4() {
		set3timesWalk();
	}

	@Override
	public boolean isResistantToGrassWave() {
		return LV == 4 && !conditionCheck(CONDITION.封印)
				&& !conditionCheck(CONDITION.ええんじゃないか);
	}

	@Override
	public boolean reflection(ThrowingItem ti) {
		if (LV > 1 && ti.HOW == HowToThrow.MAGIC) {
			Message.set("浄玻璃の鏡で魔法弾を反射した");
			return true;
		} else if (LV >= 3) {
			Message.set("浄玻璃の鏡で投擲物を反射した");
			return true;
		}
		return super.reflection(ti);
	}

	@Override
	protected void saveKO() {
		super.saveKO();
		if (LV == 4) {
			Medal.絶対許ザナデゥをやっつけた.addCount();
		}
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
}
