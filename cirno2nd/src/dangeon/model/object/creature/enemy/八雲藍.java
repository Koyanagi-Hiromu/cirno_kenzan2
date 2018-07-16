package dangeon.model.object.creature.enemy;

import java.awt.Point;

import dangeon.model.object.creature.player.Player;

public class 八雲藍 extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private int rolling_attack_task = 0, rolling_frame = -1;

	private int pre_lv;

	public 八雲藍(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		for (int i = 0; i <= LV; i++) {
			Player.me.normalAttack();
		}
		return true;
	}

	private boolean reCheck() {
		if (pre_lv == LV) {
			if (Player.me.getHP() > 0 && isSkillActive()) {
				return attack_possible();
			}
		}
		return false;
	}

	@Override
	protected boolean specialAttack() {
		if (!attack_possible()) {
			return false;
		}
		direction = converDirection(Player.me.getMassPoint());

		rolling_attack_task = LV + 1;
		rolling_frame = 0;
		pre_lv = this.LV;
		attack();
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (attack_possible()) {
			return true;
		}
		return false;
	}

	@Override
	protected void upDate_NormalAttack() {
		rolling_frame++;
		super.upDate_NormalAttack();
		attack_No = rolling_frame / 2 % 4;
		if (attaking_frame == -1) {
			rolling_attack_task--;
			if (rolling_attack_task > 0) {
				Player.me.endDamaging();
				if (reCheck())
					attack();
			} else {
				rolling_frame = -1;
			}
		}
	}

}
