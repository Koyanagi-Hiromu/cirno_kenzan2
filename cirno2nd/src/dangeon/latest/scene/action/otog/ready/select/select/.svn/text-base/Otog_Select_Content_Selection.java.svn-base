package dangeon.latest.scene.action.otog.ready.select.select;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import main.constant.FR;
import main.constant.PropertySupporter;
import main.res.BGM;
import main.res.Image_LargeCharacter;
import main.thread.MainThread;
import main.util.Show;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.scene.action.otog.value.HitChecker.TEXT;
import dangeon.latest.scene.action.otog.value.ValueHolder;
import dangeon.model.config.Config;
import dangeon.view.util.StringFilter;

public class Otog_Select_Content_Selection extends Otog_Select_Content_Left
		implements Comparable<Otog_Select_Content_Selection> {
	public final File FILE;
	public final String AUTHOR;
	public final long OFFSET;
	public final BGM BGM;
	public final ValueHolder VH;
	private Image_LargeCharacter IMLC;
	public long SCORE;
	public int COMBO;
	public int RANK;
	public String MISS;
	public int PLAY_COUNT;
	public int FLAG_CLEARED;
	public final PropertySupporter PROP;

	public boolean flag;

	protected Otog_Select_Content_Selection() {
		super(1, 1);
		AUTHOR = null;
		OFFSET = 0;
		BGM = null;
		VH = null;
		SCORE = 0;
		COMBO = 0;
		RANK = 0;
		MISS = null;
		PROP = null;
		PLAY_COUNT = 0;
		FILE = null;
	}

	public Otog_Select_Content_Selection(String author, int level, int version,
			long offset, String bgm, File file, String prop_pass, ValueHolder vh) {
		super(version, level);
		// ,long score,
		// int combo, String rank, int miss, int play_count
		AUTHOR = author;
		OFFSET = offset;
		BGM = main.res.BGM.get(bgm);
		FILE = file;
		VH = vh;
		PropertySupporter p = null;
		try {
			p = new PropertySupporter(prop_pass, true);
		} catch (IOException e) {
			try {
				if (!new File(prop_pass).createNewFile()) {
					Show.showErrorMessageDialog("新譜面ファイルの初期情報の書き出しに失敗しました");
				}
				p = new PropertySupporter(prop_pass, true);
				p.saveProperty("SCORE", 0);
				p.saveProperty("COMBO", 0);
				p.saveProperty("RANK", 0);
				p.saveProperty("MISS", "-");
				p.saveProperty("PLAY_COUNT", 0);
				boolean flag_demand_to_gacha = true;
				if (LEVEL <= 2) {
					flag_demand_to_gacha = false;
				} else {
					File f = new File("./otog/editor");
					if (f.exists()) {
						if (AUTHOR.equals(new PropertySupporter(f)
								.getProperty_Nature("author"))) {
							flag_demand_to_gacha = false;
						}
					}
				}
				// 　これを-1にすることでガチャらせる
				p.saveProperty("FLAG_CLEARED", flag_demand_to_gacha ? -1 : 0);
			} catch (IOException e1) {
				Show.showErrorMessageDialog("新譜面ファイルの初期情報の書き出しに失敗しました");
				Show.showErrorMessageDialog(e1);
			}
		}
		PROP = p;
		SCORE = Long.valueOf(p.getProperty_Nature("SCORE").toString());
		COMBO = p.getProperty("COMBO");
		RANK = p.getProperty("RANK");
		MISS = p.getProperty_Nature("MISS").toString();
		PLAY_COUNT = p.getProperty("PLAY_COUNT");
		FLAG_CLEARED = p.getProperty("FLAG_CLEARED");
		// SCORE = score;
		// COMBO = combo;
		// RANK = rank;
		// MISS = miss;
		// PLAY_COUNT = play_count;
	}

	@Override
	public int compareTo(Otog_Select_Content_Selection o) {
		if (VH.select_sort == 0)
			return LEVEL - o.LEVEL;
		else if (VH.select_sort == 1)
			return BGM.TITLE.compareTo(o.BGM.TITLE);
		else if (VH.select_sort == 2)
			return AUTHOR.compareTo(o.AUTHOR);
		return 0;
	}

	protected boolean drawLevel(Graphics2D g, int x, int y, int w, int h) {
		StringBuilder sb = new StringBuilder();
		if (LEVEL < 10)
			sb.append(" ");
		sb.append(LEVEL);
		StringFilter.drawString(g, sb.toString(), x + 2, y
				+ g.getFontMetrics().getHeight() / 2 - 1);
		return true;
	}

	public void drawMe(Graphics2D g, int x, int cols) {
		x += dx;
		if (cols == 0)
			drawStatus(g);
		else
			IMLC = null;
		flag = cols == 0;
		int x1 = x, x2 = x + h / 2, x3 = x + getW() - h / 2, x4 = x + getW();
		int y = (cols + 3) * (h + 2) + dy;
		int y_m = y - h / 2, y_p = y + h / 2;
		int[] xs = { x1, x2, x3, x4, x3, x2 };
		int[] ys = { y, y_m, y_m, y, y_p, y_p };
		g.drawImage(FRAME, x1, y_m, FRAME.getWidth() / 2,
				FRAME.getHeight() / 2, null);
		int size = h / 2;
		int margine = 2;
		boolean b = drawMedal(g, x2, y, size);
		if (b)
			size += margine * 2;
		int title_head = x2 + size;
		b = drawLevel(g, title_head, y, x3 - margine - title_head, h - margine
				* 2);
		if (b)
			title_head += 23;
		drawTitle(g, title_head, y, x3 - margine - title_head, h - margine * 2);
	}

	protected boolean drawMedal(Graphics2D g, int x2, int y, int size) {
		if (FLAG_CLEARED != 0) {
			Color c = VH.getDifficultyColors(FLAG_CLEARED);
			if (MainThread.getFrame() / 2 % 2 == 0) {
				c = c.darker();
			}
			g.setPaint(new java.awt.RadialGradientPaint(x2 + size / 2, y,
					size / 2, new float[] { 0.3f, 1f }, new Color[] { c,
							c.darker().darker() }, CycleMethod.NO_CYCLE));
			g.fillRect(x2, y - size / 2, size, size);
			g.setPaint(null);
			g.setColor(Color.BLUE);
			g.drawRect(x2, y - size / 2, size, size);
		}
		return true;
	}

	protected void drawStatus(Graphics2D g) {
		int font_h = g.getFontMetrics().getHeight() + 1;
		int h = (font_h) * 5;
		int w = Message.W;
		int x = (FR.SCREEN_WIDTH - w) / 2;
		int y = FR.SCREEN_HEIGHT - h - x / 2;
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(x, y, w, h);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, w, h);
		g.setColor(Color.WHITE);
		g.setClip(x, y, w, h - 1);
		int x1 = x + 5;
		int x2 = x + w / 2 - 10;
		int x3 = x + w / 2;
		int x4 = x + w - 10;
		drawStatusL(g, Color.YELLOW, "SCORE", x1, y, font_h, 0);
		drawStatusL(g, Color.ORANGE, "COMBO", x1, y, font_h, 1);
		drawStatusL(g, Color.PINK, "RANK", x1, y, font_h, 2);
		drawStatusL(g, Color.CYAN, "BAD COUNT", x1, y, font_h, 3);
		drawStatusL(g, Color.MAGENTA, "PLAY COUNT", x1, y, font_h, 4);
		NumberFormat nf = NumberFormat.getNumberInstance();
		drawStatusR(g, StringFilter.NUMBERS, nf.format(SCORE), x2, y, font_h, 0);
		drawStatusR(g, StringFilter.NUMBERS, nf.format(COMBO), x2, y, font_h, 1);
		drawStatusR(g, null, VH.getRank(g, RANK), x2, y, font_h, 2);
		drawStatusR(g, StringFilter.NUMBERS, MISS, x2, y, font_h, 3);
		drawStatusR(g, StringFilter.NUMBERS, nf.format(PLAY_COUNT), x2, y,
				font_h, 4);
		drawStatusL(g, Color.WHITE, "ARRANGE by", x3, y, font_h, 3);
		drawStatusL(g, Color.LIGHT_GRAY, "OBJECT  by", x3, y, font_h, 4);
		drawStatusR(g, Color.WHITE, BGM.AUTHOR, x4, y, font_h, 3);
		drawStatusR(g, Color.LIGHT_GRAY, AUTHOR, x4, y, font_h, 4);
		if (IMLC == null && BGM.CLASS != null) {
			String card;
			card = BGM.CLASS.getSimpleName();
			card = card.substring(0, card.lastIndexOf("のカード"));
			IMLC = Image_LargeCharacter.get(card);
		}
		g.setClip(x3, y + 5, w / 2 - 5 * 2, font_h * 3 - 5 * 2);
		g.setColor(Color.WHITE);
		StringFilter.drawString(g, BGM.TITLE, x3, y + font_h * 2 - 1);
		if (IMLC != null) {
			IMLC.draw(g, x3 + 5, y + 5, w / 2 - 5 * 2, font_h * 3 - 5 * 2, true);
		}
		g.setClip(null);
	}

	protected void drawStatusL(Graphics2D g, Color c, String str, int x, int y,
			int font_h, int cols) {
		g.setColor(c);
		StringFilter.drawString_plain(g, str, x, y + font_h * ++cols - 2);
	}

	protected void drawStatusR(Graphics2D g, Color c, int i, int x, int y,
			int font_h, int cols) {
		drawStatusR(g, c, String.valueOf(i), x, y, font_h, cols);
	}

	protected void drawStatusR(Graphics2D g, Color c, String str, int x, int y,
			int font_h, int cols) {
		if (c != null) {
			g.setColor(c);
			StringFilter.drawString_plain(g, str, x
					- g.getFontMetrics().stringWidth(str), y + font_h * ++cols
					- 2);
		} else {
			StringFilter.drawString(g, str,
					x - g.getFontMetrics().stringWidth(str), y + font_h
							* ++cols - 2);
		}
	}

	@Override
	protected void drawTitle(Graphics2D g, int x, int y, int w, int h) {
		String str = VERSION.concat(" ").concat(Color.WHITE.toString())
				.concat(BGM.TITLE);
		String plan = StringFilter.getPlainString(str);
		if (flag && frame != -1) {
			double d = MainThread.getFrame() / 16.0;
			g.setPaint(new GradientPaint((float) (w * Math.cos(d)) + x + w / 2,
					(float) (h * 2 * Math.sin(d)) + y, Color.WHITE, x + w / 2,
					y, Color.ORANGE, true));
		} else
			g.setColor(Color.BLUE);
		g.drawRect(x, y - h / 2, w, h);
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect(x, y - h / 2, w, h);
		g.setClip(x + 1, y - h / 2, w - 1, h);
		g.setColor(Color.WHITE);
		if (flag && frame != -1) {
			int str_w = g.getFontMetrics().stringWidth(plan) + 10;
			str_w += 10;
			frame += 1;
			if (frame > str_w)
				frame -= str_w;
			StringFilter.drawString(g, str, x + 2 - frame + str_w, y
					+ g.getFontMetrics().getHeight() / 2 - 1);
			StringFilter.drawString(g, str, x + 2 - frame + str_w * 2, y
					+ g.getFontMetrics().getHeight() / 2 - 1);
			StringFilter.drawString(g, str, x + 2 - frame, y
					+ g.getFontMetrics().getHeight() / 2 - 1);
		} else {
			StringFilter.drawString(g, str, x + 3, y
					+ g.getFontMetrics().getHeight() / 2 - 1);
		}
		g.setClip(null);
	}

	@Override
	public void init() {
		dx += 20;
	}

	public void saveProp() {
		if (VH.score_milli / 1000 > SCORE) {
			SCORE = VH.score_milli / 1000;
			PROP.saveProperty("SCORE", VH.score_milli / 1000);
			int sum = 0;
			for (TEXT t : VH.hash_save_tex_count.keySet()) {
				if (t.isNoGood()) {
					sum += VH.hash_save_tex_count.get(t);
				}
			}
			String s = NumberFormat.getInstance().format(sum);
			PROP.saveProperty("MISS", s);
			MISS = s;
		}
		if (VH.MAX_COMBO > COMBO) {
			PROP.saveProperty("COMBO", VH.MAX_COMBO);
			COMBO = VH.MAX_COMBO;
		}
		if (VH.RANK > RANK) {
			PROP.saveProperty("RANK", VH.RANK);
			RANK = VH.RANK;
		}
		PROP.saveProperty("PLAY_COUNT", ++PLAY_COUNT);
		if (FLAG_CLEARED == 0) {
			FLAG_CLEARED = 1;
			PROP.saveProperty("FLAG_CLEARED", FLAG_CLEARED);
		}
		if (!VH.isFailed()) {
			boolean FULL_COMBO = true;
			for (TEXT text : VH.hash_save_tex_count.keySet()) {
				if (text.isCombo())
					continue;
				if (VH.hash_save_tex_count.get(text) > 0) {
					FULL_COMBO = false;
					break;
				}
			}
			int flag = 0;
			if (FULL_COMBO) {
				flag = 6;
			} else if (VH.difficulty > FLAG_CLEARED) {
				flag = VH.difficulty;
			}
			if (FLAG_CLEARED < flag) {
				Config.changeGachaPoints((flag - FLAG_CLEARED) * 5);
				FLAG_CLEARED = flag;
				PROP.saveProperty("FLAG_CLEARED", flag);
			}
		}
	}

	public void saveProp(boolean flag_get) {
		if (flag_get) {
			FLAG_CLEARED = 0;
		} else {
			FLAG_CLEARED = -1;
		}
		PROP.saveProperty("FLAG_CLEARED", FLAG_CLEARED);
	}

}
