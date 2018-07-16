package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.latest.scene.action.message.Message;

public class 何でも装備NPC extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public 何でも装備NPC(Point p) {
		super(p, "宮古芳香", CHARA_IMAGE.宮古芳香, "神霊廟の地下へ向かうのかー？", null);
	}

	@Override
	protected void sayBrief() {
		Message.set("救出大作戦に挑めるようになった！@");
	}

	@Override
	protected void sayFirst() {
		say("大妖精を助けに行くんだな？");
		say("よし、それなら私も・・・");
		say("え？　なに？　どうしてもひとりで行きたい？");
		say("うーん分かったよ");
		say(true, "だけど私の魔法瓶は持って行ってもらうからな");
	}
}
