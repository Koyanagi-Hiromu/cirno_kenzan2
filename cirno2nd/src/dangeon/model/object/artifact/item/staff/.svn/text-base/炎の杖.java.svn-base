package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印炎;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.view.anime.FireEffect;
import dangeon.view.detail.MainMap;

public class 炎の杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "炎の杖";
	public static final String item_name_sound = "こおりのつえ";

	public 炎の杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(final Base_Creature c) {
		int dam = 70;
		if (Player.me.equals(c)
				&& EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
						ENCHANT_SIMBOL.魔)) {
			dam /= 2;
		}
		final int dammm = dam;
		MainMap.addEffect(new FireEffect(c, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
			}

			@Override
			protected void work(int frame) {
				if (frame == 6) {
					int damage = c.damagedWithFire(dammm);
					if (c.equals(Player.me)) {
						Damage.damage(used_creature, "炎に焼かれ", null,
								used_creature, c, 印炎.effect(damage));
					} else {
						Damage.damage(used_creature, "炎に焼かれ", null,
								used_creature, c, damage);
					}
				}
			}
		}), true);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "こおりの杖";
	}

}