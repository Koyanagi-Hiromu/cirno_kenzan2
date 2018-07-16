package event.ending;

import main.Listener.ACTION;
import main.res.BGM;
import main.res.SE;
import main.util.BlackOut;
import dangeon.controller.task.Task;
import dangeon.model.map.PresentField;
import dangeon.model.object.creature.player.Player;
import event.Base_Updater;
import event.Event_Scene;

public class Ending_Updater extends Base_Updater {
	private int frame = -150, sleep_1st = 100, sleep_2nd = 100;
	private final int PAGE_MAX = 5;

	private boolean flag_key_non_valid;

	private int page = 0;

	public float getFloat() {
		if (frame < 0) {
			int max = 150;
			float f = 1f - 1f * (max + frame) / max;
			if (f < 0) {
				f = 0;
			}
			return f;
		} else {
			int sleep = 100;
			if (frame < sleep) {
				return 1f;
			} else {
				sleep -= frame;
				int frame = -sleep;
				int max = 200;
				float f = 1f * (max - frame) / max;
				if (f < 0) {
					f = 0;
				}
				return f;
			}
		}
	}

	public int getFrame() {
		return frame;
	}

	public int getPage() {
		return page;
	}

	private void goNext() {
		frame = 0;
		SE.PAPER.play();
		flag_key_non_valid = true;
		new BlackOut(new Task() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				if (page < PAGE_MAX) {
					flag_key_non_valid = false;
				}
			}
		}, new Task() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				page++;
			}
		});
	}

	public boolean isEnd() {
		return page == PAGE_MAX;
	}

	@Override
	public void keyPressed(ACTION a) {
		if (flag_key_non_valid) {
			return;
		}
		if (a == ACTION.ENTER) {
			goNext();
		}
	}

	@Override
	public void upDate() {
		frame++;
		if (isEnd()) {
			if (sleep_1st > 0) {
				sleep_1st--;
				frame--;
			} else if (getFloat() == 0) {
				sleep_2nd--;
				if (sleep_2nd == 0) {
					Player.me.setMassPoint(PresentField.get()
							.getEntrancePoint());
					BGM.kanpyo_ch_plume.play();
					Event_Scene.STAFROLL.set();
				}
			}
		} else {
			if (frame > 150) {
				goNext();
			}
		}
	}

}
