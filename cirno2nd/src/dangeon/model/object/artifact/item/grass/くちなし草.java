package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.GoodBadEffect;
import dangeon.view.detail.MainMap;

/**
 * 
 * @author Administrator
 * 
 */
public class くちなし草 extends Base_Grass {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "めぐすり草";
	public static final String item_name_sound = "めくすりそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.封;

	public くちなし草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		SE.STATUS_SEAL.play();
		c.setCondition(CONDITION.封印, 0);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "罠や透明の敵を見ることができるよく見え状態になる。良い状態も１つの状態異常として扱われる。ちなみにイチゴシロップの味がする。";
	}

	@Override
	public boolean grassUse() {
		MainMap.addEffect(new GoodBadEffect(false));
		SE.STATUS_SEAL.play();
		Player.me.setCondition(CONDITION.封印, 0);
		Message.set(Player.me.getColoredName(), "はクチが使えなくなった");
		return true;
	}
}