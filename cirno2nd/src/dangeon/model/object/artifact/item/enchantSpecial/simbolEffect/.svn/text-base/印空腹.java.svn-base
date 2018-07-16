package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.config.Config;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.秋姉妹のカード;
import dangeon.model.object.artifact.item.spellcard.西行寺幽々子のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;

public class 印空腹 extends EnchantSpecial {
	public static int hungry() {
		int value = 100;
		value -= Config.getFate() * 10;
		if (BonusConductor.冒険家()) {
			value += 10;
		}
		if (enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.ハラヘリ)) {
			value = value * 40 / 100;
		}
		if (enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.回復)) {
			value /= 4;
		}
		if (enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.皮)) {
			value *= SetEnchantCard.isSetCard(秋姉妹のカード.class) ? 10 : 3;
		}
		if (印邪.isHalfHARAPEKO()) {
			value *= 3;
		}
		return value;
	}

	public static boolean notHungry() {

		return SetEnchantCard.isSetCard(西行寺幽々子のカード.class)
				|| enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.芋)
				|| 印邪.isHARAHERAZU();
	}
}
