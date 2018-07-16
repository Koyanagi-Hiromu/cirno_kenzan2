package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.double_card.DoubleCard;
import dangeon.model.object.creature.player.Player;

public class カード合成の壷 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public カード合成の壷(Point p) {
		this(p, 2);
	}

	public カード合成の壷(Point p, int size) {
		super(p, "カード合成の壷", size);
	}

	private void doubleCardComposition(Base_Artifact before, Base_Artifact after) {
		if (before instanceof SpellCard) {
			if (after instanceof SpellCard) {
				staticCheck();
				DoubleCard dc = new DoubleCard(Player.me.getMassPoint(),
						(SpellCard) before, (SpellCard) after);
				LIST.remove(before);
				LIST.remove(after);
				MAX--;
				LIST.add(dc);
			}
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "カード使用合成専用アイテム。カードを二枚入れるとダブルカードになるので注意しよう";
	}

	@Override
	protected void potUse() {
		if (LIST.size() >= 2) {
			Base_Artifact before = LIST.get(LIST.size() - 2);
			doubleCardComposition(before, A);
		}
	}

}
