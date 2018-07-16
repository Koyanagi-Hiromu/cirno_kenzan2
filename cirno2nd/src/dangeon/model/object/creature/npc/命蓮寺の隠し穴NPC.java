package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.命蓮寺の隠し穴;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;

public class 命蓮寺の隠し穴NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 命蓮寺の隠し穴NPC(Point p) {
		super(p, "寅丸星", CHARA_IMAGE.寅丸星, false);
	}

	@Override
	public void message() {
		Message.setImageLargeCharacter(Image_LargeCharacter.寅丸星);
		Message.set("命蓮寺の隠し穴に入るか？$（全３０Ｆ）@");
		new Base_SelectBox("はい", "やめる") {
			@Override
			public void pushEnter(int y) {
				if (y == 0) {
					SE.YUKARI_SPELL.play();
					Player.me.resetStatus();
					Enchant.allRemove();
					if (!Belongings.isMax()) {
						for (Base_Artifact a : 命蓮寺の隠し穴.ME.firstItems()) {
							Belongings.setItems(a);
						}
					}
					new MassCreater(命蓮寺の隠し穴.ME, null, true).createFirstMap(0);
				}
				end();
			}
		};
		Message.removeILC();
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
