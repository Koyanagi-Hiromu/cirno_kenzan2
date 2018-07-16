package dangeon.latest.scene.action.otog.ready.select.select;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.constant.FR;
import main.thread.MainThread;
import main.util.BeautifulView;
import dangeon.view.util.StringFilter;

public class Otog_Select_Content_Left {
	public final int h = 24;
	public final String VERSION;
	public final int LEVEL;
	public final boolean IS_SELECTED;
	public final int VER;
	public int dx = 0, dy = 0;
	public int frame = -1;
	public final BufferedImage FRAME;

	public Otog_Select_Content_Left(int ver) {
		this(ver, -1, false);
	}

	public Otog_Select_Content_Left(int version, int level) {
		this(version, level, false);
	}

	public Otog_Select_Content_Left(int version, int level, boolean is_selected) {
		int r = version * 65;
		if (r > 255)
			r = 255;
		int gr = 255 / version;
		int b = 255 / ((version + 1) / 2);
		if (level == -1)
			r = gr = b = 100;
		VERSION = new Color(r, gr, b).toString().concat(convertString(version))
				.concat("の譜");
		LEVEL = level;
		IS_SELECTED = is_selected;
		VER = version;
		int x = 0;
		int x1 = x, x2 = x + h / 2, x3 = x + getW() - h / 2, x4 = x + getW();
		x1 *= 2;
		x2 *= 2;
		x3 *= 2;
		x4 *= 2;
		int y = h / 2;
		int y_m = y - h / 2, y_p = y + h / 2;
		y *= 2;
		y_m *= 2;
		y_p *= 2;
		int[] xs = { x1, x2, x3, x4, x3, x2 };
		int[] ys = { y, y_m, y_m, y, y_p, y_p };
		FRAME = new BufferedImage(x4 - x1 + 1, h * 2 + 1,
				BufferedImage.TYPE_4BYTE_ABGR_PRE);
		Graphics2D g = FRAME.createGraphics();
		BeautifulView.setAntiAliasing(g, true);
		g.setColor(new Color(100, 200, 250, 200));
		g.fillPolygon(xs, ys, 6);
		g.setColor(Color.BLUE);
		g.drawPolygon(xs, ys, 6);
		g.dispose();
		init();
	}

	public Otog_Select_Content_Left(Otog_Select_Content_Selection s, boolean b) {
		this(s.VER, s.LEVEL, b);
	}

	protected String convertString(int level) {
		switch (level) {
		case 1:
			return "壱";
		case 2:
			return "弐";
		case 3:
			return "参";
		case 4:
			return "終";
		case 5:
			return "悟";
		case 6:
			return "碌";
		case 7:
			return "無";
		case 8:
			return "辞";
		case 9:
			return "仇";
		case 0:
			return "零";
		}
		return null;
	}

	public void drawMe(Graphics2D g, int cols) {
		int x = -FR.SCREEN_WIDTH / 2 - 10;
		x += dx;
		if (IS_SELECTED)
			x += 4;
		int x1 = x, x2 = x + h / 2, x3 = x + FR.SCREEN_WIDTH * 3 / 4 - h / 2, x4 = x
				+ FR.SCREEN_WIDTH * 3 / 4;
		int y = (VER + 5) * (h + 2);
		int y_m = y - h / 2, y_p = y + h / 2;
		g.drawImage(FRAME, x1, y_m, FRAME.getWidth() / 2,
				FRAME.getHeight() / 2, null);
		int size = h / 2;
		int margine = 2;
		size += margine * 2;
		int title_head = x2 + size;
		drawTitle(g, title_head, y, x3 - margine - title_head, h - margine * 2);
	}

	protected void drawTitle(Graphics2D g, int x, int y, int w, int h) {
		StringBuilder sb = new StringBuilder();
		sb.append(VERSION);
		if (LEVEL == -1) {
			sb.append("  -");
		} else {
			sb.append(" ");
			if (LEVEL < 10)
				sb.append(" ");
			sb.append(LEVEL);
		}
		String str = sb.toString();
		if (frame != -1) {
			double d = MainThread.getFrame() / 16.0;
			g.setPaint(new GradientPaint((float) (w * Math.cos(d)) + x + w / 2,
					(float) (h * 2 * Math.sin(d)) + y, Color.WHITE, x + w / 2,
					y, Color.ORANGE, true));
		} else
			g.setColor(Color.BLUE);
		g.drawRect(x, y - h / 2, w, h);
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect(x, y - h / 2, w, h);
		// g.setClip(x, y - h / 2, w, h);
		g.setColor(Color.WHITE);
		int _w = g.getFontMetrics().stringWidth(
				StringFilter.getPlainString(str));
		StringFilter.drawString(g, str, x + w - 5 - _w, y
				+ g.getFontMetrics().getHeight() / 2 - 1);
		g.setClip(null);
	}

	protected int getW() {
		return FR.SCREEN_WIDTH * 3 / 4;
	}

	public void init() {
		dx -= IS_SELECTED ? 160 : 120;
		frame = IS_SELECTED ? 0 : -1;
	}

	public void upDate() {
		if (dx != 0) {
			dx = dx * 6 / 10;
		}
		if (dy != 0) {
			dy = dy * 6 / 10;
		}
	}
}
