package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import main.res.SE;
import main.util.FileReadSupporter;
import main.util.Show;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.resistant.ResistWeakPoint;
import dangeon.util.Damage;
import dangeon.util.ThunderDamage;

public class 雲居一輪 extends Base_Enemy {

	private enum FORM {
		地震, 雷, 火事
	}

	private static final long serialVersionUID = 1L;

	private FORM present_form = FORM.地震;

	public 雲居一輪(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public SE getAttackSE() {
		if (getAnimation() == null) {
			return SE.ATTACK_FIRE;
		} else {
			return null;
		}
	}

	@Override
	public int getMaxLV() {
		return 99;
	}

	// @Override
	// public Image getImage() {
	// // int LV = this.LV;
	// // if (LV <= 6) {
	// // LV = 1;
	// // } else if (LV <= 12) {
	// // LV = 2;
	// // } else {
	// // LV = 3;
	// // }
	// // if (isAttacking()) {
	// // return getATKImage(LV, attacking_direction, attack_No);
	// // } else if (flag_damaging) {
	// // return IM.getDamImage(LV, direction);
	// // }
	// // return IM.getWalkImage(LV, direction, getMoveAnimationSpeed());
	// }
	//
	// @Override
	// protected Point getAttackPoint() {
	// if (getAnimation() == null) {
	// return super.getAttackPoint();
	// } else {
	// return new Point(screen_point.x + attack_delt_point.x / 2,
	// screen_point.y + attack_delt_point.y / 2);
	// }
	// }

	@Override
	public boolean resistOrWeakAction(ResistWeakPoint res, int damage) {
		switch (res) {
		case 炎耐性:
			if (present_form == FORM.火事) {
				addLV(1);
				return true;
			}
			break;
		case 雷耐性:
			if (present_form == FORM.雷) {
				chengeHP(null, null, damage);
			}
			break;
		}
		return false;
	}

	@Override
	public void setNameAndStatus() {
		if (LV < 1) {
			super.setNameAndStatus();
			return;
		}
		try {
			setStateChengeByLevelUp();
			InputStreamReader read;
			read = FileReadSupporter.readUTF8("res/enemyTable/enemy.csv");
			BufferedReader br = new BufferedReader(read);
			String line = "";
			String line2 = "名前;0;0;0;0";
			String child = ";";
			boolean roop = true;
			while (roop && (line = br.readLine()) != null) {
				String clazz = line.substring(0, line.indexOf(child));
				if (clazz.isEmpty())
					continue;
				clazz = line.substring(0, line.indexOf("("));
				if (!clazz.matches(getClass().getSimpleName()))
					continue;
				setSpecialAttackParcent(Integer.valueOf(line.substring(
						line.indexOf("(") + 1, line.indexOf("%)"))));
				for (int i = 0; i < LV - 1; i++) {
					line2 = br.readLine();
				}
				StringTokenizer st = new StringTokenizer(line, child);
				StringTokenizer st2 = new StringTokenizer(line2, child);
				st.nextToken();
				st2.nextToken();
				name = st.nextToken().concat(String.valueOf(LV));
				setFirstStatus(
						Integer.valueOf(st.nextToken())
								+ Integer.valueOf(st2.nextToken()) * LV,
						Integer.valueOf(st.nextToken())
								+ Integer.valueOf(st2.nextToken()) * LV,
						Integer.valueOf(st.nextToken())
								+ Integer.valueOf(st2.nextToken()) * LV);
				setENEMY_EXP(Integer.valueOf(st.nextToken()) * LV);
				roop = false;
				break;
			}
			if (roop)
				super.setNameAndStatus();
			br.close();
		} catch (Exception e) {
			Show.showCriticalErrorMessageDialog(e);
		}
	}

	@Override
	protected void setStateChengeByLevelUp() {
		switch ((LV + 3) % 3) {
		case 1:
			present_form = FORM.地震;
			break;
		case 2:
			present_form = FORM.雷;
			break;
		case 0:
			present_form = FORM.火事;
			setConditionList(CONDITION.炎上, 0);
			break;
		}
	}

	/*
	 * @Override protected List<Integer> statusLevel() {
	 * setConditionList(CONDITION.炎上, 0); List<Integer> list = new
	 * ArrayList<Integer>(); list.add(HP + (LV * 5)); list.add(STR + (LV * 2));
	 * list.add(DEF + LV); list.add(EXP * LV); return list; }
	 */
	@Override
	protected boolean specialAttack() {
		ArrayList<Base_Creature> _list = new ArrayList<Base_Creature>();
		_list.add(Player.me);
		for (Base_Creature c : MapList.getListCreature()) {
			if (MassCreater.isPointInTheSameRoom(getMassPoint(),
					c.getMassPoint())) {
				_list.add(c);
			}
		}
		if (MassCreater.isPlayerInTheSameRoom(getMassPoint())) {
			switch (present_form) {
			case 地震:
				Message.set(getColoredName(), "は地震を引き起こした");
				for (Base_Creature c : _list) {
					Damage.damage(this, null, "地震によって", this, c, 25 + LV);
				}
				break;
			case 雷:
				Message.set(getColoredName(), "は自分に雷を落とした");
				ThunderDamage.thunderDamage(this, this, this, 30 + LV * 3);
				break;
			case 火事:
				Message.set(getColoredName(), "は火事を引き起こした");
				for (Base_Creature c : _list) {
					if (c instanceof Player) {
						Damage.damage(this, null, "火事によって", this, c, 25 + LV);
					} else {
						c.setCondition(CONDITION.炎上, 0);
					}
				}
				break;
			}
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (isSpecialParcent()) {
			if (MassCreater.isPlayerInTheSameRoom(getMassPoint())) {
				return true;
			}
		}
		return false;
	}
}
