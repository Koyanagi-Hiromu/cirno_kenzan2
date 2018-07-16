package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.ThrowingItem.HowToThrow;
import dangeon.controller.task.Task;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

/**
 * 杖
 * 
 * @author Administrator
 * 
 */
public class 吹き飛ばしの杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "吹き飛ばしの杖";
	public static final String item_name_sound = "ふきとはしのつえ";
	private static final int item_use_damage = 5;

	public 吹き飛ばしの杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(Base_Creature c) {
		DIRECTION direction = DIRECTION.getDirection(
				used_creature.getMassPoint(), c.getMassPoint());
		if (direction == DIRECTION.NEUTRAL) {
			// 反射された
			direction = c.direction.getReverse();
		}
		if (Player.me.equals(c)
				&& EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
						ENCHANT_SIMBOL.魔)) {
			MapInSelect.吹き飛ばし(used_creature, "吹き飛ばし", c, direction, 5,
					item_use_damage / 2);
		} else {
			MapInSelect.吹き飛ばし(used_creature, "吹き飛ばし", c, direction, 10,
					item_use_damage);
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "１０マス吹き飛ばし５ダメージ与える。距離を取ったあと他のアイテムで迎撃するために使うか通路や階段に逃げ込むために使おう。";
		// return
		// "１０マス吹き飛び５ダメージ与える。敵だけではなくアイテムや罠も階段も飛ぶ。２マス先の敵に振るときは手前にアイテムがないか確認しよう。";
	}

	@Override
	public boolean isMagicHitToItem() {
		return true;
	}

	@Override
	public void itemHitCheck(boolean ento, final Base_Creature c,
			final Base_Artifact a) {
		staticCheck();
		new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				DIRECTION direction = DIRECTION.getDirection(c.getMassPoint(),
						a.getMassPoint());
				a.direction = direction;
				a.itemThrow(c, HowToThrow.NORMAL, false, 10, true);
			}
		}.work_appointment();
	}
}