package dangeon.model.object.creature.action;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印毒消し;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

public class UtilEffect {

	@Deprecated
	public static void poison(Base_Creature c) {

		SE.STATUS_SLOW.play();
		c.setCondition(CONDITION.鈍足, 0);
		if (c instanceof Player) {
			if (印毒消し.effect()) {
				return;
			}
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.衰)) {
				Message.set("リボンのおかげで",
						c.getColoredName().concat("のちからは下がらなかった"));
				return;
			}
		}
		SE.POIZON.play();
		Message.set(c.getColoredName().concat("のちからが下がった"));
		if (c instanceof Player) {
			((Player) c).setSTR((Player.me.getNoEnchantStr() - 1));
		} else {
			c.setSTR(c.getSTR() / 2);
		}
	}
}
