package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.field.random.second.チルノ見参;

public class チルノ見参NPC extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public チルノ見参NPC(Point p) {
		super(p, "チルノ", CHARA_IMAGE.チルノ, "チルノ見参！", チルノ見参.ME);
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
