package dangeon.model.condition;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印邪;
import dangeon.model.object.artifact.item.grass.鳳凰の種;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.view.anime.DecurseEffect;
import dangeon.view.anime.HououEffect;
import dangeon.view.detail.MainMap;
import dangeon.view.detail.View_Sider;
import main.res.BGM;
import main.res.SE;
import main.util.Show;

public class 復活 {
	
	private static Base_Artifact getHououNoTane() {
		Base_Artifact a = null;
		for (Base_Artifact _a : Belongings.getListItems()) {
			if (_a.getClass().equals(鳳凰の種.class)) {
				a = _a;
				if (!_a.isCurse()) {
					break;
				}
			}
		}
		return a;
	}
	
	public static BGM tryRecovery()
	{
		// 半死人＋邪法
		if (Player.me.recoveryGhost()) {
			return BGM.BladeRunner;
		} 
		
		if (Player.me.getConditionList().contains(CONDITION.復活)) {
			CONDITION.conditionRecovery(Player.me, CONDITION.復活);
			recoveryMessageByPhenix(Player.me.getColoredName(), "は不死鳥のごとく蘇った");
			return BGM.ASHtoASH;
		} 
		
		String 復活の種_元の名前 = null;
		Base_Artifact 復活の種 = getHououNoTane();
		if (復活の種 != null) {
			
			if (!復活の種.isCurse())
			{
				復活の種.staticCheck();
				Belongings.remove(復活の種);
				recoveryMessageByPhenix(復活の種.getColoredName(), "のおかげで元気が戻った");
				return BGM.ASHtoASH;
			}
			else 
			{
				if (!復活の種.isStaticCheked()) {
					復活の種_元の名前 = 復活の種.getColoredName();
				}
				for (Base_Artifact _a : Belongings.getListItems()) {
					if (_a.getClass().equals(鳳凰の種.class)) {
						_a.check();
					}
				}
			}
		}
		
		if (BonusConductor.蓬莱人形_自動復活()) {
			if (Player.me.getMAX_HP() > MapList.getFloor()) {
				Player.me.setMAX_HP(Player.me.getOriginMAX_HP() - MapList.getFloor());
				recoveryMessageByPhenix(Player.me.getColoredName(),
						"は不死鳥のごとく蘇った");
				return BGM.ASHtoASH;
			}
		}
		
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.復活)) {
			ringRecovery();
			return BGM.ASHtoASH;
		}
		
		if (印邪.is(10)) {
			SE.SYSTEM_CURSE.play();
			Player.me.setTelepoteAnimation(true, null);
			Message.set(Player.me.getColoredName(), "は邪法の力でよみがえった");
			Player.me.chengeHP(null, null, Player.me.getMAX_HP());
			Player.me.setAnimation(new DecurseEffect());
			for (Base_Artifact a : Belongings.getListItems()) {
				a.setCurse(false);
				a.check("forge");
			}
			return BGM.StayWithinTheWall;
		}
		
		if (復活の種 != null) {
			if (復活の種_元の名前 != null)
			{
				View_Sider.setInformation(復活の種_元の名前, "は", 復活の種.getColoredName(), "だった");
				View_Sider.setInformation("しかし", 復活の種.getColoredName(), "は呪われていたため復活出来なかった");	
			}
			else 
			{
				View_Sider.setInformation(復活の種.getColoredName(), "は呪われていたため復活出来なかった");
			}
			Medal.鳳凰の種が呪われていて復活できなかった.addCount();
		}
		return null;
	}

	public static boolean recovery() {
		return false;
	}

	private static boolean recoveryMessageByPhenix(String... arr) {
		MainMap.addEffect(new HououEffect(MassCreater.isPlayerInRoom()), true);
		SE.BURN.play();
		Player.me.chengeHP_NoEffect(Player.me.getMAX_HP());
		Message.set(arr);
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
			return recoveryMessageByPhenix(Player.me.getColoredName(),
					"は使用していたリボンのおかげで蘇った");
		}
		for (Base_Artifact a : Enchant.getEnchantListOfAny()) {
			if (a.sim.equals(e)) {
				Belongings.remove(a);
				return recoveryMessageByPhenix(Player.me.getColoredName(), "の身代わりとなって",
						a.getColoredName(), "は燃えてなくなった");
			}
		}
		if (PresentField.get() != null) {
			if (PresentField.get().isAllEnchantDungeon()) {
				for (Base_Artifact a : Belongings.getListItems()) {
					if (a.sim != null) {
						if (a.sim.equals(e)) {
							Belongings.remove(a);
							return recoveryMessageByPhenix(Player.me.getColoredName(),
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
				return recoveryMessageByPhenix("原因不明の理由で蘇った");
			}
		}
		for (Base_Artifact a : as) {
			a.getListComposition().remove(e);
		}
		return recoveryMessageByPhenix("装備カードの印が身代わりとなって蘇った");
	}
}
