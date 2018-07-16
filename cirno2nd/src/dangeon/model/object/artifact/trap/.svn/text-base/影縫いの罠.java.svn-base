package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;

public class 影縫いの罠 extends Base_Trap {
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
	protected static final String name = "影縫いの罠";

	public 影縫いの罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_17;
		sim = ENCHANT_SIMBOL.影縫い;
	}

	@Override
	protected void action(Base_Creature c) {
		flag_demand_waiting = false;
		Message.set(c.getColoredName(), "は移動できなくなった");
		SE.STATUS_SHADOW.play();
		c.setCondition(CONDITION.影縫い, 0);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "踏むと影にクナイが刺さる。クナイが抜けるまで移動できなくなる。攻撃やアイテムの使用は出来るのでかかっても焦らないこと。";
	}
}
