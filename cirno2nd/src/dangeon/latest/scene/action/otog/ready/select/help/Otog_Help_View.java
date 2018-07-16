package dangeon.latest.scene.action.otog.ready.select.help;

import java.awt.Graphics2D;

import dangeon.latest.scene.Base_View;

public class Otog_Help_View extends Base_View {
	public Otog_Help_View(Base_View bv) {
		super(bv);
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBlack(g, 200);
		((Otog_Help) PARENT_SCENE).WINDOW.drawWindow(g);
	}
}