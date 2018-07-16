package event;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.util.FileReadSupporter;

public class Event_Drawen_Object {
	protected final BufferedImage BI;
	protected final int X, Y, W, H;

	public Event_Drawen_Object(String img, int x, int y) {
		BI = FileReadSupporter.readImage("bg/".concat(img), "png");
		X = x;
		Y = y;
		W = BI.getWidth() / 2;
		H = BI.getHeight() / 2;
	}

	/**
	 * 
	 * @param g
	 * @return true → 削除要求
	 */
	public boolean drawImage(Graphics2D g) {
		g.drawImage(BI, X, Y, W, H, null);
		return false;
	}

}
