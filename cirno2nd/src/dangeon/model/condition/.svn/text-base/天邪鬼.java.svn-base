package dangeon.model.condition;

import dangeon.model.object.creature.Base_Creature;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;

public class 天邪鬼 {
	public static void effect(Base_Creature c) {
		// DIRECTION d = c.getDirection();
		Base_Creature _c = MapInSelect.getFrontEnemyFromCreature(c);
		// Point p = new Point(c.getMassPoint().x - c.getDirection().X,
		// c.getMassPoint().y - c.getDirection().Y);
		if (_c != null) {
			Damage.normalAttack(c, _c);
		}
	}
}
