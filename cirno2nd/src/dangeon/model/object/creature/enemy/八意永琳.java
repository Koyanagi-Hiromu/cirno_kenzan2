package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.List;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.grass.毒草;
import dangeon.model.object.artifact.item.grass.混乱草;
import dangeon.model.object.artifact.item.grass.睡眠草;
import dangeon.model.object.artifact.item.grass.胃拡張の種;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import main.util.DIRECTION;

public class 八意永琳 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private boolean flag_throwing = false;

	public 八意永琳(Point p, int Lv) {
		super(p, Lv);
	}

	// public Image getImage() {
	// return main.res.IMAGE.幽香.getImage(direction, MainThread.getKoma());
	// }

	private boolean check() {
		if (!player_is_in_sight) {
			return false;
		}
		Point player_point = Player.me.getMassPoint();
		if (getMassPoint().x == player_point.x) {
			if (getMassPoint().y > player_point.y) {
				direction = DIRECTION.UP;
			} else {
				direction = DIRECTION.DOWN;
			}
		} else if (getMassPoint().y == player_point.y) {
			if (getMassPoint().x > player_point.x) {
				direction = DIRECTION.LEFT;
			} else {
				direction = DIRECTION.RIGHT;
			}
		} else if (Math.abs(getMassPoint().x - player_point.x) == Math
				.abs(getMassPoint().y - player_point.y)) {
			if (getMassPoint().x > player_point.x) {
				if (getMassPoint().y > player_point.y) {
					direction = DIRECTION.UP_LEFT;
				} else {
					direction = DIRECTION.DOWN_LEFT;
				}
			} else {
				if (getMassPoint().y > player_point.y) {
					direction = DIRECTION.UP_RIGHT;
				} else {
					direction = DIRECTION.DOWN_RIGHT;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param c
	 *            投げたキャラクター
	 */
	public boolean effect() {
		// SE.EIRIN_SP.play();
		final Base_Artifact a = itemSelect();
		final Base_Creature c = this;
		flag_throwing = true;
		startAttack(new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				a.check();
				Message.set(getColoredName(), "は", a.getColoredName(), "を投げた");
				a.itemThrow(c);
			}
		});
		return true;
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Base_Artifact a = itemSelect();
		a.itemThrow(Player.me);
		return true;
	}

	@Override
	public Image getATKImage(int LV, DIRECTION attacking_direction,
			int attack_No) {
		if (flag_throwing) {
			return IM.getSPImage(LV, attacking_direction, attack_No);
		} else {
			return IM.getATKImage(LV, attacking_direction, attack_No);
		}
	}

	@Override
	protected Base_Artifact getDropItem() {
		return itemSelect();
	}

	@Override
	public boolean isResistantToGrassWave() {
		return !conditionCheck(CONDITION.封印) && true;
	}

	private Base_Artifact itemSelect() {
		if (LV == 1) {
			return new 毒草(getMassPoint());
		} else if (LV == 2) {
			if (Player.me.getConditionList().contains(CONDITION.混乱)) {
				return new 毒草(getMassPoint());
			}
			return new 混乱草(getMassPoint());
		} else {
			if (LV == 4) {
				List<Base_Creature> list = MapInSelect.getListStraightHitCreature(direction, getMassPoint(), 10);
				if (!list.isEmpty()) {
					Base_Creature aimed_creature = list.get(0);
					for (DIRECTION d : DIRECTION.values()) {
						Base_Creature surrounded_creature = MapList
								.getCreature(d.getFrontPoint(aimed_creature
										.getMassPoint().getLocation()));
						if (surrounded_creature != null
								&& !(surrounded_creature instanceof Player)
								&& !surrounded_creature.isPlayerSide()) {
							if (d == DIRECTION.NEUTRAL
									|| !surrounded_creature
											.isResistantToGrassWave()) {
								return new 胃拡張の種(mass_point);
							}
						}
					}
				}
			}
			if (Player.me.getConditionList().contains(CONDITION.睡眠)) {
				if (Player.me.getConditionList().contains(CONDITION.混乱)) {
					return new 毒草(getMassPoint());
				} else {
					return new 混乱草(getMassPoint());
				}
			}
			return new 睡眠草(getMassPoint());
		}
	}

	private boolean railgun() {
		if (!player_is_in_sight) {
			return false;
		}
		effect();
		return true;
	}

	protected boolean specialAction() {
		if (railgun()) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean specialAttack() {
		if (special_ok) {
			if (railgun()) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean specialCheck() {
		if (!check()) {
			return false;
		}
		if (isSpecialParcent()) {
			return true;
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
