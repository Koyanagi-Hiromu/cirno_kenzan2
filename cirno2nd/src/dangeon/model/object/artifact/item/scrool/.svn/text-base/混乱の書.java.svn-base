package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 混乱の書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "混乱の書";
	public static final String item_name_sound = "こんらんのしょ";

	public 混乱の書(Point p) {
		super(p, item_name);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "部屋内の敵が２０ターンの間混乱する。混乱状態の敵はふらふらと移動する。攻撃もすることがあるが特殊能力は使用してこない。緊急回避手段としてはトップレベル。";
	}

	@Override
	public void scroolUse() {
		SE.STATUS_PIYOPIYO.play();
		MapInSelect.roomOrRoadInEnemyChengeCondition(Player.me.getMassPoint(),
				CONDITION.混乱, 0);
	}
}
