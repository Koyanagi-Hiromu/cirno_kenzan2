package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.model.map.field.random.二撃必殺;

public class NPC二撃 extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public NPC二撃(Point p) {
		super(p, "星熊勇儀", CHARA_IMAGE.星熊勇儀, "ん？　二撃必殺に挑むのか？", new 二撃必殺());
	}

	@Override
	protected String getMsg_cancel() {
		return "あっはっは　怖気づいちゃったかい？";
	}

	@Override
	protected void sayBrief() {
		talks("エネミーもチルノもどんな攻撃をうけてもＨＰが１になるダンジョンだ！");
		talks("もちろんＨＰ１からもう一度くらうと倒れるよ");
		talks(true, "初めにもらえる霊夢の結界リボンをうまく使おう！");
	}

	@Override
	protected void sayFirst() {
		say("よぉ　天邪鬼をこらしめたんだってな！");
		say("よしよしよくやったぞ");
		say("そこでだついでにこのダンジョンも遊んでみないか？");
		say(true, "スリル満点だよ");
	}
}
