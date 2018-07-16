package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import main.util.DIRECTION;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.毒ナイフ;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.十六夜咲夜;

public class 毒の矢の罠 extends Base_Trap {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 罠が発動する確立
	 */
	protected static final int PARCENTAGE = 80;
	/**
	 * 罠の壊れ安さ
	 */
	protected static final int DURABILITY = 20;
	/**
	 * 罠の名前
	 */
	public static final int composition = 0;
	protected static final String name = "毒ナイフの罠";

	public 毒の矢の罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_12;
		sim = ENCHANT_SIMBOL.毒の矢;
	}

	@Override
	protected Base_Artifact action(final Base_Artifact a) {
		action(a, false);
		Medal.アイテムを投げて毒ナイフを起動させた.addCount();
		new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;
			private boolean flag_end;

			@Override
			public boolean isDemandToContinue() {
				return !flag_end;
			}

			@Override
			public void work() {
				if (TaskOnMapObject.isThrowTaskEmpty()) {
					for (Base_Artifact a : MapList.getListArtifact()) {
						if (a.isAnimating()) {
							return;
						}
					}
					flag_end = true;
					MapList.removeArtifact(a);
					ItemFall.flag_trap_check = false;
					ItemFall.itemFall(a);
				}
			}
		}.work_appointment();
		return null;
	}

	@Override
	protected void action(Base_Creature c) {
		action(c, true);
	}

	private void action(Base_MapObject o, boolean b) {
		flag_demand_waiting = false;
		Point p = o.getMassPoint().getLocation();
		DIRECTION d = o.getDirection().getNeiboringDirection()
				.getNeiboringDirection();
		while (MassCreater.getMass(p).WALKABLE) {
			p.translate(d.X, d.Y);
		}
		Base_Enemy en = new 十六夜咲夜(p, 0);
		en.setDirection(d.getReverse());
		new 毒ナイフ(en).itemThrow(en, HowToThrow.NORMAL,
				Math.max(MassCreater.WIDTH, MassCreater.HEIGHT));
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "踏むとチルノの右側から毒ナイフが飛んでくる。当たるとちからが下がるいやな罠。うまく調整して敵に当てると攻撃力がさがるしダメージも入っていい感じ。";
	}
}
