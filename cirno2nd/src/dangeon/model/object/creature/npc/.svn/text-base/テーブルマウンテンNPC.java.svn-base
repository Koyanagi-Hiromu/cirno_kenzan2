package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.隕石異変;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;

public class テーブルマウンテンNPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public テーブルマウンテンNPC(Point p) {
		super(p, "ゆっくり", CHARA_IMAGE.ゆっくり霊夢, false);
	}

	@Override
	public void message() {
		Message.set("隕石異変？%（30F持ち込み可ダンジョン）");
		new Base_SelectBox("持ち物持込", "やめる") {
			@Override
			public void pushEnter(int y) {
				if (y == 0) {
					Player.me.resetStatus();
					Enchant.allRemove();
					if (!Belongings.isMax()) {
						Belongings.setItems(new 大きなおにぎり(Player.me
								.getMassPoint()).createSpellCard(false, 0));
					}
					new MassCreater(隕石異変.ME, null, true).createFirstMap(0);
					end();
				} else {
					end();
				}
			}
		};
		// new SelectBox_YesNo() {
		// @Override
		// public void yes() {
		// for (Enchant e : Enchant.values()) {
		// e.forceToRemove();
		// }
		// Belongings.getListItems().clear();
		// Belongings.setItems(new 大きなおにぎり(Player.me.getMassPoint()));
		// new MassCreater(慧音の最終問題.ME, null, true).createMap();
		// }
		// };
	}
}
