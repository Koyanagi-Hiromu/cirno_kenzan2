package dangeon.controller;

import dangeon.controller.listener.KeyAccepter;
import dangeon.controller.task.Task;

public enum DangeonScene {
	DANGEON, MENU, STAIRS, GOUSEI, ENCHANT, SELECT, SELECT_ITEM, RESULT;
	private static DangeonScene present_scene = DANGEON;

	public static DangeonScene getPresentScene() {
		return present_scene;
	}

	public static boolean isPresentScene(DangeonScene object) {
		return present_scene == object;
	}

	public static boolean isPresentSceneLikeMenu() {
		return MENU.isPresentScene() || GOUSEI.isPresentScene()
				|| ENCHANT.isPresentScene() || RESULT.isPresentScene();
	}

	public static void setScene(DangeonScene new_scene) {
		KeyAccepter.setMovingKeyFalse();
		present_scene = new_scene;
	}

	private Task task = null;

	private Object obj = null;

	public Object getObject() {
		return obj;
	}

	public Task getTask() {
		return task;
	}

	public boolean isPresentScene() {
		return present_scene == this;
	}

	public void setObject(Object o) {
		obj = o;
	}

	public void setPresentScene() {
		present_scene = this;
	}

	public void setTask(Task t) {
		task = t;
	}
}
