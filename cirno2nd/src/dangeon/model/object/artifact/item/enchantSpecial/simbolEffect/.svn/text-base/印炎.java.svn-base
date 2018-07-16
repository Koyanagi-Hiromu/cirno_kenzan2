package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;

public class 印炎 extends EnchantSpecial {
	public static int effect(int i) {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.炎)) {
			i /= 2;
		}
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.火)) {
			i -= 10;
		}
		return i;
	}

	public static int expDamege(int damage) {
		if (BonusConductor.人工太陽_爆発２倍()) {
			damage = damage * 3 / 2;
		}
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.炎)) {
			damage = damage / 2;
		}
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.火)) {
			damage -= 10;
		}
		return damage;
	}

	public static void expEffect() {
		int damage = Player.me.getHP() / 2;
		if (BonusConductor.人工太陽_爆発２倍()) {
			damage = damage * 3 / 2;
		}
		if (Player.me.conditionCheck(CONDITION.炎上)) {
			damage = 0;
		} else if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
				ENCHANT_SIMBOL.炎)) {
			damage /= 2;
		}
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.火)) {
			damage -= 10;
		}
		Player.me.chengeHP(null, null, -damage);
	}
}
