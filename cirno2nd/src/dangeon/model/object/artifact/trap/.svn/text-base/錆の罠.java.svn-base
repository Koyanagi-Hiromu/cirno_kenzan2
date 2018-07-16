package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.RainEffect;
import dangeon.view.detail.MainMap;

public class 錆の罠 extends Base_Trap {
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
	protected static final int DURABILITY = 30;
	/**
	 * 罠の名前
	 */
	public static final int composition = 0;
	protected static final String name = "ケロケロの罠";

	final boolean flag_tewi;

	public 錆の罠(Point p) {
		this(p, false);
	}

	public 錆の罠(Point p, boolean b) {
		super(p, name, PARCENTAGE, b ? 100 : DURABILITY);
		IM = Image_Artifact.trap1_10;
		sim = ENCHANT_SIMBOL.錆の罠;
		flag_tewi = b;
		if (flag_tewi)
			super.name = "てゐ印の" + super.name;
	}

	@Override
	public Base_Artifact action(Base_Artifact a) {
		MainMap.addEffect(RainEffect.work(a), true);
		return null;
	}

	@Override
	protected void action(final Base_Creature c) {
		flag_demand_waiting = false;
		if (c instanceof Player) {
			MainMap.addEffect(RainEffect.work(flag_tewi ? -10 : -1), true);
		} else {
			MainMap.addEffect(new RainEffect(c, true, new Task() {
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					if (c.conditionCheck(CONDITION.炎上)) {
						c.setConditionRemoveTask(CONDITION.炎上);
					}
					Message.set(c.getColoredName(), "の防御力が０になった");
				}
			}), true);
		}
		return;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "雨が降り装備しているカードが濡れてしまい強さが１下がる。強さを表す数字（修正値）がマイナスになっても使用することはできる。";
	}
}
