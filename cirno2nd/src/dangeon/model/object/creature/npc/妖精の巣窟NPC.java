package dangeon.model.object.creature.npc;

import java.awt.Image;
import java.awt.Point;

import main.res.Image_LargeCharacter;
import main.res.Image_Object;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.config.Config;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.妖精の巣窟;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;

public class 妖精の巣窟NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 妖精の巣窟NPC(Point p) {
		super(p, "チルノ", false);
		IM = null;
	}

	@Override
	public Image getImage() {
		return Image_Object.身代わり人形.getImage();
	}

	@Override
	public void message() {
		Message.setImageLargeCharacter(Image_LargeCharacter.チルノ);
		if (Config.getReimuEvent() < 10000) {
			Message.set("「妖精の巣窟」ニ行キマスカ？$（全３０Ｆ／持ち込み２０コ以下）@");
		}
		new Base_SelectBox("はい", "やめる") {
			@Override
			public void pushEnter(int y) {
				if (y == 0) {
					if (Belongings.getSize() > 20) {
						Message.set("アイテムハ２０コマデデス");
					} else {
						SE.YUKARI_SPELL.play();
						Player.me.resetStatus();
						Enchant.allRemove();
						for (Base_Artifact a : 妖精の巣窟.ME.firstItems()) {
							Belongings.setItems(a);
						}
						new MassCreater(妖精の巣窟.ME, null, true).createFirstMap(0);
					}
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
