package dangeon.controller.listener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

import main.Listener.ACTION;
import dangeon.controller.DangeonScene;
import dangeon.controller.MainSystem;

@Deprecated
public class KeyHolder {

	private static LinkedHashSet<ACTION> tasklist_action = new LinkedHashSet<ACTION>(
			ACTION.values().length);
	public static final HashMap<ACTION, Boolean> action_pushing_map = new HashMap<ACTION, Boolean>(
			ACTION.values().length);
	static {
		for (ACTION a : ACTION.values()) {
			action_pushing_map.put(a, false);
		}
	}

	public static String getListString() {
		return tasklist_action.toString();
	}

	public static synchronized boolean isContraining(ACTION action) {
		return tasklist_action.contains(action);
	}

	public static synchronized boolean isContraining(ACTION... actions) {
		for (ACTION a : actions) {
			if (!tasklist_action.contains(a)) {
				return false;
			}
		}
		return true;
	}

	public static synchronized void removeTaskKeyAction(ACTION action) {
		action_pushing_map.put(action, false);
		tasklist_action.remove(action);
	}

	public static synchronized void setTaskKeyAction(ACTION action) {
		if (action == null) {
			return;
		}
		// if (!DangeonScene.SELECT.isPresentScene()
		// && Message.isDemandToWaitPushingAnyKey()) {
		// if (action != null) {
		// Message.releaseLock();
		// }
		// } else {
		if (!DangeonScene.DANGEON.isPresentScene()) {
			if (action_pushing_map.get(action)) {
				return;
			} else {
				action_pushing_map.put(action, true);
			}
		}
		tasklist_action.add(action);
		// }
	}

	public static synchronized void workTaskKeyAcrion() {
		if (!MainSystem.isKeyAccept()) {
			return;
		}
		for (Iterator<ACTION> i = tasklist_action.iterator(); i.hasNext();) {
			if (KeyAccepter.playerAction(i.next())) {
				i.remove();
			}
		}
	}

}
