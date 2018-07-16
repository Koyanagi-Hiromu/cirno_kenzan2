package dangeon.model.object.artifact.item.food;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.Player;

/**
 * 大きなおにぎり
 * 
 * @author Administrator
 * 
 */
public class 巨大なおにぎり extends Food {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "巨大なおにぎり";
	public static final int composition = 0;
	public static final int item_str_level1 = 0;
	public static final int item_str_level2 = 0;
	public static final int item_str_level3 = 0;
	public static final int item_def_level1 = 0;
	public static final int item_def_level2 = 0;
	public static final int item_def_level3 = 0;

	public 巨大なおにぎり(Point p) {
		super(p, item_name);
		sim = ENCHANT_SIMBOL.飯;
	}

	@Override
	protected void foodUse() {
		if (Player.me.getMAX_SATIETY() == 200) {
			Player.me.chengeSatiety(Player.me.getMAX_SATIETY());
			Message.set("満腹になりお腹が重くなった");
		} else {
			SE.IKAKUTYO.play();
			Player.me.setMAX_SATIETY(Player.me.getMAX_SATIETY() + 5);
			Player.me.chengeSatiety(Player.me.getMAX_SATIETY());
			Message.set("満腹になり最大満腹度が5上がった");
			Message.set("お腹も足も重くなった");
		}
		SE.STATUS_SLOW.play();
		Player.me.setCondition(CONDITION.鈍足, 0);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "しかし出来るだけ満腹度を減らさないように行動するのが優れた冒険者であろう。斜め移動はしているか？足踏みはし過ぎていないか？草を飲まずに捨てていないか？";
	}

}
