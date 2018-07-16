package dangeon.latest.scene.action.otog.ready.select.select;

import java.awt.Graphics2D;

import main.constant.FR;
import dangeon.latest.scene.Base_View;
import dangeon.view.constant.NormalFont;

public class Otog_Select_View extends Base_View {

	public Otog_Select_View(Base_View bv) {
		super(bv);
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		Otog_Select s = (Otog_Select) PARENT_SCENE;
		drawBlack(g, 150);
		g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
		Otog_Select_Content_Selection c;
		for (int i = -1; i >= -4; i--) {
			c = s.getContent(i);
			if (c != null)
				c.drawMe(g, FR.SCREEN_WIDTH / 3, i);
		}
		for (int i = 1; i < 7; i++) {
			c = s.getContent(i);
			if (c != null)
				s.getContent(i).drawMe(g, FR.SCREEN_WIDTH / 3, i);
		}
		s.getContent(0).drawMe(g, FR.SCREEN_WIDTH / 3 - 20, 0);
		for (Otog_Select_Content_Left left : s.left) {
			left.drawMe(g, 0);
		}
		s.gauge.drawMe(g, 1, s);
	}

}
