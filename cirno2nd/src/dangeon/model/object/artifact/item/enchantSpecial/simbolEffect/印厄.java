package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.鍵山雛のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 印厄 extends EnchantSpecial {
	public static void effect(CONDITION con) {
		if (!enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.厄)) {
			return;
		}
		if (con.isGood()) {
			return;
		}
		if (SetEnchantCard.isSetCard(鍵山雛のカード.class)) {
			for (Base_Enemy em : MapInSelect.getListRoomInEnemy()) {
				em.setCondition(con, 0);
			}
		} else {
			for (Base_Creature c : MapInSelect
					.getListAroundInCreature(Player.me.getMassPoint())) {
				c.setCondition(con, 0);
			}
		}
	}
}
