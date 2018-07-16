package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.ナズーリンのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Player;

public class 印鼠 extends EnchantSpecial {
	public static boolean effect() {
		if (!EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.鼠)) {
			return false;
		}
		if (SetEnchantCard.isSetCard(ナズーリンのカード.class)) {
			return Player.me.getHP() * 15 / 10 < Player.me.getMAX_HP();
		} else
			return Player.me.pinchHp();
	}
}