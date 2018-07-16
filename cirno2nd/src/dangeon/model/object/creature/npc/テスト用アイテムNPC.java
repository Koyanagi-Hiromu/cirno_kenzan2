package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.staff.いかづちの杖;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;

public class テスト用アイテムNPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private boolean item_stack_flag;

	public テスト用アイテムNPC(Point p) {
		super(p, "リグル", CHARA_IMAGE.リグルナイトバグ, false);
	}

	@Override
	public void message() {
		Message.setImageLargeCharacter(Image_LargeCharacter.リグル);
		Message.set("アイテムを渡すよ！");
		Belongings.setItems(new いかづちの杖(Player.me.getMassPoint()));
		Message.removeILC();
	}
}
