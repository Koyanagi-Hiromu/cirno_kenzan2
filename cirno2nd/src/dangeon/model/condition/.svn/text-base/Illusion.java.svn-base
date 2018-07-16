package dangeon.model.condition;

import main.res.SE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.action.SpecialAction;

public class Illusion {
	public static boolean effect(Base_Creature c) {
		if (!c.getConditionList().contains(CONDITION.イリュージョン)) {
			return false;
		}
		SE.WARP.play();
		CONDITION.conditionRecovery(c, CONDITION.イリュージョン);
		SpecialAction.高飛び(c);
		return true;
	}
}
