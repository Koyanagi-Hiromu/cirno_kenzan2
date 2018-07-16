package dangeon.latest.scene.action.otog.ready.select.select;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import main.constant.FR;
import main.thread.MainThread;
import dangeon.view.util.StringFilter;

public class Select_Content_Gauge extends Otog_Select_Content_Left {

	private int flag = 0;

	private int selected_y = -1;

	public Select_Content_Gauge() {
		super(1, -1, false);
	}

	@Override
	protected String convertString(int level) {
		switch (level) {
		case 1:
			return "壱";
		case 2:
			return "弐";
		case 3:
			return "参";
		case 4:
			return "四";
		case 5:
			return "五";
		case 6:
			return "陸";
		case 7:
			return "七";
		case 8:
			return "八";
		case 9:
			return "九";
		case 0:
			return "零";
		}
		return null;
	}

	public void drawMe(Graphics2D g, int cols, Otog_Select s) {
		int x = -FR.SCREEN_WIDTH / 2 + 2 + dx;
		if (flag == selected_y)
			x += 4;
		int x1 = x, x2 = x + h / 2, x3 = x + FR.SCREEN_WIDTH * 3 / 4 - h / 2, x4 = x
				+ FR.SCREEN_WIDTH * 3 / 4;
		int y = cols * (h + 2);
		int y_m = y - h / 2, y_p = y + h / 2;
		int[] xs = { x1, x2, x3, x4, x3, x2 };
		int[] ys = { y, y_m, y_m, y, y_p, y_p };
		g.drawImage(FRAME, x1, y_m, FRAME.getWidth() / 2,
				FRAME.getHeight() / 2, null);
		// g.setColor(Color.BLUE);
		// g.drawPolygon(xs, ys, 6);
		// g.setColor(new Color(50, 50, 50, 200));
		// g.fillPolygon(xs, ys, 6);
		// g.setPaint(null);
		int size = h / 2;
		int margine = 2;
		size += margine * 2;
		int title_head = x2 + size;
		drawTitle(g, title_head, y, x3 - margine - title_head, h - margine * 2,
				s);
		if (flag++ == 3)
			flag = 0;
		else
			drawMe(g, cols + 1, s);
	}

	protected void drawTitle(Graphics2D g, int x, int y, int w, int h,
			Otog_Select s) {
		String str;
		if (flag == 1) {
			str = "GAUGE:" + s.VH.getDifficulty();
		} else if (flag == 3) {
			str = "RANDOM:";
			int r = s.VH.flag_random;
			if (r == 1) {
				str = str.concat(Color.ORANGE.toString()).concat("ON")
						.concat(Color.RED.toString()).concat("a");
			} else if (r == 2) {
				str = str.concat(Color.ORANGE.toString()).concat("ON")
						.concat(Color.GREEN.toString()).concat("b");
			} else {
				str = str.concat(Color.GRAY.toString()).concat("OFF");
			}
		} else if (flag == 2) {
			int speed = s.VH.SPEED * 2;
			str = "SPEED x";
			str = str.concat(String.valueOf(speed / 10)).concat(".");
			speed = speed % 10;
			str = str.concat(String.valueOf(speed));
		} else {
			str = "Sort:";
			if (s.VH.select_sort == 0)
				str = str.concat(Color.CYAN.toString()).concat("LEVEL");
			else if (s.VH.select_sort == 1)
				str = str.concat(Color.PINK.toString()).concat("TITLE");
			else if (s.VH.select_sort == 2)
				str = str.concat(Color.LIGHT_GRAY.toString()).concat("SCORE");
		}
		if (flag == selected_y) {
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

	public void set(int sub_y) {
		selected_y = sub_y;
	}
}
