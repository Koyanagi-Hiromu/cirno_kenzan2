package dangeon.view.detail;

import java.awt.Graphics2D;
import java.awt.Image;

import main.constant.FR;
import dangeon.latest.scene.action.message.MsgBlock;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.Window;

public class MessageBox {
	public static final int X;
	public static final int W;
	public static final int W_VALID;
	public static final int H;
	public static final int H_VALID;
	public static final int Y;
	public static final int DRAWABLE_MAX_NUMBER;
	public static final Image WINDOW;
	static {
		X = SCREEN.X;
		W = FR.SCREEN_WIDTH - X * 2;
		H = SCREEN.Y_MARGINE * 2 + (NormalFont.HEIGHT - NormalFont.LEADING)
				* MsgBlock.BOX_LINE + NormalFont.LEADING;
		Y = FR.SCREEN_HEIGHT - H - SCREEN.Y_MARGINE;
		WINDOW = Window.get(W, H);
		W_VALID = W - SCREEN.X_MARGINE * 2;
		H_VALID = H - SCREEN.Y_MARGINE * 2;
		DRAWABLE_MAX_NUMBER = FR.SCREEN_HEIGHT / H + 1;
	}

	public static void draw(Graphics2D g) {
	}

}
