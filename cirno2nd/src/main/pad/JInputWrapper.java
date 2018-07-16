package main.pad;

import java.util.ArrayList;
import java.util.List;

import main.util.Show;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 * JInputライブラリラッパー簡易版ver120327
 * 
 * @author zndmch
 */
public class JInputWrapper {

	private static Controller con[];
	// private static int gamepadID;
	private static List<Integer> gamepadID = new ArrayList<Integer>();
	private static Integer useID = null;

	public static boolean flgButton[] = new boolean[16];
	public static int arrowX;
	public static int arrowY;

	public static int lastButton;
	public static int lastPad;

	public static Identifier buttonID[] = new Identifier[16];

	static {
		buttonID[0] = Identifier.Button._0;
		buttonID[1] = Identifier.Button._1;
		buttonID[2] = Identifier.Button._2;
		buttonID[3] = Identifier.Button._3;
		buttonID[4] = Identifier.Button._4;
		buttonID[5] = Identifier.Button._5;
		buttonID[6] = Identifier.Button._6;
		buttonID[7] = Identifier.Button._7;
		buttonID[8] = Identifier.Button._8;
		buttonID[9] = Identifier.Button._9;
		buttonID[10] = Identifier.Button._10;
		buttonID[11] = Identifier.Button._11;
		buttonID[12] = Identifier.Button._12;
		buttonID[13] = Identifier.Button._13;
		buttonID[14] = Identifier.Button._14;
		buttonID[15] = Identifier.Button._15;
	}

	private static Float[] polle_base = null;

	private static Component[] con_init(int ID) {
		con[ID].poll();
		Component[] com = con[ID].getComponents();
		if (polle_base == null) {
			polle_base = new Float[com.length];
			for (int c = 0; c < com.length; c++) {
				float p = com[c].getPollData();
				if (com[c].getIdentifier() == Identifier.Axis.X) {
					polle_base[c] = p;
				}
				if (com[c].getIdentifier() == Identifier.Axis.Y) {
					polle_base[c] = p;
				}
			}
		}
		return com;

	}

	/**
	 * 方向キー（パッドによってはスティック）のX軸状態を-1（←）,0,1（→）の値で返す。
	 * 
	 * @return X軸状態(int)
	 */
	public static int getArrowX() {
		return arrowX;
	}

	/**
	 * 方向キー（パッドによってはスティック）のY軸状態を-1（↑）,0,1（↓）の値で返す。
	 * 
	 * @return Y軸状態(int)
	 */
	public static int getArrowY() {
		return arrowY;
	}

	/**
	 * ボタン0～ボタン15までの16個のボタンの状態をboolean[16]として返す。
	 * 
	 * @return boolean[16]
	 */
	public static boolean[] getButton() {
		return flgButton;
	}

	/**
	 * 最後に押されたボタンNo.を返す
	 */
	public static int getLastButton() {
		return lastButton;
	}

	/**
	 * 最後に入力があったゲームパッドのIDを返す
	 */
	public static int getLastGamepad() {
		return lastPad;
	}

	/**
	 * はじめに一度だけ呼ぶ。ゲームパッド認識と初期化。
	 */
	public static void init() {
		con = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (int c = 0; c < con.length; c++) {
			Controller.Type ct = con[c].getType();
			if (ct == Controller.Type.STICK || ct == Controller.Type.GAMEPAD) {
				gamepadID.add(c);
			}
		}
	}

	/**
	 * 最後に押されたボタンNo.を-1にする
	 */
	public static void resetLastButton() {
		lastButton = -1;
	}

	/**
	 * 使用するゲームパッドのIDを指定
	 */
	public static void setUseGamepadID(int i) {
		useID = i;
	}

	/**
	 * ゲームパッドのボタンおよび方向キー状態を更新する。
	 */
	public static void upDate() {
		try {
			if (useID != null) {
				upDateOne(useID);
			} else {
				for (int ID : gamepadID) {
					upDateOne(ID);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			Show.showErrorMessageDialog("ゲームパッドが複数検出された可能性があります　２つ以上差している場合は１つにしてください");
			Show.showCriticalErrorMessageDialog(e);
			e.printStackTrace();
		}
	}

	/**
	 * ゲームパッドごとの更新処理
	 */
	private static void upDateOne(int ID) {

		Component com[] = con_init(ID);
		float arrow_x = 0, arrow_y = 0;
		for (int c = 0; c < com.length; c++) {
			float p = com[c].getPollData();
			for (int b = 0; b < 16; b++) {
				if (com[c].getIdentifier() == buttonID[b]) {
					if (p != 0) {
						flgButton[b] = true;
						lastButton = b;
						lastPad = ID;
					} else {
						flgButton[b] = false;
					}
				}
			}
			if (com[c].getIdentifier() == Identifier.Axis.X) {
				arrow_x += p - polle_base[c];
			}
			if (com[c].getIdentifier() == Identifier.Axis.Y) {
				arrow_y += p - polle_base[c];
			}
		}
		if (arrow_x < -0.5f) {
			arrowX = -1;
		} else if (arrow_x > 0.5f) {
			arrowX = 1;
		} else {
			arrowX = 0;
		}
		if (arrow_y < -0.5f) {
			arrowY = -1;
		} else if (arrow_y > 0.5f) {
			arrowY = 1;
		} else {
			arrowY = 0;
		}
	}
}
