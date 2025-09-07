package dangeon.view.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dangeon.model.config.Config;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Enchant;
import dangeon.model.object.creature.player.EnchantArrow;
import dangeon.view.constant.NormalFont;
import dangeon.view.detail.Item;
import main.Second_Firster;
import main.res.Image_Artifact;
import main.util.BeautifulView;
import main.util.DIRECTION;
import main.util.Show;

public class StringFilter {
	public static final String VALUE_REGEX = "\\[.*?\\]",
			COLOR_REGEX = "java.awt.Color".concat(VALUE_REGEX);

	public static final String ITEM_WARD = "#";

	private static final Pattern p_java_awt_color = Pattern
			.compile(COLOR_REGEX);

	public static final Color NUMBERS = new Color(170, 255, 70);

	private static final HashMap<Key_StringFilter, BufferedImage> pre_list = new HashMap<Key_StringFilter, BufferedImage>(),
			new_list = new HashMap<Key_StringFilter, BufferedImage>();

	private static boolean flag_neglect_dight = false;

	public static void drawArtifactName(Graphics2D g, Base_Artifact a, int x,
			int y) {
		drawArtifactName(g, a, x, y, null);
	}

	public static void drawArtifactName(Graphics2D g, Base_Artifact a, int x,
			int y, Color c) {
		if (a != null) {
			drawIcon(g, a, x, y);
			if (a instanceof SpellCard) {
				drawBombs(g, (SpellCard) a, x, y);
			}
			drawEnchant(g, a, x, y);
			drawCurse(g, a, x, y);
			x += 21;
			if (c == null)
				drawString(g, a.getColoredName(false), x, y);
			else {
				Color _c = g.getColor();
				g.setColor(c);
				drawString(g, getPlainString(a.getColoredName(false)), x, y);
				g.setColor(_c);
			}
			if (a.flag_sample) {
				x += 245;
				g.setColor(NUMBERS);
				String text = ItemTable.getRank_String(a);
				int w2 = g.getFontMetrics().stringWidth(text);
				drawEdgedString_plain(g, text, x - (28 + w2) / 2 + 6, y - 1);
				g.setColor(Color.WHITE);
				return;
			}
			if (a.isNotYoursYet()) {
				x += 245;
				int w = 28;
				int h = 16;
				g.setColor(new Color(150, 150, 150, 200));
				g.fillRect(x - w - 2, y - h + 2, w + 4, h);
				g.setColor(Color.WHITE);
			}
			if (a.isMerchant()) {
				String text = Integer.toString(a.getMerchantBuyValue());
				int w2 = g.getFontMetrics().stringWidth(text);
				drawEdgedString_plain(g, text, x - (28 + w2) / 2, y - 1);
			}
		}
	}

	private static void drawBombs(Graphics2D g, SpellCard a, int x, int y) {
		Font font = g.getFont();
		for (int j = 0; j < 4; j++) {
			if (!a.isStaticCheked()) {
				g.setColor(Color.YELLOW);
			} else if (j >= 4 - a.getBombs()) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.darkGray);
			}
			g.setFont(font.deriveFont(10f));
			StringFilter.drawEdgedString_plain(g, "*", x - 1, y + 4 * j - 8);
			g.setFont(font);
			g.setColor(Color.white);
		}
	}

	private static void drawCurse(Graphics2D g, Base_Artifact a, int x, int y) {
		if (a.getListComposition().contains(ENCHANT_SIMBOL.金)) {
			String s = "☆";
			g.setColor(Color.YELLOW);
			Font font = g.getFont();
			g.setFont(font.deriveFont(8f));
			StringFilter.drawEdgedString_plain(g, s, x + 12, y + 2);
			g.setFont(font);
			g.setColor(Color.WHITE);
		} else if (a.isCurse_And_isItViewed()) {
			String s = "呪";
			g.setColor(Color.PINK);
			Font font = g.getFont();
			g.setFont(font.deriveFont(10f));
			StringFilter.drawEdgedString_plain(g, s, x + 8, y - 2);
			g.setFont(font);
			g.setColor(Color.WHITE);
		}
	}

	public static void drawEdgedString(Graphics2D g, String str, int x, int y) {
		drawString(g, str, x, y, false);
	}

	public static void drawEdgedString_plain(Graphics2D g, String str, int x,
			int y) {
		drawEdgedString_plain(g, str, x, y, new Color(0x00, 0x00, 0x00, 0x99));
	}

	public static void drawEdgedString_plain(Graphics2D g, String str, int x,
			int y, Color edge_c) {
		Color c = g.getColor();
		Paint p = g.getPaint();
		g.setColor(edge_c);
		g.setColor(Color.BLACK);
		// BeautifulView.setAntiAliasing(g, false);
		for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
			g.drawString(str, x + d.X, y + d.Y);
		}
		// BeautifulView.setAntiAliasing(g, true);
		g.setColor(c);
		g.setPaint(p);
		g.drawString(str, x, y);
	}

	private static void drawEnchant(Graphics2D g, Base_Artifact a, int x, int y) {
		if (a.isEnchantedNow()) {
			g.setColor(Enchant.getEnchantedPlace(a).getColor());
			drawEnchant(g, x, y);
		} else if (EnchantArrow.isEnchant(a)) {
			g.setColor(Enchant.CL_ANY3);
			drawEnchant(g, x, y);
		}
	}

	public static void drawEnchant(Graphics2D g, int x, int y) {
		Font font = g.getFont();
		g.setFont(font.deriveFont(10f));
		StringFilter.drawEdgedString_plain(g, "E", x + 4, y - 2);
		g.setFont(font);
		g.setColor(Color.WHITE);
	}

	private static void drawIcon(Graphics2D g, Base_Artifact a, int x, int y) {
		Image im = a.getImage();
		int w = im.getWidth(null), h = im.getHeight(null);
		if (a.getImage_Artifact().SIZE != null) {
			w = h = a.getImage_Artifact().SIZE;
		}
		x += -w / 2 + NormalFont.HEIGHT / 2 + 2;
		y += -h / 2 - NormalFont.HEIGHT / 2 + 2;
		if (a.getImage_Artifact().X != null) {
			x += a.getImage_Artifact().X;
		}
		if (a.getImage_Artifact().Y != null) {
			y += a.getImage_Artifact().Y;
		}
		g.drawImage(im, x, y, w, h, null);
	}

	/**
	 * 
	 * @param g
	 * @param str
	 * @param x
	 * @param y
	 * @param child
	 *            0: dropshadow 1:eddeged 2:plain
	 */
	private static void drawString(Graphics2D g, String str, int child) {
		if (child == 2)
			flag_neglect_dight = true;
		int x = 0, y = g.getFontMetrics().getHeight();
		if (str.contains(ITEM_WARD)) {
			String[] arr = str.split(ITEM_WARD, 2);
			{
				if (arr[1].length() < 2) {
					str = arr[0];
				} else {
					str = arr[1];
					int icon = Integer.valueOf(String.valueOf(str.charAt(0)),
							Character.MAX_RADIX) - 1;
					int freeze = Integer.valueOf(String.valueOf(str.charAt(1)));
					if (icon >= 0) {
						Image im = Image_Artifact.values()[icon]
								.getImage(freeze);
						Item.drawItemName(
								g,
								g.getFontMetrics().stringWidth(
										getPlainString(arr[0])), y, im,
								str.substring(2));
						str = arr[0];
					} else {
						str = arr[0].concat("◆").concat(str.substring(2));
					}
				}
			}
			// int w = im.getWidth(null);
			// int h = im.getHeight(null);
			// g.drawImage(im, -w / 2, -h / 2, null);
			// str = "  ".concat(str.substring(3));
		}
		String print;
		String color = null;
		StringBuilder sb = new StringBuilder();
		int w = 0;
		int seek = 0;
		Matcher m = p_java_awt_color.matcher(str);
		while (m.find()) {
			print = str.substring(seek, m.start());
			color = m.group();
			w = g.getFontMetrics().stringWidth(sb.toString());
			if (child == 0)
				drawString(g, print, x + w, y, g.getColor(), true);
			else if (child == 1)
				drawEdgedString_plain(g, print, x + w, y);
			else
				drawString(g, print, x + w, y, g.getColor(), false);
			g.setColor(getColor(color));
			sb.append(print);
			seek = m.end();
		}
		w = g.getFontMetrics().stringWidth(sb.toString());
		print = str.substring(seek);
		if (child == 0)
			drawString(g, print, x + w, y, g.getColor(), true);
		else if (child == 1)
			drawEdgedString_plain(g, print, x + w, y);
		flag_neglect_dight = false;
	}

	public static void drawString(Graphics2D g, String str, int x, int y) {
		drawString(g, str, x, y, true);
	}

	public static void drawString(Graphics2D g, String str, int x, int y,
			boolean true_shadow__false_edged) {
		drawString(g, str, x, y, true_shadow__false_edged ? 0 : 1);
	}

	private static void drawString(Graphics2D g, String str, int x, int y,
			Color c, boolean drop_shadow) {
		if (flag_neglect_dight) {
			drawString_run(g, str, x, y, drop_shadow);
		} else {
			String print;
			StringBuilder sb = new StringBuilder();
			int w = 0;
			int seek = 0;
			Matcher m = Pattern.compile("[0-9]|[０-９]|,|\\+|\\-|\\?").matcher(
					str);
			while (m.find()) {
				print = str.substring(seek, m.start());
				w = g.getFontMetrics().stringWidth(sb.toString());
				drawString_run(g, print, x + w, y, drop_shadow);
				sb.append(print);
				g.setColor(NUMBERS);
				print = m.group();
				w = g.getFontMetrics().stringWidth(sb.toString());
				drawString_run(g, print, x + w, y, drop_shadow);
				sb.append(print);
				g.setColor(c);
				seek = m.end();
			}
			w = g.getFontMetrics().stringWidth(sb.toString());
			print = str.substring(seek);
			drawString_run(g, print, x + w, y, drop_shadow);
		}
	}

	/**
	 * 
	 * @param g
	 * @param str
	 * @param x
	 * @param y
	 * @param child
	 *            0: dropshadow 1:eddeged 2:plain
	 */
	private static void drawString(Graphics2D g, String str, int x, int y,
			int child) {
		BufferedImage bi;
		if (pre_list.containsKey(str)) {
			bi = pre_list.get(str);
		} else {
			int width = g.getFontMetrics().stringWidth(getPlainString(str)) + 10;
			// if (str.contains(ITEM_WARD)) {
			// Matcher m = Pattern.compile(ITEM_WARD).matcher(str);
			// while (m.find()) {
			// width += 5;
			// mはgroupcountとかあった
			// }
			// }
			if (width <= 0) {
				return;
			}
			int height = g.getFontMetrics().getHeight()
					+ g.getFontMetrics().getDescent() + 1;
			bi = new BufferedImage(width * 2, height * 2,
					BufferedImage.TYPE_4BYTE_ABGR_PRE);
			Graphics2D off = bi.createGraphics();
			off.scale(2, 2);
			off.setFont(g.getFont());
			off.setColor(g.getColor());
			BeautifulView.setAntiAliasing(off, true);
			drawString(off, str, child);
			off.dispose();
		}
		new_list.put(new Key_StringFilter(g, str), bi);
		int w = bi.getWidth() / 2;
		int h = (bi.getHeight()) / 2;
		g.drawImage(bi, x, y - h + g.getFontMetrics().getDescent(), w, h, null);
	}

	public static void drawString_plain(Graphics2D g, String str, int x, int y) {
		flag_neglect_dight = true;
		drawString(g, str, x, y, true);
		flag_neglect_dight = false;
	}

	private static void drawString_run(Graphics2D g, String str, int x, int y,
			boolean drop_shadow) {
		Color c = g.getColor();
		Paint p = g.getPaint();
		if (Config.isLightVer()) {
			g.setColor(c);
		} else {
			if (drop_shadow) {
				g.setColor(new Color(0x00, 0x00, 0x00, 0xee));
				for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
					g.drawString(str, x + d.X, y + d.Y);
				}
				g.setColor(new Color(0x00, 0x00, 0x00, 0x66));
				g.drawString(str, x + 2, y + 2);
			}
			double rate = 1.3;
			g.setPaint(new GradientPaint(0f, y - g.getFont().getSize() / 2, c,
					0, y, new Color((int) (c.getRed() / rate), (int) (c
							.getGreen() / rate), (int) (c.getBlue() / rate), c
							.getAlpha())));

		}
		g.drawString(str, x, y);
		g.setColor(c);
		g.setPaint(p);

	}

	public static ArrayList<String> getBreakSpacingStrings(Font font,
			String str, int width) {
		ArrayList<String> list_item_cord = new ArrayList<String>();
		String reg = ITEM_WARD.concat("..");
		Matcher m = Pattern.compile(reg).matcher(str);
		while (m.find()) {
			list_item_cord.add(m.group());
		}
		String rep = "＃";
		str = str.replaceAll(reg, rep);
		ArrayList<String> list = getBreakSpacingStrings(font, str, width,
				new ArrayList<String>());
		if (!list_item_cord.isEmpty()) {
			ArrayList<String> _list_ = new ArrayList<String>();
			int i = 0;
			for (String s : list) {
				while (s.contains(rep)) {
					if (i < list_item_cord.size()) {
						s = s.replaceFirst(rep, list_item_cord.get(i++));
					}
				}
				_list_.add(s);
			}
			list = _list_;
		}
		return list;
	}

	public static ArrayList<String> getBreakSpacingStrings(Font font,
			String str, int width, ArrayList<String> list) {
		// FIXME private?
		Matcher m = p_java_awt_color.matcher(str);
		StringBuilder sb = new StringBuilder();
		int seek = 0;
		while (m.find()) {
			for (int i = seek; i < m.start(); i++) {
				ArrayList<String> l = getBreakSpacingStrings(sb, str, i, font,
						width, list);
				if (l != null)
					return l;
			}
			seek = m.end();
		}
		for (int i = seek; i < str.length(); i++) {
			ArrayList<String> l = getBreakSpacingStrings(sb, str, i, font,
					width, list);
			if (l != null)
				return l;
		}
		str = str.replaceAll("%", "");
		String ex = "$";
		while (str.startsWith(ex))
			str = str.replaceFirst("\\".concat(ex), "");
		if (str.contains(ex)) {
			int i = str.indexOf(ex);
			list.add(str.substring(0, i));
			return getBreakSpacingStrings(font, str.substring(i + 1), width,
					list);
		} else {
			list.add(str);
			return list;
		}
	}

	private static ArrayList<String> getBreakSpacingStrings(StringBuilder sb,
			String str, int i, Font font, int width, ArrayList<String> list) {
		// str = str.replaceAll(ITEM_WARD, "");
		FontMetrics fm = Second_Firster.ME.getFontMetrics(font);
		sb.append(str.substring(i, i + 1));
		if (fm.stringWidth(sb.toString()) > width) {
			String line = str.substring(0, i);
			String[] arr = { "$", "%" };
			boolean flag = true;
			for (String ex : arr) {
				while (line.startsWith(ex))
					line = line.replaceFirst("\\".concat(ex), "");
				if (line.contains(ex)) {
					i = line.indexOf(ex);
					list.add(line.substring(0, i++));
					flag = false;
					break;
				}
			}
			if (flag) {
				list.add(line);
			}
			String color = "";

			Matcher m = p_java_awt_color.matcher(line);
			while (m.find()) {
				color = m.group();
			}
			return getBreakSpacingStrings(font, color.concat(str.substring(i)),
					width, list);
		}
		return null;
	}

	/**
	 * 
	 * @param g
	 *            null is OK
	 * @param str
	 * @return
	 */
	public static Color getColor(Graphics2D g, String str) {
		String color = null;
		Matcher m = p_java_awt_color.matcher(str);
		while (m.find()) {
			color = m.group();
		}
		return color == null ? null : getColor(color);
	}

	private static Color getColor(String color) {
		Matcher m = Pattern.compile(VALUE_REGEX).matcher(color);
		if (!m.find()) {
			Show.showCriticalErrorMessageDialog("Error @ StringFilter.getColor()");
			return null;
		}
		String group = m.group();
		String[] arr = group.substring(1, group.length() - 1).split(",");
		int value[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			value[i] = Integer.valueOf(arr[i].substring(2, arr[i].length()));
		}
		if (value.length == 3) {
			return new Color(value[0], value[1], value[2]);
		}
		return new Color(value[0], value[1], value[2], value[3]);
	}

	public static Color getFirstColor(Graphics2D g, String str) {
		Matcher m = p_java_awt_color.matcher(str);
		while (m.find()) {
			return getColor(m.group());
		}
		return null;
	}

	public static String getPlainString(String str) {
		Matcher m = p_java_awt_color.matcher(str);
		return m.replaceAll("");
	}

	public static String getStringAt(String str, int i) {
		String plain = getPlainString(str);
		if (i >= plain.length())
			return null;
		String s = String.valueOf(plain.charAt(i));
		Matcher m = p_java_awt_color.matcher(str);
		int exclude = 0;
		while (m.find()) {
			int start = m.start() - exclude;
			exclude += m.group().length();
			if (i == start)
				s = m.group().concat(s);
		}
		return s;
	}

	private static boolean isWithinMsgBox(String str, int w, boolean small) {
		String check = str.replaceAll(StringFilter.COLOR_REGEX, "");
		Font font = NormalFont.NORMALFONT
				.deriveFont((small) ? NormalFont.SMALL_SIZE : NormalFont.SIZE);
		return Second_Firster.ME.getFontMetrics(font).stringWidth(check) <= w;
	}

	public static void setColor(Graphics2D g, String str) {
		String color = null;
		Matcher m = p_java_awt_color.matcher(str);
		while (m.find()) {
			color = m.group();
		}
		if (color != null)
			g.setColor(getColor(color));
	}

	public static void setList(ArrayList<String> list, int w,
			boolean small_size, String... string_arr) {
		list.clear();
		String line;
		if (string_arr.length > 2) {
			StringBuilder sb = new StringBuilder();
			for (String str : string_arr) {
				sb.append(str);
			}
			line = sb.toString();
		} else {
			line = "";
			for (String str : string_arr) {
				line = line.concat(str);
			}
		}
		String[] br = line.split("\\$");

		for (String str : br) {
			if (isWithinMsgBox(str.replaceAll("%", ""), w, small_size)) {
				list.add(str.replaceAll("%", ""));
			} else {
				if (str.contains("%")) {
					list.add(str.substring(0, str.indexOf("%")));
					list.add(str.substring(str.indexOf("%") + 1));
				} else {
					int end = str.length() - 1;
					for (int i = 0; true; i++) {
						if (isWithinMsgBox(str.substring(0, end - i), w,
								small_size)) {
							list.add(str.substring(0, end - i));
							list.add(str.substring(end - i));
							break;
						}
					}
				}
			}
		}
	}

	public static void setList(ArrayList<String> list, int w,
			String... string_arr) {
		setList(list, w, false, string_arr);
	}

	public static void setOnlyColor(Graphics2D g, String str) {
		String print;
		String color = null;
		StringBuilder sb = new StringBuilder();
		int seek = 0;
		Matcher m = p_java_awt_color.matcher(str);
		while (m.find()) {
			print = str.substring(seek, m.start());
			color = m.group();
			g.setColor(getColor(color));
			sb.append(print);
			seek = m.end();
		}
		print = str.substring(seek);
	}

	public static void upDate() {
		pre_list.clear();
		for (Key_StringFilter str : new_list.keySet()) {
			pre_list.put(str, new_list.get(str));
		}
		new_list.clear();
	}
}
