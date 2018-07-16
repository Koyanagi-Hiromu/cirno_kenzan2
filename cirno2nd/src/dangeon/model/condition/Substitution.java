package dangeon.model.condition;

import java.awt.Point;

import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

public class Substitution {
	private static Base_Creature c;
	private static Point target;
	private static boolean target_in_sight = false;
	private static boolean checked_mass[][] = new boolean[40][40];

	public static void conditionEffect(Base_Creature creature, Point p) {
		c = creature;
		target = p;
		if (creature == Player.me)
			return;
		if (MassCreater.getMass(p).ROOM) {

		} else if (MassCreater.getMass(p).ROAD) {

		}
	}

	public static void playerSearchInRoom() {
		Point p = c.getMassPoint().getLocation();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (checked_mass[p.x + i][p.y + j])
					continue;
				else if (!MassCreater.getMass(p.x + i, p.y + j).WALKABLE) {
					checked_mass[p.x + i][p.y + j] = true;
					continue;
				} else if (target.x == p.x + i && target.y == p.y + j) {
					target_in_sight = true;
					return;
				} else if (MassCreater.getMass(p.x + i, p.y + j).ROAD) {
					checked_mass[p.x + i][p.y + j] = true;
					continue;
				}
			}
		}
	}
}
