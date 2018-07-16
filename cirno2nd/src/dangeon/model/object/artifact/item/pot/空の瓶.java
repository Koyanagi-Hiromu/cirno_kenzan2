package dangeon.model.object.artifact.item.pot;

import java.awt.Point;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.util.R;
import dangeon.util.STAGE;

public class 空の瓶 extends Base_Pot {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 空の瓶(Point p) {
		this(p, 2 + (new R().nextInt(5) + 1) / 2);
	}

	public 空の瓶(Point p, int size) {
		super(p, "", size, STAGE.地霊殿);
	}

	@Override
	public boolean broken() {
		if (!isFrozen()) {
			staticCheck();
			Message.set(getColoredName(), "は爆発した");
			MapInSelect.explosion(getMassPoint());
		}
		return super.broken();
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "【注意書き】";
	}

	@Override
	public boolean itemUse() {
		if (isMax()) {
			Message.set("中身がいっぱいで使用できない！");
			return false;
		} else if (isFrozen()) {
			Message.set(getColoredName(), "凍っていて引力が発揮出来ない！");
			staticCheck();
			return false;
		} else {
			staticCheck();
			DIRECTION d = Player.me.getDirection();
			Point p = Player.me.getMassPoint().getLocation();
			do {
				final Base_Artifact a = MapList.getArtifact(p);
				if (a != null && a.isMobile()) {
					SE.WARP_INSTANT.play();
					Message.set(a.getColoredName(), "が引き寄せられた");
					a.setMassPoint_ParabolaJump_NoAttack(Player.me
							.getMassPoint().getLocation(), new Task() {
						private static final long serialVersionUID = 1L;

						@Override
						public void work() {
							if (isUnableToPutIn(a)) {
								Message.set(a.getColoredName(),
										"は魔法瓶の中には入らなかった");
								// setMax(MAX - 1);
							} else {
								SE.SYSTEM_PICKUP.play();
								MapList.removeArtifact(a);
								Message.set(a.getColoredName(), "が中に入った");
								LIST.add(a);
							}
						}
					});
					return true;
				}
				p.translate(d.X, d.Y);
			} while (MassCreater.getMass(p).WALKABLE);
			if (!isStaticCheked() && LIST.isEmpty()) {
				return emptyAction();
			} else {
				Message.set("引き寄せる対象がなかった");
				return false;
			}
		}
	}

}
