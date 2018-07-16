package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.util.R;
import dangeon.util.STAGE;

public class 雛瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 雛瓶(Point p) {
		this(p, 3 + (new R().nextInt(3) + 1) / 2);
	}

	public 雛瓶(Point p, int size) {
		super(p, "", size, STAGE.風神録);
		setCurse(true);
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
		if (A.compositionSpace()) {
			A.addListComposition(ENCHANT_SIMBOL.金);
			A.changeToGolden();
			this.staticCheck();
		}
	}
}
