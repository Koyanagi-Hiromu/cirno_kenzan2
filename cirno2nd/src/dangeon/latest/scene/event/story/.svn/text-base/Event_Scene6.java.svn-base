package dangeon.latest.scene.event.story;

import java.awt.Color;

import main.res.BGM;
import main.res.Image_LargeCharacter;
import main.util.BlackOut;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.special.map.BossMap;

public class Event_Scene6 extends Event_Scene {

	/**
	 * 生成するだけでシーン切り替え
	 * 
	 * @param bMR
	 * @param bmr
	 */
	public Event_Scene6(BossMap bMR) {
		super(6, bMR, null);
	}

	@Override
	protected void end() {
	}

	@Override
	protected void message() {
		say("この最強となった私をどうやって倒すって？");
		setRep(Image_LargeCharacter.チルノ);
		rep("・・・");
		ConvEvent cne1 = new ConvEvent() {
			@Override
			public void workAfterPush() {
				BGM.kanpyo_ch2_fairy_starsapphire.play();
			}
		};
		new Conversation("アンタなにも分かってないわね", cne1);
		rep(Conversation.SLOW_REGEX, Color.YELLOW.toString(), "教えてあげるわ！");
		// rep("ふふふ、アンタなにも分かってないわね");
		// new Conversation("教えてあげるわ", cne1);
		rep(Conversation.SLOW_REGEX, Color.YELLOW.toString(),
				"あたいの方がより最強ってことをね！");
		say("・・・あっはは！");
		say("いいわ！　かかって来なさい！");
		ConvEvent cne = new ConvEvent() {
			@Override
			public void workAfterPush() {
				new BlackOut(bm, new Task() {
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						setNextScene(getPreviousScene());
						MassCreater mc = new MassCreater(bm, false);
						mc.createMap();
					}
				});
			}
		};
		new Conversation(Image_LargeCharacter.鬼人正邪, cne,
				Conversation.SLOW_REGEX, Color.RED.toString(), "地上まで逆戻りさせてあげる！");
	}

}
