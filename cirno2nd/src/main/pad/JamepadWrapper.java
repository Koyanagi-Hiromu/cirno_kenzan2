package main.pad;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class JamepadWrapper {
	public static void main(String[] args) throws InterruptedException {
		init();

		while (true) {
			refresh();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < flgButton.length; i++) {
				if (flgButton[i]) {
					sb.append(PadKey.parse(i));
					sb.append(",");
				}
			}
			System.out.println("[" + arrowX + ", " + arrowY + "] , ボタン: " + sb);
			Thread.sleep(16); // 約60fps
		}
	}

	static int arrowX;
	static int arrowY;

	static ControllerManager controllers;

	public static final int buttonID_length = 16;
	public static boolean flgButton[] = new boolean[16];

	public static void init() {
		controllers = new ControllerManager();
		controllers.initSDLGamepad();
	}

	public static void resetLastButton() {
	}

	public static void upDate() {
		refresh();
	}

	public static int getArrowX() {
		return arrowX;
	}

	public static int getArrowY() {
		return arrowY;
	}

	public static boolean[] getButton() {
		return flgButton;
	}

	public static void refresh() {
		for (int i = 0; i < flgButton.length; i++) {
			flgButton[i] = false;
		}

		float _arrowX = 0, _arrowY = 0;
		for (int i = 0; i < controllers.getNumControllers(); i++) {
			ControllerState state = controllers.getState(i);
			if (!state.isConnected) {
				continue;
			}

			updateFlags(state);

			_arrowX += getArrowX(state);
			_arrowY += getArrowY(state);
		}

		float deadzone = 0.2f;
		arrowX = _arrowX > deadzone ? 1 : _arrowX < -deadzone ? -1 : 0;
		arrowY = _arrowY > deadzone ? 1 : _arrowY < -deadzone ? -1 : 0;
		arrowY = -arrowY;
	}

	static void updateFlags(ControllerState state) {
		if (state.a)
			flgButton[PadKey.A.ID] = true;
		if (state.b)
			flgButton[PadKey.B.ID] = true;
		if (state.x)
			flgButton[PadKey.X.ID] = true;
		if (state.y)
			flgButton[PadKey.Y.ID] = true;
		if (state.lb)
			flgButton[PadKey.L1.ID] = true;
		if (state.rb)
			flgButton[PadKey.R1.ID] = true;
		if (state.back)
			flgButton[PadKey.BACK.ID] = true;
		if (state.start)
			flgButton[PadKey.START.ID] = true;
		if (state.leftTrigger > 0.1f)
			flgButton[PadKey.LT.ID] = true;
		if (state.rightTrigger > 0.1f)
			flgButton[PadKey.RT.ID] = true;
	}

	static float getArrowX(ControllerState state) {
		float value = state.leftStickX;
		if (state.dpadLeft)
			value -= 1;
		if (state.dpadRight)
			value += 1;
		return value;
	}

	static float getArrowY(ControllerState state) {
		float value = state.leftStickY;
		if (state.dpadDown)
			value -= 1;
		if (state.dpadUp)
			value += 1;
		return value;
	}
}