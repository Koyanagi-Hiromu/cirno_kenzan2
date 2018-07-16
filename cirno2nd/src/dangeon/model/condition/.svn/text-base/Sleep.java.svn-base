package dangeon.model.condition;

import dangeon.controller.TurnSystemController;
import dangeon.model.map.MapList;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

public class Sleep {

	public static void conditionEffect(Base_Creature creature) {
		if (creature == Player.me) {
			TurnSystemController.callMeToStartEnemyTurn();
		} else {
			MapList.getEnemy(creature.getMassPoint()).enemy_actioned = true;
		}
	}

	public static void 暗闇(Base_Creature creature) {
		if (creature == Player.me) {
			TurnSystemController.callMeToStartEnemyTurn();
		} else {
			MapList.getEnemy(creature.getMassPoint()).enemy_actioned = true;
		}
	}
}
