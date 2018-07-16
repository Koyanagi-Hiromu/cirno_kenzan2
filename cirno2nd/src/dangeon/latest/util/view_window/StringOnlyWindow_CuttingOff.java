package dangeon.latest.util.view_window;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class StringOnlyWindow_CuttingOff extends StringOnlyWindow {

	public StringOnlyWindow_CuttingOff(Integer x, Integer y, Font font,
			String... contents) {
		super(x, y, font, contents);
	}

	public StringOnlyWindow_CuttingOff(Integer x, Integer y, int w, Font font,
			String... contents) {
		super(x, y, w, font, contents);
	}

	public StringOnlyWindow_CuttingOff(Integer x, Integer y, String... contents) {
		super(x, y, contents);
	}

	@Override
	public WindowFrame createDoubleSizeClearCopy() {
		final WindowFrame ME = this;
		WindowFrame wf = new StringOnlyWindow_CuttingOff(0, 0, FONT) {
			@Override
			public void drawWindow(Graphics2D g) {
				g.drawImage(WINDOW, ME.getViewX(), ME.getViewY() + INSETS.top,
						getWidth() / 2, getHeight() / 2, null);
			}

			@Override
			protected int getViewY() {
				return super.getViewY() - INSETS.top;
			}
		};
		wf.WINDOW = new BufferedImage(
				(getWidth() - INSETS.left - INSETS.right) * 2, (getHeight()
						- INSETS.top - INSETS.bottom) * 2, WINDOW.getType());
		return wf;
	}

	@Override
	protected void drawString(Graphics2D g, int X, int Y) {
		WindowFrame wf = createDoubleSizeClearCopy();
		for (int i = 0; i < CONTENTS.length; i++) {
			wf.drawString(wf.initGraphics2Scaled(), CONTENTS[i], i + 1, 3);
		}
		wf.drawWindow(g);
	}

	@Override
	public int getContentHeight() {
		return super.getContentHeight() - 5;
	}
}
