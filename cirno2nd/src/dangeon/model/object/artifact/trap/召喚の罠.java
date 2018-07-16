package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印招;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.util.R;

public class 召喚の罠 extends Base_Trap {
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
	protected static final int DURABILITY = 100;
	/**
	 * 罠の名前
	 */
	public static final int composition = 0;
	protected static final String name = "召喚の罠";

	public 召喚の罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_13;
		sim = ENCHANT_SIMBOL.召還;
	}

	@Override
	protected Base_Artifact action(Base_Artifact a) {
		action(a, false);
		return super.action(a);
	}

	@Override
	protected void action(Base_Creature c) {
		action(c, true);
	}

	private void action(final Base_MapObject c, boolean b) {
		SE.SUMMON.play();
		flag_demand_waiting = false;
		if (印招.effect()) {
			Message.set("しかし召喚は失敗に終わった");
			return;
		}
		final int rnd = (int) (new R().nextDouble() * 2 + 3);
		if (c instanceof Base_Enemy) {
			new Task() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					SE.WARP_INSTANT.play();
					MapList.summonEnemy(c.getMassPoint(), rnd, null);
				}
			}.work_appointment();
		} else {
			SE.WARP_INSTANT.play();
			MapList.summonEnemy(c.getMassPoint(), rnd, null);
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "周囲に敵がたくさん出現する。圧倒的なピンチになるのでアイテムを惜しまずなんとかしよう。常にこの罠を突破・打開できるアイテムを１つは持っておきたい。";
	}
}
