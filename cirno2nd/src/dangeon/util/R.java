package dangeon.util;

import java.util.Random;

import main.util.Show;
import dangeon.model.config.Config;
import dangeon.model.map.MapList;

public class R extends Random {
	private static final long serialVersionUID = 1L;
	public static long count = 0;
	private static long seed = -1;

	/**
	 * dangeon関係なし
	 * 
	 * @return
	 */
	public static double _nextDouble() {
		return new Random().nextDouble();
	}

	public static void next() {
		if (seed == -1)
			return;
		// 1st 999=>10998
		// 2nd 999=>11097
		seed += 9999l + MapList.getFloor() * 99;
		count = 0;
		if (seed == -1)
			seed = 9999l;
		Config.saveRandomSeed(seed);
	}

	/**
	 * 
	 * @param i
	 * @return (0)～(i-1)までの数字を１つ1/iの確率で返す
	 */
	public static int ran(int i) {
		return (int) (new R().nextDouble() * i);
	}

	private static void releaseFix() {
		seed = -1;
		count = 0;
		Config.saveRandomSeed(seed);
	}

	/**
	 * 
	 * @param seed_
	 *            -1で無効
	 */
	public static void setFix(long seed_) {
		if (seed_ == -1) {
			R.releaseFix();
		} else {
			seed = seed_;
			count = 0;
			Config.saveRandomSeed(seed);
		}
	}

	public R() {
		super();
		// 1st 10998 count =0
		// 2nd 11097
		if (seed != -1) {
			setSeed(seed + 999l * count * count++);
		}
	}

	public boolean is(int percent_100) {
		return nextInt(100) < percent_100;
	}

	@Override
	public int nextInt(int n) {
		try {
			return super.nextInt(n);
		} catch (Exception e) {
			Show.showErrorMessageDialog("Randomに0以下の値が渡されました<br/>0を返して続行します");
			return 0;
		}
	}
}
