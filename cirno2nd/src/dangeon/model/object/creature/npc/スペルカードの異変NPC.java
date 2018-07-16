package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.config.Config;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.スペルカードの異変;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;

public class スペルカードの異変NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public スペルカードの異変NPC(Point p) {
		super(p, "因幡てゐ", CHARA_IMAGE.因幡てゐ, false);
	}

	@Override
	public void message() {
		Message.setImageLargeCharacter(Image_LargeCharacter.因幡てゐ);
		if (Config.getReimuEvent() < 10000) {
			Message.set("スペルカードの異変？$（全３０Ｆ）@");
		}
		new Base_SelectBox("はい", "やめる") {
			@Override
			public void pushEnter(int y) {
				if (y == 0) {
					SE.YUKARI_SPELL.play();
					Player.me.resetStatus();
					Enchant.allRemove();
					for (Base_Artifact a : スペルカードの異変.ME.firstItems()) {
						Belongings.setItems(a);
					}
					new MassCreater(スペルカードの異変.ME, null, true).createFirstMap(0);
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
