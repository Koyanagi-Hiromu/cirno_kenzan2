package dangeon.model.object.artifact.device;

import java.awt.Point;

import main.res.Image_Artifact;
import main.util.BlackOut;
import main.util.DIRECTION;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.Base_Map;
import dangeon.model.object.creature.player.Player;

public class HiddenMovePoint extends HiddenDevice {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final Base_Map BFM;
	private final Point PLAYER;
	private final DIRECTION D;

	private Point walk = null;

	public HiddenMovePoint(int x, int y, Base_Map bfm, DIRECTION d) {
		super(new Point(x, y));
		IM = Image_Artifact.STAIRS;
		BFM = bfm;
		PLAYER = bfm.getEntrancePoint();
		D = d;
	}

	public HiddenMovePoint(int x, int y, Base_Map bfm, Point player, DIRECTION d) {
		super(new Point(x, y));
		IM = Image_Artifact.STAIRS;
		BFM = bfm;
		PLAYER = player;
		D = d;
	}

	private void effect() {
		new BlackOut(BFM, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				TaskOnMapObject.setNewMap(new MassCreater(BFM, PLAYER, false));
				Player.me.setDirection(D);
			}
		});
	}

	public HiddenMovePoint setMovePoint(Point p) {
		walk = p;
		return this;
	}

	@Override
	public boolean walkOnAction() {
		if (walk == null) {
			effect();
		} else {
			Player.me.setMassPoint_WalkLike(walk, 1, new Task() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					effect();
				}
			});
		}
		return true;
	}

}
