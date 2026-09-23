package dangeon.latest.scene.action.strage;

import java.util.ArrayList;

import main.util.DIRECTION;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.model.object.creature.player.strage.CHEN_Strage;

/**
 * 橙の倉庫の「引き出す」カテゴリメニュー（アイテム図鑑のItemWiki_Commandと同じ構成）
 */
public class ChenStorage_Command extends Base_Scene_Menu {

	public final ChenStorage_List LIST;

	public ChenStorage_Command(ChenStorage_List list) {
		super(null, 1, list.KH, new ChenStorage_Command_View(
				list.CURRENT_VIEW));
		LIST = list;
	}

	@Override
	protected void action_else() {
	}

	@Override
	public boolean arrow(DIRECTION d) {
		boolean b = super.arrow(d);
		setNextScene(new ChenStorage_Command(new ChenStorage_List(getY(), 0,
				LIST.VIEW)));
		return b;
	}

	@Override
	public Base_Scene getPreviousScene() {
		return Scene_Action.getMe();
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		for (int i = 0; i < CHEN_Strage.CATEGORY_COUNT; i++) {
			setContents(CHEN_Strage.categoryLabel(i), "", new Book() {
				@Override
				protected void work() {
					setNextScene(LIST);
				}
			});
		}
	}

	@Override
	protected void initialX_Y() {
		y = LIST.INDEX;
	}
}
