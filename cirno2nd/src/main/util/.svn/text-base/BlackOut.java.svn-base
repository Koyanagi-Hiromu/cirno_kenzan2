package main.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import main.constant.FR;
import main.res.SE;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.model.map.MapList;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.Base_Map;
import dangeon.view.constant.BGMFONT;
import dangeon.view.constant.NormalFont;

public class BlackOut {
	public final Task TASK, END_TASK;
	public final String TITLE;
	public String TITLE2 = null;
	public int time = 0;
	public final int LENGTH = 50, HALF = LENGTH / 2;
	private static final ArrayList<BlackOut> LIST = new ArrayList<BlackOut>();

	private static boolean no_se = false;

	private synchronized static void add(BlackOut b) {
		LIST.add(b);
	}

	public synchronized static boolean isEmpty() {
		return LIST.isEmpty();
	}

	public static void setNoMoveSE() {
		no_se = true;
	}

	public synchronized static void staticDraw(Graphics2D g) {
		for (Iterator<BlackOut> i = LIST.iterator(); i.hasNext();) {
			BlackOut type = i.next();
			if (type.draw(g)) {
				i.remove();
			}
		}
	}

	public BlackOut(final Base_Map bm) {
		this(bm, new Task() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void work() {
				new MassCreater(bm, null, true).createMap();
			}
		});
	}

	public BlackOut(Base_Map bm, Task task_at_mid) {
		this(bm.getTitle(), task_at_mid);
		if (bm.RANDOM_LENGTH > 0) {
			TITLE2 = String.valueOf(MapList.getFloor()).concat("Ｆ");
		}
	}

	public BlackOut(String title, String title2, Task task_at_mid) {
		playSE();
		TITLE = title;
		TITLE2 = title2;
		TASK = task_at_mid;
		END_TASK = null;
		add(this);
	}

	public BlackOut(String title, Task task_at_mid) {
		playSE();
		TITLE = title;
		TASK = task_at_mid;
		END_TASK = null;
		add(this);
	}

	public BlackOut(Task middle_task) {
		this("", middle_task);
	}

	public BlackOut(Task end_task, Task task_at_mid) {
		TITLE = TITLE2 = "";
		TASK = task_at_mid;
		END_TASK = end_task;
		add(this);
	}

	public boolean draw(Graphics2D g) {
		Float f = new Float(Math.sin(Math.PI * time / LENGTH));
		if (f >= 1f) {
			f = 1f;
		}
		BeautifulView.setAlphaOnImg(g, f);
		Font font = g.getFont();
		if (PresentField.get() != null && PresentField.isRandomField()
				&& TITLE2 != null)
			g.setFont(BGMFONT.FONT);
		else
			g.setFont(NormalFont.NORMALFONT);
		drawFloor(g);
		if (time == HALF) {
			if (TaskOnMapObject.isTaskTask()) {
				return false;
			}
			if (TASK != null) {
				TASK.work();
			}
		}
		g.setFont(font);
		if (time >= LENGTH) {
			if (END_TASK != null) {
				END_TASK.work();
			}
			return true;
		}
		time++;
		return false;
	}

	private void drawFloor(Graphics2D g) {
		g.setColor(Color.BLACK);
		int del = 30;
		g.fillRect(-del, -del, FR.SCREEN_WIDTH + del * 2, FR.SCREEN_HEIGHT
				+ del * 2);
		g.setColor(Color.WHITE);
		int w = g.getFontMetrics().stringWidth(TITLE);
		g.drawString(TITLE, (FR.SCREEN_WIDTH - w) / 2,
				FR.SCREEN_HEIGHT / 2 - 50);
		if (TITLE2 != null) {
			g.drawString(TITLE2, 200, FR.SCREEN_HEIGHT / 2);
		}
	}

	private void playSE() {
		if (no_se) {
			SE.SYSTEM_STAIR_STEP.play();
		}
		no_se = false;

	}
}
