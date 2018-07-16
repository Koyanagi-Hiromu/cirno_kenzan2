package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.パルスィのカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.Battle.Battle;

public class 印嫉妬 extends EnchantSpecial {
	public static boolean combo(Base_Creature c) {
		if (Player.me.getConditionList().contains(CONDITION.必中会心)) {
			return true;
		}
		if (SetEnchantCard.isSetCard(パルスィのカード.class)) {
			if (c.getHP() > Player.me.getHP()) {
				return true;
			}
		}
		return false;
	}

	public static void effect(CONDITION condition) {
		if (!enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.嫉妬)) {
			return;
		}
		if (condition.isBad()) {
			Player.me.changeConditoinTurn(CONDITION.必中会心, 1);
			Message.set("嫉妬力が溜まった");
		}
	}

	public static boolean isEffect() {
		if (Player.me.getConditionList().contains(CONDITION.必中会心)) {
			Player.me.changeConditoinTurn(CONDITION.必中会心, -1);
			Battle.setHitFlag();
			return true;
		}
		return false;
	}

	public static void miss() {
		if (!enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.嫉妬)) {
			return;
		}
		Message.set("攻撃を外して嫉妬力が溜まった");
		Player.me.changeConditoinTurn(CONDITION.必中会心, 1);
	}
}
