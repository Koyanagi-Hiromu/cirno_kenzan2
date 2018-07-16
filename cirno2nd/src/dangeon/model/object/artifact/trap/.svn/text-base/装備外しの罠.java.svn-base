package dangeon.model.object.artifact.trap;

import java.awt.Point;
import java.util.ArrayList;

import main.res.Image_Artifact;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.UtilMessage;
import dangeon.view.detail.View_Sider;

public class 装備外しの罠 extends Base_Trap {
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
	protected static final String name = "装備外しの罠";

	public 装備外しの罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_1;
		sim = ENCHANT_SIMBOL.装備;
	}

	@Override
	protected void action(Base_Creature c) {
		flag_demand_waiting = false;
		if (c instanceof Player) {
			action_player();
		} else {
			c.setCondition(CONDITION.封印, 0);
		}
	}

	private void action_player() {
		if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.融合)) {
			UtilMessage.effectDefMsg_RIBON();
			return;
		}
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>(2);
		remove(list, new Enchant[] { Enchant.ATK, Enchant.DEF });
		if (list.size() == 0) {
			remove(list, new Enchant[] { Enchant.ANY1, Enchant.ANY2 });
		}
		if (list.size() == 0) {
			Message.set("しかし何も外されなかった");
		} else {
			Message.set("触手がぬるぬると動いて");
			for (Base_Artifact a : list) {
				Message.set(a.getColoredName(), "が外されてしまった");
				View_Sider.setInformation(a.getColoredName(), "が外れました");
				Enchant.forceToRemove(a);
			}
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "踏むと足元から触手が迫り装備しているアイテムを全て外してしまう。触手は見た目がとてもえっちぃけど偉い人が怒るといけないので見せられないよ。";
	}

	private void remove(ArrayList<Base_Artifact> list, Enchant[] enchants) {
		for (Enchant e : enchants) {
			Base_Artifact a = e.getEnchant();
			if (a != null)
				list.add(a);
		}
	}
}
