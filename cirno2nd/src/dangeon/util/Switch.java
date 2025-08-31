package dangeon.util;

import main.constant.PropertySupporter;

/**
 * テスト用スイッチ。エクスポートする際は必ずfalseにする事
 * 
 * @author rottiti
 * 
 */
public class Switch {
	public static boolean test;
	static {
		// 必ずfalseにすること
		test = true;
		if (test) {
			test = new PropertySupporter(4).isGotPropertyTrue("eclipse_test");
		}
	}
	public static boolean force_hasFinished_true = true;

	public static boolean huyukomi = false;
	public static boolean reitaisai = false;
	public static boolean create = false;
	// バージョン変更用保険
	public static String big_ver = "2";
	public static String small_ver = "30";
	// public static int version = 100;
	public static String current_game = "２製品版";
	public static final String main_name = "チルノ見参２"
			.concat(Switch.test ? "デバッグモード" : "");
	private static final String sub_string = "";

	public static boolean switch_player_no_death = true;
	public static boolean switch_wall_walk = false;

	public static String getMainTitle() {
		return main_name.concat(" ver" + Switch.big_ver + "."
				+ Switch.small_ver + " " + Switch.sub_string);
	}
}
