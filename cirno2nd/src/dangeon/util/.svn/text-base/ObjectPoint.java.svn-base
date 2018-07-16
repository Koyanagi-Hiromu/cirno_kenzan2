package dangeon.util;

import java.awt.Point;

import dangeon.model.object.Base_MapObject;
import dangeon.model.object.creature.player.Player;
import dangeon.view.constant.MAP;

public class ObjectPoint {
	public static Point getDangeonScreenPoint(int x, int y) {
		return new Point(MAP.TILE_SIZE * x, MAP.TILE_SIZE * y);
	}

	public static Point getDangeonScreenPoint(Point mass_point) {
		int x = MAP.TILE_SIZE * mass_point.x;
		int y = MAP.TILE_SIZE * mass_point.y;
		return new Point(x, y);
	}

	public static Point getDifferenceBetweenMass_Screen(Base_MapObject o) {
		int x = o.getMassPoint().x * MAP.TILE_SIZE - o.getScreenPoint().x;
		int y = o.getMassPoint().y * MAP.TILE_SIZE - o.getScreenPoint().y;
		return new Point(x, y);
	}

	public static Point getDifferenceBetweenPlayer(int x, int y) {
		int dx = x - Player.me.getPureScreenPoint().x;
		int dy = y - Player.me.getPureScreenPoint().y;
		return new Point(dx, dy);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return 何マス離れているか
	 */
	public static int getLengthBetweenPlayer(int x, int y) {
		int _x = Player.me.getMassPoint().x - x;
		int _y = Player.me.getMassPoint().y - y;
		return Math.max(Math.abs(_x), Math.abs(_y));
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return 何マス離れているか
	 */
	public static int getLengthBetweenPlayer(Point p) {
		int _x = Player.me.getMassPoint().x - p.x;
		int _y = Player.me.getMassPoint().y - p.y;
		return Math.max(Math.abs(_x), Math.abs(_y));
	}

	public static Point getMassPoint(Point screen_point) {
		int x = screen_point.x / MAP.TILE_SIZE;
		int y = screen_point.y / MAP.TILE_SIZE;
		return new Point(x, y);
	}

	public static Point getScreenPointRelPlayer(Base_MapObject o) {
		int x = MAP.TILE_SIZE * (MAP.TILE_HORIZON_INNER_NUMBER - 1) / 2;
		int y = MAP.TILE_SIZE * (MAP.TILE_VERTICAL_INNER_NUMBER - 1) / 2;
		if (o.isJumping())
			y += o.getJumpDY();
		if (o.equals(Player.me)) {
			return new Point(x + Player.me.getDeltX(), y + Player.me.getDeltY());
		} else {
			x += o.getScreenPoint().x - Player.me.getPureScreenPoint().x;
			y += o.getScreenPoint().y - Player.me.getPureScreenPoint().y;
			return new Point(x, y);
		}
	}

	public static Point getScreenPointRelPlayer(int screen_x, int screen_y) {
		int dx = screen_x - Player.me.getPureScreenPoint().x;
		int dy = screen_y - Player.me.getPureScreenPoint().y;
		screen_x = MAP.TILE_SIZE * (MAP.TILE_HORIZON_INNER_NUMBER - 1) / 2 + dx;
		screen_y = MAP.TILE_SIZE * (MAP.TILE_VERTICAL_INNER_NUMBER - 1) / 2
				+ dy;
		return new Point(screen_x, screen_y);
	}

	public static Point getScreenPointRelPlayer(Point screen_point) {
		int dx = screen_point.x - Player.me.getPureScreenPoint().x;
		int dy = screen_point.y - Player.me.getPureScreenPoint().y;
		int x = MAP.TILE_SIZE * (MAP.TILE_HORIZON_INNER_NUMBER - 1) / 2 + dx;
		int y = MAP.TILE_SIZE * (MAP.TILE_VERTICAL_INNER_NUMBER - 1) / 2 + dy;
		return new Point(x, y);
	}

	public static boolean isNoDifferenceBetweenMass_Screen(Base_MapObject o) {
		int x = o.getMassPoint().x * MAP.TILE_SIZE - o.getScreenPoint().x;
		int y = o.getMassPoint().y * MAP.TILE_SIZE - o.getScreenPoint().y;
		return x == 0 && y == 0;
	}
}
