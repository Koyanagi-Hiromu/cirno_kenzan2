package main.res;

import java.awt.image.BufferedImage;

import main.util.FileReadSupporter;

public enum Image_Dungeon_Name {
	youseinoodoriba(0), shucchou_teragoya(5), keine_no_kyoushitu(10), inseki_ihen(
			20), keine_no_saishuumondai(30), danjon_no_sukima(35), hakureinaka_kekkai(
			40), saikyou_no_michi(50), tei_no_mondai(-10);
	public final int NO;
	private static Image_Dungeon_Name now;
	private static BufferedImage image;

	public static BufferedImage getImage(int i) {
		for (Image_Dungeon_Name idn : values()) {
			if (idn.NO == i) {
				return idn.getImage();
			}
		}
		return null;
	}

	private Image_Dungeon_Name(int no) {
		NO = no;
	}

	public BufferedImage getImage() {
		if (now != this) {
			load();
		}
		return image;
	}

	private void load() {
		now = this;
		image = FileReadSupporter.readImage("bg/".concat(name()), "png");
	}
}
