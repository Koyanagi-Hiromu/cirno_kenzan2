package dangeon.controller.listener.menu;

import main.Second_Firster;
import main.constant.FR;
import main.thread.MainThread;
import dangeon.controller.DangeonScene;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.config.Config;

public class Menu_Config extends Base_SelectBox {
	private static String getBGMFix() {
		if (Config.isFixBGM()) {
			return "カード装備時にＢＧＭを変更する";
		} else {
			return "カード装備時にＢＧＭを変更しない";
		}
	}

	private static Object getCutIN() {
		StringBuilder sb = new StringBuilder();
		if (Config.isCutIN()) {
			sb.append("カードのカットインをＯＦＦにする");
		} else {
			sb.append("カードのカットインをＯＮにする");
		}
		return sb.toString();
	}

	private static String[] getMsg() {
		StringBuilder sb = new StringBuilder();
		sb.append("音楽のボリュームを変更する");
		sb.append("@");
		sb.append("ゲームパッドを割り当てる");
		sb.append("@");
		sb.append(getBGMFix());
		sb.append("@");
		sb.append(getCutIN());
		sb.append("@");
		sb.append(getStringSize());
		sb.append("@");
		sb.append(getStringFullScreen());
		sb.append("@");
		sb.append(getStringMiniMap());
		sb.append("@");
		sb.append(getTips());
		sb.append("@");
		sb.append(FR.getStringSpeed());
		sb.append("@");
		return sb.toString().split("@");
	}

	private static String getStringFullScreen() {
		StringBuilder sb = new StringBuilder();
		if (Second_Firster.ME.isFullScreen()) {
			sb.append("フルスクリーンを解除する");
		} else {
			sb.append("フルスクリーンで表示する");
		}
		return sb.toString();
	}

	private static String getStringMiniMap() {
		StringBuilder sb = new StringBuilder();
		if (Config.isMini_map_on()) {
			sb.append("ミニマップの表示をＯＦＦにする");
		} else {
			sb.append("ミニマップの表示をＯＮにする");
		}
		return sb.toString();
	}

	private static String getStringSize() {
		if (Second_Firster.ME.isFullScreen()) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		if (Second_Firster.ME.isDoubledScreen()) {
			sb.append("画面サイズを等倍表示する");
		} else {
			sb.append("画面サイズを２倍表示する（推奨）");
		}
		return sb.toString();
	}

	private static String getTips() {
		StringBuilder sb = new StringBuilder();
		if (Config.isKey_ex()) {
			sb.append("KeyTipsの表示をＯＦＦにする");
		} else {
			sb.append("KeyTipsの表示をＯＮにする");
		}
		return sb.toString();
	}

	boolean flag_return_menu = false;

	public Menu_Config() {
		super(false, getMsg());
	}

	@Override
	protected DangeonScene endScene() {
		if (flag_return_menu) {
			return DangeonScene.MENU;
		} else {
			return DangeonScene.DANGEON;
		}
	}

	@Override
	public void pushCancel() {
		end();
		new Menu_Information();
	}

	@Override
	public void pushEnter(int y) {
		if (y == 0) {
			flag_return_menu = true;
			Menu_Volume.init();
		} else if (y == 1) {
			flag_return_menu = true;
			Menu_Pad.init();
		} else if (y == 2) {
			Config.switchBgm();
			if (Config.isFixBGM()) {
				Message.set("BGMを現在の曲に固定しました$装備を変更してもＢＧＭを維持します");
			} else {
				Message.set("BGMの固定を解除しました$新しく装備を変更してください");
			}
		} else if (y == 3) {
			Config.switchCutIN();
			Message.set("カードのカットインの表示設定を変更しました");
		} else if (y == 4) {
			MainThread.setSwitchSizeTask();
		} else if (y == 5) {
			MainThread.setSwitchFullScreenTask();
		} else if (y == 6) {
			Config.switchMini_map_on();
			Message.set("ミニマップの表示設定を変更しました");
		} else if (y == 7) {
			Config.switchKey_ex();
			Message.set("Tipsの表示設定を変更しました");
		} else if (y == 8) {
			FR.changeSleep();
		}
		end();
	}
}