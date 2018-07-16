package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.res.SE;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Player;

public class 写印 extends EnchantSpecial {
	static ENCHANT_SIMBOL sim = ENCHANT_SIMBOL.写;

	public static boolean effect() {
		if (Player.me.conditionCheck(CONDITION.透視)) {
			return false;
		} else {
			if (enchantSimbolAllCheck(sim)) {
				if (parcent()) {
					Message.set("フロアの敵とアイテムを念写した");
					SE.LIGHT_ON.play();
					Scene_Action.getMe().tellRestStop();
					Player.me.setCondition(CONDITION.透視, 40);
					return true;
				}
			}
			return false;
		}
	}

	private static boolean parcent() {
		if (parcentCheck(1, sim)) {
			return true;
		}
		return false;
	}
}
