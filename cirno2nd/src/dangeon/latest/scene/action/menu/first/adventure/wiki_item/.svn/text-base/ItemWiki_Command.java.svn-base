package dangeon.latest.scene.action.menu.first.adventure.wiki_item;

import java.util.ArrayList;

import main.util.DIRECTION;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.model.config.Config;

public class ItemWiki_Command extends Base_Scene_Menu {

	public final Base_Scene PREVIOUS_SCENE;
	public final ItemWiki LIST;

	public ItemWiki_Command(ItemWiki list) {
		super(null, 1, list.KH, new ItemWiki_Command_View(list.CURRENT_VIEW));
		PREVIOUS_SCENE = list.BACK.PARENT_SCENE;
		LIST = list;
	}

	@Override
	protected void action_else() {

	}

	@Override
	public boolean arrow(DIRECTION d) {
		boolean b = super.arrow(d);
		setNextScene(new ItemWiki_Command(new ItemWiki(LIST.KH,
				LIST.CURRENT_VIEW.PREVIOUSE_VIEW, getY())));
		return b;
	}

	@Override
	public Base_Scene getPreviousScene() {
		return PREVIOUS_SCENE;
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		for (int i = 0; i < 8; i++) {
			setContents(Config.getItemDataKey(i), i);
		}
	}

	@Override
	protected UnderMenuWindow initializeSubWindow() {
		return super.initializeSubWindow();
	}

	@Override
	protected void initialX_Y() {
		y = LIST.INDEX;
	}

	private void setContents(String name, int i) {
		setContents(name, "", new Book() {
			@Override
			protected void work() {
				setNextScene(LIST);
			}
		});
	}

}