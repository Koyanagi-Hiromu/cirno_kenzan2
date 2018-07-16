package main.res;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.util.FileReadSupporter;

public enum Image_MapTip {
	地霊殿, 地霊殿_水, 紅魔館, マヨヒガ, 木造校舎, 永遠亭, 草原, スキマ, 命蓮寺, water, water_ice, 草原_秋, stars;
	private final Image wall[] = new Image[40];

	private final Image floor, holy;

	private Image_MapTip() {
		BufferedImage img = loadImage("res/image/maptip/".concat(name())
				.concat(".png"));
		int w = img.getWidth() / 3 / 2;
		int h = img.getHeight() / 4 / 2;
		int count = 0;
		for (int k = 0; k < 2; k++) {
			for (int l = 0; l < 2; l++) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						wall[count] = img.getSubimage(2 * w * i + (w * k), 2
								* h * (j + 1) + (h * l), w, h);
						count++;
					}
				}
				wall[count] = img.getSubimage(4 * w + (w * k), (h * l), w, h);
				count++;
			}
		}
		holy = img.getSubimage(w * 2, 0, w * 2, h * 2);
		floor = img.getSubimage(0, 0, w * 2, h * 2);
	}

	public Image getFloor() {
		return floor;
	}

	public Image getHap() {
		return holy;
	}

	public Image getHoly() {
		return holy;
	}

	public Image getWall(int index) {
		return wall[index];
	}

	private BufferedImage loadImage(String filename) {
		try {
			return ImageIO.read(FileReadSupporter.readURL(filename));
		} catch (IOException e) {
			main.util.Show.showNotOpenErrorMessageDialog(filename);
			return null;
		}
	}
}
