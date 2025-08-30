package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.util.MapInSelect;
import dangeon.view.anime.ThunderEffect;
import dangeon.view.detail.MainMap;
import main.res.SE;


public class 自由人の狂想曲 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "非未来「自由人の狂想曲」";
	public static final String item_name_sound = "ひみらい「じゆうじんのげんそうきょく」";

	private boolean read = false;

	public 自由人の狂想曲(Point p) {
		super(p, "非未来「自由人の狂想曲」");
		sim = ENCHANT_SIMBOL.鴇;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "様々な事が書いてある";
	}

	@Override
	public boolean itemUse() {
		scroolUse();
		return true;
	}

	@Override
	public void scroolUse() {
		SE.LIGHTNING.play();
		MainMap.addEffect(new ThunderEffect(MassCreater.isPlayerInRoom()));
		for (Base_Enemy em : MapInSelect.getListRoomOrRoadInEnemy()) {
			em.setLv4();
		}
	}
}
