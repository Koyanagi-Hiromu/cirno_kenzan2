package main.res;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import load.Loading;
import main.util.FileReadSupporter;
import dangeon.model.condition.CONDITION;

public enum Image_Icon {
	size(null), stt_icon_piypiy(CONDITION.混乱), stt_icon_quick(CONDITION.倍速), stt_icon_quick0(
			null), stt_icon_quick3(CONDITION.三倍速), stt_icon_sibibi(CONDITION.麻痺), stt_icon_death(
			CONDITION.死), stt_icon_kagenui(CONDITION.影縫い), stt_icon_koishi(
			CONDITION.透明), stt_icon_kutinashi(CONDITION.封印), stt_icon_megusuri(
			CONDITION.目薬), stt_icon_mokou(CONDITION.復活), stt_icon_power(
			CONDITION.パワーアップ), stt_icon_rethi(CONDITION.回復), stt_icon_satori(
			CONDITION.絶対回避), stt_icon_slow(CONDITION.鈍足), stt_icon_toushi(
			CONDITION.透視), stt_icon_wrigle(CONDITION.蛍), stt_icon_yuugi(
			CONDITION.超会心), stt_icon_zzz(CONDITION.睡眠), stt_icon_reflect(
			CONDITION.反射), stt_icon_rumia(CONDITION.やりすごし), stt_icon_ran(
			CONDITION.イリュージョン), stt_icon_futo(CONDITION.絶対必中), stt_icon_seiga(
			CONDITION.邪法), stt_icon_sanae(CONDITION.おにぎり), stt_icon_pal(
			CONDITION.必中会心), stt_icon_enjo(CONDITION.炎上);
	public final static int SIZE = 30;

	public static BufferedImage getImage(CONDITION con) {
		for (Image_Icon ii : values()) {
			if (con.equals(ii.CON)) {
				return ii.getImage();
			}
		}
		return null;
	}

	private final BufferedImage image;

	private final CONDITION CON;

	private Image_Icon(CONDITION con) {
		CON = con;
		Loading.setStr(this);
		image = loadImage("res/image/icon/stt/".concat(
				this.name().toLowerCase()).concat(".png"));
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
