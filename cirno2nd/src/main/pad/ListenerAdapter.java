package main.pad;

import java.awt.Component;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * JInputWrapperをKeyListenerから使うためのクラス
 * 
 * @author zndmch
 * 
 */
public class ListenerAdapter {

	private static boolean buttonState[] = new boolean[16];
	private static int flgButtonPressed[] = new int[16];
	private static boolean flgButtonReleased[] = new boolean[16];
	private static int arrowXState = 0;
	private static int arrowYState = 0;
	private static int flgLeftPressed = 0;
	private static int flgRightPressed = 0;
	private static int flgUpPressed = 0;
	private static int flgDownPressed = 0;
	private static boolean flgLeftReleased = false;
	private static boolean flgRightReleased = false;
	private static boolean flgUpReleased = false;
	private static boolean flgDownReleased = false;

	private static int KEYINTERVAL_A = 5;
	private static int KEYINTERVAL_B = 5;

	private static boolean arrowSeparateMode = false;
	private static boolean arrowStepX = false;

	private static Integer keyConfig[] = new Integer[16];

	private static KeyListener listener;
	private static Component com;
	private static int prev_int = -1;

	static {
		for (int i = 0; i < 16; i++) {
			buttonState[i] = false;
			keyConfig[i] = -1;
		}
		com = new Panel();
	}

	private static void action() {
		@SuppressWarnings("deprecation")
		KeyEvent kev = new KeyEvent(com, 0, 0, 0, 0);
		for (int i = 0; i < 16; i++) {
			if ((flgButtonPressed[i] == 1 || flgButtonPressed[i] > KEYINTERVAL_B)
					&& keyConfig[i] != null) {
				kev.setKeyCode(keyConfig[i]);
				keyPressed(i, kev);
			} else if (flgButtonReleased[i] && keyConfig[i] != null) {
				kev.setKeyCode(keyConfig[i]);
				keyReleased(kev);
				flgButtonReleased[i] = false;
			}
		}
		if (arrowSeparateMode) {
			if (arrowStepX) {
				actionAX(kev);
				arrowStepX = false;
			} else {
				actionAY(kev);
				arrowStepX = true;
			}
		} else {
			actionAX(kev);
			actionAY(kev);
		}
		// System.out.println("←" + flgLeftPressed + " ↑" + flgUpPressed + " →"
		// + flgRightPressed + " ↓" + flgDownPressed);
	}

	private static void actionAX(KeyEvent kev) {
		if (flgLeftPressed == 1 || flgLeftPressed > KEYINTERVAL_A) {
			kev.setKeyCode(KeyEvent.VK_LEFT);
			keyPressed(kev);
		} else if (flgLeftReleased) {
			kev.setKeyCode(KeyEvent.VK_LEFT);
			keyReleased(kev);
			flgLeftReleased = false;
		}
		if (flgRightPressed == 1 || flgRightPressed > KEYINTERVAL_A) {
			kev.setKeyCode(KeyEvent.VK_RIGHT);
			keyPressed(kev);
		} else if (flgRightReleased) {
			kev.setKeyCode(KeyEvent.VK_RIGHT);
			keyReleased(kev);
			flgRightReleased = false;
		}
	}

	private static void actionAY(KeyEvent kev) {
		if (flgUpPressed == 1 || flgUpPressed > KEYINTERVAL_A) {
			kev.setKeyCode(KeyEvent.VK_UP);
			keyPressed(kev);
		} else if (flgUpReleased) {
			kev.setKeyCode(KeyEvent.VK_UP);
			keyReleased(kev);
			flgUpReleased = false;
		}
		if (flgDownPressed == 1 || flgDownPressed > KEYINTERVAL_A) {
			kev.setKeyCode(KeyEvent.VK_DOWN);
			keyPressed(kev);
		} else if (flgDownReleased) {
			kev.setKeyCode(KeyEvent.VK_DOWN);
			keyReleased(kev);
			flgDownReleased = false;
		}
	}

	/**
	 * フレーム毎の処理。ボタン・方向キー判定を行い、対応した擬似KeyEventを発生させる。
	 */
	public static void frame() {
		JInputWrapper.upDate();
		frameB();
		if (arrowSeparateMode) {
			if (arrowStepX) {
				frameAX();
			} else {
				frameAY();
			}
		} else {
			frameAX();
			frameAY();
		}
		action();
	}

	private static void frameAX() {
		int arrowX;
		arrowX = JInputWrapper.getArrowX();

		if (arrowX != 0) {
			if (arrowX == 1 && arrowXState != 1) {
				if (flgRightPressed <= KEYINTERVAL_A) {
					flgRightPressed++;
				}
			} else if (arrowX == -1 && arrowXState != -1) {
				if (flgLeftPressed <= KEYINTERVAL_A) {
					flgLeftPressed++;
				}
			} else {
				arrowXState = arrowX;
			}
		} else {
			arrowXState = 0;
		}
		if (flgRightPressed != 0 && arrowX != 1) {
			flgRightPressed = 0;
			flgRightReleased = true;
		} else if (flgLeftPressed != 0 && arrowX != -1) {
			flgLeftPressed = 0;
			flgLeftReleased = true;
		}

	}

	private static void frameAY() {
		int arrowY;
		arrowY = JInputWrapper.getArrowY();

		if (arrowY != 0) {
			if (arrowY == 1 && arrowYState != 1) {
				if (flgDownPressed <= KEYINTERVAL_A) {
					flgDownPressed++;
				}
			} else if (arrowY == -1 && arrowYState != -1) {
				if (flgUpPressed <= KEYINTERVAL_A) {
					flgUpPressed++;
				}
			} else {
				arrowYState = arrowY;
			}
		} else {
			arrowYState = 0;
		}
		if (flgDownPressed != 0 && arrowY != 1) {
			flgDownPressed = 0;
			flgDownReleased = true;
		} else if (flgUpPressed != 0 && arrowY != -1) {
			flgUpPressed = 0;
			flgUpReleased = true;
		}
	}

	// ---
	// private method
	private static void frameB() {
		boolean b[] = new boolean[16];

		b = JInputWrapper.getButton();
		for (int i = 0; i < 16; i++) {
			if (b[i] && buttonState[i] != true) {
				if (flgButtonPressed[i] <= KEYINTERVAL_B) {
					flgButtonPressed[i]++;
				}
			} else if (flgButtonPressed[i] != 0 && b[i] != true) {
				flgButtonPressed[i] = 0;
				flgButtonReleased[i] = true;
			} else {
				buttonState[i] = b[i];
			}
		}
	}

	public static int getPrevInt() {
		int i = prev_int;
		prev_int = -1;
		return i;
	}

	// ---
	// public method
	/**
	 * はじめに一度だけ呼ぶ。JInputWrapperの初期化。
	 */
	public static void init() {
		JInputWrapper.init();
	}

	/**
	 * ActionKey相当
	 * 
	 * @param i
	 * @param e
	 */
	private static void keyPressed(int i, KeyEvent e) {
		prev_int = i;
		listener.keyPressed(e);
	}

	/**
	 * ArrowKey相当
	 * 
	 * @param e
	 */
	private static void keyPressed(KeyEvent e) {
		listener.keyPressed(e);
	}

	private static void keyReleased(KeyEvent e) {
		listener.keyReleased(e);
	}

	/**
	 * trueにすると方向キーのX軸とY軸の入力イベントが同一フレーム内で発生しなくなる。
	 * 主に斜め入力で、キーボードと同等のタイミングでイベントが発生しないと不具合が生じる処理系で使用。
	 * 
	 * @param asm
	 */
	public static void setArrowSeparateMode(boolean asm) {
		arrowSeparateMode = asm;
	}

	/**
	 * ボタン0～15を押した時に発生させる擬似KeyEventのKeyCodeを設定する。
	 * 例：ListenerAdapter.setKeyConfig(0, KeyEvent.VK_Z);//ボタン0にZキーを対応させる
	 * 
	 * @param buttonID
	 *            ボタンNo
	 * @param keyCode
	 *            KeyEvent対応キーコード
	 */
	public static int setKeyConfig(int buttonID, int keyCode) {
		if (keyConfig[buttonID] == keyCode)
			keyCode = -1;
		return keyConfig[buttonID] = keyCode;
	}

	/**
	 * 方向キーのキーリピート開始までの時間（フレーム数）を設定。デフォルト値5
	 * 
	 * @param ki
	 */
	public static void setKeyIntervalA(int ki) {
		KEYINTERVAL_A = ki;
	}

	/**
	 * 方向キーのキーリピート開始までの時間（フレーム数）を設定。デフォルト値5
	 * 
	 * @param ki
	 */
	public static void setKeyIntervalB(int ki) {
		KEYINTERVAL_B = ki;
	}

	/**
	 * KeyListenerを登録する。
	 * 
	 * @param kl
	 *            KeyListener
	 */
	public static void setListener(KeyListener kl) {
		listener = kl;
	}

}
