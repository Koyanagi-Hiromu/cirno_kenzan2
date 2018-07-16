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
import dangeon.view.detail.MiniMap;

/**
 * 
 * @author Administrator
 * 
 */
public class 灯草 extends Base_Grass {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "灯草";
	public static final String item_name_sound = "めくすりそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.冴;

	public 灯草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "罠や透明の敵を見ることができるよく見え状態になる。良い状態も１つの状態異常として扱われる。ちなみにイチゴシロップの味がする。";
	}

	@Override
	public boolean grassUse() {
		MainMap.addEffect(new GoodBadEffect(true));
		SE.STATUS_MEGUSURI.play();
		Message.set("目が凄く良くなった");
		Player.me.setCondition(CONDITION.目薬, 0);
		Player.me.setCondition(CONDITION.蛍, 0);
		MiniMap.openTiles();
		// if (isEnchantedNow())
		// Enchant.forceToRemove(this);
		// Belongings.remove(this);
		return true;
	}

	@Override
	protected boolean isAbleToBeHittedChecked(Base_Creature c) {
		return false;
	}
}