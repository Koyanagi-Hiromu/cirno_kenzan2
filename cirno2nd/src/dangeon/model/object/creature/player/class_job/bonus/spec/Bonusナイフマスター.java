package dangeon.model.object.creature.player.class_job.bonus.spec;

import java.awt.Point;
import java.util.Random;

import javax.xml.ws.ResponseWrapper;

import main.res.SE;
import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.Classナイフマスター;
import dangeon.model.object.creature.player.class_job.bonus.BonusSpecialAction;
import dangeon.util.R;
import dangeon.view.anime.DoronEffect;
import dangeon.view.detail.MainMap;

public class Bonusナイフマスター extends BonusSpecialAction {
	private static final long serialVersionUID = 1L;

	public Bonusナイフマスター(Classナイフマスター classナイフマスター) {
		super(classナイフマスター, 2, 20, "3秒だけ時を止める");
	}

	@Override
	public ActionCheck excuteSpecAction() {
		int r = new Random().nextInt(5);
		if (r == 0)
		{
			Message.set("時よ止まりなさい!");			
		}
		else if (r == 1)
		{
			Message.set("時よ止まれ!");
		}
		else if (r == 2)
		{
			Message.set("ザ・ワールド！");
		}
		else if (r == 3)
		{
			Message.set("私だけの世界");
		}
		else
		{
			Message.set("私の時間よ");
		}

		int time = new R().is(25) ? 3 : 6;
		TurnSystemController.setTimeStop(time);
		return ActionCheck.SUCCESS;
	}
}
