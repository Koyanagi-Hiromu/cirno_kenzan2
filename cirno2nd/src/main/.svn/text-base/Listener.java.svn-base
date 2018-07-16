package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.StringTokenizer;

import main.pad.JInputWrapper;
import main.util.DIRECTION;
import dangeon.model.config.Config;

public class Listener implements KeyListener {
	public enum ACTION {
		//@formatter:off
		// メインコマンド
		ENTER("攻撃",KeyEvent.VK_Z, true, "決定キーです","敵に攻撃したりNPCに話しかけたりします"),
		CANCEL("組み合わせ",KeyEvent.VK_X,true,"キャンセルキーです","その他のキーと組み合わせて使います"),
		MENU("メニュー",KeyEvent.VK_V,  true,"メニューキーです","メニューを開きます","メニュー中はキャンセルキーとして働きます"),
		BIAS("斜め制限",KeyEvent.VK_CONTROL, true,"斜めキーです","押している最中は必ず斜めに歩きます","何回か押すことで斜めに振り向くこともできます"),
		TURN("方向転換",KeyEvent.VK_SHIFT, true, "方向キーです","押し続けることでその場で好きな方向を向けます","何回か押すことで近くの敵に振り向きます"),
		FIRE("射撃",KeyEvent.VK_F,true,"射撃キーです","セットしている射撃物を使用します","アイテムを複数選択します"),
		IDASH("あいダッシュ",KeyEvent.VK_C,true, "あいダッシュキーです","あたいにおまかせダッシュをします","長い通路などに便利です"),
		MAP("ミニマップ",KeyEvent.VK_D, true, "ミニマップキーです","ミニマップだけ表示します"),
		// ショートカットコマンド
		ITEM("道具一覧",KeyEvent.VK_A,       "キャンセル+メニュー","アイテムの一覧を開きます"),
		SYMBOL("印一覧",KeyEvent.VK_Q, "キャンセル+ミニマップ","識別済み印一覧を開きます"),
		GATHER("足下or拾う",KeyEvent.VK_G, "キャンセル+射撃","足下にあるアイテムを拾います","固定されている場合はコマンドを開きます"),
		RECORD("メッセージ",KeyEvent.VK_R, "キャンセル+方向","メッセージ履歴を開きます"),
		SPEC("特殊行動",KeyEvent.VK_S,       "キャンセル+あいダッシュ","特別なダンジョンでのみ使用します"),
		BGM("ＢＧＭ切替",KeyEvent.VK_B,  "","カードを付け替えた時のBGM設定を切り替えます"),
		OWATTA("オワッタ",KeyEvent.VK_O, "","チルノがオワッタポージングをします"),
		//キー説明用
		REST("ターン送り", "チルノが休息します"),
		MOVE("移動","チルノが歩きます"),
		DASH("ダッシュ", "チルノが走ります"),
		//没
		INFO(KeyEvent.VK_I,       "キャンセル+ミニマップ","情報"),
		WINDOW(KeyEvent.VK_Q, "フルスクリーン変更"),
		NAME(KeyEvent.VK_N, "ネームチェック"),
		SOUND(KeyEvent.VK_S, "音量調整"),
		//@formatter:on
		// テストコマンド
		TEST, TEST2, TEST3, TEST4, FUCKIN_MAP, ;
		public static ACTION[] getMainCommands() {
			return new ACTION[] { ENTER, CANCEL, TURN, MENU, BIAS, FIRE, MAP,
					IDASH };
		}

		public static ACTION[] getSubCommands() {
			return new ACTION[] { SPEC, ITEM, GATHER, SYMBOL, RECORD, BGM,
					OWATTA };
		}

		public static ACTION[] getVails() {
			ACTION[] m = getMainCommands();
			ACTION[] s = getSubCommands();
			ACTION as[] = new ACTION[m.length + s.length];
			for (int i = 0; i < m.length; i++) {
				as[i] = m[i];
			}
			for (int i = 0; i < s.length; i++) {
				as[i + m.length] = s[i];
			}
			return as;
		}

		public final int KEY_NUM;

		public final boolean IS_MAIN;
		public final String SHORT_EX;
		public final String EXP[];

		private ACTION() {
			this(-1);
		}

		private ACTION(int key_num) {
			this(key_num, "");
		}

		private ACTION(int key_num, String... exp) {
			this(null, key_num, exp);
		}

		private ACTION(String short_ex, int key_num, boolean is_main,
				String... exp) {
			EXP = exp;
			SHORT_EX = short_ex;
			IS_MAIN = is_main;
			KEY_NUM = key_num;
		}

		private ACTION(String short_ex, int key_num, String... exp) {
			this(short_ex, key_num, false, exp);
		}

		private ACTION(String short_ex, String... exp) {
			this(short_ex, -1, false, exp);
		}

		public void setKey(Object property) {
			if (property == null)
				return;
			StringTokenizer st = new StringTokenizer(property.toString(), ",");
			while (st.hasMoreTokens()) {
				ME.key.put(Integer.valueOf(st.nextToken()), this);
			}
		}
	}

	private static boolean flag_unvalid = false;

	public static HashMap<Integer, ACTION> getKey() {
		return ME.key;
	}

	public static void setValid(boolean b) {
		flag_unvalid = !b;
	}

	private final HashMap<Integer, ACTION> key = new HashMap<Integer, ACTION>();

	private final HashMap<Integer, DIRECTION> allow_map = new HashMap<Integer, DIRECTION>();

	public final static Listener ME = new Listener();

	public static HashMap<Integer, DIRECTION> getAllow_map() {
		return ME.allow_map;
	}

	public static void init() {
		ME.ini();
	}

	private Listener() {
		allow_map.put(KeyEvent.VK_UP, DIRECTION.UP);
		allow_map.put(KeyEvent.VK_DOWN, DIRECTION.DOWN);
		allow_map.put(KeyEvent.VK_LEFT, DIRECTION.LEFT);
		allow_map.put(KeyEvent.VK_RIGHT, DIRECTION.RIGHT);
	}

	private void ini() {
		for (ACTION a : ACTION.values()) {
			if (!key.containsValue(a) && !key.containsKey(a.KEY_NUM)) {
				key.put(a.KEY_NUM, a);
			}
		}
		if (key.containsKey(KeyEvent.VK_ENTER))
			key.remove(KeyEvent.VK_ENTER);
		if (key.containsKey(KeyEvent.VK_ESCAPE))
			key.remove(KeyEvent.VK_ESCAPE);
		key.put(KeyEvent.VK_ENTER, ACTION.ENTER);
		key.put(KeyEvent.VK_ESCAPE, ACTION.MENU);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (flag_unvalid) {
			return;
		}
		if (Scene.getPresentScene().SYS.KEY != null) {
			Scene.getPresentScene().SYS.KEY.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (flag_unvalid) {
			return;
		}
		if (Scene.getPresentScene().SYS.KEY != null) {
			Scene.getPresentScene().SYS.KEY.keyReleased(e);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void reset() {
		for (int j = 0; j < JInputWrapper.buttonID.length; j++) {
			Config.setPadKey(j, -1);
		}
		getKey().clear();
		key.put(KeyEvent.VK_SPACE, ACTION.TURN);
		key.put(KeyEvent.VK_ENTER, ACTION.ENTER);
		key.put(KeyEvent.VK_ESCAPE, ACTION.MENU);
		for (ACTION a : ACTION.getVails()) {
			Config.saveKey(a.KEY_NUM, a);
		}
		getAllow_map().clear();
		allow_map.put(KeyEvent.VK_UP, DIRECTION.UP);
		allow_map.put(KeyEvent.VK_DOWN, DIRECTION.DOWN);
		allow_map.put(KeyEvent.VK_LEFT, DIRECTION.LEFT);
		allow_map.put(KeyEvent.VK_RIGHT, DIRECTION.RIGHT);
	}

	public void setKey(DIRECTION d, Object property) {
		if (property == null || d == null || d == DIRECTION.NEUTRAL)
			return;
		StringTokenizer st = new StringTokenizer(property.toString(), ",");
		while (st.hasMoreTokens()) {
			allow_map.put(Integer.valueOf(st.nextToken()), d);
		}
	}

}
