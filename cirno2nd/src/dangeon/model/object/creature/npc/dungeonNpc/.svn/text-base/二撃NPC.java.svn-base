package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.field.random.second.四天王ロード;

public class 二撃NPC extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public 二撃NPC(Point p) {
		super(p, "星熊勇儀", CHARA_IMAGE.星熊勇儀, "ん？　四天王ロードに挑むのか？", 四天王ロード.ME);
	}

	@Override
	protected String getMsg_cancel() {
		return "あっはっは　怖気づいちゃったかい？";
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
