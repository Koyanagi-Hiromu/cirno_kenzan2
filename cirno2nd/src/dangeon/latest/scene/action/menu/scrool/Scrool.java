package dangeon.latest.scene.action.menu.scrool;

import java.util.ArrayList;

import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.ScroolWindow;
import dangeon.latest.util.view_window.UnderMenuWindow;

public class Scrool extends Base_Scene_Menu {
	public final ScroolWindow WINDOW;

	public Scrool(int cols, KeyHolder kh, Base_View bv,
			ArrayList<String> contents) {
		this(cols, kh, bv, contents.toArray(new String[0]));
	}

	private Scrool(int cols, KeyHolder kh, Base_View bv, String... contents) {
		super(cols, kh, new Scrool_View(bv));
		WINDOW = createScroolWindow(contents);
		y = getFirstIndex();
		WINDOW.setY(y);
	}

	public Scrool(KeyHolder kh, Base_View bv, String... contents) {
		this(13, kh, bv, contents);
	}

	@Override
	protected void action_else() {
	}

	@Override
	public boolean arrow(DIRECTION d) {
		if (d.Y != 0)
			y += d.Y;
		else
			y += d.X * 5;
		if (y < 0)
			y = 0;
		while (!WINDOW.isExist(y))
			y--;

		SE.SYSTEM_CURSOR.play();
		WINDOW.setY(y);
		return END;
	}

	protected ScroolWindow createScroolWindow(String... contents) {
		return new ScroolWindow(this, contents);
	}

	protected int getFirstIndex() {
		return 0;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
	}

	@Override
	protected UnderMenuWindow initializeSubWindow() {
		return null;
	}

}