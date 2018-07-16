package dangeon.model.object.artifact.item.food;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.creature.player.Player;

/**
 * 大きなおにぎり
 * 
 * @author Administrator
 * 
 */
public class スモールおにぎり extends Food {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "小さなおにぎり";

	public スモールおにぎり(Point p) {
		super(p, item_name);
	}

	@Override
	protected void foodUse() {
		if (Player.me.getMAX_SATIETY() <= Player.me.getSATIETY()) {
			Player.me.setMAX_SATIETY(Player.me.getMAX_SATIETY() + 1);
			Player.me.chengeSatiety(50);
			SE.IKAKUTYO.play();
			Message.set("最大満腹度が１上がった");
		} else if (Player.me.getMAX_SATIETY() <= Player.me.getSATIETY() + 50) {
			Player.me.chengeSatiety(100);
			Message.set("お腹が一杯になった");
		} else {
			Player.me.chengeSatiety(50);
			Message.set("それなりにお腹が膨れた");
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "このおにぎりは凍ったりメッキをされたり腐ったりする運命が控えていてどの未来に辿り着くかはチルノの手に委ねられている。なんてね。";
	}

}
