package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.bullet.御柱;
import dangeon.model.object.creature.player.Player;

public class 八坂神奈子 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private boolean flag_throwing = false;

	public 八坂神奈子(Point p, int Lv) {
		super(p, Lv);
	}

	private void effect() {
		SE.THROW_HEAVY.play();
		setThrowing();
		Message.set(getColoredName().concat("は").concat("御柱を投げた"));
		new 御柱(this).itemThrow(this, HowToThrow.BREAK, 3);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		SE.THROW_HEAVY.play();
		new 御柱(this).itemThrow(this, HowToThrow.BREAK, 3);
		return false;
	}

	@Override
	protected Point getAttackPoint() {
		if (flag_throwing) {
			return screen_point;
		} else {
			return super.getAttackPoint();
		}
	}

	@Override
	public Image getImage() {
		if (flag_throwing) {
			return IM.getSPImage(LV, attacking_direction, attack_No);
		}
		return super.getImage();
	}

	private boolean loop(int x, int y) {
		int xx = 0;
		int yy = 0;
		for (int i = 0; i < 3; i++) {
			xx += x;
			yy += y;
			if (Player.me.getMassPoint().x == getMassPoint().x + xx
					&& Player.me.getMassPoint().y == getMassPoint().y + yy) {
				return true;
			}
		}
		return false;
	}

	private boolean playerSearchCheck() {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (loop(i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	public void setThrowing() {
		attacking_direction = direction;
		normal_attack_phase = AttackPhase.GO;
		flag_throwing = true;
	}

	@Override
	protected boolean specialAttack() {
		if (!player_is_in_sight) {
			return false;
		}
		if (!playerSearchCheck()) {
			return false;
		}
		effect();
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (!player_is_in_sight) {
			return false;
		}
		if (!isSpecialParcent()) {
			return false;
		}
		return playerSearchCheck();
	}

	@Override
	public void upDate() {
		if (flag_throwing) {
			super.upDate_NormalAttack();
			flag_throwing = (normal_attack_phase != null);
		} else {
			super.upDate();
		}
	}

	// private void effect() {
	// DIRECTION dir = converDirection(Player.me.getMassPoint());
	// Point e = getMassPoint().getLocation();
	// Point value = new Point(dir.X, dir.Y);
	// Point point = getMassPoint().getLocation();
	// for (int i = 0; i < 3; i++) {
	// point.x += value.x;
	// point.y += value.y;
	// if (MapList.getCreature(point) != null) {
	// Base_Creature c = MapList.getCreature(point);
	// if (c instanceof Player) {
	// playerDamage(true);
	// } else {
	// damageEnemyToEnemy(
	// (Base_Enemy) c,
	// damageValueEnemyEnemy((Base_Enemy) this,
	// (Base_Enemy) c));
	// }
	// }
	// }
	// }

}
