package dangeon.controller.listener.menu;

import main.Listener.ACTION;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.DangeonScene;
import dangeon.latest.scene.action.message.Message;

public class RECORDS {

	private static int y = 0;

	public static void actionKeyPressed(ACTION action) {
		y = 0;
		DangeonScene.setScene(DangeonScene.DANGEON);
		Conducter.phase = Conducter.PHASE.FIRST;
		if (action != ACTION.RECORD) {
			new Menu_Information();
		} else {
			SE.SYSTEM_CANCEL.play();
		}
	}

	public synchronized static void arrowKeyPressed(DIRECTION direction) {
		switch (direction) {
		case UP:
			y++;
			break;
		case DOWN:
			y--;
			break;
		}
		if (y < 0) {
			y = 0;
			// TODO 効果音
		} else if (y > Message.MAX_RECORD) {
			y = Message.MAX_RECORD;
			// TODO 効果音
		}
	}

	public synchronized static int getY() {
		return y;
	}

}
