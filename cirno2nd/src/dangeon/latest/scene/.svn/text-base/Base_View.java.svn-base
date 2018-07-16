package dangeon.latest.scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.constant.FR;
import title.objects.Base_Title_Object;
import dangeon.model.config.Config;

public abstract class Base_View {
	public Base_Scene PARENT_SCENE = null;
	public final ArrayList<Base_Title_Object> objects = new ArrayList<Base_Title_Object>();
	public final Base_View PREVIOUSE_VIEW;

	public Base_View() {
		PREVIOUSE_VIEW = null;
	}

	public Base_View(Base_View bv) {
		PREVIOUSE_VIEW = bv;
	}

	public final void beginToDraw(Graphics2D g, boolean current) {
		if (PREVIOUSE_VIEW != null)
			PREVIOUSE_VIEW.beginToDraw(g, false);
		draw(g, current);
	}

	public abstract void draw(Graphics2D g, boolean current);

	protected final void drawBlack(Graphics2D g) {
		if (Config.isLightVer())
			return;
		g.setColor(new Color(0, 0, 0, 125));
		g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
		g.setColor(Color.WHITE);
	}

	protected final void drawBlack(Graphics2D g, int i) {
		g.setColor(new Color(0, 0, 0, i));
		g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
		g.setColor(Color.WHITE);
	}

	void setParentScene(Base_Scene base_Scene) {
		PARENT_SCENE = base_Scene;
	}
}
