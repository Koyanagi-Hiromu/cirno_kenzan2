package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.awt.Color;

import main.res.SE;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DecurseEffect;
import dangeon.view.anime.DoronEffect;
import dangeon.view.anime.OuraEffect;
import dangeon.view.detail.MainMap;

public class 印状態異常 extends EnchantSpecial {
	private static int condition_chenge;

	public static void attack(Base_Creature creature) {
		if (enchantSimbolAllCheck(ENCHANT_SIMBOL.面)) {
			int flag = 0;
			for (CONDITION c : creature.getConditionList()) {
				if (c.isGood()) {
					if (flag < 2)
						flag = 1;
					CONDITION.conditionRecovery(creature, c);
					if (parcentCheck(25, ENCHANT_SIMBOL.面)) {
						flag = 2;
						Player.me.setCondition(c, 0);
						if (CONDITION.透視.equals(c)) {
							Medal.敵から透視状態を奪った.addCount();
						}
					}
				}
			}
			if (flag > 0) {
				MainMap.addEffect(new DecurseEffect(creature.getMassPoint()
						.getLocation(), null), false);
				if (flag == 1) {
					Message.set(creature.getColoredName(), "の良い状態をかき消した");
				} else {
					MainMap.addEffect(new OuraEffect(Player.me, true), true);
					Message.set(creature.getColoredName(), "の状態異常を奪った");
				}
			}
		}
		condition_chenge = 100;
		attackedConditioner(CASE.ATK, ENCHANT_SIMBOL.狼, CONDITION.影縫い, creature);
		condition_chenge = 11;
		attackedConditioner(CASE.ATK, ENCHANT_SIMBOL.影縫い, CONDITION.影縫い,
				creature);
		attackedConditioner(CASE.ATK, ENCHANT_SIMBOL.倍速, CONDITION.倍速, creature);
		attackedConditioner(CASE.ATK, ENCHANT_SIMBOL.封, CONDITION.封印, creature);
		condition_chenge = 9;
		attackedConditioner(CASE.ATK, ENCHANT_SIMBOL.天, CONDITION.天邪鬼, creature);
		attackedConditioner(CASE.ATK, ENCHANT_SIMBOL.混, CONDITION.混乱, creature);
		attackedConditioner(CASE.ATK, ENCHANT_SIMBOL.痺, CONDITION.麻痺, creature);
		attackedConditioner(CASE.ATK, ENCHANT_SIMBOL.鈍足, CONDITION.鈍足, creature);
		condition_chenge = 7;
		attackedConditioner(CASE.ATK, ENCHANT_SIMBOL.睡, CONDITION.睡眠, creature);
	}

	private static void attackedConditioner(CASE caze, ENCHANT_SIMBOL es,
			CONDITION con, Base_Creature creature) {
		SE se = null;
		if (con == CONDITION.混乱) {
			se = SE.STATUS_PIYOPIYO;
		} else if (con == CONDITION.影縫い) {
			se = SE.STATUS_SHADOW;
		} else if (con == CONDITION.麻痺) {
			se = SE.STATUS_SIBIBI;
		} else if (con == CONDITION.封印) {
			se = SE.STATUS_SEAL;
		} else if (con == CONDITION.鈍足) {
			se = SE.STATUS_SLOW;
		} else if (con == CONDITION.睡眠) {
			se = SE.STATUS_SLEEP;
		} else if (con == CONDITION.倍速) {
			se = SE.STATUS_SPEEDY;
		}
		effect(caze, es, con, creature, se);
	}

	public static void deffece(Base_Creature creature) {
		condition_chenge = 9;
		// attackedConditioner(CASE.DEF, ENCHANT_SIMBOL.狸,
		// CONDITION.conditionSelect(null), creature);
		attackedConditioner(CASE.DEF, ENCHANT_SIMBOL.倍速, CONDITION.倍速, creature);
		attackedConditioner(CASE.DEF, ENCHANT_SIMBOL.影縫い, CONDITION.影縫い,
				creature);
		attackedConditioner(CASE.DEF, ENCHANT_SIMBOL.痺, CONDITION.麻痺, creature);
		attackedConditioner(CASE.DEF, ENCHANT_SIMBOL.封, CONDITION.封印, creature);
		condition_chenge = 7;
		attackedConditioner(CASE.DEF, ENCHANT_SIMBOL.混, CONDITION.混乱, creature);
		印瞳.effect(creature);
		attackedConditioner(CASE.DEF, ENCHANT_SIMBOL.鈍足, CONDITION.鈍足, creature);
		condition_chenge = 5;
		attackedConditioner(CASE.DEF, ENCHANT_SIMBOL.睡, CONDITION.睡眠, creature);
	}

	private static void effect(CASE cs, ENCHANT_SIMBOL es, CONDITION con,
			Base_Creature creature, SE se) {
		if (!enchantSimbolAllCheck(cs, es)) {
			return;
		}
		if (creature.getHP() > 0) {
			if (parcentCheck(condition_chenge, es)) {
				if (con == CONDITION.天邪鬼) {
					if (creature instanceof Player) {
						return;
					}
					SE.AMANOJACK.play();
					MainMap.addEffect(new DoronEffect(creature.getMassPoint(),
							null, true, false), false);
				} else if (se != null) {
					se.play();
				}
				Message.set(creature.getColoredName(), "に", Color.YELLOW,
						con.NAME, Color.WHITE, "を与えた");
				int time = 0;
				creature.setCondition(con, time);
			}
		}
	}
}
