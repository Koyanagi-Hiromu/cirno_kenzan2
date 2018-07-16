package main.constant;

import dangeon.view.constant.MAP;

public class FR {
	public static final int THREAD_SLEEP_VERY_FAST = 16;
	public static final int THREAD_SLEEP_FAST = 33;
	public static final int THREAD_SLEEP_NORMAL = 40;
	public static final int THREAD_SLEEP_SLOW = 50;
	public static int THREAD_SLEEP = THREAD_SLEEP_FAST;
	public static final int SCREEN_HEIGHT = MAP.TILE_SIZE
			* MAP.TILE_VERTICAL_INNER_NUMBER - 10;
	public static final int SCREEN_WIDTH = MAP.TILE_SIZE
			* MAP.TILE_HORIZON_INNER_NUMBER;

	public static void changeSleep() {
		switch (THREAD_SLEEP) {
		case THREAD_SLEEP_FAST:
			THREAD_SLEEP = THREAD_SLEEP_SLOW;
			break;
		case THREAD_SLEEP_SLOW:
			THREAD_SLEEP = THREAD_SLEEP_NORMAL;
			break;
		case THREAD_SLEEP_NORMAL:
			THREAD_SLEEP = THREAD_SLEEP_FAST;
			break;
		}
	}

	public static void changeSleep(int speed) {
		THREAD_SLEEP = speed;
	}

	public static String getStringSpeed() {
		switch (THREAD_SLEEP) {
		case THREAD_SLEEP_FAST:
			return "秒間の目標描画枚数を20fpsにする";
		case THREAD_SLEEP_SLOW:
			return "秒間の目標描画枚数を25fpsにする";
		case THREAD_SLEEP_NORMAL:
			return "秒間の目標描画枚数を30fpsにする";
		default:
			return null;
		}
	}

}
