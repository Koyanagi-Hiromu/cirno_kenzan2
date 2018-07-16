package dangeon.model.object.artifact.item.enchantSpecial;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

import main.res.SE;
import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.Base_Artifact.STATUS;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.写印;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印かまいたち;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印つるはし;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印ダメージUP;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印バトルカウンター;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印不幸;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印冷;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印和;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印喘息;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印妹切草;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印密;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印小;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印教;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印柱;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印核;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印温;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印無;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印状態異常;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印祝;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印緋;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印薬草;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印蛍;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印血;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印見切り;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印超人;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印邪;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印闇;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印飛;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印高飛び;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印魔;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印鴇;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印龍;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.心印;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.頭印;
import dangeon.model.object.artifact.item.spellcard.キスメのカード;
import dangeon.model.object.artifact.item.spellcard.因幡てゐのカード;
import dangeon.model.object.artifact.item.spellcard.河城にとりのカード;
import dangeon.model.object.artifact.item.spellcard.犬走椛のカード;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.detail.View_Sider;

/**
 * 未設置
 * 
 * 装備した時→装備を外した時（ステータスに関わる物、透視、よく見え）count 歩く直前（パコレプキン）return
 * 
 * 設置済
 * 
 * 錆を受ける直前（錆）return 個別に対応 状態異常を受ける直前（状態異常回避）return 個別に対応
 * ターン終了時（自然回復、腹減り、しあわせ、モンスター発生率）count 呪を受ける直前（祓）return (個別対応)
 * アイテムを拾った時（識別）return 経験値を取得した時（しあわせ草）count 通常攻撃する直前（かまいたち、つるはし、ワナ）
 * 通常攻撃ダメージ直前（必中）return 通常攻撃ダメージ判定ステップ（種族特効、会心） 通常攻撃した後（状態異常系）
 * 通常攻撃した後、状態異常系効果後（不幸、高飛び、薬、妹、自爆） 通常防御ダメージ直前（見切り）return 通常防御ダメージ判定ステップ（会心）
 * 通常防御ダメージ判定後（バトルカウンター） 通常防御した後（状態異常系(自分に)、自爆）
 * 
 * @param c
 * @return
 */
public class EnchantSpecial {
	public enum CASE {
		ATK, DEF, ANY, ALL, RING
	}

	protected static Random ran = new R();
	protected static int parcentage;
	protected static int PARCENT = 100;

	/**
	 * 今居るフロアにだけ及ぼす効果。目薬草を呑んだときなど
	 */
	private static List<ENCHANT_SIMBOL> always_enchant_special = new ArrayList<ENCHANT_SIMBOL>();

	private static boolean flag_neglect_heal;

	private static LinkedHashSet<ENCHANT_SIMBOL> atk = new LinkedHashSet<ENCHANT_SIMBOL>(
			3), def = new LinkedHashSet<ENCHANT_SIMBOL>(3);

	public static void addAlways_enchant_special(ENCHANT_SIMBOL es) {
		if (always_enchant_special.contains(es)) {
			return;
		}
		always_enchant_special.add(es);
	}

	public static String allCheck() {
		List<ENCHANT_SIMBOL> list = new ArrayList<ENCHANT_SIMBOL>();
		for (ENCHANT_SIMBOL enchant : getAlways_enchant_special()) {
			list.add(enchant);
		}
		String str = "◎";
		for (ENCHANT_SIMBOL enchant : list) {
			str = str.concat(enchant.name());
		}
		return str;
	}

	public static boolean alwaysEnchantSpecialCheck(ENCHANT_SIMBOL es) {
		for (ENCHANT_SIMBOL enchant : getAlways_enchant_special()) {
			if (enchant == es)
				return true;
		}
		return false;
	}

	public static void AttackPahseOneMoreAttack(Base_Creature c) {
		if (c.getHP() <= 0)
			return;
		印柱.effect(c);
	}

	/**
	 * 血
	 */
	public static void attackPhaseBloodHeal(int damage) {
		印血.effect(damage);
	}

	public static void attackPhaseCondition(Base_Creature creature) {
		印小.effect(creature);
		印状態異常.attack(creature);
		印不幸.attackEffect(creature);
		印緋.comboEffect(creature);
		印飛.attackEffect(creature);
		印核.attackEffect(creature);
	}

	public static int attackPhaseDamageUp(int damage, Base_Creature c) {
		return 印ダメージUP.effect(damage, c);
	}

	public static void attackPhaseEffectPlus(Base_Creature creature) {
		印超人.effect(creature);
	}

	public static void attackPhaseEnemyBreak(Base_Creature c) {
		印祝.effect(c);
	}

	public static void attackPhaseFinal(Base_Creature creature) {
		印妹切草.attackEffect();
		印薬草.attackEffect();
		ArrayList<Class<?>> list = new ArrayList<Class<?>>();
		for (Enchant e : Enchant.getANYS()) {
			Base_Artifact a = e.getEnchant();
			if (a != null) {
				if (!list.contains(a.getClass())) {
					// TODO 敵が死んだときは除外
					Checker.addAtkCount(a);
				}
				list.add(a.getClass());
			}
		}
	}

	public static void attackPhaseStart() {
		印つるはし.picks();
		印かまいたち.anyAttack();
	}

	public static boolean attackPhaseWalkableThrough() {
		if (EnchantSpecial
				.enchantSimbolAllCheck(CASE.ATK, ENCHANT_SIMBOL.かまいたち)
				|| SetEnchantCard.isSetCard(犬走椛のカード.class) || 印邪.isWallAttack()) {
			return true;
		}
		return false;
	}

	public static int attackValue() {
		int status_value = 0;
		status_value += 印鴇.effect();
		status_value += 印和.effect();
		return status_value;
	}

	// public static boolean endPhase() {
	// if (EnchantSpecial.enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.妬)) {
	// if (parcentCheck(10)) {
	// MessageRecord.setMessageTask("ﾊﾟﾙﾊﾟﾙﾊﾟﾙﾊﾟﾙﾊﾟﾙ……");
	// return true;
	// }
	// }
	// return false;
	// }

	private static <iterable_type> boolean bake(ENCHANT_SIMBOL e) {
		if (e.number == ENCHANT_SIMBOL.SIMBOL_NUMBER_ATK) {
			if (enchantSimbolAllCheck__no_Check(ENCHANT_SIMBOL.謎))
				for (ENCHANT_SIMBOL enc : atk)
					if (e == enc)
						return true;
		} else if (e.number == ENCHANT_SIMBOL.SIMBOL_NUMBER_DEF) {
			if (enchantSimbolAllCheck__no_Check(ENCHANT_SIMBOL.狸))
				for (ENCHANT_SIMBOL enc : def)
					if (e == enc)
						return true;
		}
		return false;
	}

	public static boolean bake(int i) {
		ENCHANT_SIMBOL[] en = { ENCHANT_SIMBOL.謎, ENCHANT_SIMBOL.狸 };
		return enchantSimbolAllCheck__no_Check(en[i]);
	}

	public static LinkedHashSet<ENCHANT_SIMBOL> bakeList(int i) {
		if (i == 0)
			return atk;
		else
			return def;
	}

	public static void battleCounter(int damage, Base_Creature c) {
		if (c != null) {
			印バトルカウンター.effect(damage, c);
			印核.deffenceEffect();
		}
	}

	public static boolean confAndSleep() {
		return enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.覚醒);
	}

	public static int deffeceMagic(int damage) {
		return 印魔.effect(damage);
	}

	public static void deffecePhaseCondition(Base_Creature creature) {
		印状態異常.deffece(creature);
	}

	public static int deffecePhaseDodge() {
		int dodge = 0;
		dodge += 印見切り.effect();
		dodge += 印闇.effect();
		if (Player.me.getConditionList().contains(CONDITION.絶対回避)) {
			dodge += 100;
			if (Player.me.getConditionTurn(CONDITION.絶対回避) == 0) {
				CONDITION.conditionRecovery(Player.me, CONDITION.絶対回避);
			}
		}
		return dodge;
	}

	public static int deffecePhase友(int damage) {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.友)) {
			int parcent = SetEnchantCard.isSetCard(河城にとりのカード.class) ? 25 : 13;
			if (parcentCheck(parcent, ENCHANT_SIMBOL.友)) {
				SE.REIMU_BARRIER.play();
				Message.set("にとりが守ってくれた");
				return 1;
			}
		}
		return damage;
	}

	public static int deffeceValue() {
		int status_value = 0;
		if (MassCreater.getMass(Player.me.getMassPoint()).ROAD) {
			if (enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.狭)) {
				status_value += SetEnchantCard.isSetCard(キスメのカード.class) ? 10
						: 5;
			}
		}
		status_value += 印和.effect();
		status_value += 印鴇.effect();
		// if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
		// ENCHANT_SIMBOL.人間)) {
		// status_value += 3;
		// }
		// if (EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.冷))
		// {
		// status_value += 印冷.effect();
		// }
		return status_value;
	}

	public static void deffenceDamageCounter() {
		if (isFlagNeglectHeal()) {
			setFlagNeglectHeal(false);
		} else {
			印妹切草.deffenceEffect();
			印薬草.deffenceEffect();
			印高飛び.effect();
		}
	}

	public static void deffencePhaseFinal(Base_Creature creature) {
		印不幸.deffenceEffect(creature);
		頭印.effect();
		印飛.deffenceEffect(creature);
		ArrayList<Class<?>> list = new ArrayList<Class<?>>();
		for (Enchant e : Enchant.getANYS()) {
			Base_Artifact a = e.getEnchant();
			if (a != null) {
				if (!list.contains(a.getClass()))
					Checker.addDefCount(a);
				list.add(a.getClass());
			}
		}
	}

	public static int enchantCompositionAll(ENCHANT_SIMBOL e) {
		int count = 0;
		for (Base_Artifact a : Enchant.getEnchantList()) {
			if (a.sim == e) {
				count++;
			}
			for (ENCHANT_SIMBOL es : a.getListComposition()) {
				if (es == e)
					count++;
			}
		}
		return count;
	}

	public static boolean enchantSimbolAllCheck(CASE c, ENCHANT_SIMBOL e) {
		if (enchantSimbolAllCheck__no_Check(c, e)) {
			Enchant.check(e, false);
			return true;
		}
		return bake(e);
	}

	public static boolean enchantSimbolAllCheck(ENCHANT_SIMBOL e) {
		return enchantSimbolAllCheck(null, e);
	}

	public static boolean enchantSimbolAllCheck__no_Check(CASE c,
			ENCHANT_SIMBOL e) {
		if (Player.me.getClassJob().checkSimbol(e))
			return true;
		if (PresentField.get() != null) {
			if (PresentField.get().isAllEnchantDungeon()) {
				for (Base_Artifact a : Belongings.getListItems()) {
					if (a.sim != null) {
						if (a.sim.equals(e)) {
							return true;
						}
					}
				}
			}
		}
		if (c == null || e.getEnchantCase() != CASE.RING)
			c = e.getEnchantCase();
		if (alwaysEnchantSpecialCheck(e)) {
			return true;
		}
		if (c == CASE.ALL) {
			return enchantSimbolCheck(c, e);
		}
		return enchantSimbolCheck_Simply(c, e);

	}

	public static boolean enchantSimbolAllCheck__no_Check(ENCHANT_SIMBOL e) {
		return enchantSimbolAllCheck__no_Check(null, e);
	}

	private static boolean enchantSimbolCheck(CASE c, ENCHANT_SIMBOL e) {
		for (ENCHANT_SIMBOL enchant : getAlways_enchant_special()) {
			if (enchant == e) {
				return true;
			}
		}
		if (c == null)
			return false;
		switch (c) {
		case ATK:
			if (Enchant.ATK.getEnchant() == null) {
				break;
			}
			for (ENCHANT_SIMBOL es : Enchant.ATK.getEnchant()
					.getListCompositionIncludeEnchant()) {
				if (es == e)
					return true;
			}
			break;
		case DEF:
			if (Enchant.DEF.getEnchant() == null) {
				break;
			}
			for (ENCHANT_SIMBOL es : Enchant.DEF.getEnchant()
					.getListCompositionIncludeEnchant()) {
				if (es == e)
					return true;
			}
			break;
		case ANY:
			for (Base_Artifact a : Enchant.getEnchantListOfAny()) {
				if (a.sim == e) {
					return true;
				}
			}
			break;
		case RING:
			// if (enchantSimbolCheck(CASE.ANY, e)) {
			// return true;
			// }
			if (enchantSimbolCheck(CASE.ATK, e)
					&& enchantSimbolCheck(CASE.DEF, e)) {
				return true;
			} else {
				return enchantSimbolCheck(CASE.ANY, e);
			}
		case ALL:
			if (enchantSimbolCheck(CASE.ATK, e)) {
				return true;
			}
			if (enchantSimbolCheck(CASE.DEF, e)) {
				return true;
			}
			if (enchantSimbolCheck(CASE.ANY, e)) {
				return true;
			}
			break;
		}
		return false;
	}

	public static boolean enchantSimbolCheck_Simply(CASE c, ENCHANT_SIMBOL e) {
		if (enchantSimbolCheck(c, e)) {
			return true;
		}
		return enchantSimbolCheck(CASE.ANY, e);
	}

	public static boolean enchantSimbolRingCheck_View_Simbol(ENCHANT_SIMBOL e) {
		if (alwaysEnchantSpecialCheck(e)) {
			// テンポラリーチェック
			return true;
		}
		if (enchantSimbolCheck(CASE.ATK, e) && enchantSimbolCheck(CASE.DEF, e)) {
			// 合成チェック
			return true;
		}
		for (Base_Artifact a : Enchant.getEnchantListOfAny()) {
			if (a.sim == e && a.isStaticCheked()) {
				// 何でもチェック　※識別済み
				return true;
			}
		}
		return false;
	}

	public static void endPlayerTurn() {
		印喘息.effect();
	}

	public static void fireAttack() {
		印龍.effect();
	}

	public static int floorEndPhaseItemHot() {
		return 印温.effect();
	}

	public static List<ENCHANT_SIMBOL> getAlways_enchant_special() {
		return always_enchant_special;
	}

	protected static int getParcent(int i, ENCHANT_SIMBOL es) {
		int parcent = i;
		if (enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.兎)) {
			if (SetEnchantCard.isSetCard(因幡てゐのカード.class)) {
				parcent *= 4;
			} else {
				parcent *= 2;
			}
		}
		if (enchantSimbolAllCheck(CASE.ALL, ENCHANT_SIMBOL.音)) {
			if (es.grass_book) {
				parcent = parcent * 3 / 2;
			}
		}
		return parcent;
	}

	public static int happy(int exp) {
		exp = 印教.effect(exp);
		return exp;
	}

	public static boolean isFlagNeglectHeal() {
		return flag_neglect_heal;
	}

	public static boolean mekki() {
		return enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.金)
				|| enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.衰);
	}

	protected static boolean parcentCheck(int i, ENCHANT_SIMBOL es) {
		int select = new R().nextInt(100) + 1;
		int parcent = getParcent(i, es);
		// if (印密.isFlag())
		// parcent *= 3;
		if (parcent >= select || 印密.isFlag()) {
			Enchant.check(es, true);
			return true;
		} else {
			return false;
		}
	}

	public static boolean plating(Base_Artifact a) {
		for (ENCHANT_SIMBOL es : a.getListComposition()) {
			if (es == ENCHANT_SIMBOL.金)
				return true;
		}
		return false;
	}

	public static void removeAlwaysEnchantSpecial() {
		TurnSystemController.removeTimeStop();
		always_enchant_special.clear();
	}

	public static void setBake() {
		atk.clear();
		def.clear();
		int num;
		ArrayList<ENCHANT_SIMBOL> list;
		num = new R().nextInt(7);
		if (num == 0)
			num = 4;
		else if (num < 3)
			num = 3;
		else
			num = 2;
		list = ENCHANT_SIMBOL.values(ENCHANT_SIMBOL.SIMBOL_NUMBER_ATK);
		list.remove(ENCHANT_SIMBOL.謎);
		for (int i = 0; i < num; i++)
			atk.add(list.get(new R().nextInt(list.size())));
		num = new R().nextInt(7);
		if (num == 0)
			num = 4;
		else if (num < 3)
			num = 3;
		else
			num = 2;
		list = ENCHANT_SIMBOL.values(ENCHANT_SIMBOL.SIMBOL_NUMBER_DEF);
		list.remove(ENCHANT_SIMBOL.狸);
		for (int i = 0; i < num; i++)
			def.add(list.get(new R().nextInt(list.size())));
		setInfoBake();
	}

	// public static int enchantSimbolAllCount(CASE c, ENCHANT_SIMBOL e) {
	// int count = 0;
	// if (c == CASE.ALL) {
	// return enchantSimbolCount(c, e);
	// }
	// count += enchantSimbolCount(c, e);
	// count += enchantSimbolCount(CASE.ANY, e);
	// return count;
	// }

	public static void setFlagNeglectHeal(boolean b) {
		flag_neglect_heal = b;
	}

	private static void setInfoBake() {
		setInfoBake(0);
		setInfoBake(1);
	}

	public static void setInfoBake(int i) {
		if (i == 0 && bake(0)) {
			View_Sider.setInformation("謎印の効果で次の印が発揮されます");
			String s = " ";
			for (ENCHANT_SIMBOL e : atk)
				s = s.concat(e.getName()).concat(" ");
			View_Sider.setInformation(s);
		} else if (i == 1 && bake(1)) {
			View_Sider.setInformation("狸印の効果で次の印が発揮されます");
			String s = " ";
			for (ENCHANT_SIMBOL e : def)
				s = s.concat(e.getName()).concat(" ");
			View_Sider.setInformation(s);
		}
	}

	// private static int enchantSimbolCount(CASE c, ENCHANT_SIMBOL e) {
	// int count = 0;
	// switch (c) {
	// case ATK:
	// if (Enchant.ATK.getEnchant() == null) {
	// break;
	// }
	// for (ENCHANT_SIMBOL es : Enchant.ATK.getEnchant()
	// .getListCompositionIncludeEnchant()) {
	// if (es == e)
	// count++;
	// }
	// break;
	// case DEF:
	// if (Enchant.DEF.getEnchant() == null) {
	// break;
	// }
	// for (ENCHANT_SIMBOL es : Enchant.DEF.getEnchant()
	// .getListCompositionIncludeEnchant()) {
	// if (es == e)
	// count++;
	// }
	// break;
	// case ANY:
	// for (Base_Artifact a : Enchant.getEnchantListOfAny()) {
	// if (a.sim == e) {
	// count++;
	// }
	// }
	// break;
	// case RING:
	// // count += enchantSimbolCount(CASE.ANY, e);
	// if (enchantSimbolCheck(CASE.ATK, e)
	// && enchantSimbolCheck(CASE.DEF, e)) {
	// int i = enchantSimbolCount(CASE.ATK, e);
	// int j = enchantSimbolCount(CASE.DEF, e);
	// if (i != j) {
	// count += Math.abs(i - j);
	// } else {
	// count += i;
	// }
	// }
	// break;
	// case ALL:
	// count += enchantSimbolCount(CASE.ATK, e);
	// count += enchantSimbolCount(CASE.DEF, e);
	// count += enchantSimbolCount(CASE.ANY, e);
	// break;
	// }
	// return count;
	// }

	/**
	 * １つのartifactが引数のENCHANT_SIMBOLを持っているかチェックする
	 * 
	 * @return
	 */
	public static boolean simbolCheckOne(Base_Artifact a, ENCHANT_SIMBOL e) {
		for (ENCHANT_SIMBOL es : a.getListCompositionIncludeEnchant()) {
			if (es == e) {
				return true;
			}
		}
		return false;
	}

	public static int staffPlusStatus(STATUS s) {
		CASE c;
		if (s.equals(STATUS.STR)) {
			c = CASE.ATK;
		} else if (s.equals(STATUS.DEF)) {
			c = CASE.DEF;
		} else {
			return 0;
		}

		int i = 0;
		if (enchantSimbolAllCheck(c, ENCHANT_SIMBOL.杖)) {
			i += 2;
		}
		if (enchantSimbolAllCheck(c, ENCHANT_SIMBOL.書)) {
			i += 1;
		}
		return i;
	}

	public static int strUp() {
		if (enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.力３)) {
			return 3;
		}
		return 0;
	}

	public static boolean toushi() {
		return enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.透視)
				|| Player.me.getConditionList().contains(CONDITION.透視)
				|| PresentField.get().isTouShiDungeon();
	}

	/**
	 * 回避率20％ 重複印一つにつき5%UP
	 */
	public static int trap() {
		int value = 20;
		int value_plus = 5;
		int count = enchantCompositionAll(ENCHANT_SIMBOL.ワナ);
		if (count != 0) {
			if (count == 1)
				return value;
			return value + count * value_plus;
		}

		return 0;
	}

	public static void turnStepEffect() {
		印蛍.effect();
		印無.effect();
		心印.effect();
		写印.effect();
		印冷.effect();
		if (!Player.me.conditionCheck(CONDITION.封印)) {
			if (enchantSimbolAllCheck(ENCHANT_SIMBOL.封印)) {
				if (parcentCheck(1, ENCHANT_SIMBOL.封印)) {
					SE.STATUS_SEAL.play();
					Message.set("くちが効けなくなった");
					Player.me.setCondition(CONDITION.封印, 0);
				}
			}
		}
	}

}
