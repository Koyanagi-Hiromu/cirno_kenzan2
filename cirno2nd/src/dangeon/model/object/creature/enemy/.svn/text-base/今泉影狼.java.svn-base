package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.view.constant.MAP;

public class 今泉影狼 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private boolean flag_waoon = false;

	public 今泉影狼(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		return false;
	}

	@Override
	protected void init4() {
		setCondition(CONDITION.透明, 0);
	}

	@Override
	protected boolean specialAttack() {
		if (!specialCheck()) {
			return false;
		}
		flag_waoon = true;
		SE.WAOON.play();
		final Base_Enemy c = this;
		Point point = new Point(Player.me.getMassPoint().x + direction.X,
				Player.me.getMassPoint().y + direction.Y);
		c.setMassPoint_WalkLike(point, 2);
		startAttack(new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			private int effect(int lv) {
				switch (lv) {
				case 2:
					return 10;
				case 3:
				case 4:
					return 25;
				}
				return 0;
			}

			@Override
			public void work() {
				Damage.normalAttack(c, Player.me);
				if (c.getLV() > 1) {
					SE.STATUS_SHADOW.play();
					Player.me.setCondition(CONDITION.影縫い, effect(c.getLV()));
				}
			}
		});
		return true;
	}

	@Override
	protected boolean specialCheck() {
		Point point = new Point(Player.me.getMassPoint().x + direction.X,
				Player.me.getMassPoint().y + direction.Y);
		return attack_possible() && MassCreater.isWalkableFor(this, point);
	}

	@Override
	public void upDate() {
		super.upDate();
		if (flag_waoon) {
			if (isMoving())
				upDate(MAP.MoveSpeed);
			else
				flag_waoon = false;
		}
	}
}
