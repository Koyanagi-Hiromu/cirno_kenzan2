package dangeon.model.object.artifact.item.enchantSpecial;

import java.awt.Color;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import main.res.BGM;

public class Composition {
	private static boolean checkComposition(Base_Artifact a, Base_Artifact a2) {
		if (a instanceof SpellCard) {
			if (a2 instanceof SpellCard) {
				a.setForgeValue(a2.getForgeValue());
				((SpellCard) a).addBomb(1);
				return true;
			} else if (a2.sim != null) {
				return true;
			}
		} else if (a instanceof Staff && a2 instanceof Staff) {
			if (a.getClass().equals(a2.getClass())) {
				((Staff) a).addStaffRest(a2.staff_rest);
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	public static String composition(Base_Artifact a) {
		String com = null;
		String str;
		if (a.sim != null) {
			com = a.sim.getName().concat(":");
		}
		if (a.getListComposition().isEmpty()) {
			if (a.sim != null) {
				Enchant e;
				if (a.sim.getShow() == 1) {
					e = Enchant.ATK;
				} else if (a.sim.getShow() == 2) {
					e = Enchant.DEF;
				} else if (a.sim.getShow() == 3) {
					e = Enchant.ANY1;
				} else {
					return "";
				}
				com = com.concat(a.sim.getEXPLAIN(e));
			}
		} else {
			for (ENCHANT_SIMBOL es : a.getListComposition()) {
				str = compositionString(es);
				if (com == null)
					com = "(" + str + ")";
				else
					com = com.concat("(" + str + ")");
			}
			for (int i = 0; i < a.composition_number
					- a.getListComposition().size(); i++) {
				com = com.concat("(　)");

			}
		}
		if (com == null)
			return "";
		return com;
	}

	public static String compositionSimply(Base_Artifact a) {
		String com = " ";
		String str;
		if (a.sim != null) {
			com = com.concat(a.sim.getName());
		}
		for (ENCHANT_SIMBOL es : a.getListComposition()) {
			str = compositionString(es);
			if (com == null)
				com = str;
			else
				com = com.concat(str);
		}
		for (int i = 0; i < a.composition_number
				- a.getListComposition().size(); i++) {
			com = com.concat("□");
		}
		return com.concat("");
	}

	public static String compositionString(ENCHANT_SIMBOL c) {
		String str;
		str = c.getSimbolName();
		switch (c.getShow()) {
		case 1:
			return Enchant.ATK.getColor().toString().concat(str)
					.concat(Color.white.toString());
		case 2:
			return Enchant.DEF.getColor().toString().concat(str)
					.concat(Color.white.toString());
		case 3:
			return Enchant.ANY1.getColor().toString().concat(str)
					.concat(Color.white.toString());
		case 4:
			return Enchant.ANY3.getColor().toString().concat(str)
					.concat(Color.white.toString());
		default:
			return null;
		}
	}

	/**
	 * 引数に入れた Base_Artifact の特殊合成判定。
	 * 
	 * @param a
	 *            特殊合成されたか判定するアイテム
	 * @return 特殊合成されない場合null: SpecialCardについてはSpecialCardクラスを参照
	 */
	public static SpecialCard isSpecialCard(Base_Artifact a) {
		if (a instanceof SpellCard) {
			return SpecialCard.check((SpellCard) a);
		}
		return null;
	}

	/**
	 * 特殊合成判定。持ち物の指定番号と入れ替える
	 * 
	 * @param a
	 *            判定するアイテム
	 * @return 特殊合成されない場合null
	 */
	public static boolean isSpecialCard(Base_Artifact a, int number) {
		SpecialCard sc = null;
		if (a instanceof SpellCard) {
			sc = SpecialCard.check((SpellCard) a);
			if (sc != null) {
				Belongings.remove(a);
				Belongings.setItems(sc.getThisCard(), number);
				return true;
			}
		}
		return false;
	}

	/**
	 * boolean は合成が出来たかどうか、剣＋盾ならばfalse
	 * 
	 * @param a
	 *            ベースとなるアイテム
	 * @param a2合成材料
	 * 
	 */
	public static boolean setComposition(Base_Artifact a, Base_Artifact a2) {
		if ((a.isCurse() && a.isEnchantedNow())
				|| (a2.isCurse() && a2.isEnchantedNow())) {
			// 呪われていて装備がはずせない
			return false;
		}
		if (!checkComposition(a, a2))
			return false;
		if (a2.isCurse() == true) {
			Enchant.tryToRemove(a);
			a.setCurse(true);
		}
		// if (a2.isPunishment()) {
		// a.setPunishment(true);
		// }
		a2.remove();
		a2.check();
		a.check();
		if (a instanceof Staff) {
			return true;
		}
		if ((a instanceof SpellCard) && (a2 instanceof SpellCard))
			for (BGM bgm : ((SpellCard) a2).BGM_LIST)
				((SpellCard) a).BGM_LIST.add(bgm);
		if (a2.sim != null) {
			a.addListComposition(a2.sim);
		}
		if (a2.getListComposition().size() != 0) {
			for (ENCHANT_SIMBOL es : a2.getListComposition()) {
				if (es != null) {
					a.addListComposition(es);
				}
			}
		}
		// 色を付ける
		((Base_Item) a).flag_gousei = true;
		
		if (a.isCurse() && EnchantSpecial.simbolCheckOne(a, ENCHANT_SIMBOL.金))
		{
			a.setCurse(false);
		}

		return true;
	}
}
