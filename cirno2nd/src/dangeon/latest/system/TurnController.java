package dangeon.latest.system;

import java.util.ArrayList;

public class TurnController {
	public static void stackTask(Base_Task bt) {
		me.STACKING_TASK_LIST.add(bt);
	}

	public final MainSystem PARENT;

	private static TurnController me;

	private final ArrayList<Base_Task> STACKING_TASK_LIST = new ArrayList<Base_Task>();

	TurnController(MainSystem mainSystem) {
		PARENT = mainSystem;
		me = this;
	}

	private void perform() {

	}

	void upDate() {
		if (STACKING_TASK_LIST.isEmpty()) {
			perform();
		} else {
			STACKING_TASK_LIST.get(0).work();
			if (STACKING_TASK_LIST.get(0).isEnd()) {
				STACKING_TASK_LIST.remove(0);
			}
		}
	}

}
