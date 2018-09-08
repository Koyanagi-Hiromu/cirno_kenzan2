package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.table.ItemDetail;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.artifact.item.staff.いかづちの杖;
import dangeon.model.object.artifact.item.staff.パチュリーの杖;
import dangeon.model.object.artifact.item.staff.封印の杖;
import dangeon.model.object.artifact.item.staff.氷の杖;
import dangeon.model.object.artifact.item.staff.炎の杖;
import dangeon.model.object.artifact.item.staff.金縛りの杖;
import dangeon.model.object.artifact.item.staff.鈍足の杖;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.anime.Special_Wait_FrameByFrame;

public class パチュリー extends Base_Enemy {

	private static final long serialVersionUID = 1L;
	private Staff STAFF, S[];

	private DIRECTION dir;

	public パチュリー(Point p, int Lv) {
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
							Staff s = null;
							String n = "";
							int rdm = -1;
							if (LV == 1) {
								if (Player.me.conditionCheck(CONDITION.麻痺)) {
									rdm = 1;
								} else {
									s = STAFF;
									n = "麻痺";
								}
							} else if (LV == 2) {
								if (Player.me.conditionCheck(CONDITION.封印)) {
									rdm = new R().nextInt(2);
								} else {
									s = STAFF;
									n = "封印";
								}
							} else if (LV == 3) {
								if (Player.me.conditionCheck(CONDITION.鈍足)) {
									rdm = new R().nextInt(3);
								} else {
									s = STAFF;
									n = "鈍足";
								}
							} else {
								if (Player.me.conditionCheck(CONDITION.封印)
										&& Player.me
												.conditionCheck(CONDITION.鈍足)
										&& Player.me
												.conditionCheck(CONDITION.麻痺)) {
									rdm = new R().nextInt(3);
								} else {
									s = STAFF;
									n = "悪魔";
								}
							}
							if (s == null) {
								s = S[rdm];
								n = new String[] { "雷", "氷", "炎" }[rdm];
							}
							Message.set(getColoredName(), "は", n, "のページを開いた");
							s.enemyStaffUse(C);
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
		if (LV != 4)
			return STAFF;
		else
			return ItemTable.itemReturn(mass_point, ItemDetail.STAFF);
	}

	@Override
	public Image getImage() {
		return super.getImage();
	}

	@Override
	public int getItemDropParcent() {
		return super.getItemDropParcent() / 4;
	}

	@Override
	protected void init1() {
		STAFF = new 金縛りの杖(getMassPoint());
		S = new Staff[] { new いかづちの杖(getMassPoint()), new 氷の杖(mass_point),
				new 炎の杖(mass_point) };
		S[1].freezeCountReset();
		S[1].setFlagNoFrozen(true);
	}

	@Override
	protected void init2() {
		STAFF = new 封印の杖(getMassPoint());
		S = new Staff[] { new いかづちの杖(getMassPoint()), new 氷の杖(mass_point),
				new 炎の杖(mass_point) };
		S[1].freezeCountReset();
		S[1].setFlagNoFrozen(false);
	}

	@Override
	protected void init3() {
		STAFF = new 鈍足の杖(getMassPoint());
		S = new Staff[] { new いかづちの杖(getMassPoint()), new 氷の杖(mass_point),
				new 炎の杖(mass_point) };
		S[1].freezeCountReset();
		S[1].setFlagNoFrozen(false);
	}

	@Override
	protected void init4() {
		STAFF = new パチュリーの杖(getMassPoint());
		S = new Staff[] { new いかづちの杖(getMassPoint()), new 氷の杖(mass_point, true),
				new 炎の杖(mass_point) };
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
