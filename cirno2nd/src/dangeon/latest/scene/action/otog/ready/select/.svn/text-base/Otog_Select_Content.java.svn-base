package dangeon.latest.scene.action.otog.ready.select;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import main.constant.FR;
import main.thread.MainThread;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.otog.ready.select.select.Otog_Select_Content_Selection;
import dangeon.view.util.StringFilter;

public class Otog_Select_Content extends Otog_Select_Content_Selection {

	public final String TITLE;
	public final Task TASK;

	public Otog_Select_Content(String title, Task task) {
		super();
		TITLE = title;
		TASK = task;
	}

	@Override
	protected boolean drawLevel(Graphics2D g, int x, int y, int w, int h) {
		return false;
	}

	@Override
	protected boolean drawMedal(Graphics2D g, int x2, int y, int size) {
		return false;
	}

	@Override
	protected void drawStatus(Graphics2D g) {
	}

	@Override
	protected void drawTitle(Graphics2D g, int x, int y, int w, int h) {
		String str = TITLE;
		String plan = StringFilter.getPlainString(str);
		if (frame != -1) {
			double d = MainThread.getFrame() / 16.0;
			g.setPaint(new GradientPaint((float) (w * Math.cos(d)) + x + w / 2,
					(float) (h * 2 * Math.sin(d)) + y, Color.WHITE, x + w / 2,
					y, Color.ORANGE, true));
		} else
			g.setColor(Color.BLUE);
		g.drawRect(x, y - h / 2, w, h);
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect(x, y - h / 2, w, h);
		g.setClip(x, y - h / 2, w, h);
		g.setColor(Color.WHITE);
		if (frame != -1) {
			int str_w = g.getFontMetrics().stringWidth(plan) + 10;
			if (str_w > w) {
				str_w += 10;
				frame += 1;
				if (frame > str_w)
					frame -= str_w;
				StringFilter.drawString(g, str, x + 2 - frame + str_w, y
						+ g.getFontMetrics().getHeight() / 2 - 1);
			}
		}
		StringFilter.drawString(g, str, x + 2 - frame, y
				+ g.getFontMetrics().getHeight() / 2 - 1);
		g.setClip(null);
	}

	public void enter() {
		TASK.work();
	}

	@Override
	protected int getW() {
		return FR.SCREEN_WIDTH / 2;
	}

	@Override
	public void init() {
		dx += 100;
	}
}
