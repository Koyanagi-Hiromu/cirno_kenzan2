package main.util;

import java.awt.Point;

public class UtilPoint extends Point {

	private static final long serialVersionUID = 1L;

	public static boolean isEqual(Point p1, Point p2) {
		return p1.x == p2.x && p1.y == p2.y;
	}

	public static boolean isWithinRange(Point p1, Point p2, int range) {
		return Math.abs(p1.x - p2.x) <= range && Math.abs(p1.y - p2.y) <= range;
	}
}
