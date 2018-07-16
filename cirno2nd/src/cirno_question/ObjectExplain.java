package cirno_question;

import java.util.ArrayList;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class ObjectExplain {
	public static ArrayList<String> importEnemyExplain(Base_Enemy em) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(em.getClass().getSimpleName());
		boolean b = false;
		for (int i = 1; i <= 3; i++) {
			StringBuffer sb = new StringBuffer();
			em.chengeLv(i);
			s(sb, "Lv" + em.getLV());
			s(sb, "名", em.getName());
			s(sb, "HP", String.valueOf(em.getHP()));
			s(sb, "STR", String.valueOf(em.getSTR()));
			s(sb, "DEF", String.valueOf(em.getDEF()));
			ls(sb, "EXP", String.valueOf(em.getENEMY_EXP()));
			list.add(sb.toString());
		}
		list.add("特殊能力");
		for (String str : em.getSpecialActionExplain()) {
			list.add(str);
		}
		return list;
	}

	public static ArrayList<String> importItemExplain(Base_Artifact a) {
		ArrayList<String> list = new ArrayList<String>();
		return list;
	}

	public static ArrayList<String> importTrapExplain(Base_Trap t) {
		ArrayList<String> list = new ArrayList<String>();
		return list;
	}

	public static StringBuffer ls(StringBuffer sb, String str, String str2) {
		sb.append(str);
		sb.append("/");
		sb.append(str2);
		return sb;
	}

	public static String oneLineEnemyText(Base_Enemy em, int lv) {
		StringBuffer sb = new StringBuffer();
		em.chengeLv(lv);
		sb.append(em.getHP());
		sb.append(":");
		sb.append(em.getSTR());
		sb.append(":");
		sb.append(em.getDEF());
		sb.append(":");
		sb.append(em.getENEMY_EXP());
		return sb.toString();
	}

	public static String oneLineItemText(Base_Artifact a, int lv) {
		StringBuffer sb = new StringBuffer();
		sb.append(a.getOneLineText());
		return sb.toString();
	}

	public static StringBuffer s(StringBuffer sb, String str) {
		sb.append(str);
		sb.append(" : ");
		return sb;
	}

	public static StringBuffer s(StringBuffer sb, String str, String str2) {
		sb.append(str);
		sb.append("/");
		sb.append(str2);
		sb.append(" : ");
		return sb;
	}
}
