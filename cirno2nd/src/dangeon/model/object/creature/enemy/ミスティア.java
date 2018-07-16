package dangeon.model.object.creature.enemy;

import java.awt.Point;

import main.res.SE;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.food.ナツメおにぎり;
import dangeon.model.object.artifact.item.scrool.混乱の書;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.view.anime.Special_Wait_FrameByFrame;

public class ミスティア extends Base_Enemy {

	private static final long serialVersionUID = 1L;

	public ミスティア(Point p, int Lv) {
		super(p, Lv);
	}

	private boolean check() {
		if (!player_is_in_sight)
			return false;
		if (LV == 4) {
			if (MassCreater.isPlayerInTheSameRoom(getMassPoint())) {
				return true;
			}
		}
		int length = LV;
		Point p = Player.me.getMassPoint().getLocation();
		Point e = getMassPoint().getLocation();
		for (int i = -length; i <= length; i++) {
			for (int j = -length; j <= length; j++) {
				if (p.x == e.x + i && p.y == e.y + j) {
					return true;
				}
			}
		}
		return false;
		// if (attack_possible()) {
		// return true;
		// }
		// return false;
	}

	private void effect() {
		SE.MISTHIA_VOISE.play();
		Message.set(getColoredName(), "は熱く歌った");
		setAnimation(new Special_Wait_FrameByFrame(
				this,
				SE.STATUS_PIYOPIYO,
				0,
				new Task() {
					/**
			 *
			 */
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						if (MassCreater.isSpotValid()) {
							if (Player.me.setCondition(CONDITION.混乱, 0)) {
								SE.STATUS_PIYOPIYO.play();
							}
						} else {
							CONDITION.conditionRecovery(Player.me, CONDITION.蛍);
							MassCreater.setSpot(true);
							Message.set("視界が悪くなった");
						}
					};
				}, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 0, 0, 0, 1, 1, 1, 2, 2,
				2, 3, 3, 3));
	}

	@Override
	public boolean enchantEnemySpecialAction() {
		MapInSelect.aroundInEnemyChengeCondition(CONDITION.混乱, 0);
		return true;
	}

	@Override
	protected Base_Artifact getDropItem() {
		return new R().is(50) ? new 混乱の書(mass_point) : new ナツメおにぎり(mass_point);
	}

	@Override
	protected boolean specialAttack() {
		if (check()) {
			if (MassCreater.isPlayerInTheSameRoom(getMassPoint())) {
				direction = converDirection(Player.me.getMassPoint());
				effect();
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean specialCheck() {
		if (isSpecialParcent()) {
			if (check()) {
				return true;
			}
		}
		return false;
	}

}
