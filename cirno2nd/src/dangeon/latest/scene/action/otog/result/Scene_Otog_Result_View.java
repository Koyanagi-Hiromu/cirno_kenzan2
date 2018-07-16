package dangeon.latest.scene.action.otog.result;

import java.awt.Graphics2D;

import main.constant.FR;
import dangeon.latest.scene.Base_View;
import dangeon.view.constant.NormalFont;

public class Scene_Otog_Result_View extends Base_View {

	public Scene_Otog_Result_View() {
		super();
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		Scene_Otog_Result s = (Scene_Otog_Result) PARENT_SCENE;
		if (s.PARENT.IM != null)
			g.drawImage(s.PARENT.IM, 0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT,
					null);
		g.setFont(NormalFont.NORMALFONT);
		drawBlack(g);
		drawBlack(g);
		s.PANEL.draw(g);
	}
}
