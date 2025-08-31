package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.player.Player;
import dangeon.util.STAGE;
import dangeon.view.detail.Header;

public class かき氷器 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public かき氷器(Point p) {
		this(p, 2);
	}

	public かき氷器(Point p, int size) {
		super(p, "かき氷器", size, STAGE.妖々夢);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	protected void potUse() {
		if (A.isFrozen()) {
			staticCheck();
			LIST.remove(A);
			Message.set("かき氷を食べた");
			Header.setEatIce();
			CONDITION.conditionAllClear(Player.me, true);
			Player.me.chengeHP(this, null, 999);
			MAX--;
		}
	}
}
