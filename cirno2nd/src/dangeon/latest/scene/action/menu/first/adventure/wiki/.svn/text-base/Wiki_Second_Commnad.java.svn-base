package dangeon.latest.scene.action.menu.first.adventure.wiki;

import java.util.ArrayList;

import main.res.SE;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class Wiki_Second_Commnad extends Base_Scene_Menu {

	public final int INDEX, MAX;
	public final Base_Enemy E;
	public final String GAME_TITLE;

	public Wiki_Second_Commnad(Wiki_SelectionCharacter list, int i, int max,
			Base_Enemy e, String game_title) {
		super(null, 1, list.KH, new Wiki_First_Commnad_View(list.CURRENT_VIEW));
		INDEX = i;
		MAX = max;
		E = e;
		GAME_TITLE = game_title;
	}

	@Override
	protected void action_else() {

	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		for (int i = 0; i < 4; i++) {
			final int I = i + 1;
			if (Config.isExist(E, I)) {
				final int IND = E.getDeConvertedLV(I);
				setContents(
						IND == 4 ? "ANOTHER"
								: "Lv:".concat(String.valueOf(IND)), "",
						new Book() {
							@Override
							protected void work() {
								CONDITION.conditionAllClear(E);
								E.chengeLv(IND);
								setNextScene(new Wiki_Enemy(KH, CURRENT_VIEW,
										E, INDEX, MAX, GAME_TITLE, I));
							}
						});
			} else {
				setDeprecatedContents("？？？", new Book() {

					@Override
					protected void work() {
						SE.SYSTEM_CURSE.play();
					}
				});
			}
		}
	}

	@Override
	protected UnderMenuWindow initializeSubWindow() {
		return super.initializeSubWindow();
	}

}