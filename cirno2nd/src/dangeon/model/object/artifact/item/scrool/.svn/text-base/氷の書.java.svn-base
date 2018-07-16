package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import main.res.SE;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.util.Damage;
import dangeon.util.MapInSelect;
import dangeon.view.anime.CirnoIceEffect;
import dangeon.view.detail.MainMap;

/**
 * 
 * @author Administrator
 * 
 */
public class 氷の書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "氷の書";
	public static final String item_name_sound = "こおりのしょ";
	public ENCHANT_SIMBOL simbol = ENCHANT_SIMBOL.冴;

	int use_damage = 50;

	public 氷の書(Point p) {
		super(p, item_name);
		sim = simbol;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "部屋内にダメージを与えるぞ。%完全に凍ると範囲が広がるぞ";
	}

	@Override
	public void scroolUse() {
		SE.ICE.play();
		MainMap.addEffect(new CirnoIceEffect(), true);
		if (this.isFrozen()) {
			MassCreater.frozenWater();
			MapInSelect.damageIcy_toAllEnemies(null, null, use_damage);
		} else {
			MassCreater.frozenWater(MassCreater.getPlayerRoom());
			for (Base_Creature c : MapInSelect.getListRoomOrRoadInEnemy()) {
				Damage.damage(this, null, null, Player.me, c,
						use_damage * (c.isIce() ? -1 : 1));
			}
		}
	}

}