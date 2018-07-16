package dangeon.latest.scene.action.menu.first.adventure.wiki;

import java.util.ArrayList;

import main.util.DIRECTION;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.util.STAGE;

public class Wiki_First_Commnad extends Base_Scene_Menu {

	public final Base_Scene PREVIOUS_SCENE;
	public final Wiki_SelectionCharacter LIST;

	public Wiki_First_Commnad(Wiki_SelectionCharacter list) {
		super(null, 1, list.KH, new Wiki_First_Commnad_View(list.CURRENT_VIEW));
		PREVIOUS_SCENE = list.BACK.PARENT_SCENE;
		LIST = list;
	}

	@Override
	protected void action_else() {

	}

	@Override
	public boolean arrow(DIRECTION d) {
		boolean b = super.arrow(d);
		setNextScene(new Wiki_First_Commnad(new Wiki_SelectionCharacter(
				LIST.KH, LIST.CURRENT_VIEW.PREVIOUSE_VIEW, getY(), LIST.LIST)));
		return b;
	}

	@Override
	public Base_Scene getPreviousScene() {
		return PREVIOUS_SCENE;
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		for (STAGE stage : STAGE.values()) {
			if (stage.ONE_NAME == null)
				continue;
			setContents(stage.name(), "", new Book() {
				@Override
				protected void work() {
					setNextScene(LIST);
				}
			});
		}
		setContents("その他", "", new Book() {
			@Override
			protected void work() {
				setNextScene(LIST);
			}
		});
	}

	@Override
	protected UnderMenuWindow initializeSubWindow() {
		return super.initializeSubWindow();
	}

	@Override
	protected void initialX_Y() {
		y = LIST.INDEX;
	}

}