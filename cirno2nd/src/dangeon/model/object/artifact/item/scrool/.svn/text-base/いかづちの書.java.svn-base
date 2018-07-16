package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import main.res.SE;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.ThunderDamage;
import dangeon.view.anime.ThunderEffect;
import dangeon.view.detail.MainMap;

public class いかづちの書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "いかづちの書";
	public static final String item_name_sound = "いかつちのしょ";
	ENCHANT_SIMBOL SIM = ENCHANT_SIMBOL.槌;

	public いかづちの書(Point p) {
		super(p, item_name);
		sim = SIM;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "部屋内の敵にダメージを与える。ミニマップ上で青色の所が「部屋」である。なおチルノが通路にいる場合は周囲８マスの敵に効果が及ぶ。";
	}

	@Override
	public void scroolUse() {
		SE.LIGHTNING.play();
		MainMap.addEffect(new ThunderEffect(MassCreater.isPlayerInRoom()));
		for (Base_Enemy em : MapInSelect.getListRoomOrRoadInEnemy()) {
			ThunderDamage.thunderDamage(Player.me, Player.me, em, 25);
		}
	}
}
