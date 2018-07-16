package dangeon.model.object.artifact.device;

import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.map.MapList;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.random.TEST;
import dangeon.model.object.artifact.item.enchantSpecial.EnchantSpecial;
import dangeon.model.object.creature.player.Player;

public class 神階段 extends Stairs {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final int composition = 0;
	public boolean sight = true;
	public static String name = "神階段";

	public final int sitei;

	public 神階段(Point p, int sitei) {
		super(p, name.concat(String.valueOf(sitei)), 1, composition,
				ITEM_CASE.TRAP, GROW_RATE.早熟, false);
		IM = Image_Artifact.STAIRS;
		this.sitei = sitei;
		bm = TEST.ME;
	}

	@Override
	protected String[] getExplan() {
		return new String[] { "この階段は".concat(String.valueOf(sitei)).concat(
				"階行きです。") };
	}

	@Override
	public void move() {
		if (bm != null) {
			PresentField.setPresentField(bm);
		}
		Message.set(getColoredName(), "を踏んだ");
		MapList.setFloor(sitei - 1);
		EnchantSpecial.removeAlwaysEnchantSpecial();
		CONDITION.conditionAllClear(Player.me);
		EnemyTable.resetEnemyTurn();
		TurnSystemController.turnCountReset();
		TaskOnMapObject.setNewMap();
		if (Config.getStory() < 200) {
			Config.setStory(200);
		}
	}

}
