package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;

public class バネの罠 extends Base_Trap {
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
	protected static final String name = "バネの罠";

	public バネの罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_8;
		sim = ENCHANT_SIMBOL.バネ;
	}

	@Override
	protected Base_Artifact action(Base_Artifact a) {
		a.setMassPoint_Jump(MassCreater.getItemIP());
		return null;
	}

	@Override
	protected void action(Base_Creature c) {
		flag_demand_waiting = false;
		Message.set(c.getColoredName(), "は跳ね飛ばされた");
		c.setMassPoint_Jump(MassCreater.getWarpPoint(null));
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "どこかの部屋にワープする。運が悪ければモンスターハウスに飛ぶこともあるが普段はあまり危険度は高くない。ダメージも受けないので移動手段としてどうぞ。";
	}
}
