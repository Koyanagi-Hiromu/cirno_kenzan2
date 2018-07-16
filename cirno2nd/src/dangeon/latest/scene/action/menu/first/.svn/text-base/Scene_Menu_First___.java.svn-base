package dangeon.latest.scene.action.menu.first;

import main.res.SE;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.system.KeyHolder;

public abstract class Scene_Menu_First___ extends Base_Scene_Menu {

	public Scene_Menu_First___(Integer cols, KeyHolder kh,
			Base_Scene_Menu_View smv) {
		super(cols, kh, smv);
	}

	public Scene_Menu_First___(KeyHolder kh, Base_Scene_Menu_View smv) {
		super(kh, smv);
	}

	@Override
	protected void action_else() {
		SE.SYSTEM_MENU.play();
		((Scene_Menu_First) CURRENT_VIEW.PREVIOUSE_VIEW.PARENT_SCENE).ascX();
	}

	@Override
	protected void arrow_x_less_than_zero() {
		((Scene_Menu_First) CURRENT_VIEW.PREVIOUSE_VIEW.PARENT_SCENE).decX();
	};

	@Override
	protected void arrow_x_more_than_max() {
		((Scene_Menu_First) CURRENT_VIEW.PREVIOUSE_VIEW.PARENT_SCENE).ascX();
	}
}