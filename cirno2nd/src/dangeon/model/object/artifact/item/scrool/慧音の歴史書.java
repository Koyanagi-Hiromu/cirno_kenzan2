package dangeon.model.object.artifact.item.scrool;

import java.awt.Point;

import main.res.SE;
import dangeon.controller.TaskOnMapObject;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.table.EnemyTable;
import dangeon.model.map.MapList;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.Base_Creature;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;

public class 慧音の歴史書 extends Scrool {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String item_name = "幻想郷縁起";
	public static final String item_name_sound = "けんそうきょうえんき";

	public 慧音の歴史書(Point p) {
		super(p, item_name);
		sim = ENCHANT_SIMBOL.識別;
	}

	@Override
	protected String getSecondExplain_ByEach() {
		return "様々な事が書いてある";
	}

	@Override
	public void itemHit(Base_Creature c, Base_Creature c2) {
		if (c instanceof Player || c instanceof Base_NPC) {
			super.itemHit(c, c2);
			return;
		}
		staticCheck();
		SE.BURN.play();
		EnemyTable.ねだやし(c);
		for (Base_Creature _c : MapList.getListEnemy()) {
			if (!EnemyTable.ねだやしチェック(_c)) {
				continue;
			}
			TaskOnMapObject.addEnemyRemoveTask(_c);
		}
		Message.set(c.getColoredName(), "を根絶やした");
	}

	@Override
	public void scroolUse() {
		SE.CHECK.play();
		Message.set("持ち物全てが識別された");
		for (Base_Artifact a : Belongings.getListItems_except(this)) {
			a.check();
		}
	}
}
