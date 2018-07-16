package main.res;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import load.Loading;
import main.util.FileReadSupporter;
import dangeon.model.object.artifact.Base_Artifact;

public enum Image_Artifact {
	SCROLL(1), FOOD(2), GOLDEN_FOOD(3), CARD(4, 3, 0, null), GLASS(5), RING(6), STAFF(
			7), DISC(8), BOMB(9), HAKKE(10), KNIFE(11), P_KNIFE(12), BIN(13), RIBBON(
			14), BOOK(15), BOOK2(16),
	// device
	NULL, STAIRS(0, 0, 20)
	// trap
	, trap1_1, trap1_2, trap1_3, trap1_4, trap1_5, trap1_6, trap1_7, trap1_8, trap1_9, trap1_10, trap1_11, trap1_12, trap1_13, trap1_14, trap1_15, trap1_16(
			0, 0, 20), trap1_17
	// trap2
	, pitfall_002, pitfall_003, pitfall_004;

	public static int getIdentifier(Base_Artifact a) {
		for (Image_Artifact ia : values()) {
			if (ia == a.getImage_Artifact()) {
				return ia.identifier;
			}
		}
		return 0;
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

	private final Image[] image;

	public final Integer X, Y, SIZE;

	public final int identifier;

	private static BufferedImage static_temp_load;

	private Image_Artifact() {
		this(null, null, null);
	}

	private Image_Artifact(int i) {
		this(i, null, null, null);
	}

	private Image_Artifact(int i, Integer x, Integer y, Integer size) {
		image = new Image[3];
		for (int j = 0; j < image.length; j++) {
			image[j] = initImage(i, j);
		}
		X = x;
		Y = y;
		SIZE = size;
		identifier = i;
	}

	private Image_Artifact(Integer x, Integer y, Integer size) {
		Loading.setStr(this);
		image = new Image[3];
		image[0] = loadImage("res/image/artifact/".concat(
				this.name().toLowerCase()).concat(".png"));
		image[2] = image[1] = image[0];
		System.out.println("load : " + name());
		X = x;
		Y = y;
		SIZE = size;
		identifier = 0;
	}

	/**
	 * 
	 * @param freeze_step
	 *            0(warm),1(cooled),2(frozen)
	 * @return
	 */
	public Image getImage(int freeze_step) {
		return image[freeze_step];
	}

	private Image initImage(int i, int j) {
		if (static_temp_load == null) {
			static_temp_load = loadImage("res/image/artifact/item.png");
		}
		int s = 50;
		Image im = static_temp_load.getSubimage(s * (i - 1), s * j, s, s);
		if (i == 17) {
			static_temp_load = null;
		}
		return im;
	}
}
