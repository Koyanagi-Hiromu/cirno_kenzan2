package dangeon.latest.scene.action.otog.main.panel;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

public abstract class Base_Panel {
	public final int X, Y, W, H;
	protected final Panel_Main P;

	public Base_Panel(int x, int y, int w, int h, Panel_Main p) {
		X = x;
		Y = y;
		W = w;
		H = h;
		P = (this instanceof Panel_Main) ? (Panel_Main) this : p;
	}

	public final void draw(Graphics2D g) {
		g.translate(X, Y);
		g.setClip(0, 0, W, H);
		drawBG(g);
		drawME(g);
		g.setClip(null);
		if (P != null && P.VH.LIFE_100 < 2000)
			g.setColor(new Color(200, 0, 0));
		else
			g.setColor(new Color(0, 0, 250));
		g.drawRect(0, 0, W, H);
		g.translate(-X, -Y);
	}

	private void drawBG(Graphics2D g) {
		g.setColor(new Color(getBGColorR(), getBGColorG(), getBGColorB(), 200));
		g.fillRect(0, 0, W, H);
		drawBG_protected(g);
		int h = 20;
		g.setPaint(new GradientPaint(0, 0, Color.BLACK, 0, h, new Color(
				getBGColorR(), getBGColorG(), getBGColorB(), 0)));
		g.fillRect(0, 0, W, h);
		g.setPaint(new GradientPaint(0, H - h, new Color(getBGColorR(),
				getBGColorG(), getBGColorB(), 0), 0, H, Color.BLACK));
		// g.setColor(Color.RED);
		g.fillRect(0, H - h, W, h);
	}

	protected void drawBG_protected(Graphics2D g) {
	}

	protected void drawHLine(Graphics2D g, int y) {
		g.drawLine(0, y, W, y);
	}

	public abstract void drawME(Graphics2D g);

	protected int getBGColorB() {
		return 200;
	}

	protected int getBGColorG() {
		return 100;
	}

	protected int getBGColorR() {
		return 0;
	}

	public void upDate() {
	}
}
