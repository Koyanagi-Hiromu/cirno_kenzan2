package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.artifact.item.staff.邪法の杖;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.anime.Special_Wait_FrameByFrame;
import dangeon.view.util.WithinOutofScreen;

public class 霍青娥 extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private static final int length1 = 2;
	private static final int length2 = 5;
	private static final int length3 = 10;

	private final Staff STAFF;

	public 霍青娥(Point p, int Lv) {
		super(p, Lv);
		int turn;
		if (Lv == 1)
			turn = 10;
		else if (Lv == 2)
			turn = 15;
		else
			turn = 20;
		STAFF = new 邪法の杖(mass_point, turn);
	}

	private boolean check() {
		return !getTargetList().isEmpty();
	}

	private void effect() {
		ArrayList<Base_Creature> list = getTargetList();
		direction = DIRECTION.getDirection(this,
				list.get(new R().nextInt(list.size())));
		Message.set(this.getColoredName(), "は邪法を唱えた");
		if (!WithinOutofScreen.isOutside(this)) {
			final Base_Creature ME = this;
			setAnimation(new Special_Wait_FrameByFrame(this, SE.YUYUKO_SPELL,
					0, new Task() {

						/**
				 *
				 */
						private static final long serialVersionUID = 1L;

						@Override
						public void work() {
						}

						@Override
						protected void work(int frame) {
							if (frame == 4)
								STAFF.enemyStaffUse(ME);
						}
					}, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 0, 0, 0));

		} else {
			STAFF.enemyStaffUse(this);
		}
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		STAFF.staffUse();
		return true;
	}

	@Override
	public int getFootDeltY() {
		return -5;
	}

	@Override
	public int getShadowSize100() {
		return 20;
	}

	private ArrayList<Base_Creature> getTargetList() {
		int length = length();
		int x = getMassPoint().x;
		int y = getMassPoint().y;
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		for (DIRECTION direction : DIRECTION.values_exceptNeatral()) {
			for (int i = 1; i < length + 1; i++) {
				Base_Creature c = MapList.getCreature(x + direction.X * i, y
						+ direction.Y * i);
				if (c != null)
					if (c instanceof Player || c.getGhost() != 1)
						if (!c.conditionCheck(CONDITION.邪法))
							list.add(c);
			}
		}
		return list;
	}

	private int length() {
		if (LV == 1) {
			return length1;
		} else if (LV == 2) {
			return length2;
		} else if (LV == 3) {
			return length3;
		} else {
			return Math.max(MassCreater.HEIGHT, MassCreater.WIDTH);
		}
	}

	@Override
	protected boolean specialAttack() {
		if (check()) {
			effect();
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (!isSpecialParcent()) {
			return false;
		}
		if (!check()) {
			return false;
		}
		return true;
	}

}
