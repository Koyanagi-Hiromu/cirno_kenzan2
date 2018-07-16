package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.util.MapInSelect;

public class 小石の罠 extends Base_Trap {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 罠が発動する確立
	 */
	protected static final int PARCENTAGE = 100;
	/**
	 * 罠の壊れ安さ
	 */
	protected static final int DURABILITY = 20;
	/**
	 * 罠の名前
	 */
	public static final int composition = 0;
	protected static final String name = "小石の罠";

	public 小石の罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_14;
		sim = ENCHANT_SIMBOL.ハラ;

	}

	@Override
	protected void action(Base_Creature c) {
		flag_demand_waiting = false;
		MapInSelect.転ばし(this, "転んだ", c, c.getDirection(), 0, 5);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "転ぶぞ";
	}
}
