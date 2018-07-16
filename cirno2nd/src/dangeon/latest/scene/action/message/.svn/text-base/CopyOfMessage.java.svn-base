package dangeon.latest.scene.action.message;

import java.awt.Graphics2D;
import java.util.ArrayList;

import dangeon.latest.util.view_window.WindowFrame;

public class CopyOfMessage {
	public static final Integer X = null, Y = 255, W = 370, COLS = 3;
	public final static CopyOfMessage ME = new CopyOfMessage();
	private static final int HOLDING_INTERVAL = 60;

	public static void set(String... strings) {
		ME.set_non_statiic(strings);
	}

	private final ArrayList<String> task;
	private final WindowFrame WINDOW_FRAME;
	private int holding_time;

	private int diff;

	private CopyOfMessage() {
		task = new ArrayList<String>();
		WINDOW_FRAME = new WindowFrame(X, Y, W, COLS);
		reset();
	}

	public void draw(Graphics2D g) {
		if (!task.isEmpty()) {
			if (holding_time < HOLDING_INTERVAL) {
				WINDOW_FRAME.drawWindow(g);
				drawStringField(g);
			} else {
				reset();
			}
		}
	}

	private void draw4Strings(Graphics2D g, WindowFrame wf) {
		if (diff < wf.getContentHeight()) {
			for (int i = 0; i < COLS + 1; i++) {
				wf.drawString(g, task.get(i), i + 1, -diff);
			}
			diff += speed();
		} else {
			task.remove(0);
			diff -= wf.getContentHeight();
			drawString(g, wf);
		}
	}

	private void drawbelow4Strings(Graphics2D g, WindowFrame wf) {
		for (int i = 0; i < task.size(); i++) {
			wf.drawString(g, task.get(i), i + 1, 0);
		}
	}

	private void drawString(Graphics2D g, WindowFrame wf) {
		if (task.size() <= COLS) {
			drawbelow4Strings(g, wf);
			holding_time++;
		} else {
			draw4Strings(g, wf);
		}
	}

	private void drawStringField(Graphics2D g) {
		WindowFrame wf = WINDOW_FRAME.createDoubleSizeClearCopy();
		drawString(wf.initGraphics2Scaled(), wf);
		wf.drawWindow(g);
	}

	private void reset() {
		task.clear();
		holding_time = 0;
		diff = 0;
	}

	private void set_non_statiic(String[] strings) {
		StringBuilder sb = new StringBuilder();
		for (String s : strings) {
			sb.append(s);
		}
		task.add(sb.toString());
		holding_time = 0;
	}

	private int speed() {
		double device;
		switch (task.size() - COLS) {
		case 1:
		case 2:
			device = 5;
			break;
		case 3:
		case 4:
			device = 4;
			break;
		case 5:
		case 6:
			device = 3;
			break;
		case 7:
		case 8:
			device = 2;
			break;
		default:
			device = 1.5;
			break;
		}
		return (int) (WINDOW_FRAME.getContentHeight() / device);
	}

}
