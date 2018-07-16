package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.TurnSystemController;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.arrow.毒ナイフ;
import dangeon.model.object.artifact.item.arrow.鉄の矢;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Player;

public class 十六夜咲夜 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private int count = 0;

	public 十六夜咲夜(Point p, int Lv) {
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
		startAttack(new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				timeStop();
			}
		});
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Arrow a;
		if (LV == 2) {
			a = new 毒ナイフ(getMassPoint(), false);
			// } else if (LV == 4) {
			// a = new 鉄の矢(getMassPoint(), false);
		} else {
			a = new 鉄の矢(getMassPoint(), false);
		}
		Message.set(a.getName(), "を投げた");
		a.itemThrow(Player.me);
		return true;
	}

	@Override
	protected Base_Artifact getDropItem() {
		int rest = 10 - count;
		if (rest > 0) {
			Arrow a;
			if (LV == 2) {
				a = new 毒ナイフ(getMassPoint(), true);
			} else {
				a = new 鉄の矢(getMassPoint(), true);
			}
			return a.setArrowRest(rest);
		} else
			return super.getDropItem();
	}

	@Override
	public boolean isInValidOnAttack() {
		if (LV >= 3) {
			if (TurnSystemController.time_stop || !isSkillActive()) {
				Medal.ザ・咲夜に通常攻撃を当てた.addCount();
				return false;
			}
			DIRECTION d = DIRECTION.getDirection(this, Player.me);
			Point p = d.getFrontPoint(Player.me.getMassPoint().getLocation());
			if (MassCreater.isWalkableFor(this, p)) {
				SE.TIME_STOP.play();
				if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.竹)) {
					this.setMassPoint_WalkLike(p, 2);
					Message.set(getColoredName(), "の時を止め返した");
					TurnSystemController.setTimeStop(1);
					return true;
				} else {
					Message.set(getColoredName(), "は時を止めて背後に回った");
					setMassPoint(p);
					direction = d.getReverse();
					return true;
				}
			}
			Medal.ザ・咲夜に通常攻撃を当てた.addCount();
			return false;
		} else {
			return false;
		}
	}

	@Override
	protected int itemDropParcent() {
		return LV == 1 && count < 10 ? 10000 : 500;
	}

	@Override
	protected boolean specialAttack() {
		effect();
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

	public boolean throwing() {
		Arrow a;
		if (LV == 2) {
			a = new 毒ナイフ(getMassPoint(), false);
		} else {
			a = new 鉄の矢(getMassPoint(), false);
		}
		count++;
		Message.set(getColoredName().concat("は"), a.getName().concat("を投げた"));
		a.itemThrow(this);
		if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.竹)) {
			Message.set(getColoredName(), "の時を止め返した");
			TurnSystemController.setTimeStop(2);
		}
		return true;
	}

	private void timeStop() {
		if (playerIsInNeiver() || LV >= 3) {
			Point p = getBackPoint();
			if (MassCreater.isWalkableFor(this, p)
					&& MassCreater.getRoom(p) != null) {
				SE.TIME_STOP.play();
				Message.set(getColoredName(), "は時を止めた");
				setMassPoint(p);
				throwing();
				return;
			}
		}
		throwing();
	}

}
