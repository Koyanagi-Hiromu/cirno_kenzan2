package main;

import load.Loading;
import main.util.BlackOut;
import dangeon.controller.MainSystem;
import dangeon.controller.task.Task;
import event.Event_System;

public enum Scene {
	LOADING() {
		@Override
		protected Base_System getSYS() {
			return new Loading();
		}
	},
	DANGEON() {
		@Override
		protected Base_System getSYS() {
			return new MainSystem();
		}
	},
	EVENT() {
		@Override
		protected Base_System getSYS() {
			return new Event_System();
		}
	};
	private static Scene present_scene = LOADING;
	static {
		LOADING.init();
	}

	public static Scene getPresentScene() {
		return present_scene;
	}

	public static boolean isPresentScene(Scene object) {
		return present_scene == object;
	}

	public static void setScene(Scene new_scene) {
		new_scene.setScene();
	}

	public Base_System SYS;

	private void end() {
		SYS.end();
		SYS = null;
	}

	protected abstract Base_System getSYS();

	private void init() {
		SYS = getSYS();
	}

	public boolean isPresentScene() {
		return this == present_scene;
	}

	public void setScene() {
		final Scene ME = this;
		new BlackOut(new Task() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				ME.init();
				present_scene.end();
				present_scene = ME;
			}
		});
	}
}
