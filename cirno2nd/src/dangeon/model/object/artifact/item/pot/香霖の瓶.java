package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.model.config.table.ItemDetail;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.util.R;
import dangeon.util.STAGE;

public class 香霖の瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 香霖の瓶(Point p) {
		this(p, 1 + new R().nextInt(4));
	}

	public 香霖の瓶(Point p, int size) {
		super(p, "", size, STAGE.外伝);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	protected void potUse() {
		LIST.remove(A);
		int old_rank = ItemTable.getRank(A);
		int i = 1000;
		Base_Artifact a = null;
		ItemDetail cat = ItemDetail.getCategory(A);
		while (i-- > 0) {
			a = ItemTable.itemReturn(getMassPoint(), cat);
			if (a == null) {
				continue;
			} else if (ItemTable.getRank(a) != old_rank) {
				continue;
			} else {
				a.staticCheck();
				break;
			}
		}
		if (a == null) {
			a = A;
		}
		A = a;
		LIST.add(A);
		staticCheck();
	}
}
