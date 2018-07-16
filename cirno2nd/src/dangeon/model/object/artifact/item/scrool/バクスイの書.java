package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.action.message.Message;
import dangeon.util.R;

public class バクスイの書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "バクスイの書";
	public static final String item_name_sound = "はくすいのしょ";

	public バクスイの書(Point p) {
		super(p, item_name);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "部屋内の敵が１５ターンの間ぐっすり寝る。と同時に移動速度が１段階上がる。つまり起きた敵は倍速で襲ってくるので寝てる間になんとかしよう。";
	}

	@Override
	public void scroolUse() {
		Message.set("時の法則が乱れる");
		int time = new R().is(50) ? 5 : 9;
		TurnSystemController.setTimeStop(time);
	}
}
