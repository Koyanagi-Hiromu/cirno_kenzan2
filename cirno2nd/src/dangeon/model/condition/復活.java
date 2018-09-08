package dangeon.model.condition;

import main.res.SE;
import main.util.Show;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.grass.鳳凰の種;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.util.MapInSelect;
import dangeon.view.anime.HououEffect;
import dangeon.view.detail.MainMap;

public class 復活 {
	private static boolean checkHououNoTane() {
		Base_Artifact a = null;
		boolean flag = false;
		boolean curse_flag = false;
		for (Base_Artifact _a : Belongings.getListItems()) {
			if (_a.getClass().equals(鳳凰の種.class)) {
				a = _a;
				if (_a.isCurse()) {
					curse_flag = true;
				} else {
					flag = true;
					break;
				}
			}
		}
		if (flag) {
			a.staticCheck();
			Belongings.remove(a);
			return recoveryMessage(a.getColoredName(), "のおかげで元気が戻った");
		} else if (curse_flag) {
			String s = "";
			if (!a.isStaticCheked()) {
				s = a.getColoredName();
				a.check();
				Message.set(s, "は", a.getColoredName(), "だった");
				s = "しかし";
			}
			Message.set(s, a.getColoredName(), "は呪われていたため復活出来なかった");
			Medal.鳳凰の種が呪われていて復活できなかった.addCount();
		}
		return false;
	}

	public static boolean recovery() {
		if (Player.me.recoveryGhost()) {
			return true;
		} else if (Player.me.getConditionList().contains(CONDITION.復活)) {
			CONDITION.conditionRecovery(Player.me, CONDITION.復活);
			return recoveryMessage(Player.me.getColoredName(), "は不死鳥のごとく蘇った");
		} else if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
				ENCHANT_SIMBOL.復活)) {
			return ringRecovery();
		} else if (checkHououNoTane()) {
			return true;
		} else if (BonusConductor.蓬莱人形_自動復活()) {
			if (Player.me.getMAX_HP() > MapList.getFloor()) {
				Player.me.setMAX_HP(Player.me.getOriginMAX_HP() - MapList.getFloor());
				return recoveryMessage(Player.me.getColoredName(),
						"は不死鳥のごとく蘇った");
			} else {
				return false;
			}
		}
		return false;
	}

	private static boolean recoveryMessage(String... arr) {
		MainMap.addEffect(new HououEffect(MassCreater.isPlayerInRoom()), true);
		SE.BURN.play();
		Player.me.chengeHP(null, null, Player.me.getMAX_HP());
		Message.set(arr);
		if (BonusConductor.蓬莱人形_復活時炎上()) {
			Player.me.setCondition(CONDITION.炎上, 0);
		}
		if (BonusConductor.蓬莱人形_復活時自爆()) {
			MapInSelect.explosion(Player.me.getMassPoint());
		}
		return true;
	}

	/**
	 * 使用済み→装備中→リボン所持→合成済み装備中→x合成所持
	 * 
	 * @return
	 */
	private static boolean ringRecovery() {
		ENCHANT_SIMBOL e = ENCHANT_SIMBOL.復活;
		if (EnchantSpecial.getAlways_enchant_special().contains(e)) {
			EnchantSpecial.getAlways_enchant_special().remove(e);
			return recoveryMessage(Player.me.getColoredName(),
					"は使用していたリボンのおかげで蘇った");
		}
		for (Base_Artifact a : Enchant.getEnchantListOfAny()) {
			if (a.sim.equals(e)) {
				Belongings.remove(a);
				return recoveryMessage(Player.me.getColoredName(), "の身代わりとなって",
						a.getColoredName(), "は燃えてなくなった");
			}
		}
		if (PresentField.get() != null) {
			if (PresentField.get().isAllEnchantDungeon()) {
				for (Base_Artifact a : Belongings.getListItems()) {
					if (a.sim != null) {
						if (a.sim.equals(e)) {
							Belongings.remove(a);
							return recoveryMessage(Player.me.getColoredName(),
									"の身代わりとなって", a.getColoredName(),
									"は燃えてなくなった");
						}
					}
				}
			}
		}
		Enchant[] encs = { Enchant.ATK, Enchant.DEF };
		Base_Artifact[] as = new Base_Artifact[encs.length];
		for (int i = 0; i < encs.length; i++) {
			as[i] = null;
			Base_Artifact a = encs[i].getEnchant();
			for (ENCHANT_SIMBOL es : a.getListCompositionIncludeEnchant()) {
				if (es == e) {
					as[i] = a;
					break;
				}
			}
			if (as[i] == null) {
				Show.showInformationMessageDialog("復活リボンの印が発揮されていますが、その発揮元が見つかりません　\n掲示板へバグ報告お願いします");
				return recoveryMessage("原因不明の理由で蘇った");
			}
		}
		for (Base_Artifact a : as) {
			a.getListComposition().remove(e);
		}
		return recoveryMessage("装備カードの印が身代わりとなって蘇った");
	}
}
