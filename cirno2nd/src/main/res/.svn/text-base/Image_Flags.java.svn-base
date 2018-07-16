package main.res;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.util.FileReadSupporter;
import dangeon.model.map.field.random.Base_Map_Random.Difficulty;

public enum Image_Flags {
	ATK(0), DEF(1), ANY(2), FOOD(3), CARD(4), GLASS(5), RIBBON(6), STAFF(7), ARROW(
			8), BOOK(9);

	public static final BufferedImage image = loadImage("res/image/icon/flags.png");

	public static void drawImage(Graphics2D g, int cols, int rows, int x, int y) {
		int s = 15;
		int x2 = s * rows;
		int y2 = s * (cols + 1);
		g.drawImage(image, x, y, x + s, y + s, x2, y2, x2 + s, y2 + s, null);
	}

	private static BufferedImage loadImage(String filename) {
		try {
			return ImageIO.read(FileReadSupporter.readURL(filename));
		} catch (IOException e) {
			try {
				return ImageIO.read(FileReadSupporter
						.readURL("res/image/floor.png"));
			} catch (IOException e1) {
				return null;
			}
		}
	}

	public final Integer index;

	private Image_Flags(int index) {
		this.index = index;
	}

	public void drawImage(Graphics2D g, Difficulty diff, int x, int y) {
		drawImage(g, diff.index, x, y);
	}

	public void drawImage(Graphics2D g, int index, int x, int y) {

	}

}
