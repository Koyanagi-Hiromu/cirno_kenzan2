package dangeon.model.object.creature.enemy;

import java.awt.Point;

public class 雛形 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 雛形(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

}
