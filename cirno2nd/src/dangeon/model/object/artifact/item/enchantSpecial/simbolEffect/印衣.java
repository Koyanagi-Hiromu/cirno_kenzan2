package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.永江衣玖のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Player;

public class 印衣 extends EnchantSpecial {

	public static boolean effect(int damage) {
		if (!enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.衣)) {
			return false;
		}
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.電)) {
			damage += 10;
		}
		damage = SetEnchantCard.isSetCard(永江衣玖のカード.class) ? damage * 2 : damage;
		Player.me.chengeHP(null, null, damage);
		Message.set(Player.me.getColoredName(), "は雷を吸収した");
		return true;
	}
}
