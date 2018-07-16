package connection.sv_cl;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class ImagenPerso implements Serializable {

	private static final long serialVersionUID = 1L;
	int width;
	int height;
	int[] pixels;

	public ImagenPerso(BufferedImage bi) {
		width = bi.getWidth();
		height = bi.getHeight();
		pixels = new int[width * height];
		int[] tmp = bi.getRGB(0, 0, width, height, pixels, 0, width);
		pixels = tmp;
	}

	public BufferedImage getImage() {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		bi.setRGB(0, 0, width, height, pixels, 0, width);
		return bi;
	}

}