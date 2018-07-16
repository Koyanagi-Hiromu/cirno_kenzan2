package dangeon.controller.listener.menu;

import main.Listener.ACTION;
import main.pad.JInputWrapper;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.DangeonScene;
import dangeon.controller.listener.menu.Conducter.PHASE;
import dangeon.model.config.Config;
import dangeon.view.detail.View_Pad;

public class Menu_Pad {

	private static int y = 0;
	private static boolean flag_sub = false;

	public static void actionKeyPressed(ACTION action) {
		switch (action) {
		case ENTER:
			if (y == getReset()) {
				reset();
			} else if (y == getEnd()) {
				finish();
			} else {
				// y = 0;
				// flag_sub = !flag_sub;
			}
			break;
		case CANCEL:
			y = getEnd();
			break;
		}
	}

	public synchronized static void arrowKeyPressed(DIRECTION direction) {
		switch (direction) {
		case UP:
			y--;
			break;
		case DOWN:
			y++;
			break;
		case RIGHT:
		case LEFT:
			// y = 0;
			// flag_sub = !flag_sub;
			// SE.SYSTEM_ARRANGEMENT.play();
			return;
		}
		if (y < 0) {
			y = getEnd();
		} else if (y > getEnd()) {
			y = 0;
		}
	}

	private static void finish() {
		View_Pad.finish();
		y = 0;
		DangeonScene.setScene(DangeonScene.DANGEON);
		Conducter.phase = Conducter.PHASE.FIRST;
	}

	public static ACTION[] getACTION() {
		return flag_sub ? ACTION.getSubCommands() : ACTION.getMainCommands();
	}

	private static int getEnd() {
		return getACTION().length + 2;
	}

	public static String getExp() {
		if (y < getACTION().length) {
			if (flag_sub) {
				return getACTION()[y].EXP + "のショートカットキー";
			} else {
				return getACTION()[y].EXP + "キー";
			}
		} else if (y == getReset() - 1) {
			return "ページを切り替えます";
		} else if (y == getReset()) {
			return "リセットしてすべてのキーを無効にします";
		} else {
			return "設定を終了します";
		}
	}

	private static int getReset() {
		return getACTION().length + 1;
	}

	public static int getY() {
		return y;
	}

	public static void init() {
		View_Pad.init();
		Conducter.phase = PHASE.パッド;
		JInputWrapper.resetLastButton();
	}

	public static boolean isMain() {
		return !flag_sub;
	}

	public static void keyPressed(int i) {
		if (y < getACTION().length) {
			if (Config.getPadKey(i) == getACTION()[y].KEY_NUM) {
				SE.SYSTEM_CANCEL.play();
				Config.setPadKey(i, -1);
			} else {
				SE.SYSTEM_ENTER.play();
				Config.setPadKey(i, getACTION()[y].KEY_NUM);
			}
		} else {
			actionKeyPressed(ACTION.ENTER);
		}
	}

	private static void reset() {
		SE.SYSTEM_ARRANGEMENT.play();
		for (int j = 0; j < JInputWrapper.buttonID.length; j++) {
			Config.setPadKey(j, -1);
		}
	}

}
