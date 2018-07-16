package dangeon.latest.system;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

import main.Listener.ACTION;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.message.Message;
import dangeon.view.util.ValueFollower;

public class KeyHolder {

	/**
	 * for checking whether contraing the key.
	 */
	private final HashMap<ACTION, Boolean> action_pushing_map,
			request_release_action;
	public final SceneHolder_KeyAccepter K_ACC;
	private final HashMap<DIRECTION, Boolean> request_release = new HashMap<DIRECTION, Boolean>(
			4) {
		private static final long serialVersionUID = 1L;

		@Override
		public Boolean put(DIRECTION key, Boolean value) {
			if (key.isBias()) {
				super.put(key.getDeNeiboringDirection(), value);
				return super.put(key.getNeiboringDirection(), value);
			}
			return super.put(key, value);
		};
	};

	private final LinkedHashSet<ACTION> tasklist_action;

	private final LinkedHashSet<DIRECTION> tasklist_direction;

	ArrayList<ACTION> list__a = new ArrayList<ACTION>();

	ArrayList<DIRECTION> list__d = new ArrayList<DIRECTION>(8);

	private KeyEvent ev;

	public KeyHolder(SceneHolder_KeyAccepter k_acc) {
		tasklist_action = new LinkedHashSet<ACTION>(ACTION.values().length);
		tasklist_direction = new LinkedHashSet<DIRECTION>(
				DIRECTION.values().length);
		action_pushing_map = new HashMap<ACTION, Boolean>(
				ACTION.values().length);
		request_release_action = new HashMap<ACTION, Boolean>(
				ACTION.values().length);
		for (ACTION a : ACTION.values()) {
			action_pushing_map.put(a, false);
			request_release_action.put(a, false);
		}
		K_ACC = k_acc;
	}

	public synchronized void clearTaskKeyActions() {
		for (ACTION action : ACTION.values()) {
			removeTaskKeyAction(action);
		}
	}

	public synchronized DIRECTION[] getDirections() {
		return tasklist_direction.toArray(new DIRECTION[0]);
	}

	public synchronized String getListString() {
		return tasklist_action.toString();
	}

	private synchronized boolean isAnyReleaseRequest() {
		for (Iterator<Boolean> iterator = request_release.values().iterator(); iterator
				.hasNext();)
			if (iterator.next())
				return true;
		return false;
	}

	private synchronized boolean isAnyReleaseRequestAction() {
		for (Iterator<Boolean> iterator = request_release_action.values()
				.iterator(); iterator.hasNext();)
			if (iterator.next())
				return true;
		return false;
	}

	public synchronized boolean isContraining(ACTION a) {
		return action_pushing_map.get(a);
	}

	public synchronized boolean isContraining(ACTION... actions) {
		for (ACTION a : actions) {
			if (!action_pushing_map.get(a)) {
				return false;
			}
		}
		return true;
	}

	public synchronized boolean isDirecting() {
		return !tasklist_direction.isEmpty();
	}

	public boolean isThisCurrentScene(Base_Scene base_scene) {
		return K_ACC.isCurrentScene(base_scene);
	}

	public synchronized void removeTaskKeyAction(ACTION action) {
		tasklist_action.remove(action);
		action_pushing_map.put(action, false);
		request_release_action.put(action, false);
	}

	public synchronized void removeTaskKeyDirection(DIRECTION d) {
		if (d == null)
			return;
		tasklist_direction.remove(d);
		request_release.put(d, false);
	}

	public synchronized void set(KeyEvent e) {
		ev = e;
	}

	public synchronized void setKeyAccepter(Base_Scene base_scene) {
		K_ACC.setKeyAccepter(base_scene);
	}

	public synchronized void setRequestReleasePushingKey() {
		if (isDirecting()) {
			for (Iterator<DIRECTION> i = tasklist_direction.iterator(); i
					.hasNext();) {
				setRequestReleasePushingKey(i.next());
			}
		}
	}

	public synchronized void setRequestReleasePushingKey(ACTION a) {
		request_release_action.put(a, true);
	}

	public synchronized void setRequestReleasePushingKey(DIRECTION d) {
		request_release.put(d, true);
	}

	public synchronized void setTaskKeyAction(ACTION action) {
		if (action_pushing_map.get(action)) {
			// おしっぱ無効時に追加入力を受けた　→　無視
		} else {
			tasklist_action.add(action);
			action_pushing_map.put(action, true);
		}
	}

	public synchronized void setTaskKeyArrow(DIRECTION d) {
		tasklist_direction.add(d);
	}

	private synchronized void test() {
		if (!tasklist_direction.isEmpty() || !tasklist_action.isEmpty()) {
			if (tasklist_action.isEmpty()) {
				ValueFollower.setChase(tasklist_direction);
			} else if (tasklist_direction.isEmpty()) {
				ValueFollower.setChase(tasklist_action);
			} else {
				ValueFollower.setChase(tasklist_direction.toString() + ","
						+ tasklist_action);
			}
		} else {
			ValueFollower.setChase("-empty-");
		}
	}

	public synchronized void upDate() {
		if (ev != null) {
			K_ACC.set(ev);
			ev = null;
		}
		// test();
		upDate_direction();
		upDate_action();
	}

	private synchronized void upDate_action() {
		if (isAnyReleaseRequestAction()) {
			label: for (Iterator<ACTION> iterator = request_release_action
					.keySet().iterator(); iterator.hasNext();) {
				ACTION a = iterator.next();
				if (request_release_action.get(a)) {
					for (ACTION pushing_a : tasklist_action) {
						if (a == pushing_a)
							break label;
					}
					request_release_action.put(a, false);
				}
			}
		}
		if (Message.isDemandToWaitPushingAnyKey() && !tasklist_action.isEmpty()) {
			Message.releaseLock(tasklist_action);
			for (ACTION a : tasklist_action)
				list__a.add(a);
		} else {
			int j = 0;
			ACTION[] as = new ACTION[tasklist_action.size()];
			for (Iterator<ACTION> i = tasklist_action.iterator(); i.hasNext();) {
				as[j++] = i.next();
			}
			for (ACTION a : as) {
				if (!request_release_action.get(a)) {
					if (K_ACC.action(a))
						list__a.add(a);
				}
			}
		}
		for (ACTION a : list__a) {
			tasklist_action.remove(a);
		}
		list__a.clear();
	}

	private synchronized void upDate_direction() {
		if (isAnyReleaseRequest()) {
			label: for (Iterator<DIRECTION> iterator = request_release.keySet()
					.iterator(); iterator.hasNext();) {
				DIRECTION d = iterator.next();
				if (request_release.get(d)) {
					for (DIRECTION pushing_d : tasklist_direction) {
						if (d == pushing_d)
							break label;
					}
					request_release.put(d, false);
				}
			}
			if (isAnyReleaseRequest())
				return;
		}
		for (DIRECTION d : tasklist_direction) {
			if (K_ACC.arrow(d))
				list__d.add(d);
		}
		for (DIRECTION d : list__d) {
			tasklist_direction.remove(d);
		}
		list__d.clear();
	}

}
