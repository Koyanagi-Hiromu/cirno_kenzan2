package dangeon.view.detail;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import main.res.CHARA_IMAGE;
import main.util.DIRECTION;
import main.util.半角全角コンバーター;
import dangeon.controller.listener.menu.ITEM;
import dangeon.controller.listener.menu.Menu_Result;
import dangeon.model.map.field.random.Base_Map_Random.Difficulty;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.Composition;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Enchant;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.StringFilter;
import dangeon.view.util.Window;

public class View_Result {

	public static void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		if (Menu_Result.getX() == -1) {
			// int w = Frame.SCREEN_WIDTH - SCREEN.X * 2, h = Window
			// .getNarrowWindowH_SmallFont(12);
			g.drawImage(Item.ITEM_WINDOW, SCREEN.X, SCREEN.Y, null);
			g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
			lineDrawer1(g, 1);
			lineDrawer2(g, 2);
			lineDrawer3_4(g, 3);
			lineDrawer5(g, 5);
			lineDrawer6(g, 6);
			lineDrawer7(g, 7);
			lineDrawer8_14(g);
			g.drawImage(Item.MINI_EXPLANATION_WINDOW, SCREEN.X, SCREEN.Y
					+ Item.H + SCREEN.WINDOW_MARGINE, null);
			lineDrawer14_(g);
		} else {
			g.drawImage(Item.ITEM_WINDOW, SCREEN.X, SCREEN.Y, null);
			drawItems(g);
			try {
				drawExplan(g);
			} catch (IndexOutOfBoundsException e) {
				// 無視
			}
		}
		int x = Item.ITEM_WINDOW.getWidth(null) - 30;
		int _y = Item.ITEM_WINDOW.getHeight(null) - 12;
		if (Menu_Result.getX() != -1) {
			g.drawImage(CHARA_IMAGE.arrow.getWalkImage(1, DIRECTION.LEFT, 0),
					x - 26, _y, null);
			g.drawString(String.valueOf(Menu_Result.getX() + 1), x + 6, _y + 33);
		}
		if (Menu_Result.getX() != 2) {
			g.drawImage(CHARA_IMAGE.arrow.getWalkImage(1, DIRECTION.RIGHT, 0),
					x, _y, null);
		}
	}

	private static void drawBelongingItemName(Graphics2D g, int index, int y) {
		Base_Artifact a = Menu_Result.getResult().ITEMS.get(index);
		int x = SCREEN.X + SCREEN.X_MARGINE;
		boolean enchant = false;
		for (int i = 0; i < Menu_Result.getResult().ENCHANT_ARTIFACT.length; i++) {
			if (Menu_Result.getResult().ENCHANT_ARTIFACT[i] == a) {
				enchant = true;
				Item.drawItemName(
						g,
						x,
						y,
						a.getImage(),
						Enchant.values()[i].getColor()
								+ a.getColoredName(false));
				Item.drawItemEnchanted(g, x, y, Enchant.values()[i].getColor());
				break;
			}
		}
		if (!enchant) {
			Item.drawItemName(g, x, y, a.getImage(), a.getColoredName());
		}
		if (a.isCurse()) {
			if (a instanceof SpellCard && !a.isPerfectCheked()) {
				return;
			}
			Item.drawItemSholder(g, x, y, Color.PINK, "呪");
		}
	}

	private static void drawExplan(Graphics2D g)
			throws IndexOutOfBoundsException {
		int X = SCREEN.X, Y = SCREEN.Y + Item.H + Item.margine;
		Base_Artifact a = Menu_Result.getResult().ITEMS.get(Menu_Result.getX()
				* ITEM.Y_LENGTH + Menu_Result.getY());
		if (a == null)
			return;
		String[] arr;
		arr = a.firstPageExplain();
		g.drawImage(Item.MINI_EXPLANATION_WINDOW, X, Y, null);
		g.setFont(g.getFont().deriveFont(NormalFont.SMALL_SIZE));
		int times = arr.length;
		if (arr.length > Item.MINI_EXPLANATION_MAX_LENGTH) {
			times = Item.MINI_EXPLANATION_MAX_LENGTH;
		}
		int x, y;
		x = X + SCREEN.X_MARGINE;
		for (int i = 0; i < times; i++) {
			y = Y + SCREEN.Y_SMALL_MARGINE
					+ (NormalFont.SMALL_HEIGHT + Item.margine) * (i + 1)
					- Item.margine;
			StringFilter.drawString(g, arr[i], x, y);
		}
		g.setFont(NormalFont.NORMALFONT);
	}

	private static void drawItem(Graphics2D g, int i, int j) {
		int y = SCREEN.Y + Window.getNarrowStringY(j);
		drawBelongingItemName(g, i, y);
	}

	private static void drawItems(Graphics2D g) {
		int i = Menu_Result.getX() * ITEM.Y_LENGTH;
		for (int j = 0; i < Menu_Result.getResult().ITEMS.size()
				&& j < ITEM.Y_LENGTH; i++, j++) {
			drawItem(g, i, j);
		}
		int x = SCREEN.X + SCREEN.X_MARGINE;
		int y = SCREEN.Y + SCREEN.Y_MARGINE
				+ (NormalFont.HEIGHT + NormalFont.DECENT)
				* (Menu_Result.getY() + 1) - NormalFont.DECENT;
		View_Cursor.draw(g, x, y, true);
	}

	private static void drawString1STTail(Graphics2D g, String str, int i) {
		int w = Records.WINDOW.getWidth(null);
		int x = w / 2 - g.getFontMetrics().stringWidth(str);
		stringDrawer(g, str, x, i, 1);
	}

	private static void drawString2NDTail(Graphics2D g, String str, int i) {

		int w = Records.WINDOW.getWidth(null);
		int x = w - g.getFontMetrics().stringWidth(str);
		stringDrawer(g, str, x, i, 2);
	}

	private static void drawStringCenter(Graphics2D g, String str, int i) {
		int w = Records.WINDOW.getWidth(null);
		int x = w / 2;
		stringDrawer(g, str, x, i, 0);
	}

	private static void drawStringLeft(Graphics2D g, String str, int i) {
		stringDrawer(g, str, 0, i, 0);
	}

	private static void lineDrawer1(Graphics2D g, int i) {
		drawStringLeft(g, Menu_Result.getResult().PLAYER.getColoredName(), i);
		StringBuilder sb = new StringBuilder();
		sb.append("Lv");
		sb.append(半角全角コンバーター.半角To全角数字(Menu_Result.getResult().PLAYER.getLV()));
		drawString1STTail(g, sb.toString(), i);
		long t = Menu_Result.getResult().PLAYER.getPlayingMilliTime();
		sb = new StringBuilder();
		t /= 1000;
		if (t > 3600) {
			sb.append(半角全角コンバーター.半角To全角数字(t / 3600));
			sb.append("時間");
			t -= t / 3600 * 3600;
		}
		if (t > 60) {
			sb.append(半角全角コンバーター.半角To全角数字(t / 60));
			sb.append("分");
			t -= t / 60 * 60;
		}
		sb.append(半角全角コンバーター.半角To全角数字(t));
		sb.append("秒");
		drawString2NDTail(g, sb.toString(), i);
	}

	private static void lineDrawer11_13(Graphics2D g, int i, Base_Artifact a) {
		if (a != null) {
			drawStringLeft(g,
					Enchant.getANYS()[i - 12].getColor() + a.getColoredName(),
					i);
		} else {
			if (Boolean.TRUE
					.equals(Menu_Result.getResult().PLAYER.hash_restriction
							.get(Enchant.getANYS()[i - 12]))) {
				drawStringLeft(g, Enchant.getANYS()[i - 12].getColor()
						+ "（未使用）", i);
			}
		}
	}

	private static void lineDrawer14_(Graphics2D g) {
		g.setFont(NormalFont.NORMALFONT);
		for (int j = 0, i = Menu_Result.getY(); i < Menu_Result.getResult().RECORD.length
				&& j < Menu_Result.getMaxY(); i++, j++) {
			// drawStringLeft(g, Menu_Result.getResult().RECORD[i], 16 + j);
			// stringDrawer(g, Menu_Result.getResult().RECORD[i], 30, 15 + j,
			// 0);
			System.out.println(Menu_Result.getY());
			StringFilter
					.drawString(
							g,
							Menu_Result.getResult().RECORD[i],
							SCREEN.X + SCREEN.Y_MARGINE,
							SCREEN.Y
									+ SCREEN.Y_MARGINE
									+ 5
									+ (NormalFont.SMALL_HEIGHT + NormalFont.SMALL_LEADING)
									* (16 + j));
		}
	}

	private static void lineDrawer2(Graphics2D g, int i) {
		if (Menu_Result.getResult().FIELD_NAME != null) {
			StringBuilder sb = new StringBuilder();
			sb.append(Menu_Result.getResult().FIELD_NAME);
			sb.append("-");
			sb.append(Difficulty.getFromIndex(Menu_Result.getResult().DIFFICULTY));
			sb.append("-");
			sb.append("　");
			sb.append(半角全角コンバーター.半角To全角数字(Menu_Result.getResult().FLOOR));
			sb.append("Ｆ");
			drawStringLeft(g, sb.toString(), i);
		}
	}

	private static void lineDrawer3_4(Graphics2D g, int i) {
		String s = StringFilter
				.getPlainString(Menu_Result.getResult().PLAYER.cause_of_death);
		if (s == null) {
			s = "原因不明の熱病で倒れた";
		}
		if (g.getFontMetrics().stringWidth(s) < Records.WINDOW.getWidth(null)) {
			drawStringLeft(g, s, i);
		} else {
			FontMetrics fm = g.getFontMetrics();
			int w = Records.WINDOW.getWidth(null);
			for (int j = 0; true; j++) {
				if (fm.stringWidth((String) s.subSequence(0, s.length() - j)) < w) {
					j++;
					drawStringLeft(g,
							(String) s.subSequence(0, s.length() - j), i);
					drawStringLeft(g,
							(String) s.subSequence(s.length() - j, s.length()),
							i + 1);
					break;
				}
			}
		}
	}

	private static void lineDrawer5(Graphics2D g, int i) {
		drawStringLeft(g, "こうげき", i);
		drawString1STTail(g, 半角全角コンバーター.半角To全角数字(Menu_Result.getResult().ATK),
				i);
		drawStringCenter(g, "最大ＨＰ", i);
		drawString2NDTail(
				g,
				半角全角コンバーター.半角To全角数字(Menu_Result.getResult().PLAYER.getMAX_HP()),
				i);
	}

	private static void lineDrawer6(Graphics2D g, int i) {
		drawStringLeft(g, "ぼうぎょ", i);
		drawString1STTail(g, 半角全角コンバーター.半角To全角数字(Menu_Result.getResult().DEF),
				i);
		drawStringCenter(g, "最大満腹度", i);
		drawString2NDTail(
				g,
				半角全角コンバーター.半角To全角数字(
						Menu_Result.getResult().PLAYER.getMAX_SATIETY())
						.concat("％"), i);
	}

	private static void lineDrawer7(Graphics2D g, int i) {
		drawStringLeft(g, "ちから", i);
		StringBuilder sb = new StringBuilder();
		sb.append(半角全角コンバーター.半角To全角数字(Menu_Result.getResult().PLAYER.getSTR()));
		sb.append("/");
		sb.append(半角全角コンバーター.半角To全角数字(Menu_Result.getResult().PLAYER
				.getMAX_STR()));
		drawString1STTail(g, sb.toString(), i);
		drawStringCenter(g, "経験値", i);
		drawString2NDTail(g, 半角全角コンバーター.半角To全角数字(Menu_Result.getResult().PLAYER
				.getPlayerExp()), i);
	}

	private static void lineDrawer7_10(Graphics2D g, int i, SpellCard sc) {
		if (sc != null) {
			drawStringLeft(g,
					Enchant.get(i / 2 - 4).getColor() + sc.getColoredName(), i);
			drawStringLeft(g, Composition.composition(sc), i + 1);
		} else {
			if (Boolean.TRUE
					.equals(Menu_Result.getResult().PLAYER.hash_restriction
							.get(Enchant.get(i / 2 - 4)))) {
				drawStringLeft(g, Enchant.get(i / 2 - 4).getColor() + "（未使用）",
						i);
			}
		}
	}

	private static void lineDrawer8_14(Graphics2D g) {
		Base_Artifact[] as = Menu_Result.getResult().ENCHANT_ARTIFACT;
		lineDrawer7_10(g, 8, (SpellCard) as[0]);
		lineDrawer7_10(g, 10, (SpellCard) as[1]);
		lineDrawer11_13(g, 12, as[2]);
		lineDrawer11_13(g, 13, as[3]);
		lineDrawer11_13(g, 14, as[4]);
		g.setColor(Color.WHITE);
	}

	private static void stringDrawer(Graphics2D g, String str, int x, int i,
			int tail) {
		x -= SCREEN.Y_MARGINE * tail;
		StringFilter.drawString(g, str, SCREEN.X + SCREEN.Y_MARGINE + x,
				SCREEN.Y + SCREEN.Y_MARGINE
						+ (NormalFont.SMALL_HEIGHT + NormalFont.SMALL_LEADING)
						* i);
	}
}
