package main.res;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import load.Loading;
import main.util.FileReadSupporter;

public enum Image_Window_Frame {
	GLOW(false), LINE(false),

	header_satiety_bar, header_frame_hp_body, header_frame_hp_variable, header_frame_sat, header_hp_bar, header_frame_hp_end,

	menu_1, menu_2, menu_4, menu_7, menu_8, bg, ;

	public final BufferedImage image;

	private Image_Window_Frame() {
		Loading.setStr(this);
		image = loadImage("res/image/frame/".concat(this.name().toLowerCase())
				.concat(".png"));
		System.out.println("load : " + name());
	}

	private Image_Window_Frame(boolean b) {
		image = null;
	}

	private BufferedImage loadImage(String filename) {
		try {
			return ImageIO.read(FileReadSupporter.readURL(filename));
		} catch (IOException e) {
			return null;
		}
	}

}
