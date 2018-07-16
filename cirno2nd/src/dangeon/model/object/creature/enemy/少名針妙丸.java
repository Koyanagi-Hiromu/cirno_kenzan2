package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.anime.Special_Wait_FrameByFrame;
import dangeon.view.util.WithinOutofScreen;

public class 少名針妙丸 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 少名針妙丸(Point p, int Lv) {
		super(p, Lv);
	}

	protected void bigger(ArrayList<Base_Enemy> list) {
		Base_Enemy c = getTargetList().get(
				new R().nextInt(getTargetList().size()));
		SE.IKAKUTYO.play();
		// if (LV == 3 || (LV == 2 && c.getLargement() == 1)) {
		// c.changeSize(2);
		// Message.set(c.getColoredName(), "はすごく大きくなった");
		// } else
		{
			c.changeSize(1);
			Message.set(c.getColoredName(), "は大きくなった");
		}
		CONDITION.conditionRecovery(c, CONDITION.仮眠);
	}

	private boolean check(Base_Enemy e) {
		if (e instanceof 少名針妙丸) {
			return false;
		}
		int largement = e.getLargement();
		switch (LV) {
		case 2:
		case 3:
			// return largement == 0 || largement == 1;
		default:
			return largement == 0;
		}
	}

	public boolean effect(Player p) {
		return true;
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		// FIXME
		Message.set("未実装");
		return true;
	}

	@Override
	public Image getImage() {
		return super.getImage();
	}

	public ArrayList<Base_Enemy> getTargetList() {
		ArrayList<Base_Enemy> list = new ArrayList<Base_Enemy>();
		if (LV == 4) {
			for (Base_Creature c : MapInSelect
					.getListRoomInCreatureAtPoint(getMassPoint())) {
				if (c instanceof Base_Enemy && !(c instanceof Base_NPC)) {
					list.add((Base_Enemy) c);
				}
			}
		} else {
			for (Base_Creature c : MapList
					.getListAroundCreature(getMassPoint())) {
				if (c instanceof Base_Enemy && !(c instanceof Base_NPC)) {
					list.add((Base_Enemy) c);
				}
			}
		}
		for (Iterator<Base_Enemy> iterator = list.iterator(); iterator
				.hasNext();) {
			Base_Enemy e = iterator.next();
			if (!check(e))
				iterator.remove();
		}
		return list;
	}

	@Override
	protected void init1() {
		changeSize(-1);
	}

	@Override
	protected void init2() {
		changeSize(-1);
	}

	@Override
	protected void init3() {
		changeSize(-1);
	}

	@Override
	protected void init4() {
		changeSize(-1);
	}

	@Override
	protected boolean specialAttack() {
		final ArrayList<Base_Enemy> list = getTargetList();
		if (list.isEmpty())
			return false;
		SE.BIGGER.play();
		Message.set(getColoredName(), "は打ち出の小槌を振るった");
		if (WithinOutofScreen.isOutside(this)) {
			bigger(list);
		} else {
			this.setAnimation(new Special_Wait_FrameByFrame(this, null, 1,
					new Task() {
						private static final long serialVersionUID = 1L;

						@Override
						public void work() {
							bigger(list);
						}
					}, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0));
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		return isSpecialParcent() && !getTargetList().isEmpty();
	}
}
