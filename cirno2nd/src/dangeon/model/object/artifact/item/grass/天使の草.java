package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;

/**
 * 天使の種
 * 
 * @author Administrator
 * 
 */
public class 天使の草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "天使の草";
	public static final String item_name_sound = "てんしのくさ";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.天使;

	public 天使の草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		c.addLV(3);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "幸せ草３つ分の効果がある。ただし印は幸せ草と同じ効果である。ちなみに家庭的な味がする。幸福とはあるべき所に帰ることなのかもしれない。";
	}

	@Override
	public boolean grassUse() {
		SE.LEVEL_UP.play();
		Message.set(Player.me.getColoredName(), "のレベルが３上がった");
		Player.me.addLV(3);
		return true;
	}

}