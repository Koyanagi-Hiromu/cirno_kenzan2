package dangeon.view.detail;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import main.Scene;
import main.constant.FR;
import main.res.BGM;
import main.res.Image_LargeCharacter;
import main.util.BeautifulView;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.message.Message;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Footer {

	private static final int BGM_INTERVAL = 3000;

	private static final String LOADING = "少女呼び出しちゅ";

	private static int y = Message.WINDOW_Y - NormalFont.HEIGHT / 2 - 4;

	private static String title, author, card;
	private static int title_move, bgm_time;

	private static boolean flag;

	public static void draw(Graphics2D g) {
		if (Scene_Action.getMe().isCurrentScene()) {
			if (BGM.isLoading()) {
				drawLoading(g);
			} else if (flag && bgm_time > 0) {
				drawBGM(g);
			}
		} else {
			if (flag && bgm_time > 0) {
				bgm_time -= FR.THREAD_SLEEP;
			}
		}
	}

	public static void draw9(Graphics2D g, int x, int y, float size, int frame,
			int hosei, float stroke_size) {
		Font f = g.getFont();
		g.setFont(f.deriveFont(size));
		String str = "９";
		int w = g.getFontMetrics().stringWidth(str);
		int h = g.getFontMetrics().getHeight();
		g.setStroke(new BasicStroke(stroke_size));
		g.drawOval(x + -w / 2, y + -w / 2, w, w);
		g.setStroke(new BasicStroke());
		double theta = frame;
		AffineTransform firts_at = g.getTransform();
		AffineTransform at = new AffineTransform(firts_at);
		at.translate(x, y);
		at.rotate(Math.PI * 2 * theta / 360);
		g.setTransform(at);
		g.drawString(str, -w / 2, h / 2 - hosei);
		g.setTransform(firts_at);
		g.setFont(f);
		BeautifulView.setAlphaOnImg(g, 1f);
	}

	private static void drawBGM(Graphics2D g) {
		Font f = g.getFont();
		g.setFont(f.deriveFont(NormalFont.SMALL_SIZE));
		g.setFont(g.getFont().deriveFont(Font.ITALIC));
		int w = g.getFontMetrics().stringWidth(title) + 3;
		int w2 = g.getFontMetrics().stringWidth(author) + 3;
		int h = NormalFont.SMALL_HEIGHT + NormalFont.SMALL_DECENT;
		int x = FR.SCREEN_WIDTH - title_move - 10;
		int x2 = x + g.getFontMetrics().stringWidth(title)
				- g.getFontMetrics().stringWidth(author);
		bgm_time -= FR.THREAD_SLEEP;
		float alpha = 1;
		if (bgm_time < BGM_INTERVAL / 2) {
			alpha = new Float((double) bgm_time / (BGM_INTERVAL / 2));
		}
		if (title_move < w) {
			title_move += w / 10;
		}
		if (title_move > w) {
			title_move = w;
		}
		BeautifulView.setAlphaOnImg(g, alpha * 2 / 5);
		Image_LargeCharacter.get(card).draw_diff(g, -title_move + w - 20);
		BeautifulView.setAlphaOnImg(g, alpha);
		drawString(g, title, x, y - h * 2, w, h);
		drawString(g, author, x2, y - h + 6, w2, h);
		BeautifulView.setAlphaOnImg(g, 1f);
		g.setFont(f);
	}

	private static void drawLoading(Graphics2D g) {
		flag = false;
		bgm_time += 1;
		int max = 20;
		float alpha = new Float((double) bgm_time / max);
		BeautifulView.setAlphaOnImg(g, alpha > 0.3f ? 0.3f : alpha);
		g.setColor(new Color(200, 255, 255));
		draw9(g, FR.SCREEN_WIDTH - 40, y - 50, 80, bgm_time * 10, 9, 8);
		Font f = g.getFont();
		g.setFont(f.deriveFont(NormalFont.SMALL_SIZE));
		int w = g.getFontMetrics().stringWidth(LOADING);
		int h = NormalFont.SMALL_HEIGHT + NormalFont.SMALL_DECENT;
		int x = FR.SCREEN_WIDTH - w - 10;
		drawString(g, LOADING, x, y - h * 2, w, h);
		g.setFont(f);
		BeautifulView.setAlphaOnImg(g, 1f);
	}

	private static void drawString(Graphics2D g, String str, int x, int y,
			int w, int h) {
		g.setColor(new Color(200, 200, 255, 100));
		g.fillRect(x, y + NormalFont.DECENT, w, h);
		Color c = new Color(255, 220, 100);
		g.setStroke(new BasicStroke(2));
		g.setPaint(new GradientPaint(0, y, c, 0, y + h, c.darker().darker()));
		g.drawRect(x, y + NormalFont.DECENT, w, h);
		int s = 2;
		g.setStroke(new BasicStroke());
		g.drawRect(x - s, y + NormalFont.DECENT - s, w + s * 2, h + s * 2);
		g.setColor(new Color(0, 0, 0, 100));
		g.drawRect(x + s, y + NormalFont.DECENT + 1, w - s - 1, h - s);
		g.setColor(new Color(255, 255, 255));
		StringFilter.drawString_plain(g, str, x + 1, y + h - 1);
	}

	public static void set(BGM bgm) {
		flag = true;
		String t = bgm.TITLE;
		String a = bgm.AUTHOR;
		if (Scene.DANGEON.isPresentScene() && t != null && a != null) {
			title = t;
			author = a;
			if (bgm.CLASS != null) {
				card = bgm.CLASS.getSimpleName();
				card = card.substring(0, card.lastIndexOf("のカード"));
			} else
				card = "";
			bgm_time = BGM_INTERVAL;
			title_move = 0;
		}
	}

	public static void unSet() {
		bgm_time = 0;
	}

}
