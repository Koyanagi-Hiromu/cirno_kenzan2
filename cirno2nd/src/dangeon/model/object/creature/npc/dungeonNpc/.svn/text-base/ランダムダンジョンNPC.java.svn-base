package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.field.random.second.てゐの試験ダンジョン;

public class ランダムダンジョンNPC extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public ランダムダンジョンNPC(Point p) {
		super(p, "因幡てゐ", CHARA_IMAGE.因幡てゐ, "ちょっと潜ってみないうさ？", てゐの試験ダンジョン.ME);
	}

	@Override
	protected void intoDungeon() {
		super.intoDungeon();
	}

	@Override
	public void message() {
		say("ダンジョンを掘っていたら、よく分からない滅茶苦茶なダンジョンに繋がったうさ");
		super.message();
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