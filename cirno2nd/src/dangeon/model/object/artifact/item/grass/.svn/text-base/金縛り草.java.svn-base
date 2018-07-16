package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

/**
 * 睡眠草
 * 
 * @author Administrator
 * 
 */
public class 金縛り草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "金縛り草";
	public static final String item_name_sound = "かなしはりそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.痺;

	public 金縛り草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		if (c instanceof Player) {
			SE.STATUS_SIBIBI.play();
			c.setCondition(CONDITION.麻痺, 0);
		} else {
			SE.STATUS_SIBIBI.play();
			c.setCondition(CONDITION.麻痺, 0);
		}
	}

	@Override
	protected String getSecondExplain_ByEach() {
		// TODO 決定稿か不明
		return "投げて使うか印を利用しよう。金縛り状態にする方法は多いが金縛り状態にする印はこのアイテムしかない。ちなみにコンペイトウの味がする。";
	}

	@Override
	public boolean grassUse() {
		SE.STATUS_SIBIBI.play();
		Player.me.setCondition(CONDITION.麻痺, 0);
		// if (isEnchantedNow())
		// Enchant.forceToRemove(this);
		// Belongings.remove(this);
		return true;
	}

}
