package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.比那名居天子のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 印緋 extends EnchantSpecial {
	private static final int parcent = 5;

	public static void comboEffect(Base_Creature c) {
		if (SetEnchantCard.isSetCard(比那名居天子のカード.class)) {
			if (parcentCheck(parcent, ENCHANT_SIMBOL.緋)) {
				MapInSelect.転ばし(Player.me, null, c, Player.me.getDirection(),
						10, 15);
			}
		}
	}

	public static boolean effect() {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.緋)) {
			return true;
		}
		return false;
	}
}
