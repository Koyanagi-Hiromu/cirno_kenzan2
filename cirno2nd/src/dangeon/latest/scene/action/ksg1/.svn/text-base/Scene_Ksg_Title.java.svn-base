package dangeon.latest.scene.action.ksg1;

import main.Listener.ACTION;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.Scene_Action;

public class Scene_Ksg_Title extends Base_Scene {

	public Scene_Ksg_Title(Base_Scene prev) {
		super(Scene_Action.getMe().KH, new Scene_Ksg_Title_View(
				Scene_Action.getMe().CURRENT_VIEW));
		setNextScene(new Ksg_First(KH, CURRENT_VIEW, prev));
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
