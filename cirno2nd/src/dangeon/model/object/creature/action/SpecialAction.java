package dangeon.model.object.creature.action;

import java.awt.Point;

import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;

public class SpecialAction {
	public static void 高飛び(Base_Creature c) {
		if (!PresentField.get().isHaraheru()
				&& !MassCreater.getMass(c.getMassPoint()).WATER) {
			Message.set("不思議なちからが働いて効果が打ち消された");
			return;
		}
		if (c instanceof Player) {
			if (Player.me.isGray()) {
				if (MassCreater.getIP().getRoomList().size() == 2) {
					Medal.ニ部屋しかないマップで高飛びして泥棒した.addCount();
				}
			}
			Player.me.setMassPoint_Jump(MassCreater.getWarpPoint(null));
		} else {
			c.setMassPoint_Jump(MassCreater.getWarpPoint((Base_Enemy) c));
		}
	}

	public static void 高飛び(Base_Creature c, Task task) {
		if (!PresentField.get().isHaraheru()) {
			Message.set("不思議なちからが働いて効果が打ち消された");
			return;
		}
		if (c instanceof Player) {
			Player.me.setMassPoint_Jump(MassCreater.getWarpPoint(null), task);
		} else {
			c.setMassPoint_Jump(MassCreater.getWarpPoint((Base_Enemy) c));
		}
	}

	public static void 場所替え(Base_Creature c, Base_Creature c2) {
		if (!MassCreater.getMass(c.getMassPoint()).WALKABLE
				|| !MassCreater.getMass(c2.getMassPoint()).WALKABLE) {
			return;
		}
		Point p = new Point(c.getMassPoint().getLocation());
		c.setMassPoint(c2.getMassPoint());
		c2.setMassPoint(p);
	}
}
