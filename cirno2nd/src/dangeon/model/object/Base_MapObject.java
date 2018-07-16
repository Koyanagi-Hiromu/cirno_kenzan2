package dangeon.model.object;

import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;

import main.constant.FR;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.ThrowingItem;
import dangeon.controller.task.Task;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.EnemyFall;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.ObjectPoint;
import dangeon.view.constant.MAP;
import dangeon.view.detail.MiniMap;
import dangeon.view.util.WithinOutofScreen;

public abstract class Base_MapObject implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected int LV = 1;
	protected Point mass_point;
	protected Point screen_point;
	protected boolean flag_move_animating = false;
	public DIRECTION direction = DIRECTION.DOWN;

	protected Point jump_point;
	public int jumped_away_y = 0, jumped_away_x_speed = 0,
			jumped_away_y_speed = 0, jumped_away_y_speed_shadow = 0;
	protected int jumped_away_frame = -1, jumped_away_frame_max = 0;

	protected boolean flag_jump_no_attack;

	/**
	 * mass_pointで指定される点から何pxずれてるかを表す
	 */
	protected Point delt = new Point(0, 0);

	/**
	 * 名前
	 */
	protected String name;

	protected Task at_movement_end_task = null;

	private int move_speed_level = -1;

	protected Base_MapObject(final String name, Point p, int level) {
		this.name = name;
		this.mass_point = p.getLocation();
		this.screen_point = ObjectPoint.getDangeonScreenPoint(mass_point);
		this.LV = level;

	}

	public void addDelt(DIRECTION direction) {
		addDelt(direction, 1);
	}

	public void addDelt(DIRECTION direction, int d) {
		switch (direction) {
		case DOWN:
			delt.y += d;
			break;
		case DOWN_LEFT:
			delt.x -= d;
			delt.y += d;
			break;
		case DOWN_RIGHT:
			delt.x += d;
			delt.y += d;
			break;
		case UP:
			delt.y -= d;
			break;
		case UP_LEFT:
			delt.x -= d;
			delt.y -= d;
			break;
		case UP_RIGHT:
			delt.x += d;
			delt.y -= d;
			break;
		case LEFT:
			delt.x -= d;
			break;
		case RIGHT:
			delt.x += d;
			break;
		}
	}

	public void endJump() {
		jump_point = null;
		jumped_away_y = 0;
		jumped_away_frame = -1;
	}

	protected void endParabonalJump() {
		Base_Creature c = MapList.getCreature(jump_point);
		ThrowingItem th = TaskOnMapObject.getThrow();
		if (c != null && c.conditionCheck(CONDITION.反射) && th != null
				&& !th.isReflected()) {
			th.setReflected(true);
			direction = direction.getReverse();
			jumped_away_y = -1;
			setMassPoint_ParabolaJumpAttack(mass_point);
			upDate_Jump();
		} else {
			if (at_movement_end_task != null) {
				Point p = mass_point;
				Point screen = screen_point.getLocation();
				setMassPoint(jump_point);
				at_movement_end_task.work();
				at_movement_end_task = null;
				setMassPoint(p);
				screen_point.x = screen.x;
				screen_point.y = screen.y;
			} else {
				setMassPoint(jump_point);
				if (!(this instanceof Base_Creature) || c == null || c == this) {
					endJump();
				} else {
					String s = "空中から敵にぶつかった衝撃で倒れた";
					Damage.damageNoMessage(this, s, c, (Base_Creature) this, 5);
					Damage.damageNoMessage(this, s, (Base_Creature) this, c, 5);
					jumped_away_y = -1;
					setMassPoint_ParabolaJumpAttack(EnemyFall
							.getPoint(jump_point));
					upDate_Jump();
				}
			}
		}
	}

	public void flagMoveAnimeating() {
		// if (!MassCreater.isTheObjectInsideOfScreen(this)) {
		// flag_move_animating = true;
		// upDate(0);
		// }
	}

	public String getColoredName() {
		return name;
	}

	public Point getDelt() {
		return delt;
	}

	public DIRECTION getDirection() {
		return direction;

	}

	public abstract Image getImage();

	public int getJumpDY() {
		return jumped_away_y;
	}

	public final int getLV() {
		return LV;
	}

	public Point getMassPoint() {
		return mass_point;
	}

	public int getMaxLV() {
		return isLvLimitBreak() ? 4 : 3;
	}

	public String getName() {
		return name;
	}

	public Point getScreenPoint() {
		return screen_point;
	}

	public boolean isAnimating() {
		if (isJumping()) {
			return true;
		} else {
			return flag_move_animating;
		}
	}

	public boolean isAnimeComletelyEnded() {
		return ObjectPoint.isNoDifferenceBetweenMass_Screen(this);
	}

	public boolean isJumping() {
		return jump_point != null || jumped_away_y != 0;
	}

	/**
	 * レベル４に上がる条件を満たしている場合はLV4になる
	 * 
	 * @return
	 */
	protected boolean isLvLimitBreak() {
		return LV == 4;
	}

	public boolean isMoving() {
		return flag_move_animating;
	}

	public void setMassPoint(int x, int y) {
		setMassPoint(new Point(x, y));
	}

	/**
	 * 瞬間移動
	 * 
	 * @param p
	 */
	public void setMassPoint(Point p) {
		this.mass_point = p.getLocation();
		this.screen_point = ObjectPoint.getDangeonScreenPoint(mass_point);
	}

	/**
	 * 
	 * @param p
	 */
	public void setMassPoint_Jump(Point p) {
		setMassPoint_Jump(p, null);
	}

	public void setMassPoint_Jump(Point p, Task task) {
		SE.WARP.play();
		jump_point = p.getLocation();
		jumped_away_frame = -1;
		jumped_away_y = 0;
		at_movement_end_task = task;
	}

	/**
	 * @param p
	 */
	public void setMassPoint_ParabolaJump_NoAttack(Point p) {
		setMassPoint_ParabolaJumpAttack(p);
		mass_point = p;
		flag_jump_no_attack = true;
	}

	public void setMassPoint_ParabolaJumpAttack(Point p) {
		setMassPoint_ParabolaJumpAttack(p, null);
	}

	/**
	 * jumped_away_y は呼ぶ前に設定すること
	 * 
	 * @param p
	 * @param task
	 */
	public void setMassPoint_ParabolaJumpAttack(Point p, Task task) {
		jump_point = p.getLocation();
		jumped_away_frame = 0;
		Point scp = ObjectPoint.getDangeonScreenPoint(jump_point);
		if (this == Player.me) {
			jumped_away_frame_max = 12;
		} else {
			jumped_away_frame_max = 8;
		}
		jumped_away_x_speed = (scp.x - screen_point.x)
				/ (jumped_away_frame_max + 1);
		jumped_away_y_speed = (scp.y - screen_point.y)
				/ (jumped_away_frame_max + 1);
		jumped_away_y_speed_shadow = -jumped_away_y
				/ (jumped_away_frame_max + 1);
		at_movement_end_task = task;
	}

	/**
	 * @param p
	 * @param move_speed_level
	 *            　1で普段と変わらず　２で２倍速で指定マスまで駆けつける
	 */
	public void setMassPoint_WalkLike(Point p, int move_speed_level) {
		setMassPoint_WalkLike(p, move_speed_level, null);
	}

	/**
	 * @param p
	 * @param move_speed_level
	 *            　1で普段と変わらず　２で２倍速で指定マスまで駆けつける
	 */
	public void setMassPoint_WalkLike(Point p, int move_speed_level, Task task) {
		flag_move_animating = true;
		this.mass_point = p.getLocation();
		MiniMap.openTiles();
		this.move_speed_level = move_speed_level;
		at_movement_end_task = task;
	}

	/**
	 * called from TurnSystemController.enemyAnimate()
	 */
	public void upDate() {
		if (jump_point != null) {
			upDate_Jump();
		} else {
			int speed;
			if (Player.me.isDushing()) {
				speed = MAP.MoveHighSpeed;
			} else {
				speed = MAP.MoveSpeed;
			}
			if (move_speed_level != -1) {
				speed *= move_speed_level;
			}
			upDate(speed);
			if (move_speed_level != -1 && !flag_move_animating) {
				move_speed_level = -1;
			}
		}
	}

	public void upDate(final int MOVE_SPEED) {
		Point dif = ObjectPoint.getDifferenceBetweenMass_Screen(this);
		if (dif.x == 0 && dif.y == 0) {
			if (at_movement_end_task != null) {
				Task end = at_movement_end_task;
				end.work();
				if (end != at_movement_end_task)
					return;
				at_movement_end_task = null;
			}
			flag_move_animating = false;
			return;
		}
		if (!((this instanceof Base_Artifact))
				&& WithinOutofScreen.isOutside(this)) {
			screen_point = ObjectPoint.getDangeonScreenPoint(mass_point);
			return;
		}
		if (dif.x != 0) {
			int signal = (dif.x > 0) ? 1 : -1;
			screen_point.x += MOVE_SPEED * signal;
			if (ObjectPoint.getDifferenceBetweenMass_Screen(this).x * signal < 0) {
				screen_point.x = ObjectPoint.getDangeonScreenPoint(mass_point).x;
			}
		}
		if (dif.y != 0) {
			int signal = (dif.y > 0) ? 1 : -1;
			screen_point.y += MOVE_SPEED * signal;
			if (ObjectPoint.getDifferenceBetweenMass_Screen(this).y * signal < 0) {
				screen_point.y = ObjectPoint.getDangeonScreenPoint(mass_point).y;
			}
		}
		dif = ObjectPoint.getDifferenceBetweenMass_Screen(this);
		if (at_movement_end_task != null) {
			flag_move_animating = true;
		} else {
			flag_move_animating = Math.abs(dif.x) > MOVE_SPEED
					|| Math.abs(dif.y) > MOVE_SPEED;
		}
	}

	protected void upDate_Jump() {
		if (jumped_away_frame == -1) {
			// 垂直上昇
			jumped_away_y -= MAP.MoveSpeed * 3;
			if (jumped_away_y < -FR.SCREEN_HEIGHT) {
				setMassPoint(jump_point);
				if (at_movement_end_task != null) {
					at_movement_end_task.work();
					at_movement_end_task = null;
				} else {
					endJump();
				}
			}
		} else {
			// 放物線
			// jumped_away_frame++;
			screen_point.x += jumped_away_x_speed;
			screen_point.y += jumped_away_y_speed;
			jumped_away_y += jumped_away_y_speed_shadow;
			int signal = (jumped_away_frame > jumped_away_frame_max / 2) ? 1
					: -1;
			jumped_away_y += signal * MAP.MoveSpeed / 2;
			if (++jumped_away_frame >= jumped_away_frame_max) {
				jumped_away_frame = jumped_away_frame_max;
				if (flag_jump_no_attack) {
					if (at_movement_end_task != null) {
						at_movement_end_task.work();
						at_movement_end_task = null;
					} else {
						endJump();
					}
				} else
					endParabonalJump();
			}
		}
	}
}
