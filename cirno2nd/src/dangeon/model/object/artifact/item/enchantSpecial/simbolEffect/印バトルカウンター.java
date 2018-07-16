package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.洩矢諏訪子のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;

public class 印バトルカウンター extends EnchantSpecial {
	public static void effect(int damage, Base_Creature c) {
		int parcent = SetEnchantCard.isSetCard(洩矢諏訪子のカード.class) ? 20 : 10;
		if (enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.バトル)) {
			if (parcentCheck(parcent, ENCHANT_SIMBOL.バトル)) {
				SE.BROKEN.play();
				Message.set(c.getColoredName(), "は神に祟られた");
				Damage.damageNoMessage(null, null, Player.me, c, 999);
			}
		}

		// int counter = 0;
		// System.out.println("caefasef");
		// if (enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.バトル)) {
		// counter = SetEnchantCard.isSetCard(洩矢諏訪子のカード.class) ? 60 : 30;
		// }
		// System.out.println("caefasef");
		// counter += SetEnchantCard.isSetCard(幽谷響子のカード.class) ? 30 : 0;
		// if (counter == 0) {
		// return;
		// }
		// System.out.println("caefasef");
		// double counter_damage = damage * counter / 100;
		// int i = (int) counter_damage;
		// if (i <= 1 || c instanceof 黄金ゆっくり) {
		// i = 1;
		// }
		// Message.set(new String[] { "ダメージを反射した",
		// c.getColoredName().concat("に") + i + "ポイントのダメージを与えた" });

	}
}
