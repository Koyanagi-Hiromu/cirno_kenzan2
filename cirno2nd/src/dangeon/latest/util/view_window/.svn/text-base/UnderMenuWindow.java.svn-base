package dangeon.latest.util.view_window;

import java.awt.Font;
import java.awt.Graphics2D;

import dangeon.latest.scene.action.message.Message;

public class UnderMenuWindow extends SetContentsWindow {
	protected String explain[] = new String[0];

	public UnderMenuWindow(int cols, Font font, WindowContent... contents) {
		super(null, Message.Y, Message.W, cols, font, contents);
	}

	public UnderMenuWindow(int cols, int y, Font font,
			WindowContent... contents) {
		super(null, y, Message.W, cols, font, contents);
	}

	public UnderMenuWindow(int cols, int y, WindowContent... contents) {
		super(null, y, Message.W, cols, contents);
	}

	public UnderMenuWindow(int cols, WindowContent... contents) {
		super(null, Message.Y, Message.W, cols, contents);
	}

	@Override
	protected void drawString(Graphics2D g, int X, int Y) {
		super.drawString(g, X, Y);
		drawLine(g, 1, false);
		for (String exp : explain) {
			drawString(g, exp, 3);
		}
	}

	public void setExplain(String explain) {
		this.explain = new String[] { explain };
	}

	public void setExplain(String[] explain) {
		this.explain = explain;
	}

}
