package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.Base_Creature;
import dangeon.util.MapInSelect;

public class 引き寄せの杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "引き寄せの杖";
	public static final String item_name_sound = "ひきよせのつえ";

	public 引き寄せの杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(final Base_Creature c) {
		DIRECTION direction = DIRECTION.getDirection(
				used_creature.getMassPoint(), c.getMassPoint()).getReverse();
		if (direction == DIRECTION.NEUTRAL) {
			// 反射された
			return;
		}
		MapInSelect.吹き飛ばし(used_creature, "吹き飛ばし", c, direction, 10, 0);
		Message.set(used_creature.getColoredName(), "は", c.getColoredName(),
				"を引き寄せた");
		//
		// Point p = used_creature.getMassPoint().getLocation();
		// p.translate(direction.X, direction.Y);
		// c.startDamaging();
		// c.setMassPoint_WalkLike(p, 3, new Task() {
		//
		// /**
		// *
		// */
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void work() {
		// c.endDamaging();
		// }
		// });
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "何でも引き寄せるので注意しよう";
	}

	@Override
	public boolean hitWall(final Base_Creature used_creature) {
		staticCheck();
		used_creature.startDamaging();
		Point p = used_creature.getMassPoint().getLocation();
		DIRECTION d = used_creature.getDirection();
		while (MassCreater.getMass(p).WALKABLE) {
			p.translate(d.X, d.Y);
		}
		do {
			p.translate(-d.X, -d.Y);
		} while (MapList.isCreature(p)
				&& !p.equals(used_creature.getMassPoint()));
		Message.set(used_creature.getColoredName(), "は壁に引き寄せられた");
		used_creature.setMassPoint_WalkLike(p, 3, new Task() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				used_creature.endDamaging();
			}
		});
		return true;
	}

	@Override
	public boolean isMagicHitToItem() {
		return true;
	}

	@Override
	public void itemHitCheck(boolean ento, final Base_Creature c,
			final Base_Artifact a) {
		staticCheck();
		Message.set(c.getColoredName(), "は", a.getColoredName(), "を引き寄せた");
		new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				DIRECTION direction = DIRECTION.getDirection(c.getMassPoint(),
						a.getMassPoint());
				a.direction = direction.getReverse();
				int dx = c.getMassPoint().x - a.getMassPoint().x;
				int dy = c.getMassPoint().y - a.getMassPoint().y;
				int range = Math.max(Math.abs(dx), Math.abs(dy)) - 1;
				if (range > 10)
					range = 10;
				a.itemThrow(c, HowToThrow.NORMAL, false, range, true);
			}
		}.work_appointment();
	}

}