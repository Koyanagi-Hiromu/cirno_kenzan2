package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.SelectItem;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.util.R;
import dangeon.util.STAGE;

public class 聖瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 聖瓶(Point p) {
		this(p, 2 + (new R().nextInt(5) + 1) / 2);
	}

	public 聖瓶(Point p, int size) {
		super(p, "", size, STAGE.星蓮船);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	protected void potUse() {
		staticCheck();
		if (A instanceof SelectItem) {
			if (A instanceof Base_Item)
				Checker.select(A);
		} else if (A.isCurse()) {
			// カードの呪いアイコン表示
			A.setCurse(true);
		} else {
			Message.set(A.getColoredName(), "を使用した");
			if (!(A instanceof Staff)) {
				if (A.staticCheck()) {
					A.check();
				}
			}
			A.itemUse();
		}
	}
}
