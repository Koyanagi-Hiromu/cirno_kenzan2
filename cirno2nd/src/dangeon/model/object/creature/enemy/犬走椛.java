package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.grass.すばやさ草;
import dangeon.model.object.creature.player.Player;

public class 犬走椛 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 犬走椛(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		if (isSkillActive() && conditionCheck(CONDITION.安心)
				&& conditionCheck(CONDITION.透視)
				&& !Player.me.conditionCheck(CONDITION.透明)) {
			for (int i = -2; i <= 2; i++) {
				for (int j = -2; j <= 2; j++) {
					Point p = Player.me.getMassPoint().getLocation();
					p.translate(i, j);
					if (p.equals(getMassPoint())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Player.me.setCondition(CONDITION.透視, 0);
		return true;
	}

	@Override
	protected Point getAttackPoint() {
		return new Point(screen_point.x + attack_delt_point.x / 2,
				screen_point.y + attack_delt_point.y / 2);
	}

	@Override
	protected Base_Artifact getDropItem() {
		return new すばやさ草(mass_point.getLocation());
	}

	@Override
	public int getFootDeltY() {
		// int h = 48;
		// if (isAttacking()) {
		// h *= 2;
		// }
		// return -h + MAP.Y_OFFSET;
		return 0;
	}

	@Override
	public Image getImage() {
		if (conditionCheck(CONDITION.おにぎり))
			return Image_Artifact.FOOD.getImage(0);
		else if (isAttacking()) {
			return getATKImage(LV, attacking_direction, attack_No);
		} else if (getAnimation() != null) {
			return IM.getSPImage(LV, direction, getAnimation().getComa());
		} else if (flag_damaging) {
			return IM.getDamImage(LV, direction);
		}
		if (specialMove()) {
			int k = IM.getKoma(getMoveAnimationSpeed());
			if (k != 0)
				k -= 1;
			int LV = this.LV;
			if (LV == 4)
				LV = 0;
			return IM.WALK[LV][direction.getRotate()][k];
		} else {
			return IM.getWalkImage(LV, direction, getMoveAnimationSpeed());
		}
	}

	@Override
	protected CONDITION getInitCondition() {
		setCondition(CONDITION.透視, 0);
		return CONDITION.安心;
	}

	@Override
	protected int itemDropParcent() {
		return super.itemDropParcent();
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

	@Override
	protected boolean specialMove() {
		if (!super.specialMove()) {
			return check();
		}
		return true;
	}

}
