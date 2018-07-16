package dangeon.model.object.creature.player;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import main.util.BeautifulView;
import main.util.DIRECTION;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.CardAttackEffect;
import dangeon.model.object.creature.enemy.Exルーミア;
import dangeon.util.ObjectPoint;
import dangeon.view.constant.MAP;

public class Stand {
	public final Base_Enemy E;
	private int dx = 0, dy = 0;
	final DIRECTION D;
	private int attacking_frame = 0;
	private int attack_No = 0;
	private float alpha = 1.1F;
	public final int SCREEN_X, SCREEN_Y;
	private boolean end = false;
	private final boolean FLAG_FARTHER;
	private final boolean FLAG_NO_MOVE;
	private final int LV;

	private boolean lastAttackNoChange = false;

	Stand(Class<? extends Base_Enemy> clazz, DIRECTION d,
			boolean isStandAttacking2MassFurther, int lv) {
		this(clazz, d, isStandAttacking2MassFurther, lv, Player.me
				.getMassPoint());
	}

	Stand(Class<? extends Base_Enemy> clazz, DIRECTION d,
			boolean isStandAttacking2MassFurther, int lv, Point p) {
		LV = lv;
		Object obj = null;
		try {
			Constructor<?> con = clazz.getConstructor(Point.class, int.class);
			obj = con.newInstance(p, 1);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		E = (Base_Enemy) obj;
		E.setPlayerSide(true);
		D = d;
		E.setDirection(D);
		E.startAttack(null);
		SCREEN_X = E.getScreenPoint().x;
		SCREEN_Y = E.getScreenPoint().y;
		FLAG_FARTHER = isStandAttacking2MassFurther;
		if (E instanceof CardAttackEffect) {
			dx += D.X * MAP.MoveSpeed;
			dy += D.Y * MAP.MoveSpeed;
		}
		FLAG_NO_MOVE = E instanceof Exルーミア;
	}

	private void back() {
		Point p = ObjectPoint.getDifferenceBetweenPlayer(SCREEN_X + dx,
				SCREEN_Y + dy);
		if (Math.abs(p.x) < Speed() && Math.abs(p.y) < Speed()) {
			end = true;
			return;
		}
		if (p.x != 0) {
			int sig = (p.x > 0) ? 1 : -1;
			dx -= Speed() * sig;
			// if (p.x * sig > MAP.TILE_SIZE) {
			// dx -= MAP.MoveHighSpeed * sig;
			// } else {
			// dx -= MAP.MoveSpeed * sig;
			// }
		}
		if (p.y != 0) {
			int sig = (p.y > 0) ? 1 : -1;
			dy -= Speed() * sig;
			// if (p.y * sig > MAP.TILE_SIZE) {
			// dy -= MAP.MoveHighSpeed * sig;
			// } else {
			// dy -= MAP.MoveSpeed * sig;
			// }
		}
		Point _p = ObjectPoint.getDifferenceBetweenPlayer(SCREEN_X + dx,
				SCREEN_Y + dy);
		if (p.x * _p.x < 0) {
			dx -= _p.x;
		}
		if (p.y * _p.y < 0) {
			dy -= _p.y;
		}
	}

	public boolean end() {
		return end;
	}

	public int getFootX() {
		return E.getFootX() + dx;
	}

	// void upDate() {
	// alpha -= 0.03f;
	// if (!E.isPhaseBack()) {
	// E.upDate();
	// }
	// attacking_frame++;
	// attack_No = E.getAttackNo();
	// if (attacking_frame < 3) {
	// return;
	// } else if (attacking_frame < 20) {
	// move();
	// return;
	// }
	// back();
	// }

	public int getFootY() {
		return E.getFootY() + dy;
	}

	/**
	 * 透過も指定して画像を返します
	 * 
	 * @param g
	 * @return
	 */
	public Image getImage(Graphics2D g) {
		float a = alpha;
		if (a < 0) {
			a = 0;
		} else if (a > 1) {
			a = 1f;
		}
		BeautifulView.setAlphaOnImg(g, a);
		return E.getATKImage(LV, D, attack_No);
	}

	private void move() {
		if (FLAG_NO_MOVE)
			return;
		int range = MAP.TILE_SIZE / 3;
		if (FLAG_FARTHER) {
			range = MAP.TILE_SIZE;
		}
		if (E instanceof CardAttackEffect) {
			range += MAP.TILE_SIZE * 2 / 3;
		}
		if (!(Math.abs(dx) > range || Math.abs(dy) > range)) {
			dx += D.X * MAP.MoveSpeed;
			dy += D.Y * MAP.MoveSpeed;
			if (FLAG_FARTHER) {
				dx += D.X * MAP.MoveSpeed;
				dy += D.Y * MAP.MoveSpeed;
			}
		}
	}

	private int Speed() {
		int speed;
		if (Player.me.isDushing() && Player.me.isMoving()) {
			speed = MAP.MoveHighSpeed + 1;
		} else {
			speed = MAP.MoveSpeed + 1;
		}
		if (FLAG_FARTHER) {
			speed *= 2;
		}
		return speed;
	}

	void upDate() {
		alpha -= 0.03f;
		attacking_frame++;
		if (!lastAttackNoChange) {
			if (Player.me.conditionCheck(CONDITION.鈍足)) {

			}
			E.upDate();
			attack_No = E.getAttackNo();
			lastAttackNoChange = attacking_frame > 9;
		}
		// if (!lastAttackNoChange) {
		// int no = E.getAttackNo();
		// E.upDate();
		// if (E.isAttackPhaseEnd()) {
		// lastAttackNoChange = true;
		// attack_No = no;
		// } else {
		// attack_No = E.getAttackNo();
		// }
		// }
		if (attacking_frame < 3) {
			return;
		} else if (attacking_frame < 20) {
			move();
		} else {
			if (E instanceof CardAttackEffect) {
				end = true;
			} else {
				back();
			}
		}
	}
}
