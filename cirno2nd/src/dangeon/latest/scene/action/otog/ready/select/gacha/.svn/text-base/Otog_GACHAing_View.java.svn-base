package dangeon.latest.scene.action.otog.ready.select.gacha;

import java.awt.Graphics2D;
import java.awt.Image;

import main.constant.FR;
import main.res.CHARA_IMAGE;
import main.res.Image_Player;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_View;
import dangeon.view.constant.NormalFont;

public class Otog_GACHAing_View extends Base_View {

	public Otog_GACHAing_View(Base_View bv) {
		super(bv);
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		if (current) {
			drawBlack(g, 200);
			Otog_GACHAing s = (Otog_GACHAing) PARENT_SCENE;
			if (s.flag_cirno_moving) {
				int i = s.frame * 3 / s.MAX_FRAME;
				if (i > 2)
					i = 2;
				Image im = CHARA_IMAGE.チルノ.WALK[0][DIRECTION.LEFT.getRotate()][i];
				g.drawImage(im, 50 * (3 - i), 100, null);
			} else {
				g.drawImage(Image_Player.rising_hand.IM, 50, 100, null);
				g.setFont(NormalFont.NORMALFONT
						.deriveFont(NormalFont.SMALL_SIZE));
				for (int i = 0; i < s.content.size(); i++) {
					s.content.get(i).drawMe(g, FR.SCREEN_WIDTH / 4, i + 2);
				}
			}
		}
	}

}
