package dangeon.model.object.creature.player.class_job;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusSpecialAction;
import dangeon.view.detail.View_Sider;
import dangeon.view.util.StringFilter;
import main.res.BGM;
import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.util.Show;

public abstract class BaseClassJob implements Serializable {
	private static final long serialVersionUID = 1L;
	public final SpellCard s;

	private ArrayList<ENCHANT_SIMBOL> list_enchant_simbol = new ArrayList<ENCHANT_SIMBOL>();

	private final HashMap<Integer, Boolean> swich_map = new HashMap<Integer, Boolean>(
			5);

	private BonusSpecialAction sp_action = null;

	protected int lv = 0;

	public final HashMap<CONDITION, Integer> condition_map = new HashMap<CONDITION, Integer>();

	public final ArrayList<Task> end_task_map = new ArrayList<Task>();

	public BaseClassJob() {
		s = getRep();
		if (getMedal_ミラクルクエスト() != null)
			getMedal_ミラクルクエスト().saveLevel(5, 1);
		
		if (getMedal_七曜クエスト() != null)
			getMedal_七曜クエスト().saveLevel(5, 1);
	}

	public void addCondition(CONDITION condition, int time) {
		condition_map.put(condition, time);
	}

	public void addEndTask(Task task) {
		end_task_map.add(task);
	}

	public int addLv(ArrayList<Base_Artifact> list) {
		setSaisenListLv(++lv, list);
		return lv;
	}

	protected abstract void bonusLv1(ArrayList<BaseBonus> l, Point p);

	protected abstract void bonusLv2(ArrayList<BaseBonus> l, Point p);

	protected abstract void bonusLv3(ArrayList<BaseBonus> l, Point p);

	public boolean checkSimbol(ENCHANT_SIMBOL e) {
		return list_enchant_simbol.contains(e);
	}

	public boolean checkSpec() {
		return sp_action != null;
	}

	public boolean checkSwicth(int child) {
		Boolean b = swich_map.get(child);
		return b != null && b;
	}

	public String className() {
		return this.getClass().getSimpleName().substring(5);
	}
	
	public abstract Medal getMedal_ミラクルクエスト();
	public abstract Medal getMedal_七曜クエスト();

	protected abstract void firstDemerite(ArrayList<BaseBonus> l, Point p);

	protected abstract void firstMerite(ArrayList<BaseBonus> l, Point p);

	public BGM getBGM() {
		if (s == null) {
			return BGM.kanpyo_ch2_fff;
		} else {
			return BGM.get(s.getClass());
		}
	}

	public CHARA_IMAGE getCharaImage() {
		try {
			Constructor<? extends Base_Enemy> con = s.getStand()
					.getConstructor(Point.class, int.class);
			Base_Enemy obj = con.newInstance(s.getMassPoint(), 0);
			return obj.getCharacterImage();
		} catch (Exception e) {
			e.printStackTrace();
			Show.showErrorMessageDialog(e);
		}
		return null;
	}

	public String getExn() {
		return "";
	}

	public Image_LargeCharacter getIMLC() {
		if (s != null)
			return s.IMLC;
		else
			return null;
	}

	public int getLv() {
		return lv;
	}

	protected abstract SpellCard getRep();

	public String[] getSelectingExn() {
		ArrayList<String> list = new ArrayList<String>();
		Color[] cs = { Color.ORANGE, Color.RED, StringFilter.NUMBERS,
				Color.WHITE, Color.WHITE, Color.WHITE };
		list.add("クラス　" + cs[0] + "【" + className() + "】");
		Point p = Player.me.getMassPoint();
		ArrayList<BaseBonus> l;
		for (int i = 1; i < cs.length; i++) {
			String intend;
			l = new ArrayList<BaseBonus>();
			if (i == 1) {
				intend = "代償　　";
				firstDemerite(l, p);
			} else if (i == 2) {
				intend = "ボーナス";
				firstMerite(l, p);
			} else if (i == 3) {
				intend = "ＬＶ１　";
				bonusLv1(l, p);
			} else if (i == 4) {
				intend = "ＬＶ２　";
				bonusLv2(l, p);
			} else if (i == 5) {
				intend = "ＬＶ３　";
				bonusLv3(l, p);
			} else {
				intend = "";
			}
			if (l.isEmpty()) {
				list.add(cs[i] + intend + "（なし）");
			} else {
				for (BaseBonus baseBonus : l) {
					list.add(cs[i] + intend + "【" + baseBonus.getEffectExn()
							+ cs[i] + "】");
					intend = "　　　　";
				}
			}
		}
		return list.toArray(new String[0]);
	}

	/**
	 * 中身をいじらないようにね
	 * 
	 * @return
	 */
	public ArrayList<ENCHANT_SIMBOL> getSimbols() {
		return list_enchant_simbol;
	}

	public abstract String getSwitchExp();

	public void newMapAction() {
		for (CONDITION c : condition_map.keySet()) {
			Player.me.setConditionNeglectResist(c, condition_map.get(c));
		}
	}

	public int setLv(int lv) {
		return this.lv = lv;
	}

	public void setSaisenListLv(int lv, ArrayList<Base_Artifact> list) {
		Point p = Player.me.getMassPoint();
		ArrayList<BaseBonus> l = new ArrayList<BaseBonus>();
		if (lv == 0) {
			firstDemerite(l, p);
			firstMerite(l, p);
		} else if (lv == 1) {
			bonusLv1(l, p);
		} else if (lv == 2) {
			bonusLv2(l, p);
		} else if (lv == 3) {
			bonusLv3(l, p);
		}
		if (!l.isEmpty()) {
			View_Sider.setInformation("以下の効果を得ました");
			for (BaseBonus baseBonus : l) {
				baseBonus.excute(list, list_enchant_simbol);
				View_Sider.setInformation("【" + baseBonus.getEffectExn() + "】");
			}
		}
	}

	public void setSwichON(BonusSpecialAction bonusSpecialAction) {
		sp_action = bonusSpecialAction;
	}

	public void setSwichON(int child) {
		swich_map.put(child, true);
	}

	public void specExcute() {
		if (sp_action != null) {
			sp_action.excute();
		}
	}

	public void turnEndAction() {
		for (Task task : end_task_map) {
			task.work();
		}
	}

}
