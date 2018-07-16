package dangeon.latest.scene.action.otog.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import main.Listener;
import main.Listener.ACTION;
import main.util.DIRECTION;
import dangeon.latest.scene.action.otog.main.panel.Panel_Main;
import dangeon.latest.scene.action.otog.value.ValueHolder;
import dangeon.view.util.StringFilter;

public class Accepter extends Base_Otog_Object {
	private final Color C;
	public final Color BG_C;
	public final ACTION ACTION;
	public final int SIZE;
	public final int INDEX;

	public final String str;

	protected int frame;

	protected int FRAME = 15;
	protected boolean pushing_me;

	public Accepter(Panel_Main p, ACTION a, int i, Accepter[] accs) {
		super(p);
		BG_C = new Color(100, 200, 255);
		INDEX = i;
		ACTION = a;
		int margine = 5;
		x = margine;
		for (Accepter accepter : accs) {
			if (accepter != null)
				x += accepter.SIZE + margine;
		}
		if (a != null) {
			SIZE = 25;
		} else {
			SIZE = 50;
		}
		y = p.H - 35 - SIZE / 2 - margine;
		String str = "?";
		for (int key : Listener.getKey().keySet()) {
			if (Listener.getKey().get(key) == ACTION) {
				str = KeyEvent.getKeyText(key);
				if (str.length() > 2)
					str = str.substring(0, 2);
				if (!str.matches("En|Es"))
					break;
			}
		}
		this.str = str;
		if (INDEX % 2 == 0)
			C = Color.YELLOW;
		else
			C = Color.WHITE;
	}

	protected int convert(int top_y) {
		return (int) ((1 - (Math.pow(Math.sin(P.pre_ave / 4.5), 2) / 30.0)) * top_y);
	}

	@Override
	public final void draw(Graphics2D g) {
		drawBG(g);
		if (frame > 0) {
			drawWhite(g);
			frame--;
		}
	}

	public void draw_oku(Graphics2D g) {
		g.setColor(C);
		int s = SIZE / 2;
		g.drawLine(x + s, 0, x + s, y);
		g.drawLine(x + s, y + SIZE, x + s, P.H);
		// g.setColor(Color.BLACK);
		// g.drawLine(x, y + s, x - 2, y + s);
		// g.drawLine(x + SIZE - 1, y + s, x + SIZE + 4, y + s);
		// if (INDEX == 0) {
		// g.drawLine(x - 2, y + s, x - 4, y + s);
		// }
		// int h = 15;
		// int top_y = y + s - h;
		// top_y = convert(top_y);
		// g.setPaint(new GradientPaint(x, top_y, new Color(0, 0, 0, 0), x, y +
		// s,
		// Color.CYAN));
		// g.fillRect(x + SIZE - 2, top_y, 4, y + s - top_y);
		// g.fillRect(x - 3, top_y, 4, y + s - top_y);
		// if (INDEX == 0) {
		// g.fillRect(x - 5, top_y, 2, y + s - top_y);
		// } else if (INDEX == 4) {
		// g.fillRect(x + SIZE + 2, top_y, 3, y + s - top_y);
		// }
	}

	protected void drawBG(Graphics2D g) {
		// drawLight(g, Color.CYAN, y - 10);
		// System.out.println(1 - (Math.pow(Math.sin(P.pre_ave / 3.0), 2) /
		// 40.0));
		if (pushing_me)
			drawLight(g, C.brighter().brighter(), P.H / 2);
		drawLight(g, Color.CYAN, y - 8);
		setColor(g);
		drawSnow(g, 0);
		Font font = g.getFont();
		g.setFont(font.deriveFont(14f));
		int w = g.getFontMetrics().stringWidth(str);
		int h = g.getFontMetrics().getHeight() - 2;
		g.setColor(StringFilter.NUMBERS);
		StringFilter.drawString(g, str, x + SIZE / 2 - w / 2, y + h / 2 + SIZE
				/ 2);
		g.setFont(font);
	}

	protected void drawLight(Graphics2D g, Color c, int top_y) {
		top_y = convert(top_y);
		int w = SIZE;
		int x = this.x + w / 2;
		int y = this.y;
		int dx = (w * 433 + 500) / 1000;
		int x_r = x + dx;
		int x_l = x - dx;
		int dy = (w + 2) / 4;
		int xs[] = { x_l, x, x_r, x_r, x, x_l };
		int ys[] = { y + dy, y, y + dy, top_y + dy, top_y, top_y + dy };
		g.setPaint(new GradientPaint(x, top_y, new Color(0, 0, 0, 0), x,
				y + dy, c));
		g.fillPolygon(xs, ys, 6);
		// g.fillRect(x, 0, SIZE, y);
	}

	protected void drawSnow(Graphics2D g, int del) {
		int w = SIZE + del;
		int x = this.x - del / 2 + w / 2;
		int y = this.y - del / 2;
		int dx = (w * 433 + 500) / 1000;
		int y2 = y + (w + 2) / 4;
		int y3 = y + (w * 3 + 2) / 4;
		int xs[] = { x, x + dx, x + dx, x, x - dx, x - dx };
		int ys[] = { y, y2, y3, y + w, y3, y2 };
		if (del == 0) {
			Color c = g.getColor();
			g.setColor(BG_C);
			g.fillPolygon(xs, ys, 6);
			g.setColor(c);
		}
		g.drawPolygon(xs, ys, 6);
		// g.drawRect(x, y, w, w);
	}

	private void drawWhite(Graphics2D g) {
		g.setColor(new Color(255, 255, 255, 255 * frame / FRAME));
		int del = 2 * (FRAME - frame - 1);
		drawSnow(g, del);
	}

	public boolean push(ACTION a, DIRECTION d) {
		if (ACTION == a) {
			setFrame();
			return true;
		}
		return false;
	}

	protected void setColor(Graphics2D g) {
		if (pushing_me)
			g.setColor(Color.WHITE);
		else
			g.setColor(Color.BLUE);
	}

	protected void setFrame() {
		frame = FRAME;
	}

	@Override
	public void upDate(ValueHolder valueHolder, long frame, Iterator<?> iterator) {
		pushing_me = P.KH.isContraining(ACTION);
	}

}
