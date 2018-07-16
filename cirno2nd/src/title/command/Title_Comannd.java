package title.command;

import java.util.ArrayList;

import main.Listener;
import main.res.BGM;
import main.util.BlackOut;
import title.Title;
import dangeon.controller.task.Task;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.Scene_Menu_First___;
import dangeon.latest.scene.action.menu.first.config.keys.KeyConfig;
import dangeon.latest.system.KeyHolder;

public class Title_Comannd extends Scene_Menu_First___ {
	public final Title PARENT;

	public Title_Comannd(KeyHolder kh, Base_View bv, Title title) {
		super(kh, new Title_Comannd_View(bv));
		PARENT = title;
	}

	@Override
	protected void action_else() {
	}

	@Override
	protected void arrow_x_less_than_zero() {
	};

	@Override
	protected void arrow_x_more_than_max() {
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {

		setContents("冒険を始める", "", new Book() {
			@Override
			protected void work() {
				PARENT.startGame();
			}
		});
		// final Base_Scene ME = this;
		// setContents("ダンシング・チルノ", "", new Book() {
		// @Override
		// protected void work() {
		// new Scene_Otog_Ready(ME);
		// }
		// });
		// setContents("刹那の仕分け", "", new Book() {
		// @Override
		// protected void work() {
		// new Scene_Ksg_Title(ME);
		// }
		// });
		setContents("キーコンフィグ", "", new Book() {
			@Override
			protected void work() {
				setNextScene(new KeyConfig(KH, PARENT.CURRENT_VIEW));
			}
		});
		setContents("ゲームを終える", "", new Book() {
			@Override
			protected void work() {
				Listener.setValid(false);
				new BlackOut("", new Task() {
					private static final long serialVersionUID = 1L;

					@Override
					public void work() {
						System.exit(0);
					}
				});
				BGM.waitUntilFadeOut_Thread();
			}
		});
	}

	@Override
	public void upDate() {
		PARENT.upDate();
	}
}