package dangeon.latest.scene.action.ksg1;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import main.constant.FR;
import dangeon.latest.scene.Base_View;

public class Scene_Ksg_Title_View extends Base_View {

	public Scene_Ksg_Title_View(Base_View bv) {
		super();
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		g.setPaint(new GradientPaint(0, 0, Color.CYAN, 0, FR.SCREEN_HEIGHT,
				Color.WHITE));
		g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
	}
}
