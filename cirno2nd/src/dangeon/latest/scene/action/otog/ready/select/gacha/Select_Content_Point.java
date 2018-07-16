package dangeon.latest.scene.action.otog.ready.select.gacha;

import java.awt.Color;
import java.awt.Graphics2D;

import main.constant.FR;
import dangeon.latest.scene.action.otog.ready.select.select.Otog_Select_Content_Left;
import dangeon.view.util.StringFilter;

public class Select_Content_Point extends Otog_Select_Content_Left {
	public String POINT;

	private int selected_y = -1;

	public Select_Content_Point() {
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

	public void drawMe(Graphics2D g) {
		int x = -FR.SCREEN_WIDTH / 2 + 2 + dx;
		int x1 = x, x2 = x + h / 2, x3 = x + FR.SCREEN_WIDTH * 3 / 4 - h / 2, x4 = x
				+ FR.SCREEN_WIDTH * 3 / 4;
		int y = 3 * (h + 2);
		int y_m = y - h / 2, y_p = y + h / 2;
		g.drawImage(FRAME, x1, y_m, FRAME.getWidth() / 2,
				FRAME.getHeight() / 2, null);
		int size = h / 2;
		int margine = 2;
		size += margine * 2;
		int title_head = x2 + size;
		drawTitle(g, title_head, y, x3 - margine - title_head, h - margine * 2);
	}

	@Override
	protected void drawTitle(Graphics2D g, int x, int y, int w, int h) {
		String str = POINT;
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

	public void setTitle(int p) {
		POINT = String.valueOf(p).concat(" p");
	}
}
