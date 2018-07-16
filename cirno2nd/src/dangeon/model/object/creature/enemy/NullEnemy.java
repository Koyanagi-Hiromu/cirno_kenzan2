package dangeon.model.object.creature.enemy;

import java.awt.Point;

public class NullEnemy extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public NullEnemy(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

}
