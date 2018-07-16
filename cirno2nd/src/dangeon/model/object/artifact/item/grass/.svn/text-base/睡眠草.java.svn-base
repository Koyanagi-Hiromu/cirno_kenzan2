package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

/**
 * 睡眠草
 * 
 * @author Administrator
 * 
 */
public class 睡眠草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "睡眠草";
	public static final String item_name_sound = "すいみんそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.睡;

	public 睡眠草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		SE.STATUS_SLEEP.play();
		c.setCondition(CONDITION.睡眠, 0);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		// TODO 決定稿か不明
		return "投げて使うか印を利用しよう。眠ると３ターンの間何もできなくなる。他の状態異常印と比べて発動確率が少しだけ低い。ちなみに白ブドウの味がする。";
	}

	@Override
	public boolean grassUse() {
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.覚醒)) {
			Message.set("眠りそうになったが", Player.me.getColoredName(),
					"は目が冴えていて大丈夫だった");
		} else {
			Message.set("チルノは寝た");
			SE.STATUS_SLEEP.play();
			Player.me.setCondition(CONDITION.睡眠, 0);
		}
		// if (isEnchantedNow())
		// Enchant.forceToRemove(this);
		// Belongings.remove(this);
		return true;
	}
}
