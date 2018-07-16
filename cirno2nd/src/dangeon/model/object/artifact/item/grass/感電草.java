package dangeon.model.object.artifact.item.grass;

import java.awt.Point;

import main.res.SE;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.ThunderDamage;

public class 感電草 extends Base_Grass {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "感電草";
	public static final String item_name_sound = "かんてんそう";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.電;

	private final int damage = 15;

	public 感電草(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected void effect(Base_Creature c) {
		SE.STATUS_SPEEDY.play();
		ThunderDamage.thunderDamage(this, Player.me, c, damage);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "感電し草";
	}

	@Override
	public boolean grassUse() {
		ThunderDamage.thunderDamage(this, Player.me, Player.me, damage);
		return false;
	}

}
