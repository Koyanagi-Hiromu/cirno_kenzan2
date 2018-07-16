package main.res;

import java.awt.image.BufferedImage;

import main.util.FileReadSupporter;

public enum Image_Effect {
	HitA_(3), hit_00(3), hasu_(2), ha_(1);
	public final BufferedImage[] BI;

	private Image_Effect(int size) {
		BI = new BufferedImage[size];
		for (int i = 0; i < BI.length; i++) {
			BI[i] = FileReadSupporter.readImage("effect/".concat(name())
					.concat(String.valueOf(i + 1)), "png");
		}
	}
}
