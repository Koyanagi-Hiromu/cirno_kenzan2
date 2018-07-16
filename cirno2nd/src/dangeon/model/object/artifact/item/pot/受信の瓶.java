package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.creature.player.Player;
import dangeon.util.STAGE;

public class 受信の瓶 extends Base_Pot_Selective {
	private static final long serialVersionUID = 1L;

	public 受信の瓶(Point p, int size) {
		super(p, "式神の魔法瓶", size, STAGE.永夜抄);
	}

	public 受信の瓶(送信の瓶 jar) {
		super(Player.me.getMassPoint(), "式神の魔法瓶", 0, STAGE.永夜抄);
		if (jar != null) {
			for (Base_Artifact e : jar.LIST) {
				LIST.add(e);
			}
			setMax(LIST.size());
		}
		Checker.checkStatic(this);
	}

	@Override
	public void endPlayerTurn() {
	}

	public String[] getListForNames() {
		String[] arr = new String[LIST.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = LIST.get(i).getColoredName();
		}
		return arr;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	protected void potUse() {
	}

}
