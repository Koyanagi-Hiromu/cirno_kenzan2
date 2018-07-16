package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.object.artifact.item.scrool.ワイルドカード;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.魅魔のカード;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

public class 印の瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final static int MAX = 9;

	public 印の瓶(Point p) {
		super(p, "魅魔の魔法瓶", new R().nextInt(4) / 3 + 1);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】@器っていうのはな印が入る数のことさ。@つまりこの瓶に入れたカードは普段より１つ多くの印が持てるわけね。@まあ適当に使うといいんじゃないかい？@そんな珍しいものでもないからね（うそ）@@器は最大で９までだよ";
	}

	@Override
	protected void potUse() {
		if (A instanceof SpellCard) {
			if (A.composition_number < MAX) {
				A.composition_number++;
			}
		} else if (A instanceof ワイルドカード) {
			LIST.remove(A);
			魅魔のカード c = new 魅魔のカード(Player.me.getMassPoint());
			c.setForgeValue(-99);
			Medal.魅魔のカードを作った.addCount();
			LIST.add(c);
		} else {
			return;
		}
		staticCheck();

	}
}
