package dangeon.latest.scene.action.otog.ready;

import main.Listener.ACTION;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.otog.ready.select.Otog_Select_First;

public class Scene_Otog_Ready extends Base_Scene {

	public Scene_Otog_Ready(Base_Scene prev) {
		super(Scene_Action.getMe().KH, new Scene_Otog_Ready_View(
				Scene_Action.getMe().CURRENT_VIEW));
		setNextScene(new Otog_Select_First(KH, CURRENT_VIEW, prev));
	}

	@Override
	public boolean action(ACTION a) {
		return false;
	}

	@Override
	public boolean arrow(DIRECTION d) {
		return false;
	}

}
