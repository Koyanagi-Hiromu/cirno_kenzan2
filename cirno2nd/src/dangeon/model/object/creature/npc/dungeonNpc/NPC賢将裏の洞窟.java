package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.model.map.field.random.second.賢将裏の洞窟;

public class NPC賢将裏の洞窟 extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public NPC賢将裏の洞窟(Point p) {
		super(p, "ナズーリン", CHARA_IMAGE.ナズーリン, "君も挑戦するのか？", new 賢将裏の洞窟());
	}

	@Override
	protected void sayBrief() {
		talks("罠を利用して進む３０Ｆダンジョンだ");
		talks(true, "普段より頭を使うかも？");
	}

	@Override
	protected void sayFirst() {
		say("やあ妖精、君はいつも楽しそうだね");
		say("ん？　この裏の道が気になるのかい？");
		say(true, "険しい道だけど止めはしないよ");
	}
}
