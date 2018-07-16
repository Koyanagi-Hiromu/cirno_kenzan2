package dangeon.latest.scene.action.otog.ready.select.keys;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Listener.ACTION;
import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu.MenuContent;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.MenuWindow_SelfAdjusting;
import dangeon.latest.util.view_window.WindowFrame;
import dangeon.model.config.Config;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class OtogKeyConfig_View extends Base_Scene_Menu_View {

	public OtogKeyConfig_View(Base_View bv) {
		super(null, null, Image_Window_Frame.LINE, bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		return new MenuWindow_SelfAdjusting(this, Message.W / 3,
				NormalFont.NORMALFONT.deriveFont(11f)) {
			private void drawKeyBord(Graphics2D g, int x, int y, String s) {
				x += W / 4;
				StringFilter.drawString(g, s, x, y);
			}

			@Override
			protected void drawString(Graphics2D g, int x, int y) {
				g.setFont(FONT);
				int _x = getX(x, 0);
				int _y = getY(g, y, 0) + 3;
				g.setColor(StringFilter.NUMBERS);
				drawTitle(g, _x, _y, "ID");
				g.setColor(Color.ORANGE);
				drawKeyBord(g, _x, _y, "ACTION");
				super.drawString(g, x, y);
			}

			@Override
			protected void drawStringFilter(Graphics2D g, int i, int x, int y) {
				MenuContent c = SCENE.getContent(i);
				if (c.OBJECT instanceof Integer) {
					Integer id = (Integer) c.OBJECT;
					x = getX(x, i);
					y = getY(g, y, i);
					drawTitle(g, x, y, String.valueOf(id));
					g.setColor(Color.WHITE);
					ACTION a = Config.getOtogKey(id);
					for (int j = 1; j < 5; j++) {
						if (id != j && a == Config.getOtogKey(j)) {
							g.setColor(Color.YELLOW);
							break;
						}
					}
					drawKeyBord(g, x, y, a.name());
				} else {
					String s = SCENE.getContentName(i);
					if (WindowFrame.HORIZON.matches(s))
						drawLine(g, i - 1, true);
					else
						StringFilter.drawString_plain(g, s, getX(x, i),
								getY(g, y, i));
				}
			}

			private void drawTitle(Graphics2D g, int x, int y, String s) {
				StringFilter.drawString(g, s, x, y);
			}

			@Override
			protected int getW_Inside() {
				CONTENTS_WIDTH = new int[SCENE.ROW];
				for (int i = 0; i < CONTENTS_WIDTH.length; i++) {
					CONTENTS_WIDTH[i] = W / CONTENTS_WIDTH.length;
				}
				return W;
			}
		};
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBlack(g);
		drawBlack(g);
		drawBlack(g);
		super.draw(g, current);
	}
}
