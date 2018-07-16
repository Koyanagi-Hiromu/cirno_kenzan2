package main.res;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import load.Loading;
import main.util.FileReadSupporter;

public enum Image_Object {
	ice_block, cir_eating, cir_damage_eye, cir_damage_L_eye, cir_damage_R_eye, cir_damage, cir_norma1, cir_norma2, cir_normal3, 賽銭箱, 身代わり人形, suika, jewel_aqua, jewel_green, jewel_perple, jewel_red, jewel_yellow, takara;
	private final BufferedImage image;

	private Image_Object() {
		Loading.setStr(this);
		image = loadImage("res/image/object/".concat(this.name().toLowerCase())
				.concat(".png"));
		System.out.println("load : " + name());
	}

	public BufferedImage getImage() {
		return image;
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
