package dangeon.model.object.creature.enemy;

import java.awt.Point;
import java.util.List;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.bullet.ドラゴンブレス;
import dangeon.model.object.artifact.item.bullet.目からビーム;
import dangeon.model.object.artifact.item.food.Food;
import dangeon.model.object.artifact.item.staff.MagicBullet;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;

public class ルーミア extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	private int eat_times;

	public ルーミア(Point p, int Lv) {
		super(p, Lv);
	}

	protected void darkRoom() {
		List<Base_Creature> list = MapInSelect
				.getListRoomOrRoadInCreature(this);
		if (list == null)
			return;
		for (Base_Creature c : list) {
			c.setCondition(CONDITION.やりすごし, 0);
		}
		if (MassCreater.isPlayerInTheSameRoom(getMassPoint())) {
			SE.STATUS_GOOD.play();
			Player.me.setCondition(CONDITION.やりすごし, 0);
		}
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		Player.me.setCondition(CONDITION.やりすごし, 0);
		return true;
	}

	@Override
	protected CONDITION getInitCondition() {
		if (getMassPoint().x == 0 && getMassPoint().y == 0)
			return null;
		if (LV == 4) {
			eat_times = 99;
			darkRoom();
		} else if (LV == 3) {
			eat_times = new R().nextInt(2) + 2;
		} else {
			eat_times = new R().nextInt(LV) + 1;
		}
		return CONDITION.やりすごし;
	}

	@Override
	public boolean itemHitEffect(final Base_Artifact a, boolean ento) {
		if (a instanceof MagicBullet || a instanceof 目からビーム) {
			return true;
		}
		if (ento || a instanceof ドラゴンブレス || --eat_times < 0) {
			return true;
		}
		SE.ATTACK_EAT.play();
		direction = a.direction.getReverse();
		attaking_frame = 4;
		final Base_Creature THIS = this;
		startAttack(new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				if (THIS.getLV() < 3 && a instanceof Food) {
					Medal.ルーミアにおにぎりを投げつけた.addCount();
					THIS.addLV(1);
				} else {
					boolean flag_power_up = false;
					if (THIS.getLV() == 2) {
						if (eat_times == 0) {
							flag_power_up = true;
						}
					} else if (THIS.getLV() == 3) {
						if (eat_times == 1 || eat_times == 2) {
							Message.set(getColoredName(), "はアイテムを食べた");
							Message.set("部屋が闇に包まれた");
							darkRoom();
							return;
						} else if (eat_times == 0) {
							// flag_power_up = true;
						}
					} else if (THIS.getLV() == 4) {
						flag_power_up = true;
					}
					if (flag_power_up) {
						Message.set(getColoredName(), "はアイテムを食べてパワーアップした");
						powerUP();
					} else {
						Message.set(getColoredName(), "はアイテムを食べた");
					}
					if (eat_times == 0) {
						Message.set(getColoredName(), "はおなかいっぱいになった");
					}
				}
			}
		});
		return false;
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}

}
