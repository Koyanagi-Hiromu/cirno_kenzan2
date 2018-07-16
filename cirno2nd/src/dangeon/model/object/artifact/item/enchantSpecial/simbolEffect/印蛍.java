package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.res.SE;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.リグルのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Player;
import dangeon.view.detail.MiniMap;

public class 印蛍 extends EnchantSpecial {

	public static void combo() {
		SE.LIGHT_ON.play();
		Message.set("光がフロアに広がった");
		Player.me.setCondition(CONDITION.蛍, 0);
		MiniMap.openTiles();
	}

	public static boolean effect() {
		if (Player.me.conditionCheck(CONDITION.蛍)) {
			if (Player.me.getConditionTurn(CONDITION.蛍) == 0) {
				return false;
			} else {
				// if (!enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.蛍)) {
				// CONDITION.conditionRecovery(Player.me, CONDITION.蛍);
				// }
				return false;
			}
		} else {
			if (enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.蛍)) {
				if (parcent()) {
					SE.LIGHT_ON.play();
					Message.set("光がフロアに広がった");
					Scene_Action.getMe().tellRestStop();
					Player.me.setCondition(CONDITION.蛍, 50);
					MiniMap.openTiles();
					return true;
				}
			}
			return false;
		}
	}

	private static boolean parcent() {
		if (parcentCheck(SetEnchantCard.isSetCard(リグルのカード.class) ? 2 : 1,
				ENCHANT_SIMBOL.蛍)) {
			return true;
		}
		return false;
	}
}
