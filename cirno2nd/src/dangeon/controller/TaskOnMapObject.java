package dangeon.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.res.SE;
import main.util.Show;
import dangeon.controller.listener.menu.Base_MenuItem;
import dangeon.controller.task.Task.TaskHasAppo;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.first.item.list.Scene_Menu_First_Item_List___Foot;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.device.Stairs;
import dangeon.model.object.artifact.item.enchantSpecial.simbolEffect.印宝;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.trap.Base_Trap;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.model.object.creature.enemy.二ッ岩マミゾウ;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.save.SaveLoad;
import dangeon.model.select.SelectBox_Stair;

public class TaskOnMapObject {
	private static ArrayList<Base_Artifact> artifact_set_task = new ArrayList<Base_Artifact>();
	private static ArrayList<Base_Artifact> item_remove_task = new ArrayList<Base_Artifact>();
	private static ArrayList<Base_Artifact> artifact_remove_task = new ArrayList<Base_Artifact>();
	private static ArrayList<Base_Artifact> map_item_remove_task = new ArrayList<Base_Artifact>();
	private static ArrayList<Base_Enemy> enemy_move_task = new ArrayList<Base_Enemy>();
	private static ArrayList<Base_Enemy> enemy_wait_move_task = new ArrayList<Base_Enemy>();
	private static ArrayList<Base_Enemy> enemy_remove_task = new ArrayList<Base_Enemy>();
	private static ArrayList<Base_Enemy> enemy_plus_task = new ArrayList<Base_Enemy>();
	private static ArrayList<Base_NPC> npc_remove_task = new ArrayList<Base_NPC>();
	private static MassCreater task_new_map = null;
	private static Base_Artifact task_stairs = null;
	private static Base_MenuItem task_menu_item = null;
	private static SpellCard task_used = null;
	private static boolean flag_counter_direction = false;
	private static boolean flag_skip_player_turn = false;
	private static boolean flag_start_enemy_turn = false;

	public static SaveLoad save_load = null;

	private static ThrowingItem throw_task = null;

	private static final ArrayList<Base_Creature> damaging_creature = new ArrayList<Base_Creature>();

	private static boolean player_moved_check_artifact_at_foot = false,
			player_moved_check_artifact_at_foot_in_dushing = false;

	public static Base_Artifact task_trap;

	private static ArrayList<TaskHasAppo> task_list_task = new ArrayList<TaskHasAppo>();

	public static void addEnemyMoveTask(Base_Enemy em) {
		enemy_move_task.add(em);
	}

	public static void addEnemyPlusTask(Base_Enemy em) {
		enemy_plus_task.add(em);
	}

	public static void addEnemyRemoveTask(Base_Creature c) {
		if (c instanceof Base_Enemy) {
			enemy_remove_task.add((Base_Enemy) c);
			if (c instanceof 二ッ岩マミゾウ) {
				((二ッ岩マミゾウ) c).itemFall();
			}
		}
		if (c instanceof Base_NPC) {
			npc_remove_task.add((Base_NPC) c);
		}
	}

	public static void addEnemyWaitMoveTask(Base_Enemy em) {
		// enemy_wait_move_task.add(em);
	}

	public static void addItemRemoveTask(Base_Artifact a) {
		item_remove_task.add(a);
	}

	public static void addMapItemRemoveTask(Base_Artifact a) {
		map_item_remove_task.add(a);
	}

	public static void addTrapRemoveTask(Base_Artifact base_Artifact) {
		artifact_remove_task.add(base_Artifact);
	}

	public static void addTrapSetTask(Base_Trap trap) {
		artifact_set_task.add(trap);
	}

	/**
	 * 
	 * @return 敵のターンを飛ばす場合true。例えば階段を下ったときなど。
	 */
	public static boolean artifactOnAction() {
		Base_Artifact a = MapList.getArtifact(Player.me.getMassPoint());

		if (player_moved_check_artifact_at_foot) {
			player_moved_check_artifact_at_foot = false;
			if (a != null) {
				task_trap = a;
				return a.walkOnAction();
			}
		} else if (player_moved_check_artifact_at_foot_in_dushing) {
			player_moved_check_artifact_at_foot_in_dushing = false;
			if (a != null) {
				task_trap = a;
				return a.walkOnAction(false);
			}
		}
		Show.showErrorMessageDialog("Error フラグが経っていないのに呼ばれました @TaskOnMapObject.artifactOnAction");
		return false;

	}

	public static void clearCheckArtifact() {
		player_moved_check_artifact_at_foot = false;
		player_moved_check_artifact_at_foot_in_dushing = false;
	}

	static void clearWaitMoveTask() {
		enemy_wait_move_task.clear();
	}

	public static void finishDamaging() {
		if (damaging_creature == null)
			return;
		for (Base_Creature c : damaging_creature) {
			c.endDamaging();
		}
		damaging_creature.clear();
	}

	public static List<Base_Enemy> getEnemyMoveTask() {
		return enemy_move_task;
	}

	public static ThrowingItem getThrow() {
		return throw_task;
	}

	static List<Base_Enemy> getWaitMoveTask() {
		return enemy_wait_move_task;
	}

	public static boolean isFlagCounterDirection() {
		return flag_counter_direction;
	}

	public static boolean isFlagSkipPlayerTurn() {
		return flag_skip_player_turn;
	}

	public static boolean isFlagStartEnemyTurn() {
		return flag_start_enemy_turn;
	}

	static boolean isMoveTaskEmpty() {
		return enemy_move_task.isEmpty();
	}

	public static boolean isPlayerMoveOnArtifact() {
		if (player_moved_check_artifact_at_foot
				|| player_moved_check_artifact_at_foot_in_dushing) {
			Base_Artifact a = 印宝.effect();
			if (a != null) {
				Scene_Action.getMe().set_I_Dushing_request_arrow_key(true);
				return true;
			}
			player_moved_check_artifact_at_foot = false;
			player_moved_check_artifact_at_foot_in_dushing = false;
		}
		return false;
	}

	public static boolean isTaskTask() {
		return !task_list_task.isEmpty();
	}

	public static boolean isThrowTaskEmpty() {
		return throw_task == null;
	}

	static boolean isWaitMoveTaskEmpty() {
		clearWaitMoveTask();
		return enemy_wait_move_task.isEmpty();
	}

	public static void reCreateNewMap() {
		setNewMap(new MassCreater(PresentField.get(), Player.me.getMassPoint(),
				false));
	}

	private static void removeItemTask() {
		for (Base_Artifact a : item_remove_task) {
			if (Enchant.isEnchanted(a)) {
				Enchant.forceToRemove(a);
			}
			Belongings.remove(a);
		}
		item_remove_task.clear();
	}

	public static void setDamaging(Base_Creature c) {
		damaging_creature.add(c);
	}

	public static void setDashingMoved() {
		player_moved_check_artifact_at_foot_in_dushing = true;
	}

	public static void setFlagCounterDirection(boolean f, Base_Creature c) {
		TaskOnMapObject.flag_counter_direction = f;
		Player.me.last_attack = c;
	}

	public static void setFlagSkipPlayerTurn(boolean b) {
		flag_skip_player_turn = b;
	}

	public static void setFlagStartEnemyTurn(boolean b) {
		flag_start_enemy_turn = b;
	}

	public static void setMoved() {
		player_moved_check_artifact_at_foot = true;
	}

	public static void setNewMap() {
		setNewMap(new MassCreater());
	}

	public static void setNewMap(boolean save) {
		if (save) {
			setNewMap(new MassCreater(), true);
		} else {
			setNewMap();
		}
	}

	public static void setNewMap(MassCreater next_map) {
		task_new_map = next_map;
	}

	public static void setNewMap(MassCreater next_map, boolean save) {
		task_new_map = next_map;
		if (save) {
			save_load = new SaveLoad(task_new_map);
		} else {
			save_load = null;
		}
	}

	public static void setTaskMenuItem(Base_MenuItem bmi) {
		task_menu_item = bmi;
	}

	public static void setTaskSpellUsed(SpellCard sc) {
		task_used = sc;
	}

	public static void setTaskStairs(Base_Artifact s) {
		task_stairs = s;
	}

	/**
	 * @since Task.work_appointment
	 * @param task
	 */
	public static void setTaskTask(TaskHasAppo task) {
		task_list_task.add(task);
	}

	public static void setThrow(ThrowingItem ti) {
		throw_task = ti;
	}

	public static void taskSpellUsed() {
		if (task_used != null) {
			Message.set(task_used.getClass().getSimpleName(), "力を使い果たしは消え去った");
			task_used = null;
		}
	}

	public static void work() {
		// FIXME TurnSystemControllerへ移譲
		// if (!Player.me.isAnimating()) {
		// MapList.checkSpecialRoom(Player.me.getMassPoint());
		// MapList.checkWater();
		// }
		// KeyHolder.workTaskKeyAcrion();
		for (Base_Artifact a : artifact_set_task) {
			MapList.addArtifact(a);
		}
		for (Base_Artifact artifact : artifact_remove_task) {
			MapList.getListArtifact().remove(artifact);
		}
		for (Base_Artifact artifact : map_item_remove_task) {
			MapList.getListArtifact().remove(artifact);
		}
		for (Base_Enemy em : enemy_remove_task) {
			MapList.getListEnemy().remove(em);
		}
		for (Base_Enemy em : enemy_plus_task) {
			em.enemy_actioned = true;
			MapList.addEnemy(em);
		}
		for (Base_NPC npc : npc_remove_task) {
			MapList.getListEnemy().remove(npc);
		}
		if (!item_remove_task.isEmpty()) {
			removeItemTask();
		}
		if (task_new_map != null) {
			task_new_map.createMap();
			task_new_map = null;
		}
		if (task_menu_item != null) {
			task_menu_item.work();
			task_menu_item = null;
		}
		enemy_plus_task.clear();
		artifact_set_task.clear();
		artifact_remove_task.clear();
		enemy_remove_task.clear();
		npc_remove_task.clear();
		work_task();
	}

	public static void work_task() {
		// task処理中に他のtaskが増える可能性があるのでロジック変更
		ArrayList<TaskHasAppo> list = new ArrayList<TaskHasAppo>(
				task_list_task.size());
		for (Iterator<TaskHasAppo> i = task_list_task.iterator(); i.hasNext();) {
			list.add(i.next());
		}
		for (TaskHasAppo task : list) {
			task.parent.work_call(task.appo);
			if (!task.parent.isDemandToContinue()) {
				task_list_task.remove(task);
			}
		}
	}

	static void workMove() {
		List<Base_Enemy> list = new ArrayList<Base_Enemy>();
		for (Base_Enemy enemy : enemy_move_task) {
			list.add(enemy);
		}
		enemy_move_task.clear();
		for (Base_Enemy enemy : list) {
			enemy.moving();
			if (Base_Enemy.enemy_move_wait) {
				enemy.enemy_actioned = true;
			}
		}
		enemy_move_task.clear();
	}

	public static void workStairs() {
		if (task_stairs != null && !TurnSystemController.isTurnOver()) {
			// 小町とかアンカーで移動されてないかのチェック
			if (!Player.me.isDead()
					&& !CONDITION.enemyDontMove(Player.me)
					&& MapList.getArtifact(Player.me.getMassPoint()) == task_stairs) {
				if (task_stairs instanceof Stairs) {
					new SelectBox_Stair((Stairs) task_stairs);
				} else {
					SE.SYSTEM_ENTER.play();
					Scene_Action.getMe().setNextScene(
							new Scene_Menu_First_Item_List___Foot(Scene_Action
									.getMe().KH,
									Scene_Action.getMe().CURRENT_VIEW, true));
				}

			}
		}
		task_stairs = null;
	}

}
