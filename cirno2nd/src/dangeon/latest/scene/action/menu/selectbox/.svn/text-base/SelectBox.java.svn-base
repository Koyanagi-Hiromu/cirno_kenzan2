package dangeon.latest.scene.action.menu.selectbox;

import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;

public abstract class SelectBox extends Base_Scene_Menu {

	public static boolean isSelecting() {
		return Scene_Action.getMe().KH.K_ACC.isScene(SelectBox.class);
	}

	public SelectBox() {
		super(Scene_Action.getMe().KH, new SelectBox_View(
				Scene_Action.getMe().CURRENT_VIEW));
		Scene_Action.getMe().setNextScene(this);
	}

	protected SelectBox(String... strings) {
		super(Scene_Action.getMe().KH, new SelectBox_View(
				Scene_Action.getMe().CURRENT_VIEW, strings));
		Scene_Action.getMe().setNextScene(this);
	}

	@Override
	protected void action_cancel() {
		super.action_cancel();
	}

	@Override
	protected final void action_else() {
	}

	@Override
	protected final void action_enter() {
		setNextScene(Scene_Action.getMe());
		super.action_enter();
	}
}
