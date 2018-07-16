package dangeon.model.object.creature.enemy;

import java.awt.Color;
import java.awt.Point;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class リグルナイトバグ extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public リグルナイトバグ(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Base_Creature c = MapInSelect.getFrontEnemyFromCreature(Player.me);
		if (c == null) {
			return false;
		}
		MapInSelect.転ばし(Player.me, "蹴飛ばすぞ", c, Player.me.getDirection(), 1,
				10 * LV * 2);
		return true;
	}

	@Override
	protected void enemyBreakAction() {
		if (CONDITION.check(this, CONDITION.封印)
				|| CONDITION.check(this, CONDITION.ええんじゃないか)) {
			return;
		}
		if (LV == 4) {
			Message.set(getColoredName(), "は爆発した", Color.WHITE.toString());
			MapInSelect.explosion(getMassPoint());
		}
	}

	private void kick() {
		SE.ATTACK_HEAVY.play();
		final リグルナイトバグ ME = this;
		Task kick = new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Message.set(getColoredName(), "は", Player.me.getColoredName(),
						"を蹴り飛ばした");
				int dam;
				if (LV == 1) {
					dam = 5;
				} else if (LV == 2) {
					dam = 15;
				} else if (LV == 3) {
					dam = 30;
				} else {
					dam = 50;
				}
				MapInSelect.転ばし(ME, null, Player.me, getDirection(), 1, dam,
						LV + 1);
			}
		};
		startAttack(kick);
	}

	@Override
	protected boolean specialAttack() {
		if (!attack_possible()) {
			return false;
		}
		kick();
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (!attack_possible()) {
			return false;
		}
		if (!isSpecialParcent()) {
			return false;
		}
		return true;
	}

}
