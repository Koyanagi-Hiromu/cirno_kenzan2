package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.action.SpecialAction;
import dangeon.util.MapInSelect;
import dangeon.util.R;

/**
 * 杖
 * 
 * @author Administrator
 * 
 */
public class 小悪魔秘蔵の杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "小悪魔秘蔵の杖";
	public static final String item_name_sound = "こあくまひそうのつえ";

	public 小悪魔秘蔵の杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(Base_Creature c) {
		switch (R.ran(3)) {
		case 0:
			c.setCondition(CONDITION.鈍足, 0);
			c.setCondition(CONDITION.混乱, 0);
			c.setCondition(CONDITION.封印, 0);
			break;
		case 1:
			MapInSelect.吹き飛ばし(used_creature, "吹き飛ばし", c, direction, 10, 5);
			c.setCondition(CONDITION.倍速, 0);
			SpecialAction.高飛び(c);
			break;
		case 2:
			c.setCondition(CONDITION.麻痺, 0);
			c.setCondition(CONDITION.鈍足, 0);
			c.setCondition(CONDITION.影縫い, 0);
			break;
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "パチュリーが小悪魔の杖を魔改造したという話。";
	}

}