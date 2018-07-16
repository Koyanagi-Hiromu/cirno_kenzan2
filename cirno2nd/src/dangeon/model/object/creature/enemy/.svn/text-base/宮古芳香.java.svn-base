package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印毒消し;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

public class 宮古芳香 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private final int lv1 = 15;

	private final int lv2 = 30;

	private final int lv3 = 10;

	private boolean flag_sp;

	public 宮古芳香(Point p, int Lv) {
		super(p, Lv);
	}

	private void effect() {
		Player.me.startDamaging();
		Message.set(getColoredName(), "は", Player.me.getColoredName(), "をかじった！");
		if (!印毒消し.effect()) {
			MainMap.addEffect(new GoodBadEffect(false));
			SE.ISHUKUSHO.play();
			if (LV == 1) {
				Player.me.chengeSatiety(-lv1);
				Message.set("満腹度が" + lv1, "下がった");
			} else if (LV == 2) {
				Player.me.chengeSatiety(-lv1);
				Message.set("満腹度が" + lv1, "下がった");
				SE.STATUS_SLOW.play();
				Player.me.setCondition(CONDITION.鈍足, 0);
			} else {
				if (new R().is(50)) {
					Player.me.chengeSatiety(-lv2);
					Message.set("満腹度が" + lv2, "下がった");
				} else {
					Player.me.chengeMaxSatiety(-lv3);
					Message.set("最大満腹度が" + lv3, "下がった");
				}
				SE.STATUS_SLOW.play();
				Player.me.setCondition(CONDITION.鈍足, 0);
			}
		}
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Player.me.chengeSatiety(10 * LV);
		return true;
	}

	@Override
	public SE getAttackSE() {
		if (flag_sp)
			return SE.SYSTEM_EAT;
		return super.getAttackSE();
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			return IM.getATKImage(LV, direction, getAnimation().getComa());
		} else {
			return super.getImage();
		}
	}

	@Override
	protected boolean specialAttack() {
		SE.ATTACK_EAT.play();
		flag_sp = true;
		startAttack(new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				effect();
			};
		});
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

	@Override
	protected void upDate_NormalAttack() {
		super.upDate_NormalAttack();
		if (flag_sp) {
			if (attack_No == 1)
				attack_No = 0;
			if (normal_attack_phase == AttackPhase.BACK) {
				flag_sp = false;
			}
		}
	}

}
