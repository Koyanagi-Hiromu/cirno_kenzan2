package dangeon.view.detail;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Second_Firster;
import dangeon.controller.listener.menu.Menu_Select;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.StringFilter;
import dangeon.view.util.Window;

public class View_SelectStairs {
	private static Image WINDOW;

	public static void draw(Graphics2D g) {
		Menu_Select.upDate();
		g.drawImage(WINDOW, SCREEN.X, SCREEN.Y, null);
		drawCommands(g);
		View_Cursor.draw(g, SCREEN.X + Window.getStringX(0),
				SCREEN.Y + Window.getNarrowStringY(Menu_Select.getY()), true);
	}

	private static void drawCommands(Graphics2D g) {
		int x = SCREEN.X + Window.getStringX(0);
		for (int i = 0; i < Menu_Select.getCommands().length; i++) {
			int y = SCREEN.Y + Window.getNarrowStringY(i);
			StringFilter.drawString(g, Menu_Select.getCommands()[i], x, y);
		}
	}

	public static void setWndow(String max_str, int length) {
		WINDOW = Window.get(
				Second_Firster.ME.getFontMetrics(NormalFont.NORMALFONT)
						.stringWidth(max_str) + SCREEN.X_MARGINE * 2,
				Window.getNarrowWindowH(length));
	}

}
