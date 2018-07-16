package dangeon.latest.scene.action.otog.object;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.Iterator;

import main.util.DIRECTION;
import dangeon.latest.scene.action.otog.main.panel.Panel_Main;
import dangeon.latest.scene.action.otog.value.ValueHolder;

public class AccepterD extends Accepter {
	private DIRECTION d;
	private final Color C;

	public AccepterD(Panel_Main p, int i, Accepter[] accs) {
		super(p, null, i, accs);
		d = DIRECTION.NEUTRAL;
		C = Color.RED;
	}

	@Override
	protected void drawBG(Graphics2D g) {
		g.setColor(C);
		g.drawLine(x + SIZE / 2, 0, x + SIZE / 2, y);
		// g.drawLine(x + SIZE / 2, y + SIZE, x + SIZE / 2, P.H);
		if (pushing_me)
			drawLight(g, Color.RED, y - SIZE * 3 / 2);
		// drawLight(g, Color.CYAN, y - 8);
		g.setColor(BG_C);
		g.fillRect(x + 1, y + 1, SIZE - 3, SIZE - 2);
		setColor(g);
		g.drawRect(x + 1, y + 1, SIZE - 3, SIZE - 2);
		g.drawImage(P.VH.HASH.getImage(d), x, y, null);
		if (!pushing_me) {
			d = DIRECTION.NEUTRAL;
		}
	}

	@Override
	protected void drawLight(Graphics2D g, Color c, int top_y) {
		top_y = convert(top_y);
		int h = y - top_y;
		int w = SIZE;
		g.setPaint(new GradientPaint(x, top_y, new Color(0, 0, 0, 0), x, y, c));
		g.fillRect(this.x, top_y, w, h);
	}

	@Override
	protected void drawSnow(Graphics2D g, int del) {
		int w = SIZE + del;
		int x = this.x - del / 2;
		int y = this.y - del / 2;
		g.drawRect(x, y, w, w);
	}

	@Override
	public boolean push(main.Listener.ACTION a, DIRECTION d) {
		if (d != null) {
			setFrame();
			this.d = d;
			return true;
		}
		return false;
	}

	@Override
	public void upDate(ValueHolder valueHolder, long frame, Iterator<?> iterator) {
		pushing_me = P.KH.getDirections().length > 0;
	}
}
