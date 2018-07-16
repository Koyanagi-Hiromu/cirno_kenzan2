package main.util;

public class FrameShaker {

	public static final int SHAKE_INTERVAL = 200;

	private static int shake_strength = 0;

	private static int shake = 0;

	private static int shake_time = 0;

	public static void done(int strength) {
		shake_strength = strength;
		setShake_time(FrameShaker.SHAKE_INTERVAL);
	}

	public static void doneNormaly() {
		done(5);
	}

	public static void doneSoftly() {
		done(3);
	}

	public static void doneStrongly() {
		done(10);
	}

	public static int getShake() {
		return shake;
	}

	public static int getShake_strength() {
		return shake_strength;
	}

	public static int getShake_time() {
		return shake_time;
	}

	public static void setShake(int s) {
		shake = s;
	}

	public static void setShake_time(int shake_time) {
		FrameShaker.shake_time = shake_time;
	}

	public static int shake() {
		if (shake_time == 0)
			return 0;
		shake_time -= main.constant.FR.THREAD_SLEEP;
		if (shake_time <= 0) {
			shake_time = 0;
			shake = 0;
			return 0;
		}
		if (shake == shake_strength)
			shake = -FrameShaker.getShake_strength();
		else
			shake = FrameShaker.getShake_strength();
		return shake;
	}

}
