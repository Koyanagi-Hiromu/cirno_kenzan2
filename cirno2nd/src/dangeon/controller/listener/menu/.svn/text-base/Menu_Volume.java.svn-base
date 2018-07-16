package dangeon.controller.listener.menu;

import main.Listener.ACTION;
import main.pad.JInputWrapper;
import main.res.BGM;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.DangeonScene;
import dangeon.controller.listener.menu.Conducter.PHASE;
import dangeon.model.config.Config;
import dangeon.view.detail.View_Volume;

public class Menu_Volume {

	private static int y = 0;
	private static boolean flag_sub = false;

	private static int bgm_vol, se_vol;

	public static void actionKeyPressed(ACTION action) {
		switch (action) {
		case SOUND:
			SE.SYSTEM_CANCEL.play();
			finish();
			break;
		case ENTER:
			if (y == 2) {
				finish();
				new Menu_Config();
			}
			break;
		case CANCEL:
			y = 2;
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
			if (y == 0) {
				bgm_vol += direction.X;
				if (bgm_vol < 0) {
					bgm_vol = 0;
				} else if (bgm_vol > BGM.VOL_MAX) {
					bgm_vol = BGM.VOL_MAX;
				}
				BGM.setVol(bgm_vol);
			} else if (y == 1) {
				se_vol += direction.X;
				if (se_vol < 0) {
					se_vol = 0;
				} else if (se_vol > SE.VOL_MAX) {
					se_vol = SE.VOL_MAX;
				}
				SE.setVol(se_vol);
			}
			return;
		}
		if (y < 0) {
			y = 2;
		} else if (y > 2) {
			y = 0;
		}
	}

	private static void finish() {
		View_Volume.finish();
		y = 0;
		Config.setBGMVol(bgm_vol);
		Config.setSEVol(se_vol);
		DangeonScene.setScene(DangeonScene.DANGEON);
		Conducter.phase = Conducter.PHASE.FIRST;
	}

	public static ACTION[] getACTION() {
		return flag_sub ? ACTION.getSubCommands() : ACTION.getMainCommands();
	}

	public static int getBGMVol() {
		return bgm_vol;
	}

	public static int getSEVol() {
		return se_vol;
	}

	public static int getY() {
		return y;
	}

	public static void init() {
		View_Volume.init();
		bgm_vol = Config.getBGMVol();
		se_vol = Config.getSEVol();
		Conducter.phase = PHASE.VOL;
		JInputWrapper.resetLastButton();
	}
}
