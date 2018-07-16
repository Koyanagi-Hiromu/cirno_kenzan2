package main.res;

import java.awt.image.BufferedImage;

import main.util.FileReadSupporter;

public enum Image_Number {
	bg("kai", "kaime");
	private final BufferedImage image[] = new BufferedImage[10];
	public final BufferedImage STEP, TIMES;

	private Image_Number(String step) {
		this(step, null);
	}

	private Image_Number(String step, String times) {
		for (int i = 0; i < 10; i++) {
			image[i] = FileReadSupporter.readImage(
					name().concat("/n").concat(String.valueOf(i)), "png");
		}
		STEP = FileReadSupporter.readImage(name().concat("/").concat(step),
				"png");
		if (times == null) {
			TIMES = null;
		} else {
			TIMES = FileReadSupporter.readImage(name().concat("/")
					.concat(times), "png");
		}
	}

	public BufferedImage getImage(int i) {
		if (i > 10) {
			return null;
		} else {
			return image[i];
		}
	}

	public BufferedImage getImage_10th(int i) {
		return getImage_1st(i / 10);
	}

	public BufferedImage getImage_1st(int i) {
		if (i < 0) {
			return null;
		} else {
			return image[i - i / 10 * 10];
		}
	}
}
