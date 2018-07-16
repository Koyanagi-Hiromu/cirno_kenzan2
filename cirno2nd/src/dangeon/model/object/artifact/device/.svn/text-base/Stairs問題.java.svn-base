package dangeon.model.object.artifact.device;

import java.awt.Point;

import main.util.BlackOut;
import main.util.DIRECTION;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.model.config.Config;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.Base_Map;
import dangeon.model.object.creature.player.Player;

public class Stairs問題 extends Stairs {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final Point P_P;
	private final DIRECTION D;
	private final int STORY;

	public Stairs問題(Point p, Base_Map bm, Point player_point, DIRECTION d,
			int story) {
		super(p, bm);
		D = d;
		P_P = player_point;
		STORY = story;
	}

	@Override
	public void move() {
		// if (sakuya()) {
		// return;
		// }
		MapList.setFlagSheef(false);
		if (Config.getStory() < STORY) {
			Config.setStory(STORY);
		}
		new BlackOut(bm, new Task() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				TaskOnMapObject.setNewMap(new MassCreater(bm, P_P, false));

				Player.me.resetAll();
				Player.me.setDirection(D);
			}
		});

	}
}
