package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.item.pot.合成の瓶;
import dangeon.model.object.artifact.item.ring.ルーミアのリボン;
import dangeon.model.object.artifact.item.scrool.おはらいの書;
import dangeon.model.object.artifact.item.spellcard.ルーミアのカード;
import dangeon.model.object.artifact.item.spellcard.上白沢慧音のカード;
import dangeon.model.object.artifact.item.spellcard.九十九姉妹のカード;
import dangeon.model.object.artifact.item.spellcard.二ッ岩マミゾウのカード;
import dangeon.model.object.artifact.item.spellcard.堀川雷鼓のカード;
import dangeon.model.object.artifact.item.spellcard.封獣ぬえのカード;
import dangeon.model.object.artifact.item.spellcard.少名針妙丸のカード;
import dangeon.model.object.artifact.item.spellcard.鬼人正邪のカード;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;

public class デバッグ専用NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public デバッグ専用NPC(Point p) {
		super(p, "ミスティア", CHARA_IMAGE.ミスティア, false);
	}

	@Override
	public void message() {
		Message.setImageLargeCharacter(Image_LargeCharacter.みすちー);
		Message.set("新アイテムセットだよ！");
		Belongings.setItems(new 鬼人正邪のカード(Player.me.getMassPoint()));
		Belongings.setItems(new 少名針妙丸のカード(Player.me.getMassPoint()));
		Belongings.setItems(new 九十九姉妹のカード(Player.me.getMassPoint()));
		Belongings.setItems(new 堀川雷鼓のカード(Player.me.getMassPoint()));
		Belongings.setItems(new 合成の瓶(Player.me.getMassPoint(), 9));
		Belongings.setItems(new 上白沢慧音のカード(Player.me.getMassPoint()));
		Belongings.setItems(new 封獣ぬえのカード(Player.me.getMassPoint()));
		Belongings.setItems(new 二ッ岩マミゾウのカード(Player.me.getMassPoint()));
		Belongings.setItems(new ルーミアのカード(Player.me.getMassPoint()));
		Belongings.setItems(new ルーミアのリボン(Player.me.getMassPoint()));
		Belongings.setItems(new おはらいの書(Player.me.getMassPoint()));
		Message.removeILC();
	}
}
