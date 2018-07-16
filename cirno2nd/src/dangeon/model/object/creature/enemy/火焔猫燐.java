package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印招;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 火焔猫燐 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 火焔猫燐(Point p, int Lv) {
		super(p, Lv);
	}

	private void effect() {
		SE.SUMMON.play();
		if (印招.effect()) {
			Message.set(getColoredName(), "は召喚に失敗した");
			return;
		}
		MapInSelect.createEnemy(getMassPoint(), LV, LV, true);
		Message.set(getColoredName(), "はエネミーを召喚した");
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		SE.SUMMON.play();
		MapInSelect.createEnemy(getMassPoint(), (LV == 4) ? 1 : LV, LV, true);
		Message.set("エネミーを召喚した");
		return true;
	}

	@Override
	protected boolean specialAttack() {
		if (attack_possible()) {
			direction = converDirection(Player.me.getMassPoint());

			if (!MapInSelect.aroundEmptyCheck(getMassPoint()).isEmpty()) {
				effect();
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean specialCheck() {
		if (attack_possible()) {
			if (isSpecialParcent()) {
				return true;
			}
		}
		return false;
	}

}
