package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.Iterator;
import java.util.List;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.creature.Base_Creature;
import dangeon.util.R;
import dangeon.view.anime.OuraEffect;
import dangeon.view.anime.Special_Wait_FrameByFrame;
import dangeon.view.detail.MainMap;

public class 秦こころ extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 秦こころ(Point p, int Lv) {
		super(p, Lv);
	}

	private Base_Creature getCreature() {
		List<Base_Enemy> list = MapList.getListAroundEnemy(mass_point);
		for (Iterator<Base_Enemy> iterator = list.iterator(); iterator
				.hasNext();) {
			if (iterator.next().conditionCheck(CONDITION.ええんじゃないか)) {
				iterator.remove();
			}
		}
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(new R().nextInt(list.size()));
		}
	}

	@Override
	protected void init1() {
		setHalfWalk();
	}

	@Override
	protected void init2() {
		setHalfWalk();
	}

	@Override
	protected void init3() {
		setHalfWalk();
	}

	@Override
	protected void init4() {
		setHalfWalk();
	}

	@Override
	protected boolean specialAttack() {
		final Base_Creature c = getCreature();
		if (c != null) {
			SE.SYSTEM_USING_RING.play();
			this.direction = DIRECTION.getDirection(this, c);
			c.startDamaging();
			MainMap.addEffect(new OuraEffect(c, true), true);
			MainMap.addEffect(new OuraEffect(this, false), false);
			this.setAnimation(new Special_Wait_FrameByFrame(this, null, 1,
					new Task() {
						private static final long serialVersionUID = 1L;

						@Override
						public void work() {
							zetubo(c);
						}
					}, 0, 0, 1, 1, 2, 2, 3, 3, 2, 2, 3, 3, 2, 2, 3, 3));
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		return isSpecialParcent() && getCreature() != null;
	}

	protected void zetubo(Base_Creature c) {
		CONDITION.wakeUp(c);
		c.setCondition(CONDITION.ええんじゃないか, 0);
		// boolean flag = false;
		// flag = (conditionCheck(CONDITION.鈍足));
		// if (LV == 2) {
		// flag = !conditionCheck(CONDITION.倍速)
		// && !conditionCheck(CONDITION.三倍速);
		// } else if (LV > 2) {
		// flag = !conditionCheck(CONDITION.三倍速);
		// }
		// if (flag) {
		// SE.STATUS_SPEEDY.play();
		// setCondition(CONDITION.倍速, 0);
		CONDITION.conditionAllClear(this);
		chengeHP(null, null, getMAX_HP());
		Message.set(getColoredName(), "は", c.getColoredName(),
				"から希望の面を剥ぎとって本来の動きに戻った");
		// } else {
		// Message.set(getColoredName(), "は", c.getColoredName(),
		// "から希望の面を剥ぎとった");
		// }
	}

}
