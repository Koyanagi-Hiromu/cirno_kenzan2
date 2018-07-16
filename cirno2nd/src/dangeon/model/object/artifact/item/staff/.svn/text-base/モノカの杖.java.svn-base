package dangeon.model.object.artifact.item.staff;

import java.awt.Point;

import main.res.SE;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.table.ItemTable;
import dangeon.model.map.ItemFall;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.enemy.Base_Enemy;
import dangeon.util.Damage;
import dangeon.util.R;

public class モノカの杖 extends Staff {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final ITEM_CASE item_case = ITEM_CASE.STAFF;
	public static final String item_name = "モノカの杖";
	public static final String item_name_sound = "ものかのつえ";

	public モノカの杖(Point p) {
		super(p, item_name, 1);
	}

	@Override
	protected void effect(Base_Creature c) {
		if (c instanceof Base_Enemy && new R().is(20)) {
			SE.MIRACLE_ONIGIRI.play();
			Message.set(c.getColoredName(), "はアイテムになった");
			TaskOnMapObject.addEnemyRemoveTask(c);
			ItemFall.itemFall(ItemTable.itemReturn(c.getMassPoint(), false));
		} else {
			Damage.damage(used_creature, null, null, used_creature, c,
					c.getHP() - 1);
		}
		// Message.set("に致命的なダメージを与えた");
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "電気は伝播するので、気をつけよう";
	}

}