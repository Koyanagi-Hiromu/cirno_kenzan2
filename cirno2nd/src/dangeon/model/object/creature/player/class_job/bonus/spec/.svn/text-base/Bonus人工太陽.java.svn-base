package dangeon.model.object.creature.player.class_job.bonus.spec;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.arrow.大砲の弾;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.Class人工太陽;
import dangeon.model.object.creature.player.class_job.bonus.BonusSpecialAction;

public class Bonus人工太陽 extends BonusSpecialAction {
	private static final long serialVersionUID = 1L;

	public Bonus人工太陽(Class人工太陽 clazz) {
		super(clazz, 2, 20, "核弾を発射する");
	}

	@Override
	public ActionCheck excuteSpecAction() {
		Message.set(Player.me.getColoredName(), "は核弾を発射した！");
		Player.me.setThrowing(new 大砲の弾(Player.me.getMassPoint(), false),
				Player.me.direction);
		return ActionCheck.SUCCESS;
	}
}
