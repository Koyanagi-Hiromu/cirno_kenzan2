package dangeon.controller.listener.menu;

import main.Listener.ACTION;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.DangeonScene;
import dangeon.controller.listener.menu.Conducter.PHASE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.select.SelectBox_Stair;

public class FIRST {
	public static int x = 0, y = 0;
	private static final int X_LENGTH = 2, Y_LENGTH = 2;

	public static void actionKeyPressed(ACTION action) {
		switch (action) {
		case MENU:
			SE.SYSTEM_ENTER.play();
		case ENTER:
			switch (PHASE.get(x, y)) {
			case 足元:
				Base_Artifact a = Belongings.get(-1);
				if (a != null) {
					if (a instanceof Stairs) {
						DangeonScene.setScene(DangeonScene.DANGEON);
						new SelectBox_Stair((Stairs) a);
					} else {
						ITEM.setFoot();
						Conducter.phase = PHASE.道具;
					}
				} else {
					Message.set("特に何もない");
				}
				break;
			case 道具:
				if (Belongings.getSize() != 0) {
					ITEM.setInit();
					Conducter.phase = PHASE.道具;
				} else {
					Message.set("特に何も持っていない");
				}
				break;
			case 射撃:
				Conducter.phase = PHASE.射撃;
				break;
			case 情報:
				new Menu_Information();
			default:
				break;
			}
			break;
		case CANCEL:
			DangeonScene.setScene(DangeonScene.DANGEON);
			break;
		}
	}

	public static void arrowKeyPressed(DIRECTION direction) {
		switch (direction) {
		case UP:
			y--;
			break;
		case DOWN:
			y++;
			break;
		case LEFT:
			x--;
			break;
		case RIGHT:
			x++;
			break;
		}
		if (x < 0)
			x = 0;
		if (x > X_LENGTH - 1)
			x = X_LENGTH - 1;
		if (y < 0)
			y = 0;
		if (y > Y_LENGTH - 1)
			y = Y_LENGTH - 1;
	}
}
