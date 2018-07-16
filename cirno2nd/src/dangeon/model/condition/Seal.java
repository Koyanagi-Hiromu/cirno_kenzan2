package dangeon.model.condition;

import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

public class Seal {
	public static void conditionEffect(Base_Creature creature) {
		if (creature == Player.me) {
			return;
		} else {

		}
	}
}
