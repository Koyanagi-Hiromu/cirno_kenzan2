package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.grass.すばやさ草;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.anime.CameraEffect;
import dangeon.view.detail.MainMap;

public class 姫海棠はたて extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public static boolean isShotSpoit(Point p, Base_Artifact a) {
		boolean flag = false;
		for (Base_Enemy em : MapList.getListEnemy()) {
			if (em instanceof 姫海棠はたて) {
				if (MassCreater.isPointInTheSameRoomInEntrance(
						em.getMassPoint(), p)
						&& ((姫海棠はたて) em).shotSpoit()) {
					((姫海棠はたて) em).shotSpoit(p, a);
					flag = true;
				}
			}
		}
		return flag;
	}

	private int count_max;

	private int count = 0;

	public 姫海棠はたて(Point p, int Lv) {
		super(p, Lv);

	}

	private void countSet() {
		count_max = (LV != 4) ? LV : 9999;
	}

	@Override
	protected Point getAttackPoint() {
		return screen_point;
	}

	@Override
	protected Base_Artifact getDropItem() {
		return new すばやさ草(mass_point.getLocation());
	}

	@Override
	protected CONDITION getInitCondition() {
		countSet();
		return CONDITION.倍速;
	}

	@Override
	protected void init1() {
	}

	@Override
	protected void init2() {
	}

	@Override
	protected void init3() {
	}

	@Override
	protected void init4() {
		setCondition(CONDITION.透明, 0);
	}

	@Override
	protected int itemDropParcent() {
		return super.itemDropParcent();
	}

	public boolean shotSpoit() {
		if (!isSkillActive()) {
			return false;
		} else if (count >= count_max) {
			return false;
		} else {
			if (new R().is(30)) {
				count += count_max;
			} else {
				count++;
			}
			return true;
		}
	}

	public void shotSpoit(final Point p, final Base_Artifact a) {
		direction = DIRECTION.getDirection(this.getMassPoint(), p);
		final 姫海棠はたて THIS = this;
		super.startAttack(new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				MainMap.addEffect(new CameraEffect(THIS.getMassPoint()
						.getLocation(), p));
				if (count >= count_max) {
					if (LV == 1) {
						Message.set(a.getColoredName(), "は激写された！");
					}
					Message.set(getColoredName(), "の取材用携帯の電池が切れた");
				} else {
					Message.set(a.getColoredName(), "は激写された！");
				}
			}
		});

	}

	@Override
	protected boolean specialAttack() {
		if (attack_possible()) {
			quick_one_attack = true;
			direction = converDirection(Player.me.getMassPoint());
			attack();
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (attack_possible()) {
			return true;
		}
		return false;
	}

	@Override
	public void startAttack(Task t) {
		MainMap.addEffect(new CameraEffect(this.getMassPoint().getLocation(),
				this.direction.getFrontPoint(mass_point.getLocation())));
		super.startAttack(t);
	}

	@Override
	protected void upDate_NormalAttack() {
		super.upDate_NormalAttack();
	}

}
