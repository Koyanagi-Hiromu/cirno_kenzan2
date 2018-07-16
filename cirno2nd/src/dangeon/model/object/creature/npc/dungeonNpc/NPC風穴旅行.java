package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.condition.CONDITION;
import dangeon.model.map.field.random.風穴旅行;

public class NPC風穴旅行 extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public NPC風穴旅行(Point p) {
		super(p, "古明地こいし", CHARA_IMAGE.古明地こいし, "地霊殿に向かうの？", new 風穴旅行());
		setCondition(CONDITION.透明, 0);
	}

	@Override
	protected void preCreaterFirstMap() {
		Message.set("頑張ってね！");
	}

	@Override
	protected void sayBrief() {
		talks("モンハウだらけの３０Ｆダンジョンだ！");
		talks("敵も濃密に詰まっていて危険な道のりになるぞ");
		talks(true, "でも別にこいしは一緒に来てくれるわけではないようです");
	}

	@Override
	protected void sayFirst() {
		say("こんにちは！　私こいし！");
		say("私のペットにならない？");
		say("・・・");
		say("えー　残念！");
		say("ねえ貴方、地霊殿まで行ってみない？");
		say(true, "お姉ちゃんもきっと歓迎してくれるよ！");
	}
}
