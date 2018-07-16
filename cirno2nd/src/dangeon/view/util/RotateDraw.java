package dangeon.view.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import main.thread.MainThread;

public class RotateDraw {
	public static void draw(Graphics2D g, Image img, int x, int y) {
		int w = img.getWidth(null) / 2;
		int h = img.getWidth(null) / 2;
		draw(g, img, x, y, w, h);
	}

	public static void draw(Graphics2D g, Image image, int x, int y, int w,
			int h) {
		draw(g, image, x, y, w, h, 2);
	}

	public static void draw(Graphics2D g, Image image, int x, int y, int w,
			int h, double angle) {
		AffineTransform firts_at = g.getTransform();
		AffineTransform at = new AffineTransform(firts_at);
		at.translate(x + w / 2, y + h / 2);
		at.rotate(angle);
		g.setTransform(at);
		g.drawImage(image, -w / 2, -h / 2, w, h, null);
		g.setTransform(firts_at);
	}

	public static void draw(Graphics2D g, Image image, int x, int y, int w,
			int h, int wait) {
		AffineTransform firts_at = g.getTransform();
		AffineTransform at = new AffineTransform(firts_at);
		at.translate(x + w / 2, y + h / 2);
		int coma = 12;
		at.rotate(Math.PI * 2 / coma * (MainThread.getFrame() / wait % coma));
		g.setTransform(at);
		g.drawImage(image, -w / 2, -h / 2, w, h, null);
		g.setTransform(firts_at);
	}

	public static void draw(Graphics2D g, String str, int x, int y, int angle) {
		int s = g.getFont().getSize();
		AffineTransform firts_at = g.getTransform();
		AffineTransform at = new AffineTransform(firts_at);
		at.translate(x + s / 2, y + s / 2);
		at.rotate(Math.PI * 2 * angle / 360);
		g.setTransform(at);
		StringFilter.drawString(g, str, 0, 0);
		g.setTransform(firts_at);
	}

}
