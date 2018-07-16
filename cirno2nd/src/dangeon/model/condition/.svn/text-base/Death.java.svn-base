package dangeon.model.condition;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;

public class Death {
	public static void effect(Base_Creature c) {
		Message.set("死に誘われた");
		if (c instanceof Player) {
			if (EnchantSpecial.enchantSimbolAllCheck(EnchantSpecial.CASE.DEF,
					ENCHANT_SIMBOL.竹)) {
				Message.set("しかし永遠の前では無意味だった");
				return;
			}
			SE.YUYUKO_SPELL2.play();
			if (c.getGhost() == 1) {
				c.startDamaging();
				c.chengeHP(null, null, -Player.me.getHP() / 2);
			} else if (c.getGhost() == -1) {
				c.chengeHP(null, null, 999);
			} else {
				c.startDamaging();
				Damage.damageNoMessage(null, null, null, Player.me, 1);
			}
		} else {
			SE.YUYUKO_SPELL2.play();
			if (c.getGhost() == 1) {
				c.startDamaging();
				Damage.damageNoMessage(null, null, Player.me, c, 999);
			} else if (c.getGhost() == -1) {
				c.chengeHP(null, null, 999);
			} else {
				c.startDamaging();
				Damage.damageNoMessage(null, null, Player.me, c, 1);
			}
		}
	}
}
