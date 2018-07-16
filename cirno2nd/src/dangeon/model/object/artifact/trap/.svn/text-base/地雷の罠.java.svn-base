package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.util.MapInSelect;

public class 地雷の罠 extends Base_Trap {
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
	protected static final String name = "地雷の罠";

	public 地雷の罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_7;
		sim = ENCHANT_SIMBOL.地雷の罠;
	}

	private void action() {
		flag_demand_waiting = false;
		MapInSelect.explosion(getMassPoint());
	}

	@Override
	protected Base_Artifact action(Base_Artifact a) {
		action();
		MapList.removeArtifact(a);
		return null;
	}

	@Override
	protected void action(Base_Creature c) {
		action();
	}

	@Override
	protected String getSecondExplain_ByEach() {
		// TODO 自動生成されたメソッド・スタブ
		return "周囲を巻き込んで爆発が起こる。チルノはＨＰが半分になり敵は一撃で倒れる。ピンチの時は自分から踏もう。ＨＰが２以上あればこの罠で倒れることはない。";
	}
}
