package dangeon.model.object.creature.enemy;

import java.awt.Point;

public class 二ツ岩マミゾウ extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 二ツ岩マミゾウ(Point p, int LV) {
		super(p, LV);
	}

	public 二ツ岩マミゾウ(Point p, String name, int LV) {
		super(p, name, LV);
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

}
