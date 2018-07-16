package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class レミリア extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private boolean flag_shoot = false;

	public レミリア(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		if (LV < 4 && !player_is_in_sight) {
			return false;
		}
		if (playerIsInNeiver()) {
			return false;
		}
		if (LV == 4) {
			Point p = getMassPoint().getLocation();
			DIRECTION dir = getDirection();
			while (true) {
				p.translate(dir.X, dir.Y);
				Base_Creature c = MapList.getCreature(p);
				if (c != null) {
					if (c.equals(Player.me)) {
						flag_shoot = true;
						return true;
					} else {
						return false;
					}
				}
				if (MassCreater.getMass(p).equals(Mass.nullpo)) {
					return false;
				}
			}
		} else {
			for (DIRECTION dir : DIRECTION.values_exceptNeatral()) {
				if (MapInSelect.getStraightMoveCheck(dir, getMassPoint(), 0) instanceof Player) {
					flag_shoot = true;
					return true;
				}
			}

		}
		return false;
	}

	@Override
	protected void drain(int dam) {
		int min = 10;
		if (dam < min)
			return;
		if (LV == 2 || LV == 1) {
			dam = (int) Math.round(dam / 4d);
		} else {
			dam = (int) Math.round(dam / 2d);
		}
		if (dam < min)
			dam = min;

		heal(this, dam);
	}

	private void effect(DIRECTION dir) {
		SE.WARP_INSTANT.play();
		Message.set(getColoredName().concat("は突撃してきた"));
		setMassPoint_WalkLike(new Point(Player.me.getMassPoint().x - dir.X,
				Player.me.getMassPoint().y - dir.Y), 2, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				attack();
				upDate();
			}
		});
		// setMassPoint_WalkLike(, 5);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		DIRECTION d = Player.me.getDirection();
		Point p = Player.me.getMassPoint().getLocation();
		Base_Creature e = MapInSelect.getStraightMoveCheck(d, p, 0);
		System.out.println(e);
		if (e == null) {
			return false;
		}
		Point _p = e.getMassPoint().getLocation();
		Point f_p = new Point(_p.x - d.X, _p.y - d.Y);
		SE.WARP_INSTANT.play();
		Task ta = new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Player.me.normalAttack();
			}

		};
		Player.me.attack_point = f_p;
		Player.me.setMassPoint_WalkLike(f_p, 10, ta);
		return true;
	}

	@Override
	protected boolean specialAttack() {
		if (flag_shoot && check()) {
			direction = converDirection(Player.me.getMassPoint());
			effect(direction);
			return true;
		} else {
			attack();
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		flag_shoot = false;
		if (isSpecialParcent()) {
			if (check()) {
				return true;
			}
		}
		return LV > 1 && attack_possible();
	}

}
