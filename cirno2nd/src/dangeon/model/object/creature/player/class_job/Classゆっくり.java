package dangeon.model.object.creature.player.class_job;

import java.awt.Point;
import java.util.ArrayList;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.ゆっくりのカード;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;
import dangeon.model.object.creature.player.class_job.bonus.BonusEndTurnAction;
import dangeon.model.object.creature.player.class_job.bonus.BonusItem;
import dangeon.model.object.creature.player.class_job.bonus.BonusStatus;
import dangeon.model.object.creature.player.class_job.bonus.BonusSwitch;

public class Classゆっくり extends BaseClassJob {

	private static final long serialVersionUID = 1L;
	private int turn_count = 0;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> list, Point p) {
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> list, Point p) {
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusItem(new ゆっくりのカード(p)));
	}

	@Override
	protected void firstDemerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusStatus(this, CONDITION.鈍足));
		list.add(new BonusEndTurnAction(this, "鈍足状態でないと１００ターン後に鈍足", new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				if (!Player.me.conditionCheck(CONDITION.鈍足)) {
					if (++turn_count >= 100) {
						turn_count = 0;
						Message.set("動きがゆっくりになった");
						SE.STATUS_SLOW.play();
						Player.me.setCondition(CONDITION.鈍足, -2);
					}
				}
			}
		}));
	}

	@Override
	protected void firstMerite(ArrayList<BaseBonus> list, Point p) {
		list.add(new BonusSwitch(this, 1, "被ダメージ半減"));
	}

	@Override
	public SpellCard getRep() {
		return new ゆっくりのカード(new Point());
	}

	@Override
	public String getSwitchExp() {
		return "ゆっくりに倒された";
	}
}
