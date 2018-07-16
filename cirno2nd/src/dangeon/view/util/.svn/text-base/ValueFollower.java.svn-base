package dangeon.view.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.constant.FR;
import main.thread.MainThread;
import dangeon.controller.TurnSystemController;
import dangeon.view.constant.NormalFont;

public class ValueFollower {

	private static String chase = "";

	private static String chase2 = "";

	public static void drawAnyoneYouWanaChase(Graphics2D g) {
		boolean test = false;
		if (!test)
			return;
		Font f = g.getFont();
		g.setFont(f.deriveFont(f.getSize() - 5f));
		StringFilter
				.drawEdgedString_plain(g, chase, FR.SCREEN_WIDTH
						- g.getFontMetrics().stringWidth(chase) - 10,
						NormalFont.HEIGHT);
		StringFilter.drawEdgedString_plain(g, chase2, FR.SCREEN_WIDTH
				- g.getFontMetrics().stringWidth(chase2) - 10,
				NormalFont.HEIGHT * 2 + NormalFont.DECENT);
		g.setFont(f);
	}

	public static void drawEnemyTurnStep(Graphics2D g) {
		g.drawString(TurnSystemController.getStepName(), 0,
				FR.SCREEN_HEIGHT - 25);
	}

	public static void drawFrameRate(Graphics2D g) {
		boolean n = false;
		if (n) {
			g.setColor(Color.WHITE);
			StringFilter.drawEdgedString(g,
					String.valueOf(MainThread.getFramerate()) + " fps", 5,
					FR.SCREEN_HEIGHT - 5);
		}
	}

	public static void setChase(Object o) {
		if (o == null) {
			chase = "null";
		} else {
			chase = o.toString();
		}
		setChase2("");
	}

	public static void setChase2(Object obj) {
		if (obj == null) {
			chase2 = "null";
		} else {
			chase2 = obj.toString();
		}
	}

}
