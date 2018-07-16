package dangeon.latest.scene.action.menu.first.music;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.MenuWindow_SelfAdjusting;

public class Music_Selection_View extends Base_Scene_Menu_View {

	public Music_Selection_View(Base_View bv, int x, int y) {
		super(x, y, Image_Window_Frame.GLOW, bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		MenuWindow_SelfAdjusting w = new MenuWindow_SelfAdjusting(this);
		Y -= w.getHeight_MiddleArea() / 2;
		int max = Message.Y - w.getHeight() - 4;
		if (Y > max)
			Y = max;
		return w;
	}
}
