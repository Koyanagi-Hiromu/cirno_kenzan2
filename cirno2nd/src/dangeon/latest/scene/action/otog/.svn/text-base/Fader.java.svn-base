package dangeon.latest.scene.action.otog;

import main.constant.FR;
import main.res.BGM;
import main.util.BlackOut;
import dangeon.controller.task.Task;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.otog.ready.select.select.Otog_Select;
import dangeon.latest.scene.action.otog.result.Scene_Otog_Result;

public class Fader {
	public Fader(final Base_Scene now, final Base_Scene next) {
		if (next instanceof Scene_Otog)
			if (((Scene_Otog) next).main_panel != null)
				((Scene_Otog) next).main_panel.black_oouting = true;
		new BlackOut(new Task() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				if (next instanceof Scene_Otog || next instanceof Otog_Select) {
					BGM.waitUntilFadeOut();
				}
				FR.changeSleep(FR.THREAD_SLEEP_FAST);
				now.KH.setKeyAccepter(next);
				taskWork();
			}
		});
	}

	public Fader(Scene_Otog p) {
		this(p, new Scene_Otog_Result(p));
	}

	protected void taskWork() {
	}
}
