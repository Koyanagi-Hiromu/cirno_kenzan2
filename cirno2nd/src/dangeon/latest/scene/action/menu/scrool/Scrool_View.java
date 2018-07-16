package dangeon.latest.scene.action.menu.scrool;

import java.awt.Graphics2D;

import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;

public class Scrool_View extends Base_Scene_Menu_View {

	public Scrool_View(Base_View bv) {
		super(bv);
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBlack(g);
		((Scrool) PARENT_SCENE).WINDOW.drawWindow(g, current);
	}
}
