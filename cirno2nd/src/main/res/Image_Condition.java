package main.res;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import load.Loading;
import main.thread.MainThread;
import main.util.BeautifulView;
import main.util.FileReadSupporter;
import dangeon.controller.TurnSystemController;
import dangeon.model.condition.CONDITION;
import dangeon.model.object.creature.Base_Creature;

public enum Image_Condition {
	strange_piyo(5, true), strange_dodo(3, true, false, 20, -3), strange_sibi(
			3, false, false, 0, -5), strange_zzz(3, true, false, 15, 0), strange_seal(
			2, true, false, 0, 0), strange_slow(3, true, true, 0, 5, 2), strange_death(
			4, true), strange_death_num(10, true), strange_kagenui(3, false,
			true, -4, 13), strange_fire(3, false, true, 0, -19), strange_dark(
			1, false, false, 0, -16), strange_ejanaika(3, true, false, 0, 15), strange_amjk(
			3, true, true, 0, 0, 2);

	public static Image_Condition get(String string) {
		if (string == null)
			return null;
		for (Image_Condition c : values()) {
			if (c.name().matches(string))
				return c;
		}
		return null;
	}

	private final Image[] image;
	private final boolean HEAD, ROTATE;

	private final int X_DIF, Y_DIF, DELAY;

	private Image_Condition(int length, boolean head) {
		this(length, head, false, 0, 0);
	}

	private Image_Condition(int length, boolean head, boolean rotate,
			int x_diff, int y_diff) {
		this(length, head, rotate, x_diff, y_diff, 1);
	}

	private Image_Condition(int length, boolean head, boolean rotate,
			int x_diff, int y_diff, int delay) {
		HEAD = head;
		ROTATE = rotate;
		DELAY = delay;
		X_DIF = x_diff;
		Y_DIF = y_diff;
		Loading.setStr(this);
		image = new Image[length];
		StringBuilder sb = new StringBuilder();
		sb.delete(0, sb.length());
		sb.append("res/image/anime/");
		sb.append("strange");
		sb.append("/");
		sb.append(name());
		sb.append(".png");
		BufferedImage bi = loadImage(sb.toString());
		int h = bi.getHeight() / length;
		for (int j = 0; j < image.length; j++) {
			image[j] = bi.getSubimage(0, h * j, bi.getWidth(), h);
		}
		System.out.println("load : " + name());
	}

	public void draw(Graphics2D g, int x, int y, Base_Creature c) {
		if (HEAD) {
			y += c.getHeadY();
		}
		x += X_DIF;
		y += Y_DIF;
		if (this == strange_fire) {
			BeautifulView.setAlphaOnImg(g, 0.2f);
			g.drawImage(getImage(), x, y, null);
			BeautifulView.setAlphaOnImg(g, 1f);
		} else if (this == strange_dark) {
			BeautifulView.setAlphaOnImg(g, MainThread.getFrame() / 3 / DELAY
					% 2 * 0.3f + 0.5f);
			g.drawImage(getImage(), x, y, null);
			BeautifulView.setAlphaOnImg(g, 1f);
		} else if (this == strange_ejanaika) {
			int del = MainThread.getFrame() / 12 % 2 == 0 ? 5 : -31;
			g.drawImage(getImage(), x + del, y, null);
		} else {
			g.drawImage(getImage(), x, y, null);
		}
		if (this == strange_death) {
			int t = c.getConditionTurn(CONDITION.死);
			if (t < 10) {
				g.drawImage(strange_death_num.image[t], x, y, null);
			} else {
				int tenth = t / 10, ones = t - tenth * 10;
				g.drawImage(strange_death_num.image[tenth], x - 4, y, null);
				g.drawImage(strange_death_num.image[ones], x + 4, y, null);
			}
		}
	}

	/**
	 * 
	 * @return フレーム数に応じたアニメ（１コマ当たり２フレーム）
	 */
	public Image getImage() {
		if (TurnSystemController.time_stop_count > 0) {
			return image[0];
		}
		if (ROTATE) {
			int len = image.length * 2 - 2;
			int i = MainThread.getFrame() / 3 / DELAY % len;
			if (i >= image.length) {
				i = len - i;
			}
			return image[i];
		} else {
			return image[MainThread.getFrame() / 3 / DELAY % image.length];
		}
	}

	public int getLength() {
		return image.length;
	}

	private BufferedImage loadImage(String filename) {
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
}
