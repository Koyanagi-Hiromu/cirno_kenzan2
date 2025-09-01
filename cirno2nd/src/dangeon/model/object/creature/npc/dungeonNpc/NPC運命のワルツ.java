package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.Config;
import dangeon.model.config.StoryManager;
import dangeon.model.map.field.random.運命のワルツ;
import main.res.CHARA_IMAGE;
import main.res.SE;

public class NPC運命のワルツ extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public NPC運命のワルツ(Point p) {
		super(p, "Exルーミア", CHARA_IMAGE.Exルーミア, "・・・・進むのか？", new 運命のワルツ());
	}

	@Override
	protected String getMsg_cancel() {
		return "そうか";
	}

	@Override
	protected String getMsg_overItems() {
		return "*アイテムは持ち込めない*";
	}

	@Override
	protected void intoDungeon() {
		if (Config.getFate() == 2 && !StoryManager.運命のワルツclear.hasFinished()) {
			SE.SYSTEM_CURSE.play();
			Message.set("*この難度の運命にはまだ挑めない*");
		} else {
			super.intoDungeon();
		}
	}

	@Override
	protected void preCreaterFirstMap() {
	}

	@Override
	protected void sayBrief() {
		talks("最難関未識別９９Ｆダンジョンだ");
		talks("時々レベルが１つ高い敵が出現するよ");
		talks("しかもボスも特定階層にいるから気をつけてね");
		talks(true, "まずは倉庫にカードを置きに戻ろう");
	}

	@Override
	protected void sayFirst() {
		say("・・・・・");
		say("・・・・・ここが終焉");
		say("・・・・・ここまで来るのにたくさんあった");
		say("・・・・・振り返ればそれはまるで");
		say(true, "・・・・・【運命のワルツ】");
	}
}
