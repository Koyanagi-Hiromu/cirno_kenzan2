package dangeon.model.object.creature.player.class_job.bonus.spec;

import java.awt.Point;

import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.Class風水師;
import dangeon.model.object.creature.player.class_job.bonus.BonusSpecialAction;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class Bonus風水師 extends BonusSpecialAction {
	private static final long serialVersionUID = 1L;

	public Bonus風水師(Class風水師 class風水師) {
		super(class風水師, 2, 20, "目の前に必ず水場を作る");
	}

	@Override
	public ActionCheck excuteSpecAction() {
		Point p = Player.me.getDirection().getFrontPoint(
				Player.me.getMassPoint().getLocation());
		if (MassCreater.getMass(p).WALKABLE && !MassCreater.getMass(p).WATER) {
			MainMap.addEffect(new DoronEffect(p, null, true, false), false);
			SE.ATTACK_WATER.play();
			MassCreater.getMass(p).setWater(true);
			MassCreater.retakeMassSet();
			return ActionCheck.SUCCESS;
		} else {
			if (!MassCreater.getMass(p).WALKABLE) {
				Message.set("壁には作れんぞ@");
			} else if (MassCreater.getMass(p).WATER) {
				Message.set("そこはもう水があるぞ@");
			}
			return ActionCheck.FAILURE;
		}
	}
}
