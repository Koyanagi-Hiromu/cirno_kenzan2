package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.豊聡耳神子のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.util.MapInSelect;

public class 印和 extends EnchantSpecial {

	public static int effect() {
		int spec = waterFire();

		int count = 0;
		int count_up = 0;
		if (!enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.和)) {
			return spec;
		}
		for (int i = 0; i < MapInSelect.getListAroundInCreature(
				Player.me.getMassPoint()).size(); i++) {
			count_up++;
			count += count_up;
		}
		return 2
				* (SetEnchantCard.isSetCard(豊聡耳神子のカード.class) ? count * 2
						: count) + spec;
	}

	private static int waterFire() {
		if (BonusConductor.風水師_炎水()) {
			int spec = -MapList.getFloor() / 4;
			int bonus = 2;
			for (Base_Creature e : MapInSelect
					.getListAroundInCreature(Player.me.getMassPoint())) {
				if (e.conditionCheck(CONDITION.炎上)) {
					spec += bonus * 2;
				}
			}
			if (Player.me.conditionCheck(CONDITION.炎上)) {
				spec += bonus * 2;
			}
			for (DIRECTION d : DIRECTION.values()) {
				Point p = d.getFrontPoint(Player.me.getMassPoint()
						.getLocation());
				Mass mass = MassCreater.getMass(p);
				if (mass.WATER || mass.WATER_FROZEN) {
					spec += bonus;
				}
			}
			return spec;
		} else {
			return 0;
		}
	}

}
