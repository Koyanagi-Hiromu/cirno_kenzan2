package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.food.スモールおにぎり;
import dangeon.model.object.artifact.item.food.奇跡のおにぎり;
import dangeon.model.object.artifact.item.food.腐ったおにぎり;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;

public class 印祝 extends EnchantSpecial {
	public static void effect(Base_Creature c) {
		// if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.祝))
		// {
		// if (parcentCheck(25)) {
		// ItemFall.itemFall(c.getMassPoint(),
		// new 小さなおにぎり(c.getMassPoint()));
		// return;
		// }
		// }
		印鰻.effect(c);
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.飯)) {
			if (parcentCheck(4, ENCHANT_SIMBOL.飯)) {
				if (BonusConductor.守矢神()) {
					SE.MIRACLE_ONIGIRI.play();
					ItemFall.itemFall(c.getMassPoint(),
							new 奇跡のおにぎり(c.getMassPoint()));
				} else {
					ItemFall.itemFall(c.getMassPoint(),
							new スモールおにぎり(c.getMassPoint()));
				}
			}
		}
		if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.腐)) {
			if (parcentCheck(8, ENCHANT_SIMBOL.腐)) {
				if (BonusConductor.守矢神()) {
					SE.MIRACLE_ONIGIRI.play();
					ItemFall.itemFall(c.getMassPoint(),
							new 奇跡のおにぎり(c.getMassPoint()));
				} else {
					ItemFall.itemFall(c.getMassPoint(),
							new 腐ったおにぎり(c.getMassPoint()));
				}
			}
		}
		if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.祝)) {
			if (parcentCheck(2, ENCHANT_SIMBOL.祝)) {
				Message.set("奇跡が起こって、おにぎりがあらわれた");
				SE.MIRACLE_ONIGIRI.play();
				ItemFall.itemFall(c.getMassPoint(),
						new 奇跡のおにぎり(c.getMassPoint()));
			}
		}
	}
}
