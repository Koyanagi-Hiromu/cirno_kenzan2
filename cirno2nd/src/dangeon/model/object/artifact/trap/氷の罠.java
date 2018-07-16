package dangeon.model.object.artifact.trap;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.ItemFall;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.R;
import dangeon.util.UtilMessage;
import dangeon.view.anime.IceEffect;
import dangeon.view.detail.MainMap;

public class 氷の罠 extends Base_Trap {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 罠が発動する確立
	 */
	protected static final int PARCENTAGE = 80;
	/**
	 * 罠の壊れ安さ
	 */
	protected static final int DURABILITY = 50;
	/**
	 * 罠の名前
	 */
	public static final int composition = 0;
	protected static final String name = "氷の罠";

	public 氷の罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_9;
		sim = ENCHANT_SIMBOL.氷の罠;
	}

	@Override
	protected Base_Artifact action(final Base_Artifact a) {
		MainMap.addEffect(new IceEffect(a.getMassPoint(), new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				effect(a);
				MapList.removeArtifact(a);
				ItemFall.itemFall(a);
			}
		}, LV), true);
		return null;
	}

	@Override
	protected void action(final Base_Creature c) {
		flag_demand_waiting = false;
		if (c instanceof Player) {
			if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.護)) {
				UtilMessage.effectDefMsg_Hina();
				return;
			}
			final ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
			for (Base_Artifact a : Belongings.getListItems()) {
				if (a.isFrozen()
						|| a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
					continue;
				}
				list.add(a);
			}
			if (list.isEmpty()) {
				UtilMessage.effectDefMsg();
			} else {
				MainMap.addEffect(new IceEffect(Player.me, new Task() {
					/**
					 *
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						effect(list);
					}
				}, LV), true);
			}
		} else {
			MainMap.addEffect(new IceEffect(c, new Task() {
				/**
			 *
			 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					if (c.conditionCheck(CONDITION.炎上)) {
						c.setConditionRemoveTask(CONDITION.炎上);
					}
					int _dam = 40;
					if (c.isIce())
						_dam = -_dam;
					Damage.damage(null, "冷気によって", "凍りついて倒れた", null, c, _dam);
				}
			}, LV), true);

		}
	}

	private void effect(ArrayList<Base_Artifact> list) {
		SE.ICE.play();
		Random ran = new R();
		int selection = ran.nextInt(list.size());
		effect(list.get(selection));
	}

	private void effect(Base_Artifact a) {
		if (a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
			UtilMessage.effectDefMsg_Hina();
		} else {
			SE.ICE.play();
			Message.set(a.getColoredName(), "は冷気に包まれた");
			a.freezeCountPlus(10);
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "アイテムが１つ完全に凍る。装備しているアイテムが選ばれることもある。凍ったアイテムを印として使いたいなら合成するか解凍の書を使おう。";
	}
}
