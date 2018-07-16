package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.table.ItemDetail;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.artifact.item.scrool.ワイルドカード;
import dangeon.util.R;
import dangeon.util.STAGE;

public class 小鈴の瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 小鈴の瓶(Point p) {
		this(p, 1 + new R().nextInt(3) + new R().nextInt(2));
	}

	public 小鈴の瓶(Point p, int size) {
		super(p, "", size, STAGE.外伝);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	protected void potUse() {
		staticCheck();
		if (!A.isPerfectCheked()) {
			A.check();
			SE.CHECK.play();
			Message.set("「ほうほう！@」");
			Message.set("「これは", A.getColoredName(), "よ」@");
			Message.set("「出現度は〈", ItemTable.getRank_String(A), "〉ね！」@");
		} else {
			Message.set("「う～ん・・・」@");
			Message.set("「", A.getColoredName(), "の出現度は〈",
					ItemTable.getRank_String(A), "〉みたい！」@");
		}
		if (A instanceof Scrool) {
			LIST.remove(A);
			int old_rank = ItemTable.getRank(A);
			int i = 1000;
			Base_Artifact a = null;
			while (i-- > 0) {
				a = ItemTable.itemReturn(getMassPoint(),
						ItemDetail.getCategory(A));
				if (a == null) {
					break;
				} else if (ItemTable.getRank(a) < old_rank) {
					continue;
				} else {
					break;
				}
			}
			if (a == null) {
				a = new ワイルドカード(getMassPoint());
			}
			A = a;
			if (A instanceof Scrool) {
				A.staticCheck();
				LIST.add(A);
				Message.set("それなら", A.getColoredName(), "〈",
						ItemTable.getRank_String(A), "〉と交換させて戴くわ！@");
			} else {
				Message.set("でもその場所じゃアイテムが出現しないから交換はできないわね@");
			}
		}
	}
}
