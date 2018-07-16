package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.Battle.Battle;
import dangeon.util.Damage;
import dangeon.view.anime.Special_Wait_Effect;
import dangeon.view.util.WithinOutofScreen;

public class 着物勇儀 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private boolean flag;

	public 着物勇儀(Point p, int Lv) {
		super(p, Lv);
	}

	private void effect() {
		if (flag) {
			final Base_Enemy e = this;
			startAttack(new Task() {
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					if (Player.me.conditionCheck(CONDITION.麻痺)) {
						Damage.enemyCriticalAttack(e);
					} else {
						int d = Damage.enemyCriticalAttack(e);
						if (d != 0 && !Player.me.conditionCheck(CONDITION.麻痺)
								&& isSpecialParcent()) {
							SE.STATUS_SIBIBI.play();
							Message.set(Damage.enemy_color,
									Player.me.getColoredName(),
									"は衝撃によって動けなくなった");
							Player.me.setCondition(CONDITION.麻痺, 2);
						}
					}
				}
			});
		} else {
			flag = true;
			if (LV == 4) {
				Message.set(getColoredName(), "は闘士を高めた");
				SE.STATUS_DODODO.play();
				setCondition(CONDITION.イカリ, 0);
			} else {
				Message.set(getColoredName(), "はちからをためた");
			}
			if (!WithinOutofScreen.isOutside(this)) {
				setAnimation(new Special_Wait_Effect(18, 1));
			}
			powerUP();
		}
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Battle.setCriticalFlag();
		Player.me.normalAttack();
		return true;
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			return getATKImage(LV, direction, 0);
		}
		return super.getImage();
	}

	@Override
	protected boolean specialAttack() {
		// if (spe) {
		effect();
		// }
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (!flag) {
			return player_is_in_sight;
		} else if (attack_possible()) {
			direction = converDirection(Player.me.getMassPoint());
			return true;
		}
		return false;
	}

}
