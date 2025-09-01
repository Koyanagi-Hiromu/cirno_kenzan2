package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.field.random.ミラクルクエスト;
import dangeon.util.R;
import main.res.CHARA_IMAGE;

public class NPCミラクルクエスト extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public NPCミラクルクエスト(Point p) {
		super(p, "東風谷早苗", CHARA_IMAGE.東風谷早苗, "ミラクエに行くのですね！", new ミラクルクエスト());
	}

	@Override
	protected String getMsg_overItems() {
		return "アイテムは持ち込めません！";
	}

	@Override
	protected void preCreaterFirstMap() {
		int r = new R().nextInt(5);
		if (r == 1) {
			Message.set("最後にヒソウテンソクが挑戦者を待っています！");
		} else if (r == 2) {
			Message.set("ミラクエが流行すれば回りまわって信心ゲットです！");
		} else if (r == 3) {
			Message.set("時々壁の中に賽銭箱が埋まっているそうですよ");
		} else if (r == 4) {
			Message.set("氷の罠は存在しないようですよ");
		} else {
			Message.set("それぞれのクラスには出現条件があるそうですよ");
		}
	}

	@Override
	protected void sayBrief() {
		talks("初めにクラスを選択して挑む、特殊な未識別９９Ｆダンジョンだ！");
		talks("それぞれのクラスには特徴的なメリットがあるぞ");
		talks("例えばクラス【守矢神】を選択すると３枚のカードをもらえるぞ");
		talks(true, "ただし【代償】もあるのでよく考えて選択してね");
	}

	@Override
	protected void sayFirst() {
		say("チルノさん、聞きましたよ！");
		say("天邪鬼をコテンパンにやっつけたそうですね！");
		say("まるでどこぞの勇者のようです！");
		say("そこで私はインスピレーションを受けました！");
		say("その名も【ミラクルクエスト】！");
		say("最初にクラスを選択して冒険を始めてもらいます");
		say(true, "それぞれのクラスの特性をうまく活かしてクリアしてください！");
	}
}
