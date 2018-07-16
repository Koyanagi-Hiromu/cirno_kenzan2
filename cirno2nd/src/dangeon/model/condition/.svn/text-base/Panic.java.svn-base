package dangeon.model.condition;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

public class Panic {
	private static int MP = 60;
	// FIXME
	static Mass[][] mass = MassCreater.getMass();
	private static boolean check_room[][] = new boolean[MP][MP];
	private static boolean player_watched = false;

	private static void check_clear() {
		for (int i = 0; i < MP; i++) {
			for (int j = 0; j < MP; j++) {
				check_room[i][j] = false;
			}
		}
	}

	public static void conditionEffect(Base_Creature creature) {
		if (creature == Player.me)
			return;
		if (mass[creature.getMassPoint().x][creature.getMassPoint().y].ROOM) {
			check_clear();
			player_watched = false;
			whereIsPlayerInRoom(creature, creature.getMassPoint().x,
					creature.getMassPoint().y);
			if (!player_watched) {
				return;
			} else {
				escapeFromPlayer(creature);
				creature.convertEnemyFromCreature(creature).enemy_condition_actioned = true;
				return;
			}
		} else if (mass[creature.getMassPoint().x][creature.getMassPoint().y].ROAD) {
			if (!whereIsPlayerInRoad(creature)) {
				return;
			} else {
				escapeFromPlayer(creature);
				creature.convertEnemyFromCreature(creature).enemy_condition_actioned = true;
				return;
			}
		} else
			return;
	}

	private static DIRECTION directionOfPlayer(Base_Creature creature) {
		if (Player.me.getMassPoint().x > creature.getMassPoint().x) {
			if (Player.me.getMassPoint().y > creature.getMassPoint().y)
				return DIRECTION.DOWN_RIGHT;
			else if (Player.me.getMassPoint().y < creature.getMassPoint().y)
				return DIRECTION.UP_RIGHT;
			else
				return DIRECTION.RIGHT;
		} else if (Player.me.getMassPoint().x < creature.getMassPoint().x) {
			if (Player.me.getMassPoint().y > creature.getMassPoint().y)
				return DIRECTION.DOWN_LEFT;
			else if (Player.me.getMassPoint().y < creature.getMassPoint().x)
				return DIRECTION.UP_LEFT;
			else
				return DIRECTION.RIGHT;
		} else {
			if (Player.me.getMassPoint().y < creature.getMassPoint().y)
				return DIRECTION.UP;
			else
				return DIRECTION.DOWN;
		}
	}

	private static void escapeFromPlayer(Base_Creature creature) {
		Point p = creature.getMassPoint();
		switch (directionOfPlayer(creature)) {
		case UP:
			if (!mass[p.x][p.y + 1].WALKABLE
					|| MapList.getEnemy(p.x, p.y + 1) != null) {
				if (mass[p.x - 1][p.y + 1].WALKABLE
						&& MapList.getEnemy(p.x - 1, p.y + 1) == null
						&& mass[p.x - 1][p.y].WALKABLE
						&& mass[p.x][p.y + 1].WALKABLE) {
					creature.convertEnemyFromCreature(creature).enemy_move(-1,
							1, DIRECTION.DOWN_LEFT);
				} else {
					if (mass[p.x + 1][p.y + 1].WALKABLE
							&& MapList.getEnemy(p.x + 1, p.y + 1) == null
							&& mass[p.x + 1][p.y].WALKABLE
							&& mass[p.x][p.y + 1].WALKABLE) {
						creature.convertEnemyFromCreature(creature).enemy_move(
								1, 1, DIRECTION.DOWN_RIGHT);
					} else {
						if (mass[p.x - 1][p.y].WALKABLE
								&& MapList.getEnemy(p.x - 1, p.y) == null) {
							creature.convertEnemyFromCreature(creature)
									.enemy_move(-1, 0, DIRECTION.LEFT);
						} else {
							if (mass[p.x + 1][p.y].WALKABLE
									&& MapList.getEnemy(p.x + 1, p.y) == null) {
								creature.convertEnemyFromCreature(creature)
										.enemy_move(1, 0, DIRECTION.RIGHT);
							} else
								return;
						}
					}
				}
			} else
				creature.convertEnemyFromCreature(creature).enemy_move(0, 1,
						DIRECTION.DOWN);
			break;
		case DOWN:
			if (!mass[p.x][p.y - 1].WALKABLE
					|| MapList.getEnemy(p.x, p.y - 1) != null) {
				if (mass[p.x - 1][p.y - 1].WALKABLE
						&& MapList.getEnemy(p.x - 1, p.y - 1) == null
						&& mass[p.x - 1][p.y].WALKABLE
						&& mass[p.x][p.y - 1].WALKABLE) {
					creature.convertEnemyFromCreature(creature).enemy_move(-1,
							-1, DIRECTION.UP_LEFT);
				} else {
					if (mass[p.x + 1][p.y - 1].WALKABLE
							&& MapList.getEnemy(p.x + 1, p.y - 1) == null
							&& mass[p.x + 1][p.y].WALKABLE
							&& mass[p.x][p.y - 1].WALKABLE) {
						creature.convertEnemyFromCreature(creature).enemy_move(
								1, -1, DIRECTION.UP_RIGHT);
					} else {
						if (mass[p.x - 1][p.y].WALKABLE
								&& MapList.getEnemy(p.x - 1, p.y) == null) {
							creature.convertEnemyFromCreature(creature)
									.enemy_move(-1, 0, DIRECTION.LEFT);
						} else {
							if (mass[p.x + 1][p.y].WALKABLE
									&& MapList.getEnemy(p.x + 1, p.y) == null) {
								creature.convertEnemyFromCreature(creature)
										.enemy_move(1, 0, DIRECTION.RIGHT);
							} else
								return;
						}
					}
				}
			} else
				creature.convertEnemyFromCreature(creature).enemy_move(0, -1,
						DIRECTION.UP);
			break;
		case LEFT:
			if (!mass[p.x + 1][p.y].WALKABLE
					|| MapList.getEnemy(p.x + 1, p.y) != null) {
				if (mass[p.x + 1][p.y + 1].WALKABLE
						&& MapList.getEnemy(p.x + 1, p.y + 1) == null
						&& mass[p.x + 1][p.y].WALKABLE
						&& mass[p.x][p.y + 1].WALKABLE) {
					creature.convertEnemyFromCreature(creature).enemy_move(1,
							1, DIRECTION.DOWN_RIGHT);
				} else {
					if (mass[p.x + 1][p.y - 1].WALKABLE
							&& MapList.getEnemy(p.x + 1, p.y - 1) == null
							&& mass[p.x + 1][p.y].WALKABLE
							&& mass[p.x][p.y - 1].WALKABLE) {
						creature.convertEnemyFromCreature(creature).enemy_move(
								1, -1, DIRECTION.UP_RIGHT);
					} else {
						if (mass[p.x][p.y + 1].WALKABLE
								&& MapList.getEnemy(p.x, p.y + 1) == null) {
							creature.convertEnemyFromCreature(creature)
									.enemy_move(0, 1, DIRECTION.DOWN);
						} else {
							if (mass[p.x][p.y - 1].WALKABLE
									&& MapList.getEnemy(p.x, p.y - 1) == null) {
								creature.convertEnemyFromCreature(creature)
										.enemy_move(0, -1, DIRECTION.UP);
							} else
								return;
						}
					}
				}
			} else
				creature.convertEnemyFromCreature(creature).enemy_move(1, 0,
						DIRECTION.RIGHT);
			break;
		case RIGHT:
			if (!mass[p.x - 1][p.y].WALKABLE
					|| MapList.getEnemy(p.x - 1, p.y) != null) {
				if (mass[p.x - 1][p.y + 1].WALKABLE
						&& MapList.getEnemy(p.x - 1, p.y + 1) == null
						&& mass[p.x - 1][p.y].WALKABLE
						&& mass[p.x][p.y + 1].WALKABLE) {
					creature.convertEnemyFromCreature(creature).enemy_move(-1,
							1, DIRECTION.DOWN_LEFT);
				} else {
					if (mass[p.x - 1][p.y - 1].WALKABLE
							&& MapList.getEnemy(p.x - 1, p.y - 1) == null
							&& mass[p.x - 1][p.y].WALKABLE
							&& mass[p.x][p.y - 1].WALKABLE) {
						creature.convertEnemyFromCreature(creature).enemy_move(
								-1, -1, DIRECTION.UP_LEFT);
					} else {
						if (mass[p.x][p.y + 1].WALKABLE
								&& MapList.getEnemy(p.x, p.y + 1) == null) {
							creature.convertEnemyFromCreature(creature)
									.enemy_move(0, 1, DIRECTION.DOWN);
						} else {
							if (mass[p.x][p.y - 1].WALKABLE
									&& MapList.getEnemy(p.x, p.y - 1) == null) {
								creature.convertEnemyFromCreature(creature)
										.enemy_move(-1, 0, DIRECTION.LEFT);
							} else
								return;
						}
					}
				}
			} else
				creature.convertEnemyFromCreature(creature).enemy_move(-1, 0,
						DIRECTION.LEFT);
			break;
		case DOWN_RIGHT:
			if (!mass[p.x - 1][p.y - 1].WALKABLE
					|| MapList.getEnemy(p.x - 1, p.y - 1) != null) {
				if (mass[p.x][p.y - 1].WALKABLE
						&& MapList.getEnemy(p.x, p.y - 1) == null) {
					creature.convertEnemyFromCreature(creature).enemy_move(0,
							-1, DIRECTION.UP);
				} else {
					if (mass[p.x - 1][p.y].WALKABLE
							&& MapList.getEnemy(p.x - 1, p.y) == null) {
						creature.convertEnemyFromCreature(creature).enemy_move(
								-1, 0, DIRECTION.LEFT);
					} else
						return;
				}
			} else
				creature.convertEnemyFromCreature(creature).enemy_move(-1, -1,
						DIRECTION.UP_LEFT);
			break;
		case DOWN_LEFT:
			if (!mass[p.x + 1][p.y - 1].WALKABLE
					|| MapList.getEnemy(p.x + 1, p.y - 1) != null) {
				if (mass[p.x][p.y - 1].WALKABLE
						&& MapList.getEnemy(p.x, p.y - 1) == null) {
					creature.convertEnemyFromCreature(creature).enemy_move(0,
							-1, DIRECTION.UP);
				} else {
					if (mass[p.x + 1][p.y].WALKABLE
							&& MapList.getEnemy(p.x + 1, p.y) == null) {
						creature.convertEnemyFromCreature(creature).enemy_move(
								1, 0, DIRECTION.RIGHT);
					} else
						return;
				}
			} else
				creature.convertEnemyFromCreature(creature).enemy_move(1, -1,
						DIRECTION.UP_RIGHT);
			break;
		case UP_RIGHT:
			if (!mass[p.x - 1][p.y + 1].WALKABLE
					|| MapList.getEnemy(p.x - 1, p.y + 1) != null) {
				if (mass[p.x][p.y + 1].WALKABLE
						&& MapList.getEnemy(p.x, p.y + 1) == null) {
					creature.convertEnemyFromCreature(creature).enemy_move(0,
							1, DIRECTION.DOWN);
				} else {
					if (mass[p.x - 1][p.y].WALKABLE
							&& MapList.getEnemy(p.x - 1, p.y) == null) {
						creature.convertEnemyFromCreature(creature).enemy_move(
								-1, 0, DIRECTION.LEFT);
					} else
						return;
				}
			} else
				creature.convertEnemyFromCreature(creature).enemy_move(-1, 1,
						DIRECTION.DOWN_LEFT);
			break;
		case UP_LEFT:
			if (!mass[p.x + 1][p.y + 1].WALKABLE
					|| MapList.getEnemy(p.x + 1, p.y + 1) != null) {
				if (mass[p.x][p.y + 1].WALKABLE
						&& MapList.getEnemy(p.x, p.y + 1) == null) {
					creature.convertEnemyFromCreature(creature).enemy_move(0,
							1, DIRECTION.DOWN);
				} else {
					if (mass[p.x - 1][p.y].WALKABLE
							&& MapList.getEnemy(p.x - 1, p.y) == null) {
						creature.convertEnemyFromCreature(creature).enemy_move(
								-1, 0, DIRECTION.LEFT);
					} else
						return;
				}
			} else
				creature.convertEnemyFromCreature(creature).enemy_move(1, 1,
						DIRECTION.DOWN_RIGHT);
			break;
		}
	}

	private static boolean whereIsPlayerInRoad(Base_Creature creature) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (Player.me.getMassPoint().x == creature.getMassPoint().x + i
						&& Player.me.getMassPoint().y == creature
								.getMassPoint().y + j) {
					return true;
				}
			}
		}
		return false;
	}

	private static void whereIsPlayerInRoom(Base_Creature creature, int x, int y) {

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (check_room[x + i][y + j]) {
					continue;
				}
				if (Player.me.getMassPoint().x == x + i
						&& Player.me.getMassPoint().y == y + j) {
					check_room[x + i][y + j] = true;
					player_watched = true;
				}
				if (!mass[x + i][y + j].WALKABLE) {
					check_room[x + i][y + j] = true;
					continue;
				} else if (mass[x + i][y + j].ROAD) {
					check_room[x + i][y + j] = true;
					continue;
				} else if (mass[x + i][y + j].ROOM) {
					check_room[x + i][y + j] = true;
					whereIsPlayerInRoom(creature, x + i, y + j);
				}
			}
		}
		return;
	}
}
