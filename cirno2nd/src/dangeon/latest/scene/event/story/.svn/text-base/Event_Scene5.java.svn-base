package dangeon.latest.scene.event.story;

import main.res.Image_LargeCharacter;
import main.res.SE;
import main.util.DIRECTION;
import main.util.FrameShaker;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.map.field.special.map.BossMap;
import dangeon.model.object.creature.player.Player;

public class Event_Scene5 extends Event_Scene {

	private final BossMap bMR;

	/**
	 * 生成するだけでシーン切り替え
	 * 
	 * @param bossMap
	 * @param bmr
	 */
	public Event_Scene5(BossMap bossMap) {
		super(5, null, null);
		Player.me.direction = DIRECTION.UP;
		this.bMR = bossMap;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void message() {
		Image_LargeCharacter i = Image_LargeCharacter.ANY;
		new Conversation(i, "よくここまで来たな・・・！");
		new Conversation(i, "しかしお前のような小さな存在が来ていい場所ではない。");
		new Conversation(i, "即刻立ち去れい！");
		setRep(Image_LargeCharacter.チルノ);
		rep("うるさい！　さっさと大ちゃんの宝物を返せ！");
		new Conversation(i, "私はひとが嫌がることを進んでする妖怪");
		new Conversation(i, "まったくもって返すわけがない");
		rep("だったら手っ取り早く奪い取るまでよ！");
		new Conversation(i, "・・・");
		ConvEvent cne = new ConvEvent() {
			@Override
			public void workAfterPush() {
				SE.GOGOGO.play();
				FrameShaker.setShake(3);
				FrameShaker.setShake_time(FrameShaker.SHAKE_INTERVAL * 10);
				new Event_Scene6(bMR);
			}
		};
		new Conversation(i, cne, "あははは！");
	}

}
