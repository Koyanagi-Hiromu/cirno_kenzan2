package dangeon.controller;

import java.awt.Color;
import java.awt.Image;

import connection.sv_cl.SocketHolder;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.condition.Nap;
import dangeon.model.config.Config;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.map.MapList;
import dangeon.model.map.Mass;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.special.map.BossMap.BOSS;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial.CASE;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.artifact.trap.落とし穴の罠;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.二ッ岩マミゾウ;
import dangeon.model.object.creature.enemy.鈴仙・優曇華院・イナバ;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.I_Dush;
import dangeon.model.object.creature.player.Player;
import dangeon.util.MapInSelect;
import dangeon.view.anime.CirnoIceEffect;
import dangeon.view.detail.MainMap;
import dangeon.view.detail.View_Sider;
import main.res.Image_Icon;
import main.res.SE;
import main.thread.MainThread;
import main.util.FrameShaker;
import main.util.半角全角コンバーター;

public class TurnSystemController {
	private enum EnemyTurnStep {
		主人公待ち, 敵移動, 敵移動待ち, 足元チェック, 敵攻撃, 敵攻撃待機, 全アニメ終了待ち, 罠発動待ち, 敵罠発動
	}

	private enum Phase {
		等速, 倍速, 三倍速;
	}

	private static int player_condition_sleep = 0;
	private static int turn_count = 0;
	private static boolean flag_turn_count_first = false,
			flag_turn_count_second = false;
	private static boolean flag_turn_count_max = false;

	private static Phase phase = Phase.等速;

	private static boolean slow_switch = false;

	private static EnemyTurnStep step = null;

	private static int non_actioned_count_hold = 0;

	// 仮--------------
	public static boolean time_stop = false;

	public static int time_stop_count = 0, time_stop_count2 = 0;

	private static boolean all_moved_and_start_to_wait = false;

	private static boolean flag_while_end = false;

	private static BOSS boss;

	private static void bossCheck() {
		if (boss == null)
			return;
		if (!MapList.getListEnemy().contains(boss.creature)) {
			boss.map.end();
			boss = null;
		}
	}

	public static void callMeToStartEnemyTurn() {
		callMeToStartEnemyTurn(false);
	}

	// -------------------
	public static void callMeToStartEnemyTurn(boolean only_walk_or_rest) {
		if (!only_walk_or_rest) {
			if (Player.me.conditionCheck(CONDITION.やりすごし)) {
				CONDITION.quickRemoveCondition(CONDITION.やりすごし, Player.me);
			}
		}
		Scene_Action.getMe().setBias_button_push_twice_over(false);
		step = EnemyTurnStep.主人公待ち;
	}

	private static void endEnemy() {
		if (!TaskOnMapObject.isMoveTaskEmpty()) {
			TaskOnMapObject.getEnemyMoveTask().clear();
		}
	}

	private static void endPlayer() {
		Player.me.selfHealing();
		Player.me.turnSatiety();
		Player.me.setBackPoint();
		CONDITION.邪法(Player.me);
		if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING, ENCHANT_SIMBOL.幸せ)) {
			if (!PresentField.get().isHaraheru()) {
				return;
			}
			Player.me.setExpCashNoMessage(1);
		}
		Player.me.playerLevelUp();
		EnchantSpecial.endPlayerTurn();
		Player.me.getClassJob().turnEndAction();
		for (Base_Artifact a : Belongings.getListItems()) {
			a.endPlayerTurn();
		}
		CONDITION.conditionEffect(Player.me);
		SocketHolder.endPlayer();
	}

	/**
	 * アタックorスペシャル
	 * 
	 * @return 移動町状態ならtrue
	 */
	private static boolean enemyAttack() {

		Base_Enemy non_actioned_enemy = MapList.getNonActionedEnemu();
		if (non_actioned_enemy != null) {
			// 移動中のキャラを待つフェイズ

			if (!Player.me.isAnimeComletelyEnded()) {
				return true;
			}
			for (Base_Creature c : MapList.getListCreature()) {
				if (!c.isAnimeComletelyEnded()) {
					return true;
				}
			}
			non_actioned_enemy.attackOrSpecial();
			step = EnemyTurnStep.敵攻撃待機;
		} else {
			step = EnemyTurnStep.全アニメ終了待ち;
		}
		return false;
	}

	/**
	 * 全てのエネミーの移動待機状態をtrue or falseにする
	 * 
	 * @param turn_start
	 *            ターン開始時ににはtrue 移動待機状態を解除する場合はtrue;
	 */
	private static void enemyMoveWaitAll(boolean turn_start) {
		Base_Enemy.enemy_move_wait = turn_start;
	}

	private static int get1stWarning() {
		return 5000;
	}

	private static int get2ndWarning() {
		return getMaxTurn() - 1500;
	}

	private static int getFinalWarning() {
		return getMaxTurn();
	}

	public static int getMaxTurn() {
		switch (Config.getFate()) {
		case 0:
			return 9990;
		case 1:
			return 8000;
		case 2:
			return 7000;
		default:
			return 6600;
		}
	}

	public static Image getPhaseImage() {
		switch (phase) {
		case 三倍速:
			return Image_Icon.stt_icon_quick3.getImage();
		case 倍速:
			return Image_Icon.stt_icon_quick.getImage();
		case 等速:
			return Image_Icon.stt_icon_quick0.getImage();
		}
		return null;
	}

	public static int getRestTurn() {
		return getMaxTurn() - turn_count;
	}

	public static int getRestTurnForHeader() {
		return flag_turn_count_second ? getRestTurn()  : -1;
	}

	public static boolean getSlowSwitch() {
		return slow_switch;
	}

	public static String getStepName() {
		if (step == null)
			return "null";
		return step.name();
	}

	public static boolean isAbleToMoveAdditionally(Base_Enemy e) {
		if (e.flag_3rd_move)
			return false;
		Player player = Player.me;
		switch (phase) {
		case 等速:
			if (e.flag_2nd_move) {
				if (e.conditionCheck(CONDITION.三倍速)) {
					e.flag_3rd_move = true;
					return true;
				} else
					return false;
			} else if (e.conditionCheck(CONDITION.倍速)
					|| e.conditionCheck(CONDITION.三倍速)) {
				if (!player.conditionCheck(CONDITION.倍速)
						&& !player.conditionCheck(CONDITION.三倍速)) {
					e.flag_2nd_move = true;
					return true;
				}
			}
			return false;
		case 倍速:
			if (e.conditionCheck(CONDITION.三倍速)
					&& !player.conditionCheck(CONDITION.三倍速)) {
				e.flag_3rd_move = true;
				return true;
			}
			return false;
		case 三倍速:
			return false;
		}
		return false;
	}

	public static boolean isBoss(Base_Enemy e) {
		return boss != null && boss.creature == e;
	}

	private static boolean isEnemyAnimatigCompletelyEnded() {
		for (Base_Enemy enemy : MapList.getListEnemy()) {
			if (!enemy.isAnimeComletelyEnded()) {
				return false;
			}
		}
		return true;
	}

	private static boolean isEnemyAnimatigEnded() {
		return MapList.isEnemyAnimatigEnded();
	}

	public static boolean isEnemyTurn() {
		return step != null;
	}

	public static boolean isTurnOver() {
		return turn_count > getMaxTurn();
	}

	private static boolean isXndWarning(int x) {
		if (x == 1) {
			return turn_count >= get1stWarning();
		} else if (x == 2) {
			return turn_count >= get2ndWarning();
		} else if (x == 3) {
			return turn_count >= getFinalWarning();
		}
		return false;
	}

	/**
	 * 
	 * @return ターンエンドに移行するならば、true
	 * 
	 */
	private static boolean moveOrActionLoop() {
		while (true) {
			int non_actioned_count = 0;
			if (MapList.getListEnemy().size() == 0) {
				// エネミーが居ないのならばreturn
				for (Base_Enemy em : MapList.getListEnemy()) {
					if (em.enemy_actioned) {
						em.flagMoveAnimeating();
					}
				}
				return true;
			}
			for (Base_Enemy enemy : MapList.getListEnemy()) {
				if (!enemy.enemy_actioned)
					non_actioned_count++;
			}
			if (non_actioned_count == 0) {
				// 全てのエネミーが行動済みならばreturn
				for (Base_Enemy em : MapList.getListEnemy()) {
					if (em.enemy_actioned) {
						em.flagMoveAnimeating();
					}
				}
				return true;
			}
			if (non_actioned_count == non_actioned_count_hold) {
				// 初めて
				enemyMoveWaitAll(true);
			}
			non_actioned_count_hold = non_actioned_count;
			if (TaskOnMapObject.isWaitMoveTaskEmpty()) {
				for (Base_Enemy enemy : MapList.getListEnemy()) {
					if (!enemy.enemy_actioned) {
						enemy.action();
					}
				}
			} else {
				for (Base_Enemy enemy : TaskOnMapObject.getWaitMoveTask()) {
					if (!enemy.enemy_actioned) {
						enemy.action();
					}
				}
			}
			if (!TaskOnMapObject.isMoveTaskEmpty()) {
				TaskOnMapObject.clearWaitMoveTask();
				// turn_end_flag = false;
				for (Base_Enemy em : MapList.getListEnemy()) {
					if (em.enemy_actioned) {
						em.flagMoveAnimeating();
					}
				}
				return false;
			}
			// 歩ききった
			all_moved_and_start_to_wait = true;
			for (Base_Enemy em : MapList.getListEnemy()) {
				if (em.enemy_actioned) {
					em.flagMoveAnimeating();
				}
			}
			return false;
		}
	}

	/**
	 * 
	 * @return　プレイヤーターンを飛ばす場合はtrue 飛ばさない場合はfalse
	 */
	private static boolean nextPhase() {
		switch (phase) {
		case 等速:
			if (quickPlayer()) {
				phase = Phase.倍速;
				return false;
			}
			if (quickEnemy()) {
				phase = Phase.倍速;
				return true;
			}
			break;
		case 倍速:
			if (quickPlayer()) {
				phase = Phase.三倍速;
				return false;
			}
			if (quickEnemy()) {
				phase = Phase.三倍速;
				return true;
			}
			phase = Phase.等速;
			break;
		case 三倍速:
			phase = Phase.等速;
			break;
		}
		return false;
	}

	/**
	 * @return 現phaseより次のフェイズに移行可能ならばtrueを返す
	 */
	private static boolean quickEnemy() {
		switch (phase) {
		case 等速:
			for (Base_Enemy em : MapList.getListEnemy()) {
				if (em.getConditionList().contains(CONDITION.倍速)
						|| em.getConditionList().contains(CONDITION.三倍速))
					if (!em.flag_2nd_move)
						return true;
			}
		case 倍速:
			for (Base_Enemy em : MapList.getListEnemy()) {
				if (em.getConditionList().contains(CONDITION.三倍速))
					if (!em.flag_3rd_move)
						return true;
			}
		}
		return false;
	}

	/**
	 * @return 現phaseより次のフェイズに移行可能ならばtrueを返す
	 */
	private static boolean quickPlayer() {
		switch (phase) {
		case 等速:
			if (Player.me.getConditionList().contains(CONDITION.倍速)
					|| Player.me.getConditionList().contains(CONDITION.三倍速))
				return true;
		case 倍速:
			if (Player.me.getConditionList().contains(CONDITION.三倍速))
				return true;
		}
		return false;
	}

	public static void removeTimeStop() {
		if (time_stop) {
			time_stop_count = -1;
			Message.set("そして時は動き出す@");
			Message.setTask_AfterReleaseDemandToPushEnter(new Task() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void work() {
					time_stop_count = 0;
					time_stop = false;
					MainThread.switchWB(false);
				}
			});
		}
	}

	public static void setBoss(BOSS boss) {
		TurnSystemController.boss = boss;
		if (boss != null)
			MapList.addEnemy(boss.creature);
	}

	public static void setPlayerConditionSleep() {
		player_condition_sleep = 5;
	}

	public static void setTimeStop(int count) {
		SE.TIME_STOP.play();
		MainThread.switchWB(true);
		time_stop = true;
		time_stop_count = count;
		time_stop_count2 = 0;
		setTurnFinish();
	}

	/**
	 * ターン開始時処理 phaseにより、行動可能なエネミーを行動済み状態から未行動状態にする
	 */
	private static void settingEnemyActioned() {
		Base_Enemy[] es = new Base_Enemy[MapList.getListEnemy().size()];
		for (int i = 0; i < es.length; i++) {
			es[i] = MapList.getListEnemy().get(i);
		}
		switch (phase) {
		case 等速:
			CONDITION.conditionRemove();
			slow_switch = slowSwitch();
			for (Base_Enemy enemy : es) {
				enemy.flag_2nd_move = enemy.flag_3rd_move = false;
				enemy.massCheck();
				if (enemy.getConditionList().contains(CONDITION.鈍足)
						&& slow_switch) {
					enemy.enemy_actioned = true;
					continue;
				}
				enemy.startTurn();
				enemy.enemy_actioned = false;
				enemy.quick_one_attack = false;
				CONDITION.conditionOneEnemyContinue(enemy);
				// Nap.TurnCheck(true, enemy);
			}
			break;
		case 倍速:
			for (Base_Enemy enemy : es) {
				if (!CONDITION.conditionEffect(enemy)) {
					if (enemy.getConditionList().contains(CONDITION.倍速)
							|| enemy.getConditionList().contains(CONDITION.三倍速)) {
						if (!enemy.quick_one_attack && !enemy.flag_2nd_move) {
							enemy.enemy_actioned = false;
							enemy.startTurn();
						}
					}
				}
				// if (enemy.enemy_actioned) {
				// enemy.whereIsPlayer();
				// }
			}
			break;
		case 三倍速:
			for (Base_Enemy enemy : es) {
				if (!CONDITION.conditionEffect(enemy)) {
					if (enemy.getConditionList().contains(CONDITION.三倍速)) {
						if (!enemy.quick_one_attack && !enemy.flag_3rd_move) {
							enemy.enemy_actioned = false;
							enemy.startTurn();
						}
					}
				}
				// if (enemy.enemy_actioned) {
				// enemy.whereIsPlayer();
				// }
			}
			break;
		}
	}

	public static void setTurnFinish() {
		step = null;
		phase = Phase.等速;
	}

	/**
	 * 
	 * @return 
	 *         slow_switchがtrueかつ、プレイヤーが鈍足の場合trueを返して、enemy_flag_turnをtrueにして、ターンを飛ばす
	 *         。
	 */
	private static boolean slowPlayer() {
		if (slow_switch) {
			if (Player.me.getConditionList().contains(CONDITION.鈍足)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return slow_switchがfalseかつ、鈍足のクリーチャーが居る場合はtrueを返してslow_switchをtrueにする
	 *         鈍足のクリーチャーが居る場合(false→true→false→true)*nとなる。
	 */
	private static boolean slowSwitch() {
		if (!slow_switch) {
			if (Player.me.getConditionList().contains(CONDITION.鈍足))
				return true;
			for (Base_Enemy enemy : MapList.getListEnemy()) {
				if (enemy.getConditionList().contains(CONDITION.鈍足))
					return true;
			}
		}
		return false;
	}

	private static void stairCheck() {
		if (!PresentField.get().isRandomField()) {
			return;
		}
		for (Base_Artifact a : MapList.getListArtifact()) {
			if (a instanceof Stairs) {
				return;
			}
		}
		for (Base_Enemy e : MapList.getListEnemy()) {
			if (e instanceof 二ッ岩マミゾウ) {
				if (((二ッ岩マミゾウ) e).hasStair()) {
					return;
				}
			}
		}
		MapList.addArtifact(new Stairs(MassCreater.getStairsIP()));
		Message.set("どこかに階段が現れたようだ");
	}

	// private boolean FLAG_NEXT = false;

	private static void stepAction() {
		if (moveOrActionLoop()) {
			step = (all_moved_and_start_to_wait) ? EnemyTurnStep.全アニメ終了待ち
					: EnemyTurnStep.敵移動待ち;
			all_moved_and_start_to_wait = true;
			return;
		}
		if (!TaskOnMapObject.isMoveTaskEmpty()) {
			TaskOnMapObject.workMove();
		}
		step = EnemyTurnStep.敵移動待ち;
	}

	/**
	 * ターンエンド時処理
	 */
	private static void stepEnd() {
		Message.setConcatFlag(false);
		MassCreater.stepEnd();
		for (Base_Enemy em : MapList.getListEnemy()) {
			Nap.TurnCheck(false, em);
			if (phase == Phase.等速)
				CONDITION.邪法(em);
			if (CONDITION.enemyDontMove(em)) {
				continue;
			}
			if (MassCreater.getMass(em.getMassPoint()).ROAD) {
				em.playerChecked();
				if (em.player_is_in_sight) {
					em.setPlayerBackPoint(Player.me.getMassPoint()
							.getLocation());
				} else {
					if (!em.rootIsAround()) {
						em.setPlayerBackPoint(Mass.nullpo.null_point);
					}
				}
			}
			if (em.getPlayerIsInSight()
					&& !Player.me.getConditionList().contains(CONDITION.やりすごし)
					&& !Player.me.getConditionList().contains(CONDITION.透明)) {
				if (!CONDITION.enemyDontMove(em)) {
					if (!em.getConditionList().contains(CONDITION.混乱)) {
						if (!em.getConditionList().contains(CONDITION.仮眠)
								|| !em.getConditionList().contains(
										CONDITION.特殊仮眠)
								|| !em.getConditionList()
										.contains(CONDITION.麻痺)) {
							em.direction = em.converDirection(Player.me
									.getMassPoint());
						}
						em.playerChecked();
					}
				}
			}
		}
		// TaskOnMapObjectから移譲
		while (MapList.checkSpecialRoom(Player.me.getMassPoint()))
			;
		if (nextPhase()) {
			flag_while_end = true;
			stepStart();
			return;
		}
		EnemyTable.makeEnemyTurn();
		if (slowPlayer()) {
			CONDITION.conditionPlayerContinue();
			flag_while_end = true;
			stepStart();
			return;
		}
		if (phase == Phase.等速) {
			CONDITION.conditionPlayerContinue();
		}
		// ターン終了時効果
		stairCheck();
		bossCheck();
		endPlayer();
		MassCreater.turnCount();
		endEnemy();
		//
		step = null;
		if (TaskOnMapObject.isFlagSkipPlayerTurn()) {
			鈴仙・優曇華院・イナバ.effect();
			callMeToStartEnemyTurn();
			TaskOnMapObject.setFlagSkipPlayerTurn(false);
		} else {
			TaskOnMapObject.workStairs();
			Message.setTurnStartFlagOn();
			if (Scene_Action.getMe().is_I_Dushing()) {
				I_Dush.work();
			}
		}
		turnCount();
		// TaskOnMapObjectから移譲
		while (MapList.checkSpecialRoom(Player.me.getMassPoint()))
			;
	}

	/**
	 * ターン開始
	 */
	private static void stepStart() {
		if (time_stop && timeCount()) {
			step = null;
			return;
		}
		// TaskOnMapObjectから移譲
		MapList.checkWater();
		// while (MapList.checkSpecialRoom(Player.me.getMassPoint()))
		// ;
		EnchantSpecial.turnStepEffect();
		all_moved_and_start_to_wait = false;
		settingEnemyActioned();
		enemyMoveWaitAll(false);
		step = EnemyTurnStep.敵移動;
	}

	public static boolean timeCount() {
		SE.SYSTEM_CURSOR.play();
		time_stop_count--;
		time_stop_count2++;
		Message.set(半角全角コンバーター.半角To全角数字(time_stop_count2), "秒経過！");
		if (time_stop_count <= 0) {
			removeTimeStop();
			return false;
		} else if (time_stop_count2 == 5) {
			Message.set("ふはは、まだまだ止めていられるぞ！");
		}
		return true;
	}

	private static void turnCount() {
		if (!PresentField.get().isHaraheru()) {
			return;
		}
		turn_count += 10;
		if (!isXndWarning(1)) {
			if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
					ENCHANT_SIMBOL.寒)) {
				turn_count = get1stWarning();
			}
		}
		if (isXndWarning(1) && !isXndWarning(2)) {
			if (flag_turn_count_first) {
				// if (EnchantSpecial.enchantSimbolAllCheck(CASE.RING,
				// ENCHANT_SIMBOL.寒)) {
				// turn_count += 90;
				// }
			} else {
				flag_turn_count_first = true;
				Player.me.setCondition(CONDITION.透視, 0);
				// MassCreater.setAllMassOnMiniMap();
				Message.set("ダンジョンが冷えて浅い水路が凍った");
				MassCreater.frozenWater(true);
				View_Sider.setInformation("そろそろ次の階へ向かおう");
				SE.ICE.play();
				FrameShaker.doneSoftly();
				FrameShaker.setShake_time(1000);
			}
			return;
		} else if (isXndWarning(2) && !isXndWarning(3)
				&& !flag_turn_count_second) {
			turn_count = get2ndWarning();
			flag_turn_count_second = true;
			Player.me.setCondition(CONDITION.透視, 0);
			// MassCreater.setAllMassOnMiniMap();
			View_Sider.setInformation("早く次の階に行かないと…！");
			SE.ICE.play();
			MainMap.addEffect(new CirnoIceEffect(), true);
			MassCreater.frozenWater(true);
			MapInSelect.damageIcy_toAllEnemies(null, null,
					Player.me.getLV() / 3);
			Message.set(Color.red, "だんだん楽しくなってきた！");
			FrameShaker.doneNormaly();
			FrameShaker.setShake_time(1000);
			return;
		} else if (isTurnOver() && !flag_turn_count_max) {
			Player.me.flag_game_over_TurnOver = true;
			Player.me.death(null, "楽しくなって冒険の目的を忘れた");
			if (MapList.getFlagSheef()) {
				Medal.泥棒していたら冒険の目的を忘れた.addCount();
			}
			flag_turn_count_max = true;
			return;
		} else {

		}
	}

	public static void turnCountReset() {
		turn_count = 0;
		flag_turn_count_first = false;
		flag_turn_count_second = false;
		flag_turn_count_max = false;
	}

	public static void turnSkip() {
		// if (!isXndWarning(1)) {
		turn_count = get1stWarning() - 1;
		// }
	}

	public static void upDate() {
		upDate_body();
		// if (step != null)
		// switch (step) {
		// case 敵攻撃待機:
		// case 敵移動:
		// case 敵移動待ち:
		// case 敵罠発動:
		// case 罠発動待ち:
		// ((SceneHolder_KeyAccepter) Scene.DANGEON.SYS.KEY)
		// .clearTaskKeyActions();
		// }
	}

	public static void upDate_body() {
		if (player_condition_sleep > 0) {
			player_condition_sleep--;
		}
		while (true) {
			if (Message.isDemandToWaitPushingAnyKey()) {
				return;
			}
			Player.me.updateStatus();
			if (step == null) {
				CONDITION.conditionEffect(Player.me);
				if (MainSystem.isKeyAccept()) {
					TaskOnMapObject.finishDamaging();
				}
				return;
			}
			if (MainSystem.isDemandForWaitExist()) {
				// アニメの描画中
				return;
			}
			switch (step) {
			case 主人公待ち:
				if (!DangeonScene.DANGEON.isPresentScene()) {
					return;
				}
				if (Message.isDemandToWaitAnySystem()) {
					return;
				}
				if (Player.me.isAnimating_withoutMoving()) {
					return;
				}
				if (!isEnemyAnimatigEnded()) {
					return;
				}
				if (TaskOnMapObject.isTaskTask()) {
					return;
				}
				Message.setConcatFlag(false);
				TaskOnMapObject.taskSpellUsed();
				stepStart();
				break;
			case 敵移動:
				stepAction();
				break;
			case 敵攻撃:
				if (Player.me.isAnimating()) {
					return;
				}
				if (enemyAttack()) {
					return;
				}
				break;
			case 敵移動待ち:
				if (all_moved_and_start_to_wait) {
					if (isEnemyAnimatigEnded()) {
						if (TaskOnMapObject.isPlayerMoveOnArtifact()) {
							step = EnemyTurnStep.足元チェック;
							return;
						} else {
							step = EnemyTurnStep.敵罠発動;
							TaskOnMapObject.finishDamaging();
							break;
						}
					} else {
						return;
					}
				} else {
					step = EnemyTurnStep.敵移動;
					break;
				}
			case 敵攻撃待機:
				if (Player.me.isAnimating()) {
					return;
				}
				if (!Player.me.isAnimeComletelyEnded()) {
					return;
				}
				if (isEnemyAnimatigEnded() && isEnemyAnimatigCompletelyEnded()) {
					step = (all_moved_and_start_to_wait) ? EnemyTurnStep.敵罠発動
							: EnemyTurnStep.敵移動;
					TaskOnMapObject.finishDamaging();
					break;
				} else {
					return;
				}
			case 敵罠発動:
				for (Base_Enemy em : MapList.getListEnemy()) {
					if (em.flag_move) {
						em.flag_move = false;
					} else {
						continue;
					}
					Base_Trap a = MapList.getTrap(em.getMassPoint());
					if (a != null) {
						a.walkOnActionByEnemy(em);
					}
				}
				step = EnemyTurnStep.敵攻撃;
				break;
			case 足元チェック:
				if (Player.me.isAnimating()) {
					return;
				}
				boolean flag = TaskOnMapObject.artifactOnAction();
				step = (flag) ? EnemyTurnStep.全アニメ終了待ち : EnemyTurnStep.罠発動待ち;
				return;
			case 罠発動待ち:
				if (Player.me.isAnimating()) {
					return;
				}
				if (TaskOnMapObject.task_trap.isDemandWaiting()) {
					return;
				} else {
					if (!Player.me.isAnimeComletelyEnded()) {
						return;
					}
					if (TaskOnMapObject.task_trap instanceof 落とし穴の罠) {
						step = EnemyTurnStep.全アニメ終了待ち;
					} else {
						step = EnemyTurnStep.敵罠発動;
					}
				}
				break;
			case 全アニメ終了待ち:
				if (Player.me.isAnimating()) {
					return;
				}
				if (isEnemyAnimatigEnded() == false) {
					return;
				}
				if (player_condition_sleep > 0) {
					return;
				}
				stepEnd();
				break;
			}
		}
	}
}
