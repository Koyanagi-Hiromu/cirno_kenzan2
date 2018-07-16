package dangeon.view.detail;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import main.constant.FR;
import main.util.BeautifulView;
import main.util.半角全角コンバーター;
import dangeon.controller.DangeonScene;
import dangeon.controller.listener.menu.ENCHANT;
import dangeon.controller.listener.menu.Gousei;
import dangeon.controller.listener.menu.ITEM;
import dangeon.controller.listener.menu.ITEM.ITEM_COMMAND;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.StringFilter;
import dangeon.view.util.Window;

public class Item {

	public static final int W, H, H_foot, H_command, W_enchant_select,
			W_enchant_item, W_wide, W_commands;
	public static final int margine = SCREEN.WINDOW_MARGINE;
	public static final Image ENCHANT_SELECT_WINDOW;
	public static final Image COMMANDS_WINDOW;
	public static final Image ITEM_WINDOW;
	public static final Image ITEM_WINDOW_FOOT;
	public static final Image MINI_EXPLANATION_WINDOW = Menu_First.STATUS_WINDOW;
	public static final int MINI_EXPLANATION_MAX_LENGTH = 4;

	static {
		W_commands = NormalFont.WIDTH2 + SCREEN.X_MARGINE * 2;
		H_command = Window.getNarrowWindowH(ITEM_COMMAND.LENGTH);
		COMMANDS_WINDOW = Window.get(W_commands, H_command);
		W_enchant_select = NormalFont.WIDTH2 * 2 + SCREEN.X_MARGINE * 2;
		W_enchant_item = FR.SCREEN_WIDTH - SCREEN.X * 2 - W_enchant_select;
		W_wide = FR.SCREEN_WIDTH - SCREEN.X * 2;
		W = W_wide;// - commands_W;
		H = Window.getNarrowWindowH(ITEM.Y_LENGTH);
		H_foot = Window.getNarrowWindowH(1);
		ITEM_WINDOW = Window.get(W, H);
		// int h =
		// Window.getNarrowWindowH_SmallFont(MINI_EXPLANATION_MAX_LENGTH);
		// MINI_EXPLANATION_WINDOW = Window.get(W, h);
		ENCHANT_SELECT_WINDOW = Window.get(W,
				Window.getNarrowWindowH(Enchant.LENGTH));
		ITEM_WINDOW_FOOT = Window.get(W, H_foot);
	}

	public static void draw(Graphics2D g) {
		drawItemWindow(g);
		if (DangeonScene.GOUSEI.isPresentScene()) {
			drawGOUSEI(g);
		}
		drawItems(g);
		// View_Cursor.draw(g, ITEM.flag_command_phase);
		drawExplain(g);
		if (ITEM.flag_command_phase) {
			drawCommands(g);
		}
		if (ITEM.flag_explanation_phase) {
			drawExplain_on_phase(g);
		} else if (DangeonScene.ENCHANT.isPresentScene()) {
			drawENCHANT(g);
		} else if (ITEM.flag_naming_phase) {
			fillBlack(g);
			View_Select_String.draw(g);
		}
	}

	private static void drawBelongingItemName(Graphics2D g, int index, int y) {
		Base_Artifact a = Belongings.get(index);
		int x = SCREEN.X + SCREEN.X_MARGINE;
		drawItemName(g, x, y, a.getImage(), a.getColoredName(false));
		if (Belongings.get(index).isEnchantedNow()) {
			for (Enchant enc : Enchant.values()) {
				if (enc.isEquals(a)) {
					drawItemEnchanted(g, x, y, enc.getColor());
					break;
				}
			}
		}
		if (a.isCurse()) {
			if (a instanceof SpellCard && !a.isPerfectCheked()) {
				return;
			}
			drawItemSholder(g, x, y, Color.PINK, "呪");
		}
	}

	private static void drawCommands(Graphics2D g) {
		int x = SCREEN.X + W - W_commands;
		int y = SCREEN.Y;
		g.drawImage(COMMANDS_WINDOW, x, y, null);
		for (int i = 0; i < ITEM_COMMAND.LENGTH; i++) {
			ITEM_COMMAND command = ITEM_COMMAND.get(i);
			g.setColor(command.getColor());
			g.drawString(command.toString(), x + SCREEN.X_MARGINE, y
					+ SCREEN.Y_MARGINE + (NormalFont.HEIGHT + margine)
					* (i + 1) - margine);
		}
		int _x = x + SCREEN.X_MARGINE;
		int _y = y + SCREEN.Y_MARGINE + (NormalFont.HEIGHT + margine)
				* (ITEM.command_y + 1) - margine;

		View_Cursor.draw(g, _x, _y, !DangeonScene.ENCHANT.isPresentScene());
	}

	private static void drawENCHANT(Graphics2D g) {
		fillBlack(g);
		int x = SCREEN.X;
		int y = SCREEN.Y + H_command - 20;
		g.drawImage(ENCHANT_SELECT_WINDOW, x, y, null);
		int __y = y + ENCHANT_SELECT_WINDOW.getHeight(null)
				+ SCREEN.WINDOW_MARGINE;
		g.drawImage(Window.get(W, Window.getNarrowWindowH_SmallFont(2)), x,
				__y, null);
		x += SCREEN.X_MARGINE;
		// y += SCREEN.Y_MARGINE;
		for (int i = 0; i < Enchant.LENGTH; i++) {
			Enchant command = Enchant.get(i);
			g.setColor(command.getColorWithForbitten());
			int del = Window.getNarrowStringY(i);
			if (command.isEnchant()) {
				Base_Item item = (Base_Item) command.getEnchant();
				if (ENCHANT.isSelectedArtifactEqualEnchant(command)) {
					BeautifulView.setAlphaOnImg(g, 0.5f);
				}
				drawItemName(g, x, y + del, item.getImage(),
						item.getName(new StringBuilder()).toString());
				BeautifulView.setAlphaOnImg(g, 1f);
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append("(");
				sb.append(command);
				sb.append(")");
				g.drawString(sb.toString(), x, y + del);
			}
		}
		g.setColor(Color.WHITE);
		g.setFont(NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
		ArrayList<String> list = new ArrayList<String>();
		StringFilter.setList(list, W - SCREEN.X_MARGINE * 2, true,
				ENCHANT.getEXP());
		for (int j = 0; j < list.size(); j++) {
			StringFilter.drawString(g, list.get(j), x, __y
					+ NormalFont.SMALL_HEIGHT
					+ (NormalFont.SMALL_HEIGHT + NormalFont.SMALL_LEADING) * j
					+ SCREEN.Y_SMALL_MARGINE);
		}
		g.setFont(NormalFont.NORMALFONT);
		View_Cursor.draw(g, x, y + Window.getNarrowStringY(ENCHANT.getY()),
				true);
	}

	private static void drawExplain(Graphics2D g) {
		boolean foot = ITEM.isFoot();
		if (!foot && Belongings.getSize() == 0) {
			return;
		}
		// BeautifulView.setAlphaOnImg(g, View.MENU_ALPHA);
		drawExplanation(g, SCREEN.X, (foot) ? SCREEN.Y + H_foot + margine
				: SCREEN.Y + H + margine, true);

	}

	private static void drawExplain_on_phase(Graphics2D g) {
		fillBlack(g);
		g.setColor(Color.WHITE);
		int h = Window.getNarrowWindowH(1);
		Window.draw(g, SCREEN.X, SCREEN.Y, W, h);
		drawBelongingItemName(g, ITEM.getIndex(), SCREEN.Y + SCREEN.Y_MARGINE
				+ NormalFont.HEIGHT);
		drawExplanation(g, SCREEN.X, SCREEN.Y + h + margine, false);
	}

	private static void drawExplanation(Graphics2D g, int X, int Y, boolean mini) {
		Base_Artifact a = Belongings.get(ITEM.getIndex());
		if (a == null)
			return;
		String[] arr;
		if (mini) {
			arr = a.firstPageExplain();
			g.drawImage(MINI_EXPLANATION_WINDOW, X, Y, null);
		} else {
			arr = a.secondPageExplain().toArray(new String[0]);
			int h = Window.getNarrowWindowH_SmallFont(arr.length);
			Window.draw(g, X, Y, W, h);
		}
		// g.setFont(ExplationFont.EXP_FONT);
		g.setFont(g.getFont().deriveFont(NormalFont.SMALL_SIZE));
		int times = arr.length;
		if (mini && arr.length > MINI_EXPLANATION_MAX_LENGTH) {
			times = MINI_EXPLANATION_MAX_LENGTH;
		}
		int x, y;
		x = X + SCREEN.X_MARGINE;
		for (int i = 0; i < times; i++) {
			y = Y + SCREEN.Y_SMALL_MARGINE
					+ (NormalFont.SMALL_HEIGHT + margine) * (i + 1) - margine;
			StringFilter.drawString(g, arr[i], x, y);
		}
		g.setFont(NormalFont.NORMALFONT);
	}

	private static void drawGOUSEI(Graphics2D g) {
		int x, y, w, h;
		FontMetrics fo = g.getFontMetrics();
		h = SCREEN.Y_MARGINE * 2 + fo.getHeight();
		w = SCREEN.X_MARGINE * 2 + fo.stringWidth("合成");
		x = SCREEN.X + W - w / 2;
		y = SCREEN.Y - h / 2;
		Window.draw(g, x, y, w, h);
		g.drawString("合成", x + SCREEN.X_MARGINE,
				y + SCREEN.Y_MARGINE + fo.getAscent());
		if (Gousei.getSelected_first() > 0) {
			g.setColor(Color.CYAN);
			int _y = SCREEN.Y + SCREEN.Y_MARGINE
					+ (NormalFont.HEIGHT + margine)
					* Gousei.getSelected_first();
			int _h = NormalFont.HEIGHT;
			g.fillRect(SCREEN.X, _y, W, _h);
		}
	}

	private static void drawItem(Graphics2D g, int i, int j) {
		int y = SCREEN.Y + Window.getNarrowStringY(j);
		drawBelongingItemName(g, i, y);
	}

	static void drawItemEnchanted(Graphics2D g, int x, int y, Color c) {
		g.setColor(c);
		Font font = g.getFont();
		g.setFont(font.deriveFont(10f));
		StringFilter.drawEdgedString_plain(g, "E", x - 2, y - 2);
		g.setFont(font);
		g.setColor(Color.WHITE);
	}

	public static void drawItemName(Graphics2D g, int x, int y, Image i,
			String name) {
		int _x = x - i.getWidth(null) / 2 + NormalFont.HEIGHT / 2;
		int _y = y - i.getHeight(null) / 2 - NormalFont.HEIGHT / 2 + 2;
		g.drawImage(i, _x, _y, null);
		StringFilter.drawString(g, name, x + 20, y);
	}

	private static void drawItems(Graphics2D g) {
		if (ITEM.isFoot()) {
			drawItem(g, -1, 0);
			// if (!ITEM.flag_command_phase) {
			int x = SCREEN.X + SCREEN.X_MARGINE;
			int y = SCREEN.Y + SCREEN.Y_MARGINE + NormalFont.HEIGHT;
			View_Cursor.draw(g, x, y, !ITEM.flag_command_phase);
			// }
			return;
		}
		int i = ITEM.x * ITEM.Y_LENGTH;
		for (int j = 0; i < Belongings.getSize() && j < ITEM.Y_LENGTH; i++, j++) {
			drawItem(g, i, j);
		}
		int x = SCREEN.X + SCREEN.X_MARGINE;
		int y = SCREEN.Y + SCREEN.Y_MARGINE
				+ (NormalFont.HEIGHT + NormalFont.DECENT) * (ITEM.y + 1)
				- NormalFont.DECENT;
		View_Cursor.draw(g, x, y, !ITEM.flag_command_phase);
	}

	static void drawItemSholder(Graphics2D g, int x, int y, Color c, String s) {
		g.setColor(c);
		Font font = g.getFont();
		g.setFont(font.deriveFont(10f));
		StringFilter.drawEdgedString_plain(g, s, x + 20
				- g.getFontMetrics().stringWidth(s), y - 5);
		g.setFont(font);
		g.setColor(Color.WHITE);
	}

	private static void drawItemWindow(Graphics2D g) {
		if (ITEM.isFoot()) {
			g.drawImage(ITEM_WINDOW_FOOT, SCREEN.X, SCREEN.Y, null);
			return;
		}
		int x = SCREEN.X + W / 2 - NormalFont.WIDTH2 / 2;
		g.drawImage(ITEM_WINDOW, SCREEN.X, SCREEN.Y, null);
		g.drawString(半角全角コンバーター.半角To全角数字(ITEM.getPage()), x - SCREEN.X_MARGINE
				- margine, SCREEN.Y + H);
		g.drawString("／", x, SCREEN.Y + H);
		g.drawString(半角全角コンバーター.半角To全角数字(ITEM.getMaxPage()), x
				+ SCREEN.X_MARGINE + margine, SCREEN.Y + H);
	}

	private static void fillBlack(Graphics2D g) {
		g.setColor(new Color(0x00, 0x00, 0x00, 0x88));
		g.fillRect(0, 0, FR.SCREEN_WIDTH, FR.SCREEN_HEIGHT);
	}

}
