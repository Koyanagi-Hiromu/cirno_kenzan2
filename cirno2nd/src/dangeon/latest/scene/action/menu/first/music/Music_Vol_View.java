package dangeon.latest.scene.action.menu.first.music;

import java.awt.Color;
import java.awt.Graphics2D;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.util.view_window.Scrool_Bar;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Music_Vol_View extends Base_Scene_Menu_View {
	public Scrool_Bar BAR;

	public Music_Vol_View(Base_View bv, int x, int y) {
		super(x, y, Image_Window_Frame.GLOW, bv);
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		Music_Vol s = (Music_Vol) PARENT_SCENE;
		if (BAR == null) {
			BAR = new Scrool_Bar(X, Y, 4, NormalFont.NORMALFONT, 100, s) {
				@Override
				protected int getViewY() {
					int Y = this.Y - BAR.getHeight_MiddleArea() / 2;
					int max = ((Base_Scene_Menu_View) PREVIOUSE_VIEW).Y;
					if (Y < max)
						Y = max;
					return Y;
				}

				@Override
				public void setY(int index) {
					up = index > 0;
					down = index < LENGTH;
				}
			};
			BAR.setY(s.getY());
		}
		BAR.drawWindow(g);
		StringBuilder sb = new StringBuilder("→");
		int v = 100 - s.getY();
		if (v < 100)
			sb.append(" ");
		if (v < 10)
			sb.append(" ");
		sb.append(v);
		g.setColor(Color.CYAN);
		StringFilter.drawString(g, sb.toString(), 258, (s.INDEX == 0) ? 93
				: 116);

	}
}
