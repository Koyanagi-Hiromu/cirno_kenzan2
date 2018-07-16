package dangeon.latest.util.view_window;

import java.awt.Font;
import java.awt.Graphics2D;

import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class SetContentsWindow extends Base_Window {

	public final WindowContent[] CONTENTS;

	public SetContentsWindow(Integer x, Integer y, int w, int cols, Font font,
			WindowContent... contents) {
		super(x, y, font);
		CONTENTS = contents;
		WINDOW = createWindow(getWidth(w), getHeight(cols));
	}

	public SetContentsWindow(Integer x, Integer y, int w, int cols,
			WindowContent... contents) {
		this(x, y, w, cols, NormalFont.NORMALFONT, contents);
	}

	@Override
	protected void drawString(Graphics2D g, int X, int Y) {
		g.setFont(FONT);
		for (WindowContent content : CONTENTS) {
			StringFilter.drawString(g, content.NAME, X + INSETS.left + MARGINE
					+ content.X, Y + INSETS.top + getContentHeight()
					* (content.COL + 1));
		}
	}

}
