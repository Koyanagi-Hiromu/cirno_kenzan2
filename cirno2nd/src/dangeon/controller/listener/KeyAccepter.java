package dangeon.controller.listener;

import java.awt.Point;
import java.awt.event.KeyEvent;

import main.Base_KeyAccepter;
import main.Listener;
import main.Listener.ACTION;
import main.constant.FR;
import main.res.SE;
import main.thread.MainThread;
import main.util.DIRECTION;
import dangeon.controller.DangeonScene;
import dangeon.controller.MainSystem;
import dangeon.controller.TurnSystemController;
import dangeon.controller.listener.menu.Conducter;
import dangeon.controller.listener.menu.Conducter.PHASE;
import dangeon.controller.listener.menu.FIRST;
import dangeon.controller.listener.menu.ITEM;
import dangeon.controller.listener.menu.Menu_Select;
import dangeon.controller.listener.menu.Menu_Volume;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.Config;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.EnchantArrow;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.hold_enemy.HoldEnemy;

public class KeyAccepter extends Base_KeyAccepter {

	private static int time = 0;
	private static final int INTERVAL = 50;
	private static boolean dash_button_pushing = false;
	private static boolean player_direction_pushing = false;
	private static boolean bias_button_pushing = false,
			bias_button_push_twice_over = false;
	private static boolean leftKey_pushing = false, downKey_pushing = false,
			rightKey_pushing = false, upKey_pushing = false;

	private static int direction_holding_frame = 0;

	private static boolean name_pushing = false;

	private static final int DIRECTION_HOLDING_FRAME = 5;

	private static boolean request_release_UPkey, request_release_DOWNkey,
			request_release_LEFTkey, request_release_RIGHTkey;

	private static boolean isAnyReleaseRequest() {
		if (request_release_UPkey) {
			return true;
		}
		if (request_release_DOWNkey) {
			return true;
		}
		if (request_release_LEFTkey) {
			return true;
		}
		if (request_release_RIGHTkey) {
			return true;
		}
		return false;
	}

	public static boolean isBiasButtonPushing() {
		return bias_button_pushing;
	}

	public static boolean isDirecting() {
		return player_direction_pushing;
	}

	public static boolean isDushing() {
		return dash_button_pushing;
	}

	public static boolean isNamePushing() {
		return name_pushing;
	}

	/**
	 * 
	 * @param action
	 * @return actionを一度しか受け付けないならtrue 押しっぱなし有効ならfalse
	 */
	public static boolean playerAction(ACTION action) {
		switch (DangeonScene.getPresentScene()) {
		case DANGEON:
			return playerAction_Dangeon(action);
		case SELECT:
			Menu_Select.actionKeyPressed(action);
			break;
		case GOUSEI:
		case SELECT_ITEM:
		case MENU:
		case ENCHANT:
		case RESULT:
			Conducter.actionKeyPressed(action);
			break;
		default:
			break;
		}
		return true;
	}

	private static boolean playerAction_Dangeon(ACTION action) {
		if (!MainSystem.isKeyAccept()) {
			return true;
		}
		if (action != ACTION.BIAS) {
			setBias_button_push_twice_over(false);
		}
		switch (action) {
		case BIAS:
			if (!bias_button_pushing) {
				if (!bias_button_push_twice_over) {
					setBias_button_push_twice_over(true);
				} else {
					DIRECTION d = Player.me.getDirection();
					if (d.ROTATE % 2 == 0) {
						Player.me.setDirection(d.getNeiboringDirection());
					} else {
						Player.me.setDirection(d.getNeiboringDirection()
								.getNeiboringDirection());
					}
				}
			}
			bias_button_pushing = true;
			break;
		case NAME:
			name_pushing = true;
			break;
		case TURN:

			if (!player_direction_pushing) {
				player_direction_pushing = true;
				direction_holding_frame = 0;
				// ボタンの押し始めは隣接エネミーがいる方向を向く
				Point p = Player.me.getMassPoint();
				DIRECTION _d = Player.me.getDirection();
				Base_Enemy e = MapList.getEnemy(p.x + _d.X, p.y + _d.Y);
				if (e != null) {
					int rotate = _d.ROTATE + 1;
					for (int i = 0; i < 8; i++, rotate++) {
						DIRECTION d = DIRECTION.getDirectionFromRotate(rotate);
						if (MapList.getEnemy(p.x + d.X, p.y + d.Y) != null) {
							Player.me.setDirection(d);
							break;
						}
					}
				} else {
					for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
						if (MapList.getEnemy(p.x + d.X, p.y + d.Y) != null) {
							Player.me.setDirection(d);
							break;
						}
					}
				}
			}
			break;
		case CANCEL:
			dash_button_pushing = (!KeyHolder.isContraining(ACTION.ENTER));
			return false;
		case MAP:
		case OWATTA:
			return false;
		}
		if (KeyHolder.isContraining(ACTION.MAP)) {
			return true;
		}
		switch (action) {
		case ENTER:
			if (KeyHolder.isContraining(ACTION.CANCEL)) {
				TurnSystemController.callMeToStartEnemyTurn();
				return false;
			}
			if (Player.me.normalAttack()) {
				TurnSystemController.callMeToStartEnemyTurn();
			}
			break;
		case RECORD:
			SE.SYSTEM_MENU.play();
			SE.mute(true);
			playerAction(ACTION.MENU);
			playerArrow(DIRECTION.DOWN);
			playerArrow(DIRECTION.RIGHT);
			playerAction(ACTION.ENTER);
			playerAction(ACTION.ENTER);
			SE.mute(false);
			break;
		case INFO:
			SE.SYSTEM_MENU.play();
			SE.mute(true);
			playerAction(ACTION.MENU);
			playerArrow(DIRECTION.DOWN);
			playerArrow(DIRECTION.RIGHT);
			playerAction(ACTION.ENTER);
			SE.mute(false);
			break;
		case SYMBOL:
			SE.SYSTEM_MENU.play();
			SE.mute(true);
			playerAction(ACTION.MENU);
			playerArrow(DIRECTION.DOWN);
			playerArrow(DIRECTION.RIGHT);
			playerAction(ACTION.ENTER);
			playerArrow(DIRECTION.DOWN);
			playerAction(ACTION.ENTER);
			SE.mute(false);
			break;
		case BGM:
			Config.switchBgm();
			if (Config.isFixBGM()) {
				Message.set("BGMを現在の曲に固定しました$装備を変更してもＢＧＭを維持します");
			} else {
				Message.set("BGMの固定を解除しました$新しく装備を変更してください");
			}
			break;
		case SOUND:
			SE.SYSTEM_MENU.play();
			DangeonScene.MENU.setPresentScene();
			Menu_Volume.init();
			break;
		case WINDOW:
			MainThread.setSwitchFullScreenTask();
			break;
		case GATHER:
			Base_Artifact a = Belongings.get(-1);
			if (a != null) {
				if (!a.isMobile()) {
					playerAction(ACTION.MENU);
					playerArrow(DIRECTION.DOWN);
					playerAction(ACTION.ENTER);
				} else {
					boolean flag_success = (Belongings.getSize() < Belongings
							.getMax());
					if (a instanceof Arrow) {
						Arrow arr = Belongings.getArrow(a.getClass());
						if (arr != null) {
							flag_success = true;
						}
					}
					Belongings.get(-1).itemPickUp();
					if (flag_success) {
						TurnSystemController.callMeToStartEnemyTurn();
					}
				}
			} else {
				Message.set("足元には特に何もない");
			}
			break;
		case ITEM:
			if (Belongings.getSize() != 0) {
				ITEM.setInit();
				Conducter.phase = PHASE.道具;
				while (!Message.isTaskEmpty()) {
					Message.getTask().upDate();
				}
				SE.SYSTEM_ENTER.play();
				FIRST.x = 0;
				FIRST.y = 0;
				DangeonScene.setScene(DangeonScene.MENU);
			} else {
				Message.set("特に何も持っていない");
			}
			break;
		case MENU:
			while (!Message.isTaskEmpty()) {
				Message.getTask().upDate();
			}
			SE.SYSTEM_MENU.play();
			FIRST.x = 0;
			FIRST.y = 0;
			DangeonScene.setScene(DangeonScene.MENU);
			break;
		case FIRE:
			if (HoldEnemy.ME.specialAction()) {
				break;
			}
			if (EnchantArrow.getArrow() == null) {
				Message.set("何もセットしていない");
			} else {
				Arrow arrow = Belongings
						.getArrow(EnchantArrow.getArrow().CLAZZ);
				if (arrow == null) {
					Message.set(EnchantArrow.getArrow().name(), "を持っていない");
				} else {
					if (arrow.itemUse()) {
						TurnSystemController.callMeToStartEnemyTurn();
					}
				}
			}
			break;
		case TEST3:
			HoldEnemy.ME.throwEnemy();
			break;
		case TEST2:
			HoldEnemy.ME.specialAction();
			break;
		case TEST:
			HoldEnemy.ME.hold();
			break;
		case FUCKIN_MAP:
			dangeon.model.map.MassCreater.saveFuckinMap();
		}
		return true;
	}

	private static void playerArrow(DIRECTION direction) {
		if (direction == null) {
			return;
		}
		if (!DangeonScene.SELECT.isPresentScene()
				&& Message.isDemandToWaitPushingAnyKey()) {
			return;
		}
		if (isAnyReleaseRequest()) {
			return;
		}
		switch (DangeonScene.getPresentScene()) {
		case DANGEON:
			switch (direction) {
			case UP:
				upKey_pushing = true;
				break;
			case DOWN:
				downKey_pushing = true;
				break;
			case LEFT:
				leftKey_pushing = true;
				break;
			case RIGHT:
				rightKey_pushing = true;
				break;
			case DOWN_LEFT:
				downKey_pushing = true;
				leftKey_pushing = true;
				break;
			case DOWN_RIGHT:
				downKey_pushing = true;
				rightKey_pushing = true;
				break;
			case UP_LEFT:
				upKey_pushing = true;
				leftKey_pushing = true;
				break;
			case UP_RIGHT:
				upKey_pushing = true;
				rightKey_pushing = true;
				break;
			}
			break;
		case SELECT:
			Menu_Select.arrowKeyPressed(direction);
			break;
		case MENU:
		case SELECT_ITEM:
		case GOUSEI:
		case ENCHANT:
		case RESULT:
			if (!MainSystem.isKeyAccept())
				return;
			Conducter.arrowKeyPressed(direction);
			break;
		default:
			break;
		}
	}

	public static void setBias_button_push_twice_over(boolean b) {
		KeyAccepter.bias_button_push_twice_over = b;
	}

	public static void setMovingKeyFalse() {
		upKey_pushing = false;
		downKey_pushing = false;
		leftKey_pushing = false;
		rightKey_pushing = false;
	}

	public static void setRequestReleasePushingKey() {
		request_release_UPkey = upKey_pushing;
		request_release_DOWNkey = downKey_pushing;
		request_release_LEFTkey = leftKey_pushing;
		request_release_RIGHTkey = rightKey_pushing;
		KeyAccepter.setMovingKeyFalse();
	}

	public static void setTimeZero() {
		time = 0;
	}

	public static void upDate() {
		if (!upKey_pushing && !downKey_pushing && !rightKey_pushing
				&& !leftKey_pushing) {
			time = 0;
			return;
		}
		time += FR.THREAD_SLEEP;
		if (!MainSystem.isKeyAccept()) {
			return;
		}
		if (player_direction_pushing || KeyHolder.isContraining(ACTION.MAP)) {
			if (leftKey_pushing && upKey_pushing) {
				Player.me.direction = DIRECTION.UP_LEFT;
				direction_holding_frame = DIRECTION_HOLDING_FRAME;
			} else if (leftKey_pushing && downKey_pushing) {
				Player.me.direction = DIRECTION.DOWN_LEFT;
				direction_holding_frame = DIRECTION_HOLDING_FRAME;
			} else if (rightKey_pushing && upKey_pushing) {
				Player.me.direction = DIRECTION.UP_RIGHT;
				direction_holding_frame = DIRECTION_HOLDING_FRAME;
			} else if (rightKey_pushing && downKey_pushing) {
				Player.me.direction = DIRECTION.DOWN_RIGHT;
				direction_holding_frame = DIRECTION_HOLDING_FRAME;
			} else {
				if (bias_button_pushing) {
					return;
				}
				if (direction_holding_frame > 0) {
					direction_holding_frame--;
					return;
				}
				if (leftKey_pushing) {
					Player.me.direction = DIRECTION.LEFT;
				} else if (rightKey_pushing) {
					Player.me.direction = DIRECTION.RIGHT;
				} else if (upKey_pushing) {
					Player.me.direction = DIRECTION.UP;
				} else if (downKey_pushing) {
					Player.me.direction = DIRECTION.DOWN;
				}
			}
			return;
		}
		if (KeyHolder.isContraining(ACTION.MAP)) {
			return;
		}
		if (leftKey_pushing && upKey_pushing) {
			Player.me.move(DIRECTION.UP_LEFT);
			return;
		} else if (leftKey_pushing && downKey_pushing) {
			Player.me.move(DIRECTION.DOWN_LEFT);
			return;
		} else if (rightKey_pushing && upKey_pushing) {
			Player.me.move(DIRECTION.UP_RIGHT);
			return;
		} else if (rightKey_pushing && downKey_pushing) {
			Player.me.move(DIRECTION.DOWN_RIGHT);
			return;
		}
		if (bias_button_pushing)
			return;
		if (time < INTERVAL)
			return;
		if (leftKey_pushing) {
			Player.me.move(DIRECTION.LEFT);
		} else if (rightKey_pushing) {
			Player.me.move(DIRECTION.RIGHT);
		} else if (upKey_pushing) {
			Player.me.move(DIRECTION.UP);
		} else if (downKey_pushing) {
			Player.me.move(DIRECTION.DOWN);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		playerArrow(Listener.getAllow_map().get(e.getKeyCode()));
		KeyHolder.setTaskKeyAction(Listener.getKey().get(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		DIRECTION allow = Listener.getAllow_map().get(e.getKeyCode());
		if (allow != null) {
			switch (allow) {
			case UP:
				request_release_UPkey = upKey_pushing = false;
				break;
			case DOWN:
				request_release_DOWNkey = downKey_pushing = false;
				break;
			case LEFT:
				request_release_LEFTkey = leftKey_pushing = false;
				break;
			case RIGHT:
				request_release_RIGHTkey = rightKey_pushing = false;
				break;
			case DOWN_LEFT:
				request_release_DOWNkey = downKey_pushing = false;
				request_release_LEFTkey = leftKey_pushing = false;
				break;
			case DOWN_RIGHT:
				request_release_DOWNkey = downKey_pushing = false;
				request_release_RIGHTkey = rightKey_pushing = false;
				break;
			case UP_LEFT:
				request_release_UPkey = upKey_pushing = false;
				request_release_LEFTkey = leftKey_pushing = false;
				break;
			case UP_RIGHT:
				request_release_UPkey = upKey_pushing = false;
				request_release_RIGHTkey = rightKey_pushing = false;
				break;
			}
		} else {
			ACTION action = Listener.getKey().get(e.getKeyCode());
			if (action == null) {
				return;
			}
			KeyHolder.removeTaskKeyAction(action);
			switch (action) {
			case BIAS:
				bias_button_pushing = false;
				break;
			case CANCEL:
				dash_button_pushing = false;
				break;
			case TURN:
				player_direction_pushing = false;
				break;
			case NAME:
				name_pushing = false;
				break;
			}
		}
	}

}
