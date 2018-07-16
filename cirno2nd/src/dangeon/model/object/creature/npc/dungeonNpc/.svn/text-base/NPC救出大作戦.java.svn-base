package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.SE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.field.random.救出大作戦;

public class NPC救出大作戦 extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public NPC救出大作戦(Point p) {
		super(p, "レティ", CHARA_IMAGE.レティ, "大妖精をひとりで助けに行くのか？", "ダンジョンに挑むのか？",
				new 救出大作戦());
	}

	@Override
	protected SE getSE() {
		return null;
	}

	@Override
	protected void preCreaterFirstMap() {
		Message.set("かきごおりを食べると体力が回復するからな");
	}

	@Override
	protected void sayBrief() {
		talks("チルノ見参２最初のダンジョンだ！");
		talks(true, "ダンジョンの最後にボスがいるから気をつけよう！");
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
