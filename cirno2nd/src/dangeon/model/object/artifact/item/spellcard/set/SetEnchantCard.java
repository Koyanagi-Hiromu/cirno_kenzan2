package dangeon.model.object.artifact.item.spellcard.set;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印蛍;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.アリスのカード;
import dangeon.model.object.artifact.item.spellcard.チルノのカード;
import dangeon.model.object.artifact.item.spellcard.パチュリーのカード;
import dangeon.model.object.artifact.item.spellcard.リグルのカード;
import dangeon.model.object.artifact.item.spellcard.レミリアのカード;
import dangeon.model.object.artifact.item.spellcard.伊吹萃香のカード;
import dangeon.model.object.artifact.item.spellcard.十六夜咲夜のカード;
import dangeon.model.object.artifact.item.spellcard.博麗霊夢のカード;
import dangeon.model.object.artifact.item.spellcard.古明地こいしのカード;
import dangeon.model.object.artifact.item.spellcard.多々良小傘のカード;
import dangeon.model.object.artifact.item.spellcard.大妖精のカード;
import dangeon.model.object.artifact.item.spellcard.封獣ぬえのカード;
import dangeon.model.object.artifact.item.spellcard.射命丸文のカード;
import dangeon.model.object.artifact.item.spellcard.東風谷早苗のカード;
import dangeon.model.object.artifact.item.spellcard.紅美鈴のカード;
import dangeon.model.object.artifact.item.spellcard.霧雨魔理沙のカード;
import dangeon.model.object.creature.player.Enchant;
import dangeon.util.STAGE;

public enum SetEnchantCard {
	チルダイ(チルノのカード.class, 大妖精のカード.class), レイチル(チルノのカード.class, 博麗霊夢のカード.class), チルマリ(
			チルノのカード.class, 霧雨魔理沙のカード.class), チグル(チルノのカード.class, リグルのカード.class),
	// ,
	マリアリ(霧雨魔理沙のカード.class, アリスのカード.class, "マリアリ！"), マリパチュ(霧雨魔理沙のカード.class,
			パチュリーのカード.class, "マリパチュ！"), にとまり(霧雨魔理沙のカード.class, パチュリーのカード.class,
			"にとまり！"),
	//
	レイマリ(霧雨魔理沙のカード.class, 博麗霊夢のカード.class, "レイマリ！"), レイレミ(博麗霊夢のカード.class,
			レミリアのカード.class, "レイレミ！"), レイアリ(博麗霊夢のカード.class, アリスのカード.class,
			"レイアリ！"), ゆかれいむ(博麗霊夢のカード.class, アリスのカード.class, "ゆかれいむ！"), れいすいか(
			博麗霊夢のカード.class, 伊吹萃香のカード.class, "れいすいか！"), あやれいむ(博麗霊夢のカード.class,
			射命丸文のカード.class, "あやれいむ！"),
	//
	ごがさな(東風谷早苗のカード.class, 多々良小傘のカード.class), さなぬえ(東風谷早苗のカード.class,
			封獣ぬえのカード.class), さなこい(東風谷早苗のカード.class, 古明地こいしのカード.class),
	//
	メイサク(十六夜咲夜のカード.class, 紅美鈴のカード.class), ステージ();
	//
	;
	public static SetEnchantCard allCheck() {
		if (!taikenban) {
			return null;
		}
		Base_Artifact atk = Enchant.ATK.getEnchant();
		Base_Artifact def = Enchant.DEF.getEnchant();
		for (SetEnchantCard sec : values()) {
			if (atk != null && def != null) {
				if (atk.getClass().equals(sec.S1)
						|| def.getClass().equals(sec.S1)) {
					if (atk.getClass().equals(sec.S2)
							|| def.getClass().equals(sec.S2)) {
						return sec;
					}
				}
			}
		}
		if (SetEnchantStage()) {
			return ステージ;
		}
		return null;
	}

	/**
	 * 共鳴しているかチェック
	 * 
	 * @return
	 */
	public static boolean isChecks() {
		if (!taikenban) {
			return false;
		}
		for (SetEnchantCard sec : values()) {
			if (sec.isSetCard()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isSetCard(Class<? extends Base_Artifact> s) {
		if (!taikenban) {
			return false;
		}
		Base_Artifact atk = Enchant.ATK.getEnchant();
		Base_Artifact def = Enchant.DEF.getEnchant();
		if (atk != null && def != null) {
			if (!atk.getClass().equals(s) && !def.getClass().equals(s)) {
				return false;
			}
		}
		for (SetEnchantCard sec : values()) {
			if (!s.equals(sec.S1) || !s.equals(sec.S2)) {
				continue;
			}
			if (sec.isSetCard()) {
				return true;
			}
		}
		if (SetEnchantStage()) {
			return true;
		}
		return false;
	}

	private static boolean SetEnchantStage() {
		Base_Artifact atk = Enchant.ATK.getEnchant();
		Base_Artifact def = Enchant.DEF.getEnchant();
		if (atk != null && def != null) {
			if (atk.getClass().equals(def.getClass())) {
				return false;
			}
			SpellCard at = (SpellCard) atk;
			SpellCard de = (SpellCard) def;
			for (STAGE stage : at.getListStage()) {
				if (de.getListStage().contains(stage)) {
					return true;
				}
			}
		}
		return false;
	}

	private final Class<? extends Base_Artifact> S1;
	private final Class<? extends Base_Artifact> S2;
	private final String EXP;
	private final static String DEFAULT_EXP = "共鳴！";
	/**
	 * 装備時即時発動効果
	 */
	private final boolean QUICK_EFFECT;
	private static boolean taikenban = false;

	SetEnchantCard() {
		this(null, null, DEFAULT_EXP);
	}

	SetEnchantCard(Class<? extends Base_Artifact> s1,
			Class<? extends Base_Artifact> s2) {
		S1 = s1;
		S2 = s2;
		EXP = DEFAULT_EXP;
		QUICK_EFFECT = false;
	}

	/**
	 * 
	 * @param s1
	 *            カード１
	 * @param s2
	 *            カード２
	 * @param exp
	 *            共鳴文
	 */
	SetEnchantCard(Class<? extends Base_Artifact> s1,
			Class<? extends Base_Artifact> s2, String exp) {
		S1 = s1;
		S2 = s2;
		EXP = exp;
		QUICK_EFFECT = false;
	}

	/**
	 * 
	 * @param s1
	 *            カード１
	 * @param s2
	 *            カード２
	 * @param exp
	 *            共鳴文
	 */
	SetEnchantCard(Class<? extends Base_Artifact> s1,
			Class<? extends Base_Artifact> s2, String exp, boolean b) {
		S1 = s1;
		S2 = s2;
		EXP = exp;
		QUICK_EFFECT = b;
	}

	public String getExp() {
		return EXP;
	}

	public boolean isSetCard() {
		if (!taikenban) {
			return false;
		}
		Base_Artifact atk = Enchant.ATK.getEnchant();
		Base_Artifact def = Enchant.DEF.getEnchant();
		if (atk != null && def != null) {
			if (atk.getClass().equals(S1) || def.getClass().equals(S1)) {
				if (atk.getClass().equals(S2) || def.getClass().equals(S2)) {
					return true;
				}
			}
		}
		return false;
	}

	public void quickEffect() {
		if (!taikenban) {
			return;
		}
		if (QUICK_EFFECT) {
			if (isSetCard(リグルのカード.class)) {
				印蛍.combo();
			}
		}
	}
}
