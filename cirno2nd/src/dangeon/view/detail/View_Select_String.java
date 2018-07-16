package dangeon.view.detail;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import main.constant.FR;
import dangeon.controller.listener.menu.Menu_Select_String;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.Window;

public class View_Select_String {

	public static final int W, H, H_SUB;
	public static final int SCREEN_X, SCREEN_Y, SCREEN_Y_SUB;
	private static final Image MAIN_WINDOW;
	static {
		W = SCREEN.X_MARGINE * 3 + (NormalFont.WIDTH2 / 2 + SCREEN.X_MARGINE)
				* 11;
		H = SCREEN.Y_MARGINE * 2 + (NormalFont.HEIGHT + NormalFont.DECENT)
				* (Menu_Select_String.ME.Y_LENGTH / 2 + 2) - NormalFont.DECENT;
		H_SUB = SCREEN.Y_MARGINE * 2 + NormalFont.HEIGHT;
		MAIN_WINDOW = Window.get(W, H);
		SCREEN_X = (FR.SCREEN_WIDTH - W) / 2;
		SCREEN_Y_SUB = (FR.SCREEN_HEIGHT - (H_SUB + SCREEN.Y_MARGINE + H)) / 2;
		SCREEN_Y = SCREEN_Y_SUB + H_SUB + SCREEN.Y_MARGINE;
	}

	public static void draw(Graphics2D g) {
		drawTitle(g);
		drawString(g);
	}

	private static void drawString(Graphics2D g) {
		Menu_Select_String mss = Menu_Select_String.ME;
		g.drawImage(MAIN_WINDOW, SCREEN_X, SCREEN_Y, null);
		int w = NormalFont.WIDTH2 / 2 + SCREEN.X_MARGINE;
		int h = NormalFont.HEIGHT + NormalFont.DECENT;
		int x = SCREEN_X + SCREEN.X_MARGINE * 2;
		int y = SCREEN_Y + SCREEN.Y_MARGINE + h;
		g.setColor(Color.YELLOW);
		g.drawString("けす", x + w * 4, y);
		g.drawString("けってい", x + w * 8, y);
		g.drawString("こうほ", x, y);
		g.setColor(Color.WHITE);
		for (int i = 0; i < mss.X_LENGTH; i++) {
			for (int j = 0; j < mss.Y_LENGTH; j++) {
				g.drawString(mss.getChar(i, j), x + w * mss.getPointX(i, j), y
						+ h * (mss.getPointY(j)));
			}
		}
		View_Cursor.draw(g, x + w * mss.getPointX(), y + h * (mss.getPointY()),
				true);
		g.setColor(Color.YELLOW);
		g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
		// g.drawString("Fでおぎなう　Ctrlで濁点", x + w * 5, y + h * 7);
		g.drawString("F：けってい　Shift/Space：こうほ　Ctrl：濁点", x - 15, y + h * 8);
		g.setFont(NormalFont.NORMALFONT);
	}

	private static void drawTitle(Graphics2D g) {
		String tit = Menu_Select_String.ME.getTitle();
		int w = SCREEN.X_MARGINE * 2 + g.getFontMetrics().stringWidth(tit);
		Image img = Window.get(w, H_SUB);
		int x = (FR.SCREEN_WIDTH - w) / 2;
		int y = SCREEN_Y_SUB;
		g.drawImage(img, x, y, null);
		g.setColor(Color.WHITE);
		g.drawString(tit, x + SCREEN.X_MARGINE, y + SCREEN.Y_MARGINE
				+ NormalFont.HEIGHT);
	}

	// private static void drawItemWindow(Graphics2D g) {
	// int x = SCREEN_X + W / 2 - NormalFont.WIDTH2 / 2;
	// g.drawImage(ITEM_WINDOW, SCREEN_X, SCREEN_Y, null);
	// g.drawString(半角全角コンバーター.半角To全角数字(Base_MenuItem.getNow().getPage()), x
	// - SCREEN.X_MARGINE - margine, SCREEN_Y + H);
	// g.drawString("／", x, SCREEN_Y + H);
	// g.drawString(半角全角コンバーター.半角To全角数字(Base_MenuItem.getNow().getMaxPage()),
	// x + SCREEN.X_MARGINE + margine, SCREEN_Y + H);
	// }
	//
	// private static void drawBelongingItemName(Graphics2D g, int index, int y,
	// int h) {
	// Base_Artifact a = Base_MenuItem.getNow().get(index);
	// if (a == null) {
	// return;
	// }
	// int x = SCREEN_X + SCREEN.X_MARGINE;
	// g.drawImage(a.getImage(), x - h / 2, y - h * 3 / 2, h * 2, h * 2, null);
	// StringFilter.drawString(g, a.getColoredName(), x + h, y);
	// // g.drawString(String.valueOf(a.getFreezeCount()), x - 2, y - 2);
	// if (Base_MenuItem.getNow().get(index).isEnchantedNow()) {
	// g.drawString("E", x - 2, y - 2);
	// }
	// }
	//
	// private static void drawExplain(Graphics2D g) {
	// drawExplanation(g, SCREEN_X, SCREEN_Y + H + margine);
	// }
	//
	// private static void drawExplanation(Graphics2D g, int X, int Y) {
	// if (Base_MenuItem.getNow().IS_EMPTY) {
	// return;
	// }
	// Base_Artifact a = Base_MenuItem.getNow().get();
	// if (a == null) {
	// return;
	// }
	// String[] arr = a.firstPageExplain();
	// g.drawImage(EXPLANATION_WINDOW, X, Y, null);
	// g.setFont(g.getFont().deriveFont(NormalFont.SMALL_SIZE));
	// int times = arr.length;
	// if (arr.length > MINI_EXPLANATION_MAX_LENGTH) {
	// times = MINI_EXPLANATION_MAX_LENGTH;
	// }
	// int x, y;
	// x = X + SCREEN.X_MARGINE;
	// for (int i = 0; i < times; i++) {
	// y = Y + SCREEN.Y_SMALL_MARGINE
	// + (NormalFont.SMALL_HEIGHT + margine) * (i + 1) - margine;
	// StringFilter.drawString(g, arr[i], x, y);
	// }
	// g.setFont(NormalFont.NORMALFONT);
	// }
	//
	// private static void drawItems(Graphics2D g) {
	// if (Base_MenuItem.getNow().IS_EMPTY) {
	// int x = SCREEN_X + SCREEN.X_MARGINE;
	// int h = NormalFont.HEIGHT;
	// int y = SCREEN_Y + Window.getNarrowStringY(0);
	// StringFilter.drawString(g, "―候補がない―", x + h, y);
	// return;
	// }
	// int i = Base_MenuItem.getNow().getPage() * Base_MenuItem.Y_LENGTH;
	// for (int j = 0; i < Base_MenuItem.getNow().getSize()
	// && j < Base_MenuItem.Y_LENGTH; i++, j++) {
	// drawItem(g, i, j);
	// }
	// int x = SCREEN_X + SCREEN.X_MARGINE;
	// int y = SCREEN_Y + SCREEN.Y_MARGINE
	// + (NormalFont.HEIGHT + NormalFont.DECENT)
	// * (Base_MenuItem.getNow().getY() + 1) - NormalFont.DECENT;
	// View_Cursor.draw(g, x, y, true);
	// }
	//
	// private static void drawItem(Graphics2D g, int i, int j) {
	// int y = SCREEN_Y + Window.getNarrowStringY(j);
	// drawBelongingItemName(g, i, y, NormalFont.HEIGHT);
	// }

}
