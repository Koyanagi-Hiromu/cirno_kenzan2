package dangeon.latest.scene.event.story;

import main.res.BGM;
import main.res.Image_LargeCharacter;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.map.field.Base_Map;

public class Event_Scene3 extends Event_Scene {

	private final Base_Map bMR;
	private final Task task;

	/**
	 * 生成するだけでシーン切り替え
	 * 
	 * @param bMR
	 * @param bmr
	 */
	public Event_Scene3(Base_Map bMR, Task task) {
		super(3, null, BGM.to_kou_chiruno);
		this.bMR = bMR;
		this.task = task;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void message() {
		set("なんと階段を下った先には大妖精が解放されていた@");
		setRep(Image_LargeCharacter.チルノ);
		rep("だいちゃん！");
		setTalks(Image_LargeCharacter.大妖精);
		talks("助けに来てくれてありがと・・・");
		talks("でも、ぅぅぅ・・・");
		ConvEvent cne = new ConvEvent() {
			@Override
			public void workAfterPush() {
				new Event_Scene4(bMR, task);
			}
		};
		new Conversation(Conversation.previous_opponent, cne,
				"プルートさんからもらった流れ星のかけらを取られちゃったよぉ・・・");
	}

}
