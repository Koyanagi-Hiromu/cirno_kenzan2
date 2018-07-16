package dangeon.latest.scene.action.menu.first.adventure.wiki_item;

import java.awt.Graphics2D;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.view.constant.NormalFont;

public class ItemWiki_View extends Base_Scene_Menu_View {
	public ItemWiki_View() {
		super(25, null, Image_Window_Frame.LINE,
				Scene_Action.getMe().CURRENT_VIEW);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		return new MenuWindow(this, 247,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE)) {
			@Override
			protected void drawBackLight(Graphics2D g, boolean current,
					int index) {
				if (current || ((ItemWiki) PARENT_SCENE).flag_selected)
					super.drawBackLight(g, current, index);
			}

			@Override
			protected void drawSuikaIcon(Graphics2D g, boolean current) {
				if (current || ((ItemWiki) PARENT_SCENE).flag_selected)
					super.drawSuikaIcon(g, current);
			}
		};
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBlack(g);
		((ItemWiki) PARENT_SCENE).SUB_WINDOW.drawWindow(g);
		super.draw(g, current);
	}
}