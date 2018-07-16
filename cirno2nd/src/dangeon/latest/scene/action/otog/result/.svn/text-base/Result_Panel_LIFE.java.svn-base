package dangeon.latest.scene.action.otog.result;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import main.res.SE;
import dangeon.latest.scene.action.otog.main.panel.Base_Panel;
import dangeon.latest.scene.action.otog.main.panel.Panel_Main;
import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;
import dangeon.view.util.StringFilter;

public class Result_Panel_LIFE extends Base_Panel {

	public final boolean FULL_COMBO;

	protected int process_frame = 0;

	private int text_frame = 0;

	int frame = 0;

	protected int flash_frame = 0;

	protected final int PROCESS = 100;

	private final int TEXT_FRAME = 5;

	protected final int FLASH = 10;

	protected boolean finish;

	public Result_Panel_LIFE(int x, int y, int w, int h, Panel_Main p) {
		super(x, y, w, h, p);
		for (TEXT text : p.VH.hash_save_tex_count.keySet()) {
			if (text.isCombo())
				continue;
			if (p.VH.hash_save_tex_count.get(text) > 0) {
				FULL_COMBO = false;
				return;
			}
		}
		FULL_COMBO = true;
	}

	private void drawFailed(Graphics2D g) {
		String str = "FAILED...";
		int half_w = g.getFontMetrics().stringWidth(str) / 2;
		int half_h = g.getFontMetrics().getHeight() / 2;
		StringFilter.drawString(g, str, W / 2 - half_w + 10, H / 2 + half_h
				- (TEXT_FRAME - text_frame) * 5);
	}

	@Override
	protected void drawHLine(Graphics2D g, int rate_100) {
		super.drawHLine(g, H * rate_100 / 100);
	}

	@Override
	public void drawME(Graphics2D g) {
		g.setClip(0, 0, W, H);
		g.setColor(Color.WHITE);
		drawHLine(g, 50);
		int line = H * 50 / 100;
		for (int i = 0; i < process_frame; i++) {
			int y1 = getY(i);
			int y2 = getY(i + 1);
			int d1 = line - y1;
			int d2 = line - y2;
			if (d1 * d2 < 0) {
				int x1 = getX(i);
				int x2 = getX(i + 1);
				int x1_2 = x1 + 1;
				if (d1 >= 0)
					g.setColor(Color.RED);
				else
					g.setColor(Color.GREEN);
				g.drawLine(x1, y1, x1_2, line);
				if (d1 >= 0)
					g.setColor(Color.GREEN);
				else
					g.setColor(Color.RED);
				g.drawLine(x1_2, line, x2, y2);
			} else {
				if (d1 < 0 || d2 < 0)
					g.setColor(Color.GREEN);
				else
					g.setColor(Color.RED);
				g.drawLine(getX(i), y1, getX(i + 1), y2);
			}
		}
		if (process_frame == PROCESS)
			drawText(g);
		if (finish) {
			int c = P.VH.isFailed() ? 50 : 255;
			g.setColor(new Color(c, c, c, 255 * (FLASH - flash_frame) / FLASH));
			g.fillRect(0, 0, W, H);
		}
		g.setClip(null);
	}

	private void drawSuccess(Graphics2D g, String str) {
		int half_w = g.getFontMetrics().stringWidth(str) / 2;
		int half_h = g.getFontMetrics().getHeight() / 2;
		// int s = g.getFont().getSize();
		AffineTransform firts_at = g.getTransform();
		AffineTransform at = new AffineTransform(firts_at);
		at.translate(W / 2, H / 2);
		at.rotate(Math.PI * 2 * text_frame / TEXT_FRAME);
		g.setTransform(at);
		StringFilter.drawString(g, str, -half_w, half_h);
		g.setTransform(firts_at);
	}

	private void drawText(Graphics2D g) {
		if (P.VH.isFailed()) {
			g.setColor(Color.LIGHT_GRAY);
			drawFailed(g);
		} else {
			if (FULL_COMBO) {
				if (!finish)
					g.setColor(Color.WHITE);
				else
					g.setColor(P.VH.getDifficultyColors(6));
				drawSuccess(g, "FULL COMBO!!");
			} else {
				if (!finish || frame / 2 % 2 == 0)
					g.setColor(Color.YELLOW);
				else
					g.setColor(Color.WHITE);
				drawSuccess(g, "CLEARED !!");
			}
		}
	}

	@Override
	protected int getBGColorB() {
		return 0;
	}

	@Override
	protected int getBGColorG() {
		return 0;
	}

	@Override
	protected int getBGColorR() {
		return 0;
	}

	protected int getX(int i) {
		return W * i / 100;
	}

	private int getY(int i) {
		if (i <= -1)
			i = 0;
		long get;
		if (i < P.VH.LIFE_PROCESS.size())
			get = P.VH.LIFE_PROCESS.get(i);
		else
			get = P.VH.LIFE_100;
		return (H - 2) * (PROCESS - (int) (get / PROCESS)) / 100 + 1;
	}

	@Override
	public void upDate() {
		frame++;
		if (finish) {
			if (++flash_frame > FLASH) {
				flash_frame = FLASH;
			}
		} else if (++process_frame > PROCESS) {
			process_frame = PROCESS;
			if (++text_frame > TEXT_FRAME) {
				text_frame = TEXT_FRAME;
				finish = true;
				if (P.VH.isFailed())
					SE.SYSTEM_CURSE.play();
				else
					SE.ICE.play();
			}
		} else
			SE.SYSTEM_CANCEL.play();
	}

}
