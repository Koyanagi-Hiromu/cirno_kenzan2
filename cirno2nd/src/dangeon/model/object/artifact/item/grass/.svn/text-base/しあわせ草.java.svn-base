package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

/**
 * しあわせそう
 * 
 * @author Administrator
 * 
 */
public class しあわせ草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "しあわせ草";
	public static final String item_name_sound = "しあわせそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.幸せ草;

	public しあわせ草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		c.addLV(1);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "投げて当たった場合もレベルが１上がる。レベルが上がった敵を倒す手段があるなら飲むより投げてみてもいい。ちなみにワタアメの味がする。";
	}

	@Override
	public boolean grassUse() {
		if (Player.me.getLV() == 99) {
			Message.set(Player.me.getColoredName().concat("特に効果が無かった"));
			return true;
		}
		SE.LEVEL_UP.play();
		Player.me.addLV(1);
		// if (isEnchantedNow())
		// Enchant.forceToRemove(this);
		// Belongings.remove(this);
		return true;
	}
}