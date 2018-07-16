package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.anime.Special_Wait_FrameByFrame;

public class 寅丸星 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private Base_Creature c = null;

	public 寅丸星(Point p, int Lv) {
		super(p, Lv);
	}

	private List<Base_Enemy> around() {
		List<Base_Enemy> list = new ArrayList<Base_Enemy>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				if (MapList.getEnemy(new Point(getMassPoint().x + i,
						getMassPoint().y + j)) != null) {
					if (attackPossibleFromEnemy(MapList.getEnemy(new Point(
							getMassPoint().x + i, getMassPoint().y + j)))) {
						list.add(MapList.getEnemy(new Point(getMassPoint().x
								+ i, getMassPoint().y + j)));
					}
				}
			}
		}
		return list;
	}

	private boolean creatureSearch() {
		for (Base_Creature c : MapList.getListCreature()) {
			if (creatureSearch(c)) {
				return true;
			}
		}
		return false;
	}

	private boolean creatureSearch(Base_Creature c) {
		if (c == this) {
			return false;
		}
		int length = length();
		for (int i = -length; i <= length; i++) {
			for (int j = -length; j <= length; j++) {
				if (c.getMassPoint().x == getMassPoint().x + i
						&& c.getMassPoint().y == getMassPoint().y + j) {
					if (MassCreater.getMass(c.getMassPoint()).WALKABLE) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void creatureThrow(Base_Creature _c, final Point goal,
			final Task task) {
		c = _c;
		// c.getMassPoint().setLocation(mass_point.getLocation());
		// c.getScreenPoint().setLocation(
		// ObjectPoint.getDangeonScreenPoint(mass_point));
		c.setMassPoint(mass_point.getLocation());
		c.jumped_away_y = -30;
		final int[] i;
		final int t;
		if (c == Player.me) {
			int[] a = { 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
			i = a;
			t = 8;
		} else {
			direction = DIRECTION.getDirectionToPlayer(this);
			int[] a = { 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
			i = a;
			t = 5;
		}
		final 寅丸星 me = this;
		setAnimation(new Special_Wait_FrameByFrame(this, SE.THROW_BOMB, t,
				new Task() {
					/**
			 *
			 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
					};

					@Override
					public void work(int frame) {
						c.startDamaging();
						if (frame < t) {
							if (c == Player.me) {
								c.jumped_away_y -= 2;
							} else {
								c.jumped_away_y -= 3;
							}
						} else if (frame == t - 2) {
							if (c != Player.me
									|| !EnchantSpecial.enchantSimbolAllCheck(
											CASE.DEF, ENCHANT_SIMBOL.イカリ)) {
								setDirection(DIRECTION.getDirection(
										getMassPoint(), goal));
							}
						} else if (frame == t) {
							Message.set(getColoredName(), "は",
									c.getColoredName(), "を放り投げた");
							if (c == Player.me
									&& EnchantSpecial.enchantSimbolAllCheck(
											CASE.DEF, ENCHANT_SIMBOL.イカリ)) {
								Message.set("しかし重くて飛ばなかった");
								c.setMassPoint_ParabolaJumpAttack(
										new Point(Player.me.getMassPoint().x
												+ me.direction.X, Player.me
												.getMassPoint().y
												+ me.direction.Y), null);
							} else {
								c.setMassPoint_ParabolaJumpAttack(goal, task);
							}
						}
					}
				}, i));
	}

	private boolean effect() {
		// if (!player_is_in_sight) {
		// return false;
		// }
		boolean flag;
		if (new R().nextInt(3) == 0) {
			flag = method_playerThrow();
			if (flag)
				return true;
			flag = method_enemyThrow();
		} else {
			flag = method_enemyThrow();
			if (flag)
				return true;
			flag = method_playerThrow();
		}
		return flag;
	}

	private boolean enemyThrow() {
		List<Base_Enemy> list = around();
		if (list.isEmpty()) {
			return false;
		}
		creatureThrow(list.get(new R().nextInt(list.size())),
				Player.me.getMassPoint(), null);
		return true;
	}

	private Point getCreatureSearchOnePoint() {
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		int length = length();
		for (Base_Creature c : MapList.getListCreature()) {
			if (c == this) {
				continue;
			}
			for (int i = -length; i <= length; i++) {
				for (int j = -length; j <= length; j++) {
					if (c.getMassPoint().x == getMassPoint().x + i
							&& c.getMassPoint().y == getMassPoint().y + j) {
						list.add(c);
					}
				}
			}
		}
		return list.get((int) (new R().nextDouble() * list.size()))
				.getMassPoint();
	}

	private int length() {
		if (LV == 1) {
			return 5;
		} else if (LV == 2) {
			return 10;
		} else {
			return 20;
		}
	}

	private boolean method_enemyThrow() {
		if (!around().isEmpty() && creatureSearch(Player.me)) {
			enemyThrow();
			return true;
		} else {
			return false;
		}
	}

	private boolean method_playerThrow() {
		if (attack_possible()) {
			boolean flag_trap = !trapThrow().isEmpty();
			boolean flag_enemy = creatureSearch();
			if (flag_enemy && flag_trap) {
				if (new R().nextInt(2) == 0) {
					flag_enemy = false;
				} else {
					flag_trap = false;
				}
			}
			if (flag_trap) {
				trapSelect();
				return true;
			}
			if (flag_enemy) {
				creatureThrow(Player.me, getCreatureSearchOnePoint(), null);
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean specialAttack() {
		if (!specialCheck()) {
			return false;
		}
		return effect();
	}

	@Override
	protected boolean specialCheck() {
		if (!isSpecialParcent()) {
			return false;
		}
		if (LV <= 3 && !player_is_in_sight) {
			return false;
		}
		if (attack_possible()) {
			if (!trapThrow().isEmpty()) {
				return true;
			}
			if (creatureSearch()) {
				return true;
			}
		}
		if (around().isEmpty()) {
			return false;
		}
		if (!creatureSearch(Player.me)) {
			return false;
		}
		return true;
	}

	private void trapSelect() {
		List<Base_Trap> list = trapThrow();
		final Base_Trap trap = list.get(new R().nextInt(list.size()));
		creatureThrow(Player.me, trap.getMassPoint(), new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				trap.trapEffect();
			}
		});
	}

	private List<Base_Trap> trapThrow() {
		int length = length();
		List<Base_Trap> list = new ArrayList<Base_Trap>();
		Point p = Player.me.getMassPoint();
		Point ep = getMassPoint();
		for (int i = -length; i <= length; i++) {
			for (int j = -length; j <= length; j++) {
				if (p.x == ep.x + i && p.y == ep.y + j) {
					continue;
				}
				if (MapList.getTrap(new Point(ep.x + i, ep.y + j)) != null) {
					if (MapList.getEnemy(ep.x + i, ep.y + j) == null) {
						list.add(MapList.getTrap(new Point(ep.x + i, ep.y + j)));
					}
				}
			}
		}
		return list;
	}

}
