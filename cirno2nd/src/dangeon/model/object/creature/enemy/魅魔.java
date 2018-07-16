package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DoronEffect;
import dangeon.view.anime.EvilField;
import dangeon.view.detail.MainMap;

public class 魅魔 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 魅魔(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public void action() {
		super.action();
		// enemy_actioned = true;
	}

	private boolean attackCheck() {
		if (!player_is_in_sight)
			return false;
		int length = 2;
		Point p = Player.me.getMassPoint().getLocation();
		Point e = getMassPoint().getLocation();
		for (int i = -length; i <= length; i++) {
			for (int j = -length; j <= length; j++) {
				if (p.x == e.x + i && p.y == e.y + j) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean check() {
		return attackCheck() || warpCheck();
	}

	private boolean effect() {
		if (warpCheck()) {
			if (roomInWarp()) {
				warp(false);
			} else {
				warp(true);
				Message.set(getColoredName(), "が現れた");
				Scene_Action.getMe().set_I_Dushing_request_arrow_key(true);
			}
		} else {
			startAttack(getAttackTask(this, Player.me),
					Player.me.getMassPoint());
			Message.set(getColoredName(), "は破壊の魔法を唱えた");
		}
		return true;
	}

	/**
	 * <pre>
	 * 　２　３
	 * １　　　４　
	 * 　　←
	 * ８　　　５
	 * 　７　６
	 * 
	 * 　７　８　
	 * ６　　　１
	 * 　　➚　　
	 * ５　　　２
	 *  ４　３
	 * </pre>
	 */
	private Point[] get8Points() {
		Point[] array = new Point[8];

		DIRECTION[] ds = Player.me.getDirection().getNeiboringDirections8();
		for (int i = 0; i < ds.length; i++) {
			DIRECTION direction = ds[i];
			array[i] = getMass(direction);
		}
		return array;
	}

	@Override
	public Image getATKImage(int LV, DIRECTION attacking_direction,
			int attack_No) {
		return super.getATKImage(LV, attacking_direction, attack_No);
		// return IM.getATKImage(0, direction, 0);
	}

	@Override
	protected Point getAttackPoint() {
		return screen_point;
	}

	private Point getMass(DIRECTION direction) {
		Point p = Player.me.getMassPoint().getLocation();
		direction = direction.getDeNeiboringDirection();
		direction.getFrontPoint(p);
		direction.getFrontPoint(p);
		if (direction.isBias()) {
			direction.getNeiboringDirection().getNeiboringDirection()
					.getNeiboringDirection().getFrontPoint(p);
		} else {
			direction.getNeiboringDirection().getNeiboringDirection()
					.getFrontPoint(p);
		}
		return p;
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
		if (parcent() && warpCheck())
			return true;
		return isSpecialParcent() && attackCheck();
	}

	@Override
	public void startAttack(Task t) {
		startAttack(t, this.direction.getFrontPoint(mass_point.getLocation()));
	}

	private void startAttack(Task t, Point p) {
		SE.ATTACK_HEAVY.play();
		MainMap.addEffect(new EvilField(p.getLocation()));
		super.startAttack(t);
	}

	@Override
	protected void upDate_NormalAttack() {
		super.upDate_NormalAttack();
	}

	private void warp(boolean jump) {
		for (Point p : get8Points()) {
			if (!MassCreater.getMass(p).isWalkable()) {
				continue;
			}
			if (MapList.getCreature(p) != null) {
				continue;
			}
			odoroke(p, jump);
			return;
		}
	}

	private boolean warpCheck() {
		if (attack_possible()) {
			return false;
		}
		if (!MassCreater.getMass(Player.me.getMassPoint()).ROOM) {
			return false;
		}
		boolean flag = false;
		for (Point p : get8Points()) {
			if (!MassCreater.getMass(p).isWalkable())
				continue;
			Base_Creature c = MapList.getCreature(p);
			if (c == this)
				return false;
			if (c != null)
				continue;
			flag = true;
		}
		return flag;
	}
}
