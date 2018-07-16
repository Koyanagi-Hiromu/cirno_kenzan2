package dangeon.latest.scene.event.story;

import main.Listener.ACTION;
import main.constant.FR;
import main.res.BGM;
import main.res.Image_LargeCharacter;
import main.util.BlackOut;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.map.field.Base_Map;

public abstract class Event_Scene extends Base_Scene {
	private final Base_Scene PREV;

	public final int index;

	public final Base_Map bm;

	protected final BGM bgm;

	protected int first_sleep;

	protected final int FIRST_SLEEP = 700;

	/**
	 * 生成するだけでシーン切り替え
	 * 
	 * @param i
	 * @param bmr
	 */
	public Event_Scene(int i, Base_Map bm) {
		this(i, bm, BGM.dorobo);
	}

	/**
	 * 生成するだけでシーン切り替え
	 * 
	 * @param i
	 * @param bmr
	 */
	public Event_Scene(int i, Base_Map bm, BGM bg) {
		super(Scene_Action.getMe().KH, new Event_Scene_View(i - 1));
		this.bgm = bg;
		index = i;
		PREV = Scene_Action.getMe();
		this.bm = bm;
		final Event_Scene THIS = this;
		new BlackOut(new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				first_sleep = FIRST_SLEEP;
			}
		}, new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				Scene_Action.getMe().setNextScene(THIS);
				if (bgm != null) {
					bgm.play();
				} else {
					BGM.waitUntilFadeOut_Thread();
				}
			}
		});
	}

	@Override
	public boolean action(ACTION a) {
		return false;
	}

	@Override
	public boolean arrow(DIRECTION d) {
		return false;
	}

	protected void end() {
		Message.setTask_AfterReleaseDemandToPushEnter(new Task() {
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				BGM.waitUntilFadeOut_Thread();
				new BlackOut(bm, new Task() {
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						setNextScene(getPreviousScene());
						MassCreater mc = new MassCreater(bm, false);
						mc.createMap();
					}
				});
			}
		});
	}

	@Override
	public Base_Scene getPreviousScene() {
		return PREV;
	}

	protected abstract void message();

	protected void rep(String... strings) {
		new Conversation(strings);
	}

	protected void say(String... strings) {
		new Conversation(Image_LargeCharacter.鬼人正邪, strings);
	}

	protected void set(String... strings) {
		Message.set(strings);
	}

	protected void setRep(Image_LargeCharacter imlc) {
		Conversation.previous_player = imlc;
	}

	protected void setTalks(Image_LargeCharacter imlc) {
		Conversation.previous_opponent = imlc;
	}

	protected void talks(String... strings) {
		new Conversation(Conversation.previous_opponent, strings);
	}

	@Override
	public void upDate() {
		if (first_sleep > 0) {
			first_sleep -= FR.THREAD_SLEEP;
			if (first_sleep <= 0) {
				message();
				end();
			}
		}
		super.upDate();
	}

}
