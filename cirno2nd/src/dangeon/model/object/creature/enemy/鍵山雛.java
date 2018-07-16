package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DecurseEffect;
import dangeon.view.anime.Special_Wait_Effect;
import dangeon.view.util.WithinOutofScreen;

public class 鍵山雛 extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private boolean power_up = false;

	private boolean flag = false;

	public 鍵山雛(Point p, int Lv) {
		super(p, Lv);
	}

	private void condition(Base_Creature c) {
		CONDITION.conditionAllClear(c);
		if (LV == 4) {
			c.chengeHP(null, null, getMAX_HP());
		}
		flag = true;
	}

	private boolean effect(boolean attack) {
		List<Base_Creature> list = new ArrayList<Base_Creature>();
		list = MapList.getListAroundCreature(this.getMassPoint());
		if (LV == 4) {
			list.add(this);
		}
		if (list.isEmpty()) {
			return false;
		}
		for (Base_Creature c : list) {
			if (c.equals(Player.me)) {
				if (LV == 1) {
					boolean decurse = false;
					for (Base_Artifact a : Enchant.getEnchantList()) {
						if (a.isCurse()) {
							if (attack) {
								a.setCurse(false);
								flag = true;
								decurse = true;
							} else {
								return true;
							}
						}
					}
					if (decurse) {
						Player.me.setAnimation(new DecurseEffect());
						Medal.雛に呪いを吸い取ってもらった.addCount();
					}
				}
			}
			if (!c.getConditionList().isEmpty()) {
				if (attack) {
					condition(c);
				} else {
					return true;
				}
			}

		}
		if (!flag) {
			return false;
		}
		flag = true;
		if (WithinOutofScreen.isOutside(this)) {
			SE.STATUS_POWER_UP.play();
			Message.set(getColoredName(), "は周りの厄を吸い取ってちからの最大値に変換した");
			powerUP();
			chengeHP(null, null, getMAX_HP());
		} else {
			setAnimation(new Special_Wait_Effect(6, 3, SE.WARP_INSTANT, 1,
					new Task() {
						/**
				 *
				 */
						private static final long serialVersionUID = 1L;

						@Override
						public void work() {
							Message.set(getColoredName(), "は周りの厄を吸い取ってパワーアップした");
							powerUP();
							chengeHP(null, null, getMAX_HP());
						}
					}));
		}
		if (LV <= 2)
			power_up = true;
		return true;
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		if (!Player.me.getConditionList().isEmpty()) {
			Message.set("チルノの状態異常を吸い取った");
			CONDITION.conditionAllClear(Player.me);
			Player.me.setCondition(CONDITION.パワーアップ, 0);
		} else {
			Message.set("しかし、何も起らなかった");
		}
		return true;
	}

	@Override
	protected CONDITION getInitCondition() {
		power_up = false;
		return super.getInitCondition();
	}

	private boolean level() {
		if (isSpecialParcent()) {
			List<Base_Creature> list = new ArrayList<Base_Creature>();
			list = MapList.getListAroundCreature(this.getMassPoint());
			if (LV == 4)
				list.add(this);
			if (list.isEmpty()) {
				return false;
			}
			for (Base_Creature c : list) {
				if (!c.getConditionList().isEmpty()) {
					if (c.getConditionList().size() == 1) {
						if (c.getConditionList().contains(CONDITION.仮眠)
								|| c.getConditionList()
										.contains(CONDITION.特殊仮眠)) {
							continue;
						}
					}
					return true;
				}
				if (c.equals(Player.me)) {
					if (LV == 1) {
						for (Base_Artifact a : Enchant.getEnchantList()) {
							if (a.isCurse()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	protected boolean specialAttack() {
		if (!power_up) {
			effect(true);
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		if (power_up) {
			return false;
		}
		if (level()) {
			if (!effect(false)) {
				return false;
			}
			return true;
		}
		return false;
	}

}
