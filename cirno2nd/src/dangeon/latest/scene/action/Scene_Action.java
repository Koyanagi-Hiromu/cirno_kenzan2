package dangeon.latest.scene.action;

import java.awt.Point;
import java.util.HashMap;

import main.Listener.ACTION;
import main.Scene;
import main.constant.FR;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.DangeonScene;
import dangeon.controller.MainSystem;
import dangeon.controller.TurnSystemController;
import dangeon.controller.listener.menu.Conducter;
import dangeon.controller.listener.menu.Menu_Select;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.first.Scene_Menu_First;
import dangeon.latest.scene.action.menu.first.infomation.contents.Record;
import dangeon.latest.scene.action.menu.first.infomation.contents.Simbols;
import dangeon.latest.scene.action.menu.first.item.Scene_Menu_First_Item;
import dangeon.latest.scene.action.menu.first.item.list.Scene_Menu_First_Item_List;
import dangeon.latest.scene.action.menu.first.item.list.Scene_Menu_First_Item_List___Foot;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.system.SceneHolder_KeyAccepter;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Base_Device;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.EnchantArrow;
import dangeon.model.object.creature.player.I_Dush;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.class_job.bonus.bonus_switch.BonusConductor;
import dangeon.model.object.creature.player.hold_enemy.HoldEnemy;
import dangeon.model.select.SelectBox_Stair;

public class Scene_Action extends Base_Scene {
	public static Scene_Action getMe() {
		return ((SceneHolder_KeyAccepter) Scene.DANGEON.SYS.KEY).SCENE_ACTION;
	}

	private int time = 0;

	private final int INTERVAL = 60;
	private boolean dash_button_pushing = false;
	private boolean player_direction_pushing = false;
	private boolean bias_button_pushing = false,
			bias_button_push_twice_over = false;
	private boolean resting = false;

	private boolean name_pushing = false;

	private boolean flag_i_dushing = false;

	private boolean flag_map = false;

	private boolean flag_rest_pre_neiboring = false;

	private boolean flag_rest_stopper;

	private DIRECTION direction = DIRECTION.NEUTRAL;

	// private void agressiveAction() {
	// if (Player.me.conditionCheck(CONDITION.やりすごし)) {
	// Message.set("宵闇状態を解除した");
	// CONDITION.conditionRecovery(Player.me, CONDITION.やりすごし);
	// }
	// }

	private HashMap<DIRECTION, Boolean> must_release_map = new HashMap<DIRECTION, Boolean>();

	private DIRECTION directing;

	private boolean flag_request_i_dushing_for_push_arrow;

	boolean flag_current;

	public Scene_Action(KeyHolder kh) {
		super(kh, new Scene_Action_View());
	}

	@Override
	public boolean action(ACTION a) {
		if (MainSystem.isKeyAccept()) {
			switch (DangeonScene.getPresentScene()) {
			case SELECT:
				Menu_Select.actionKeyPressed(a);
				return true;
			case GOUSEI:
			case SELECT_ITEM:
			case MENU:
			case ENCHANT:
			case RESULT:
				Conducter.actionKeyPressed(a);
				return true;
			}
			switch (a) {
			case MENU:
				return key_menu();
			case ENTER:
				return key_enter();
			case BIAS:
				return key_bias();
			case TURN:
				return key_direction();
			case CANCEL:
				return key_cancel();
			case FIRE:
				return key_fire();
			case IDASH:
				return key_idush();
			case MAP:
				return key_map();
			case GATHER:
				return key_gather();
			case ITEM:
				return key_item();
			case RECORD:
				return key_record();
			case BGM:
				return key_bgm();
			case SYMBOL:
				return key_simbols();
			case SPEC:
				return key_spec();
			default:
				return END;
			}
		} else {
			// 先行入力受付のためにON
			return AUTO_REPEAT;
		}
	}

	@Override
	public boolean arrow(DIRECTION d) {
		return playerArrow(d);
	}

	private void directing(boolean b) {
		Point p = Player.me.getMassPoint();
		int x, y;
		int j = 0;
		boolean[] in_sight = new boolean[8];
		while (true) {
			int rotate = Player.me.getDirection().ROTATE + 1;
			j++;
			for (int i = 0; i < 8; i++, rotate++) {
				DIRECTION d = DIRECTION.getDirectionFromRotate(rotate);
				x = p.x + d.X * j;
				y = p.y + d.Y * j;
				Base_Enemy e = MapList.getEnemy(x, y);
				if (e != null && e.isAbleToFeel()) {
					Player.me.setDirection(d);
					return;
				}
				in_sight[i] = !MassCreater.getMass(x, y).equals(Mass.nullpo)
						&& (EnchantSpecial.toushi()
								|| !MassCreater.isSpotValid() || MassCreater
									.isPlayerInTheSameRoom(x, y));
			}
			if (!b)
				return;
			boolean d = false;
			for (boolean c : in_sight) {
				if (c) {
					d = true;
					break;
				}
			}
			if (!d)
				return;
		}
	}

	private void directing(DIRECTION d) {
		// System.out.println(d);
		if (directing != null) {
			if (DIRECTION.isBias(directing)) {
				if (DIRECTION.getDirection(directing.X, 0).equals(d)
						|| DIRECTION.getDirection(0, directing.Y).equals(d)) {
					return;
				}
			} else {
				if (directing.X == 0 && d.Y == 0) {
					d = DIRECTION.getDirection(d.X, directing.Y);
				} else if (directing.Y == 0 && d.X == 0) {
					d = DIRECTION.getDirection(directing.X, d.Y);
				}
			}
		}
		Player.me.direction = d;
		directing = d;
		//
		//
		// if (leftKey_pushing && upKey_pushing) {
		// Player.me.direction = DIRECTION.UP_LEFT;
		// direction_holding_frame = DIRECTION_HOLDING_FRAME;
		// } else if (leftKey_pushing && downKey_pushing) {
		// Player.me.direction = DIRECTION.DOWN_LEFT;
		// direction_holding_frame = DIRECTION_HOLDING_FRAME;
		// } else if (rightKey_pushing && upKey_pushing) {
		// Player.me.direction = DIRECTION.UP_RIGHT;
		// direction_holding_frame = DIRECTION_HOLDING_FRAME;
		// } else if (rightKey_pushing && downKey_pushing) {
		// Player.me.direction = DIRECTION.DOWN_RIGHT;
		// direction_holding_frame = DIRECTION_HOLDING_FRAME;
		// } else {
		// if (bias_button_pushing) {
		// return;
		// }
		// if (direction_holding_frame > 0) {
		// direction_holding_frame--;
		// return;
		// }
		// if (leftKey_pushing) {
		// Player.me.direction = DIRECTION.LEFT;
		// } else if (rightKey_pushing) {
		// Player.me.direction = DIRECTION.RIGHT;
		// } else if (upKey_pushing) {
		// Player.me.direction = DIRECTION.UP;
		// } else if (downKey_pushing) {
		// Player.me.direction = DIRECTION.DOWN;
		// }
		// }
	}

	public DIRECTION getDirection() {
		return direction;
	}

	public boolean is_I_Dushing() {
		return flag_i_dushing;
	}

	public boolean is_Stopping_I_Dushing() {
		return flag_request_i_dushing_for_push_arrow;
	}

	public boolean isBiasButtonPushing() {
		return bias_button_pushing;
	}

	public boolean isCurrentScene() {
		return flag_current;
	}

	public boolean isDirecting() {
		return player_direction_pushing;
	}

	public boolean isDushing() {
		return (dash_button_pushing && KH.isDirecting()) || flag_i_dushing;
	}

	public boolean isDushKeyPushing() {
		return dash_button_pushing;
	}

	public boolean isMap() {
		return flag_map;
	}

	public boolean isNamePushing() {
		return name_pushing;
	}

	public boolean isOwata() {
		return KH.isContraining(ACTION.OWATTA);
	}

	public boolean isResting() {
		return resting;
	}

	private boolean key_bgm() {
		Config.switchBgm();
		if (Config.isFixBGM()) {
			Message.set("BGMを現在の曲に固定しました$装備を変更してもＢＧＭを維持します");
		} else {
			Message.set("BGMの固定を解除しました$新しく装備を変更してください");
		}
		return END;
	}

	private boolean key_bias() {
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
		return END;
	}

	private boolean key_cancel() {
		// directing(false);
		dash_button_pushing = true;
		return END;
	}

	private boolean key_direction() {
		if (dash_button_pushing) {
			return key_record();
		} else if (!player_direction_pushing) {
			name_pushing = true;
			player_direction_pushing = true;
			directing = null;
			directing(true);
		}
		return END;
	}

	private boolean key_enter() {
		if (dash_button_pushing) {
			DIRECTION flag_neiboring = DIRECTION.NEUTRAL;
			for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
				Base_Enemy e = MapList.getEnemy(d.getFrontPoint(Player.me
						.getMassPoint().getLocation()));
				if (MapList.isAnyEnemyInScreen_Check(e)) {
					if (Player.me.attackWalkableCheck(Player.me.getMassPoint(),
							d)) {
						flag_neiboring = d;
						break;
					}
				}
			}
			boolean no_skip = (!flag_rest_pre_neiboring && flag_neiboring != DIRECTION.NEUTRAL);
			flag_rest_pre_neiboring = flag_neiboring != DIRECTION.NEUTRAL;
			if (flag_neiboring != DIRECTION.NEUTRAL)
				Player.me.direction = flag_neiboring;
			if (no_skip)
				flag_rest_stopper = true;
			if (flag_rest_stopper) {
				flag_rest_stopper = false;
				resting = false;
				return END;
			} else {
				resting = true;
				return AUTO_REPEAT;
			}
			//
			// if (false) {
			//
			// if (flag_rest_stopper) {
			// TurnSystemController.callMeToStartEnemyTurn();
			// return END;
			// }
			// boolean flag = false;
			// for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			// if (MapList.isAnyEnemyInScreen_Check(MapList.getEnemy(d
			// .getFrontPoint(Player.me.getMassPoint()
			// .getLocation())))) {
			// flag = true;
			// break;
			// }
			// }
			// if (flag) {
			// flag_rest_stopper = true;
			// // rest_warning = true;
			// resting = false;
			// // TurnSystemController.callMeToStartEnemyTurn();
			// return END;
			// } else {
			// resting = true;
			// return AUTO_REPEAT;
			// }
			// }
		} else if (resting) {
			resting = false;
			return END;
		} else {
			resting = false;
			boolean attack_true__speak_false = Player.me.normalAttack();
			if (attack_true__speak_false) {
				TurnSystemController.callMeToStartEnemyTurn();
				return AUTO_REPEAT;
			} else {
				return END;
			}
		}
	}

	private boolean key_fire() {
		if (dash_button_pushing) {
			return key_gather();
		} else {
			if (HoldEnemy.ME.isHold()) {
				HoldEnemy.ME.specialAction();
				return AUTO_REPEAT;
			} else {
				EnchantArrow a = EnchantArrow.getArrow();
				if (a != null) {
					Arrow arrow = Belongings.getArrow(a.CLAZZ);
					if (arrow == null) {
						Message.set(EnchantArrow.getArrow().name(), "を持っていない");
					} else {
						if (arrow.itemUse()) {
							TurnSystemController.callMeToStartEnemyTurn();
							return AUTO_REPEAT;
						} else {
							Message.set("うまく投げられなかった");
						}
					}
				} else {
					Message.set("何もセットされていません");
				}
			}
		}
		return END;
	}

	private boolean key_gather() {

		Base_Artifact a = Belongings.getFoot();
		if (a != null) {
			if (a instanceof Stairs) {
				setNextScene(Scene_Action.getMe());
				new SelectBox_Stair((Stairs) a);
			} else if (!(a instanceof Base_Device)) {
				if (Player.me.conditionCheck(CONDITION.おにぎり)) {
					Message.set("おにぎりの姿ではアイテムに触われません");
				} else if (HoldEnemy.ME.isHold()) {
					Message.set("敵を装備している間はアイテムに触われません");
				} else {
					if (!a.isMobile()) {
						setNextScene(new Scene_Menu_First_Item_List___Foot(KH,
								CURRENT_VIEW));
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
				}
			}
		} else {
			Message.set("足元には特に何もない");
		}

		return END;
	}

	private boolean key_idush() {
		if (dash_button_pushing) {
			return key_spec();
		} else {
			directing(true);
			set_I_Dushing(true);
		}
		return END;
	}

	private boolean key_item() {
		SE.SYSTEM_MENU.play();
		Message.end();
		Base_Scene_Menu menu;
		if (Belongings.isEmpty()) {
			Message.set("何も持っていない");
			return END;
		} else {
			menu = new Scene_Menu_First_Item_List(KH, CURRENT_VIEW);
		}
		setNextScene(menu);
		return END;

	}

	private boolean key_map() {
		if (dash_button_pushing) {
			return key_simbols();
		} else {
			flag_map = true;
		}
		return END;
	}

	private boolean key_menu() {
		if (dash_button_pushing)
			return key_item();
		SE.SYSTEM_MENU.play();
		Message.end();
		Base_Scene_Menu menu;
		Scene_Menu_First smf = new Scene_Menu_First(KH, CURRENT_VIEW);
		menu = new Scene_Menu_First_Item(smf.KH, smf.CURRENT_VIEW);
		setNextScene(menu);
		return END;
	}

	private boolean key_record() {
		SE.SYSTEM_MENU.play();
		setNextScene(new Record(KH, CURRENT_VIEW));
		return END;
	}

	private boolean key_simbols() {
		SE.SYSTEM_MENU.play();
		setNextScene(new Simbols(KH, CURRENT_VIEW));
		return END;
	}

	private boolean key_spec() {
		if (BonusConductor.checkSpec()) {
			Player.me.getClassJob().specExcute();
		} else if (PresentField.get().isEnemyEnchantDungeon()) {
			if (HoldEnemy.ME.isHold())
				HoldEnemy.ME.throwEnemy();
			else
				HoldEnemy.ME.hold();
		}
		return END;
	}

	@Override
	public void keyReleased(ACTION a, DIRECTION d) {
		if (a != null) {
			switch (a) {
			case MAP:
				flag_map = false;
				break;
			case BIAS:
				bias_button_pushing = false;
				break;
			case CANCEL:
				releaseCancel();
				break;
			case TURN:
				name_pushing = false;
				player_direction_pushing = false;
				break;
			case NAME:
				name_pushing = false;
				break;
			case IDASH:
				set_I_Dushing(false);
				break;
			}
		}
		if (d != null) {
			must_release_map.put(d, false);
		}
		super.keyReleased(a, d);
	}

	private boolean playerArrow(DIRECTION direction) {
		if (direction == null) {
			return END;
		}
		if (DangeonScene.SELECT.isPresentScene()) {
			Menu_Select.arrowKeyPressed(direction);
			return END;
		}
		if (Message.isDemandToWaitPushingAnyKey()) {
			return END;
		}
		switch (DangeonScene.getPresentScene()) {
		case DANGEON:
			return AUTO_REPEAT;
		case SELECT:
			Menu_Select.arrowKeyPressed(direction);
			break;
		case MENU:
		case SELECT_ITEM:
		case GOUSEI:
		case ENCHANT:
		case RESULT:
			if (!MainSystem.isKeyAccept())
				return END;
			Conducter.arrowKeyPressed(direction);
			break;
		default:
			break;
		}
		return END;
	}

	private void releaseCancel() {
		dash_button_pushing = false;
	}

	public void set_I_Dushing(boolean b) {
		if (!b) {
			KH.setRequestReleasePushingKey();
		} else {
			I_Dush.auto = true;
		}
		flag_i_dushing = b;
	}

	public void set_I_Dushing_request_arrow_key(boolean b) {
		// if (b && I_Dush.auto)
		// Player.me.setDirection(Player.me
		// .getVisibleObjectExistAtNeiboringMass_direction(false));
		flag_request_i_dushing_for_push_arrow = b;
	}

	public void setBias_button_push_twice_over(boolean b) {
		bias_button_push_twice_over = b;
	}

	@Override
	public void setNextScene(Base_Scene base_scene) {
		ACTION[] actions = { ACTION.MAP, ACTION.BIAS, ACTION.CANCEL,
				ACTION.TURN, ACTION.NAME, ACTION.IDASH };
		for (ACTION action : actions)
			keyReleased(action, null);
		super.setNextScene(base_scene);
	}

	public void setRequestReleasePushingKey() {
		for (DIRECTION d : DIRECTION.values_onlyBasic4()) {
			must_release_map.put(d, false);
		}
		for (DIRECTION d : KH.getDirections()) {
			must_release_map.put(d, true);
		}
	}

	public void setTimeZero() {
		time = 0;
	}

	public void tellRestStop() {
		flag_rest_stopper = true;
	}

	@Override
	public void upDate() {
		if (MainSystem.isKeyAccept() && resting) {
			if (dash_button_pushing && isContraining(ACTION.ENTER)) {
				TurnSystemController.callMeToStartEnemyTurn(true);
			} else {
				resting = false;
			}
		} else {
			for (boolean b : must_release_map.values()) {
				if (b) {
					return;
				}
			}
			direction = DIRECTION.getSum(KH.getDirections());
			if (DIRECTION.NEUTRAL.equals(direction)) {
				if (time == 0)
					directing = null;
				time = 0;
				return;
			} else {
				time += FR.THREAD_SLEEP;
				if (MainSystem.isKeyAccept()) {
					if (player_direction_pushing || isMap())
						directing(direction);
					else if (DIRECTION.isBias(direction))
						Player.me.move(direction);
					else if (!bias_button_pushing && time >= INTERVAL)
						Player.me.move(direction);
				}
			}
		}
	}

}
