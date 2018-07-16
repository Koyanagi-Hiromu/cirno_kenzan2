package dangeon.latest.scene.action.ksg1.main.select;

import java.awt.Color;
import java.awt.Graphics2D;
import java.text.NumberFormat;

import main.constant.FR;
import main.thread.MainThread;
import dangeon.latest.scene.Base_View;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Ksg_End_View extends Base_View {

	public Ksg_End_View(Base_View bv) {
		super(bv);
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBlack(g);
		g.setFont(NormalFont.NORMALFONT.deriveFont(16f));
		Ksg_End s = (Ksg_End) PARENT_SCENE;
		String num = NumberFormat.getInstance().format(s.COUNT);
		int x = 240;
		int y = 40;
		int dx;
		g.setColor(Color.RED);
		String str = "GAME OVER";
		dx = g.getFontMetrics().stringWidth(str);
		StringFilter.drawString(g, str, x - dx, y);
		y += 20;
		dx = g.getFontMetrics().stringWidth(num);
		StringFilter.drawString(g, num, x - dx, y);
		g.setColor(Color.ORANGE);
		StringFilter.drawString(g, "pt", x + 5, y);
		if (s.FLAG_NEW_RECORD) {
			g.setColor(MainThread.getFrame() / 2 % 2 == 0 ? Color.CYAN
					: Color.YELLOW);
			StringFilter.drawString(g, "NEW", x + 30, y);
		}
		y += 20;
		g.setColor(Color.YELLOW);
		str = "HIGH SCORE";
		dx = g.getFontMetrics().stringWidth(str);
		StringFilter.drawString(g, str, x - dx, y);
		y += 20;
		num = NumberFormat.getInstance().format(s.HIGH_SCORE);
		dx = g.getFontMetrics().stringWidth(num);
		StringFilter.drawString(g, num, x - dx, y);
		g.setColor(Color.ORANGE);
		StringFilter.drawString(g, "pt", x + 5, y);
		if (s.FLAG_NEW_RECORD) {
			g.setColor(MainThread.getFrame() / 2 % 2 == 0 ? Color.CYAN
					: Color.YELLOW);
			StringFilter.drawString(g, "NEW", x + 30, y);
		}
		g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
		for (int i = 0; i < s.content.size(); i++) {
			s.content.get(i).drawMe(g, FR.SCREEN_WIDTH / 4, i + 2);
		}
	}

}
