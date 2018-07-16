package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;

public class ねむりの罠 extends Base_Trap {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected static final int PARCENTAGE = 80;
	protected static final int DURABILITY = 10;
	protected static final String name = "ねむりの罠";

	public ねむりの罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_4;
		sim = ENCHANT_SIMBOL.ねむり;

	}

	@Override
	protected void action(Base_Creature c) {
		flag_demand_waiting = false;
		SE.STATUS_SLEEP.play();
		Message.set(c.getColoredName(), "は眠ってしまった");
		c.setCondition(CONDITION.睡眠, 0);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "睡眠ガスが勢いよく噴き出す。眠ると完全に無防備になるので大ピンチ。しかし少女には効果が薄いらしく本来７ターンのところ３ターンしか眠らない。";
	}

}
