package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.res.SE;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;

public class 印冷 extends EnchantSpecial {

	public static boolean effect() {
		if (Player.me.conditionCheck(CONDITION.回復)) {
			return false;
		} else {
			if (enchantSimbolAllCheck(ENCHANT_SIMBOL.冷)) {
				if (parcent()) {
					int freeze_count = 0;
					if (enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.冷)) {
						for (Base_Artifact a : Belongings.getListItems()) {
							if (a.isFrozen()) {
								freeze_count++;
							}
						}
					}
					freeze_count *= 2;
					freeze_count /= 3;

					SE.LIGHT_ON.play();
					Scene_Action.getMe().tellRestStop();
					Player.me.setCondition(CONDITION.回復, freeze_count + 1);
					return true;
				}
			}
			return false;
		}

	}

	private static boolean parcent() {
		if (parcentCheck(1, ENCHANT_SIMBOL.冷)) {
			return true;
		}
		return false;
	}

}
