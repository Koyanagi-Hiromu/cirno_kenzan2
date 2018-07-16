package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;

public class 回転盤の罠 extends Base_Trap {
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
	protected static final int DURABILITY = 5;
	/**
	 * 罠の名前
	 */
	public static final int composition = 0;
	protected static final String name = "回転盤の罠";

	public 回転盤の罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_5;
		sim = ENCHANT_SIMBOL.回転;
	}

	@Override
	protected void action(Base_Creature c) {
		Message.set(c.getColoredName(), "は混乱した");
		flag_demand_waiting = false;
		SE.STATUS_PIYOPIYO.play();
		c.setCondition(CONDITION.混乱, 0);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "踏むと床が高速回転して目が回る。混乱状態になったらむやみに歩かないこと。でたらめに歩くとまたこの罠を踏みかねない。見えている罠にはかかりにくいけどね。";
	}
}
