package dangeon.view.detail;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.condition.CONDITION;
import dangeon.model.config.Config;
import dangeon.model.map.MapList;
import dangeon.model.map.PresentField;
import dangeon.model.map.StairScene;
import dangeon.model.object.creature.enemy.封獣ぬえ;
import dangeon.model.object.creature.player.Player;
import dangeon.model.object.creature.player.hold_enemy.HoldEnemy;
import dangeon.util.R;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.RotateDraw;
import dangeon.view.util.StringFilter;
import main.Second_Firster;
import main.constant.FR;
import main.res.Image_Artifact;
import main.res.Image_Icon;
import main.res.Image_LargeCharacter;
import main.res.Image_Object;
import main.res.Image_Window_Frame;
import main.util.BeautifulView;
import main.util.半角全角コンバーター;

public class Header {
	private static final int y = NormalFont.HEIGHT + NormalFont.LEADING;
	private static boolean flag_nue;
	private static int eat_frame = 0;
	private final static int EAT_MAX = 25;

	private static int face_timer = 0;

	private static final int WINK_FRAME = 3;

	private static double previous_hp = getHP_V(), previous_sat = getPeko();

	private static Image NORMAL, PINCH, HARAHERI;

	private static final int X_HP, X_SLASH, X_LV, X_F;
	private static final int X_HP_V, X_MAXHP_V, X_LV_V, X_F_V;

	static {
		W = FR.SCREEN_WIDTH;
		H = NormalFont.HEIGHT * 2;
		FontMetrics fm = Second_Firster.ME
				.getFontMetrics(NormalFont.NORMALFONT);
		X_HP = FR.SCREEN_WIDTH * 2 / 5;
		X_SLASH = X_HP + fm.stringWidth("HP 0000");
		X_LV = FR.SCREEN_WIDTH * 23 / 100;
		X_F = FR.SCREEN_WIDTH / 8;
		int margine = 10;
		X_HP_V = X_HP + fm.stringWidth("HP") + margine;
		X_MAXHP_V = X_SLASH + fm.stringWidth("/") + margine;
		X_LV_V = X_LV + fm.stringWidth("Lv") + margine;
		X_F_V = X_F - (fm.stringWidth("  ") + margine);
		setNORMAL_rely_on_hack();
		PINCH = createBaseImage(new Color(0xff, 0x55, 0x55));
		HARAHERI = createBaseImage(new Color(0xff, 0x55, 0xff));
	}

	public final static int W, H;

	private static final Color HPBAR_OUTLINE_COLOR = new Color(0, 150, 0);

	private static final int HP_BAR_H = 5;

	private static int _y = y + (HP_BAR_H + 1) * 2 + 1;

	private static Image createBaseImage(Color c) {
		BufferedImage bi;
		Graphics2D g;
		bi = new BufferedImage(W * 2, H * 2, BufferedImage.TYPE_INT_ARGB_PRE);
		g = bi.createGraphics();
		g.scale(2, 2);
		g.setFont(NormalFont.NORMALFONT);
		g.setColor(c);
		StringFilter.drawEdgedString_plain(g, "HP", X_HP, y);
		StringFilter.drawEdgedString_plain(g, "/", X_SLASH, y);
		StringFilter.drawEdgedString_plain(g, "Lv", X_LV, y);
		StringFilter.drawEdgedString_plain(g, "F", X_F, y);
		g.dispose();
		return bi;
	}

	public static void draw(Graphics2D g) {
		if (!StairScene.isTasksEmpty()) {
			return;
		}
		flag_nue = 封獣ぬえ.isNue();
		setDefaultAlpha(g);
		drawLeftUp(g);
		setDefaultAlpha(g);
		drawSttIcon(g);
		g.setColor(Color.WHITE);
		g.setFont(NormalFont.NORMALFONT);
	}

	private static void drawGage(Graphics2D g) {
		int max_hp = Player.me.getMAX_HP();
		// max_hp = 999;
		int body_w = 55, var_h = 18;
		int hp_min = 53, hp_max = 296, hp_h = 12;
		int sat_w = 7, sat_h = 26;
		int w = 0;
		if (max_hp > 500)
			w = hp_max - hp_min;
		else if (max_hp < 15)
			w = 0;
		else
			w = (int) Math.round((double) (hp_max - hp_min) * (max_hp - 15)
					/ (500 - 15));
		int hp_v = getHP_V();
		int peko = getPeko();
		int x = 46;
		int y = 5;
		int mg = 3, mg_ = 2;
		int x2 = x + mg;
		int y2 = y + mg;
		g.drawImage(Image_Window_Frame.header_frame_sat.image, x, y, null);
		if (peko == previous_sat) {
			g.drawImage(Image_Window_Frame.header_satiety_bar.image, x2, y2
					+ peko, x2 + sat_w, y2 + sat_h, 0, peko, sat_w, sat_h, null);
		} else {
			if (peko > previous_sat) {
				g.drawImage(Image_Window_Frame.header_satiety_bar.image, x2, y2
						+ peko, x2 + sat_w, y2 + sat_h, 0, peko, sat_w, sat_h,
						null);
				setAlphaOnImg(g, (float) (peko - previous_sat));
				g.drawImage(Image_Window_Frame.header_satiety_bar.image, x2, y2
						+ (int) previous_sat, x2 + sat_w, y2 + sat_h, 0,
						(int) previous_sat, sat_w, sat_h, null);
				setDefaultAlpha(g);
				previous_sat += +0.1;
			} else if (peko < previous_sat) {
				g.drawImage(Image_Window_Frame.header_satiety_bar.image, x2, y2
						+ (int) previous_sat, x2 + sat_w, y2 + sat_h, 0,
						(int) previous_sat, sat_w, sat_h, null);
				setAlphaOnImg(g, 0.5f);
				g.drawImage(Image_Window_Frame.header_satiety_bar.image, x2, y2
						+ peko, x2 + sat_w, y2 + sat_h, 0, peko, sat_w, sat_h,
						null);
				setDefaultAlpha(g);
				previous_sat += -0.5;
			}
			if (previous_sat - peko < 0.5 && previous_sat - peko > -0.5)
				previous_sat = peko;
		}
		x += 14;
		// /////////////////
		drawInfoIcon(g, x, y);
		// ////////////////
		if (flag_nue) {
			return;
		}
		g.drawImage(Image_Window_Frame.header_frame_hp_body.image, x, y, null);
		int _x = x;
		x += body_w;
		g.drawImage(Image_Window_Frame.header_frame_hp_variable.image, x, y, w,
				var_h, null);

		x += w;
		g.drawImage(Image_Window_Frame.header_frame_hp_end.image, x, y, null);
		_x += mg_;
		y += mg;
		if (previous_hp == hp_v) {
			g.drawImage(Image_Window_Frame.header_hp_bar.image, _x, y, _x
					+ hp_v, y + hp_h, 0, 0, hp_v, hp_h, null);
		} else if (previous_hp < hp_v) {
			setDefaultAlpha(g);
			g.setColor(Color.BLUE);
			g.fillRect(_x, y, hp_v, hp_h);
			g.drawImage(Image_Window_Frame.header_hp_bar.image, _x, y, _x
					+ (int) previous_hp, y + hp_h, 0, 0, (int) previous_hp,
					hp_h, null);
			setAlphaOnImg(g, 0.8f);
			g.drawImage(Image_Window_Frame.header_hp_bar.image, _x, y, _x
					+ hp_v, y + hp_h, 0, 0, hp_v, hp_h, null);

			previous_hp += 0.1 + (hp_v - previous_hp) / 100.0;
			if (previous_hp > hp_v)
				previous_hp = hp_v;
		} else {
			setDefaultAlpha(g);
			g.drawImage(Image_Window_Frame.header_hp_bar.image, _x, y, _x
					+ hp_v, y + hp_h, 0, 0, hp_v, hp_h, null);
			setAlphaOnImg(g, 0.3f);
			g.drawImage(Image_Window_Frame.header_hp_bar.image, _x, y, _x
					+ (int) previous_hp, y + hp_h, 0, 0, (int) previous_hp,
					hp_h, Color.RED, null);
			previous_hp -= 0.8;
			if (previous_hp < hp_v)
				previous_hp = hp_v;
		}
	}

	private static void drawHeader(Graphics2D g) {
		if (Player.me.pinchHp()) {
			g.drawImage(PINCH, 0, 0, W, H, null);
		} else if (Player.me.getSATIETY() == 0) {
			g.drawImage(HARAHERI, 0, 0, W, H, null);
		} else {
			g.drawImage(NORMAL, 0, 0, W, H, null);
		}
		g.setFont(NormalFont.NORMALFONT);
		g.setColor(Color.WHITE);
		if (PresentField.get().isHaraheru()) {
			StringFilter.drawEdgedString_plain(g, floor(), X_F_V, y);
		} else {
			StringFilter.drawEdgedString_plain(g, "??", X_F_V, y);
		}
		StringFilter.drawEdgedString_plain(g,
				String.valueOf(Player.me.getLV()), X_LV_V, y);
		StringFilter.drawEdgedString_plain(g,
				String.valueOf(Player.me.getHP()), X_HP_V, y);
		StringFilter.drawEdgedString_plain(g,
				String.valueOf(Player.me.getMAX_HP()), X_MAXHP_V, y);
	}

	private static void drawHPBar(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(X_HP + 1, y + 5 + 1, Player.me.getMAX_HP(), HP_BAR_H);
		g.setColor(Color.GREEN);
		g.fillRect(X_HP + 1, y + 5 + 1, Player.me.getHP(), HP_BAR_H);
		g.setColor(HPBAR_OUTLINE_COLOR);
		g.drawRect(X_HP, y + 5, Player.me.getHP() + 2 - 1, HP_BAR_H + 1 - 1);
		g.setColor(Color.WHITE);
		g.drawRect(X_HP, y + 5, Player.me.getMAX_HP() + 2 - 1, HP_BAR_H + 2 - 1);
	}

	protected static void drawIMLC(Graphics2D g) {
		// if (eat_frame == 0)
		// setEatIce();
		int size = 100;
		if (Player.me.getClassJob().getIMLC() != null) {
			Player.me.getClassJob().getIMLC().draw(g, 3, 3, 50, 46, 74);
		} else if (HoldEnemy.ME.isHold()) {
			Image_LargeCharacter imlc = HoldEnemy.ME.get().getTatiE();
			imlc.draw(g, 22, 25, 50);
		} else if (eat_frame > 0) {
			eat_frame--;
			int x = 0, y = 0;
			int w = size;
			int h = size;
			double rate = Math.sin(Math.PI * (eat_frame * 1.5) / EAT_MAX) / 8;
			w -= Math.round(w * rate);
			h += Math.round(h * rate);
			g.drawImage(Image_Object.cir_eating.getImage(), x + -25 + size / 2
					- w / 2, y + -13 + size - h, w, h, null);
		} else if (flag_nue || Player.me.isBadCondition()
				|| Player.me.pinchHyperHp()) {
			drawIMLC_GURUGURU(g, 0, 0, size);
		} else if (Player.me.pinchHp()) {
			g.drawImage(Image_Object.cir_damage.getImage(), -25, -13, size,
					size, null);
			g.drawImage(Image_Object.cir_damage_eye.getImage(), -25, -13, size,
					size, null);
		} else {
			drawIMLC_DOYA(g, 0, 0, size);
		}
	}

	public static void drawIMLC_DOYA(Graphics2D g, int x, int y, int size) {
		g.drawImage(getImage(), x + -25, y + -13, size, size, null);

	}

	public static void drawIMLC_GURUGURU(Graphics2D g, int x, int y, int size) {
		g.drawImage(Image_Object.cir_damage.getImage(), x + -25, y + -13, size,
				size, null);
		RotateDraw.draw(g, Image_Object.cir_damage_L_eye.getImage(), x + 16,
				y + 24);
		RotateDraw.draw(g, Image_Object.cir_damage_L_eye.getImage(), x + 29,
				y + 24);
	}

	private static void drawInfoIcon(Graphics2D g, int x, int y) {
		y += 34;
		y += 10;
		x += 1;
		// x += HoldEnemy.ME.draw(g, x, y);
		if (Player.me.isGray()) {
			g.drawImage(Image_Icon.size.getImage(), x, y, null);
			drawNext(g, x, y, null);
			x += 33;
		}
		if (Player.me.conditionCheck(CONDITION.倍速)
				|| Player.me.conditionCheck(CONDITION.三倍速)) {
			g.drawImage(TurnSystemController.getPhaseImage(), x, y, null);
			drawNext(g, x, y, true);
			x += 33;
		}
		if (TurnSystemController.getSlowSwitch()) {
			g.drawImage(Image_Icon.stt_icon_slow.getImage(), x, y, null);
			drawNext(g, x, y, false);
		}
	}

	private static void drawLeftUp(Graphics2D g) {
		setDefaultAlpha(g);
		drawIMLC(g);
		int hp = Player.me.getHP();
		drawGage(g);
		setDefaultAlpha(g);
		drawNumbers(g, hp);
	}

	private static void drawNext(Graphics2D g, int x, int y,
			Boolean next_warning) {
		float size = 10f;
		Font font = g.getFont();
		g.setFont(font.deriveFont(size));
		x += 4;
		if (next_warning != null) {
			if (next_warning)
				g.setColor(Color.GREEN);
			else
				g.setColor(Color.YELLOW);
			StringFilter.drawEdgedString(g, "NEXT", x, y);
		} else {
			g.setColor(Color.LIGHT_GRAY);
			StringFilter.drawEdgedString(g, "GRAY", x, y);
		}
		// g.drawString("NEXT", x + 1, y + size);
		g.setFont(font);
	}

	private static void drawNumbers(Graphics2D g, int hp) {
		Color c = flag_nue || Player.me.pinchHp() ? Color.RED : Color.WHITE;
		g.setColor(c);
		g.setFont(g.getFont().deriveFont(11f));

		StringBuilder sb = new StringBuilder();
		sb = new StringBuilder();
		String diff = null;
		if (PresentField.get().getRandomMap() != null) {
			sb.append(floor());
			diff = PresentField.get().getRandomMap().getDIFFICULTY().toString();
		} else {
			sb.append("??");
			if (Config.getFate() != 0)
				diff = "Fate +".concat(String.valueOf(Config.getFate()));
		}
		sb.append(" F");
		sb.append(" LV ");
		sb.append(Player.me.getLV());
		int floor_lv_x = 3;
		int y = 62;
		int dy = 15;
		int att = TurnSystemController.getRestTurnForHeader();
		StringFilter.drawEdgedString_plain(g, sb.toString(), floor_lv_x, y);
		if (diff != null)
			StringFilter.drawEdgedString(g, diff, floor_lv_x, y + dy);
		floor_lv_x = 120;
		y = 33;
		g.drawImage(Image_Artifact.BOOK2.getImage(0), floor_lv_x - 18, y - 30,
				null);
		StringFilter.drawEdgedString_plain(g,
				"×".concat(半角全角コンバーター.半角To全角数字(Player.me.getBooks())),
				floor_lv_x + 15, y);
		if (att >= 0) {
			sb = new StringBuilder();
			sb.append("あと ");
			sb.append(Color.CYAN);
			sb.append(att / 10);
			sb.append(c);
			sb.append(" ターン");
			StringFilter.drawEdgedString(g, sb.toString(), floor_lv_x + 50, y);
		}
		sb = new StringBuilder();
		if (flag_nue) {
			return;
		} else {
			for (int i = 2; hp < Math.pow(10, i) && i > 0; i--) {
				sb.append(" ");
			}
			sb.append(hp);
		}
		int x = 64;
		y = 33;
		StringFilter.drawEdgedString_plain(g, sb.toString(), x - 1, y);
		StringFilter.drawEdgedString_plain(g, "/", x + 19, y);
		StringFilter.drawEdgedString_plain(g,
				String.valueOf(Player.me.getMAX_HP()), x + 27, y);
		if (!Config.isCoinOnly1())
		{
			int retry = Config.getRetryNumber();
		
			if (retry == 3)
				StringFilter.drawEdgedString(g, StringFilter.NUMBERS + "★★", x + 30, y + dy);
			else if (retry == 2)
				StringFilter.drawEdgedString(g, StringFilter.NUMBERS + "★" + Color.gray + "☆", x + 30, y + dy);
			else
				StringFilter.drawEdgedString(g, Color.gray + "☆☆", x + 30, y + dy);
		}
	}

	private static void drawOldIdea(Graphics2D g) {
		int hp = Player.me.getHP(), max_hp = Player.me.getMAX_HP();
		// Player.me.setHP(100);
		// Player.me.setMAX_HP(1);
		/**
		 * 　　　　　w1　　　w2<br/>
		 * 　　 xs←ーーーー→←→　　　　　　xf<br/>
		 * 　　 　　　　　　　　　 ーーーーーー┓ys<br/>
		 * 　　 　　　　　　　／　　　　　　　 ｜<br/>
		 * 　hs ┏ーーーーー-　　　　　　　　　｜hl<br/>
		 * 　　 ┗ーーーーーーーーーーーーーー ┛<br/>
		 */
		int xs = 60, w1 = 50, w2 = 4, ys = 10, hs = 5, hl = 12;
		int x0 = xs - 4, x1 = x0 + w1, x2 = x1 + w2, x3 = x0
				+ Math.min(max_hp, 400);
		int y0 = ys, y1 = ys + hl - hs, y2 = y0 + hl;
		int[] xPoints = { x0, x1, x2, x3, x3, x0 }, yPoints = { y1, y1, y0, y0,
				y2, y2 };
		int n = 6;
		if (max_hp < w1) {
			xPoints[1] = xPoints[2] = xPoints[3] = xPoints[4];
			yPoints[3] = yPoints[2] = yPoints[1];
		} else if (max_hp < w1 + w2) {
			xPoints[2] = xPoints[3] = xPoints[4];
			yPoints[3] = yPoints[2] = yPoints[0] - (hl - hs) * (max_hp - w1)
					/ w2;
		}
		int[] hp_xPoints = new int[n], hp_yPoints = new int[n];
		for (int i = 0; i < n; i++) {
			hp_xPoints[i] = xPoints[i];
			hp_yPoints[i] = yPoints[i];
		}
		hp_xPoints[3] = hp_xPoints[4] = x0 + hp;
		if (hp < w1) {
			hp_xPoints[1] = hp_xPoints[2] = hp_xPoints[3] = hp_xPoints[4];
			hp_yPoints[3] = hp_yPoints[2] = hp_yPoints[1];
		} else if (hp < w1 + w2) {
			hp_xPoints[2] = hp_xPoints[3] = hp_xPoints[4];
			hp_yPoints[3] = hp_yPoints[2] = hp_yPoints[0] - (hl - hs)
					* (hp - w1) / w2;
		}
		g.setColor(Color.RED);
		g.fillPolygon(xPoints, yPoints, n);
		g.setColor(Color.GREEN);
		g.fillPolygon(hp_xPoints, hp_yPoints, n);
		g.setColor(Color.BLACK);
		g.drawPolygon(xPoints, yPoints, n);
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(12f));
		StringBuilder sb = new StringBuilder();
		sb.append("HP");
		sb.append("　");
		for (int i = 2; hp < Math.pow(10, i) && i > 0; i--) {
			sb.append("　　");
		}
		sb.append(Player.me.getHP());
		StringFilter.drawEdgedString_plain(g, sb.toString(), xs, 14);
		g.setFont(g.getFont().deriveFont(8f));
		StringFilter.drawEdgedString_plain(g,
				"  ".concat(String.valueOf(Player.me.getMAX_HP())), x2, 15);
		// drawHeader(g);
		// drawHPBar(g);
		// drawSatietyBar(g);
		// drawSttIcon(g);

		double rate;
		if (Player.me.getSATIETY() > Player.satiety_first) {
			rate = (double) (Player.me.getSATIETY() - Player.satiety_first)
					* (100 - Player.satiety_first)
					/ (Player.me.getMAX_SATIETY() - Player.satiety_first)
					+ Player.satiety_first;
		} else {
			rate = Player.me.getSATIETY();
		}
		rate /= 100.0;
		if (rate < 0) {
			rate = 0;
		} else if (rate > 1) {
			rate = 1;
		}
		int X_HP = x0, _y = y2 + 2;
		g.setColor(new Color(255, (int) (255 * rate), 0));
		g.fillRect(X_HP + 1, _y + 1, Player.me.getSATIETY(), 5);
		// g.setColor(new Color(150, (int) (200 * rate), 0));
		// g.drawRect(X_HP, _y, Player.me.getSATIETY() + 2 - 1, 6 - 1);
		g.setColor(Color.BLACK);
		g.drawRect(X_HP, _y, Player.me.getMAX_SATIETY() + 2 - 1, 7 - 1);
	}

	private static void drawSatietyBar(Graphics2D g) {
		double rate;
		if (Player.me.getSATIETY() > Player.satiety_first) {
			rate = (double) (Player.me.getSATIETY() - Player.satiety_first)
					* (100 - Player.satiety_first)
					/ (Player.me.getMAX_SATIETY() - Player.satiety_first)
					+ Player.satiety_first;
		} else {
			rate = Player.me.getSATIETY();
		}
		rate /= 100.0;
		if (rate < 0) {
			rate = 0;
		} else if (rate > 1) {
			rate = 1;
		}
		int X_HP = 6, _y = 24;
		g.setColor(new Color(255, (int) (255 * rate), 0));
		g.fillRect(X_HP + 1, _y + 1, Player.me.getSATIETY(), 5);
		// g.setColor(new Color(150, (int) (200 * rate), 0));
		// g.drawRect(X_HP, _y, Player.me.getSATIETY() + 2 - 1, 6 - 1);
		g.setColor(Color.BLACK);
		g.drawRect(X_HP, _y, Player.me.getMAX_SATIETY() + 2 - 1, 7 - 1);
	}

	private static void drawSttIcon(Graphics2D g) {
		List<CONDITION> list = Player.me.getConditionListNow();
		Medal.一度にXXコの状態異常アイコンを出した.save_the_more(list.size());
		int margine = 4;
		int w = Image_Icon.SIZE + margine;
		int show_max = 10;
		g.setColor(StringFilter.NUMBERS);
		g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
		for (int i = 0; i < list.size(); i++) {
			CONDITION con = list.get(i);
			int row = i / show_max, col = i - row * show_max;
			int x = FR.SCREEN_WIDTH - w * (row + 1);
			int y = margine + w * col;
			g.drawImage(Image_Icon.getImage(con), x, y, null);
			int turn = Player.me.getConditionTurn(con);
			if (turn > 0) {
				String str = String.valueOf(turn);
				StringFilter.drawEdgedString_plain(g, str, x + Image_Icon.SIZE
						- g.getFontMetrics().stringWidth(str), y
						+ Image_Icon.SIZE + 5);
			}
		}

	}

	private static String floor() {
		if (MapList.getFloor() < 10) {
			return "0".concat(String.valueOf(MapList.getFloor()));
		}
		return String.valueOf(MapList.getFloor());
	}

	private static float getAlpha() {
		return Scene_Action.getMe().isDirecting() ? 0.3f : 1f;
	}

	private static int getHP_V() {
		int hp = Player.me.getHP(), max_hp = Player.me.getMAX_HP();
		int hp_min = 53, hp_max = 296;
		int w = 0;
		if (max_hp > 500)
			w = hp_max - hp_min;
		else if (max_hp < 15)
			w = 0;
		else
			w = (int) Math.round((double) (hp_max - hp_min) * (max_hp - 15)
					/ (500 - 15));
		return (int) Math.round((double) (hp_min + w) * hp / max_hp);
	}

	public static BufferedImage getImage() {
		Image_Object iml;
		if (face_timer == 0) {
			if (R._nextDouble() * 100 < 1) {
				face_timer = WINK_FRAME * 4;
			}
			iml = Image_Object.cir_norma1;
		} else {
			face_timer--;
			if (face_timer >= WINK_FRAME * 3)
				iml = Image_Object.cir_norma2;
			else if (face_timer >= WINK_FRAME * 2)
				iml = Image_Object.cir_normal3;
			else if (face_timer >= WINK_FRAME * 1)
				iml = Image_Object.cir_norma2;
			else
				iml = Image_Object.cir_norma1;
		}
		return iml.getImage();
	}

	private static int getPeko() {
		int sat_h = 26;
		return sat_h
				- (int) Math.round((double) (sat_h) * Player.me.getSATIETY()
						/ Player.me.getMAX_SATIETY());
	}

	private static void setAlphaOnImg(Graphics2D g, float f) {
		BeautifulView.setAlphaOnImg(g, f * getAlpha());
	}

	private static void setDefaultAlpha(Graphics2D g) {
		BeautifulView.setAlphaOnImg(g, getAlpha());
	}

	public static void setEatIce() {
		eat_frame = EAT_MAX;
	}

	public static void setNORMAL_rely_on_hack() {
		if (Config.isHack_playing()) {
			NORMAL = createBaseImage(new Color(0xaa, 0xaa, 0xaa));
		} else {
			NORMAL = createBaseImage(new Color(0x55, 0xcc, 0xff));
		}
	}

}
