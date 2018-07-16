package dangeon.model.object.artifact.item.check;

import java.io.Serializable;
import java.util.ArrayList;

import main.res.Image_Artifact;
import main.util.Show;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.grass.Base_Grass;
import dangeon.model.object.artifact.item.pot.Base_Pot;
import dangeon.model.object.artifact.item.ring.Ring;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.creature.player.Belongings;
import dangeon.view.util.StringFilter;

public class Checker implements Serializable {
	public static enum KeyWord {
		選択, 投擲, 使用;
	}

	public static Checker_ClassName<Checker> CARD, GRASS, RING, SCROLL, STAFF,
			POT;

	private static final long serialVersionUID = 1L;

	public static void addAtkCount(Base_Artifact a) {
		getChecker(a).addAtkCount();
	}

	public static void addDefCount(Base_Artifact a) {
		getChecker(a).addDefCount();
	}

	public static void checkStatic(Base_Artifact a) {
		getChecker(a).check(a);
	}

	public static void enchant(Base_Artifact a) {
		getChecker(a).enchant();
	}

	public static boolean enchanted(Base_Artifact a) {
		return getChecker(a).enchanted();
	}

	private static Checker_ClassName<Checker> get(
			ArrayList<Checker_ClassName_Enum> initList) {
		Checker_ClassName<Checker> list = new Checker_ClassName<Checker>();
		for (Checker_ClassName_Enum enm : initList) {
			list.add(new Checker(enm));
		}
		return list;
	}

	private static Checker_ClassName<Checker> get(Base_Artifact a) {
		if (a instanceof SpellCard)
			return CARD;
		else if (a instanceof Base_Grass)
			return GRASS;
		else if (a instanceof Ring)
			return RING;
		else if (a instanceof Scrool
				&& !a.getImage_Artifact().equals(Image_Artifact.BOOK2))
			return SCROLL;
		else if (a instanceof Staff)
			return STAFF;
		else if (a instanceof Base_Pot)
			return POT;
		return Checker_ClassName.NULL;
	}

	public static Checker_ClassName<Checker> get(int i) {
		switch (i) {
		case 0:
			return CARD;
		case 1:
			return GRASS;
		case 2:
			return RING;
		case 3:
			return SCROLL;
		case 4:
			return STAFF;
		case 5:
			return POT;
		}
		return null;
	}

	private static Checker getChecker(Base_Artifact a) {
		return get(a).get(a);
	}

	public static String getUnCheckedName(Base_Artifact a) {
		return getChecker(a).toString();
	}

	public static String getWritenName(Base_Artifact a) {
		return getChecker(a).name;
	}

	/**
	 * @param unchecked_level
	 * <br/>
	 *            0=>未識別なし（全識別）<br/>
	 *            1=>消費未識別（リボン草識別済み）<br/>
	 *            2=>装備も未識別（全未識別）<br/>
	 */
	public static void init(int unchecked_level) {
		if (unchecked_level == 0) {
			CARD = new Checker_ClassName<Checker>();
			GRASS = new Checker_ClassName<Checker>();
			RING = new Checker_ClassName<Checker>();
			SCROLL = new Checker_ClassName<Checker>();
			STAFF = new Checker_ClassName<Checker>();
			POT = new Checker_ClassName<Checker>();
		} else {
			SCROLL = get(Checker_ClassName.SCROLL.initList());
			STAFF = get(Checker_ClassName.STAFF.initList());
			CARD = get(Checker_ClassName.CARD.initList());
			if (unchecked_level == 2) {
				RING = get(Checker_ClassName.RING.initList());
				GRASS = get(Checker_ClassName.Grass.initList());
			} else {
				RING.clear();
				GRASS.clear();
			}
			POT = get(Checker_ClassName.POT.initList());
			for (Base_Artifact a : Belongings.getListItems()) {
				checkStatic(a);
			}
		}
	}

	public static boolean isStaticChecked(Base_Artifact a) {
		return getChecker(a).isStaticChecked();
	}

	public static void load(Checker_ClassName<Checker> list) {
		CARD = new Checker_ClassName<Checker>();
		GRASS = new Checker_ClassName<Checker>();
		RING = new Checker_ClassName<Checker>();
		SCROLL = new Checker_ClassName<Checker>();
		STAFF = new Checker_ClassName<Checker>();
		POT = new Checker_ClassName<Checker>();
		int i = 0;
		for (Checker c : list) {
			if (c == null)
				i++;
			else
				get(i).add(c);
		}
	}

	public static Checker_ClassName<Checker> saveHash() {
		Checker_ClassName<Checker> list = new Checker_ClassName<Checker>();
		for (int i = 0; i < 6; i++) {
			for (Checker c : get(i)) {
				list.add(c);
			}
			list.add(null);
		}
		return list;
	}

	public static void select(Base_Artifact a) {
		write(a, KeyWord.選択);
	}

	/**
	 * 
	 * @param a
	 * @param string
	 *            選択/投擲
	 */
	public static void write(Base_Artifact a, KeyWord key_word) {
		getChecker(a).setKeyWord(key_word);
	}

	/**
	 * 名前コマンドから
	 * 
	 * @param a
	 * @param name
	 */
	public static void writeName(Base_Artifact a, String name) {
		getChecker(a).setName(name);
	}

	protected Class<? extends Base_Artifact> clazz;

	public String checked_name;

	public final Checker_ClassName_Enum NAME;

	private boolean static_checked = false, selection = false, thrown = false,
			usage = false;

	private int atk_count = -1, def_count = -1;

	private String name;

	public Checker() {
		NAME = null;
		check(null);
	}

	public Checker(Checker_ClassName_Enum enm) {
		NAME = enm;
	}

	private void addAtkCount() {
		if (++atk_count > 99)
			atk_count = 99;
	}

	private void addDefCount() {
		if (++def_count > 99)
			def_count = 99;
	}

	private void check(Base_Artifact a) {
		if (!static_checked) {
			static_checked = true;
		}
		if (a != null)
			checked_name = StringFilter.getPlainString(a.getName());
		else
			checked_name = "";
	}

	private String createName() {
		if (name == null || name.isEmpty()) {
			return NAME.toString();
		} else {
			String header;
			if (NAME instanceof Checker_ClassName.STAFF) {
				header = "杖：";
			} else if (NAME instanceof Checker_ClassName.POT) {
				header = "瓶：";
			} else if (NAME instanceof Checker_ClassName.CARD) {
				header = "札：";
			} else if (NAME instanceof Checker_ClassName.Grass) {
				header = "草：";
			} else if (NAME instanceof Checker_ClassName.RING) {
				header = "飾：";
			} else if (NAME instanceof Checker_ClassName.SCROLL) {
				header = "書：";
			} else {
				header = "？：";
			}
			return Base_Item.CL_NAMED.concat(header).concat(name);
		}

	}

	private String createString() {
		String s = createName();
		if (NAME instanceof Checker_ClassName.STAFF) {
			return s;
		} else {
			if (selection || enchanted() || thrown || usage) {
				StringBuilder sb = new StringBuilder(s);
				sb.append(Base_Item.CL_NAMED);
				sb.append("《");
				if (usage) {
					sb.append("使用型");
				} else if (!enchanted()) {
					if (selection && thrown) {
						sb.append("選択式・投擲済");
					} else if (selection) {
						sb.append("選択式");
					} else if (thrown) {
						sb.append("投擲済");
					}
				} else {
					if (selection) {
						sb.append("選択・");
					}
					if (thrown) {
						sb.append("投擲・");
					}
					toName(sb);
				}
				sb.append("》");
				s = sb.toString();
			}
			return s;
		}
	}

	private void enchant() {
		if (atk_count == -1)
			atk_count = 0;
		if (def_count == -1)
			def_count = 0;
	}

	private boolean enchanted() {
		return def_count != -1;
	}

	public String getCheckedName() {
		return checked_name;
	}

	public boolean isExist() {
		return clazz != null;
	}

	public boolean isStaticChecked() {
		return static_checked;
	}

	public Checker set(Base_Artifact a) {
		clazz = a.getClass();
		return this;
	}

	public Checker set(boolean b) {
		static_checked = b;
		return this;
	}

	private void setKeyWord(KeyWord key_word) {
		switch (key_word) {
		case 選択:
			selection = true;
			return;
		case 投擲:
			thrown = true;
			return;
		case 使用:
			usage = true;
			return;
		default:
			Show.showErrorMessageDialog("No_Such_KeyWord_Error @ Checker.setKeyWord");
		}
	}

	private void setName(String name) {
		this.name = name;
	}

	private void toName(StringBuilder sb) {
		if (def_count == 0) {
			sb.append("装備済");
		} else {
			sb.append("攻");
			if (atk_count < 10)
				sb.append(" ");
			sb.append(atk_count);
			sb.append("：防");
			if (def_count < 10)
				sb.append(" ");
			sb.append(def_count);
		}
	}

	@Override
	public String toString() {
		if (NAME == null)
			return "(null)";
		return createString();
	}

}
