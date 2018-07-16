package dangeon.view.util;

import java.awt.Color;
import java.awt.Graphics2D;

import main.constant.FR;
import main.util.BeautifulView;

public class ScreenFadeInOut {
	public static int fade_time = 0;
	public static boolean flag_fadeout = false, flag_fadein = false;
	public static final int FADE_INTERVAL = 20;

	public static void fadeInOut(Graphics2D g) {
		float r;
		if (fade_time > 0) {
			fade_time--;
			double rate = (double) fade_time / FADE_INTERVAL;
			rate = Math.cos(rate);
			r = new Float(rate);
			if (flag_fadein)
				r = 1F - r;
		} else {
			r = (flag_fadein) ? 0F : 1F;
		}
		BeautifulView.setAlphaOnImg(g, r);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
	}
}
