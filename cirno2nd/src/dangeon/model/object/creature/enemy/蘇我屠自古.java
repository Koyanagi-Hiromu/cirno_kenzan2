package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.bullet.雷弾;
import dangeon.model.object.creature.player.Player;

public class 蘇我屠自古 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 蘇我屠自古(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		Point player_point = Player.me.getMassPoint();
		if (getMassPoint().x == player_point.x) {
			if (getMassPoint().y > player_point.y) {
				direction = DIRECTION.UP;
			} else {
				direction = DIRECTION.DOWN;
			}
		} else if (getMassPoint().y == player_point.y) {
			if (getMassPoint().x > player_point.x) {
				direction = DIRECTION.LEFT;
			} else {
				direction = DIRECTION.RIGHT;
			}
		} else if (Math.abs(getMassPoint().x - player_point.x) == Math
				.abs(getMassPoint().y - player_point.y)) {
			if (getMassPoint().x > player_point.x) {
				if (getMassPoint().y > player_point.y) {
					direction = DIRECTION.UP_LEFT;
				} else {
					direction = DIRECTION.DOWN_LEFT;
				}
			} else {
				if (getMassPoint().y > player_point.y) {
					direction = DIRECTION.UP_RIGHT;
				} else {
					direction = DIRECTION.DOWN_RIGHT;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	private void effect() {
		Message.set(getColoredName(), "は雷弾を放った");
		new 雷弾(this, getDamage()).itemThrow(this, HowToThrow.BREAK,
				Math.max(MassCreater.WIDTH, MassCreater.HEIGHT));
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		new 雷弾(Player.me, LV * 30).itemThrow(Player.me, HowToThrow.MAGIC,
				Math.max(MassCreater.WIDTH, MassCreater.HEIGHT));
		return true;
	}

	private int getDamage() {
		switch (LV) {
		case 1:
			return 20;
		case 2:
			return 30;
		case 3:
			return 50;
		case 4:
			return 90;
		}
		return LV * 30;
	}

	@Override
	public int getFootDeltY() {
		return -6;
	}

	@Override
	public int getShadowSize100() {
		return 14;
	}

	@Override
	protected boolean specialAttack() {
		startAttack(new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				effect();
			}
		});
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (!player_is_in_sight) {
			return false;
		}
		if (!isSpecialParcent()) {
			return false;
		}
		if (!check()) {
			return false;
		}
		return true;
	}

}
