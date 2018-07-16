package dangeon.view.detail;

import java.awt.Graphics2D;
import java.awt.Image;

import main.constant.FR;
import main.util.半角全角コンバーター;
import dangeon.controller.listener.menu.Conducter;
import dangeon.controller.listener.menu.Conducter.PHASE;
import dangeon.controller.listener.menu.FIRST;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.Player;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.Window;

public class Menu_First {
	private enum Status {
		階層(0, 0), 剣の強さ(0, 1), 盾の強さ(0, 2), 満腹度(1, 0), ちから(1, 1), 経験値(1, 2);
		private final int x, y;

		private Status(int x, int y) {
			this.x = x;
			this.y = y;
		}

		private String getString() {
			switch (this) {
			case ちから:
				return ちから();
			case 剣の強さ:
				return 剣の強さ();
			case 満腹度:
				return 満腹度();
			case 盾の強さ:
				return 盾の強さ();
			case 経験値:
				return 経験値();
			case 階層:
				return 階層();
			}
			return "Error";
		}

		private String ちから() {
			String value = 半角全角コンバーター.半角To全角数字(Player.me.getSTR());
			String max_value = 半角全角コンバーター.半角To全角数字(Player.me.getMAX_STR());
			if (value.length() == 1) {
				value = "　".concat(value);
			}
			if (max_value.length() == 1) {
				max_value = "　".concat(max_value);
			}
			return "ちから　　　".concat(value).concat("/").concat(max_value);
		}

		private String 階層() {
			return PresentField.get().getMapName();
		}

		private String 経験値() {
			String value = 半角全角コンバーター.半角To全角数字(Player.me.getPlayerExp());
			while (value.length() != 7) {
				value = "　".concat(value);
			}
			return "経験値 ".concat(value);
		}

		private String 剣の強さ() {
			String value = 半角全角コンバーター.半角To全角数字(Enchant.getSumSTR());
			while (value.length() != 4) {
				value = "　".concat(value);
			}
			return "こうげき".concat(value);
		}

		private String 盾の強さ() {
			String value = 半角全角コンバーター.半角To全角数字(Enchant.getSumDEF());
			while (value.length() != 4) {
				value = "　".concat(value);
			}
			return "ぼうぎょ".concat(value);
		}

		private String 満腹度() {
			String value = 半角全角コンバーター.半角To全角数字(Player.me.getSATIETY());
			String max_value = 半角全角コンバーター.半角To全角数字(Player.me.getMAX_SATIETY());
			while (value.length() != 3) {
				value = "　".concat(value);
			}
			while (max_value.length() != 3) {
				max_value = "　".concat(max_value);
			}
			return "満腹度".concat(value).concat("/").concat(max_value)
					.concat("％");
		}
	}

	public static final Image FIRST_MENU_WINDOW;
	public static final Image STATUS_WINDOW;
	// private static final Image ICON_WINDOW;
	public static final int W, H;
	static {
		W = Window.getSpaceWindowW(2);
		H = Window.getSpaceWindowH(2);
		FIRST_MENU_WINDOW = Window.get(W, H);

		Graphics2D g = (Graphics2D) FIRST_MENU_WINDOW.getGraphics();
		// g.setColor(FONT_COLOR);
		// BeautifulView.setAntiAliasing(g, true);
		// g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SIZE * 2));
		// for (int i = 0; i < 2; i++)
		// for (int j = 0; j < 2; j++) {
		// g.drawString(PHASE.get(i, j).name(), Window.getStringX(i) * 2,
		// Window.getStringY(j) * 2);
		// }
		g.dispose();

		int hight = SCREEN.Y_MARGINE * 2
				+ (NormalFont.HEIGHT + NormalFont.DECENT) * 3;
		int width = FR.SCREEN_WIDTH - SCREEN.X * 2;
		int[] status_x = { SCREEN.X + SCREEN.X_MARGINE,
				FR.SCREEN_WIDTH / 2 - SCREEN.X_MARGINE };
		Y_STATUS = FR.SCREEN_HEIGHT - hight - SCREEN.Y_MARGINE;
		STATUS_WINDOW = Window.get(width, hight);
		STATUS_X = status_x;
		// int icon_w = 100 + SCREEN.X_MARGINE * 2;
		// int icon_h = 100 + SCREEN.Y_MARGINE * 2;
		// ICON_WINDOW = Window.get(icon_w, icon_h);
		// ICON_W = icon_w;
		// ICON_H = icon_h;
	}
	// private static final int ICON_W, ICON_H;
	private static final int[] STATUS_X;

	private static final int Y_STATUS;

	public static void draw(Graphics2D g) {
		drawLargeCharacter(g);
		draw4Menu(g);
		drawStatus(g);
		// drawIcon(g);
	}

	private static void draw4Menu(Graphics2D g) {
		g.drawImage(FIRST_MENU_WINDOW, SCREEN.X, SCREEN.Y, null);
		g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SIZE));
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++) {
				g.drawString(PHASE.get(i, j).toString(),
						SCREEN.X + Window.getStringX(i),
						SCREEN.Y + Window.getStringY(j));
			}
		View_Cursor.draw(g, SCREEN.X + Window.getStringX(FIRST.x), SCREEN.Y
				+ Window.getStringY(FIRST.y),
				Conducter.PHASE.FIRST.isCurrentPhase());
	}

	// private static void drawIcon(Graphics2D g) {
	// int x;
	// int y;
	// x = SCREEN.X;
	// y = Y_STATUS - ICON_H;
	// String str;
	// g.setFont(g.getFont().deriveFont(NormalFont.SMALL_SIZE));
	// g.drawImage(ICON_WINDOW, x, y, null);
	// if (Enchant.ATK.isEnchant()) {
	// g.drawImage(Enchant.ATK.getEnchant_Casted_SpellCard().getIcon(), x
	// + SCREEN.X_MARGINE, y + SCREEN.Y_MARGINE, null);
	// g.setColor(Color.RED);
	// str = "恋符";
	// StringFilter.drawEdgedString(g, str, x + SCREEN.X_MARGINE + 100 + 5
	// - g.getFontMetrics().stringWidth(str), y + SCREEN.Y_MARGINE
	// + 100);
	// }
	// x += ICON_W;
	// g.drawImage(ICON_WINDOW, x, y, null);
	// if (Enchant.DEF.isEnchant()) {
	// g.drawImage(Enchant.DEF.getEnchant_Casted_SpellCard().getIcon(), x
	// + SCREEN.X_MARGINE, y + SCREEN.Y_MARGINE, null);
	// g.setColor(Color.ORANGE);
	// str = "夢符";
	// StringFilter.drawEdgedString(g, str, x + SCREEN.X_MARGINE + 100 + 5
	// - g.getFontMetrics().stringWidth(str), y + SCREEN.Y_MARGINE
	// + 100);
	// }
	// g.setColor(Color.WHITE);
	// g.setFont(NormalFont.NORMALFONT);
	// }

	private static void drawLargeCharacter(Graphics2D g) {
		if (Enchant.ATK.isEnchant()) {
			((SpellCard) Enchant.ATK.getEnchant()).IMLC.draw(g, true);
		}

		if (Enchant.DEF.isEnchant()) {
			((SpellCard) Enchant.DEF.getEnchant()).IMLC.draw(g, false);
		}

	}

	private static void drawStatus(Graphics2D g) {
		g.drawImage(STATUS_WINDOW, SCREEN.X, Y_STATUS, null);
		g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SIZE - 2f));
		for (Status s : Status.values()) {
			g.drawString(s.getString(), STATUS_X[s.x], Y_STATUS
					+ SCREEN.Y_MARGINE + NormalFont.HEIGHT
					+ (NormalFont.HEIGHT + NormalFont.DECENT) * s.y);
		}
		g.setFont(NormalFont.NORMALFONT);

	}

}
