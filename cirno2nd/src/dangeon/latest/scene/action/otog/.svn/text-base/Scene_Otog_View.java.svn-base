package dangeon.latest.scene.action.otog;

import java.awt.Color;
import java.awt.Graphics2D;

import main.constant.FR;
import main.thread.MainThread;
import main.util.BeautifulView;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.otog.main.panel.Base_Panel;
import dangeon.view.constant.NormalFont;

public class Scene_Otog_View extends Base_View {

	@Override
	public void draw(Graphics2D g, boolean current) {
		g.setFont(NormalFont.NORMALFONT);
		Scene_Otog s = (Scene_Otog) PARENT_SCENE;
		if (s.IM != null)
			g.drawImage(s.IM, 0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT, null);
		if (s.panel_frame < s.FRAME) {
			BeautifulView.setAlphaOnImg(g, (float) (++s.panel_frame / s.FRAME));
		}
		drawBlack(g);
		for (Base_Panel p : s.LIST) {
			// if (!(p instanceof Panel_Sub2))
			p.draw(g);
		}
		// s.main_panel.draw(g);
		BeautifulView.setAlphaOnImg(g, 1f);
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(MainThread.getFramerate()), 0,
				FR.SCREEN_HEIGHT);
	}
}
