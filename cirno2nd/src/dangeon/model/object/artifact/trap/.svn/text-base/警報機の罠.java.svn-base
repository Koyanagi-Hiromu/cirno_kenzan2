package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;

public class 警報機の罠 extends Base_Trap {
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
	protected static final String name = "警報機の罠";

	public 警報機の罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_14;
		sim = ENCHANT_SIMBOL.ハラ;

	}

	@Override
	protected void action(Base_Creature c) {
		SE.THROW_BOMB.play();
		flag_demand_waiting = false;
		Message.set("フロア中の敵が起きた");
		for (Base_Creature _c : MapList.getListEnemy()) {
			CONDITION.wakeUp(_c);
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "警報機";
	}
}
