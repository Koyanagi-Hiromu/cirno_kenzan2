package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.黒谷ヤマメのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;

public class 印病 extends EnchantSpecial {
	public static boolean effect(CONDITION con) {
		if (SetEnchantCard.isSetCard(黒谷ヤマメのカード.class)) {
			return true;
		}
		return false;
	}

	public static int isEnchant(CONDITION c, int time) {
		if (c.p_time <= 0) {
			/*
			 * 杖の効果でよく見え状態が発生するとうまく行かない
			 */
			if (time < 2) {
				return time;
			}
		}
		if (!c.isGood() && enchantSimbolAllCheck(ENCHANT_SIMBOL.病)) {
			return 2;
		}
		return time;
	}
}
