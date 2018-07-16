package dangeon.model.condition;

import dangeon.controller.TurnSystemController;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

public class Mouth {
	public static void conditionEffect(Base_Creature creature) {
		if (creature == Player.me) {
			TurnSystemController.callMeToStartEnemyTurn();
		} else
			return;
	}

}
