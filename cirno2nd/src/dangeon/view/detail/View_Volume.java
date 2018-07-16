package dangeon.view.detail;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import dangeon.controller.listener.menu.Menu_Volume;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.Window;

public class View_Volume {

	private static Image im;

	public static void draw(Graphics2D g) {
		int x = SCREEN.X;
		g.drawImage(im, x, SCREEN.Y, null);
		x += SCREEN.X_MARGINE;
		g.setColor(Color.WHITE);
		g.drawString("BGM", x, getY(0));
		g.drawString("SE", x, getY(1));
		g.setColor(Color.ORANGE);
		g.drawString("END", x, getY(2));
		g.setColor(Color.WHITE);
		String str;
		str = String.valueOf(Menu_Volume.getBGMVol());
		int _x = x + 65 - g.getFontMetrics().stringWidth(str);
		g.drawString(str, _x, getY(0));
		str = String.valueOf(Menu_Volume.getSEVol());
		int __x = x + 65 - g.getFontMetrics().stringWidth(str);
		g.drawString(str, __x, getY(1));
		View_Cursor.draw(g, x, getY(Menu_Volume.getY()), true);
	}

	public static void finish() {
		im = null;
	}

	private static int getY(int index) {
		return SCREEN.Y + SCREEN.Y_SMALL_MARGINE / 2
				+ (NormalFont.SMALL_HEIGHT + NormalFont.SMALL_DECENT)
				* (index + 1);
	}

	public static void init() {
		int W = 90;
		int H = Window.getNarrowWindowH_SmallFont(3);
		im = Window.get(W, H);
	}

}
