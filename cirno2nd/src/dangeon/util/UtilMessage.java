package dangeon.util;

import dangeon.latest.scene.action.message.Message;

public class UtilMessage {
	/**
	 * 汎用無効化メッセージ
	 * 
	 * @return
	 */
	public static void effectDefMsg() {
		Message.set("しかし何も起こらなかった");
	}

	/**
	 * 雛の汎用無効化メッセージ
	 * 
	 * @return
	 */
	public static void effectDefMsg_Hina() {
		Message.set("しかし神の加護があって大丈夫だった");
	}

	/**
	 * 汎用無効化メッセージ
	 * 
	 * @return
	 */
	public static void effectDefMsg_RIBON() {
		Message.set("しかしリボンの効果で無効化した");
	}
}
