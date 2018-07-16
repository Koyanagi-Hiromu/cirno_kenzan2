package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.EnemyFall;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;

/**
 * 杖
 * 
 * @author Administrator
 * 
 */
public class 一時しのぎの杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "一時しのぎの杖";
	public static final String item_name_sound = "いちししのきのつえ";
	private static final int item_use = 50;

	public 一時しのぎの杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(final Base_Creature c) {
		if (c instanceof Base_NPC) {

		} else {
			c.setMassPoint_Jump(EnemyFall.getPoint(stairs(c)), new Task() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					SE.STATUS_SIBIBI.play();
					int t;
					if (Player.me.equals(c)
							&& EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
									ENCHANT_SIMBOL.魔)) {
						t = -1;
					} else {
						t = 0;
					}
					c.setCondition(CONDITION.麻痺, t);
				}
			});
		}

	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "金縛りはしばらくすると解けてしまう。まさに一時しのぎ。機会があったら自分に当ててみよう。階段にゴールインできる。１回は攻撃されちゃうけどね。";
	}

	private Point stairs(Base_Creature c) {
		for (Base_Artifact a : MapList.getListArtifact()) {
			if (a instanceof Stairs) {
				return a.getMassPoint();
			}
		}
		return c.getMassPoint();
	}
}