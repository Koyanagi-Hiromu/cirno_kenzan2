package dangeon.latest.scene.action.menu.first.item.list.command.name;

import java.awt.Graphics2D;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.MenuWindow_SelfAdjusting;
import dangeon.latest.util.view_window.StringOnlyWindow;
import dangeon.view.constant.NormalFont;

public class Scene_Menu_First_Item_List_Command_Name_View extends
		Base_Scene_Menu_View {
	public StringOnlyWindow TOP_WINDOW;

	public Scene_Menu_First_Item_List_Command_Name_View(Base_View bv) {
		super(Message.X, 45, Image_Window_Frame.GLOW, bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		StringBuilder sb = new StringBuilder(
				((Scene_Menu_First_Item_List_Command_Name) PARENT_SCENE).sb
						.toString());
		while (sb.length() < 5) {
			sb.append("　");
		}
		TOP_WINDOW = new StringOnlyWindow(null, 5,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE),
				sb.toString());
		return new MenuWindow_SelfAdjusting(this,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
		// return new MenuWindow(this, Message.W,
		// NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE)) {
		// @Override
		// public int getContentHeight() {
		// return super.getContentHeight() - 3;
		// }
		//
		// @Override
		// protected void drawString(Graphics2D g, int x, int y) {
		// super.drawString(g, x, y);
		// for (int i = 0; i < Enchant.values().length; i++) {
		// Enchant e = Enchant.values()[i];
		// Base_Artifact a = e.getEnchant();
		// StringFilter.drawArtifactName(g, a, getX(x, i) + 80,
		// getY(g, y, i));
		// }
		// }
		// };
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBlack(g);
		drawBlack(g);
		if (TOP_WINDOW != null)
			TOP_WINDOW.drawWindow(g);
		super.draw(g, current);

	}
}
