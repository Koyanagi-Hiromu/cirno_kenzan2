package dangeon.latest.scene.action.menu.first.adventure.medal;

import java.awt.Graphics2D;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.scene.action.menu.first.item.list.Scene_Menu_First_Item_List_View;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.MenuWindow_SelfAdjusting;
import dangeon.view.constant.NormalFont;

public class Medal_Commnad_View extends Base_Scene_Menu_View {

	public Medal_Commnad_View(Base_View bv) {
		super(-Scene_Menu_First_Item_List_View.X, 38, Image_Window_Frame.GLOW,
				bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		return new MenuWindow_SelfAdjusting(this,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBlack(g);
		((Medal_Commnad) PARENT_SCENE).SUB_WINDOW.drawWindow(g);
		((Medal_Commnad) PARENT_SCENE).sub_left_window.drawWindow(g);
		super.draw(g, current);
	}

}
