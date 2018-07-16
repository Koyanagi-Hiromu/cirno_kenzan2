package dangeon.model.object.artifact.trap;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.util.R;
import dangeon.util.UtilMessage;
import dangeon.view.detail.View_Sider;

public class 呪いの罠 extends Base_Trap {
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
	protected static final String name = "呪いの罠";

	public 呪いの罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_2;
		sim = ENCHANT_SIMBOL.呪罠;
	}

	@Override
	protected Base_Artifact action(Base_Artifact a) {
		SE.SYSTEM_CURSE.play();
		if (a.isCurse() || !a.isAbleToCurse()
				|| a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
			Message.set("しかし何も効果は無かった");
		} else {
			a.setCurse(true);
			Message.set(new String[] { a.getColoredName().concat("は"), "呪われた" });
			View_Sider.setInformation(a.getColoredName(), "が呪われました");
		}
		return super.action(a);
	}

	@Override
	protected void action(Base_Creature c) {
		List<Base_Artifact> notCurse = new ArrayList<Base_Artifact>();
		flag_demand_waiting = false;
		if (c instanceof Player) {
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.護)) {
				UtilMessage.effectDefMsg_Hina();
				return;
			}
			if (EnchantSpecial.enchantSimbolAllCheck(ENCHANT_SIMBOL.祓)) {
				Message.set(Player.me.getColoredName(), "は呪いを防いだ");
				return;
			}
			for (Base_Artifact a : Belongings.getListItems()) {
				if (a.isCurse() || !a.isAbleToCurse()
						|| a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
					continue;
				}
				notCurse.add(a);
			}
			if (notCurse.size() == 0) {
				Message.set("しかし何も効果は無かった");
			} else {
				SE.SYSTEM_CURSE.play();
				Random ran = new R();
				int selection = ran.nextInt(notCurse.size());
				Message.set(new String[] {
						notCurse.get(selection).getColoredName().concat("は"),
						"呪われた" });
				View_Sider.setInformation(notCurse.get(selection)
						.getColoredName(), "が呪われました");
				notCurse.get(selection).setCurse(true);
			}
		} else {
			SE.SYSTEM_CURSE.play();
			c.setCondition(CONDITION.邪法, 0);
			c.setCondition(CONDITION.死, 0);
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "装備していない持ち物が１つ呪われる。呪われたアイテムは使用することが出来なくなり装備から外すことも出来なくなる。おはらいの書を読もう。";
	}
}
