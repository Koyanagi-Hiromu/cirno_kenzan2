package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.action.SpecialAction;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class 多々良小傘 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 多々良小傘(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		if (!MassCreater.getMass(Player.me.getMassPoint()).ROOM) {
			return false;
		}
		if (MapInSelect.aroundEmptyCheck(Player.me.getMassPoint()).isEmpty()) {
			return false;
		}
		return true;
	}

	private boolean effect() {
		if (!check()) {
			return false;
		}
		if (roomInWarp()) {
			return true;
		}
		warp(true);
		Message.set(getColoredName(), "が驚かしにきた");
		return true;
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		MapInSelect.aroundInEnemyChengeCondition(CONDITION.麻痺, 0);
		SpecialAction.高飛び(Player.me);
		return true;
	}

	@Override
	public Image getATKImage(int LV, DIRECTION attacking_direction,
			int attack_No) {
		if (attaking_frame >= 12) {
			attack_No = 1;
		}
		return super.getATKImage(LV, attacking_direction, attack_No);
	}

	@Override
	protected Point getAttackPoint() {
		// return screen_point;
		return new Point(screen_point.x + attack_delt_point.x / 2,
				screen_point.y + attack_delt_point.y / 2);
		// return super.getAttackPoint();
	}

	private void odoroke(final Point p, boolean jump) {
		if (jump) {
			SE.KOGASA_SPELL.play();
			MainMap.addEffect(new DoronEffect(p, new Task() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
				}

				@Override
				protected void work(int frame) {
					if (frame == 4) {
						setMassPoint(p);
						direction = DIRECTION.getDirection(p,
								Player.me.getMassPoint());
					}
				}
			}, true), true);
		} else {
			SE.WARP_INSTANT.play();
			setMassPoint_WalkLike(p, 3);
			direction = DIRECTION.getDirection(p, Player.me.getMassPoint());
		}
	}

	private boolean parcent() {
		if (player_is_in_sight) {
			if (MassCreater.getMass(Player.me.getMassPoint()).ROOM
					&& MassCreater.getMass(getMassPoint()).ROOM) {
				return true;
			}
		}
		if (isSpecialParcent()) {
			return true;
		}
		return false;
	}

	private boolean roomInWarp() {
		if (player_is_in_sight) {
			if (MassCreater.getMass(Player.me.getMassPoint()).ROOM
					&& MassCreater.getMass(getMassPoint()).ROOM) {
				warp(false);
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean specialAttack() {
		effect();
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (attack_possible()) {
			return false;
		}
		if (!parcent()) {
			return false;
		}
		return check();
	}

	private void warp(boolean jump) {
		Point player = Player.me.getMassPoint().getLocation();
		DIRECTION[] ds = Player.me.getDirection()
				.getNeiboringDirections8_Head();
		for (DIRECTION d : ds) {
			Point p = new Point(player.x + d.X, player.y + d.Y);
			if (!MassCreater.getMass(p).WALKABLE) {
				continue;
			}
			if (MassCreater.getMass(p).WATER) {
				continue;
			}
			if (MapList.getCreature(p) != null) {
				continue;
			}
			odoroke(p, jump);
			return;
		}
		// List<Point> list = new ArrayList<Point>();
		// for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
		// Point _P = new Point(player.x + d.X, player.y + d.Y);
		// if (!MassCreater.getMass(_P).WALKABLE) {
		// continue;
		// }
		// if (MassCreater.getMass(_P).WATER) {
		// continue;
		// }
		// if (MapList.getCreature(_P) != null) {
		// continue;
		// }
		// list.add(_P);
		// }
		// if (list.isEmpty()) {
		// return;
		// }
		// int select = new R().nextInt(list.size());
		// Point __p = Target.ME.get().getDirection()
		// .getFrontPoint(Target.ME.get().getMassPoint().getLocation());
		// final Point _p;
		// if (list.contains(player)) {
		// _p = __p;
		// } else {
		// _p = list.get(select);
		// }

	}

}
