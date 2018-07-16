package dangeon.latest.scene.action.itemlist;

import java.awt.Graphics2D;

import main.constant.FR;
import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.first.Scene_Menu_First_View;
import dangeon.latest.util.view_window.ItemExplainWindow;
import dangeon.latest.util.view_window.ItemSelectMenuWindow;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.StringOnlyWindow;
import dangeon.view.constant.NormalFont;

public class Item_List_View extends Scene_Menu_First_View {

	protected StringOnlyWindow TOP_WINDOW;
	public ItemExplainWindow BOTTOM_WINDOW;
	public static final int MAIN_W = 270, MARGINE = 19,
			MINI_W = FR.SCREEN_WIDTH - MAIN_W - 30 - MARGINE, Y = 40, X = 6;

	public Item_List_View(Base_View bv) {
		this(bv, false);
		// super(-X, Y, Image_Window_Frame.LINE, bv);
	}

	public Item_List_View(Base_View bv, boolean left) {
		super(X, Y, Image_Window_Frame.LINE, bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		Item_List smfil = (Item_List) PARENT_SCENE;
		BOTTOM_WINDOW = new ItemExplainWindow(smfil);
		TOP_WINDOW = createTop();

		return new ItemSelectMenuWindow(this, MAIN_W,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
	}

	protected StringOnlyWindow createTop() {
		int x = (super.X < 0) ? -X : -(MINI_W + 25);
		return new StringOnlyWindow(x, 2,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE),
				getTop()) {
		};
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawIMLC(g);
		drawBlack(g);
		super_draw(g, current);
		BOTTOM_WINDOW.drawWindow(g);
		TOP_WINDOW.drawWindow(g);
	}

	// private void setPages(StringBuilder sb, Item_List list) {
	// sb.append("(");
	// sb.append(list.page + 1);
	// sb.append("/");
	// sb.append(list.getMaxPage());
	// sb.append(")");
	// }

	public String getTop() {
		Item_List list = (Item_List) PARENT_SCENE;
		return list.getTop();
		// StringBuilder sb = new StringBuilder(list.TITLE);
		// int max = list.getMaxPage();
		// if (max == 0 || max == 1) {
		// if (list instanceof Item_List_Foot)
		// sb.append("(足下)");
		// } else {
		// setPages(sb, list);
		// }
		// if (sb.length() == 0) {
		// setPages(sb, list);
		// }
		// return sb.toString();
	}

	public void setTopWindow() {
		if (TOP_WINDOW != null)
			TOP_WINDOW.setString(getTop());

	}

}
