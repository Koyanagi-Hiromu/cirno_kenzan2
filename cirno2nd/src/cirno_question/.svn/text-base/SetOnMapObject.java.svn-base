package cirno_question;

import java.util.ArrayList;

import cirno_question.frame.MainFrame;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.pot.Base_Pot;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class SetOnMapObject {

	private Base_Artifact a;
	private Base_Enemy em;
	private Base_Trap tr;
	public int level = 1;
	public int forge = 0;
	public boolean angry = false;
	public boolean sleep = false;
	public boolean curse = false;
	public boolean enter = false;
	public boolean exit = false;

	public SetOnMapObject(Base_Artifact a) {
		this.a = a;
	}

	/**
	 * アイテム　修正値なし
	 * 
	 * @param a
	 * @param b
	 */
	public SetOnMapObject(Base_Artifact a, boolean b) {
		this.a = a;
		this.curse = b;
	}

	/**
	 * アイテム　修正値あり
	 * 
	 * @param a
	 * @param i
	 * @param b
	 */
	public SetOnMapObject(Base_Artifact a, int i, boolean b) {
		this.a = a;
		this.forge = i;
		this.curse = b;

	}

	public SetOnMapObject(Base_Enemy em) {
		this.em = em;
	}

	/**
	 * エネミー生成
	 * 
	 * @param em
	 * @param lv
	 * @param sleep
	 * @param angry
	 */
	public SetOnMapObject(Base_Enemy em, int lv, boolean sleep, boolean angry) {
		this.em = em;
		this.level = lv;
		this.sleep = sleep;
		this.angry = angry;
	}

	public SetOnMapObject(Base_MapObject bmo) {
		if (bmo instanceof Base_Enemy) {
			setBaseObject((Base_Enemy) bmo);
		} else if (bmo instanceof Base_Item) {
			setBaseObject((Base_Item) bmo);
		} else if (bmo instanceof Base_Trap) {
			setBaseObject((Base_Trap) bmo);

		}
	}

	/**
	 * トラップ
	 * 
	 * @param a
	 */
	public SetOnMapObject(Base_Trap a) {
		tr = a;
	}

	// public MassCategory getCategory() {
	// if (a != null) {
	// return MassCategory.ITEM;
	// } else if (em != null) {
	// return MassCategory.ENEMY;
	// } else if (tr != null) {
	// return MassCategory.TRAP;
	// }
	// return null;
	// }

	public SetOnMapObject(boolean enter, boolean exit) {
		this.enter = enter;
		this.exit = exit;
	}

	public Base_Artifact getArtifact() {
		if (a != null) {
			return a;
		} else if (tr != null) {
			return tr;
		}
		return null;
	}

	public Base_Enemy getEnemy() {
		if (em != null) {
			return em;
		}
		return null;
	}

	public Base_Artifact getItem() {
		if (a != null) {
			return a;
		}
		return null;
	}

	public String getName() {
		if (a != null) {
			return a.getName();
		} else if (em != null) {
			return em.getName();
		} else if (tr != null) {
			return tr.getName();
		} else
			return "うんこ";
	}

	public ArrayList<String> getObjectDetail() {
		ArrayList<String> list = new ArrayList<String>();
		if (a != null) {
			list.add(a.getName());
			if (a instanceof SpellCard) {
				list.add("修正値：".concat(String.valueOf(forge)));
			} else if (a instanceof Staff || a instanceof Base_Pot) {
				list.add("容量：".concat(String.valueOf(forge)));
			}
			list.add(curse ? "呪：有" : "呪：無");
		} else if (em != null) {
			list.add(em.getName());
			list.add(String.valueOf(level));
			list.add((sleep ? "【仮眠】" : "").concat((angry ? "【怒り】" : "")));
		} else if (tr != null) {
			list.add(tr.getName());
		} else {
			if (enter) {
				list.add("入口");
			} else {
				list.add("出口");
			}
		}
		return list;
	}

	public SetOnMapObject getSetOnMapObject() {
		if (QS.select_clip_object == null) {
			return null;
		}
		SetOnMapObject mo = null;
		if (em != null) {
			int level = 0;
			boolean sleep = false;
			boolean angry = false;
			String s = "";
			level = Integer.parseInt(MainFrame.ME.lv.getSelectedItem()
					.toString());
			s = MainFrame.ME.con.getSelectedItem().toString();
			if (s.matches("仮眠")) {
				sleep = true;
			} else if (s.matches("仮眠and怒り")) {
				sleep = true;
				angry = true;
			} else if (s.matches("怒り")) {
				angry = true;
			}
			mo = new SetOnMapObject(em, level, sleep, angry);
		} else if (a != null) {
			int forge = 0;
			if (MainFrame.ME.forge.getText().length() != 0) {
				forge = Integer.parseInt(MainFrame.ME.forge.getText());
			}
			mo = new SetOnMapObject(a, forge, MainFrame.ME.jc.isSelected());
		} else if (tr != null) {
			mo = this;
		} else {
			mo = this;
		}
		return mo;
	}

	public Base_Artifact getTrap() {
		if (tr != null) {
			return tr;
		}
		return null;
	}

	public void setBaseObject(Base_Enemy em) {
		this.em = em;
		this.level = em.getLV();
		this.sleep = em.getConditionList().contains(CONDITION.特殊仮眠);
		this.angry = em.getConditionList().contains(CONDITION.イカリ);
	}

	public void setBaseObject(Base_Item im) {
		this.a = im;
		this.curse = im.isCurse();
		if (im instanceof SpellCard) {
			this.forge = im.getForgeValue();
		} else if (im instanceof Staff) {
			this.forge = im.getStaffRest();
		} else if (im instanceof Base_Pot) {
			this.forge = ((Base_Pot) im).getMaxSize();
		} else if (im instanceof Arrow) {
			this.forge = ((Arrow) im).getArrowRest();
		}
	}

	public void setBaseObject(Base_Trap tr) {
		this.tr = tr;
	}
}
