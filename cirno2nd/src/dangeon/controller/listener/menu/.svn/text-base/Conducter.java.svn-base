package dangeon.controller.listener.menu;

import main.Listener.ACTION;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.DangeonScene;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.creature.player.Belongings;
import dangeon.view.detail.View_Simbols;

public class Conducter {
	public enum PHASE {
		FIRST(-1, -1), 道具(0, 0), 情報(1, 1), 射撃(1, 0), 足元(0, 1), 履歴(-1, -1), 印(
				-1, -1), パッド(-1, -1), VOL(-1, -1);
		public static PHASE get(int x, int y) {
			for (PHASE p : PHASE.values()) {
				if (p.x == x && p.y == y)
					return p;
			}
			return null;
		}

		private int x, y;

		private PHASE(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean isCurrentPhase() {
			return equals(getPhase());
		}

		@Override
		public String toString() {
			if (this == 足元) {
				Base_Artifact a = Belongings.get(-1);
				if (a != null && a instanceof Stairs) {
					if (a.isVisible()) {
						return "階段";
					} else {
						return "出口";
					}
				}
			}
			return super.toString();
		}
	}

	public static PHASE phase = PHASE.FIRST;

	public static void actionKeyPressed(ACTION action) {
		playSE(action);
		if (DangeonScene.GOUSEI.isPresentScene()) {
			Gousei.actionKeyPressed(action);
			return;
		} else if (DangeonScene.RESULT.isPresentScene()) {
			Menu_Result.actionKeyPressed(action);
		} else if (DangeonScene.SELECT_ITEM.isPresentScene()) {
			Base_MenuItem.getNow().actionKeyPressed(action);
			return;
		} else if (DangeonScene.ENCHANT.isPresentScene()) {
			ENCHANT.actionKeyPressed(action);
			return;
		} else {
			switch (phase) {
			case FIRST:
				FIRST.actionKeyPressed(action);
				break;
			case 射撃:
				ArrowSet.actionKeyPressed(action);
				break;
			case 情報:
				break;
			case 履歴:
				RECORDS.actionKeyPressed(action);
				break;
			case 足元:
			case 道具:
				ITEM.actionKeyPressed(action);
				break;
			case 印:
				DangeonScene.setScene(DangeonScene.DANGEON);
				Conducter.phase = Conducter.PHASE.FIRST;
				if (action != ACTION.SYMBOL) {
					new Menu_Information();
				} else {
					SE.SYSTEM_CANCEL.play();
				}
				break;
			case パッド:
				Menu_Pad.actionKeyPressed(action);
				break;
			case VOL:
				Menu_Volume.actionKeyPressed(action);
				break;
			}

		}

	}

	public static void arrowKeyPressed(DIRECTION direction) {
		SE.SYSTEM_CURSOR.play();
		if (DangeonScene.ENCHANT.isPresentScene()) {
			ENCHANT.arrowKeyPressed(direction);
			return;
		} else if (DangeonScene.RESULT.isPresentScene()) {
			Menu_Result.arrowKeyPressed(direction);
		} else if (DangeonScene.SELECT_ITEM.isPresentScene()) {
			Base_MenuItem.getNow().arrowKeyPressed(direction);
		} else {
			switch (phase) {
			case FIRST:
				FIRST.arrowKeyPressed(direction);
				break;
			case 射撃:
				ArrowSet.actionKeyPressed(direction);
				break;
			case 情報:
				break;
			case 履歴:
				RECORDS.arrowKeyPressed(direction);
				break;
			case 足元:
			case 道具:
				ITEM.arrowKeyPressed(direction);
				break;
			case 印:
				View_Simbols.arrowkeyPressed(direction);
				break;
			case パッド:
				Menu_Pad.arrowKeyPressed(direction);
				break;
			case VOL:
				Menu_Volume.arrowKeyPressed(direction);
				break;
			}
		}
	}

	public static PHASE getPhase() {
		return phase;
	}

	private static void playSE(ACTION action) {
		switch (action) {
		case ENTER:
			SE.SYSTEM_ENTER.play();
			break;
		case CANCEL:
			SE.SYSTEM_CANCEL.play();
			break;
		}
	}

	static void resetGousei() {
		DangeonScene.setScene(DangeonScene.DANGEON);
		phase = PHASE.FIRST;

	}

	public static void setGousei() {
		DangeonScene.setScene(DangeonScene.GOUSEI);
		phase = PHASE.道具;
	}
}
