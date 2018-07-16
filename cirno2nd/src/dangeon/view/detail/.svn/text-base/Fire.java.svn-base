package dangeon.view.detail;

import java.awt.Color;
import java.awt.Graphics2D;

import dangeon.controller.listener.menu.ArrowSet;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.EnchantArrow;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.Window;

public class Fire {

	// private static final Image ENCHANT_LIST_WINDOW;
	private static final int W, H;
	static {
		W = NormalFont.WIDTH2 * 2 + SCREEN.X_MARGINE * 2;
		H = Window.getNarrowWindowH(EnchantArrow.length());
		// ENCHANT_LIST_WINDOW = Window.get(W, H);
	}

	public static void draw(Graphics2D g) {
		int x = SCREEN.X + Menu_First.W + SCREEN.WINDOW_MARGINE;
		int y = SCREEN.Y;
		g.drawImage(Window.get(W, H), x, y, null);
		x += SCREEN.X_MARGINE;
		y += SCREEN.Y_MARGINE + NormalFont.HEIGHT;
		for (int i = 0; i < EnchantArrow.length(); i++) {
			drawItem(g, EnchantArrow.get(i), x, y);
			if (ArrowSet.getY() == i) {
				View_Cursor.draw(g, x, y, true);
			}
			y += NormalFont.HEIGHT + NormalFont.DECENT;
		}
	}

	private static void drawItem(Graphics2D g, EnchantArrow a, int x, int y) {
		Item.drawItemName(g, x, y, a.IM, a.name());
		if (EnchantArrow.getArrow() == a) {
			Item.drawItemEnchanted(g, x, y, Color.WHITE);
		}
		Arrow arrow = Belongings.getArrow(a.CLAZZ);
		if (arrow == null) {
			Item.drawItemSholder(g, x, y, Color.PINK, "0");
		} else {
			Item.drawItemSholder(g, x, y, Color.YELLOW,
					String.valueOf(arrow.getArrowRest()));
		}
	}
}
