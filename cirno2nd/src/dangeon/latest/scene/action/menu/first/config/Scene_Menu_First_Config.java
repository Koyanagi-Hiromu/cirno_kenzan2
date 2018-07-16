package dangeon.latest.scene.action.menu.first.config;

import java.awt.Color;
import java.util.ArrayList;

import main.Second_Firster;
import main.thread.MainThread;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.Scene_Menu_First___;
import dangeon.latest.scene.action.menu.first.config.keys.KeyConfig;
import dangeon.latest.system.KeyHolder;
import dangeon.model.config.Config;
import dangeon.view.util.StringFilter;

public class Scene_Menu_First_Config extends Scene_Menu_First___ {

	private abstract class MyBook extends Book {

		protected abstract void content_work();

		@Override
		protected final void work() {
			content_work();
			setNextScene(new Scene_Menu_First_Config(KH,
					CURRENT_VIEW.PREVIOUSE_VIEW, getY()));
		}

	}

	private final int INIT_Y;

	public Scene_Menu_First_Config(KeyHolder kh, Base_View bv) {
		this(kh, bv, 0);
	}

	private Scene_Menu_First_Config(KeyHolder kh, Base_View bv, int ini_y) {
		super(kh, new Scene_Menu_First_Config_View(bv));
		INIT_Y = ini_y;
	}

	private String getColor(boolean on) {
		return on ? StringFilter.NUMBERS.toString() : Color.GRAY.toString();
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		setContents("キー＆パッド設定", "アクションにキーを割り当てます", new Book() {
			@Override
			protected void work() {
				setNextScene(new KeyConfig(KH,
						Scene_Action.getMe().CURRENT_VIEW));
			}
		});
		setDeprecatedContentsHorizon();
		boolean b;
		b = Config.isHighSpeedMessage();
		setContents("メッセージ　" + getColor(!b) + "並　" + getColor(b) + "早",
				"メッセージ送り速度を変更します", new MyBook() {
					@Override
					protected void content_work() {
						Config.changeHighSpeedMessage();
					}
				});
		b = !Config.isFontPlain();
		setContents("フォント　　" + getColor(b) + "太字　" + getColor(!b) + "細字",
				"お好みで選択して下さい", new MyBook() {
					@Override
					protected void content_work() {
						Config.switchFontStyle();
					}
				});
		b = Config.isSmooth();
		setContents("残像表示　　" + getColor(!b) + "ON　" + getColor(b) + "OFF",
				"OFFにすると動作が軽くなります", new MyBook() {
					@Override
					protected void content_work() {
						Config.switchSmooth();
					}
				});
		final int j = Config.getSkipRate();
		setContents("描画速度　　" + getColor(j == 1) + "麗　" + getColor(j == 2)
				+ "並　" + getColor(j == 3) + "粗", "下げるとカクつく代わりに軽くなります",
				new MyBook() {
					@Override
					protected void content_work() {
						int i = (j % 3) + 1;
						Config.setSkipRate(i);
					}
				});
		int _i_;
		if (Second_Firster.ME.isFullScreen()) {
			_i_ = 2;
		} else if (Second_Firster.ME.isDoubledScreen()) {
			_i_ = 1;
		} else {
			_i_ = 0;
		}
		if (MainThread.isTask_size()) {
			_i_ = (_i_ + 1) % 3;
		}
		final int i = _i_;
		setContents("画面サイズ　" + getColor(i == 0) + "等　" + getColor(i == 1)
				+ "倍　" + getColor(i == 2) + "全　", "等倍にすると動作がすごく軽くなります",
				new MyBook() {
					@Override
					protected void content_work() {
						if (i == 0) {
							MainThread.setSwitchSizeTask();
						} else if (i == 1) {
							MainThread.setSwitchFullScreenTask();
						} else {
							MainThread.setSwitchSizeTask();
						}
					}
				});
	}

	@Override
	protected void initialX_Y() {
		super.initialX_Y();
		y = INIT_Y;
	}

}