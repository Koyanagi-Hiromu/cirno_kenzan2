package dangeon.latest.scene.action.otog.main.panel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.text.NumberFormat;

import main.constant.FR;
import dangeon.view.util.StringFilter;

public class Panel_East extends Base_Panel {

	public Panel_East(Panel_Main p, Panel_Sub s, int margine) {
		super(s.X, p.Y, FR.SCREEN_WIDTH - (p.X + p.W + margine * 2), p.H - s.H
				- margine, p);
	}

	@Override
	public void drawME(Graphics2D g) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		int x1 = 5, x2 = W - 5;
		int y = 22;
		int h = 20;
		g.setColor(Color.ORANGE);
		StringFilter.drawString(g, "MAX COMBO", x1, y);
		y += h;
		String s = nf.format(P.VH.MAX_COMBO);
		StringFilter
				.drawString(g, s, x2 - g.getFontMetrics().stringWidth(s), y);
		y += h + 8;
		g.setColor(Color.YELLOW);
		StringFilter.drawString(g, "SCORE", x1, y);
		y += h;
		s = nf.format(P.VH.score_milli / 1000);
		StringFilter
				.drawString(g, s, x2 - g.getFontMetrics().stringWidth(s), y);
	}

}
