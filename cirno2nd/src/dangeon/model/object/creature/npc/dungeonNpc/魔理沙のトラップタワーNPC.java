package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.model.map.field.random.second.魔理沙のトラップタワー;

public class 魔理沙のトラップタワーNPC extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public 魔理沙のトラップタワーNPC(Point p) {
		super(p, "霧雨魔理沙", CHARA_IMAGE.霧雨魔理沙, "挑戦するのか？", new 魔理沙のトラップタワー());
	}

	@Override
	protected void sayBrief() {
		talks("罠を利用して進む３０Ｆダンジョンだ");
		talks("階層に見合わない強い敵が出るけど");
		talks(true, "うまく敵を罠にかけて突破しよう！");
	}

	@Override
	protected void sayFirst() {
		say("お、チルノじゃないか");
		say("「罠師」って知ってるか？");
		say(true, "罠道を極めた者のことなんだが、チルノも目指してみないか？");
	}
}
