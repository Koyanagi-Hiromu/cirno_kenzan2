package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.util.DIRECTION;

public class キスメ extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public キスメ(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public void action() {
		enemy_actioned = true;
	}

	@Override
	public Image getATKImage(int LV, DIRECTION attacking_direction,
			int attack_No) {
		return IM.getATKImage(0, direction, 0);
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

}
