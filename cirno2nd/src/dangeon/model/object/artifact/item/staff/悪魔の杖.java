package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.Base_Creature;
import dangeon.util.R;

/**
 * 杖
 * 
 * @author Administrator
 * 
 */
public class 悪魔の杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "悪魔の杖";
	public static final String item_name_sound = "あくまのつえ";

	Staff staff = new 一時しのぎの杖(new Point(0, 0));

	public 悪魔の杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(final Base_Creature c) {
		switch ((int) (new R().nextDouble() * 4)) {
		case 0:
			SE.STATUS_SIBIBI.play();
			Message.set("金縛りの効果が起こった");
			c.setCondition(CONDITION.麻痺, 0);
			break;
		case 1:
			Message.set("一時しのぎの効果が起こった");
			staff.effect(c);
			break;
		case 2:
			SE.STATUS_PIYOPIYO.play();
			Message.set("混乱の効果が起こった");
			c.setCondition(CONDITION.混乱, 0);
			break;
		case 3:
			SE.STATUS_SHADOW.play();
			Message.set("影縫いの効果が起こった");
			c.setCondition(CONDITION.影縫い, 0);
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "実は小悪魔は三種類の悪魔の杖を振ってくるぞ";
	}
}