package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.player.Player;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class 天邪鬼の書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "天邪鬼の書";
	public static final String item_name_sound = "あまのしゃくのしょ";

	public static boolean aroundInEnemyChengeCondition(CONDITION c, int time) {
		boolean flag = false;
		for (Base_Creature em : MapList.getListAroundCreature(Player.me
				.getMassPoint())) {
			flag = true;
			MainMap.addEffect(new DoronEffect(em.getMassPoint(), null, true,
					false), false);
			em.setCondition(c, time);
		}
		return flag;
	}

	public 天邪鬼の書(Point p) {
		super(p, item_name);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "天邪鬼状態になると、つい後ろを攻撃してしまう";
	}

	@Override
	public void scroolUse() {
		SE.AMANOJACK.play();
		if (aroundInEnemyChengeCondition(CONDITION.天邪鬼, 0)) {
			Message.set("周囲の敵はあまのじゃくな気持ちになった");
		} else {
			Message.set("周囲に誰もいなかった");
		}
	}
}
