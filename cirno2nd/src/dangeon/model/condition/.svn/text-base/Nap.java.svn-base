package dangeon.model.condition;

import java.awt.Point;

import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public class Nap {
	private static boolean parcent(boolean room) {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.忍)) {
			return false;
		}
		int parcent;
		if (room) {
			parcent = 50;
		} else {
			if (EnchantSpecial.enchantSimbolAllCheck(EnchantSpecial.CASE.ALL,
					ENCHANT_SIMBOL.裁)) {
				return true;
			} else {
				parcent = 60;
			}
		}
		int select = new R().nextInt(100) + 1;
		if (parcent >= select) {
			return true;
		}
		// if (parcent + eiki() >= select) {
		// return true;
		// }
		return false;
	}

	public static void TurnCheck(boolean start, Base_Enemy em) {
		if (!em.conditionCheck(CONDITION.仮眠)
				&& !em.conditionCheck(CONDITION.特殊仮眠)) {
			return;
		}
		if (start) {
			if (TurnStartCheck(em)) {
				CONDITION.conditionRecovery(em, CONDITION.仮眠);
				CONDITION.conditionRecovery(em, CONDITION.特殊仮眠);
			}
		} else {
			if (TurnEndChecks(em)) {
				CONDITION.conditionRecovery(em, CONDITION.仮眠);
				CONDITION.conditionRecovery(em, CONDITION.特殊仮眠);
			}
		}
		for (CONDITION con : em.getConditionRemoveTask()) {
			em.getConditionList().remove(con);
		}
		em.getConditionRemoveTask().clear();
	}

	private static boolean TurnEndChecks(Base_Enemy em) {
		// if (em.getMAX_HP() != em.getHP()) {
		// return true;
		// }
		if (em.conditionCheck(CONDITION.特殊仮眠)) {
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.忍)) {
				Point p = Player.me.getMassPoint();
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if (p.x == em.getMassPoint().x + i
								&& p.y == em.getMassPoint().y + j) {
							return true;
						}
					}
				}
			} else {
				if (MassCreater
						.isThePointInThePlayerSight(em.getMassPoint(), 1)) {
					return true;
				}
			}
			return false;
		}
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.忍)) {
			return false;
		}
		if (MassCreater.isThePointInThePlayerSight(em.getMassPoint(), 1)) {
			if (MassCreater.getMass(Player.me.getBackPoint()).ROAD) {
				return parcent(true);
			}
		}
		Point p = Player.me.getMassPoint();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (p.x == em.getMassPoint().x + i
						&& p.y == em.getMassPoint().y + j) {
					return parcent(false);
				}
			}
		}

		return false;
	}

	private static boolean TurnStartCheck(Base_Enemy em) {
		if (em.getMAX_HP() != em.getHP()) {
			return true;
		}
		return false;
	}

	public static void 開幕() {
		// for (Base_Enemy em : MapList.getListEnemy()) {
		// CONDITION.conditionRecovery(em, CONDITION.特殊仮眠);
		// }
	}

	// private static int eiki() {
	// if (EnchantSpecial.enchantSimbolAllCheck(EnchantSpecial.CASE.ALL,
	// ENCHANT_SIMBOL.裁)) {
	// return SetEnchantCard.isSetCard(四季映姫・ヤマザナドゥのカード.class) ? 0 : 25;
	// }
	// return 0;
	// }

	// private static int koishi() {
	// if (EnchantSpecial.enchantSimbolAllCheck(EnchantSpecial.CASE.DEF,
	// ENCHANT_SIMBOL.無)) {
	// return SetEnchantCard.isSetCard(古明地こいしのカード.class) ? 50 : 25;
	// }
	// return 0;
	// }
}
