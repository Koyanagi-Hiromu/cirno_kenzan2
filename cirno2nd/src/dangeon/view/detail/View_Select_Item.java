package dangeon.view.detail;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import main.util.半角全角コンバーター;
import dangeon.controller.listener.menu.Base_MenuItem;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.StringFilter;
import dangeon.view.util.Window;

public class View_Select_Item {

	public static final int W = Item.W, H = Item.H;
	public static final int SCREEN_X, SCREEN_Y;
	public static final int margine = SCREEN.WINDOW_MARGINE;
	private static final Image ITEM_WINDOW = Item.ITEM_WINDOW;
	private static final Image EXPLANATION_WINDOW = Item.MINI_EXPLANATION_WINDOW;
	private static final int Y_HOSEI = SCREEN.X / 3;
	static {
		SCREEN_X = SCREEN.X;
		SCREEN_Y = SCREEN.Y + Y_HOSEI - 3;
	}
	private static final int MINI_EXPLANATION_MAX_LENGTH = Item.MINI_EXPLANATION_MAX_LENGTH;

	public static void draw(Graphics2D g) {
		drawItemWindow(g);
		drawItems(g);
		drawExplain(g);
		drawTitle(g);
	}

	private static void drawBelongingItemName(Graphics2D g, int index, int y,
			int h) {
		Base_Artifact a = Base_MenuItem.getNow().get(index);
		if (a == null) {
			return;
		}
		int x = SCREEN_X + SCREEN.X_MARGINE;
		g.drawImage(a.getImage(), x - h / 2, y - h * 3 / 2, h * 2, h * 2, null);
		StringFilter.drawString(g, a.getColoredName(false), x + h, y);
		// g.drawString(String.valueOf(a.getFreezeCount()), x - 2, y - 2);
		if (Base_MenuItem.getNow().get(index).isEnchantedNow()) {
			g.drawString("E", x - 2, y - 2);
		}
	}

	private static void drawExplain(Graphics2D g) {
		drawExplanation(g, SCREEN_X, SCREEN_Y + H + margine);
	}

	private static void drawExplanation(Graphics2D g, int X, int Y) {
		if (Base_MenuItem.getNow().IS_EMPTY) {
			return;
		}
		Base_Artifact a = Base_MenuItem.getNow().get();
		if (a == null) {
			return;
		}
		String[] arr = a.firstPageExplain();
		g.drawImage(EXPLANATION_WINDOW, X, Y, null);
		g.setFont(g.getFont().deriveFont(NormalFont.SMALL_SIZE));
		int times = arr.length;
		if (arr.length > MINI_EXPLANATION_MAX_LENGTH) {
			times = MINI_EXPLANATION_MAX_LENGTH;
		}
		int x, y;
		x = X + SCREEN.X_MARGINE;
		for (int i = 0; i < times; i++) {
			y = Y + SCREEN.Y_SMALL_MARGINE
					+ (NormalFont.SMALL_HEIGHT + margine) * (i + 1) - margine;
			StringFilter.drawString(g, arr[i], x, y);
		}
		g.setFont(NormalFont.NORMALFONT);
	}

	private static void drawItem(Graphics2D g, int i, int j) {
		int y = SCREEN_Y + Window.getNarrowStringY(j);
		drawBelongingItemName(g, i, y, NormalFont.HEIGHT);
	}

	private static void drawItems(Graphics2D g) {
		if (Base_MenuItem.getNow().IS_EMPTY) {
			int x = SCREEN_X + SCREEN.X_MARGINE;
			int h = NormalFont.HEIGHT;
			int y = SCREEN_Y + Window.getNarrowStringY(0);
			StringFilter.drawString(g, "―候補がない―", x + h, y);
			return;
		}
		int i = Base_MenuItem.getNow().getPage() * Base_MenuItem.Y_LENGTH;
		for (int j = 0; i < Base_MenuItem.getNow().getSize()
				&& j < Base_MenuItem.Y_LENGTH; i++, j++) {
			drawItem(g, i, j);
		}
		int x = SCREEN_X + SCREEN.X_MARGINE;
		int y = SCREEN_Y + SCREEN.Y_MARGINE
				+ (NormalFont.HEIGHT + NormalFont.DECENT)
				* (Base_MenuItem.getNow().getY() + 1) - NormalFont.DECENT;
		View_Cursor.draw(g, x, y, true);
	}

	private static void drawItemWindow(Graphics2D g) {
		int x = SCREEN_X + W / 2 - NormalFont.WIDTH2 / 2;
		g.drawImage(ITEM_WINDOW, SCREEN_X, SCREEN_Y, null);
		int page = Base_MenuItem.getNow().getPage() + 1;
		String s = 半角全角コンバーター.半角To全角数字(page);
		int m = 4;
		if (page > 9) {
			m += g.getFontMetrics().stringWidth(s.substring(1));
		}
		g.drawString(s, x - SCREEN.X_MARGINE - margine - m, SCREEN_Y + H);
		g.drawString("／", x, SCREEN_Y + H);
		g.drawString(半角全角コンバーター.半角To全角数字(Base_MenuItem.getNow().getMaxPage()),
				x + SCREEN.X_MARGINE + margine, SCREEN_Y + H);
	}

	private static void drawTitle(Graphics2D g) {
		BufferedImage im = Base_MenuItem.getNow().TITLE_IMAGE;
		if (im == null) {
			return;
		}
		int x = SCREEN_X;
		int y = SCREEN_Y - im.getHeight() * 2 / 3;
		g.drawImage(im, x, y, null);
		g.drawString(Base_MenuItem.getNow().TITLE, x + SCREEN.X_MARGINE, y
				+ SCREEN.Y_SMALL_MARGINE + NormalFont.HEIGHT - 2);
	}

}
