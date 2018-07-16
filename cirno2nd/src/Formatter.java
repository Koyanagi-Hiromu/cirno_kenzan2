import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

import main.res.CopyOfCHARA_IMAGE;
import main.util.DIRECTION;

public class Formatter {

	public static void main() throws Exception {
		String pass = "character_dot/";

		for (CopyOfCHARA_IMAGE c : CopyOfCHARA_IMAGE.values()) {
			// {
			// CopyOfCHARA_IMAGE c = CopyOfCHARA_IMAGE.チルノ;
			String PASS = pass.concat(c.name());
			File dir = new File(PASS);
			if (!dir.isDirectory()) {
				dir.mkdir();
			}
			Properties PROP = new Properties();
			{
				BufferedImage img;
				img = (BufferedImage) c.getWalkImage(0, DIRECTION.DOWN, 0);
				if (img == null)
					img = (BufferedImage) c.getWalkImage(1, DIRECTION.DOWN, 0);
				int w = img.getWidth(null);
				int h = img.getHeight(null);
				BufferedImage bi = new BufferedImage(w * 8, h * 3 * 4,
						BufferedImage.TYPE_INT_ARGB_PRE);
				Graphics2D g = bi.createGraphics();
				for (int lv = 0; lv < 4; lv++) {
					for (int i = 0; i < 3; i++) {
						for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
							int d_index = d.ROTATE - 5;
							if (d_index < 0) {
								d_index += 8;
							}
							g.drawImage(c.getWalkImage(lv, d, i), w * d_index,
									h * (lv * 3 + i), null);
						}
					}
				}
				g.dispose();
				ImageIO.write(
						bi,
						"png",
						new File(pass.concat(c.name()).concat("/")
								.concat("walk.png")));
				PROP.setProperty("walk", String.valueOf(3));
				PROP.setProperty("walk_dir", String.valueOf(8));
			}
			{
				BufferedImage img;
				img = (BufferedImage) c.getATKImage(0, DIRECTION.DOWN, 0);
				if (img == null)
					img = (BufferedImage) c.getATKImage(1, DIRECTION.DOWN, 0);
				int w = img.getWidth(null);
				int h = img.getHeight(null);
				BufferedImage bi = new BufferedImage(w * 8, h * 3 * 4,
						BufferedImage.TYPE_INT_ARGB_PRE);
				Graphics2D g = bi.createGraphics();
				for (int lv = 0; lv < 4; lv++) {
					for (int i = 0; i < 3; i++) {
						for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
							int d_index = d.ROTATE - 5;
							if (d_index < 0) {
								d_index += 8;
							}
							g.drawImage(c.getATKImage(lv, d, i), w * d_index, h
									* (lv * 3 + i), null);
						}
					}
				}
				g.dispose();
				ImageIO.write(
						bi,
						"png",
						new File(pass.concat(c.name()).concat("/")
								.concat("atk.png")));
				PROP.setProperty("atk", String.valueOf(3));
				PROP.setProperty("atk_dir", String.valueOf(8));
			}

			{
				if (c.isSPImage()) {
					BufferedImage img = (BufferedImage) c.getSPImage(0, 0);
					int w = img.getWidth(null);
					int h = img.getHeight(null);
					int directions = c.getSPImage().DIRECTION_ONLY_2 ? 1 : 8;
					BufferedImage bi = new BufferedImage(w * directions, h
							* c.getSPImage().LENGTH * 4,
							BufferedImage.TYPE_INT_ARGB_PRE);
					Graphics2D g = bi.createGraphics();
					for (int lv = 0; lv < 4; lv++) {
						for (int i = 0; i < c.getSPImage().LENGTH; i++) {
							if (c.getSPImage().DIRECTION_ONLY_2) {
								g.drawImage(c.getSPImage(lv, i), 0,
										h * (lv * c.getSPImage().LENGTH + i),
										null);
							} else {
								for (DIRECTION d : DIRECTION
										.values_exceptNeatral()) {
									int d_index = d.ROTATE - 5;
									if (d_index < 0) {
										d_index += 8;
									}
									g.drawImage(c.getSPImage(lv, d, i), w
											* d_index, h
											* (lv * c.getSPImage().LENGTH + i),
											null);
								}
							}
						}
					}
					g.dispose();
					ImageIO.write(bi, "png", new File(pass.concat(c.name())
							.concat("/").concat("spec.png")));
					PROP.setProperty("spec",
							String.valueOf(c.getSPImage().LENGTH));
					PROP.setProperty("spec_dir", String.valueOf(directions));
				}

			}
			{
				BufferedImage img;
				img = (BufferedImage) c.getDamImage(0, DIRECTION.DOWN);
				if (img == null)
					img = (BufferedImage) c.getDamImage(1, DIRECTION.DOWN);
				int w = img.getWidth(null);
				int h = img.getHeight(null);
				BufferedImage bi = new BufferedImage(w * 8, h * 4,
						BufferedImage.TYPE_INT_ARGB_PRE);
				Graphics2D g = bi.createGraphics();
				for (int lv = 0; lv < 4; lv++) {
					for (DIRECTION d : DIRECTION.values_exceptNeatral()) {
						int d_index = d.ROTATE - 5;
						if (d_index < 0) {
							d_index += 8;
						}
						g.drawImage(c.getDamImage(lv, d), w * d_index,
								h * (lv), null);
					}
				}
				g.dispose();
				ImageIO.write(
						bi,
						"png",
						new File(pass.concat(c.name()).concat("/")
								.concat("dam.png")));
				PROP.setProperty("dam", String.valueOf(1));
				PROP.setProperty("dam_dir", String.valueOf(8));
			}

			PROP.store(new FileOutputStream(PASS + "/dot.properties"), null);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			main();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
