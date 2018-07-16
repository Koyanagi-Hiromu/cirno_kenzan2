package dangeon.latest.util.view_window;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import dangeon.latest.scene.action.menu.scrool.Scrool;
import dangeon.latest.scene.action.message.Message;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class ScroolWindow extends StringOnlyWindow {
	public Scrool_Bar SCROOL_BAR;

	public final String[] WHOLE_CONTENTS;
	public final int COLS;
	public final Scrool SCR;

	/**
	 * 
	 * @param scr
	 * @param font
	 * @param contents
	 *            最後に無駄な行を必ず入れること
	 */
	public ScroolWindow(Scrool scr, Font font, String... contents) {
		this(scr, Message.W * 14 / 18, font, contents);
	}

	/**
	 * 
	 * @param scr
	 * @param font
	 * @param contents
	 *            最後に無駄な行を必ず入れること
	 */
	private ScroolWindow(Scrool scr, int w, Font font, String... contents) {
		super(25, null, w, scr.COL, font);
		SCR = scr;
		WHOLE_CONTENTS = contents;
		COLS = scr.COL;
		SCROOL_BAR = new Scrool_Bar(getViewX() + getWidth() + MARGINE, null,
				COLS, FONT, contents.length, SCR);
	}

	/**
	 * 
	 * @param scr
	 * @param contents
	 *            最後に無駄な行を必ず入れること
	 */
	public ScroolWindow(Scrool scr, int w, String... contents) {
		this(scr, w, NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE),
				contents);
	}

	/**
	 * 
	 * @param scr
	 * @param contents
	 *            最後に無駄な行を必ず入れること
	 */
	public ScroolWindow(Scrool scr, String... contents) {
		this(scr, NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE),
				contents);
	}

	@Override
	protected void drawHorizon(Graphics2D g, int i) {
		drawLine(g, i - 1, true);
	}

	@Override
	protected void drawString(Graphics2D g, int X, int Y) {
		StringFilter.setColor(g, CONTENTS[0]);
		for (int i = 0; i < CONTENTS.length - 1; i++) {
			if (CONTENTS[i + 1].matches(Message.HORIZON))
				drawHorizon(g, i);
			else
				drawString(g, CONTENTS[i + 1], i + 1);
		}
	}

	@Override
	public void drawWindow(Graphics2D g) {
		super.drawWindow(g);
		SCROOL_BAR.drawWindow(g);
	}

	public void drawWindow(Graphics2D g, boolean current) {
		super.drawWindow(g);
		if (current)
			SCROOL_BAR.drawWindow(g);
	}

	public boolean isExist(int index) {
		return index + COLS < WHOLE_CONTENTS.length;
	}

	@Override
	public void setY(int index) {
		SCROOL_BAR.setY(index);
		if (index >= 0) {
			ArrayList<String> list = new ArrayList<String>(COLS + 1);
			for (int i = 0; i < COLS; i++) {
				int j = index + i;
				if (j < WHOLE_CONTENTS.length)
					list.add(WHOLE_CONTENTS[j]);
				else
					break;
			}
			list.add(0, index - 1 > 0 ? WHOLE_CONTENTS[index - 1] : "");
			setString(list.toArray(new String[0]));
		}
	}
}
