package dangeon.latest.scene.event.story;

import main.res.BGM;
import main.res.Image_LargeCharacter;
import dangeon.model.map.field.Base_Map;

public class Event_Scene2 extends Event_Scene {

	/**
	 * 生成するだけでシーン切り替え
	 * 
	 * @param bmr
	 */
	public Event_Scene2(Base_Map bm) {
		super(2, bm, BGM.chirno);
	}

	@Override
	protected void message() {
		// Image_LargeCharacter i = null;
		// new Conversation(i, "ある晴れた昼下がりのこと");
		say("な、なにぃー！　もう追いついただと！？");
		setRep(Image_LargeCharacter.チルノ);
		rep("だいちゃんを返せーー！！");
		say("ええい！$少名針妙丸《すくなしんみょうまる》！");
		say("我らが悲願を叶えるため時間を稼ぐんだ！");
		setTalks(Image_LargeCharacter.少名針妙丸);
		talks("まっかせなさい！");
		setRep(Image_LargeCharacter.チルノ);
		rep("誰であろうと邪魔をするなら・・・");
		rep("イギリス牛のようにほふるまでよ！");
		set("立ちはだかるは険しいイッスンの壁！@");
		set("チルノの進撃は食い止められてしまうのか！？@");
		// set(StringFilter.NUMBERS.toString(), "チルノ見参２！　はじまりはじまり～～@");
		// setRep(Image_LargeCharacter.チルノ);
		// rep("だ、だいちゃーーん！！");
		// rep("・・・");
		// rep("・・・");
		// rep("よし、助けに行こう！");
		// set("前作チルノ見参！では偶然降ってきた流れ星にひっかきまわされた大妖精！@");
		// set("今回は鬼人正邪《きじんせいじゃ》にさらわれてしまった！@");
		// set("チルノは無事大妖精を助けられるのか！@");
		// set("大妖精の運命やいかに！@");
		// set(StringFilter.NUMBERS.toString(), "チルノ見参２！　はじまりはじまり～～@");
	}

}
