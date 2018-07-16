package dangeon.model.map;

import java.awt.Point;
import java.util.ArrayList;

import main.util.DIRECTION;

public class EnemyFall {

	public static Point getPoint(Point goal) {
		return getPoint(goal, new ArrayList<Point>());
	}

	public static Point getPoint(Point goal, ArrayList<Point> exceptions) {
		if (isAbleToFall(goal, exceptions)) {
			return goal;
		} else {
			DIRECTION ds[] = { DIRECTION.RIGHT, DIRECTION.DOWN, DIRECTION.LEFT,
					DIRECTION.UP };
			int frame = 0;
			while (true) {
				frame++;
				Point p = goal.getLocation();
				p.translate(-frame, -frame);
				for (DIRECTION d : ds) {
					for (int i = 0; i < frame * 2; i++) {
						p.translate(d.X, d.Y);
						if (isAbleToFall(p, exceptions)) {
							return p;
						}
					}
				}
			}
		}
	}

	private static boolean isAbleToFall(Point p, ArrayList<Point> exceptions) {
		for (Point point : exceptions) {
			if (point.equals(p))
				return false;
		}
		return MassCreater.getMass(p).WALKABLE
				&& MapList.getCreature(p) == null;
	}
}
