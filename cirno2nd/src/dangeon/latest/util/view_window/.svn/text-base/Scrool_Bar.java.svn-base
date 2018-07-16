package dangeon.latest.util.view_window;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;

import main.res.CHARA_IMAGE;
import main.util.BeautifulView;
import main.util.DIRECTION;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;

public class Scrool_Bar extends WindowFrame {
	public final Base_Scene_Menu PEARENT;

	public final int LENGTH;

	public final int COLS;

	protected boolean up, down;

	public Scrool_Bar(Integer x, Integer y, int cols, Font font, int length,
			Base_Scene_Menu parent) {
		super(x, y, 21, cols, font);
		COLS = cols;
		LENGTH = length;
		PEARENT = parent;
	}

	private void drawCurSor(Graphics2D g) {
		int space = 35;
		Image im = CHARA_IMAGE.arrow.getWalkImage(1, DIRECTION.UP, 0);
		int x = getViewX() - 5;
		BeautifulView.setAlphaOnImg(g, up ? 1f : 0.4f);
		g.drawImage(im, x, getViewY() - 2, null);
		BeautifulView.setAlphaOnImg(g, down ? 1f : 0.4f);
		im = CHARA_IMAGE.arrow.getWalkImage(1, DIRECTION.DOWN, 0);
		g.drawImage(im, x, getViewY() + getHeight() - space - 17, null);
		BeautifulView.setAlphaOnImg(g, 1f);
		int y = getViewY() + space;
		int max = getHeight() - space * 2;
		double par = (double) max / (LENGTH - 1);
		if (LENGTH < COLS)
			par = (double) max / COLS;
		x = getViewX() + 9;
		int w = 21;
		g.setPaint(new GradientPaint(x, y, Color.LIGHT_GRAY, x + w, y,
				Color.WHITE));
		g.fillRect(x, y, w, max);
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(x, y, w, max);
		g.setColor(Color.GRAY);
		g.drawLine(x, y, x, y + max);
		int h = (int) (par * COLS);
		y = (int) Math.round((y + par * PEARENT.getY()));
		// y = down ? (int) Math.round((y + par * PEARENT.getY())) : y + max -
		// h;
		x += 1;
		w -= 2;
		g.setPaint(new GradientPaint(x + w / 3, y, Color.WHITE, x + w, y,
				Color.GRAY));
		g.fillRect(x, y, w, h);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(x, y, w, h);
	}

	@Override
	public void drawWindow(Graphics2D g) {
		super.drawWindow(g);
		drawCurSor(g);
	}

	@Override
	public void setY(int index) {
		up = index > 0;
		down = index + COLS < LENGTH - 1;
	}
}
