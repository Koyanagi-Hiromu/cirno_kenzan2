package dangeon.latest.scene.action.otog.result;

import main.Listener.ACTION;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.otog.Fader;
import dangeon.latest.scene.action.otog.Scene_Otog;

public class Scene_Otog_Result extends Base_Scene {
	public final Scene_Otog PARENT;
	public final Result_Panel_Main PANEL;

	public Scene_Otog_Result(Scene_Otog parent) {
		super(parent.KH, new Scene_Otog_Result_View());
		PARENT = parent;
		PANEL = new Result_Panel_Main(10, PARENT.main_panel);
	}

	@Override
	public boolean action(ACTION a) {
		if (a != null) {
			SE.SYSTEM_MAGIC.play();
			new Fader(this, PARENT.getNextSelectScene()) {
				@Override
				protected void taskWork() {
					if (!PARENT.main_panel.VH.demo)
						PARENT.main_panel.VH.CONTENT.saveProp();
					PARENT.pre_bgm.play();
				}
			};
		}
		return END;
	}

	@Override
	public boolean arrow(DIRECTION d) {
		if (d != null) {
			SE.SYSTEM_MAGIC.play();
			new Fader(this, PARENT) {
				@Override
				protected void taskWork() {
					if (!PARENT.main_panel.VH.demo)
						PARENT.main_panel.VH.CONTENT.saveProp();
					PARENT.init(PARENT.PRE_OTOG_Select,
							PARENT.main_panel.VH.demo);
				}
			};
		}
		return END;
	}

	@Override
	public void upDate() {
		PANEL.upDate();
	}

}
