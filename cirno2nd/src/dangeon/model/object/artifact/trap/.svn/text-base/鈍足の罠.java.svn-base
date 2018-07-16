package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;

public class 鈍足の罠 extends Base_Trap {
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
	protected static final int DURABILITY = 50;
	/**
	 * 罠の名前
	 */
	public static final int composition = 0;
	protected static final String name = "鈍足の罠";

	public 鈍足の罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_11;
		sim = ENCHANT_SIMBOL.鈍足;
	}

	@Override
	protected void action(Base_Creature c) {
		flag_demand_waiting = false;
		Message.set(c.getColoredName(), "の足どりが重くなった");
		SE.STATUS_SLOW.play();
		c.setCondition(CONDITION.鈍足, 0);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "踏むと足元から気力が吸い取られて何をするにも面倒になってしまう。結果鈍足状態になり２ターンに１度しか動けなくなる。薬草やすばやさ草を飲むのも手。";
	}
}
