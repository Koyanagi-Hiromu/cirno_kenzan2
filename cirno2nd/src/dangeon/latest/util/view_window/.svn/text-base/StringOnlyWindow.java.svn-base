package dangeon.latest.util.view_window;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import main.Second_Firster;
import main.constant.FR;
import dangeon.latest.scene.action.message.Message;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class StringOnlyWindow extends Base_Window {

	public String[] CONTENTS;

	/**
	 * 
	 * @param w
	 *            0指定でワイドウィンドウ
	 */
	public StringOnlyWindow(Integer cols, Integer w, boolean cols_width,
			String... contents) {
		this(cols, 10, 10, w, NormalFont.NORMALFONT
				.deriveFont(NormalFont.SMALL_SIZE), contents);
	}

	public StringOnlyWindow(Integer x, Integer y, Font font, String... contents) {
		this(x, y, null, font, contents);
	}

	public StringOnlyWindow(Integer x, Integer y, int w, int cols, Font font) {
		super(x, y, font);
		WINDOW = createWindow(getWidth(w), getHeight(cols));
	}

	public StringOnlyWindow(Integer x, Integer y, int w, String... contents) {
		this(x, y, w, NormalFont.NORMALFONT, contents);
	}

	/**
	 * 
	 * @param w
	 *            0指定でワイドウィンドウ<br/>
	 *            nullで最小幅<br/>
	 */
	public StringOnlyWindow(Integer x, Integer y, Integer w, Font font,
			String... contents) {
		this(null, x, y, w, font, contents);
	}

	/**
	 * @param cols
	 *            nullで自動調整
	 * @param w
	 *            0指定でワイドウィンドウ<br/>
	 *            nullで自動調整<br/>
	 */
	public StringOnlyWindow(Integer cols, Integer x, Integer y, Integer w,
			Font font, String... contents) {
		super(x, y, font);
		if (w == null) {
			w = 0;
			FontMetrics fm = Second_Firster.ME.getFontMetrics(font);
			for (String string : contents) {
				w = Math.max(w,
						fm.stringWidth(StringFilter.getPlainString(string)));
			}
		} else if (w == 0) {
			w = FR.SCREEN_WIDTH - getWidth(0) - x * 2;
		}
		CONTENTS = contents;
		WINDOW = createWindow(getWidth(w),
				getHeight(cols == null ? contents.length : cols));
	}

	public StringOnlyWindow(Integer x, Integer y, String... contents) {
		this(x, y, NormalFont.NORMALFONT, contents);
	}

	public StringOnlyWindow(String... contents) {
		this(10, 10, NormalFont.NORMALFONT, contents);
	}

	protected void drawHorizon(Graphics2D g, int i) {
		drawLine(g, i - 1, true);
	}

	@Override
	protected void drawString(Graphics2D g, int X, int Y) {
		g.setFont(FONT);
		StringFilter.setColor(g, CONTENTS[0]);
		int w = getWidth_CenterArea();
		for (int i = 0; i < CONTENTS.length; i++) {
			if (CONTENTS[i].matches(Message.HORIZON))
				drawHorizon(g, i);
			else if (CONTENTS[i].startsWith(TAIL)) {
				drawTail(g, CONTENTS[i], w, i);
			} else {
				String s = CONTENTS[i];
				if (CONTENTS[i].contains(TAIL)) {
					drawTail(g, s.substring(s.indexOf(TAIL)), w, i);
					s = s.substring(0, s.indexOf(TAIL));
				}
				String arr[] = s.split(CENTERIZE);
				for (int j = 0; j < arr.length; j++) {
					drawString(g, arr[j], i + 1, w / arr.length * j, 0);
				}
			}
		}
	}

	private void drawTail(Graphics2D g, String string, int w, int i) {
		String s = string.replaceFirst(TAIL, "");
		drawString(
				g,
				s,
				i + 1,
				w
						- g.getFontMetrics().stringWidth(
								StringFilter.getPlainString(s)), 0);
	}

	@Override
	public void drawWindow(Graphics2D g) {
		super.drawWindow(g);
		drawString(g, getViewX(), getViewY());
	}

	public void setString(String... new_contents) {
		CONTENTS = new_contents;
	}
}
