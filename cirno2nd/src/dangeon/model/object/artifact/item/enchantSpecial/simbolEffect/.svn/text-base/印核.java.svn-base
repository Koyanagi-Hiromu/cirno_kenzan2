package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import main.util.DIRECTION;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.霊烏路空のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.util.MapInSelect;

public class 印核 extends EnchantSpecial {
	private static int explosion = 12;

	public static void attackEffect(Base_Creature creature) {
		if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.自爆)) {
			if (parcentCheck(explosion, ENCHANT_SIMBOL.自爆)) {
				Message.set(Player.me.getColoredName(), "は爆発した");
				explosion(Player.me, ENCHANT_SIMBOL.自爆);
			}
		}
		if (enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.核爆発)) {
			if (parcentCheck(
					SetEnchantCard.isSetCard(霊烏路空のカード.class) ? explosion * 2
							: explosion, ENCHANT_SIMBOL.核爆発)) {
				Message.set(creature.getColoredName(), "を爆発させた");
				DIRECTION d = Player.me
						.converDirection(creature.getMassPoint());
				MapInSelect.吹き飛ばし(Player.me, "殴り飛ばして", creature, d, 10, 5);
				explosion(creature, ENCHANT_SIMBOL.核爆発);
				return;
			}
		}
		if (BonusConductor.人工太陽_敵爆発()) {
			Message.set(creature.getColoredName(), "を爆発させた");
			explosion(creature, ENCHANT_SIMBOL.核爆発);
		}
	}

	public static void deffenceEffect() {
		if (enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.自爆)) {
			if (parcentCheck(explosion, ENCHANT_SIMBOL.自爆)) {
				Message.set(Player.me.getColoredName(), "は爆発した");
				explosion(Player.me, ENCHANT_SIMBOL.自爆);
			}
		}
	}

	private static void explosion(Base_Creature c, ENCHANT_SIMBOL es) {
		MapInSelect.explosion(c.getMassPoint());

	}
}
