package dangeon.model.object.creature.enemy;

import java.awt.Color;
import java.awt.Point;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.grass.毒草;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.resistant.Poison;
import dangeon.util.MapInSelect;
import dangeon.view.anime.Special_Wait_FrameByFrame;

public class メディスン extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public メディスン(Point p, int Lv) {
		super(p, Lv);
	}

	private void down() {
		int i = Player.me.getNoEnchantStr();
		if (i <= 1) {
			hpDown();
		} else {
			powerDown();
		}
	}

	private void effect() {
		Message.set(Color.RED.toString(), getColoredName().concat("は毒を撒いた"),
				Color.WHITE.toString());

		if (!EnchantSpecial.enchantSimbolAllCheck(CASE.DEF, ENCHANT_SIMBOL.消)
				&& !EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
						ENCHANT_SIMBOL.毒)
				&& !EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
						ENCHANT_SIMBOL.衰)) {
			Player.me.startDamaging();
		}
		setAnimation(new Special_Wait_FrameByFrame(this, SE.POIZON, 1,
				new Task() {
					/**
			 *
			 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						down();
					};

				}, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5));

	}

	@Override
	public boolean enchantEnemySpecialAction() {
		for (Base_Creature c : MapInSelect.getListAroundInCreature(Player.me
				.getMassPoint())) {
			Poison.effect(c, LV, false, true);
		}
		Message.set("周りに毒を撒いた");
		return true;
	}

	@Override
	protected Point getAttackPoint() {
		return screen_point;
	}

	@Override
	protected Base_Artifact getDropItem() {
		Base_Artifact a = new 毒草(mass_point);
		a.check();
		return a;
	}

	private void hpDown() {
		switch (LV) {
		case 1:
			Poison.effect(Player.me, 2, false, true, true);
			break;
		case 2:
			Poison.effect(Player.me, 4, false, true, true);
			break;
		case 3:
			Poison.effect(Player.me, 5, false, true, true);
			break;
		case 4:
			if (Player.me.getNoEnchantMaxStr() == 1) {
				Poison.effect(Player.me, 10, false, true, true);
			} else {
				Poison.effect(Player.me, 3, true, true);
			}
			break;
		}
	}

	private void powerDown() {
		switch (LV) {
		case 1:
			Poison.effect(Player.me, 1, false, true);
			break;
		case 2:
			Poison.effect(Player.me, 2, false, true);
			break;
		case 3:
			Poison.effect(Player.me, 3, false, true);
			break;
		case 4:
			Poison.effect(Player.me, 8, false, true);
			break;
		}

	}

	@Override
	protected boolean specialAttack() {
		if (attack_possible()) {
			direction = converDirection(Player.me.getMassPoint());

			effect();
		}
		return true;
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
