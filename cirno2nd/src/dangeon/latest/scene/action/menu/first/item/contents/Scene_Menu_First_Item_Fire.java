package dangeon.latest.scene.action.menu.first.item.contents;

import java.util.ArrayList;

import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.Scene_Menu_First___;
import dangeon.latest.system.KeyHolder;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.creature.player.EnchantArrow;

public class Scene_Menu_First_Item_Fire extends Scene_Menu_First___ {

	public Scene_Menu_First_Item_Fire(KeyHolder kh, Base_View bv) {
		super(kh, new Scene_Menu_First_Item_Fire_View(bv));
	}

	@Override
	protected void action_else() {
	}

	@Override
	protected void arrow_x_less_than_zero() {
	}

	@Override
	protected void arrow_x_more_than_max() {
	};

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		for (EnchantArrow a : EnchantArrow.values()) {
			setContents(a.SAMPLE);
		}
	}

	private void setContents(final Arrow a) {
		a.setArrowRest(-1);
		setContents(a.getColoredName(),
				new String[] { a.getExplainationInShortCutSelecting() },

				new Book() {
					@Override
					protected void work() {
						EnchantArrow.setArrow(a);
						setNextScene(Scene_Action.getMe());
					}
				}, a);
	}

}