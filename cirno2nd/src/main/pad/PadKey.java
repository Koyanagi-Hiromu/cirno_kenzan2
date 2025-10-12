package main.pad;

import dangeon.model.config.Config;
import main.Listener.ACTION;

public enum PadKey {
	A(0), B(1), X(2), Y(3), L1(4), R1(5), BACK(6), START(7), LT(8), RT(9);

	PadKey(int ID) {
		this.ID = ID;
	}

	public final int ID;

	public static PadKey parse(int ID) {
		switch (ID) {
		case 0:
			return A;
		case 1:
			return B;
		case 2:
			return X;
		case 3:
			return Y;
		case 4:
			return L1;
		case 5:
			return R1;
		case 6:
			return BACK;
		case 7:
			return START;
		case 8:
			return LT;
		case 9:
			return RT;
		default:
			return null;
		}
	}

	public static void init() {
		for (PadKey padKey : values()) {
			int test = Config.getPadKey(padKey.ID);
			if (test != -1)
				return;
		}
		resetConfig();
	}

	public static void resetConfig() {
		for (PadKey padKey : values()) {
			ACTION action = getDefaultAction(padKey);
			int save = action == null ? -1 : action.KEY_NUM;
			Config.savePadKey(padKey.ID, save);
		}
	}

	public static ACTION getDefaultAction(PadKey key) {
		switch (key) {
		case A:
			return ACTION.ENTER;
		case B:
			return ACTION.CANCEL;
		case X:
			return ACTION.MENU;
		case Y:
			return ACTION.TURN;
		case L1:
			return ACTION.MAP;
		case R1:
			return ACTION.BIAS;
		case BACK:
			return null;
		case START:
			return null;
		case LT:
			return ACTION.FIRE;
		case RT:
			return ACTION.IDASH;
		default:
			return null;
		}

	}
}
