package main.res;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import main.constant.FR;
import main.util.BeautifulView;
import main.util.DIRECTION;
import main.util.FileReadSupporter;
import main.util.Show;
import main.util.Sleep;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.config.Config;
import dangeon.model.object.artifact.item.spellcard.SpellCard;

public enum Image_LargeCharacter {
	ANY, アリス, イナバ, キスメ, チルノ, ナズーリン, パチュリー, パルスィ, フランドール, プリズムリバー三姉妹, みすちー, ミスティア, メディスンメランコリー, リグル, ルーミア, レティ, レミリア, 伊吹萃香, 一輪雲山, 因幡てゐ, 永江衣玖, 洩矢諏訪子, 河城にとり, 火焔猫燐, 宮古芳香, 犬走椛, 鍵山雛, 古明地こいし, 古明地さとり, 紅美鈴, 黒谷ヤマメ, 魂魄妖夢, 四季映姫・ヤマザナドゥ, 射命丸文, 秋姉妹, 十六夜咲夜, 小野塚小町, 上白沢慧音, 星熊勇儀, 聖白蓮, 西行寺幽々子, 蘇我屠自古, 村紗水蜜, 多々良小傘, 東風谷早苗, 藤原妹紅, 寅丸星, 博麗霊夢, 八意永琳, 八雲紫, 八雲藍, 八坂神奈子, 比那名居天子, 封獣ぬえ, 風見幽香, 物部布都, 霧雨魔理沙, 幽谷響子, 霊烏路空, 橙
	// 登録する際はエネミーテーブルの「立ち絵」を一致させること
	, リリーホワイト, わかさぎ姫, Exルーミア, 今泉影狼, 蓬莱山輝夜, 大妖精, 秦こころ, 赤蛮奇, 姫海棠はたて, 朱鷺子, 霍青娥, 小悪魔, きもけーね, 二ッ岩マミゾウ, 豊聡耳神子

	, 魅魔, 少名針妙丸, 堀川雷鼓, 鬼人正邪, 九十九姉妹, Ex大妖精, プルートJの欠片, ゆっくり, 三月精;
	public static void drawSlide(Graphics2D g) {
		ArrayList<Image_LargeCharacter> list = new ArrayList<Image_LargeCharacter>(
				3);
		for (Image_LargeCharacter imlc : list_npc_slide) {
			list.add(imlc);
		}
		for (Image_LargeCharacter imlc : list) {
			imlc.drawSlides(g, false);
		}
		list.clear();
		for (Image_LargeCharacter imlc : list_player_slide) {
			list.add(imlc);
		}
		for (Image_LargeCharacter imlc : list) {
			imlc.drawSlides(g, true);
		}
	}

	public static void drawTest(Graphics2D g, boolean b) {
		int l = 10;
		int r = 5;
		if (b) {
			g.drawLine(OFFSET_X_LEFT - l, OFFSET_Y, OFFSET_X_LEFT + l, OFFSET_Y);
			g.drawLine(OFFSET_X_LEFT, OFFSET_Y - l, OFFSET_X_LEFT, OFFSET_Y + l);
			g.drawOval(OFFSET_X_LEFT - r, OFFSET_Y - r, r * 2, r * 2);
		} else {
			g.drawLine(OFFSET_X_RIGHT - l, OFFSET_Y, OFFSET_X_RIGHT + l,
					OFFSET_Y);
			g.drawLine(OFFSET_X_RIGHT, OFFSET_Y - l, OFFSET_X_RIGHT, OFFSET_Y
					+ l);
			g.drawOval(OFFSET_X_RIGHT - r, OFFSET_Y - r, r * 2, r * 2);
		}

	}

	public static void slide_init() {
		ArrayList<Image_LargeCharacter> list = new ArrayList<Image_LargeCharacter>(
				3);
		for (Image_LargeCharacter imlc : list_npc_slide) {
			list.add(imlc);
		}
		for (Image_LargeCharacter imlc : list) {
			imlc.slideEND();
		}
		list.clear();
		for (Image_LargeCharacter imlc : list_player_slide) {
			list.add(imlc);
		}
		for (Image_LargeCharacter imlc : list) {
			imlc.slideEND();
		}
	}

	public static void SlideOUT(boolean right_img) {
		for (Image_LargeCharacter imlc : right_img ? list_npc_slide
				: list_player_slide) {
			imlc.SlideOut();
		}
	}

	public static void slideOUT_Conv_end() {
		SlideOUT(false);
		SlideOUT(true);
		Conversation.end();
	}

	public String AUTHOR, LINK;

	public int X, Y, FACE_X, FACE_Y;

	public int W, H;

	private static final int MAX = 6;

	private static final HashMap<Image_LargeCharacter, BufferedImage> hash = new HashMap<Image_LargeCharacter, BufferedImage>(
			MAX);

	private static final ArrayList<Image_LargeCharacter> list = new ArrayList<Image_LargeCharacter>(
			MAX);

	public static final int OFFSET_X_LEFT = 80,
			OFFSET_X_RIGHT = FR.SCREEN_WIDTH - OFFSET_X_LEFT, OFFSET_Y = 120;

	private static final BufferedImage IMAGE_ANY = loadImage("res/image/LargeCharacter/ANY.png");

	public static Image_LargeCharacter get(SpellCard sc) {
		String str = sc.getClass().getSimpleName();
		if (str.isEmpty())
			str = sc.getTrueName();
		return get(str.substring(0, str.lastIndexOf("のカード")));
	}

	public static Image_LargeCharacter get(String sc) {
		for (Image_LargeCharacter im : values()) {
			if (im.name().matches(sc)) {
				return im;
			}
		}
		return ANY;
	}

	private static BufferedImage loadImage(String filename) {
		try {
			return ImageIO.read(FileReadSupporter.readURL(filename));
		} catch (IOException e) {
			Show.showCriticalErrorMessageDialog(e, filename);
		}
		return null;
	}

	private boolean flag_initing = false;

	private boolean hizaue, trinity;

	private int slide_x, slide_y, hold_time, alpha;

	private int dif_x, dif_y;

	private static final int HOLD_TIME = 45;

	private static LinkedHashSet<Image_LargeCharacter> list_player_slide = new LinkedHashSet<Image_LargeCharacter>(
			3), list_npc_slide = new LinkedHashSet<Image_LargeCharacter>(3);

	private boolean fade_out, auto_out;

	private int slide_step = 0;

	public void convSlideIN(boolean b) {
		int i = 0;
		if (!b)
			for (Image_LargeCharacter imlc : list_player_slide)
				imlc.slide(list_player_slide.size() == 1 ? 30 : (i++ == 0) ? 10
						: 20, 0);
		else
			for (Image_LargeCharacter imlc : list_npc_slide)
				imlc.slide(list_npc_slide.size() == 1 ? 30 : (i++ == 0) ? 10
						: 20, 0);
		SlideIN(b, false, false);
	}

	public void draw(Graphics2D g, boolean b) {
		draw(g, b, 0);
	}

	public void draw(Graphics2D g, boolean b, int diff) {
		int dx = X - diff;
		int y = Image_LargeCharacter.OFFSET_Y - Y;
		if (b) {
			g.drawImage(getIMAGE(), Image_LargeCharacter.OFFSET_X_LEFT + dx, y,
					-W, H, null);
		} else {
			// int X = 290 / 2;
			// int Y = 190 / 2;
			dx = X - diff;
			y = Image_LargeCharacter.OFFSET_Y - Y;
			g.drawImage(getIMAGE(), Image_LargeCharacter.OFFSET_X_RIGHT - dx,
					y, W, H, null);
		}
	}

	public void draw(Graphics2D g, int x) {
		g.drawImage(getIMAGE(), x, (FR.SCREEN_HEIGHT - H) / 2, W, H, null);
	}

	public void draw(Graphics2D g, int x, int y) {
		int W = this.W;
		int H = this.H;
		x -= FACE_X;
		y -= FACE_Y;
		g.drawImage(getIMAGE(), x, y, W, H, null);
	}

	public void draw(Graphics2D g, int x, int y, int percent) {
		int W = this.W * percent / 100;
		int H = this.H * percent / 100;
		x += FACE_X * percent / 100;
		y -= FACE_Y * percent / 100;
		g.drawImage(getIMAGE(), x, y, -W, H, null);
	}

	public void draw(Graphics2D g, int x, int y, int w, int h) {
		if (Config.isLightVer()) {
			return;
		}
		int W = w * 2, H = h * 2;
		// int FACE_X = 300 / 2;
		// int FACE_Y = 303 / 2;

		int X = FACE_X * 2 - W + 20, Y = FACE_Y * 2 - 25;
		g.setPaint(new GradientPaint(0, y, new Color(255, 230, 200), 0, y + H
				* 2, new Color(185, 95, 00)));
		g.fillRect(x, y, w, h);
		g.setPaint(null);
		g.drawImage(getIMAGE(), x, y, x + w, y + h, X, Y, X + W, Y + H, null);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, w, h);
	}

	public void draw(Graphics2D g, int x, int y, int w, int h, boolean b) {
		int W = w * 2, H = h * 2;
		int X = FACE_X * 2 - W + 20, Y = FACE_Y * 2 - 25;
		g.drawImage(getIMAGE(), x, y, x + w, y + h, X, Y, X + W, Y + H, null);
	}

	public void draw(Graphics2D g, int x, int y, int parcent, int w, int h) {
		// int W = this.W * percent / 100;
		// int H = this.H * percent / 100;
		// x += FACE_X * percent / 100;
		// y -= FACE_Y * percent / 100;
		// // g.drawImage(getIMAGE(), x, y, x - W, y + H, X2, Y2, X2 + W2, Y2 +
		// H2,
		// // null);
		// g.setColor(Color.WHITE);
		// g.drawImage(getIMAGE(), x, y, x - W, y + H, 0, 0, w * 5, h * 5,
		// null);

		int W = w * 2 * 100 / parcent, H = h * 2 * 100 / parcent;
		int X = FACE_X * 2 - w * 2 + 50, Y = FACE_Y * 2 - 85;
		// System.out.println(X);
		X = X * parcent / 100;
		Y = Y * parcent / 100;
		// System.out.println(X);
		// g.setPaint(new GradientPaint(0, y, new Color(255, 230, 200), 0, y + H
		// * 2, new Color(185, 95, 00)));
		// g.fillRect(x, y, w, h);
		// g.setPaint(null);
		x += w;
		g.drawImage(getIMAGE(), x, y, x - w, y + h, X, Y, X + W, Y + H, null);
		// g.setColor(Color.BLACK);
		// g.drawRect(x, y, w, h);
	}

	public void draw_diff(Graphics2D g, int diff) {
		if (!trinity) {
			g.drawImage(getIMAGE(), FR.SCREEN_WIDTH - FACE_X + diff,
					(FR.SCREEN_HEIGHT - H) / 2, W, H, null);
		} else {
			g.drawImage(getIMAGE(), FR.SCREEN_WIDTH - W * 3 / 4 + diff,
					(FR.SCREEN_HEIGHT - H) / 2, W, H, null);
		}
	}

	private void drawSlide(Graphics2D g, boolean b, int diff_x, int diff_y) {
		int dx = FACE_X + dif_x - diff_x;
		int y = Image_LargeCharacter.OFFSET_Y - Y - dif_y + diff_y;
		if (hizaue && y + H < FR.SCREEN_HEIGHT)
			y = FR.SCREEN_HEIGHT - H;
		if (b) {
			g.drawImage(getIMAGE(), Image_LargeCharacter.OFFSET_X_LEFT + dx, y,
					-W, H, null);
		} else {
			g.drawImage(getIMAGE(), Image_LargeCharacter.OFFSET_X_RIGHT - dx,
					y, W, H, null);
		}
	}

	private void drawSlides(Graphics2D g, boolean side) {
		if (slide_step == 0) {
			slideIN(g, side);
		} else if (slide_step == 1) {
			slideHOLD(g, side);
		} else {
			slideOUT(g, side);
		}
		BeautifulView.setAlphaOnImg(g, 1f);
	}

	private void drawSlides(Graphics2D g, int i) {
		drawSlides(g, i == 1);
	}

	@Deprecated
	private void drawSlides_old(Graphics2D g, boolean side) {
		if (fade_out) {
			int hosei = auto_out ? 13 : 0;
			BeautifulView.setAlphaOnImg(g, alpha / 100f * (hold_time + hosei)
					/ HOLD_TIME);
		} else
			BeautifulView.setAlphaOnImg(g, alpha / 100f);
		if (slide_x > 0) {
			slideIN(g, side);
		} else if (slide_x < 0) {
			slideOUT(g, side);
		} else {
			slideHOLD(g, side);
		}
		BeautifulView.setAlphaOnImg(g, 1f);
	}

	public String getAuthor() {
		return AUTHOR;
	}

	public BufferedImage getIMAGE() {
		while (flag_initing) {
			Sleep.done();
		}
		BufferedImage im = hash.get(this);
		if (im != null) {
			return im;
		} else {
			init();
			im = hash.get(this);
			if (im != null) {
				return im;
			}
			return IMAGE_ANY;
		}
	}

	public Image_LargeCharacter init() {
		flag_initing = true;
		if (!hash.containsKey(this)) {
			BufferedImage IMAGE = loadCSV();
			W = IMAGE.getWidth() / 2;
			H = IMAGE.getHeight() / 2;
			if (hash.size() == MAX) {
				System.out.println("before:" + list);
				hash.remove(list.get(0));
				list.remove(0);
			}
			hash.put(this, IMAGE);
			list.add(this);
			System.out.println(" after :" + list);
		} else {
			list.remove(this);
			list.add(this);
		}
		flag_initing = false;
		return this;
	}

	public boolean isConv() {
		return (list_npc_slide.contains(this) || list_player_slide
				.contains(this)) && slide_step != 2;
	}

	private BufferedImage loadCSV() {
		String address = "res/image/LargeCharacter/";
		String URL = "";
		try {
			InputStreamReader read;
			read = FileReadSupporter.readUTF8(address.concat("lc.csv"));
			BufferedReader br = new BufferedReader(read);
			String line = "";
			read_line_loop: while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "\t");
				while (st.hasMoreTokens()) {
					if (st.nextToken().matches(name())) {
						URL = st.nextToken();
						X = Integer.valueOf(st.nextToken()) / 2;
						Y = Integer.valueOf(st.nextToken()) / 2;
						FACE_X = Integer.valueOf(st.nextToken()) / 2;
						FACE_Y = Integer.valueOf(st.nextToken()) / 2;
						AUTHOR = st.nextToken();
						LINK = st.nextToken();
						if (st.hasMoreElements())
							hizaue = Boolean.valueOf(st.nextToken());
						if (st.hasMoreElements())
							trinity = Boolean.valueOf(st.nextToken());
						break read_line_loop;
					}
				}
			}
			br.close();
		} catch (Exception e) {
			Show.showCriticalErrorMessageDialog(e);
		}
		return loadImage(address.concat(URL).concat(".png"));
	}

	public void slide() {
		DIRECTION d = DIRECTION.getRandom8Direction();
		if (dif_x * d.X > 0 || dif_y * d.Y > 0) {
			slide();
		} else {
			slide(d.X * 10, d.Y * 10);
		}
	}

	public void slide(int dx, int dy) {
		dif_x += slide_x = dx;
		dif_y += slide_y = dy;
		slide_step = 0;
	}

	private void slideEND() {
		list_player_slide.remove(this);
		list_npc_slide.remove(this);
		slide_step = 0;
		dif_x = dif_y = 0;
	}

	private void slideHOLD(Graphics2D g, boolean side) {
		drawSlide(g, side, 0, 0);
		// if (hold_time-- <= 0) {
		// if (!auto_out && fade_out) {
		// slideEND(side);
		// } else if (auto_out && hold_time <= 0)
		// slide_x = -1;
		// }
	}

	private void slideIN(Graphics2D g, boolean side) {
		drawSlide(g, side, slide_x, slide_y);
		slide_x = slide_x * 3 / 4;
		slide_y = slide_y * 3 / 4;
		if (slide_x == 0 && slide_y == 0)
			slide_step = 1;
	}

	public void SlideIN(boolean right_img, boolean fade_out, boolean auto_out) {
		SlideIN(right_img, fade_out, auto_out, 100);
	}

	/**
	 * 
	 * @param right_img
	 * @param fade_out
	 * @param auto_out
	 * @param alpha
	 *            0～100　(100で非透明　0で完全透明)
	 */
	public void SlideIN(boolean right_img, boolean fade_out, boolean auto_out,
			int alpha) {
		this.fade_out = fade_out;
		this.auto_out = auto_out;
		this.alpha = alpha;
		this.slide_step = 0;
		slide_x = 100;
		if (right_img) {
			dif_x -= 30 * list_npc_slide.size();
			list_npc_slide.add(this);
		} else {
			dif_x = -30 * list_player_slide.size();
			list_player_slide.add(this);
		}
		slide_y = dif_y = 0;
		hold_time = HOLD_TIME;
	}

	private void slideOUT(Graphics2D g, boolean side) {
		drawSlide(g, side, -slide_x, slide_y);
		// hold_time--;
		slide_x = slide_x * 8 / 7 - 1;
		if (Math.abs(slide_x) > W)
			slideEND();
	}

	public void SlideOut() {
		slide_x *= -1;
		slide_step = 2;
	}
}
