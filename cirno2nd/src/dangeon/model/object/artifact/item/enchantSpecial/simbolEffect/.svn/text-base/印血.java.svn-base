package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.レミリアのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Player;

public class 印血 extends EnchantSpecial {
	public static void effect(int damage) {
		int parcent = SetEnchantCard.isSetCard(レミリアのカード.class) ? 50 : 25;
		if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.血液)) {
			if (parcentCheck(parcent, ENCHANT_SIMBOL.血液)) {
				Player.me.chengeHP(null, null, damage / 2);
				Message.set("ズキューーーーーン！！！");
			}
		}
	}
}
