package dangeon.latest.scene.action.menu.first.adventure.records;

import java.awt.Graphics2D;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.MenuWindow_SelfAdjusting;
import dangeon.view.constant.NormalFont;

public class AdvRecord_Command_View extends Base_Scene_Menu_View {

	public AdvRecord_Command_View(Base_View bv) {
		super(70, 25, Image_Window_Frame.GLOW, bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		return new MenuWindow_SelfAdjusting(this,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE - 2)) {
			@Override
			protected void drawSuikaIcon(Graphics2D g, boolean current) {
				if (!isSelectedGlow())
					return;
				super.drawSuikaIcon(g, current);
			}

			@Override
			public void drawWindow(Graphics2D g, boolean current) {
				if (!isSelectedGlow())
					current = true;
				super.drawWindow(g, current);
			}

			@Override
			protected boolean isSelectedGlow() {
				return ((AdvRecord_Command) PARENT_SCENE).isCurrent();
			}
		};
	}

}
