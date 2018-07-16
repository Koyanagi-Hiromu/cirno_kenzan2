package main.util;

import java.util.ArrayList;

public class IntervalChecker {
	public static int MAX = 16;
	public static long start;
	public static ArrayList<Long> list = new ArrayList<Long>();

	public static void end() {
		if (list.size() == MAX) {
			double sum = 0;
			for (Long time : list) {
				sum += time / list.size();
			}
			System.out.println("\tかかった時間：\t[ms]"
					+ sum
					/ 1000
					+ "\t全使用メモリ：\t[MB]"
					+ (Runtime.getRuntime().totalMemory() - Runtime
							.getRuntime().freeMemory()) / 1000 / 1000.0);
			list.clear();
		}
		list.add((System.nanoTime() - start) / 1000l);
	}

	public static void strart() {
		start = System.nanoTime();
	}
}
