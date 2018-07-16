package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MassCreater;
import dangeon.util.MapInSelect;
import dangeon.view.anime.CirnoIceEffect;
import dangeon.view.detail.MainMap;

/**
 * マムルの位置づけ
 * 
 * @author Administrator
 * 
 */
public class チルノ extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public チルノ(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		SE.ICE.play();
		MassCreater.frozenWater();
		MainMap.addEffect(new CirnoIceEffect(), true);
		MapInSelect.damageIcy_toAllEnemies(null, null, 20 * LV);
		return true;
	}

	@Override
	public Image getImage() {
		int LV = this.LV;
		if (LV == 4)
			LV = 2;
		if (isAttacking()) {
			return getATKImage(LV, attacking_direction, attack_No);
		} else if (flag_damaging) {
			return IM.getDamImage(LV, direction);
		}
		return IM.getWalkImage(LV, direction, getMoveAnimationSpeed());
	}

	@Override
	protected void init4() {
		setCondition(CONDITION.炎上, 0);
	}

	//
	// @Override
	// protected boolean specialMoveCheck() {
	// return specialMoveParcent();
	// }
	//
	// @Override
	// protected boolean specialMove() {
	// if (LV == 3) {
	// return false;
	// }
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
