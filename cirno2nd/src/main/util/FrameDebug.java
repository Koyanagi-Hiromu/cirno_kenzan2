package main.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

import main.thread.MainThread;

/**
 * メニュー層が1フレーム消える問題の調査用（調査が終わったら ENABLED = false にするか削除）
 *
 * 記録する内容:
 * - 毎フレーム: 描画されたシーンのクラス名とフレーム処理時間
 * - シーン切替: 切替元/切替先/呼び出しスレッド/スタックトレース
 * - キー入力: ACTION がどのシーンに渡ったか
 *
 * 自動ダンプ条件:
 * - 1フレームに100ms以上かかったとき
 * - 「メニュー → Scene_Action → メニュー」を3フレーム以内で往復したとき（=メニューのチラつき）
 * 出力先: コンソール + カレントディレクトリの frame_debug.txt
 */
public class FrameDebug {

	public static final boolean ENABLED = true;

	private static final int SIZE = 300;
	private static final long DUMP_LIMIT_MS = 100;
	private static final int FLICKER_FRAMES = 3;
	private static final long DUMP_COOLDOWN_NS = 3000000000L;

	private static final String[] ring = new String[SIZE];
	private static int ring_index = 0;

	private static long frame_start_ns = System.nanoTime();
	private static String drawn_scene = "";
	private static String last_drawn = "";
	private static int frames_on_current_scene = 0;
	private static String scene_before_action = null;
	private static long last_dump_ns = 0;

	private FrameDebug() {
	}

	public static synchronized void log(String msg) {
		if (!ENABLED)
			return;
		ring[ring_index] = "F" + MainThread.getFrame() + " ["
				+ Thread.currentThread().getName() + "] " + msg;
		ring_index = (ring_index + 1) % SIZE;
	}

	/** SceneHolder_KeyAccepter.setKeyAccepter から呼ぶ */
	public static void sceneChange(Object from, Object to) {
		if (!ENABLED)
			return;
		StringBuilder sb = new StringBuilder("SCENE_CHANGE ");
		sb.append(name(from)).append(" -> ").append(name(to));
		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		for (int i = 3; i < st.length && i < 10; i++) {
			sb.append("\n\t\tat ").append(st[i]);
		}
		log(sb.toString());
	}

	/** SceneHolder_KeyAccepter.action から呼ぶ */
	public static void action(Object action_enum, Object scene) {
		if (!ENABLED)
			return;
		log("ACTION " + action_enum + " -> " + name(scene));
	}

	/** SceneHolder_KeyAccepter.draw から呼ぶ（描画したシーンの記録＋チラつき検出） */
	public static synchronized void drawn(Object scene) {
		if (!ENABLED)
			return;
		String n = name(scene);
		drawn_scene = n;
		if (n.equals(last_drawn)) {
			frames_on_current_scene++;
			return;
		}
		boolean is_action = n.equals("Scene_Action");
		if (is_action) {
			scene_before_action = last_drawn;
			frames_on_current_scene = 0;
		} else {
			if (scene_before_action != null
					&& !scene_before_action.isEmpty()
					&& frames_on_current_scene < FLICKER_FRAMES) {
				last_drawn = n;
				dump("FLICKER: " + scene_before_action + " -> Scene_Action("
						+ (frames_on_current_scene + 1) + "frames) -> " + n);
			}
			scene_before_action = null;
		}
		last_drawn = n;
		frames_on_current_scene = 0;
	}

	/** MainThread のループ先頭で呼ぶ */
	public static void frameStart() {
		if (!ENABLED)
			return;
		frame_start_ns = System.nanoTime();
	}

	/** MainThread のループ末尾で呼ぶ */
	public static void frameEnd() {
		if (!ENABLED)
			return;
		long ms = (System.nanoTime() - frame_start_ns) / 1000000L;
		log("draw=" + drawn_scene + " " + ms + "ms");
		if (ms >= DUMP_LIMIT_MS) {
			dump("LONG_FRAME " + ms + "ms on " + drawn_scene);
		}
	}

	public static synchronized void dump(String reason) {
		if (!ENABLED)
			return;
		long now = System.nanoTime();
		if (now - last_dump_ns < DUMP_COOLDOWN_NS)
			return;
		last_dump_ns = now;
		StringBuilder sb = new StringBuilder();
		sb.append("==== FrameDebug dump : ").append(reason).append(" : ")
				.append(new Date()).append(" ====\n");
		for (int i = 0; i < SIZE; i++) {
			String s = ring[(ring_index + i) % SIZE];
			if (s != null) {
				sb.append(s).append('\n');
			}
		}
		sb.append("==== end of dump ====\n");
		System.out.println(sb);
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("frame_debug.txt",
					true));
			pw.print(sb);
			pw.close();
		} catch (Exception e) {
			// 調査用なので失敗は無視
		}
	}

	private static String name(Object o) {
		if (o == null)
			return "null";
		String s = o.getClass().getSimpleName();
		return s.isEmpty() ? o.getClass().getName() : s;
	}
}
