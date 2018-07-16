package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.res.SE;
import main.util.FrameShaker;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;

public class 印殺 extends EnchantSpecial {
	static int effect = 13;

	public static boolean attackEffect(Base_Creature creature) {
		if (!enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.殺)) {
			return false;
		}
		if (parcentCheck(effect, ENCHANT_SIMBOL.殺)) {
			FrameShaker.doneSoftly();
			SE.DAMAGED_CRITICAL.play();
			return true;
		} else {
			return false;
		}
	}
}
