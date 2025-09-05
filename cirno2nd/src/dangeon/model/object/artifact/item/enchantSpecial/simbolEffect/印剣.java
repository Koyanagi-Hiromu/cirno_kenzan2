package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.controller.ThrowingItem;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.bullet.目からビーム;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.staff.MagicBullet;
import dangeon.model.object.creature.enemy.剣;
import dangeon.model.object.creature.player.Player;
import main.res.SE;
import main.util.DIRECTION;

public class 印剣 extends EnchantSpecial {

	public static boolean effect(ThrowingItem ti) {
		if (!enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.剣)) {
			return false;
		}
		if (DIRECTION.isNeiborOrSame(Player.me.direction.getReverse(), ti.A.direction)) {
			Player.me.setStandEffect(剣.class, ti.A.direction.getReverse());
			SE.ATTACK_SWORD.play();
			if (ti.A instanceof 目からビーム) {
				Message.set("ビームをかき消した");
			} else if (ti.A instanceof MagicBullet) {
				Message.set("魔法弾をかき消した");
			}
			else
				Message.set(ti.A.getColoredName(), "をかき消した");
			return true;
		}
		return false;
	}
}
