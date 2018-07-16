package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.Composition;
import dangeon.model.object.artifact.item.enchantSpecial.SpecialCard;
import dangeon.util.R;
import dangeon.util.STAGE;

public class 合成の瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 合成の瓶(Point p) {
		this(p, 2 + new R().nextInt(3));
	}

	public 合成の瓶(Point p, int size) {
		super(p, "魔理沙の魔法瓶", size, STAGE.永夜抄, STAGE.花映塚);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】@数字の数だけ瓶以外のアイテムを収納できるぜ。中身は投げ割って取り出すんだぜ。@@【合成のルール】@カードを先に入れると後に入れたアイテムの印がそのカードに足されるぜ。既にある印は無効だぜ。同じ杖を入れた場合は使用回数が足されるぜ。@@【ヒント】@合成された杖は識別されるし杖を優先して合成するのもありだぜ。おにぎりを入れて保管するのもありだぜ。";
	}

	@Override
	protected void potUse() {
		if (LIST.size() >= 2) {
			Base_Artifact before = LIST.get(LIST.size() - 2);
			if (Composition.setComposition(before, A)) {
				staticCheck();

				// 合成に成功したら=>今入れたものを破棄して容量を減らす
				SpecialCard sc = Composition.isSpecialCard(before);
				System.out.println("scc");
				if (sc != null) {
					System.out.println("scc");
					LIST.remove(before);
					LIST.add(sc.getThisCard());
				}
				LIST.remove(A);
				MAX--;
			}
		}
	}
}
