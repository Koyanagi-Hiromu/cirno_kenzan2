package dangeon.model.object.artifact.item.enchantSpecial.simbolEffect;

import java.util.HashMap;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.view.detail.View_Sider;

/**
 * 00<br />
 * 01 大+10%<br />
 * 02 大+10%<br />
 * 03 大+10%<br />
 * 04 大+20%<br />
 * 05<br />
 * 06 会+30%<br />
 * 07<br />
 * 08<br />
 * 09 壁抜け攻撃<br />
 * 10<br />
 * 11<br />
 * 12<br />
 * 13 大+100%<br />
 * 14<br />
 * 15 ハラヘリ減<br />
 * 16 半ゴースト<br />
 * 17<br />
 * 18<br />
 * 19<br />
 * 20 壁歩き<br />
 * 21<br />
 * 22 ハラヘラズ<br />
 * 23<br />
 * 24 印全発揮<br />
 * 25<br />
 * 26<br />
 * 27<br />
 * 28<br />
 * 29<br />
 * 30 呪われたアイテム解呪可能<br />
 * 
 * @author weray
 * 
 */
public class 印邪 extends EnchantSpecial {

	private static int count = 0;

	public static boolean checkLargeDamage() {
		if (!EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.邪)) {
			return false;
		}
		final int count = getCursed();
		int percent = 0;
		switch (count) {
		case 12:
		case 11:
		case 10:
		case 9:
		case 8:
		case 7:
		case 6:
		case 5:
		case 4:
			percent += 20;
		case 3:
			percent += 10;
		case 2:
			percent += 10;
		case 1:
			percent += 10;
		case 0:
			break;
		default:
			percent = 80;
		}
		return parcentCheck(percent, ENCHANT_SIMBOL.邪);
	}

	public static boolean critical() {
		return is(6) && parcentCheck(30, ENCHANT_SIMBOL.邪);
	}

	private static int getCursed() {
		int count = 0;
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a.isCurse_And_isItViewed()) {
				count++;
			}
		}
		return count;
	}

	private static boolean getCursed(int i) {
		int count = 0;
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a.isCurse_And_isItViewed()) {
				if (++count >= i)
					return true;
			}
		}
		return false;
	}

	public static int getGhost() {
		return isHalfGhost() ? 0 : 1;
	}

	public static boolean is(int i) {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.邪)) {
			return getCursed(i);
		}
		return false;
	}

	public static boolean isDeCurse() {
		return is(30);
	}

	public static boolean isEffectiveAllSimbols() {
		return enchantSimbolCheck_Simply(CASE.DEF, ENCHANT_SIMBOL.邪)
				&& getCursed(24);
	}

	public static boolean isHalfGhost() {
		return is(16) || BonusConductor.半人半霊();
	}

	public static boolean isHalfHARAPEKO() {
		return is(15);
	}

	public static boolean isHARAHERAZU() {
		return is(22);
	}

	public static boolean isWallAttack() {
		return is(9);
	}

	public static boolean isWallWalk() {
		return is(20);
	}

	public static void setInfomation() {
		if (!EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.邪)) {
			count = 0;
			return;
		}
		int pre_count = count;
		count = getCursed();
		if (pre_count != count) {
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			map.put(1, "・大ダメージ発生の可能性");
			map.put(2, "・大ダメージ発生率上昇");
			map.put(3, "・大ダメージ発生率上昇");
			map.put(4, "・大ダメージ発生率５０％");
			map.put(6, "・会心の一撃発生の可能性");
			map.put(9, "・壁中攻撃");
			map.put(13, "・大ダメージ発生率上昇");
			map.put(15, "・空気を少し食べられる");
			map.put(16, "・半死人化");
			map.put(20, "・壁中歩行");
			map.put(22, "・空気を食べられる");
			map.put(24, "・全印効果発揮");
			map.put(30, "・アイテム解呪可能");
			if (map.containsKey(count)) {
				View_Sider.setInformation(String.valueOf(count),
						"コの呪いによって以下の能力を得ます");
				View_Sider.setInformation(map.get(count));
			} else {
				View_Sider.setInformation(String.valueOf(count),
						"コの呪われたアイテムを所持しています");
			}
		}
	}

}
