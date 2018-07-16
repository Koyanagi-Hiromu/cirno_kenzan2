package dangeon.model.object.creature.player.class_job.bonus.spec;

import java.awt.Point;

import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.Classフラワーマスター;
import dangeon.model.object.creature.player.class_job.bonus.BonusSpecialAction;
import dangeon.util.Damage;
import dangeon.view.anime.MasterSpark;

public class Bonusフラワーマスター extends BonusSpecialAction {
	private static final long serialVersionUID = 1L;

	public Bonusフラワーマスター(Classフラワーマスター clazz) {
		super(clazz, 2, 20, "マスタースパークを放つ");
	}

	@Override
	public ActionCheck excuteSpecAction() {
		Message.set(Player.me.getColoredName(), "はマスタースパークを放った！");
		Player.me.setSecondAnimation(new MasterSpark(Player.me, new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Point p = Player.me.getMassPoint().getLocation();
				DIRECTION d = Player.me.getDirection();
				while (true) {
					p.x += d.X;
					p.y += d.Y;
					if (MassCreater.isPointOutOfDangeon(p)) {
						break;
					}
					if (!MassCreater.getMass(p).WALKABLE) {
						MassCreater.dig(p, true);
					}
					if (MapList.getEnemy(p) != null) {
						Damage.damage(null, null, null, Player.me,
								MapList.getEnemy(p), 50);
					}
				}
				MassCreater.retakeMassSet();
			}
		}));
		return ActionCheck.SUCCESS;
	}
}
