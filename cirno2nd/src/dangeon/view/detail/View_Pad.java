package dangeon.view.detail;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import main.Listener;
import main.Listener.ACTION;
import main.constant.FR;
import main.pad.JInputWrapper;
import dangeon.controller.listener.menu.Menu_Pad;
import dangeon.model.config.Config;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.Window;

public class View_Pad {

	private static int H;
	private static final int W = FR.SCREEN_WIDTH - SCREEN.X * 2;
	private static final int X = SCREEN.X;
	private static int Y;

	// public static void draw(Graphics2D g) {
	// g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
	// g.drawImage(Window.get(W, H), X, Y, null);
	// for (int i = 0; i < 16; i++) {
	// int _x = getX(i);
	// g.drawString("pad".concat(String.valueOf(i + 1)), _x,
	// getY(Menu_Pad.getPointY(i)));
	// int index = Config.getPadKey(i);
	// if (index != -1) {
	// g.drawString(String.valueOf(KeyEvent.getKeyText(index)), _x
	// + NormalFont.SMALL_SIZE * 4,
	// getY(Menu_Pad.getPointY(i)));
	// }
	// }
	// View_Cursor.draw(g, getX(Menu_Pad.getY()), getY(Menu_Pad.getPointY()),
	// true);
	// g.drawString("決定", getX(16), getY(Menu_Pad.getPointY(16)));
	// g.setFont(NormalFont.NORMALFONT);
	// }
	//
	// private static int getX(int i) {
	// return X + SCREEN.X_MARGINE + W / 2 * Menu_Pad.getPointX(i);
	// }
	//
	// private static int getY(int i) {
	// return Y + SCREEN.Y_SMALL_MARGINE
	// + (NormalFont.SMALL_HEIGHT + NormalFont.SMALL_DECENT) * (i + 1);
	// }
	private static Image im_main, im_sub, im_foot;

	public static void draw(Graphics2D g) {
		ACTION[] acs = Menu_Pad.getACTION();
		H = Window.getNarrowWindowH_SmallFont(acs.length + 4);
		Y = (FR.SCREEN_HEIGHT - H) / 2;
		g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
		g.drawImage(Menu_Pad.isMain() ? im_main : im_sub, X, Y, null);
		g.setColor(Color.YELLOW);
		drawStringLeft(g, "コマンドネーム", -1);
		drawStringCenter(g, "代表キー", -1);
		drawStringRight(g, "パッド", -1);
		g.setColor(Color.WHITE);
		for (int i = 0; i < acs.length; i++) {
			drawStringLeft(g, acs[i].name(), i);
			// drawStringCenter(g, acs[i].KEY, i);
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < JInputWrapper.buttonID.length; j++) {
				int pad = Config.getPadKey(j);
				if (pad == acs[i].KEY_NUM) {
					sb.append("pad");
					sb.append(j);
					sb.append(" ");
				}
			}
			drawStringRight(g, sb.toString(), i);
		}
		g.setColor(Color.YELLOW);
		drawStringLeft(g, "(製作中)", acs.length);
		g.setColor(Color.RED);
		drawStringLeft(g, "Reset", acs.length + 1);
		g.setColor(Color.ORANGE);
		drawStringLeft(g, "End", acs.length + 2);
		g.setColor(Color.WHITE);
		drawCursor(g);
		g.drawImage(im_foot, X, Y + H + SCREEN.WINDOW_MARGINE, null);
		g.drawString(Menu_Pad.getExp(), getX(0), getY(-1) + H
				+ SCREEN.WINDOW_MARGINE);
		g.setFont(NormalFont.NORMALFONT);
	}

	private static void drawCursor(Graphics2D g) {
		View_Cursor.draw(g, getX(0), getY(Menu_Pad.getY()), true);
	}

	private static void drawString(Graphics2D g, String s, int x, int index) {
		g.drawString(s, getX(x), getY(index));
	}

	private static void drawStringCenter(Graphics2D g, String s, int index) {
		drawString(g, s, W / 3, index);
	}

	private static void drawStringLeft(Graphics2D g, String s, int index) {
		drawString(g, s, 0, index);
	}

	private static void drawStringRight(Graphics2D g, String s, int index) {
		drawString(g, s, W / 2 + 10, index);
	}

	public static void finish() {
		im_main = null;
		im_sub = null;
		im_foot = null;
	}

	private static int getX(int x) {
		return x + X + SCREEN.X_MARGINE;
	}

	private static int getY(int index) {
		return Y + SCREEN.Y_SMALL_MARGINE / 2
				+ (NormalFont.SMALL_HEIGHT + NormalFont.SMALL_DECENT)
				* (index + 2);
	}

	public static void init() {
		H = Window
				.getNarrowWindowH_SmallFont(Listener.ACTION.getMainCommands().length + 4);
		im_main = Window.get(W, H);
		H = Window
				.getNarrowWindowH_SmallFont(Listener.ACTION.getSubCommands().length + 4);
		im_sub = Window.get(W, H);
		H = Window.getNarrowWindowH_SmallFont(1);
		im_foot = Window.get(W, H);
	}
}
