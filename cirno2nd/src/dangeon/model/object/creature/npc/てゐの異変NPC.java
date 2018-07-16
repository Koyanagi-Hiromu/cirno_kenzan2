package dangeon.model.object.creature.npc;

import java.awt.Point;
import java.util.ArrayList;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.res.SE;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.selectbox.SelectBox;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.てゐの異変;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;

public class てゐの異変NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public てゐの異変NPC(Point p) {
		super(p, "因幡てゐ", CHARA_IMAGE.因幡てゐ, false);
	}

	@Override
	public void message() {
		Message.setImageLargeCharacter(Image_LargeCharacter.因幡てゐ);
		// if (Config.getReimuEvent() < 10000) {
		// MessageRecord.setMessageTask("罠師のダンジョン？$（全３０Ｆ／持ち込み１０コ以下）@");
		// }
		new SelectBox() {

			@Override
			protected void initializeContents(ArrayList<MenuContent> list) {
				setContents("はい", new Book() {

					@Override
					protected void work() {
						if (Belongings.getSize() > 10) {
							Message.set("アイテムは１０コまでよ？");
						} else {
							SE.YUKARI_SPELL.play();
							Player.me.resetStatus();
							Enchant.allRemove();
							for (Base_Artifact a : てゐの異変.ME.firstItems()) {
								Belongings.setItems(a);
							}
							new MassCreater(てゐの異変.ME, null, true)
									.createFirstMap(0);
						}
					}
				});
				setContents("いいえ");
			}

		};

		// new Base_SelectBox("はい", "やめる") {
		// @Override
		// public void pushEnter(int y) {
		// if (y == 0) {
		// if (Belongings.getSize() > 10) {
		// MessageRecord.setMessageTask("アイテムは１０コまでよ？");
		// } else {
		// SE.YUKARI_SPELL.play();
		// Player.me.resetStatus();
		// Enchant.allRemove();
		// for (Base_Artifact a : てゐの異変.ME.firstItems()) {
		// Belongings.setItems(a);
		// }
		// new MassCreater(てゐの異変.ME, null, true).createFirstMap(0);
		// }
		// }
		// end();
		// }
		// };
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
