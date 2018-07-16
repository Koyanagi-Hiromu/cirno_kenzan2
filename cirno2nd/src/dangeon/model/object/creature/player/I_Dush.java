package dangeon.model.object.creature.player;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;

public class I_Dush {

	private static Player p = null;
	public static boolean auto = false;

	private final static int OUT_OF_RANGE = -1, WITH_IN_RANGE = 0,
			SUCCEESS = 1;

	private static boolean artifactCheck(Base_Artifact a) {
		if (a == null || !a.isVisible())
			return false;
		if (!a.isMobile() && !a.isDevice())
			return false;
		return MassCreater.isPlayerInTheSameRoom(a.getMassPoint());
	}

	private static int check(int x, int y, Point point) {
		if (MassCreater.isPointOutOfDangeon(x, y)) {
			return OUT_OF_RANGE;
		}
		if (artifactCheck(MapList.getArtiface(x, y))) {
			System.out.println("[target]\t"
					+ MapList.getArtiface(x, y).getName());
			point.setLocation(new Point(x, y));
			return SUCCEESS;
		}
		if (MassCreater.isEntrance(x, y)) {
			System.out.println("[present]\t" + p.getMassPoint());
			System.out.println("[target]\t" + x + "," + y);
			System.out.println();
			point.setLocation(new Point(x, y));
			return SUCCEESS;
		}
		return WITH_IN_RANGE;
	}

	private static Point getRoomObject() {
		Point point = new Point();
		int x = p.getMassPoint().x;
		int y = p.getMassPoint().y;
		DIRECTION p_d = p.getDirection(), d;
		int branch_times, c;
		c = check(x, y, point);
		if (c == SUCCEESS)
			return point;
		boolean first, second_1, second_2;
		for (int roop_times = 1;; roop_times++) {
			x += p_d.X;
			y += p_d.Y;
			c = check(x, y, point);
			if (c == SUCCEESS)
				return point;
			else
				first = c == WITH_IN_RANGE;
			second_1 = second_2 = true;
			for (int i = 0; i < roop_times; i++) {
				branch_times = i + 1;
				if (DIRECTION.isBias(p_d)) {
					d = p_d.getNeiboringDirection(3);
				} else {
					d = p_d.getNeiboringDirection(2);
				}
				if (second_1) {
					c = check(x + d.X * branch_times, y + d.Y * branch_times,
							point);
					if (c == SUCCEESS)
						return point;
					else
						second_1 = c == WITH_IN_RANGE;
				}
				if (second_2) {
					if (DIRECTION.isBias(p_d)) {
						d = p_d.getNeiboringDirection(-3);
					} else {
						d = p_d.getNeiboringDirection(-2);
					}
					c = check(x + d.X * branch_times, y + d.Y * branch_times,
							point);
					if (c == SUCCEESS)
						return point;
					else
						second_2 = c == WITH_IN_RANGE;
				}
				if (!second_1 && !second_2) {
					break;
				}
			}
			if (!(first || second_1 || second_2))
				break;
		}
		return null;
	}

	public static DIRECTION isStandInBranch() {
		DIRECTION[] ds = Player.me.getDirection().getNeiboringDirections5();
		int i = 0;
		DIRECTION direction = DIRECTION.NEUTRAL;
		for (DIRECTION d : ds) {
			if (Player.me.isMovable(d)) {
				direction = d;
				if (i++ > 0)
					return DIRECTION.NEUTRAL;
			}
		}
		return direction;
	}

	private static void move(DIRECTION direction) {
		p.move(direction);
		auto = true;
	}

	private static void path() {
		DIRECTION d = isStandInBranch();
		if (d != DIRECTION.NEUTRAL)
			move(d);
	}

	private static void room() {
		Point point = getRoomObject();
		if (point != null) {
			DIRECTION d = DIRECTION.getDirection(p.getMassPoint(), point);
			move(d);
		}
	}

	public static void work() {
		if (Scene_Action.getMe().is_Stopping_I_Dushing())
			return;
		p = Player.me;
		auto = false;
		Mass before = MassCreater.getPlayerMass();
		DIRECTION d = p.getVisibleObjectExistAtNeiboringMass_direction(false);
		if (d != null) {
			Scene_Action.getMe().set_I_Dushing_request_arrow_key(true);
			Scene_Action.getMe().setRequestReleasePushingKey();
			p.setDirection(d);
			p = null;
			return;
		} else if (MassCreater.isPlayerInRoom()) {
			room();
		} else {
			path();
		}
		Mass after = MassCreater.getPlayerMass();
		if (!auto || (before.ROOM ^ after.ROOM)) {
			Scene_Action.getMe().set_I_Dushing_request_arrow_key(true);
			if (auto)
				Scene_Action.getMe().setRequestReleasePushingKey();
		}
		p = null;
	}
}
