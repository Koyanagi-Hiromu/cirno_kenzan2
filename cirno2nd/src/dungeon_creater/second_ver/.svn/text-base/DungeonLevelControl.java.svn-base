package dungeon_creater.second_ver;

import java.awt.Graphics2D;
import java.awt.Point;

public class DungeonLevelControl {
	static int dungeon_level = 1;
	static int dungeon_max_level = 99;
	public static boolean flag_drag_move = false;

	static final int start_x = EnemyTableGraphicsPanel.ME.table_x;

	static final int start_y = EnemyTableGraphicsPanel.ME.dungeon_level_control_y;

	static int x_width = EnemyTableGraphicsPanel.ME.table_width;

	private static void addDungeonLevel(int add) {
		setDungeonLevel(dungeon_level + add);
	}

	public static int getDungeonLevel() {
		return dungeon_level;
	}

	public static int getDungeonMaxLevel() {
		return dungeon_max_level;
	}

	public static void setDungeonLevel(int level) {
		if (level <= 1) {
			level = 1;
		} else if (level >= dungeon_max_level) {
			level = dungeon_max_level;
		}
		dungeon_level = level;
		SetObject.setEnemyListDungeonLevel();
	}

	public static void setDungeonMaxLevel(int max) {
		dungeon_max_level = max;
		dungeon_level = 1;
	}

	int y_height = 0;
	static int current_start_x = start_x;
	static int current_start_y = start_y;
	static int pixel_one_level = 3;
	/**
	 * 1レベル毎の移動量
	 */
	static float one_level_pixel_move_base = (float) x_width
			/ (float) dungeon_max_level;
	static float one_level_pixel_move = (x_width - one_level_pixel_move_base)
			/ dungeon_max_level;
	static final int width_height = EnemyTableGraphicsPanel.ME.control_tab_width_and_height;

	/**
	 * レベル操作ボタンのサイズ
	 */
	static int level_button_size = 23;

	/**
	 * 操作ボタンの始点
	 */
	static Point level_plus_button = new Point(
			EnemyTableGraphicsPanel.ME.draw_dungeon_level_start_x + 80,
			EnemyTableGraphicsPanel.ME.table_start_y + 4);

	static Point level_minus_button = new Point(
			EnemyTableGraphicsPanel.ME.draw_dungeon_level_start_x + 55,
			EnemyTableGraphicsPanel.ME.table_start_y + 4);

	/**
	 * 三角形の左上、左下の座標
	 */
	static Point plus_button_start_x_y = new Point(level_plus_button.x,
			level_plus_button.y);
	static Point plus_button_end_x_y = new Point(level_plus_button.x
			+ level_button_size, level_plus_button.y + level_button_size);
	static Point minus_button_start_x_y = new Point(level_minus_button.x
			- level_button_size, level_minus_button.y);
	static Point minus_button_end_x_y = new Point(level_minus_button.x,
			level_minus_button.y + level_button_size);
	/**
	 * 三角形の頂点の座標
	 */
	static int[] triangle_plus_x = { level_plus_button.x + level_button_size,
			level_plus_button.x, level_plus_button.x };
	static int[] triangle_plus_y = {
			level_plus_button.y + level_button_size / 2, level_plus_button.y,
			level_plus_button.y + level_button_size };
	static int[] triangle_minus_x = { level_minus_button.x - level_button_size,
			level_minus_button.x, level_minus_button.x };
	static int[] triangle_minus_y = {
			level_minus_button.y + level_button_size / 2, level_minus_button.y,
			level_minus_button.y + level_button_size };

	public static void dragTab(Point p) {
		if (!flag_drag_move) {
			return;
		}
		int level = p.x - start_x;
		level /= one_level_pixel_move;
		setDungeonLevel(level);
	}

	public static void drawControler(Graphics2D g) {
		// タブ位置設定:
		float f = start_x + (dungeon_level - 1) * one_level_pixel_move;
		current_start_x = (int) f;
		// 操作タブ
		g.drawRect(current_start_x, current_start_y, width_height, width_height);
		drawLevelButton(g);
	}

	public static void drawLevelButton(Graphics2D g) {
		g.drawPolygon(triangle_plus_x, triangle_plus_y, 3);
		g.drawPolygon(triangle_minus_x, triangle_minus_y, 3);
	}

	public static void isFlagDragMove(Point p) {
		if (p.x >= current_start_x && p.x <= current_start_x + width_height) {
			if (p.y >= current_start_y && p.y <= current_start_y + width_height) {
				flag_drag_move = true;
			}
		}
	}

	private static boolean isPointIsLimitThisPoint(Point source,
			Point target_start, Point target_end) {
		if (source.x >= target_start.x && source.x <= target_end.x) {
			if (source.y >= target_start.y && source.y <= target_end.y) {
				return true;
			}
		}
		return false;
	}

	public static void pushLevelButton(Point p) {
		if (isPointIsLimitThisPoint(p, plus_button_start_x_y,
				plus_button_end_x_y)) {
			addDungeonLevel(1);
		} else if (isPointIsLimitThisPoint(p, minus_button_start_x_y,
				minus_button_end_x_y)) {
			addDungeonLevel(-1);
		}
	}

}
