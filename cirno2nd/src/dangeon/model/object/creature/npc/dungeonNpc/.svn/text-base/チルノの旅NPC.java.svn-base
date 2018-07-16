package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.field.random.緋想の地下;

public class チルノの旅NPC extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public チルノの旅NPC(Point p) {
		super(p, "比那名居天子", CHARA_IMAGE.比那名居天子, "緋想の地下に向かうのか", new 緋想の地下());
	}

	@Override
	protected String[] getMsg_overItems() {
		return new String[] { "アイテムは持って行けないよ@" };
	}

	@Override
	protected void preCreaterFirstMap() {
		Message.set("メニューの情報から識別状況が見れるから活用することね");
	}

	@Override
	protected void sayBrief() {
		talks("未識別＆持ち込み不可ダンジョンだ！");
		talks("内容の濃い５０Ｆです");
		talks(true, "ここをクリアできたら上級者！？");
	}

	@Override
	protected void sayFirst() {
		say("今日も絶好調ね！");
		say("緋想の剣を突き立てたらダンジョンが出来てしまったわ");
		say(true, "あんた折角だから挑んでみない？");
	}
}
