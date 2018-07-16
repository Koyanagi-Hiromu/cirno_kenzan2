package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import dangeon.model.object.creature.Base_Creature;

/**
 * 杖
 * 
 * @author Administrator
 * 
 */
public class 不幸の杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "不幸の杖";
	public static final String item_name_sound = "ふこうのつえ";

	public 不幸の杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(Base_Creature c) {
		c.addLV(-1);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "レベルが変化した敵はＨＰとちからが全回復する。また悪いことをすると飛び出てくる彼岸の彼女らを模した敵はレベルが下がると強くなるので注意。";
	}

}