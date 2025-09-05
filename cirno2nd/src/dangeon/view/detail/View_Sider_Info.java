package dangeon.view.detail;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

import dangeon.latest.scene.action.message.Message;
import dangeon.view.constant.BGMFONT;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.StringFilter;
import main.constant.FR;
import main.util.BeautifulView;

public class View_Sider_Info {
	private final static int INFO_INTER = 1000;
	private static final int MAX = 10;
	private static final LinkedHashSet<View_Sider_Info> info_list = new LinkedHashSet<View_Sider_Info>(
			MAX);
	private static final ArrayList<String> record = new ArrayList<String>(
			Message.MAX_RECORD);

	private static void add(View_Sider_Info type) {
		type.time = 0;
	}

	static void draw(Graphics2D g) {
		Font font = g.getFont();
		g.setFont(NormalFont.NORMALFONT.deriveFont(10f));
		int index = 0;
		for (Iterator<View_Sider_Info> i = info_list.iterator(); i.hasNext();) {
			View_Sider_Info type = i.next();
			if (type.draw_div(g, index)) {
				add(type);
				i.remove();
			}
			index++;
		}
		g.setFont(font);
	}

	private static void end(View_Sider_Info type) {
		record.add(type.name);
		while (record.size() >= Message.MAX_RECORD)
			record.remove(0);
	}

	public static String[] getRecord() {
		return record.toArray(new String[0]);
	}

	public static int getRecordSize() {
		return record.size();
	}

	public static void reset() {
		for (Iterator<View_Sider_Info> i = info_list.iterator(); i.hasNext();) {
			add(i.next());
			i.remove();
		}
	}

	static void setInformation(String... msg) {
		StringBuilder sb = new StringBuilder();
		for (String string : msg) {
			sb.append(string);
		}
		View_Sider_Info type = new View_Sider_Info(sb.toString());
		info_list.add(type);
		end(type);
	}

	final String name;

	private int time;

	View_Sider_Info(String s) {
		name = s;
		time = INFO_INTER;
	}

	boolean draw_div(Graphics2D g, int index) {
		int x = 0;
		int w = g.getFontMetrics().stringWidth(
				StringFilter.getPlainString(name)) + 5;
		int h = g.getFontMetrics().getHeight();
		int height = h * (index + 2) + BGMFONT.DECENT;
		if (time > 0) {
			BeautifulView.setAlphaOnImg(g,
					(float) Math.sin(Math.PI / 2 * time / INFO_INTER));
			x = FR.SCREEN_WIDTH - w - 10;
			if (time > INFO_INTER * 99 / 100) {
				x -= INFO_INTER * 99 / 100 - time;
			} else if (time < INFO_INTER / 8) {
				x += (INFO_INTER / 8 - time) * 2;
			}
			time--;
		}
		int y = SCREEN.Y + height;
		g.setPaint(new GradientPaint(x, y - h, new Color(0, 0, 255, 50), x, y,
				new Color(0, 0, 100, 50)));
		g.fillRect(x, y - h + 2, w, h);
		g.setColor(Color.WHITE);
		g.drawRect(x, y - h + 2, w, h);
		g.setColor(Color.WHITE);
		StringFilter.drawString(g, name, x, y);
		if (time > 0) {
			time--;
			if (time <= 0) {
				return true;
			}
			BeautifulView.setAlphaOnImg(g, 1f);
		}
		return false;
	}

	int getTime() {
		return time;
	}
}
