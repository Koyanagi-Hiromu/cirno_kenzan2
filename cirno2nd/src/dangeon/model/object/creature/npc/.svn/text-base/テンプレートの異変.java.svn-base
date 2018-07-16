package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.second.鬼の宴会場;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.util.ItemTemplate;

public class テンプレートの異変 extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public テンプレートの異変(Point p) {
		super(p, "伊吹萃香", CHARA_IMAGE.伊吹萃香, false);
	}

	@Override
	public void message() {
		// Message.setImageLargeCharacter(Image_LargeCharacter.チルノテス);
		// if (Config.getReimuEvent() < 10000) {
		Message.set("鬼の宴会場に行く？（全10Ｆ）$", "アイテムが一切落ちてないから注意ね～@");
		// }
		new Base_SelectBox("はい", "やめる") {
			@Override
			public void pushEnter(int y) {
				if (y == 0) {
					end();
					select();
				} else {
					end();
				}
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

	public void select() {
		Message.set("どのセットで行くの？@");
		new Base_SelectBox("書チーム", "妖々夢チーム", "紅魔郷チーム", "１ボスチーム") {
			@Override
			public void pushEnter(int y) {
				start(y);
				end();
			}
		};
	}

	public void start(int i) {
		Message.set("いってらっしゃ～い");
		Belongings.allRemove();
		SE.YUKARI_SPELL.play();
		Player.me.resetStatus();
		Enchant.allRemove();
		Belongings.setListItems(ItemTemplate.ENKAI.select(i));
		for (Base_Artifact a : 鬼の宴会場.ME.firstItems()) {
			Belongings.setItems(a);
		}
		new MassCreater(鬼の宴会場.ME, null, true).createFirstMap(0);
	}
}
