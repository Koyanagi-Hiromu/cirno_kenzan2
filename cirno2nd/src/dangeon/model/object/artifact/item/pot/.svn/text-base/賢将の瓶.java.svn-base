package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import dangeon.model.map.ItemFall;

public class 賢将の瓶 extends Base_Pot_Selective {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 賢将の瓶(Point p) {
		this(p, 4);
	}

	private 賢将の瓶(Point p, int size) {
		super(p, "賢将の魔法瓶", size);
	}

	@Override
	public boolean broken() {
		super.broken();
		ItemFall.itemFall(mass_point, new 賢将の瓶(mass_point));
		ItemFall.itemFall(mass_point, new 賢将の瓶(mass_point));
		return true;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	protected void potUse() {
	}
}
