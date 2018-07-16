package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.res.SE;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.古明地こいしのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Player;
import dangeon.view.detail.MiniMap;

public class 印無 extends EnchantSpecial {
	static ENCHANT_SIMBOL sim = ENCHANT_SIMBOL.無;

	public static boolean effect() {
		if (enchantSimbolAllCheck(CASE.DEF, sim)) {
			if (parcent()) {
				SE.LIGHT_ON.play();
				Scene_Action.getMe().tellRestStop();
				Player.me.setCondition(CONDITION.透明, 4);
				MiniMap.openTiles();
				return true;
			}
		}
		return false;
	}

	private static boolean parcent() {
		if (parcentCheck(SetEnchantCard.isSetCard(古明地こいしのカード.class) ? 6 : 3,
				sim)) {
			return true;
		}
		return false;
	}
}
