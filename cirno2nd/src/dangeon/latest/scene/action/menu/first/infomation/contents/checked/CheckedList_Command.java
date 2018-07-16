package dangeon.latest.scene.action.menu.first.infomation.contents.checked;

import java.util.ArrayList;

import main.util.DIRECTION;
import main.util.Show;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.model.map.PresentField;

public class CheckedList_Command extends Base_Scene_Menu {

	public final Base_Scene PREVIOUS_SCENE;
	public final CheckedList LIST;

	public CheckedList_Command(CheckedList list) {
		super(null, 1, list.KH, new CheckedList_Command_View(list.CURRENT_VIEW));
		PREVIOUS_SCENE = list.BACK.PARENT_SCENE;
		LIST = list;
	}

	@Override
	protected void action_else() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean arrow(DIRECTION d) {
		boolean b = super.arrow(d);
		setNextScene(new CheckedList_Command(new CheckedList(LIST.KH,
				LIST.CURRENT_VIEW.PREVIOUSE_VIEW, getY())));
		return b;
	}

	@Override
	public Base_Scene getPreviousScene() {
		return PREVIOUS_SCENE;
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		int level = PresentField.get().getRandomMap().UN_CHECKED_LEVEL;
		if (level > 0) {
			initSpellCard(level == 2);
			setContents("草", 1);
			setContents("リボン", 2);
			setContents("書", 3);
			setContents("杖", 4);
			setContents("魔法瓶", 5);
		} else {
			Show.showErrorMessageDialog("未識別レベルは０です");
			setContents("error", 1);
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

	private void initSpellCard(boolean b) {
		if (b)
			setContents("カード", 0);
		else
			setDeprecatedContents("カード", "", new Book() {
				@Override
				protected void work() {
					Message.set("このダンジョンではカードは識別済みです");
					setNextScene(Scene_Action.getMe());
				}
			});

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