package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.resistant.Poison;

/**
 * どくけしそう
 * 
 * @author Administrator
 * 
 */
public class 毒草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "毒草";
	public static final String item_name_sound = "とくそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.鈍足;

	public 毒草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		Poison.effect(c, 1, false, true);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "ちからが下がると攻撃力が下がるし投げたアイテムが与えるダメージも下がる。ちからが下がったら毒消し草を探そう。ちなみに熟れた柿の味がする。";
	}

	@Override
	public boolean grassUse() {
		effect(Player.me);
		return true;
	}

}