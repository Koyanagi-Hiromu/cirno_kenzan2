package dangeon.model.object.creature.enemy;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;

public class 剣 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 剣(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public void action() {
	}

	@Override
	public void drawCreature(Graphics2D g, Point p) {
		super.drawCreature(g, p);
	}

	@Override
	public Image getATKImage(int LV, DIRECTION attacking_direction,
			int attack_No) {
		return super.getATKImage(LV, attacking_direction, attack_No);
		// return IM.getATKImage(0, direction, attack_No + 1);
	}

	@Override
	protected Point getAttackPoint() {
		return screen_point;
	}

	@Override
	public Image getImage() {
		return getATKImage(LV, attacking_direction, attack_No);
	}

	public void setAttackingFrame(int attaking_frame) {
		this.attaking_frame = attaking_frame;
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

	@Override
	public void upDate_NormalAttack() {
		if (normal_attack_phase == null)
			normal_attack_phase = AttackPhase.GO;
		super.upDate_NormalAttack();
		switch (attaking_frame) {
		case 2:
			SE.ATTACK_SWORD.play();
		case 0:
		case 1:
		case 3:
			attack_No = 0;
			break;
		case 4:
			attack_No = 1;
			break;
		case 5:
			attack_No = 2;
			break;
		case 6:
			attack_No = 3;
			break;
		case 7:
		case 8:
		default:
			attack_No = 4;
			break;
		}
	}

}
