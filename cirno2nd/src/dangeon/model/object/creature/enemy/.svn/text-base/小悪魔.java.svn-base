package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.artifact.item.staff.大悪魔の杖;
import dangeon.model.object.artifact.item.staff.小悪魔の杖;
import dangeon.model.object.artifact.item.staff.悪魔の杖;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.view.anime.Special_Wait_FrameByFrame;

public class 小悪魔 extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private Staff STAFF;

	private DIRECTION dir;

	public 小悪魔(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		if (!player_is_in_sight) {
			return false;
		}
		Point player_point = Player.me.getMassPoint().getLocation();
		Point p = getMassPoint();
		if (p.x == player_point.x) {
			if (p.y > player_point.y) {
				dir = DIRECTION.UP;
			} else {
				dir = DIRECTION.DOWN;
			}
		} else if (p.y == player_point.y) {
			if (p.x > player_point.x) {
				dir = DIRECTION.LEFT;
			} else {
				dir = DIRECTION.RIGHT;
			}
		} else if (Math.abs(p.x - player_point.x) == Math.abs(p.y
				- player_point.y)) {
			if (p.x > player_point.x) {
				if (p.y > player_point.y) {
					dir = DIRECTION.UP_LEFT;
				} else {
					dir = DIRECTION.DOWN_LEFT;
				}
			} else {
				if (p.y > player_point.y) {
					dir = DIRECTION.UP_RIGHT;
				} else {
					dir = DIRECTION.DOWN_RIGHT;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public boolean effect() {
		direction = dir;
		Base_Creature bs = MapInSelect.getStraightHitCreature(direction,
				getMassPoint(), 0);
		if (bs == null) {
			return false;
		}
		direction = converDirection(bs.getMassPoint());
		final Base_Creature C = this;
		setAnimation(new Special_Wait_FrameByFrame(this, SE.SYSTEM_SCROLL, 1,
				new Task() {
					/**
			 *
			 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
					};

					@Override
					public void work(int frame) {
						if (frame == 3) {
							String how;
							if (LV == 1)
								how = "たどたどしく";
							else if (LV == 2)
								how = "慣れた手つきで";
							else if (LV == 3)
								how = "にこやかに";
							else
								how = "笑って";
							Message.set(getColoredName(), "は", how, "ページを開いた");
							STAFF.enemyStaffUse(C);
						}
					}
				}, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1));
		return true;
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		STAFF.staffUse();
		return true;
	}

	@Override
	protected Base_Artifact getDropItem() {
		return new 悪魔の杖(getMassPoint());
	}

	@Override
	public Image getImage() {
		if (isAttacking()) {
			int a = attack_No;
			if (a > 1)
				a = 1;
			return getATKImage(LV, attacking_direction, a);
		} else
			return super.getImage();
	}

	// public Image getImage() {
	// return main.res.IMAGE.幽香.getImage(direction, MainThread.getKoma());
	// }

	@Override
	protected void init1() {
		STAFF = new 小悪魔の杖(getMassPoint());
	}

	@Override
	protected void init2() {
		STAFF = new 悪魔の杖(getMassPoint());
	}

	@Override
	protected void init3() {
		STAFF = new 大悪魔の杖(getMassPoint());
	}

	@Override
	protected void init4() {
		STAFF = new 大悪魔の杖(getMassPoint());
		set2timesWalk();
	}

	private boolean railgun() {
		if (!player_is_in_sight) {
			return false;
		}
		effect();
		return true;
	}

	@Override
	protected boolean specialAttack() {
		if (!check()) {
			return false;
		}
		if (special_ok) {
			if (railgun()) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean specialCheck() {
		if (!check()) {
			return false;
		}
		if (isSpecialParcent()) {
			return true;
		}
		return false;
	}

}
