package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

/**
 * 杖
 * 
 * @author Administrator
 * 
 */
public class ハイテンションの杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "ハイテンションの杖";
	public static final String item_name_sound = "はいてんしょんのつえ";

	public ハイテンションの杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(Base_Creature c) {
		if (!Player.me.equals(c)) {
			SE.STATUS_DODODO.play();
			c.setCondition(CONDITION.ええんじゃないか, 0);
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "力が上がるハイテンション状態と、封印状態を同時に付けるぞ";
	}

}