package dangeon.model.map;

import dangeon.controller.DangeonScene;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.map.field.Base_Map;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.hold_enemy.HoldEnemy;
import dangeon.model.object.creature.player.save.SaveLoad;
import dangeon.util.R;
import dangeon.view.anime.Base_Anime;
import main.res.SE;

public class NextFloor {

	private static boolean flag_next_special_check = true;

	public static void next(Base_Map bm) {
		resetData(bm);
		if (flag_next_special_check && PresentField.get().isNextSpecial()) {
			SE.SYSTEM_STAIR_STEP.play();
			TaskOnMapObject.setNewMap(new MassCreater(PresentField.get()
					.getNextSpecialField(), true), Config.isTest());
		} else {
			TaskOnMapObject.setNewMap(true);
		}
	}

	private static void resetData(Base_Map bm) {
		if (Player.me.isGray() || MapList.getFlagSheef()) {
			Medal.泥棒成功回数.addCount();
		}
		DangeonScene.STAIRS.setPresentScene();
		flag_next_special_check = true;
		if (bm != null) {
			if (bm != PresentField.get()) {
				flag_next_special_check = false;
				PresentField.setPresentField(bm);
			}
		}
		Base_Artifact.setGrayItemYours();
		Player.me.setAnimation((Base_Anime) null);
		Player.me.endDamaging();
		HoldEnemy.ME.reset();
		MapList.resetHutoChan();
		EnchantSpecial.removeAlwaysEnchantSpecial();
		CONDITION.conditionAllClear(Player.me);
		EnemyTable.resetEnemyTurn();
		TurnSystemController.turnCountReset();
		R.next();
	}

	public static void saveEnd(Base_Map bm) {
		resetData(bm);
		if (flag_next_special_check && PresentField.get().isNextSpecial()) {
			new SaveLoad(new MassCreater(PresentField.get()
					.getNextSpecialField(), true)).saveEnd();
		} else {
			new SaveLoad(new MassCreater()).saveEnd();
		}
	}
}
