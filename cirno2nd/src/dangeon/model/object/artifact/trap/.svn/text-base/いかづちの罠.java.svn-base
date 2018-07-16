package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.util.ThunderDamage;

public class いかづちの罠 extends Base_Trap {
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
	protected static final String name = "いかづちの罠";

	public いかづちの罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_8;
		sim = ENCHANT_SIMBOL.バネ;
	}

	@Override
	protected Base_Artifact action(Base_Artifact a) {
		flag_demand_waiting = false;
		ThunderDamage.thunderDamage(this, null, a.getMassPoint(), 40);
		return a;
	}

	@Override
	protected void action(Base_Creature c) {
		flag_demand_waiting = false;
		ThunderDamage.thunderDamage(this, null, c, 40);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "踏むと、電気が奔るぞ";
	}
}
