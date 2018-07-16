package dangeon.latest.scene.action.otog.ready.select.gacha;

import java.awt.Graphics2D;

import main.constant.FR;
import dangeon.latest.scene.Base_View;
import dangeon.view.constant.NormalFont;

public class Otog_GACHA_View extends Base_View {

	public Otog_GACHA_View(Base_View bv) {
		super(bv);
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		if (current) {
			Otog_GACHA s = (Otog_GACHA) PARENT_SCENE;
			g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
			for (int i = 0; i < s.content.size(); i++) {
				s.content.get(i).drawMe(g, FR.SCREEN_WIDTH / 4, i + 2);
			}
			s.POINT.drawMe(g);
		}
	}

}
