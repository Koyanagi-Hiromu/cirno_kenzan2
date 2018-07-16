package dangeon.latest.scene.event.staffroll;

import main.Listener.ACTION;
import main.res.BGM;
import main.util.BlackOut;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.model.map.field.Base_Map;

public class StaffRoll extends Base_Scene {
	private Task task;

	protected int first_sleep;
	protected final int FIRST_SLEEP = 700;

	/**
	 * 生成するだけでシーン切り替え
	 * 
	 * @param i
	 * @param bmr
	 */
	public StaffRoll(Base_Map bm, Task task) {
		super(Scene_Action.getMe().KH, new StaffRoll_View());
		BGM.kanpyo_ch_plume.play();
		this.task = task;
		final StaffRoll THIS = this;
		new BlackOut(new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				first_sleep = FIRST_SLEEP;
			}
		}, new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Scene_Action.getMe().setNextScene(THIS);
			}
		});
	}

	@Override
	public boolean action(ACTION a) {
		return false;
	}

	@Override
	public boolean arrow(DIRECTION d) {
		return false;
	}

	void end() {
		BGM.waitUntilFadeOut_Thread();
		new BlackOut(new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				task.work();
			}
		});
	}

	@Override
	public void upDate() {
		super.upDate();
	}

}
