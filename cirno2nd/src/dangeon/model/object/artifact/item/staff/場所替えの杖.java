package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.action.SpecialAction;

/**
 * 杖
 * 
 * @author Administrator
 * 
 */
public class 場所替えの杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "場所替えの杖";
	public static final String item_name_sound = "はしょかえのつえ";

	public 場所替えの杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(Base_Creature c) {
		SpecialAction.場所替え(c, used_creature);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "この杖に攻撃力はない。敵を止めることもできない。しかしこの杖を１回振るだけで１０体の敵から逃げられることもある。冒険者の目的は敵を倒すことではないのだ。";
	}
}