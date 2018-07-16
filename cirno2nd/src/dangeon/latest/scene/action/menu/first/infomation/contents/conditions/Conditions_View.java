package dangeon.latest.scene.action.menu.first.infomation.contents.conditions;

import java.awt.Graphics2D;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.scene.action.menu.first.item.list.Scene_Menu_First_Item_List_View;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.MenuWindow_SelfAdjusting;
import dangeon.view.constant.NormalFont;

public class Conditions_View extends Base_Scene_Menu_View {

	public Conditions_View(Base_View bv) {
		super(Scene_Menu_First_Item_List_View.X
		// + Scene_Menu_First_Item_List_View.MAIN_W
		// + Scene_Menu_First_Item_List_View.MARGINE
				, 36 + 29, Image_Window_Frame.LINE, bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		return new MenuWindow_SelfAdjusting(this, Message.W,
				NormalFont.NORMALFONT.deriveFont(11f));
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBlack(g);
		if (current)
			((Conditions) PARENT_SCENE).SUB_WINDOW.drawWindow(g);
		super.draw(g, current);
	}

}
