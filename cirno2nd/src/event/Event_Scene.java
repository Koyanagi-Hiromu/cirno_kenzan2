package event;

import event.ending.Ending_Drawer;
import event.ending.Ending_Updater;
import event.stafroll.StafRoll_Drawer;
import event.stafroll.StafRoll_Updater;

public enum Event_Scene {
	ENDING(Ending_Updater.class, Ending_Drawer.class), STAFROLL(
			StafRoll_Updater.class, StafRoll_Drawer.class);
	private static Event_Scene present_scene;
	private final Class<? extends Base_Updater> clazz_up;
	private final Class<? extends Base_Drawer> clazz_dr;

	public static Base_Updater UPDATER;
	public static Base_Drawer DRWER;

	public static Event_Scene get() {
		return present_scene;
	}

	private Event_Scene(Class<? extends Base_Updater> u,
			Class<? extends Base_Drawer> d) {
		clazz_up = u;
		clazz_dr = d;
	}

	public boolean isPresentScene() {
		return this.equals(present_scene);
	}

	public void set() {
		present_scene = this;
		try {
			UPDATER = clazz_up.newInstance();
			DRWER = clazz_dr.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
