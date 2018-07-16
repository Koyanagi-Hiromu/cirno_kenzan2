package dangeon.latest.scene.action.menu.first.music;

import java.util.ArrayList;

import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.first.Scene_Menu_First___;
import dangeon.latest.system.KeyHolder;

public class Music_Selection extends Scene_Menu_First___ {
	public final MenuContent[] CONTS;

	public Music_Selection(KeyHolder kh, Base_View bv, int x, int y,
			MenuContent... contents) {
		super(kh, new Music_Selection_View(bv, x, y));
		CONTS = contents;
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
		for (MenuContent menuContent : CONTS) {
			list.add(menuContent);
		}
	}

}