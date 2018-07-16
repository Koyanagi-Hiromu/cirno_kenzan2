package main.res;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.util.FileReadSupporter;
import dangeon.model.map.field.random.Base_Map_Random.Difficulty;

public enum Image_Flags_Icon {
	;

	public static final BufferedImage image = loadImage("res/image/icon/flags_icon.png");

	/**
	 * 
	 * @param g
	 * @param rows
	 * @param difficulty
	 *            -1～+2
	 * @param x
	 * @param y
	 */
	public static void drawImage(Graphics2D g, int rows, int difficulty, int x,
			int y) {
		int s = 30;
		int x2 = s * rows;
		int y2 = s * (difficulty + 1);
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

	private Image_Flags_Icon(int index) {
		this.index = index;
	}

	public void drawImage(Graphics2D g, Difficulty diff, int x, int y) {
		drawImage(g, diff.index, x, y);
	}

	public void drawImage(Graphics2D g, int index, int x, int y) {

	}

}
