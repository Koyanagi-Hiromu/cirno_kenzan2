package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.util.R;
import dangeon.util.STAGE;

public class グリモワールの瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public グリモワールの瓶(Point p) {
		this(p, 1 + new R().nextInt(5) / 2);
	}

	public グリモワールの瓶(Point p, int size) {
		super(p, "", size, STAGE.紅魔郷);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	protected void potUse() {
		if (A instanceof SpellCard) {
			SpellCard sc = (SpellCard) A;
			if (sc.getMaxBombs() == sc.getBombs()) {
			} else {
				sc.addBomb(1);
				this.staticCheck();
			}
		} else if (A instanceof Staff) {
			Staff s = (Staff) A;
			if (!s.isPerfectCheked()) {
				s.check();
				this.staticCheck();
			}
			if (s.getStaffRest() < s.MAX_STAFF_REST) {
				s.addStaffRest(2);
				this.staticCheck();
			}
		}
	}
}
