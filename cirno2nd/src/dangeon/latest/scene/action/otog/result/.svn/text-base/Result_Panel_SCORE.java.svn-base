package dangeon.latest.scene.action.otog.result;

import java.awt.Color;
import java.awt.Graphics2D;

import dangeon.latest.scene.action.otog.main.panel.Panel_Main;

public class Result_Panel_SCORE extends Result_Panel_LIFE {

	public Result_Panel_SCORE(int x, int y, int w, int h, Panel_Main p) {
		super(x, y, w, h, p);
		long max = 0;
		for (int i = 0; i < PROCESS; i++) {
			if (i < P.VH.SCORE_PROCESS.size()) {
				max = P.VH.SCORE_PROCESS.get(i);
			} else {
				P.VH.SCORE_PROCESS.add(max);
			}
		}
	}

	@Override
	public void drawME(Graphics2D g) {
		g.setClip(0, 0, W, H);
		g.setColor(Color.GREEN);
		for (int i = 0; i < process_frame; i++) {
			g.drawLine(getX(i), getY(i - 1), getX(i + 1), getY(i));
		}
		if (finish) {
			int c = P.VH.LIFE_100 < 8000 ? 50 : 255;
			g.setColor(new Color(c, c, c, 255 * (FLASH - flash_frame) / FLASH));
			g.fillRect(0, 0, W, H);
		}
		g.setClip(null);
	}

	private int getY(int i) {
		long PROCESS = 999999999500l;
		if (i == -1)
			i = 0;
		if (i < P.VH.SCORE_PROCESS.size())
			return (int) (H * (PROCESS - P.VH.SCORE_PROCESS.get(i)) / PROCESS);
		else
			return H;
	}

}
