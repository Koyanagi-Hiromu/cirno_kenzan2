package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.八坂神奈子のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;

public class 印柱 extends EnchantSpecial {
	public static void effect(Base_Creature c) {
		if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.オンバシラ)) {
			if (!Player.me.attackWalkableCheck(Player.me.getMassPoint(),
					Player.me.getDirection())) {
				return;
			}

			int parcent = SetEnchantCard.isSetCard(八坂神奈子のカード.class) ? 50 : 25;
			if (parcentCheck(parcent, ENCHANT_SIMBOL.オンバシラ)) {
				Damage.normalAttack(Player.me, c);
			}
		}
	}
}
