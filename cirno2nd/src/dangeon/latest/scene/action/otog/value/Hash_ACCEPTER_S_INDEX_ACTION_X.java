package dangeon.latest.scene.action.otog.value;

import java.awt.Image;

import main.Listener.ACTION;
import main.res.CHARA_IMAGE;
import main.res.Image_Player;
import main.util.DIRECTION;
import dangeon.latest.scene.action.otog.object.Accepter;

public class Hash_ACCEPTER_S_INDEX_ACTION_X {
	public final ACTION ACTION[];
	public final int X[], Y[];

	public Hash_ACCEPTER_S_INDEX_ACTION_X(Accepter[] accs) {
		ACTION = new ACTION[accs.length];
		X = new int[accs.length];
		Y = new int[accs.length];
		for (int i = 0; i < accs.length; i++) {
			ACTION[i] = accs[i].ACTION;
			X[i] = accs[i].x;
			Y[i] = accs[i].y;
		}
	}

	public ACTION getA(int i) {
		return ACTION[i];
	}

	public Image getImage(DIRECTION d) {
		Image im;
		switch (d) {
		case NEUTRAL:
			// im = Image_Player.rising_hand.IM;
			im = CHARA_IMAGE.チルノ.getWalkImage(0, DIRECTION.DOWN, 0);
			break;
		case DOWN:
			// im = Image_Player.otiru1.IM;
			im = Image_Player.rising_hand.IM;
			break;
		case UP:
			im = Image_Player.otiru1.IM;
			break;
		default:
			// im = CHARA_IMAGE.arrow.getWalkImage(1, d, 0f);
			im = CHARA_IMAGE.チルノ.getATKImage(0, d, 2);
		}
		return im;
	}

	public Image getImage(DIRECTION d, int coma) {
		if (d == null) {
		}
		switch (d) {
		case DOWN_RIGHT:
			return coma / 4 % 2 == 0 ? Image_Player.sleep0.IM
					: Image_Player.sleep1.IM;
		case DOWN_LEFT:
			return Image_Player.down.IM;
		case UP_LEFT:
			if (coma > 10)
				return Image_Player.food.IM;
			else
				return coma / 4 % 2 == 0 ? Image_Player.mogu0.IM
						: Image_Player.mogu1.IM;
		case UP_RIGHT:
			return Image_Player.disc3.IM;
		case NEUTRAL:
			// return Image_Player.rising_hand.IM;
			return CHARA_IMAGE.チルノ.getWalkImage(0, DIRECTION.DOWN, 0.5f);
		case DOWN:
			return Image_Player.rising_hand.IM;
		case UP:
			return Image_Player.otiru1.IM;
		default:
			int koma = coma / 4 % 4;
			if (koma == 3)
				koma = 1;
			return CHARA_IMAGE.チルノ.getATKImage(0, d, koma);
		}
	}

	public int getIndex(ACTION a) {
		for (int j = 0; j < ACTION.length; j++) {
			if (a == ACTION[j]) {
				return j;
			}
		}
		return -1;
	}

	public int getX(int i) {
		return X[i];
	}

	public int getY(int i) {
		return Y[i];
	}

}
