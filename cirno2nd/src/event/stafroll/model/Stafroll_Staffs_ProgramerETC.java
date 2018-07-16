package event.stafroll.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.util.BeautifulView;

public class Stafroll_Staffs_ProgramerETC {
	private int frame = 0;
	private int delt = 0;
	private int speed = 5;
	private final String[] Arr;
	private final boolean flag_long;
	private int sleep = 0;

	private boolean flag_end = false;

	public Stafroll_Staffs_ProgramerETC(boolean b, String... arr) {
		Arr = arr;
		flag_long = b;
	}

	public Stafroll_Staffs_ProgramerETC(int sleep, String... arr) {
		this(true, arr);
		this.sleep = sleep;
	}

	public Stafroll_Staffs_ProgramerETC(String... arr) {
		this(false, arr);
	}

	public boolean draw(Graphics2D g) {
		int x = 30;
		int y = 200;
		if (sleep > 0) {
			drawIMLC(g, x, y, delt);
			sleep--;
			return false;
		}
		int move_frame = 5;
		Font font = g.getFont();
		g.setFont(font.deriveFont(20f));
		int intend = 20;
		delt++;
		if (delt > move_frame) {
			delt = move_frame;
			frame++;
		}
		if (frame == 0) {
			BeautifulView.setAlphaOnImg(g, 1f - 1f * (move_frame - delt)
					/ move_frame);
			drawIMLC(g, x, y, delt);
		} else {
			String[] arr = Arr;
			int h = 20;
			int wait_frame = move_frame * 6 * 2;
			if (flag_long)
				wait_frame *= 2;
			int result_wait_frame = move_frame * arr.length + wait_frame;
			if (frame > result_wait_frame + move_frame * arr.length) {
				int fr = frame - (result_wait_frame + move_frame * arr.length);
				BeautifulView.setAlphaOnImg(g, 1f * (move_frame - fr)
						/ move_frame);
				if (fr >= move_frame) {
					flag_end = true;
				}
				drawIMLC(g, x, y, delt);
			} else {
				BeautifulView.setAlphaOnImg(g, 1f);
				drawIMLC(g, x, y, delt);
			}
			if (frame > result_wait_frame) {
				int fr = frame - result_wait_frame;
				for (int i = 0; i < arr.length; i++) {
					String str = arr[i];
					int _x;
					if (fr <= move_frame * i) {
						_x = x;
						BeautifulView.setAlphaOnImg(g, 1f);
					} else if (fr > move_frame * (i + 1)) {
						continue;
					} else {
						_x = x + (move_frame * (i) - fr) * speed;
						BeautifulView.setAlphaOnImg(g, 1f
								* (move_frame * (i + 1) - fr) / move_frame);
					}
					g.drawString(str, (i == 0) ? _x : _x + intend, y + h * i);
				}
			} else {
				for (int i = 0; i < arr.length; i++) {
					String str = arr[i];
					int _x;
					if (frame <= move_frame * i) {
						continue;
					} else if (frame > move_frame * (i + 1)) {
						BeautifulView.setAlphaOnImg(g, 1f);
						_x = x;
					} else {
						_x = x + (move_frame * (i + 1) - frame) * speed;
						BeautifulView.setAlphaOnImg(g, 1f - 1f
								* (move_frame * (i + 1) - frame) / move_frame);
					}
					g.drawString(str, (i == 0) ? _x : _x + intend, y + h * i);
				}
			}
		}
		g.setFont(font);
		return flag_end;
	}

	private void drawIMLC(Graphics2D g, int x, int y, int delt) {
		BeautifulView.setAlphaOnImg(g, 1f);
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRoundRect(25, y - 30, 320, 150, 10, 10);
		g.setColor(Color.WHITE);
	}
}
