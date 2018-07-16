package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.util.R;
import dangeon.util.STAGE;

public class 妹紅の瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 妹紅の瓶(Point p) {
		this(p, 2 + new R().nextInt(3));
	}

	public 妹紅の瓶(Point p, int size) {
		super(p, "", size, STAGE.永夜抄);
	}

	@Override
	public void freezeCountPlus(int count) {
		for (Base_Artifact a : LIST) {
			int step = a.getFreeseStep();
			a.setFreezeCount(a.getFreezeCount() - 10);
			if (step != a.getFreeseStep())
				staticCheck();
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	protected void potUse() {
	}
}
