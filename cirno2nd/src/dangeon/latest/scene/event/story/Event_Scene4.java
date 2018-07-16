package dangeon.latest.scene.event.story;

import main.res.BGM;
import main.res.Image_LargeCharacter;
import main.util.BlackOut;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.field.Base_Map;

public class Event_Scene4 extends Event_Scene {

	private final Task task;

	/**
	 * 生成するだけでシーン切り替え
	 * 
	 * @param bMR
	 * @param bmr
	 */
	public Event_Scene4(Base_Map bMR, Task task) {
		super(4, bMR, BGM.to_kou_chiruno);
		this.task = task;
	}

	@Override
	protected void end() {
		Message.setTask_AfterReleaseDemandToPushEnter(new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				BGM.waitUntilFadeOut_Thread();
				new BlackOut(new Task() {
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						task.work();
					}
				});
			}
		});
	}

	@Override
	protected void message() {
		// Image_LargeCharacter.プルートJの欠片.convSlideIN(true);
		// say("あはは！　これが流れ星のちから！");
		ConvEvent cne = new ConvEvent() {
			@Override
			public void workAtTheSameTime() {
				Image_LargeCharacter.プルートJの欠片.convSlideIN(true);
			}
		};
		new Conversation(Image_LargeCharacter.鬼人正邪, cne, "あはは！　これが流れ星のちから！");
		say("わたしの願いをいともたやすく叶えてくれたわ！");
		setRep(Image_LargeCharacter.チルノ);
		rep("くそ～あれは大ちゃんのものなのに…！");
		rep("絶対取り返しに行くからなー！");
		set("突如として現れた空に浮かぶ逆ヶ島《さかさがしま》@");
		set("友達の思い出を取り返すと固く誓うチルノであった！@");
	}

}
