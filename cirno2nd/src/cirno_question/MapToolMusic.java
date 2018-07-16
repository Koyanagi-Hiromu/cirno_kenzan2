package cirno_question;

import java.util.ArrayList;

import main.res.BGM;

public class MapToolMusic {
	public static MapToolMusic ME = new MapToolMusic();

	public String[] getPlayList() {
		ArrayList<String> play_list = new ArrayList<String>();
		for (BGM bgm : BGM.values()) {
			if (bgm.TITLE == null) {
				continue;
			}
			play_list.add(bgm.TITLE);
		}
		String[] array = play_list.toArray(new String[0]);
		return array;
	}

	public void play(String str) {
		for (BGM bgm : BGM.values()) {
			if (bgm.TITLE == null) {
				continue;
			}
			if (bgm.TITLE.equals(str)) {
				bgm.play();
				return;
			}
		}
	}
}
