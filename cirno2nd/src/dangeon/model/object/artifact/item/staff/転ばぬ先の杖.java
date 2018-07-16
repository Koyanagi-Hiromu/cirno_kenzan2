package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import dangeon.model.object.creature.Base_Creature;

/**
 * 杖
 * 
 * @author Administrator
 * 
 */
public class 転ばぬ先の杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "転ばぬ先の杖";
	public static final String item_name_sound = "ころはぬのつえ";
	private static final int item_use_damage = 5;

	public 転ばぬ先の杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(Base_Creature c) {
		// MapInSelect.転ばし(this, null, c, this.direction, 1, 10);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "このアイテムを持っていると転ばなくなるぞ";
		// return
		// "１０マス吹き飛び５ダメージ与える。敵だけではなくアイテムや罠も階段も飛ぶ。２マス先の敵に振るときは手前にアイテムがないか確認しよう。";
	}
}