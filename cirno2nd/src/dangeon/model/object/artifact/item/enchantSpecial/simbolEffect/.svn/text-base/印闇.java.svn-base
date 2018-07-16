package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.ルーミアのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Player;

public class 印闇 extends EnchantSpecial {
	private static int plus = 40;

	public static int effect() {
		if (!MassCreater.getMass(Player.me.getMassPoint()).ROAD) {
			return 0;
		}
		if (enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.闇)) {
			return SetEnchantCard.isSetCard(ルーミアのカード.class) ? plus * 2 : plus;
		}
		return 0;
	}
}
