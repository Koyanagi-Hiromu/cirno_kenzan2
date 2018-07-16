package main.res;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import load.Loading;
import main.util.DIRECTION;
import main.util.FileReadSupporter;

public enum BulletImage {
	MagicBullet("MagicBullet_", 3), 御柱("onbashira", 1), アンカー("アンカー", 1), fireball(
			"fireball_", 3, false), knife("knife", 1), p_knife("p_knife", 1), キスメ(
			"kiss", 1), eye_beam("eye_beam_00", 1), thunder_shot(
			"thunder_shot_", 3);
	private final Image[][] IMAGE;
	public final int ANIME;

	private final boolean HAS_DIRECTION;

	private BulletImage(String name, int anime) {
		Loading.setStr(this);
		HAS_DIRECTION = true;
		ANIME = anime;
		IMAGE = new Image[8][anime];
		for (DIRECTION d : DIRECTION.values()) {
			if (d == DIRECTION.NEUTRAL) {
				continue;
			}
			for (int i = 0; i < anime; i++) {
				if (anime == 1) {
					IMAGE[d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
							name, d.NUM));
				} else {
					IMAGE[d.getIndexFrom0ExceptNeautral()][i] = loadImage(getURL(
							name, d.NUM, i));
				}
			}
		}
	}

	private BulletImage(String name, int anime, boolean b) {
		Loading.setStr(this);
		HAS_DIRECTION = b;
		ANIME = anime;
		IMAGE = new Image[1][anime];
		for (int i = 0; i < anime; i++) {
			IMAGE[0][i] = loadImage(getURL(name, i + 1));
		}
	}

	public Image getImage(DIRECTION d) {
		if (HAS_DIRECTION) {
			return IMAGE[d.getIndexFrom0ExceptNeautral()][0];
		} else {
			return IMAGE[0][0];
		}
	}

	public Image getImage(DIRECTION d, int index) {
		if (HAS_DIRECTION) {
			return IMAGE[d.getIndexFrom0ExceptNeautral()][index];
		} else {
			return IMAGE[0][index];
		}
	}

	public Image getImage(int index) {
		return IMAGE[0][index];
	}

	private String getURL(String name, int i) {
		StringBuilder sb = new StringBuilder();
		sb.append("res/image/artifact/bullet/");
		sb.append(name());
		sb.append("/");
		sb.append(name);
		sb.append(i);
		sb.append(".png");
		return sb.toString();
	}

	private String getURL(String name, int i, int j) {
		StringBuilder sb = new StringBuilder();
		sb.append("res/image/artifact/bullet/");
		sb.append(name());
		sb.append("/");
		sb.append(name);
		sb.append(i);
		sb.append("_");
		sb.append(j + 1);
		sb.append(".png");
		System.out.println(sb.toString());
		return sb.toString();
	}

	private BufferedImage loadImage(String filename) {
		try {
			return ImageIO.read(FileReadSupporter.readURL(filename));
		} catch (IOException e) {
			return null;
		}
	}
}
