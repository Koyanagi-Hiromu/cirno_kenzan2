package dangeon.latest.scene.action.menu.view;

import java.util.ArrayList;

import main.util.DIRECTION;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.StringOnlyWindow;
import dangeon.latest.util.view_window.UnderMenuWindow;

public class Plain extends Base_Scene_Menu {
	public final StringOnlyWindow WINDOW;
	private final Integer W;

	/**
	 * over-ride getContents
	 * 
	 * @param kh
	 * @param bv
	 */
	protected Plain(boolean fixed_height, KeyHolder kh, Base_View bv,
			Integer w, String... contents) {
		super(fixed_height ? 13 : null, kh, new Plain_View(bv));
		if (contents.length == 1 && contents[0].isEmpty()) {
			ArrayList<String> list = new ArrayList<String>();
			getContents(list);
			contents = list.toArray(new String[0]);
		}
		W = w;
		WINDOW = createScroolWindow(contents);
	}

	/**
	 * over-ride getContents
	 * 
	 * @param kh
	 * @param bv
	 */
	protected Plain(boolean fixed_height, KeyHolder kh, Base_View bv,
			String... contents) {
		this(fixed_height, kh, bv, 0, contents);
	}

	/**
	 * over-ride getContents
	 * 
	 * @param kh
	 * @param bv
	 */
	public Plain(KeyHolder kh, Base_View bv) {
		this(kh, bv, "");
	}

	public Plain(KeyHolder kh, Base_View bv, ArrayList<String> contents) {
		this(kh, bv, contents.toArray(new String[0]));
	}

	/**
	 * over-ride getContents
	 * 
	 * @param kh
	 * @param bv
	 */
	public Plain(KeyHolder kh, Base_View bv, Integer w) {
		this(kh, bv, w, "");
	}

	protected Plain(KeyHolder kh, Base_View bv, Integer w, String... contents) {
		this(false, kh, bv, w, contents);
	}

	protected Plain(KeyHolder kh, Base_View bv, String... contents) {
		this(false, kh, bv, contents);
	}

	@Override
	protected void action_else() {
	}

	@Override
	public boolean arrow(DIRECTION d) {
		return END;
	}

	protected StringOnlyWindow createScroolWindow(String... contents) {
		return new StringOnlyWindow(COL, W, true, contents);
	}

	protected void getContents(ArrayList<String> list) {
	}

	protected int getFirstIndex() {
		return 0;
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
	}

	@Override
	protected UnderMenuWindow initializeSubWindow() {
		return null;
	}

}
