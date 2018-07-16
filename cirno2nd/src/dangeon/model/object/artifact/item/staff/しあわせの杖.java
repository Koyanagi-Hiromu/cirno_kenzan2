package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import dangeon.model.config.Config;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

/**
 * 杖
 * 
 * @author Administrator
 * 
 */
public class しあわせの杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "しあわせの杖";
	public static final String item_name_sound = "しあわせのつえ";
	public static final int composition = 0;

	public しあわせの杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(Base_Creature c) {
		if (c instanceof Player && Config.getFate() == 2) {
			Player.me.setExpCash(999);
		} else
			c.addLV(1);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "敵に当たるとレベルが上がる。この杖を見て「どうして杖は自分に向かって振れないんだ」と思うのは誰しもが１度は通る道。";
	}

}