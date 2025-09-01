package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.food.腐ったおにぎり;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.util.STAGE;

public class 変化の瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 変化の瓶(Point p) {
		this(p, 3 + new R().nextInt(2));
	}

	public 変化の瓶(Point p, int size) {
		super(p, "", size, STAGE.風神録);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}
	
	@Override
	protected void onPreUse() {
		if (Enchant.ATK.isEquals(A)) {
			Medal.にとりの魔法瓶に攻撃カードを入れた.addCount();
		}
	}

	@Override
	protected void potUse() {
		LIST.remove(A);
		int i = 1000;
		Base_Artifact a = null;
		while (i-- > 0) {
			a = ItemTable.itemReturn(getMassPoint(), null);
			if (a == null) {
				a = new 腐ったおにぎり(Player.me.getMassPoint());
				break;
			} else if (!isUnableToPutIn(a)) {
				if (A.getClass().equals(a.getClass())) {
					Medal.にとりの魔法瓶で同じアイテムが抽選された.addCount();
				}
				break;
			}
		}
		if (i <= 0) {
			a = new 腐ったおにぎり(Player.me.getMassPoint());
		}

		A = a;
		LIST.add(A);
		staticCheck();

	}
}
