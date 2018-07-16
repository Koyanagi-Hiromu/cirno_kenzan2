package dangeon.model.object.artifact.trap;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.food.Food;
import dangeon.model.object.artifact.item.food.ミシャグジおにぎり;
import dangeon.model.object.artifact.item.food.腐ったおにぎり;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.util.UtilMessage;
import dangeon.view.detail.View_Sider;

public class デロデロの罠 extends Base_Trap {
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
	protected static final String name = "デロデロの罠";

	public デロデロの罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_15;
		sim = ENCHANT_SIMBOL.腐;

	}

	@Override
	public Base_Artifact action(Base_Artifact a) {
		if (a instanceof Food) {
			if (a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
				SE.MEKKI.play();
				Message.set("しかし", a.getColoredName(), "は神の加護があって大丈夫だった");
			} else {
				View_Sider.setInformation(a.getColoredName(), "がデロデロになりました");
				Message.set(a.getColoredName(), "はデロデロになった");
				return new 腐ったおにぎり(a.getMassPoint());
			}
		} else {
			Message.set("しかしなんともなかった");
		}
		return a;
	}

	@Override
	protected void action(Base_Creature c) {
		flag_demand_waiting = false;
		if (c instanceof Player) {
			actionPlayer();
		} else {
			Message.set(c.getColoredName(), "はデロデロになった");
			TaskOnMapObject.addEnemyRemoveTask(c);
			c.nodamageGetExp();
			ItemFall.itemFall(getMassPoint(), new ミシャグジおにぎり(getMassPoint()));
		}
	}

	private void actionPlayer() {
		if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.護)) {
			UtilMessage.effectDefMsg_Hina();
			return;
		}
		ArrayList<Base_Artifact> belonging_list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a instanceof Food && !Enchant.isEnchanted(a)
					&& !(a instanceof 腐ったおにぎり)
					&& !a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
				belonging_list.add(a);
			}
		}
		if (!belonging_list.isEmpty()) {
			ArrayList<Base_Artifact> li = new ArrayList<Base_Artifact>(3);
			int r = R.ran(5) / 2 + 1;
			for (int i = 0; i < r && !belonging_list.isEmpty(); i++) {
				Random ran = new R();
				int selection = ran.nextInt(belonging_list.size());
				Base_Artifact a = belonging_list.remove(selection);
				li.add(a);
			}
			for (Base_Artifact a : li) {
				View_Sider.setInformation(a.getColoredName(), "がデロデロになりました");
				Message.set(a.getColoredName(), "はデロデロになった");
				int index = Belongings.getListItems().indexOf(a);
				Belongings.remove(a);
				Belongings.setItems(new 腐ったおにぎり(Player.me.getMassPoint()),
						index);
			}
		} else {
			Message.set("しかしなんともなかった");
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "持っているおにぎりが１つデロデロになる。デロデロ。なんて気持ちの悪い響き。本当は腐るわけじゃないけど腐っているとの表現が実に言い得て妙でいやだ。";
	}
}
