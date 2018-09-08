package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.view.anime.IcyEffect;
import dangeon.view.detail.MainMap;

public class 氷の杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "氷の杖";
	public static final String item_name_sound = "こおりのつえ";
	private static final int damage = 40;

	public 氷の杖(Point p) {
		super(p, item_name, 1);
	}
	
	public 氷の杖(Point p, boolean frozen) {
		this(p);
		if (frozen)
		{
			setFreezeCount(9999);
		}
	}

	@Override
	protected void effect(final Base_Creature c) {

		MainMap.addEffect(new IcyEffect(c, new Task() {
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
					if (c.conditionCheck(CONDITION.炎上)) {
						c.setConditionRemoveTask(CONDITION.炎上);
					}
					int _dam = getDamage();
					if (Player.me.equals(c)
							&& EnchantSpecial.enchantSimbolAllCheck(CASE.DEF,
									ENCHANT_SIMBOL.魔)) {
						_dam /= 2;
					}
					if (c.isIce())
						_dam = -_dam;
					Damage.damage(used_creature, null, "凍りついて倒れた",
							used_creature, c, _dam);
				}
			}

		}), true);
	}

	private int getDamage() {
		int _dam = damage;
		if (isNonFrozen()) {
			return _dam / 2;
		} else {
			_dam += isCold() ? damage : 0;
			_dam += isFrozen() ? damage : 0;
			return _dam;
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "こおりの杖";
	}

	@Override
	public boolean isMagicHitToItem() {
		return true;
	}

	@Override
	public void itemHitCheck(boolean ento, final Base_Creature c,
			final Base_Artifact a) {
		a.setVisible(true);
		staticCheck();
		MainMap.addEffect(new IcyEffect(a.getMassPoint(), new Task() {
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
					if (a instanceof Base_Trap) {
						MapList.removeArtifact(a);
						Message.set(a.getColoredName(), "は凍って壊れた");
					} else {
						a.freezeCountPlus(10);
						Message.set(a.getColoredName(), "が凍った");
					}
				}
			}
		}), true);
	}

}