package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.awt.Point;
import java.util.ArrayList;

import main.util.DIRECTION;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.spellcard.村紗水蜜のカード;
import dangeon.model.object.artifact.item.spellcard.橙のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;

public class 印かまいたち {

	public static void anyAttack() {
		印龍.effect();
		if (EnchantSpecial
				.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.かまいたち)) {
			threeWay();
		}
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.双)) {
			twoWay();
		}
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.潜)) {
			印潜.effect();
		}
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.水)) {
			印潜.water_effect();
		}
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.追)) {
			印追.effect();
		}
		if (EnchantSpecial
				.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.オンバシラ)) {
			印オンバシラ.effect();
		}

		if (SetEnchantCard.isSetCard(村紗水蜜のカード.class)) {
			印錨.effect();
		}
	}

	private static void threeWay() {
		Point p = Player.me.getMassPoint().getLocation();
		for (DIRECTION d : Player.me.getDirection().getNeiboringDirections3()) {
			if (d != Player.me.getDirection()) {
				Player.me.setStand(d, false);
				Base_Enemy e = MapList.getEnemy(p.x + d.X, p.y + d.Y);
				if (e != null && !(e instanceof Base_NPC)) {
					Player.me.addAttackTask(Player.me, e);
				}
			}
		}
	}

	private static void twoWay() {
		ArrayList<DIRECTION> list = new ArrayList<DIRECTION>(8);
		DIRECTION p_d = Player.me.getDirection();
		for (DIRECTION d : p_d.getReverse().getNeiboringDirections8()) {
			list.add(d);
		}
		if (EnchantSpecial
				.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.かまいたち)) {
			for (DIRECTION d : p_d.getNeiboringDirections3()) {
				list.remove(d);
			}
		} else {
			list.remove(p_d);
		}
		Point p;
		int count = 0;
		for (DIRECTION d : list) {
			p = Player.me.getMassPoint().getLocation();
			if (Player.me.attackWalkableCheck(p, d)) {
				p.translate(d.X, d.Y);
				Base_Enemy e = MapList.getEnemy(p);
				if (e != null && !(e instanceof Base_NPC) && !e.isPlayerSide()) {
					if (e.getHP() <= 0) {
						continue;
					}
					Player.me.addAttackTask(Player.me, e);
					Player.me.setStand(d, false);
					if (SetEnchantCard.isSetCard(橙のカード.class)) {
						if (count == 0) {
							count++;
							continue;
						}
					}
					return;
				}
			}
		}
		if (Enchant.ATK.isEnchant()) {
			Player.me.setStand(Player.me.getDirection().getReverse(), false);
		}
	}

}
