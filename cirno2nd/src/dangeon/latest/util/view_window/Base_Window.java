package dangeon.latest.util.view_window;

import java.awt.Font;
import java.awt.Graphics2D;

import dangeon.view.constant.NormalFont;

public abstract class Base_Window extends WindowFrame {

	public Base_Window(Integer x, Integer y) {
		this(x, y, NormalFont.NORMALFONT);
	}

	public Base_Window(Integer x, Integer y, Font font) {
		super(x, y, font);
	}

	protected abstract void drawString(Graphics2D g, int X, int Y);

	@Override
	public void drawWindow(Graphics2D g) {
		super.drawWindow(g);
		drawString(g, getViewX(), getViewY());
	}

}
