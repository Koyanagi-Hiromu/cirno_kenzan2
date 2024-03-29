package dangeon.model.condition;

import dangeon.controller.TurnSystemController;
import dangeon.model.map.MapList;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

public class Paralyze {
	public static void conditionEffect(Base_Creature creature) {
		if (creature == Player.me) {
			TurnSystemController.callMeToStartEnemyTurn();
		} else {
			MapList.getEnemy(creature.getMassPoint()).enemy_actioned = true;
		}
	}

}
