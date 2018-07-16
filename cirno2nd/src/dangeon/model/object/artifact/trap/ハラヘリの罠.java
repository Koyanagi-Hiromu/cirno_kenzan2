package dangeon.model.object.artifact.trap;

import java.awt.Point;

import main.res.Image_Artifact;
import main.res.SE;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.UtilMessage;

public class ハラヘリの罠 extends Base_Trap {
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
	protected static final int DURABILITY = 20;
	/**
	 * 罠の名前
	 */
	public static final int composition = 0;
	protected static final String name = "ハラヘリの罠";

	public ハラヘリの罠(Point p) {
		super(p, name, PARCENTAGE, DURABILITY);
		IM = Image_Artifact.trap1_14;
		sim = ENCHANT_SIMBOL.ハラ;

	}

	@Override
	protected void action(Base_Creature c) {
		flag_demand_waiting = false;
		SE.ONAKASUITA.play();
		if (c instanceof Player) {
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.衰)) {
				UtilMessage.effectDefMsg_RIBON();
				return;
			}
			Message.set(c.getColoredName(), "のお腹が鳴った");
			Player.me.chengeSatiety(-20);
		} else {
			Message.set(c.getColoredName(), "はお腹がすいて倒れた");
			TaskOnMapObject.addEnemyRemoveTask(c);
			c.nodamageGetExp();
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "踏むとグゥゥとお腹がなる音がする。ただそれだけなのに一緒につられてお腹がへる。栄光のリボンを装備していると耐えることが出来る。ああたまにはお肉も食べたい。";
	}
}
