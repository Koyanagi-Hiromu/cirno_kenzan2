package dangeon.latest.scene.action.otog.result;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.NumberFormat;

import main.constant.FR;
import dangeon.latest.scene.action.otog.main.panel.Base_Panel;
import dangeon.latest.scene.action.otog.main.panel.Panel_Main;
import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;
import dangeon.view.util.StringFilter;

public class Result_Panel_Main extends Base_Panel {
	public final Result_Panel_LIFE LIFE, SCORE;
	public final boolean NEW_RECORD, NEW_COMBO;
	public final boolean IS_DEMO;
	public final long BEST_SCORE;
	public final int BEST_COMBO;

	public Result_Panel_Main(int margine, Panel_Main p) {
		super(margine, margine, FR.SCREEN_WIDTH - margine * 2, FR.SCREEN_HEIGHT
				- margine * 2, p);
		margine /= 2;
		LIFE = new Result_Panel_LIFE(margine, margine * 5, W / 2 - margine * 2,
				H / 2 - margine * 2, p);
		SCORE = new Result_Panel_SCORE(W / 2 + margine, margine * 5, W / 2
				- margine * 2, H / 2 - margine * 2, p);
		IS_DEMO = p.VH.demo;
		if (IS_DEMO) {
			BEST_SCORE = p.VH.CONTENT.SCORE;
			BEST_COMBO = p.VH.CONTENT.COMBO;
			NEW_COMBO = false;
			NEW_RECORD = false;
		} else {
			if (p.VH.score_milli / 1000 > p.VH.CONTENT.SCORE) {
				BEST_SCORE = (p.VH.score_milli / 1000);
				NEW_RECORD = true;
			} else {
				BEST_SCORE = p.VH.CONTENT.SCORE;
				NEW_RECORD = false;
			}
			if (p.VH.MAX_COMBO > p.VH.CONTENT.COMBO) {
				BEST_COMBO = p.VH.MAX_COMBO;
				NEW_COMBO = true;
			} else {
				BEST_COMBO = p.VH.CONTENT.COMBO;
				NEW_COMBO = false;
			}
		}
		p.VH.setRank();
	}

	@Override
	public void drawME(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		StringFilter.drawString(g, "RESULT", 10, 20);
		String s = P.VH.getDifficulty();
		g.setColor(StringFilter.getColor(g, s));
		s = StringFilter.getPlainString(s).trim();
		StringFilter.drawString(g, s, W / 2 + 10, 20);
		if (P.VH.flag_random != 0) {
			g.setColor(Color.LIGHT_GRAY);
			String st = "(RANDOM";
			if (P.VH.flag_random == 1)
				st = st.concat("a)");
			else
				st = st.concat("b)");
			StringFilter.drawString(g, st, W / 2 + 50, 20);
		}
		SCORE.draw(g);
		LIFE.draw(g);
		Font font = g.getFont();
		g.setFont(font.deriveFont(12f));
		NumberFormat nf = NumberFormat.getNumberInstance();
		int i = 13;
		g.setColor(Color.ORANGE);
		drawText(g, "COMBO", 0, i);
		g.setColor(StringFilter.NUMBERS);
		drawText(g, P.VH.MAX_COMBO, 1, i++, nf);
		g.setColor(Color.YELLOW);
		drawText(g, "SCORE", 0, i);
		g.setColor(StringFilter.NUMBERS);
		drawText(g, (int) (P.VH.score_milli / 1000), 1, i++, nf);
		i++;
		g.setColor(Color.ORANGE);
		drawText(g, "BEST COMBO", 0, i);
		g.setColor(StringFilter.NUMBERS);
		drawText(g, BEST_COMBO, 1, i, nf);
		if (NEW_COMBO) {
			g.setColor(TEXT.PERFECT.getColor(SCORE.frame / 2 % 2));
			drawText(g, "NEW!!", 2, i);
		}
		g.setColor(Color.YELLOW);
		drawText(g, "BEST SCORE", 0, ++i);
		g.setColor(StringFilter.NUMBERS);
		drawText(g, BEST_SCORE, 1, i, nf);
		if (NEW_RECORD) {
			g.setColor(TEXT.PERFECT.getColor(SCORE.frame / 2 % 2));
			drawText(g, "NEW!!", 2, i);
		}
		i++;
		g.setColor(Color.CYAN);
		drawText(g, "BAD COUNT", 0, ++i);
		g.setColor(StringFilter.NUMBERS);
		int sum = 0;
		for (TEXT t : P.VH.hash_save_tex_count.keySet()) {
			if (t.isNoGood()) {
				sum += P.VH.hash_save_tex_count.get(t);
			}
		}
		drawText(g, P.VH.CONTENT.MISS + " → " + nf.format(sum), 1, i);
		g.setColor(Color.WHITE);
		drawText(g, "RANK", 0, ++i);

		drawText(g, P.VH.getRank(g, P.VH.RANK), 1, i);
		i = 12;
		for (TEXT tex : TEXT.values()) {
			g.setColor(tex.getColor(SCORE.frame / 2 % 2));
			drawText(g, tex.name(), 3, ++i);
			g.setColor(StringFilter.NUMBERS);
			drawText(g, P.VH.hash_save_tex_count.get(tex), 4, i, nf);
		}
		g.setColor(Color.WHITE);
		drawText(g, Color.WHITE.toString() + P.VH.bgm.TITLE, 5, 21);
		g.setColor(Color.LIGHT_GRAY);
		drawText(g, P.VH.CONTENT.AUTHOR + "/" + P.VH.CONTENT.VERSION, 5, 22);
		g.setFont(font);
	}

	private void drawText(Graphics2D g, long number, int row, int cols,
			NumberFormat nf) {
		drawText(g, nf.format(number), row, cols);
	}

	private void drawText(Graphics2D g, String string, int row, int cols) {
		String str = StringFilter.getPlainString(string);
		int h = g.getFontMetrics().getHeight();
		int w = g.getFontMetrics().stringWidth(str);
		int x;
		if (row == 0)
			x = 5;
		else if (row == 3)
			x = 15 + W / 2;
		else if (row == 1)
			x = -7 + W / 2 - w;
		else if (row == 2)
			x = 75;
		else if (row == 4)
			x = -20 + W - w;
		else
			x = -5 + W - w;
		StringFilter.drawString_plain(g, string, x, h * ++cols + 10);
	}

	@Override
	public void upDate() {
		SCORE.upDate();
		LIFE.upDate();
	}

}
