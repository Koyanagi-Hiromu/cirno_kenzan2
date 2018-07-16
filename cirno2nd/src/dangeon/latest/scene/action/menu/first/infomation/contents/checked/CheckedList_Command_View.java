package dangeon.latest.scene.action.menu.first.infomation.contents.checked;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.scene.action.menu.first.item.list.Scene_Menu_First_Item_List_View;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.MenuWindow_SelfAdjusting;
import dangeon.view.constant.NormalFont;

public class CheckedList_Command_View extends Base_Scene_Menu_View {

	public CheckedList_Command_View(Base_View bv) {
		super(Scene_Menu_First_Item_List_View.X
				+ Scene_Menu_First_Item_List_View.MAIN_W
				+ Scene_Menu_First_Item_List_View.MARGINE, 36,
				Image_Window_Frame.GLOW, bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		return new MenuWindow_SelfAdjusting(this,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
	}

}
