package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.action.SpecialAction;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;

/**
 * 杖
 * 
 * @author Administrator
 * 
 */
public class 小悪魔の杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "小悪魔の杖";
	public static final String item_name_sound = "こあくまのつえ";

	public 小悪魔の杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(final Base_Creature c) {
		switch ((int) (new R().nextDouble() * 4)) {
		case 0:
			SE.STATUS_SPEEDY.play();
			Message.set("倍速の効果が起こった");
			c.setCondition(CONDITION.倍速, 0);
			break;
		case 1:
			Message.set("吹き飛ばしの効果が起こった");
			MapInSelect.吹き飛ばし(Player.me, "吹き飛ばし", c, direction, 10, 5);
			break;
		case 2:
			Message.set("ワープの効果が起こった");
			SpecialAction.高飛び(c);
			break;
		case 3:
			Message.set("場所替えの効果が起こった");
			SpecialAction.場所替え(c, used_creature);
			break;
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "実は小悪魔は三種類の悪魔の杖を振ってくるぞ";
	}
}