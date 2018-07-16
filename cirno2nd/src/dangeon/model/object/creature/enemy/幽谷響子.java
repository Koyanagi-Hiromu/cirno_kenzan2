package dangeon.model.object.creature.enemy;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import main.util.DIRECTION;
import dangeon.controller.ThrowingItem;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.毒ナイフ;
import dangeon.model.object.creature.Base_Creature;
import dangeon.util.MapInSelect;
import dangeon.view.anime.MasterSpark;
import dangeon.view.detail.MainMap;

public class 幽谷響子 extends Base_Enemy {

	// @Override
	// public boolean counterAttackEnemy() {
	// return true;
	// }
	private static final long serialVersionUID = 1L;

	// private Base_Artifact copy;

	public 幽谷響子(Point p, int Lv) {
		super(p, Lv);
	}

	@Override
	public Image getImage() {
		if (isAttacking()) {
			return getATKImage(LV, attacking_direction, attack_No);
		} else if (flag_damaging) {
			return IM.getDamImage(LV, direction);
		}
		return IM.getWalkImage(LV, direction, getMoveAnimationSpeed());
	}

	@Override
	protected boolean itemHitEffect(Base_Artifact a, boolean ento) {
		return true;
	}

	protected void nockBack() {
		Point p = getMassPoint().getLocation();
		DIRECTION d = direction;
		ArrayList<Base_Creature> list = new ArrayList<Base_Creature>();
		for (int i = 0; i < MassCreater.WIDTH; i++) {
			p.translate(d.X, d.Y);
			Base_Creature c = MapList.getCreature(p);
			if (c != null) {
				list.add(c);
			}
		}
		Collections.reverse(list);
		for (Base_Creature c : list) {
			MapInSelect.吹き飛ばし(this, null, c, direction, 99, 5);
		}
	}

	@Override
	public boolean reflection(final ThrowingItem ti) {
		if (ti.HOW == HowToThrow.MAGIC) {
			return super.reflection(ti);
		}
		direction = ti.A.direction.getReverse();
		if (LV >= 3) {
			MainMap.addEffect(new MasterSpark(this), true);
			setAnimation(new MasterSpark(this, new Task() {
				/**
			 *
			 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					nockBack();
				}
			}));
			Point p = getMassPoint().getLocation();
			DIRECTION d = direction;
			for (int i = 0; i < MassCreater.WIDTH; i++) {
				p.translate(d.X, d.Y);
				MassCreater.getMass(p).setDigged(true);
			}
			MassCreater.retakeMassSet();
			// nockBack();
		}
		attaking_frame = 4;
		if (!ti.ENTO)
			ti.ENTO = LV > 1;
		ti.DIGG = LV > 2;
		final String s = getColoredName();
		startAttack(new Task() {
			/**
						 *
						 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Message.set(s, "は元気よく投擲物を跳ね返した");
			}
		});
		if (LV == 1) {
			if (毒ナイフ.class.isInstance(ti.A)) {
				if (十六夜咲夜.class.isInstance(ti.C)) {
					if (ti.C.getLV() == 0) {
						Medal.アイテムヤッホーにワナの毒ナイフを当てた.addCount();
					}
				}
			}
		}
		return true;
	}

	@Override
	protected boolean specialCheck() {
		return false;
	}
}
