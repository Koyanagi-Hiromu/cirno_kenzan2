package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.bullet.アンカー;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 村紗水蜜 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private boolean flag_throwing = false;

	public 村紗水蜜(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		Point p = getMassPoint();
		Point p2 = Player.me.getMassPoint();
		if (!MassCreater.is1InTheSightFrom2(p2.x, p2.y, p.x, p.y, 1)) {
			return false;
		}
		for (DIRECTION dir : DIRECTION.values_exceptNeatral()) {
			if (MapInSelect.isCreatureOnStraight(dir, Player.me, p, 10)) {
				return true;
			}
		}
		return false;
	}

	private void effect() {
		setThrowing();
		Message.set(getColoredName().concat("は").concat("ぶっとびアンカーを投げた"));
		new アンカー(this).itemThrow(this, HowToThrow.BREAK);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		new アンカー(Player.me).itemThrow(Player.me, HowToThrow.BREAK);
		return true;
	}

	@Override
	public Image getImage() {
		if (flag_throwing) {
			return IM.getSPImage(LV, attacking_direction, attack_No);
		}
		return super.getImage();
	}

	public void setThrowing() {
		attacking_direction = direction;
		normal_attack_phase = AttackPhase.GO;
		flag_throwing = true;
	}

	@Override
	protected boolean specialAttack() {
		if (check()) {
			effect();
			return true;
		}
		return false;
	}

	@Override
	protected boolean specialCheck() {
		if (check()) {
			if (isSpecialParcent()) {
				return true;
			}
		}
		return false;
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

}
