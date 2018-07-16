package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Player;

public class 心印 extends EnchantSpecial {
	static ENCHANT_SIMBOL sim = ENCHANT_SIMBOL.心;

	public static boolean effect() {
		if (Player.me.conditionCheck(CONDITION.絶対回避)) {
			return false;
		} else {
			if (enchantSimbolAllCheck(sim)) {
				if (parcent()) {
					SE.LIGHT_ON.play();
					Message.set("相手の心が読めるようになった");
					Player.me.setCondition(CONDITION.絶対回避, 0);
					return true;
				}
			}
			return false;
		}
	}

	private static boolean parcent() {
		if (parcentCheck(2, sim)) {
			return true;
		}
		return false;
	}
}
