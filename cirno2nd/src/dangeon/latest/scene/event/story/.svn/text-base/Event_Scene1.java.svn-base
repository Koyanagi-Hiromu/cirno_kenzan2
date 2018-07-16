package dangeon.latest.scene.event.story;

import main.res.Image_LargeCharacter;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.map.field.town.map.FairyPlace;
import dangeon.view.util.StringFilter;

public class Event_Scene1 extends Event_Scene {

	/**
	 * 生成するだけでシーン切り替え
	 */
	public Event_Scene1() {
		super(1, new FairyPlace());
	}

	@Override
	protected void message() {
		Image_LargeCharacter i = null;
		new Conversation(i, "ある晴れた昼下がりのこと");
		say("ひゃっほー！　大妖精ゲットだぜー！");
		setTalks(Image_LargeCharacter.Ex大妖精);
		talks("ひえ～～～");
		setRep(Image_LargeCharacter.チルノ);
		rep("だ、だいちゃーーん！！");
		rep("・・・");
		rep("・・・");
		rep("よし、助けに行こう！");
		set("前作では偶然落ちてきた流れ星にひっかきまわされた大妖精！@");
		set("今回は鬼人正邪《きじんせいじゃ》にさらわれてしまった！@");
		set("チルノは無事大妖精を助けられるのか！@");
		set("大妖精の運命やいかに！@");
		set(StringFilter.NUMBERS.toString(), "チルノ見参２！　はじまりはじまり～～@");
	}

}
