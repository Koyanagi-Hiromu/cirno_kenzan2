package dangeon.latest.scene.action.ksg1.main;

import java.awt.Color;
import java.awt.Graphics2D;

import dangeon.latest.scene.Base_View;
import dangeon.view.util.StringFilter;
import main.constant.FR;
import main.res.BGM;
import main.res.Image_LargeCharacter;
import main.util.BeautifulView;

public class Ksg_Main_View extends Base_View {

	private Image_LargeCharacter IMLC;

	private int frame;

	private final int FRAME = 1000;

	public Ksg_Main_View(Base_View bv) {
		super();
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		Ksg_Main s = ((Ksg_Main) PARENT_SCENE);
		g.drawImage(s.IM, 0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT, null);
		int diff = 40;
		if (BGM.get() == null) {
			IMLC = null;
			frame = FRAME;
			Image_LargeCharacter.魂魄妖夢.draw(g, true, diff);
		} else {
			if (IMLC == null) {
				try {
					String card;
					card = BGM.get().CLASS.getSimpleName();
					card = card.substring(0, card.lastIndexOf("のカード"));
					IMLC = Image_LargeCharacter.get(card);
				} catch (Exception e) {
				}
			}
			if (frame > 0)
				frame--;
			float f = (float) frame / FRAME;
			BeautifulView.setAlphaOnImg(g, f);
			Image_LargeCharacter.魂魄妖夢.draw(g, true, diff);
			BeautifulView.setAlphaOnImg(g, 1 - f);
			if (IMLC != null)
				IMLC.draw(g, true, diff);
			BeautifulView.setAlphaOnImg(g, 1f);
		}
		Image_LargeCharacter.チルノ.draw(g, false, diff);

		s.PANEL.draw(g);

		g.setColor(Color.lightGray);
		{
			String text = "一番下の「食べ物」を仕分けてください";
			int w = g.getFontMetrics().stringWidth(text);
			StringFilter.drawString(g, text, (FR.SCREEN_WIDTH - w) / 2, 323);
		}
		{
			String text = "冷えてるものはダメですよ";
			int w = g.getFontMetrics().stringWidth(text);
			StringFilter.drawString(g, text, (FR.SCREEN_WIDTH - w) / 2, 338);
		}
		g.setColor(Color.white);
		{
			String text = "◀　▶";
			int w = g.getFontMetrics().stringWidth(text);
			StringFilter.drawString(g, text, (FR.SCREEN_WIDTH - w) / 2, 258);
		}
	}
}
