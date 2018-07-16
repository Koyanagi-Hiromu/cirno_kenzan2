package main.res;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import load.Loading;
import main.thread.MainThread;
import main.util.FileReadSupporter;

public enum Image_Anime {
	explo(17, false), sp_ice(20, true), eye(3, true), Doron(6, false), Doron_Large(
			6, false), ef(5, false), oura(12, false), oura_red(12, false), decurse(
			5, false), bad_down(4, false), good_up(4, false), cirno(4, false), cloud(
			4, false), sound(4, false), autom(4, false), rain(13, false), kanden(
			3, false), thunder(7, false), grass_wave(10, false), camera(3,
			false), mag(7, false), wind(5, true);
	private static final int MAX = 2;
	private static final HashMap<Image_Anime, Image[][]> static_map = new HashMap<Image_Anime, Image[][]>(
			MAX);
	private final int length;
	private final boolean has_lv;

	private int lv = 0;

	private Image_Anime(int length, boolean has_lv) {
		Loading.setStr(this);
		this.length = length;
		this.has_lv = has_lv;
		System.out.println("load : " + name());

	}

	private void check() {
		if (!static_map.containsKey(this)) {
			load();
		}
	}

	/**
	 * 
	 * @return フレーム数に応じたアニメ（１コマ当たり２フレーム）
	 */
	public Image getImage() {
		check();
		return static_map.get(this)[lv][MainThread.getFrame() / 2 % getLength()];
	}

	public Image getImage(int i) {
		check();
		return static_map.get(this)[lv][i];
	}

	public int getLength() {
		check();
		return static_map.get(this)[lv].length;
	}

	private void load() {
		Image[][] image;
		String pass = name().concat(".png");
		BufferedImage bi = FileReadSupporter.readImage("res/image/anime/"
				.concat(pass));
		int w = bi.getWidth();
		if (has_lv) {
			image = new Image[4][length];
			int h = bi.getHeight() / (length * 4);
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < length; j++) {
					image[i][j] = bi.getSubimage(0, h * (length * i + j), w, h);
				}
			}
		} else {
			image = new Image[1][length];
			int h = bi.getHeight() / length;
			for (int j = 0; j < length; j++) {
				image[0][j] = bi.getSubimage(0, h * j, w, h);
			}
		}
		if (static_map.size() == MAX) {
			static_map.remove(static_map.keySet().iterator().next());
		}
		static_map.put(this, image);
	}

	public void setLv(int lv) {
		if (lv == 4)
			lv = 0;
		this.lv = lv;
	}

}
