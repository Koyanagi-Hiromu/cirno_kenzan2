package dangeon.latest.scene.action.menu;

import java.awt.Graphics2D;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.MenuWindow_SelfAdjusting;

public class Base_Scene_Menu_View extends Base_View {
	public Integer X, Y;
	public final Image_Window_Frame TYPE;
	public MenuWindow MENU_WINDOW;

	/**
	 * you must override "draw" and "createMenuWindow"
	 * 
	 * @param bv
	 */
	public Base_Scene_Menu_View(Base_View bv) {
		super(bv);
		TYPE = null;
	}

	/**
	 * 
	 * @param x
	 *            null => centering
	 * @param y
	 *            null => middling
	 * @param type
	 * @param bv
	 */
	public Base_Scene_Menu_View(Integer x, Integer y, Image_Window_Frame type,
			Base_View bv) {
		super(bv);
		X = x;
		Y = y;
		TYPE = type;
	}

	protected MenuWindow createMenuWindow() {
		return new MenuWindow_SelfAdjusting(this);
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		if (MENU_WINDOW == null) {
			((Base_Scene_Menu) PARENT_SCENE).initializeContents();
			MENU_WINDOW = createMenuWindow();
		}
		MENU_WINDOW.drawWindow(g, current);
	}
}
