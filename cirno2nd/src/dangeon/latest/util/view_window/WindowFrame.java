package dangeon.latest.util.view_window;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import main.constant.FR;
import main.util.BeautifulView;
import dangeon.model.config.Config;
import dangeon.view.constant.NormalFont;
import dangeon.view.detail.Footer;
import dangeon.view.util.StringFilter;

public class WindowFrame {
	public final Font FONT;
	public final Insets INSETS;
	protected final int MARGINE = 5;
	public BufferedImage WINDOW;
	public final Integer X;
	public Integer Y;

	public static final int BG_NORMAL = 0, BG_RED = 1, BG_NORMAL_UNCLEARLY = 2;

	private final Color B_COLOR = new Color(0, 0, 0, 150);

	public static final String CENTERIZE = "&c", HORIZON = "&-", TAIL = "&t";

	private int frame_rotation = 0;

	protected WindowFrame(Integer x, Integer y) {
		this(x, y, NormalFont.NORMALFONT);
	}

	protected WindowFrame(Integer x, Integer y, Font font) {
		X = x;
		Y = y;
		FONT = font;
		int top = 5;
		int left = 5;
		INSETS = new Insets(top, left, top, left);
	}

	public WindowFrame(Integer x, Integer y, Integer w_inside, int cols) {
		this(x, y, w_inside, cols, NormalFont.NORMALFONT);
	}

	public WindowFrame(Integer x, Integer y, Integer w_inside, int cols,
			Font font) {
		this(x, y, font);
		if (w_inside == null) {
			w_inside = FR.SCREEN_WIDTH - getWidth(0) - X * 2;
		}
		WINDOW = createWindow(getWidth(w_inside), getHeight(cols));
	}

	public WindowFrame createDoubleSizeClearCopy() {
		final WindowFrame ME = this;
		WindowFrame wf = new WindowFrame(0, 0, FONT) {
			@Override
			public void drawWindow(Graphics2D g) {
				g.drawImage(WINDOW, ME.getViewX(), ME.getViewY() + INSETS.top,
						getWidth() / 2, getHeight() / 2, null);
			}

			@Override
			protected int getViewY() {
				return super.getViewY() - INSETS.top;
			}
		};
		wf.WINDOW = new BufferedImage(
				(getWidth() - INSETS.left - INSETS.right) * 2, (getHeight()
						- INSETS.top - INSETS.bottom) * 2, WINDOW.getType());
		return wf;
	}

	protected BufferedImage createWindow(int w, int h) {
		// BufferedImage im1 = Image_Window_Frame.menu_1.image;
		// BufferedImage im2 = Image_Window_Frame.menu_2.image;
		// BufferedImage im4 = Image_Window_Frame.menu_4.image;
		// BufferedImage im7 = Image_Window_Frame.menu_7.image;
		// BufferedImage im8 = Image_Window_Frame.menu_8.image;
		BufferedImage bi;
		if (Config.isLightVer()) {
			bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		} else {
			bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB_PRE);
		}
		Graphics2D g = bi.createGraphics();
		// int tile_w = im1.getWidth(), tile_h = im1.getHeight();
		// g.drawImage(im7, 0, 0, null);
		// g.drawImage(im7, w, 0, -tile_w, tile_h, null);
		// g.drawImage(im1, 0, h - tile_h, tile_w, tile_h, null);
		// g.drawImage(im1, w, h - tile_h, -tile_w, tile_h, null);
		// g.drawImage(im7, w, 0, -tile_w, tile_h, null);
		// g.drawImage(im4, 0, tile_h, tile_w, h - tile_h - tile_h, null);
		// g.drawImage(im4, w, tile_h, -tile_w, h - tile_h - tile_h, null);
		// g.drawImage(im8, tile_w, 0, w - tile_w - tile_w, tile_h, null);
		// g.drawImage(im2, tile_w, h - tile_h, w - tile_w - tile_w, tile_h,
		// null);
		if (Config.isLightVer()) {
			switch (getBG()) {
			case BG_NORMAL:
				g.setColor(new Color(0, 50, 00));
				break;
			case BG_RED:
				g.setColor(new Color(50, 0, 0));
				break;
			case BG_NORMAL_UNCLEARLY:
				g.setColor(new Color(0, 50, 0));
				break;
			}
		} else {
			switch (getBG()) {
			case BG_NORMAL:
				BeautifulView.setAlphaOnImg(g, 0.4f);
				g.setPaint(new GradientPaint(0, 0, Color.BLUE, 0, h, Color.CYAN));
				break;
			case BG_RED:
				BeautifulView.setAlphaOnImg(g, 0.4f);
				g.setPaint(new GradientPaint(0, 0, Color.RED, 0, h,
						Color.YELLOW));
				break;
			case BG_NORMAL_UNCLEARLY:
				BeautifulView.setAlphaOnImg(g, 0.8f);
				g.setPaint(new GradientPaint(0, 0, new Color(0, 0, 200), 0, h,
						new Color(0, 200, 200)));
				break;
			}
		}

		// g.fillRect(tile_w, tile_h, w - tile_w * 2, h - tile_h * 2);
		int x1 = 5;
		int y1 = 5;
		w = w - x1 * 2;
		h = h - y1 * 2;
		g.fillRect(x1, y1, w, h);
		g.setColor(Color.BLACK);
		int a = 2;
		g.drawRect(x1 + a, y1 + a, w - a * 2, h - a * 2);
		Color c = Color.WHITE;
		g.setPaint(new GradientPaint(0, 0, c, 0, h, new Color(c.getRed() / 2, c
				.getGreen() / 2, c.getBlue() / 2)));
		BeautifulView.setAlphaOnImg(g, 0.8f);
		g.setStroke(new BasicStroke(3));
		g.drawRect(x1, y1, w, h);
		g.setStroke(new BasicStroke());
		a = -3;
		g.drawRect(x1 + a, y1 + a, w - a * 2, h - a * 2);
		// BeautifulView.setAlphaOnImg(g, 0.8f);
		// g.drawImage(Image_Window_Frame.bg.image, x1, y1, x1 + w, y1 + h, 0,
		// 0,
		// w * 2, h * 2, null);
		return bi;
	}

	public void drawArrow(Graphics2D g, boolean writing) {
		frame_rotation += writing ? -3 : 1;
		g.setColor(writing ? new Color(10, 10, 150) : new Color(200, 255, 255));
		Footer.draw9(g, getViewX() + getWidth_CenterArea() - 5, getViewY()
				+ getHeight_MiddleArea(), 16f, frame_rotation * 5, 2, 2);
		// Image im = CHARA_IMAGE.arrow.getWalkImage(1, DIRECTION.DOWN, 1);
		// int w = im.getWidth(null);
		// int h = im.getHeight(null);
		// g.drawImage(im, FR.SCREEN_WIDTH / 2 - w / 2, FR.SCREEN_HEIGHT - 45,
		// w,
		// h, null);
	}

	public void drawEdgedString(Graphics2D g, String str, int cols) {
		g.setFont(FONT);
		StringFilter.drawEdgedString(g, str,
				getViewX() + INSETS.left + MARGINE, getViewY() + INSETS.top
						+ getContentHeight() * cols);
	}

	public void drawLine(Graphics2D g, int cols, boolean middle_under) {
		drawLine(g, cols, getWidth_CenterArea() - 1, middle_under);
	}

	public void drawLine(Graphics2D g, int cols, int w, boolean middle_under) {

		int x = getX();
		int y = getY(g, getViewY(), cols);
		FontMetrics fm = g.getFontMetrics(FONT);
		y += fm.getDescent();
		if (middle_under)
			y += getContentHeight() / 2;
		y += 1;
		g.setColor(Color.BLACK);
		g.fillRect(x, y, w, 3);
		g.setColor(Color.WHITE);
		int delt = 0;
		g.setPaint(new GradientPaint(x + delt, y, Color.WHITE, x + delt + w, y,
				Color.LIGHT_GRAY, false));
		g.fillRect(x + 1, y + 1, w - 2, 1);
		g.setColor(Color.WHITE);
	}

	public void drawString(Graphics2D g, String str, int cols) {
		g.setFont(FONT);
		StringFilter.drawString(g, str, getViewX() + INSETS.left + MARGINE,
				getViewY() + INSETS.top + getContentHeight() * cols);
	}

	public void drawString(Graphics2D g, String str, int cols, int y_diff) {
		g.setColor(Color.WHITE);
		StringFilter.drawString(g, str, getViewX() + INSETS.left + MARGINE,
				getViewY() + INSETS.top + getContentHeight() * cols + y_diff);
	}

	public void drawString(Graphics2D g, String str, int cols, int x_diff,
			int y_diff) {
		StringFilter.drawString(g, str, getViewX() + INSETS.left + MARGINE
				+ x_diff, getViewY() + INSETS.top + getContentHeight() * cols
				+ y_diff);
	}

	public void drawWindow(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawImage(WINDOW, getViewX(), getViewY(), null);
	}

	protected int getBG() {
		return BG_NORMAL;
	}

	protected final int getBG(boolean red) {
		return red ? BG_RED : BG_NORMAL;
	}

	public int getContentHeight() {
		return FONT.getSize() + MARGINE;
	}

	public int getHeight() {
		return WINDOW.getHeight();
	}

	protected int getHeight(int cols) {
		int chosei = 5;
		int h = MARGINE + chosei + getContentHeight() * cols;
		h += INSETS.top + INSETS.bottom;
		return h;
	}

	public int getHeight_MiddleArea() {
		return getHeight() - getHeight(0);
	}

	public int getViewX() {
		if (X == null)
			return (FR.SCREEN_WIDTH - WINDOW.getWidth()) / 2;
		else if (X < 0)
			return FR.SCREEN_WIDTH + X - getWidth();
		else
			return X;
	}

	protected int getViewY() {
		if (Y == null)
			return (FR.SCREEN_HEIGHT - WINDOW.getHeight()) / 2;
		else
			return Y;
	}

	public int getWidth() {
		return WINDOW.getWidth();
	}

	protected int getWidth(int w_inside) {
		int chosei = 3;
		w_inside += MARGINE + chosei;
		w_inside += INSETS.left + INSETS.right;
		return w_inside;
	}

	public int getWidth_CenterArea() {
		return getWidth() - getWidth(0);
	}

	public int getX() {
		return getViewX() + INSETS.left + MARGINE;
	}

	protected int getX(int x) {
		return x + INSETS.left + MARGINE;
	}

	protected int getY(Graphics2D g, int y, int col) {
		return y + INSETS.top + getContentHeight() * (col + 1);
	}

	public Graphics2D initGraphics2Scaled() {
		Graphics2D g = WINDOW.createGraphics();
		g.setColor(Color.WHITE);
		g.setFont(FONT);
		int y = 5;
		g.setClip(0, y, WINDOW.getWidth(), WINDOW.getHeight() - y * 2 + 1);
		g.scale(2, 2);
		BeautifulView.setAntiAliasing(g, true);
		return g;
	}

	public void setY(int y) {
		Y = y;
	}

}
