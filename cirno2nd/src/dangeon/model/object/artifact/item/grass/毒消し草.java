package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.resistant.Poison;
import dangeon.model.object.creature.resistant.ResistWeakPoint;

/**
 * どくけしそう
 * 
 * @author Administrator
 * 
 */
public class 毒消し草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "毒消し草";
	public static final String item_name_sound = "とくけしそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.消;

	public 毒消し草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		Poison.strHeal(c);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "減っているちからを全回復する。ちからが減っていない場合効果はない。印はちからもちからの最大値も下がらなくなる。ちなみに蜂蜜につけたレモンの味がする。";
	}

	@Override
	public boolean grassUse() {
		Poison.strHeal(Player.me);
		return true;
	}

	@Override
	protected boolean isAbleToBeHittedChecked(Base_Creature c) {
		if (ResistWeakPoint.毒吸収.isResist(c)) {
			return true;
		} else if (c.getSTR() < c.getMAX_STR()) {
			return true;
		}
		return false;
	}

}