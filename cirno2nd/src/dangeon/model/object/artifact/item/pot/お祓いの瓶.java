package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.util.R;
import dangeon.util.STAGE;

public class お祓いの瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public お祓いの瓶(Point p) {
		this(p, 2 + (new R().nextInt(5) + 1) / 2);
	}

	public お祓いの瓶(Point p, int size) {
		super(p, "", size, STAGE.妖々夢);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	protected void potUse() {
		if (A instanceof SpellCard && !A.isPerfectCheked()) {
			A.check("forge");
			this.staticCheck();
		}
		if (A.isCurse()) {
			A.setCurse(false);
			this.staticCheck();
		}
	}
}
