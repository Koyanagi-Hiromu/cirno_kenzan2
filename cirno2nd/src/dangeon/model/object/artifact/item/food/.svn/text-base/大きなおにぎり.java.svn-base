package dangeon.model.object.artifact.item.food;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Player;

/**
 * 大きなおにぎり
 * 
 * @author Administrator
 * 
 */
public class 大きなおにぎり extends Food {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "大きなおにぎり";

	public 大きなおにぎり(Point p) {
		super(p, item_name);
		sim = ENCHANT_SIMBOL.飯;
	}

	@Override
	protected void foodUse() {
		if (Player.me.getMAX_SATIETY() <= Player.me.getSATIETY()) {
			Player.me.setMAX_SATIETY(Player.me.getMAX_SATIETY() + 5);
			Player.me.chengeSatiety(Player.me.getMAX_SATIETY());
			SE.IKAKUTYO.play();
			Message.set("最大満腹度が５上がった");
		} else if (Player.me.getMAX_SATIETY() <= Player.me.getSATIETY() + 110) {
			Player.me.chengeSatiety(Player.me.getMAX_SATIETY());
			Message.set("お腹が一杯になった");
		} else {
			Player.me.chengeSatiety(110);
			Message.set("それなりにお腹が膨れた");
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "おにぎりは満腹の時に食べると最大満腹度が上昇する。また装備しているおにぎりは腐る対象にならない。ところで「奇跡」も装備しているアイテムには無効らしい。";
	}

}
