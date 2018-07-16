package dangeon.view.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.util.BeautifulView;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;

public class Window {
	public static final int string_y = NormalFont.ASCENT + SCREEN.Y_MARGINE;
	public static final int string_x = SCREEN.X_MARGINE;
	/**
	 * マージン入り
	 */
	public static final int string_w = NormalFont.WIDTH2 + SCREEN.X_MARGINE;
	/**
	 * マージン入り
	 */
	public static final int string_h = NormalFont.HEIGHT + SCREEN.Y_MARGINE;

	private static final int arc = 15;

	public final static Color WINDOW_COLOR[] = { new Color(0x00, 0x00, 0xcc),
			new Color(0x00, 0x00, 0x66) };

	public static void draw(Graphics2D g, int X, int Y, int W, int H) {
		drawME(g, X, Y, W, H);
	}

	private static void drawME(Graphics2D g, int X, int Y, int W, int H) {
		g.setPaint(new GradientPaint(0, Y, WINDOW_COLOR[0], 0, Y + H,
				WINDOW_COLOR[1]));
		g.fillRoundRect(X, Y, W, H, arc, arc);
		g.setColor(new Color(0, 0, 0, 0));
		g.drawRoundRect(X, Y, W, H, arc, arc);
		int hosei;
		hosei = 4;
		g.drawRoundRect(X + hosei, Y + hosei, W - hosei * 2, H - hosei * 2, arc
				- hosei, arc - hosei);
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(3F));
		hosei = 2;
		g.drawRoundRect(X + hosei, Y + hosei, W - hosei * 2, H - hosei * 2, arc
				- hosei, arc - hosei);
		g.setStroke(new BasicStroke());
	}

	public static BufferedImage get(final int W, final int H) {
		BufferedImage bi = new BufferedImage(W, H,
				BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = bi.createGraphics();
		BeautifulView.setAntiAliasing(g, true);
		BeautifulView.setAlphaOnImg(g, 0.8F);
		drawME(g, 0, 0, W, H);
		// int hosei;
		// BeautifulView.setAntiAliasing_ON(g);
		// g.setPaint(new GradientPaint(0, 0, WINDOW_COLOR[0], 0, 0 + H,
		// WINDOW_COLOR[1]));
		// g.fillRoundRect(0, 0, W, H, arc, arc);
		// g.setColor(Color.WHITE);
		// g.setStroke(new BasicStroke(2F));
		//
		// for (int i = 1; i < 5; i++) {
		// hosei = i;
		// g.drawRoundRect(0 + hosei, 0 + hosei, W - hosei * 2 - 1, H - hosei
		// * 2 - 1, arc - hosei, arc - hosei);
		// }
		// BeautifulView.setAntiAliasing_ON(g);
		// g.setColor(Color.BLACK);
		// g.setStroke(new BasicStroke(1F));
		// // BeautifulView.setAlphaOnImg(g, 0.8F);
		// // g.drawRoundRect(0, 0, W-1, H-1, arc,arc);
		// hosei = 1;
		// g.drawRoundRect(0 + hosei, 0 + hosei, W - hosei * 2 - 1, H - hosei *
		// 2
		// - 1, arc, arc);
		// g.drawRoundRect(0 + hosei, 0 + hosei, W - hosei * 2 - 1, H - hosei *
		// 2
		// - 1, arc, arc);
		// hosei = 0;
		// g.drawRoundRect(0 + hosei, 0 + hosei, W - hosei * 2 - 1, H - hosei *
		// 2
		// - 1, arc, arc);
		// g.drawRoundRect(0 + hosei, 0 + hosei, W - hosei * 2 - 1, H - hosei *
		// 2
		// - 1, arc, arc);
		// // hosei = 4;
		// // g.drawRoundRect(0+hosei, 0+hosei, W-hosei*2-1, H-hosei*2-1,
		// // arc-hosei,arc-hosei);
		// hosei = 5;
		// g.drawRoundRect(0 + hosei, 0 + hosei, W - hosei * 2 - 1, H - hosei *
		// 2
		// - 1, arc - hosei, arc - hosei);
		g.dispose();
		return bi;
	}

	public static int getNarrowStringY(int index) {
		return SCREEN.Y_MARGINE + (NormalFont.HEIGHT + NormalFont.DECENT)
				* (index + 1) - NormalFont.DECENT;
	}

	public static int getNarrowWindowH(int length) {
		return SCREEN.Y_MARGINE * 2 + (NormalFont.HEIGHT + NormalFont.DECENT)
				* length;
	}

	public static int getNarrowWindowH_SmallFont(int length) {
		return SCREEN.Y_MARGINE / 2 * 2
				+ (NormalFont.SMALL_HEIGHT + NormalFont.SMALL_DECENT) * length;
	}

	public static int getSpaceWindowH(int length) {
		return SCREEN.Y_MARGINE + (SCREEN.Y_MARGINE + NormalFont.HEIGHT)
				* length;
	}

	public static int getSpaceWindowW(int length) {
		return SCREEN.X_MARGINE + (SCREEN.X_MARGINE + NormalFont.WIDTH2)
				* length;
	}

	public static int getStringX(int index) {
		return string_x + string_w * index;
	}

	public static int getStringY(int index) {
		return string_y + string_h * index;
	}

}
