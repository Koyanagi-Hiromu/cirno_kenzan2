package dangeon.model.object.creature.enemy;

import java.awt.Point;

import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.grass.すばやさ草;
import dangeon.model.object.creature.player.Player;

public class 射命丸文 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 射命丸文(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Player.me.setCondition(CONDITION.倍速, 0);
		return true;
	}

	@Override
	protected Base_Artifact getDropItem() {
		return new すばやさ草(mass_point.getLocation());
	}

	@Override
	protected void init1() {
		set2timesWalk();
	}

	@Override
	protected void init2() {
		set2timesWalk();
	}

	@Override
	protected void init3() {
		set3timesWalk();
	}

	@Override
	protected void init4() {
		set3timesWalk();
	}

	@Override
	protected int itemDropParcent() {
		return super.itemDropParcent();
	}

	@Override
	protected void setStateChengeByLevelUp() {
	}

	@Override
	protected boolean specialAttack() {
		if (attack_possible()) {
			if (LV == 1) {
				quick_one_attack = true;
				// } else if (LV == 4) {
				// attack();
			}
			direction = converDirection(Player.me.getMassPoint());
			attack();
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (attack_possible()) {
			return true;
		}
		return false;
	}

}
