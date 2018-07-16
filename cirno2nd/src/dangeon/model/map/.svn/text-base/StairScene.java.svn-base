package dangeon.model.map;

import java.util.ArrayList;

import main.res.SE;
import dangeon.controller.DangeonScene;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;

public class StairScene extends Task {
	private enum Step {
		IN, HALF, OUT, END;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final MassCreater MC;
	private Step s = Step.IN;
	public final int FRAME = 50;
	private int frame = FRAME;

	private static ArrayList<Task> tasks = new ArrayList<Task>();

	public static void addTask(Task tsk) {
		tasks.add(tsk);
	}

	public static boolean isTasksEmpty() {
		return tasks.isEmpty();
	}

	public StairScene(MassCreater mc) {
		MC = mc;
		SE.SYSTEM_STAIR_STEP.play();
		DangeonScene.STAIRS.setTask(this);
		DangeonScene.STAIRS.setPresentScene();
	}

	public double getFrame() {
		return frame;
	}

	@Override
	public void work() {
		if (s == Step.IN) {
			frame--;
			if (frame == FRAME * 2 / 3) {
				if (TaskOnMapObject.save_load != null) {
					TaskOnMapObject.save_load.saveContinue();
					TaskOnMapObject.save_load = null;
				}
				if (PresentField.get().isHaraheru()) {
					MapList.nextFloor();
				}
			}
			if (frame < FRAME / 2) {
				s = Step.HALF;
			}
		} else if (s == Step.HALF) {
			MC.setNewMap();
			for (Task task : tasks) {
				task.work();
			}
			tasks.clear();
			s = Step.OUT;
		} else if (s == Step.OUT) {
			frame--;
			if (frame < 0) {
				frame = 0;
				s = Step.END;
			}
		} else if (s == Step.END) {
			DangeonScene.setScene(DangeonScene.DANGEON);
			DangeonScene.STAIRS.setTask(null);
		}
	}
}
