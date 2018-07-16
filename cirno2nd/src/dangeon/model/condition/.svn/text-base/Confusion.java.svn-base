package dangeon.model.condition;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public class Confusion {

	public static void conditionEffect(Base_Creature creature) {
		if (creature instanceof Player) {
			((Player) creature).setDirection(DIRECTION.getRandom8Direction());
		} else {

		}
	}

	public static void enemyRandomMove(Base_Enemy e) {
		DIRECTION dir = DIRECTION.getRandom8Direction();
		if (e.moveCheck(dir, false)) {
			e.movePoint(new Point(e.getMassPoint().x + dir.X,
					e.getMassPoint().y + dir.Y), MassCreater.getMass(e
					.getMassPoint()).ROOM, false);
		}
	}

	public static boolean movePossible(Base_Enemy e) {
		return new R().nextInt(8) > 1;
	}

	public static void randomAttack(Base_Enemy e) {
		e.attack();
	}
}
