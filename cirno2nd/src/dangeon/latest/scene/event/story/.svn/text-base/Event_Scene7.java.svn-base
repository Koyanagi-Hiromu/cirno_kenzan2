package dangeon.latest.scene.event.story;

import main.res.BGM;
import main.res.Image_LargeCharacter;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.scene.event.staffroll.StaffRoll;
import dangeon.model.map.field.Base_Map;
import dangeon.view.util.StringFilter;

public class Event_Scene7 extends Event_Scene {

	private final Base_Map bMR;
	private final Task task;

	/**
	 * 生成するだけでシーン切り替え
	 * 
	 * @param base_Map
	 * @param bmr
	 */
	public Event_Scene7(Base_Map base_Map, Task task) {
		super(7, null, BGM.kanpyo_ch_plume);
		this.bMR = base_Map;
		this.task = task;
	}

	@Override
	protected void end() {
		Message.setTask_AfterReleaseDemandToPushEnter(new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				new StaffRoll(bMR, task);
			}
		});
	}

	@Override
	protected void message() {
		say("ちくしょー　妖精なんかに・・・");
		setRep(Image_LargeCharacter.チルノ);
		rep("何か言った？");
		say("イエ、ナニモ");
		rep("早く流れ星を返しなさい！");
		ConvEvent cne = new ConvEvent() {
			@Override
			public void workAtTheSameTime() {
				Image_LargeCharacter.プルートJの欠片.convSlideIN(true);
			}
		};
		new Conversation(Image_LargeCharacter.鬼人正邪, cne, "はい・・・");
		rep("素直でよろしい");
		rep("大ちゃん、いるんでしょ？");
		setRep(Image_LargeCharacter.大妖精);
		rep("うん");
		rep("隠れてたつもりだったけどよく分かったね");
		setRep(Image_LargeCharacter.チルノ);
		rep("まあね");
		rep("それより星を・・・");
		ConvEvent cne2 = new ConvEvent() {
			@Override
			public void workAtTheSameTime() {
				Image_LargeCharacter.プルートJの欠片.SlideOut();
				Image_LargeCharacter.大妖精.SlideOut();
			}
		};
		setRep(Image_LargeCharacter.大妖精);
		new Conversation("うん", cne2);
		setRep(Image_LargeCharacter.チルノ);
		rep("・・・");
		setRep(Image_LargeCharacter.Ex大妖精);
		rep("えへへ　よかった戻ってきて・・・");
		setRep(Image_LargeCharacter.チルノ);
		rep("じゃあ帰ろっか");
		setRep(Image_LargeCharacter.Ex大妖精);
		rep("うん！");
		// say("ちくしょー　爆発しろー");
		set("無事妖精たちは流れ星の欠片を取り返すことができました！@");
		set("しばらくしたら逆ヶ島も消えてなくなることでしょう@");
		set("めでたしめでたし@");
		set(StringFilter.NUMBERS.toString(), "チルノ見参２！　これにて終幕！@");
	}

}
