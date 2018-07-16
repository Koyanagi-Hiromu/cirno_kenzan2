package main.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class BeautifulView {

	/**
	 * 透過処理
	 * 
	 * @param g
	 * @param f
	 *            0F〜1Fの小数で設定。1Fで変化なし　0Fで不可視。
	 */
	public static void setAlphaOnImg(Graphics2D g, Float f) {
		if (f < 0.0f) {
			f = 0.0f;
		} else if (f > 1.0f) {
			f = 1.0f;
		}
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f));
	}

	/**
	 * アンチエイリアシングをかける。
	 * 
	 * @param g
	 */
	public static void setAntiAliasing(Graphics2D g, boolean b) {
		if (b) {
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		} else {
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_OFF);
		}

	}

}
