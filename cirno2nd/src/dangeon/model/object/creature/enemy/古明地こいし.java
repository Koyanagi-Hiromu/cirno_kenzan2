package dangeon.model.object.creature.enemy;

import java.awt.Point;

import dangeon.model.condition.CONDITION;

/**
 * マムルの位置づけ
 * 
 * @author Administrator
 * 
 */
public class 古明地こいし extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 古明地こいし(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	protected CONDITION getInitCondition() {
		return CONDITION.透明;
	}

	@Override
	protected void setStateChengeByLevelUp() {
		switch (LV) {
		case 2:
			setCondition(CONDITION.倍速, 0);
			break;
		case 3:
			setCondition(CONDITION.三倍速, 0);
			break;
		case 4:
			setCondition(CONDITION.三倍速, 0);
			break;
		}
	}

	// @Override
	// protected boolean specialMoveCheck() {
	// return specialMoveParcent();
	// }
	//
	// @Override
	// protected boolean specialMove() {
	// if (!attack_possible()) {
	// if (!specialMoveParcent()) {
	// return false;
	// }
	// }
	// randomMoveDirection(false);
	// return true;
	// }

	@Override
	protected boolean specialCheck() {
		return false;
	}

}
