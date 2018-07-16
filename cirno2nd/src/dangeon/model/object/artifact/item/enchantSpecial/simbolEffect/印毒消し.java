package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.メディスンメランコリーのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Player;

public class 印毒消し extends EnchantSpecial {
	static int heal = 50;

	public static boolean effect() {
		if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.毒)) {
			Message.set("毒を回復に変換した");
			Player.me.chengeHP(null, null, SetEnchantCard
					.isSetCard(メディスンメランコリーのカード.class) ? heal * 2 : heal);
			return true;
		}
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.衰)
				|| EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.消)) {
			Message.set(Player.me.getColoredName(), "に毒は効かなかった");
			return true;
		}
		return false;
	}
}
