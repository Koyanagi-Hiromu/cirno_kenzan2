package main.util;

import main.constant.FR;

public class Sleep {
	private static long before_time = System.nanoTime();

	private static int over_slept_time = 0;

	private static int no_sleep_count = 0;

	private static void checkNoSleep(boolean is_demand_sleep_less_than_zero) {
		if (is_demand_sleep_less_than_zero) {
			no_sleep_count++;
			// System.out.println("\t\tno sleep count : " + no_sleep_count
			// + " / 16");
			if (no_sleep_count == 4) {
				over_slept_time = 0;
			}
			if (no_sleep_count > 8) {
				Runtime.getRuntime().gc();
				over_slept_time = 0;
				no_sleep_count = 0;
				done(1);
//				System.out.println("no_sleep_count is over");
			}
		} else {
			no_sleep_count = 0;
		}
	}

	// 描画時に呼ぶ
	public static void done() {
		long now = System.nanoTime();
		int dif_1loop = (int) Math.round((now - before_time) / 1000000.0);
		long demand_sleep = FR.THREAD_SLEEP - over_slept_time - dif_1loop;
		long slept_time = Sleep.done(demand_sleep);
		over_slept_time = (int) (slept_time - demand_sleep);
		checkNoSleep(demand_sleep < 0);
		before_time = System.nanoTime();
		// System.out.println("１ループにかかった時間(ms)　→　" + dif_1loop + "\t1.6GHzなら　→　"
		// + (dif_1loop * 21 / 16));
		// System.out.println("\tdif_1loop + slept = " + (dif_1loop +
		// slept_time)
		// + "ms <=> " + FR.THREAD_SLEEP + "ms");
		// System.out.println("\tover slept time : " + over_slept_time + " ms");
	}

	public static void done(int times) {
		try {
			Thread.sleep(FR.THREAD_SLEEP * times);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param time
	 *            sleeping time.
	 * @return slept milli_time.
	 */
	public static long done(long time) {
		if (time <= 0L) {
			return 0;
		}
		long now = System.nanoTime();
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long delt = Math.round((System.nanoTime() - now) / 1000000.0);
		return delt;
	}

}
