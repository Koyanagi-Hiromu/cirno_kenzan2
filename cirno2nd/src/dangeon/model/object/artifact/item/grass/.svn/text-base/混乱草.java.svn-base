package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

/**
 * 混乱草
 * 
 * @author Administrator
 * 
 */
public class 混乱草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "混乱草";
	public static final String item_name_sound = "こんらんそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.混;

	public 混乱草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		SE.STATUS_PIYOPIYO.play();
		c.setCondition(CONDITION.混乱, 0);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		// TODO 決定稿か不明
		return "投げて使うか印を利用しよう。チルノの混乱状態は移動と攻撃がでたらめになるが投げたり杖を振ったりする時はそのまま使える。ちなみにマスタードの味がする。";
	}

	@Override
	public boolean grassUse() {
		Message.set("チルノは混乱した");
		SE.STATUS_PIYOPIYO.play();
		Player.me.setCondition(CONDITION.混乱, 0);
		// if (isEnchantedNow())
		// Enchant.forceToRemove(this);
		// Belongings.remove(this);
		return true;
	}

}
