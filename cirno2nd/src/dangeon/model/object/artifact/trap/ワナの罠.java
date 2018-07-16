package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.table.TrapTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;

public class ワナの罠 extends Base_Trap {
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
	protected static final String name = "ワナの罠";

	public ワナの罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_3;
		sim = ENCHANT_SIMBOL.ワナ;
	}

	private void action() {
		flag_demand_waiting = false;
		SE.ZAKUZAKU.play();
		Message.set("フロアに罠が増えた");
		TrapTable.addMapList(10, null);
	}

	@Override
	protected Base_Artifact action(Base_Artifact a) {
		action();
		return super.action(a);
	}

	@Override
	protected void action(Base_Creature c) {
		action();
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "踏むとザクザクと新しい罠が設置される。これを踏んでしまったら多少面倒でも素振りしながら歩くのが無難。目薬草を飲んでいるならわざと踏む価値はある。";
	}
}
