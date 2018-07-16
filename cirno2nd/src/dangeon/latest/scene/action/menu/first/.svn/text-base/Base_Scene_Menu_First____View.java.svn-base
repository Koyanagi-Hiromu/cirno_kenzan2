package dangeon.latest.scene.action.menu.first;

import java.awt.Graphics2D;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;

public class Base_Scene_Menu_First____View extends Base_Scene_Menu_View {

	public Base_Scene_Menu_First____View(Base_View bv) {
		super(null, 65, Image_Window_Frame.GLOW, bv);
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		super.draw(g, current);
		if (PREVIOUSE_VIEW instanceof Scene_Menu_First_View) {
			((Scene_Menu_First_View) PREVIOUSE_VIEW).drawStatus(g, true);
		}
	}
}
