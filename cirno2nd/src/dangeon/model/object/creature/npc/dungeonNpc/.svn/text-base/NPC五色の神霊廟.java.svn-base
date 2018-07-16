package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.field.random.second.五色の神霊廟;

public class NPC五色の神霊廟 extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public NPC五色の神霊廟(Point p) {
		super(p, "豊聡耳神子", CHARA_IMAGE.豊聡耳神子, "五色の神霊廟に挑戦するのですね？", new 五色の神霊廟());
	}

	@Override
	protected String[] getMsg_overItems() {
		return new String[] { "アイテムは１０コまでしか持ち込めませんよ" };
	}

	@Override
	protected void preCreaterFirstMap() {
		Message.set("装備しているカードも使用できるのをお忘れなく");
	}

	@Override
	protected void sayBrief() {
		talks("持っているアイテムの印が全部発揮されちゃうダンジョンだ！");
		talks("適当にアイテムを拾うだけでチルノがとっても強くなるぞ");
		talks(true, "それとカードがたくさん落ちてるからジャンジャン使用してみよう");
	}

	@Override
	protected void sayFirst() {
		say("ふむ、みなまで聞かなくとも分かります");
		say("カードの強さを実感したいのですね？");
		say(true, "それならばここ、五色の神霊廟《ごしきのしんれいびょう》がオススメです");
	}
}
