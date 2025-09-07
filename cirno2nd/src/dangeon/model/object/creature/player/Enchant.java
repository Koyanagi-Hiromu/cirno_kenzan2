package dangeon.model.object.creature.player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import dangeon.controller.listener.menu.ENCHANT;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.Config;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.Base_Artifact.STATUS;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.set.BSE;
import dangeon.model.object.artifact.item.spellcard.set.SetEnchantCard;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.view.detail.View_Sider;
import main.res.BGM;
import main.res.SE;

public enum Enchant {
	ATK(0), DEF(1), ANY1(2), ANY2(3), ANY3(4);
	public static void forceToRemove(Base_Artifact a) {
		for (Enchant e : values()) {
			if (a.equals(e.enchanted_now)) {
				e.enchanted_now = null;
			}
		}
	}

	public static Enchant get(int y) {
		for (Enchant c : values())
			if (c.y == y)
				return c;
		return null;
	}

	public static Enchant[] getALL() {
		return new Enchant[] { ATK, DEF, ANY1, ANY2, ANY3 };
	}

	public static Enchant[] getANYS() {
		return Player.me.getAnys();
	}

	public static Enchant getEnchantedPlace(Base_Artifact a) {
		for (Enchant e : values()) {
			if (a.equals(e.enchanted_now)) {
				return e;
			}
		}
		return null;
	}

	public static List<Base_Artifact> getEnchantList() {
		List<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Enchant e : values()) {
			if (e.enchanted_now != null) {
				list.add(e.enchanted_now);
			}
		}
		System.out.println(list);
		return list;
	}

	public static List<Base_Artifact> getEnchantListOfAny() {
		List<Base_Artifact> list = new ArrayList<Base_Artifact>();
		if (ANY1.enchanted_now != null)
			list.add(ANY1.enchanted_now);
		if (ANY2.enchanted_now != null)
			list.add(ANY2.enchanted_now);
		if (ANY3.enchanted_now != null)
			list.add(ANY3.enchanted_now);
		return list;
	}

	public static ArrayList<Enchant> getListANY() {
		ArrayList<Enchant> list = new ArrayList<Enchant>();
		if (ANY1.isEnchant()) {
			list.add(ANY1);
		}
		if (ANY2.isEnchant()) {
			list.add(ANY2);
		}
		return list;
	}

	private static int getSum(STATUS s) {
		int status_value = 0;
		status_value += EnchantSpecial.staffPlusStatus(s);
		if (s == STATUS.STR) {
			status_value += EnchantSpecial.attackValue();
			if (ATK.enchanted_now != null) {
				status_value += BSE.STR.isCheck() ? BSE.STR.getValue() : 0;
				status_value += ATK.enchanted_now.itemEnchantPower(s);
				status_value += ATK.enchanted_now.getForgeValue();
			}
			Medal.攻撃力.save_the_more(status_value);
		} else {
			status_value += EnchantSpecial.deffeceValue();
			if (DEF.enchanted_now != null) {
				status_value += BSE.DEF.isCheck() ? BSE.DEF.getValue() : 0;
				status_value += DEF.enchanted_now.itemEnchantPower(s);
				status_value += DEF.enchanted_now.getForgeValue();
			}
			Medal.防御力.save_the_more(status_value);
		}
		if (status_value < 0) {
			status_value = 0;
		}
		return status_value;
	}

	// Base_Artifact a;
	// int status_value = 0;
	// int base = 10;
	// int def_base_forge = 50;
	// int str_base_forge = 25;
	// status_value += EnchantSpecial.staffPlusStatus();
	// if (s == STATUS.STR) {
	// a = ATK.enchanted_now;
	// if (a == null) {
	// return (int) status_value;
	// }
	// status_value += a.itemEnchantPower(s) * 0.75;
	// } else {
	// status_value += EnchantSpecial.deffeceValue();
	// a = DEF.enchanted_now;
	// if (a == null) {
	// return (int) status_value;
	// }
	// status_value += a.itemEnchantPower(s);
	// }
	// double forge_coefficient;
	// if (s == STATUS.STR) {
	// forge_coefficient = 1 + ((status_value - base) * str_base_forge);
	// } else {
	// forge_coefficient = 1 + ((status_value - base) * def_base_forge);
	// }
	// status_value += a.getForgeValue() * forge_coefficient / 1000;
	// return (int) status_value;
	// }
	//
	public static int getSumDEF() {
		return getSum(Base_Artifact.STATUS.DEF);
	}

	/**
	 * 装備中のアイテムの合計STR、合計DEFを返す。 合計値を参照したい場合はここを使う
	 * 
	 * @param status
	 * @return
	 */
	public static int getSumSTR() {
		return getSum(Base_Artifact.STATUS.STR);
	}

	/**
	 * 
	 * @return 何でも装備を何か１つでも装備しているか否か
	 */
	public static boolean isAnyANYEnchant() {
		for (int y = 0; y < ANY_LENGTH; y++) {
			if (get(NON_ANY_LENGTH + y).isEnchant()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isEnchanted(Base_Artifact a) {
		for (Enchant e : values()) {
			if (a.equals(e.enchanted_now)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 装備がMAXならばtrueを返す
	 * 
	 * @return
	 */
	public static boolean isEnchantSpace() {
		if (getEnchantList().size() <= 4) {
			return true;
		}
		return false;
	}

	public static void itemFreeze(Base_Artifact a) {
		Enchant[] ens = (BonusConductor.ひねくれ者_氷装備禁止()) ? getALL() : getANYS();
		for (Enchant en : ens) {
			if (en.isEquals(a)) {
				Message.set(a.getColoredName(), "は凍ってしまい", en.toString(),
						"から外れた");
				View_Sider.setInformation(a.getColoredName(), "が凍って",
						en.toString(), "から外れました");
				forceToRemove(a);
				break;
			}
		}
		Player.me.updateStatus();
	}

	public static void loadPresentEnchant(Base_Artifact[] as) {
		for (int i = 0; i < as.length; i++) {
			values()[i].enchanted_now = as[i];
		}
	}

	public static Base_Artifact[] savePresentEnchant() {
		Base_Artifact[] as = new Base_Artifact[values().length];
		for (int i = 0; i < as.length; i++) {
			as[i] = values()[i].enchanted_now;
		}
		return as;
	}

	public final int y;

	public static final int LENGTH = values().length;

	public static final int NON_ANY_LENGTH = 2;

	public static final int ANY_LENGTH = values().length - 2;

	private Base_Artifact enchanted_now = null;

	public static final Color CL_ATK = new Color(255, 10, 50),
			CL_DEF = Color.ORANGE, CL_ANY1 = Color.MAGENTA,
			CL_ANY2 = new Color(0, 220, 255),
			CL_ANY3 = new Color(150, 255, 80);

	public static void allRemove() {
		for (Enchant e : values()) {
			e.enchanted_now = null;
		}
	}

	public static void check(ENCHANT_SIMBOL es, boolean no_tokidoki_check) {
		for (Enchant e : Enchant.values()) {
			Base_Artifact a = e.getEnchant();
			if (a != null && a.sim == es) {
				a.enchantCheck(no_tokidoki_check);
			}
		}
	}

	public static boolean tryToRemove(Base_Artifact a) {
		boolean flag = false;
		for (Enchant e : values()) {
			if (a.equals(e.enchanted_now)) {
				flag = e.tryToRemove() || flag;
			}
		}
		if (flag)
			SE.SYSTEM_ENCHANT_OFF.play();
		return flag;
	}

	private Enchant(int y) {
		this.y = y;
	}

	/**
	 * 呪いも無視して外す メッセージも無し
	 */
	public void forceToRemove() {
		enchanted_now = null;
	}

	public Color getColor() {
		switch (this) {
		case ATK:
			return CL_ATK;
		case DEF:
			return CL_DEF;
		case ANY1:
			return CL_ANY1;
		case ANY2:
			return CL_ANY2;
		case ANY3:
			return CL_ANY3;
		}
		return Color.BLACK;
	}

	public Color getColorWithForbitten() {
		if (enchanted_now != null && enchanted_now.isCurse()) {
			return Color.BLUE;
		} else if (ENCHANT.isSelectedArtifactSpellCard()) {
			return getColor();
		} else {
			if (isMeANY()) {
				return getColor();
			} else {
				return Color.BLUE;
			}
		}
	}

	public Base_Artifact getEnchant() {
		return enchanted_now;
	}

	public SpellCard getEnchant_Casted_SpellCard() {
		return (SpellCard) enchanted_now;
	}

	public boolean isAbleToExchange(Base_Artifact old, Enchant old_place) {
		if (old_place == null || old == null) {
			return false;
		} else if (this == old_place) {
			return false;
		} else if (enchanted_now instanceof SpellCard
				&& old instanceof SpellCard) {
			return true;
		} else if (this.isMeANY() && old_place.isMeANY()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEnchant() {
		return enchanted_now != null;
	}

	public boolean isEnchantEmpty() {
		return enchanted_now == null;
	}

	public boolean isEquals(Base_Artifact a) {
		return a.equals(enchanted_now);
	}

	public boolean isMeANY() {
		for (int y = 0; y < ANY_LENGTH; y++) {
			if (this == get(NON_ANY_LENGTH + y)) {
				return true;
			}
		}
		return false;
	}

	public boolean isMeNONANY() {
		for (int y = 0; y < NON_ANY_LENGTH; y++) {
			if (this == get(y)) {
				return true;
			}
		}
		return false;
	}

	private boolean isSetAnyPossition() {
		switch (this) {
		case ATK:
			return false;
		case DEF:
			return false;
		default:
			return true;
		}
	}

	public void setEnchant(Base_Artifact a) {
		if (a != null && a != enchanted_now) {
			Player.me.hash_restriction.put(this, false);
		}
		enchanted_now = a;
		if (enchanted_now != null) {
			ENCHANT_SIMBOL e = enchanted_now.sim;
			// if (e == ENCHANT_SIMBOL.書) {
			// Enchant.check(e, false);
			// }
		}
	}

	public String toColoredString() {
		return getColor().toString().concat(toString())
				.concat(Color.WHITE.toString());
	}

	@Override
	public String toString() {
		switch (this) {
		case ATK:
			return "攻撃";
		case DEF:
			return "防御";
		case ANY1:
			return "何でも(１)";
		case ANY2:
			return "何でも(２)";
		case ANY3:
			return "何でも(３)";
		}
		return "エラー";
	}

	public boolean tryToRemove() {
		if (enchanted_now == null) {
			return false;
		}
		if (enchanted_now.isCurse()) {
			Message.set(enchanted_now.getColoredName().concat("は呪われていて外せない！"));
			return false;
		}
		Message.set(enchanted_now.getColoredName().concat("を")
				.concat(toColoredString()).concat("から外した"));
		enchanted_now = null;
		return true;
	}

	public boolean tryToSet(Base_Artifact selected_artifact) {
		boolean flag_checked = selected_artifact.isCurse_And_isItViewed();

		if (selected_artifact.isFrozen()) {
			if (BonusConductor.ひねくれ者_氷装備禁止()) {
				Message.set(selected_artifact.getColoredName(), "は凍っている為", "%",
						"装備する気になれない");
				return false;
			} else if (isSetAnyPossition()) {
				Message.set(selected_artifact.getColoredName(), "は凍っている為", "%",
						"何でも装備をする事が出来ない");
				return false;
			}
		}
		if (enchanted_now != null && enchanted_now.isCurse()) {
			SE.SYSTEM_CURSE.play();
			Message.set(enchanted_now.getColoredName(), "は呪われて外せない！");
			return false;
		}
		if (isMeNONANY()) {
			if (!(selected_artifact instanceof SpellCard)) {
				Message.set(toColoredString().concat("にはカードしか選択できない！"));
				return false;
			}
		}
		if (selected_artifact instanceof SpellCard) {
			final SpellCard spell = (SpellCard) selected_artifact;
			if (this.equals(ATK) || this.equals(DEF)) {
				new Thread() {
					@Override
					public void run() {
						spell.IMLC.init();
					};
				}.start();
			}
			if (!BGM.isSpecialBGMplaying()
					&& !PresentField.get().isEnchantedBGM_NoPlay()) {
				if (!Config.isFixBGM()) {
					spell.startBGM();
				}
			}
		}
		Enchant old_place = null;
		for (Enchant e : values()) {
			if (e.isEquals(selected_artifact)) {
				old_place = e;
				e.forceToRemove();
			}
		}
		Base_Artifact old = enchanted_now;
		setEnchant(selected_artifact);
		SE.SYSTEM_ENCHANT.play();
		if (selected_artifact instanceof SpellCard && !selected_artifact.isStaticCheked())
		{
			selected_artifact.check();
			if (ItemTable.getRank(selected_artifact) >= 3)
				View_Sider.setInformation("出現度：", ItemTable.getRank_String(selected_artifact));
		}
		if (old == null) {
			Message.set(enchanted_now.getColoredName().concat("を")
					.concat(toColoredString()).concat("装備した"));
		} else if (isAbleToExchange(old, old_place)) {
			if (old_place.isMeANY() && old.isFrozen()) {
				// 何でも装備していたものをこうげき装備しようとしたとき
				// 元々こうげき装備したものが凍っていたら交換できない
				SE.SYSTEM_ENCHANT_OFF.play();
				Message.set(old.getColoredName().concat("と"), enchanted_now
						.getColoredName().concat("を持ち替えた"));
				Message.set("が", old.getColoredName(), "は凍っていたため外れてしまった");
			} else {
				SE.SYSTEM_ENCHANT_OFF.play();
				old_place.setEnchant(old);
				Message.set(old.getColoredName().concat("と"), enchanted_now
						.getColoredName().concat("を持ち替えた"));
			}
		} else {
			SE.SYSTEM_ENCHANT_OFF.play();
			Message.set(
					old.getColoredName().concat("を").concat("外して$"),
					enchanted_now.getColoredName().concat("を")
							.concat(toColoredString()).concat("装備した"));
		}
		if (enchanted_now.isCurse()) {
			SE.SYSTEM_CURSE.play();
			Message.setConcatFlag(false);
			if (flag_checked) {
				Message.set(enchanted_now.getColoredName(), "は呪われている！");
			} else {
				Message.set(enchanted_now.getColoredName(), "は呪われていた！");
			}
		}
		if (this.equals(ATK) || this.equals(DEF)) {
			SetEnchantCard sec = SetEnchantCard.allCheck();
			if (sec != null) {
				Message.setConcatFlag(false);
				Message.set(sec.getExp(), "@");
				SE.FANFARE2.play();
				sec.quickEffect();
			}
		}
		selected_artifact.enchant();
		return true;
	}

}
