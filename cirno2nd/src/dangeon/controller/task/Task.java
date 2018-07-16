package dangeon.controller.task;

import java.io.Serializable;

import dangeon.controller.TaskOnMapObject;

public abstract class Task implements Serializable {
	public class TaskHasAppo {
		public final Task parent;
		public final int appo;

		public TaskHasAppo(int appo) {
			this.appo = appo;
			parent = Task.this;
		}
	}

	private static final long serialVersionUID = 1L;

	protected boolean flag_continue = false;

	public boolean isDemandToContinue() {
		return flag_continue;
	}

	public abstract void work();

	protected void work(int frame) {
	}

	public final void work_appointment() {
		TaskOnMapObject.setTaskTask(new TaskHasAppo(-1));
	}

	public final void work_appointment(int frame) {
		TaskOnMapObject.setTaskTask(new TaskHasAppo(frame));
	}

	// public Task setKey(int key) {
	// appo = key;
	// return this;
	// }

	public final void work_call(int appo) {
		if (appo > 0) {
			work(appo);
		} else if (appo == -1) {
			work();
		}
	}
}
