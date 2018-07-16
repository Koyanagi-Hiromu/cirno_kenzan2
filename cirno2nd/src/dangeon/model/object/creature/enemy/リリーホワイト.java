package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.anime.Special_Wait_FrameByFrame;

public class リリーホワイト extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public リリーホワイト(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		switch (LV) {
		case 4:
			// for (Base_Creature c : MapList.getListCreature()) {
			// if (MassCreater.isPointInTheSameRoom(getMassPoint(),
			// c.getMassPoint())) {
			// if (c.equals(this)) {
			// continue;
			// }
			// if (hpCheck(c)) {
			// return true;
			// }
			// }
			// }
		case 3:
		case 2:
			for (Base_Creature c : MapList.getListAroundEnemy(this
					.getMassPoint())) {
				if (c.equals(this)) {
					continue;
				}
				if (hpCheck(c)) {
					return true;
				}
			}
			if (LV >= 3)
				return false;
		case 1:
			for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
				if (MapInSelect.isCreatureOnStraight(d, Player.me, mass_point,
						1))
					return hpCheck(Player.me);
			}
		}
		return false;
	}

	private void cure(Base_Creature c) {
		CONDITION.conditionOneEnemyContinue(c);
	}

	private void dance(final Task t) {
		final DIRECTION d = direction;
		final Base_Creature C = this;
		setAnimation(new Special_Wait_FrameByFrame(this, SE.IKAKUTYO, 1,
				new Task() {
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
					};

					@Override
					public void work(int frame) {
						if (frame == 2) {
							setMassPoint_ParabolaJump_NoAttack(mass_point);
						}
						if (frame < 7) {
							C.direction = DIRECTION
									.getDirectionFromRotate(frame
											+ d.getRotate() + 3);
						} else {
							if (t != null)
								t.work();
							C.direction = d;
						}
					}
				}, -1, -1, -1, -1, -1, -1, -1, -1));
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		for (Iterator<Base_Enemy> iterator = MapInSelect
				.getRoomOrRoadInEnemyList(); iterator.hasNext();) {
			iterator.next().heal(Player.me, 100 * LV);
		}
		return true;
	}

	@Override
	public Image getImage() {
		if (getAnimation() != null) {
			return IM.getWalkImage(LV, direction, 0);
		} else
			return super.getImage();
	}

	private ArrayList<Base_Creature> getTargetList() {
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		switch (LV) {
		case 4:
			for (Base_Creature c : MapList.getListCreature()) {
				if (MassCreater.isPointInTheSameRoom(getMassPoint(),
						c.getMassPoint())) {
					if (hpCheck(c)) {
						list.add(c);
					}
				}
			}
		case 3:
		case 2:
			for (Base_Creature c : MapList.getListAroundEnemy(this
					.getMassPoint())) {
				if (c instanceof リリーホワイト) {
					continue;
				}
				if (!list.contains(c) && hpCheck(c)) {
					list.add(c);
				}
			}
			if (LV >= 3)
				return list;
		case 1:
			for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
				if (MapInSelect.isCreatureOnStraight(d, Player.me, mass_point,
						1))
					if (hpCheck(Player.me)) {
						list.add(Player.me);
					}
			}
		}
		return list;
	}

	private void heal(Base_Creature c) {
		c.heal(this, 15 * LV);
	}

	private boolean hpCheck(Base_Creature c) {
		return c.getMAX_HP() > c.getHP() && c.getHP() > 0;
	}

	private void sleep() {
		final Base_Creature ME = this;
		boolean flag = Player.me.conditionCheck(CONDITION.睡眠)
				|| EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
						ENCHANT_SIMBOL.春);
		Message.set(ME.getColoredName(), "は春の眠りにいざなった");
		if (flag) {
			if (Player.me.conditionCheck(CONDITION.睡眠)) {
				Message.set("しかし", Player.me.getColoredName(), "は既に眠っていた");
			} else {
				if (Player.me.setCondition(CONDITION.睡眠, 0)) {
					SE.STATUS_SLEEP.play();
					Message.set(Player.me.getColoredName(), "は眠ってしまった");
				}
			}
			dance(null);
		} else {
			dance(new Task() {

				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					if (Player.me.setCondition(CONDITION.睡眠, 2)) {
						SE.STATUS_SLEEP.play();
						Message.set(Player.me.getColoredName(), "は眠ってしまった");
					}

				}
			});

		}
	}

	@Override
	protected boolean specialAttack() {
		if (!check()) {
			if (LV == 4 && attack_possible()) {
				sleep();
				return true;
			} else {
				return false;
			}
		}
		ArrayList<Base_Creature> list = getTargetList();
		if (list.isEmpty()) {
			if (LV == 4 && attack_possible()) {
				sleep();
			}
			return true;
		}
		final Base_Creature target = list.get(new R().nextInt(list.size()));
		dance(new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				whiteHeal(target);
			}
		});
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (isSpecialParcent()) {
			if (LV == 4 && attack_possible())
				return true;
			return !getTargetList().isEmpty();
		}
		return false;
	}

	private void whiteHeal(Base_Creature c) {
		Message.set(getColoredName(), "は", c.getColoredName(), "を癒した");
		cure(c);
		heal(c);
	}
}
