package dangeon.model.object.artifact.device;

import java.awt.Point;

import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.message.Message;

public class OnlyOneMessage extends HiddenDevice {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final String MSG[];
	protected final Task TASK;

	public OnlyOneMessage(Point p, String... msg) {
		super(p);
		MSG = msg;
		TASK = null;
	}

	public OnlyOneMessage(Point p, Task task, String... msg) {
		super(p);
		MSG = msg;
		TASK = task;
	}

	@Override
	public boolean walkOnAction() {
		Message.setMessageTask(TASK, MSG);
		TaskOnMapObject.addItemRemoveTask(this);
		return false;
	}

}
