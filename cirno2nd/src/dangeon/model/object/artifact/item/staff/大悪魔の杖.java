package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;

/**
 * 杖
 * 
 * @author Administrator
 * 
 */
public class 大悪魔の杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "大悪魔の杖";
	public static final String item_name_sound = "だいあくまのつえ";

	public 大悪魔の杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(final Base_Creature c) {
		switch ((int) (new R().nextDouble() * 4)) {
		case 0:
			SE.MIRACLE_ONIGIRI.play();
			Message.set("奇跡の効果が起こった");
			c.setCondition(CONDITION.おにぎり, 0);
			break;
		case 1:
			Message.set("レベルダウンの効果が起こった");
			if (c instanceof Player
					&& EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
							ENCHANT_SIMBOL.衰)) {
				Message.set(Player.me.getColoredName(), "はリボンの効果でレベルが下がらなかった");
				return;
			}
			SE.LEVEL_DOWN.play();
			c.addLV(-1);
			break;
		case 2:
			SE.STATUS_SLOW.play();
			Message.set("睡眠の効果が起こった");
			c.setCondition(CONDITION.睡眠, 0);
			break;
		case 3:
			SE.STATUS_SEAL.play();
			Message.set("封印の効果が起こった");
			c.setCondition(CONDITION.封印, 0);
			break;
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "実は小悪魔は三種類の悪魔の杖を振ってくるぞ";
	}
}