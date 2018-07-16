package dangeon.controller.listener.menu;

import java.awt.Graphics2D;

import main.Listener.ACTION;
import main.util.BlackOut;
import main.util.DIRECTION;
import dangeon.controller.DangeonScene;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.model.config.Config;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.Base_Map_Random;
import dangeon.model.map.field.town.map.FairyPlace;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.save.ResultSaveLoad;
import dangeon.model.object.creature.player.save.SaveLoad;
import dangeon.view.detail.View_Result;

public class Menu_Result {
	private static boolean flag_enter = false;
	private static boolean flag_item_conservation;

	private static boolean flag_jast_watch_record = false;

	private static boolean flag_setScene = true;

	private static Base_Artifact[] present_enchants = new Base_Artifact[Enchant
			.values().length];

	private static ResultSaveLoad result = null;

	private static int x = 0, y = 0;

	private static final int X_LENGTH = 1 + Belongings.getMax() / 10,
			Y_LENGTH = 4;

	public synchronized static void actionKeyPressed(ACTION action) {
		if (flag_enter) {
			return;
		}
		if (flag_jast_watch_record) {
			if (action == ACTION.CANCEL) {
				action = ACTION.ENTER;
			}
		}
		if (action == ACTION.ENTER) {
			flag_enter = true;
			if (flag_jast_watch_record) {
				enchant_off();
				DangeonScene.SELECT.setPresentScene();
			} else {
				end();
			}
		}
	}

	public synchronized static void arrowKeyPressed(DIRECTION direction) {
		return;
		// switch (direction) {
		// case UP:
		// case DOWN:
		// case LEFT:
		// case RIGHT:
		// break;
		// default:
		// return;
		// }
		// x += direction.X;
		// y += direction.Y;
		// if (x < 0) {
		// y = 0;
		// x = X_LENGTH - 1;
		// } else if (x > X_LENGTH - 1) {
		// x = 0;
		// }
		// if (x == 0 && direction.X != 0) {
		// initY();
		// } else if (x == 1 && direction.X == 1) {
		// y = 0;
		// }
		// if (x == 0) {
		// if (y < 0) {
		// y = 0;
		// } else if (y > result.RECORD.length - 2) {
		// y = result.RECORD.length - 2;
		// }
		// } else {
		// if (y < 0) {
		// y = ITEM.Y_LENGTH - 1;
		// } else if (y > ITEM.Y_LENGTH - 1) {
		// y = 0;
		// }
		// }
	}

	public synchronized static void draw(Graphics2D g) {
		View_Result.draw(g);
	}

	private static void enchant_off() {
		for (int i = 0; i < Enchant.values().length; i++) {
			Enchant.values()[i].setEnchant(present_enchants[i]);
		}
	}

	private static void enchant_on() {
		for (int i = 0; i < Enchant.values().length; i++) {
			present_enchants[i] = Enchant.values()[i].getEnchant();
			Enchant.values()[i].setEnchant(result.ENCHANT_ARTIFACT[i]);
		}

	}

	private static void end() {
		if (!Player.flag_clear) {
			Config.addTimes();
		}
		BlackOut.setNoMoveSE();
		new BlackOut(new FairyPlace(), new Task() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				DangeonScene.DANGEON.setPresentScene();
				result = null;
				MassCreater mc = new MassCreater(new FairyPlace(), false);
				TaskOnMapObject.setNewMap(mc);
				if (flag_item_conservation) {
					Player.me.resetAll_exceptItems();
					Player.flag_clear = false;
				} else {
					Player.me.resetAll();
				}
				new SaveLoad(mc).saveContinue();
				SaveLoad.delete(true);
			}
		});
	}

	public static int getMaxY() {
		return Y_LENGTH;
	}

	public static ResultSaveLoad getResult() {
		return result;
	}

	/**
	 * 
	 * @return x-1
	 */
	public static int getX() {
		return x - 1;
	}

	public static int getY() {
		return y;
	}

	/**
	 * 倒れたとき
	 * 
	 * @param rsl
	 */
	public static void init(ResultSaveLoad rsl) {
		flag_item_conservation = false;
		flag_jast_watch_record = false;
		TaskOnMapObject.clearCheckArtifact();
		flag_enter = false;
		result = rsl;
		initY();
		x = 0;
		setScene();
		enchant_on();
	}

	/**
	 * ゲームクリア時や帰還するとき<br/>
	 * 直後にsetScene()を呼ぶこと
	 * 
	 * @param msg
	 * @param bmr
	 */
	public static void init(String msg, Base_Map_Random bmr, boolean flag_clear) {
		Player.flag_clear = flag_clear;
		Player.me.cause_of_death = msg;
		flag_setScene = false;
		init(new ResultSaveLoad(bmr));
		flag_item_conservation = true;
		flag_setScene = true;
	}

	/**
	 * あやから見るとき
	 * 
	 * @param field
	 * @param i
	 */
	public static void init(String field, int i) {
		// init(ResultSaveLoad.staticLoad(field, i));
		flag_jast_watch_record = true;
	}

	private static void initY() {
		y = result.RECORD.length - 2 - Y_LENGTH + 1;
		if (y < 0) {
			y = 0;
		}
	}

	public static void setFlagConservation(boolean b) {
		flag_item_conservation = b;
	}

	public static void setScene() {
		if (flag_setScene) {
			DangeonScene.RESULT.setPresentScene();
		}
	}

}