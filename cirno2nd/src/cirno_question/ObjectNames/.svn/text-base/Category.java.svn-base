package cirno_question.ObjectNames;

import java.util.ArrayList;

import cirno_question.ObjectExplain;
import cirno_question.QS;
import cirno_question.SetOnMapObject;
import cirno_question.frame.MainFrame;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.util.STAGE;
import dangeon.util.StringIsChangedToObject;

public class Category {
	public static String[] category = { "エネミー", "矢", "カード", "リボン", "草", "書",
			"杖", "壷", "おにぎり", "トラップ", "ディスク" };

	public static String getCategoryNames(String str) {
		if (str.matches("エネミー")) {
			return "enemy";
		} else if (str.matches("矢")) {
			return "arrow";
		} else if (str.matches("カード")) {
			return "spellcard";
		} else if (str.matches("リボン")) {
			return "ring";
		} else if (str.matches("草")) {
			return "grass";
		} else if (str.matches("書")) {
			return "scrool";
		} else if (str.matches("杖")) {
			return "staff";
		} else if (str.matches("壷")) {
			return "pot";
		} else if (str.matches("おにぎり")) {
			return "food";
		} else if (str.matches("ディスク")) {
			return "disc";
		} else {
			return "trap";
		}
	}

	public static String[] getNames(String str) {
		if (str.matches("エネミー")) {
			return Enemy.monster;
		} else if (str.matches("矢")) {
			return Item.arrow;
		} else if (str.matches("カード")) {
			return Item.card;
		} else if (str.matches("リボン")) {
			return Item.ring;
		} else if (str.matches("草")) {
			return Item.grass;
		} else if (str.matches("書")) {
			return Item.scrool;
		} else if (str.matches("杖")) {
			return Item.staff;
		} else if (str.matches("壷")) {
			return Item.pot;
		} else if (str.matches("おにぎり")) {
			return Item.food;
		} else if (str.matches("ディスク")) {
			return new String[] { "ディスク" };
		} else {
			return Trap.trap;
		}
	}

	public static ArrayList<String> getObjectExplain(String str) {
		String category = QS.category_position;
		if (category.matches("エネミー")) {
			Base_Enemy e = StringIsChangedToObject
					.returnBaseEnemy(str, 1, true);
			return ObjectExplain.importEnemyExplain(e);
		} else if (category.matches("トラップ")) {
			Base_Artifact a = StringIsChangedToObject.returnBaseTrap(str, true);
			ArrayList<String> list = new ArrayList<String>();
			return a.getListItemExplan(list);
		} else if (category.matches("ディスク")) {
			ArrayList<String> list = new ArrayList<String>();
			list.add("カードを強化する");
			return list;
		} else {
			Base_Artifact a = StringIsChangedToObject.returnBaseItem(str,
					getCategoryNames(category), true);
			ArrayList<String> list = new ArrayList<String>();
			return a.getListItemExplan(list);
		}
	}

	public static void setListCardCheckStage() {
		QS.CARD_CHECK_LIST.clear();
		for (Base_Artifact a : QS.CARD_LIST) {
			for (STAGE st : ((SpellCard) a).getListStage()) {
				if (MainFrame.ME.eien.isSelected()
						&& MainFrame.ME.eien.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.CARD_CHECK_LIST.add(a.getClass().getSimpleName());
					break;
				} else if (MainFrame.ME.kaei.isSelected()
						&& MainFrame.ME.kaei.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.CARD_CHECK_LIST.add(a.getClass().getSimpleName());
					break;
				} else if (MainFrame.ME.youyou.isSelected()
						&& MainFrame.ME.youyou.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.CARD_CHECK_LIST.add(a.getClass().getSimpleName());
					break;
				} else if (MainFrame.ME.tirei.isSelected()
						&& MainFrame.ME.tirei.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.CARD_CHECK_LIST.add(a.getClass().getSimpleName());
					break;
				} else if (MainFrame.ME.seiren.isSelected()
						&& MainFrame.ME.seiren.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.CARD_CHECK_LIST.add(a.getClass().getSimpleName());
					break;
				} else if (MainFrame.ME.kouma.isSelected()
						&& MainFrame.ME.kouma.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.CARD_CHECK_LIST.add(a.getClass().getSimpleName());
					break;
				} else if (MainFrame.ME.huuzin.isSelected()
						&& MainFrame.ME.huuzin.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.CARD_CHECK_LIST.add(a.getClass().getSimpleName());
					break;
				}
			}
		}
	}

	public static void setListEnemyCheckStage() {
		QS.ENEMY_CHECK_LIST.clear();
		for (Base_Enemy em : QS.ENEMY_LIST) {
			for (STAGE st : em.getCategory()) {
				if (MainFrame.ME.eien.isSelected()
						&& MainFrame.ME.eien.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.ENEMY_CHECK_LIST.add(em.getClass().getSimpleName());
					break;
				} else if (MainFrame.ME.kaei.isSelected()
						&& MainFrame.ME.kaei.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.ENEMY_CHECK_LIST.add(em.getClass().getSimpleName());
					break;
				} else if (MainFrame.ME.youyou.isSelected()
						&& MainFrame.ME.youyou.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.ENEMY_CHECK_LIST.add(em.getClass().getSimpleName());
					break;
				} else if (MainFrame.ME.tirei.isSelected()
						&& MainFrame.ME.tirei.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.ENEMY_CHECK_LIST.add(em.getClass().getSimpleName());
					break;
				} else if (MainFrame.ME.seiren.isSelected()
						&& MainFrame.ME.seiren.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.ENEMY_CHECK_LIST.add(em.getClass().getSimpleName());
					break;
				} else if (MainFrame.ME.kouma.isSelected()
						&& MainFrame.ME.kouma.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.ENEMY_CHECK_LIST.add(em.getClass().getSimpleName());
					break;
				} else if (MainFrame.ME.huuzin.isSelected()
						&& MainFrame.ME.huuzin.getActionCommand().matches(
								st.ONE_NAME)) {
					QS.ENEMY_CHECK_LIST.add(em.getClass().getSimpleName());
					break;
				}
			}
		}
	}

	public static void setObjectClip(String str) {
		String category = QS.category_position;
		SetOnMapObject mo = null;
		if (category.matches("エネミー")) {
			Base_Enemy e = StringIsChangedToObject
					.returnBaseEnemy(str, 1, true);
			mo = new SetOnMapObject(e);
		} else if (category.matches("トラップ")) {
			Base_Trap tr = StringIsChangedToObject.returnBaseTrap(str, true);
			mo = new SetOnMapObject(tr);
		} else if (category.matches("ディスク")) {
			Base_Artifact a = StringIsChangedToObject.returnBaseDisc(str,
					getCategoryNames(category), true);
			mo = new SetOnMapObject(a);
		} else {
			Base_Artifact a = StringIsChangedToObject.returnBaseItem(str,
					getCategoryNames(category), true);
			mo = new SetOnMapObject(a);
		}
		QS.select_clip_object = mo;
	}
}
