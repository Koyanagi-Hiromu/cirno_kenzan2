package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.パチュリーのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;

public class 印喘息 extends EnchantSpecial {
	public static void effect() {
		if (!enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.喘息)) {
			return;
		}
		if (parcentCheck(SetEnchantCard.isSetCard(パチュリーのカード.class) ? 1 : 5,
				ENCHANT_SIMBOL.喘息)) {
			int damage = Player.me.getMAX_HP() / 40;
			if (damage <= 1) {
				damage = 1;
			} else if (damage > 6) {
				damage = 6;
			}
			SE.ATTACK_EAT.play();
			Message.set("チルノは咳をした");
			Damage.damageNoMessage(ENCHANT_SIMBOL.喘息, "喘息をこじらせて倒れた", null,
					Player.me, damage);
			// Player.me.damage(null, "喘息によって吹き飛んだ", true, damage);
		}
	}
}
