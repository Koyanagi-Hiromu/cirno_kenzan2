package dangeon.model.object.creature.player.class_job.bonus.spec;

import java.awt.Point;

import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.model.map.MapList;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.人形;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.Class人形使い;
import dangeon.model.object.creature.player.class_job.bonus.BonusSpecialAction;
import dangeon.util.Damage;
import dangeon.view.anime.ExplosionEffect;
import dangeon.view.detail.MainMap;
import main.util.DIRECTION;

public class Bonus人形使い extends BonusSpecialAction {
	private static final long serialVersionUID = 1L;

	public Bonus人形使い(Class人形使い clazz) {
		super(clazz, 2, 0, "爆発する人形を投げる");
	}

	@Override
	public ActionCheck excuteSpecAction() {
		Player.me.startAttackWithHand(new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				final Point p = Player.me.getMassPoint().getLocation();
				DIRECTION d = Player.me.getDirection();
				final 人形 c = new 人形(p, 1, false).setDollDirection(d);
				MapList.addEnemy(c, true);
				c.changeSize(-100);
				d.getFrontPoint(p);
				d.getFrontPoint(p);
				d.getFrontPoint(p);
				c.setMassPoint_ParabolaJumpAttack(p, new Task() {
					private static final long serialVersionUID = 1L;
					@Override
					public void work() {
						TaskOnMapObject.addEnemyRemoveTask(c);
						MainMap.addEffect(new ExplosionEffect(p, null));
						for (DIRECTION d : DIRECTION.values()) {
							Base_Creature passive = MapList.getCreature(d.getFrontPoint(p
									.getLocation()));
							int damage = 40;
							int DAMAGE;
							if (passive != null) {
								if (passive instanceof Player || passive == c) {
									continue;
								}
								DAMAGE = passive.damagedWithFire(damage);
								Damage.damage(this, null, "爆発に巻き込まれて倒れた", Player.me, passive, DAMAGE);
							}
						}

					}
				});
			}
		});

		{
			return ActionCheck.SUCCESS;
		}
		
//		final 人形 e = getExistedAlreadyDoll();
//		if (e != null && !BonusConductor.人形使い_呼び寄せ()) {
//			Message.set("人形は同時に１体までしか作れない");
//			return ActionCheck.FAILURE;
//		} else if (e == null && MapList.isEnemyMax()) {
//			Message.set("マップにエネミーがいすぎてうまく作れない");
//			return ActionCheck.FAILURE;
//		} else {
//			final Point p = Player.me.getMassPoint().getLocation();
//			Player.me.getDirection().getFrontPoint(p);
//			if (MapList.getCreature(p) != null) {
//				Message.set("目の前に邪魔なものがあって特技がうまく使えない");
//				return ActionCheck.FAILURE;
//			} else if (!MassCreater.getMass(p).WALKABLE) {
//				Message.set("目の前に邪魔なものがあって特技がうまく使えない");
//				return ActionCheck.FAILURE;
//			} else {
//				if (e != null && BonusConductor.人形使い_呼び寄せ()) {
//					Message.set(e.getColoredName(), "を呼び出した");
//					e.setDollDirection(Player.me.direction);
//					e.setMassPoint_Jump(p);
//				} else {
//					Message.set("人形を作り出した！");
//					MainMap.addEffect(new DoronEffect(p, new Task() {
//						private static final long serialVersionUID = 1L;
//
//						@Override
//						public void work() {
//						}
//
//						@Override
//						protected void work(int frame) {
//							if (frame == 4) {
//								MapList.addEnemy(new 人形(p, 1, true)
//										.setDollDirection(
//												Player.me.getDirection())
//										.setConditionList(
//												CONDITION.死,
//												BonusConductor.人形使い_寿命低下() ? 25
//														: 50));
//							}
//						}
//					}), true);
//				}
//				return ActionCheck.SUCCESS;
//
//			}
//		}
	}

	private 人形 getExistedAlreadyDoll() {
		for (Base_Enemy e : MapList.getListEnemy()) {
			if (e instanceof 人形) {
				if (((人形) e).isMadeByDollMaster()) {
					return (人形) e;
				}
			}
		}
		return null;
	}
}
