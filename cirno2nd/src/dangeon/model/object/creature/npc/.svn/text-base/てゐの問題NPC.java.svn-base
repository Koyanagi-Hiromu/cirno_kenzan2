package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.random.てゐの問題;
import dangeon.model.object.artifact.item.food.大きなおにぎり;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;

public class てゐの問題NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public てゐの問題NPC(Point p) {
		super(p, "てゐ", CHARA_IMAGE.因幡てゐ, false);
	}

	@Override
	public void message() {
		Message.setImageLargeCharacter(Image_LargeCharacter.因幡てゐ);
		Message.set("ダンジョンを体験するウサ？$（10F持ち込み可ダンジョン）@");
		Message.removeILC();
		new Base_SelectBox("うん", "やめる") {
			@Override
			public void pushEnter(int y) {
				if (y == 0) {
					Player.me.resetStatus();
					Enchant.allRemove();
					if (!Belongings.isMax()) {
						Belongings.setItems(new 大きなおにぎり(Player.me
								.getMassPoint()).createSpellCard(false, 0));
					}
					new MassCreater(てゐの問題.ME, null, true).createFirstMap(0);
					end();
				} else {
					end();
				}
			}
		};
	}
}
