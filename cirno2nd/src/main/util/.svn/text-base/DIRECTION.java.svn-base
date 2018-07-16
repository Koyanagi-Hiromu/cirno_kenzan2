package main.util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public enum DIRECTION {
	UP_LEFT(7, 5, -1, -1), UP(8, 6, 0, -1), UP_RIGHT(9, 7, 1, -1), RIGHT(6, 0,
			1, 0), DOWN_RIGHT(3, 1, 1, 1), DOWN(2, 2, 0, 1), DOWN_LEFT(1, 3,
			-1, 1), LEFT(4, 4, -1, 0), NEUTRAL(5, null, 0, 0);

	public static DIRECTION backDirection(DIRECTION dir) {
		for (DIRECTION d : DIRECTION.values()) {
			if (-dir.X == d.X && -dir.Y == d.Y) {
				return d;
			}
		}
		return dir;
	}

	public static DIRECTION[] get8Direction() {
		DIRECTION[] direction = new DIRECTION[8];
		int i = 0;
		for (DIRECTION dir : DIRECTION.values()) {
			if (dir.NUM == 5) {
				continue;
			}
			direction[i] = dir;
			i++;
		}
		return direction;
	}

	/**
	 * 
	 * @param c1
	 * @param c2
	 * @return c1から見たc2の方向(c1視点)
	 */
	public static DIRECTION getDirection(Base_Creature c1, Base_Creature c2) {
		return getDirection(c1.getMassPoint(), c2.getMassPoint());
	}

	public static DIRECTION getDirection(int x, int y) {
		if (x > 0) {
			x = 1;
		} else if (x < 0) {
			x = -1;
		}
		if (y > 0) {
			y = 1;
		} else if (y < 0) {
			y = -1;
		}
		for (DIRECTION d : values()) {
			if (d.X == x && d.Y == y) {
				return d;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return p1から見たp2の方向(p1視点)
	 */
	public static DIRECTION getDirection(Point p1, Point p2) {
		int x = p2.x - p1.x;
		int y = p2.y - p1.y;
		return getDirection(x, y);
	}

	public static DIRECTION getDirectionFromRotate(int rotate) {
		if (rotate < 0) {
			rotate += 8;
		}
		if (rotate > 7) {
			rotate -= 8;
		}
		for (DIRECTION d : values()) {
			if (d == NEUTRAL) {
				continue;
			}
			if (d.ROTATE == rotate) {
				return d;
			}
		}
		return NEUTRAL;
	}

	/**
	 * 
	 * @param c
	 * @return そのキャラクターから見た主人公の方向
	 */
	public static DIRECTION getDirectionToPlayer(Base_Creature c) {
		return getDirection(c, Player.me);
	}

	public static DIRECTION getFromNUM(int num) {
		for (DIRECTION d : values()) {
			if (d.NUM == num) {
				return d;
			}
		}
		return NEUTRAL;
	}

	public static DIRECTION getRandom8Direction() {
		int ran = new R().nextInt(8) + 1;
		if (ran >= 5)
			ran++;
		for (DIRECTION d : DIRECTION.values()) {
			if (d.NUM == ran) {
				return d;
			}
		}
		Show.showCriticalErrorMessageDialog("Neutral方向が選ばれてしまいました。\n@DIRECTION.getRandom8Direction()");
		return NEUTRAL;
	}

	public static DIRECTION getSum(DIRECTION... directions) {
		int x = 0, y = 0;
		for (DIRECTION direction : directions) {
			x += direction.X;
			y += direction.Y;
		}
		return getDirection(x, y);
	}

	public static boolean isBias(DIRECTION direction) {
		return direction.isBias();
	}

	/**
	 * 
	 * @param dir
	 * @return 5 way
	 */
	public static List<DIRECTION> neiberDirection(DIRECTION dir) {
		List<DIRECTION> list = new ArrayList<DIRECTION>();
		switch (dir) {
		case UP:
			list.add(UP);
			list.add(UP_LEFT);
			list.add(UP_RIGHT);
			list.add(LEFT);
			list.add(RIGHT);
			break;
		case DOWN:
			list.add(DOWN);
			list.add(DOWN_RIGHT);
			list.add(DOWN_LEFT);
			list.add(LEFT);
			list.add(RIGHT);
			break;
		case LEFT:
			list.add(LEFT);
			list.add(UP_LEFT);
			list.add(DOWN_LEFT);
			list.add(UP);
			list.add(DOWN);
			break;
		case RIGHT:
			list.add(RIGHT);
			list.add(UP_RIGHT);
			list.add(DOWN_RIGHT);
			list.add(UP);
			list.add(DOWN);
			break;
		case UP_LEFT:
			list.add(UP_LEFT);
			list.add(LEFT);
			list.add(UP);
			list.add(UP_RIGHT);
			list.add(DOWN_LEFT);
			break;
		case UP_RIGHT:
			list.add(UP_RIGHT);
			list.add(RIGHT);
			list.add(UP);
			list.add(UP_LEFT);
			list.add(DOWN_RIGHT);
			break;
		case DOWN_LEFT:
			list.add(DOWN_LEFT);
			list.add(DOWN);
			list.add(LEFT);
			list.add(UP_LEFT);
			list.add(DOWN_RIGHT);
			break;
		case DOWN_RIGHT:
			list.add(DOWN_RIGHT);
			list.add(RIGHT);
			list.add(DOWN);
			list.add(UP_RIGHT);
			list.add(DOWN_LEFT);
			break;
		}
		return list;
	}

	public static DIRECTION[] values_exceptNeatral() {
		DIRECTION[] d = new DIRECTION[8];
		for (int i = 0; i < 8; i++) {
			d[i] = getDirectionFromRotate(i);
		}
		return d;
	}

	public static DIRECTION[] values_onlyBasic4() {
		DIRECTION[] d = new DIRECTION[4];
		for (int i = 0; i < 8; i += 2) {
			d[i / 2] = getDirectionFromRotate(i);
		}
		return d;
	}

	public static DIRECTION[] values_onlyNANAME4() {
		DIRECTION[] d = new DIRECTION[4];
		for (int i = 0; i < 8; i += 2) {
			d[i / 2] = getDirectionFromRotate(i + 1);
		}
		return d;
	}

	/**
	 * RIGHTを0として時計回りに1ずつ増加。最大値はUP_RIGHTの7。 NEUTRALはnull
	 */
	public final Integer ROTATE;

	public final int NUM, X, Y;

	private final DIRECTION[] NEIBORING3 = new DIRECTION[3],
			NEIBORING5 = new DIRECTION[5];

	private DIRECTION(int num, Integer rotate, int x, int y) {
		NUM = num;
		ROTATE = rotate;
		X = x;
		Y = y;

	}

	public DIRECTION getDeNeiboringDirection() {
		return getNeiboringDirection(-1);
	}

	public Point getFrontPoint(Point p) {
		p.translate(X, Y);
		return p;
	}

	/**
	 * 画像のインデックス用
	 * 
	 * @return NEAUTRALは-1
	 */
	public int getIndexFrom0ExceptNeautral() {
		int i = NUM;
		if (i == 5) {
			return -1;
		} else if (i > 5) {
			return i - 2;
		}
		return i - 1;
	}

	public DIRECTION getNeiboringDirection() {
		return getNeiboringDirection(1);
	}

	public DIRECTION getNeiboringDirection(int delt) {
		return getDirectionFromRotate(ROTATE + delt);
	}

	/**
	 * 自身がLEFTなら DONW_LEFT・LEFT・UP_LEFTを返す
	 * 
	 * @return 自身を含めた３つ
	 */
	public DIRECTION[] getNeiboringDirections3() {
		if (NEIBORING3[0] != null) {
			return NEIBORING3;
		}
		int i = 0;
		for (DIRECTION d : values_exceptNeatral()) {
			int dif = Math.abs(ROTATE - d.ROTATE);
			if (dif <= 1 || dif >= 7) {
				NEIBORING3[i] = d;
				i++;
			}
		}
		return NEIBORING3;
	}

	/**
	 * 自身がLEFTなら DONW・DONW_LEFT・LEFT・UP_LEFT・UPを返す
	 * 
	 * @return 自身を含めた３つ
	 */
	public DIRECTION[] getNeiboringDirections5() {
		if (NEIBORING5[0] != null) {
			return NEIBORING5;
		}
		int i = 0;
		for (DIRECTION d : values_exceptNeatral()) {
			int dif = Math.abs(ROTATE - d.ROTATE);
			if (dif <= 2 || dif >= 6) {
				NEIBORING5[i] = d;
				i++;
			}
		}
		return NEIBORING5;
	}

	/**
	 * 
	 * @return 自身を含めた８つ（rotate順に返す）
	 */
	public DIRECTION[] getNeiboringDirections8() {
		DIRECTION[] ds = new DIRECTION[8];
		for (int i = 0; i < ds.length; i++) {
			ds[i] = getDirectionFromRotate(ROTATE + i);
		}
		return ds;
	}

	public DIRECTION[] getNeiboringDirections8_Head() {
		DIRECTION[] ds = new DIRECTION[8];
		ds[0] = this;
		ds[7] = this.getReverse();
		for (int i = 1; i < 7; i++) {
			int delt = (i + 1) / 2;
			if (i % 2 == 0)
				delt *= -1;
			ds[i] = this.getNeiboringDirection(delt);
		}
		return ds;
	}

	public DIRECTION getReverse() {
		for (DIRECTION d : values()) {
			if (d.X == -X && d.Y == -Y) {
				return d;
			}
		}
		return this;
	}

	/**
	 * 
	 * @return 左上が0　～　←が7
	 */
	public int getRotate() {
		if (ROTATE == null) {
			return 5;
		}
		int r = ROTATE - 5;
		if (r < 0) {
			r += 8;
		}
		return r;
	}

	public boolean isBias() {
		return X != 0 && Y != 0;
	}

}
