package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

public class 蓬莱山輝夜 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public 蓬莱山輝夜(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public void action() {
		if (conditionCheck(CONDITION.安心)) {
			if (!isSkillActive() || !specialCheck()
					|| Player.me.getConditionList().contains(CONDITION.やりすごし)
					|| Player.me.getConditionList().contains(CONDITION.暗闇)
					|| Player.me.getConditionList().contains(CONDITION.透明)) {
				enemy_actioned = true;
				return;
			}
		}
		super.action();
	}

	@Override
	public Image getImage() {
		if (conditionCheck(CONDITION.安心)) {
			Image im = super.getImage();
			if (im.equals(IM.getWalkImage(LV, direction,
					getMoveAnimationSpeed()))) {
				return IM.getSPImage(LV, direction, 0);
			} else {
				return im;
			}
		} else {
			return super.getImage();
		}
	}

	@Override
	protected CONDITION getInitCondition() {
		setConditionList(CONDITION.安心, 0);
		return CONDITION.反射;
	}

	@Override
	public int isKamin() {
		return 0;
	}

	@Override
	protected int itemDropParcent() {
		return 2000;
	}

	@Override
	protected boolean specialAttack() {
		if (conditionCheck(CONDITION.安心)) {
			return true;
		}
		if (!attack_possible()) {
			return false;
		}
		final Base_Creature ME = this;
		boolean flag = Player.me.conditionCheck(CONDITION.睡眠)
				|| EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
						ENCHANT_SIMBOL.春);
		SE.ATTACK_HEAVY.play();
		if (flag) {
			Message.set(ME.getColoredName(), "は永夜の眠りにいざなった");
			if (Player.me.conditionCheck(CONDITION.睡眠)) {
				Message.set("しかし", Player.me.getColoredName(), "は既に眠っていた");
			} else {
				if (Player.me.setCondition(CONDITION.睡眠, 0)) {
					SE.STATUS_SLEEP.play();
					Message.set(Player.me.getColoredName(), "は眠ってしまった");
				}
			}
			startAttack(null);
		} else {
			Message.set(ME.getColoredName(), "は永夜の眠りにいざなった@");
			SE.ATTACK_HEAVY.play();
			startAttack(new Task() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {

					Message.setTask_AfterReleaseDemandToPushEnter(new Task() {

						/**
						 *
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void work() {
							if (Player.me.setCondition(CONDITION.睡眠, 0)) {
								SE.STATUS_SLEEP.play();
								Message.set(Player.me.getColoredName(),
										"は眠ってしまった");
							}
						}
					});
				}
			});

		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (conditionCheck(CONDITION.安心)) {
			return true;
		}
		return LV > 1 && attack_possible() && isSpecialParcent();
	}

}
