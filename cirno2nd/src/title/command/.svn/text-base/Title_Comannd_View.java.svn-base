package title.command;

import java.awt.Graphics2D;

import main.res.Image_Flags;
import main.res.Image_Flags_Icon;
import main.res.Image_Icon;
import main.res.Image_Window_Frame;
import main.util.半角全角コンバーター;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.StringOnlyWindow;
import dangeon.latest.util.view_window.WindowFrame;
import dangeon.model.config.Config;
import dangeon.model.config.StoryManager;
import dangeon.view.constant.NormalFont;

public class Title_Comannd_View extends Base_Scene_Menu_View {

	private StringOnlyWindow bottom_window;
	private WindowFrame right_window;

	public Title_Comannd_View(Base_View bv) {
		super(null, 194 + 45, Image_Window_Frame.LINE, bv);
	}

	private WindowFrame createClearWindow() {
		int x = X + 158;
		int w = 220;
		final int cols = 1;
		final int max = 335;

		WindowFrame window = new WindowFrame(x, null, w, cols,
				NormalFont.NORMALFONT.deriveFont(30f)) {
			@Override
			public void drawWindow(Graphics2D g) {
				int X = getViewX() + 9;
				int Y = getViewY() + INSETS.top + MARGINE + 3;
				int h = 30 + MARGINE;
				super.drawWindow(g);
				g.setFont(FONT.deriveFont(15f));
				int rows = 2;
				for (int j = 0; j < rows; j++) {
					for (int i = 0; i < cols; i++) {
						int x = X + 111 * j;
						int y = Y;
						g.drawImage(Image_Icon.stt_icon_mokou.getImage(), x, y
								+ h * i, null);
						x += 30;
						for (int k = 0; k < 2; k++) {
							for (int l = 0; l < 5; l++) {
								Image_Flags.drawImage(g, l, l + 5 * k, x + 15
										* l, y + 15 * k);
							}
						}
					}
				}
			}

			@Override
			protected int getBG() {
				return BG_NORMAL;
			}

			@Override
			protected int getViewY() {
				return super.getViewY();
			}
		};
		int y = Y;
		int bottom = y + window.getHeight();
		if (bottom > max)
			y -= bottom - max;
		window.setY(y);
		return window;
	}

	@Override
	protected MenuWindow createMenuWindow() {
		int w = 138;
		String[] arr = getContents();
		if (arr != null)
			bottom_window = new StringOnlyWindow(X, Y + 88, 104,
					NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE),
					arr) {
				@Override
				protected int getBG() {
					return BG_RED;
				}

				@Override
				public int getViewX() {
					return 278;
				}

				@Override
				protected int getViewY() {
					return 194 + 45;
				}
			};
		right_window = null;
		// createClearWindow();
		return new MenuWindow(this, w) {
			@Override
			protected int getBG() {
				return BG_NORMAL_UNCLEARLY;
			}
		};
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		super.draw(g, current);
		if (bottom_window != null)
			bottom_window.drawWindow(g);
		if (right_window != null)
			right_window.drawWindow(g);
		drawFlags(g);
	}

	private void drawFlags(Graphics2D g) {
		// TODO 自動生成されたメソッド・スタブ
		int m = 5;
		int dy = 30 + m;
		int number = 0;
		StoryManager sms[] = { StoryManager.大妖精救出clear, StoryManager.逆ヶ島clear,
				StoryManager.二撃必殺clear, StoryManager.緋想の地下clear,
				StoryManager.五色の神霊廟clear, StoryManager.ミラクルクエストclear,
				StoryManager.風穴旅行clear, StoryManager.運命のワルツclear };
		for (StoryManager storyManager : sms) {
			if (storyManager.hasFinished())
				number++;
		}
		int num = 0;
		int x = 200 - dy * number / 2;
		boolean golden = number == sms.length;
		for (int i = 0; i < sms.length; i++) {
			if (sms[i].hasFinished()) {
				int d = golden ? 2 : -1;
				// int d = sms[i].hasFateFinished(2) ? 2 : -1;
				Image_Flags_Icon.drawImage(g, i, d, x + dy * num, 208);
				num++;
			}
		}
	}

	private String[] getContents() {
		String[] arr = new String[2];
		int times = Config.getTimes(Config.getSaveIndex() - 1);
		if (times < 0)
			return null;
		arr[0] = WindowFrame.TAIL + 半角全角コンバーター.半角To全角数字(times) + "回 ";
		if (Config.doesShowHours()) {
			int sec = Config.getSeconds(Config.getSaveIndex() - 1);
			int min = sec / 60;
			sec -= min * 60;
			int hour = min / 60;
			min -= hour * 60;
			StringBuilder sb = new StringBuilder(WindowFrame.TAIL);
			if (hour > 0) {
				// if (hour < 100) {
				sb.append(半角全角コンバーター.半角To全角数字(hour));
				// } else {
				// if (hour < 1000) {
				// sb.append(" ");
				// }
				// sb.append(hour);
				// }
				sb.append("時間");
			}
			if (hour < 10) {
				if (min < 10) {
					sb.append("０");
				}
				sb.append(半角全角コンバーター.半角To全角数字(min));
				sb.append("分");
				if (hour < 1) {
					if (sec < 10) {
						sb.append("０");
					}
					sb.append(半角全角コンバーター.半角To全角数字(sec));
					sb.append("秒");
				}
			}
			sb.append(" ");
			arr[1] = sb.toString();
		} else {
			return new String[] { arr[0] };
		}
		return arr;
	}
}