package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.disc.Base_Disc;
import dangeon.model.object.artifact.item.disc.Disc;
import dangeon.model.object.artifact.item.disc.DiscA;
import dangeon.model.object.creature.player.Player;
import dangeon.util.STAGE;

public class フランドールの瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public フランドールの瓶(Point p) {
		this(p, 3);
	}

	public フランドールの瓶(Point p, int size) {
		super(p, "", size, STAGE.紅魔郷);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	protected void potUse() {
		if (LIST.size() == 3) {
			String name = A.getName();
			for (Base_Artifact a : LIST) {
				if (!a.getName().equals(name)) {
					return;
				}
			}
			setMax(4);
			Base_Artifact a;
			if (A instanceof Base_Disc) {
				if (A instanceof DiscA) {
					a = new DiscA(Player.me.getMassPoint(),
							((Base_Disc) A).list_stage.get(0).toString());
				} else {
					Base_Disc disc = (Base_Disc) A;
					a = new Disc(Player.me.getMassPoint(), disc.list_stage.get(
							0).toString(), disc.list_stage.get(1).toString());
				}
			} else {
				a = ItemTable.createObject(Player.me.getMassPoint(),
						A.getClass());
			}
			staticCheck();
			LIST.add(a);
		}
	}
}
