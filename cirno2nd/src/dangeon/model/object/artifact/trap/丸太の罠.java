package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import main.util.DIRECTION;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.controller.task.Task;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.bullet.アンカー;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.村紗水蜜;

public class 丸太の罠 extends Base_Trap {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final int composition = 2;
	private int MAX = 10;
	protected static final int PARCENTAGE = 80;
	protected static final int durability = 5;
	private int trap_damage = 5;
	public static final String name = "アンカーの罠";

	public 丸太の罠(Point p) {
		super(p, name, PARCENTAGE, durability);
		IM = Image_Artifact.trap1_6;
		sim = ENCHANT_SIMBOL.丸太;
	}

	@Override
	protected Base_Artifact action(final Base_Artifact a) {
		action(a, false);
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
		Base_Enemy en = new 村紗水蜜(p, 0);
		en.setDirection(d.getReverse());
		new アンカー(en).itemThrow(en, HowToThrow.MAGIC,
				Math.max(MassCreater.WIDTH, MassCreater.HEIGHT));
	}

	@Override
	protected String getSecondExplain_ByEach() {
		// TODO 自動生成されたメソッド・スタブ
		return "踏むとチルノの右側から必中のぶっとびアンカーが飛んでくる。ぶっとびアンカーに当たると１０マスぶっとぶ。移動手段として自分から踏むこともあるかもしれない。";
	}

	/**
	 * 足元を見た時の説明文
	 * 
	 * @return
	 */
	protected String[] underfoot_explanation() {
		// TODO とりあえず。ここに書いておく
		String under_explanation[] = new String[] {
				getColoredName().concat("。"), "アンカーが飛んできて、痛い" };
		return under_explanation;
	}
}
