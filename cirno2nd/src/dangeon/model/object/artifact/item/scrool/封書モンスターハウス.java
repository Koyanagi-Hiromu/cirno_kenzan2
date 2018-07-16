package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import main.res.SE;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.TurnSystemController;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.SpecialRoom;
import dangeon.model.object.Base_MapObject;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.action.SpecialAction;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;

public class 封書モンスターハウス extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "封書「モンスターハウス」";
	public static final String item_name_sound = "ふうしょもんすたーはうす";

	public 封書モンスターハウス(Point p) {
		super(p, item_name);
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "モンスターハウス化";
	}

	@Override
	public void scroolUse() {
		if (!PresentField.get().isDungeon()) {
			Message.set("不思議なちからによってかき消された");
			return;
		}
		SE.STATUS_PIYOPIYO.play();
		for (Base_MapObject o : MapInSelect.getListRoomInObject(Player.me
				.getMassPoint().getLocation())) {
			if (o instanceof Base_Enemy) {
				TaskOnMapObject.addEnemyRemoveTask((Base_Creature) o);
				if (TurnSystemController.isBoss((Base_Enemy) o))
					Medal.百鬼夜行抄を使用してボスを消した.addCount();
			} else if (o instanceof Base_Item) {
				TaskOnMapObject.addItemRemoveTask((Base_Artifact) o);
			} else if (o instanceof Base_Trap) {
				TaskOnMapObject.addItemRemoveTask((Base_Artifact) o);
			}
		}
		Task t = new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Point p = Player.me.getMassPoint().getLocation();
				SpecialRoom.setMonsterHouseThisRoom(MassCreater.getRoom(p));
			}
		};
		if (MassCreater.getMass(Player.me.getMassPoint()).ROAD) {
			SpecialAction.高飛び(Player.me, t);
		} else {
			t.work_appointment();
		}
	}
}
