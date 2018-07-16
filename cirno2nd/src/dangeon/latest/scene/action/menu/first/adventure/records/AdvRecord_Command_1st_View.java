package dangeon.latest.scene.action.menu.first.adventure.records;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.MenuWindow_SelfAdjusting;
import dangeon.view.constant.NormalFont;

public class AdvRecord_Command_1st_View extends Base_Scene_Menu_View {

	public AdvRecord_Command_1st_View(Base_View bv) {
		super(5, 25, Image_Window_Frame.GLOW, bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		return new MenuWindow_SelfAdjusting(this,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
	}

}
