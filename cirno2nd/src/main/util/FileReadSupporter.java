package main.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import dangeon.model.map.PresentField;

public class FileReadSupporter {
	private static String base() {
		return "res/";
	}

	private static String imageBase() {
		return base().concat("image/");
	}

	public static String mapBase() {
		return base().concat("map/");
	}

	public static BufferedImage readImage(String path) {
		try {
			return ImageIO.read(readURL(path));
		} catch (IOException e) {
			Show.showCriticalErrorMessageDialog(path, "は開けません");
			return null;
		}
	}

	/**
	 * 
	 * @param name
	 * @param code
	 *            ドット(.)はあってもなくてもいい
	 * @return
	 */
	public static BufferedImage readImage(String name, String code) {
		try {
			return ImageIO.read(readImageURL(name, code));
		} catch (IOException e) {
			Show.showCriticalErrorMessageDialog(readImageURL(name, code)
					.toString(), "は開けません");
			return null;
		}
	}

	/**
	 * 
	 * @param name
	 * @param code
	 *            ドット(.)はあってもなくてもいい
	 * @return
	 */
	public static File readImageURL(String name, String code) {
		if (!code.contains(".")) {
			return readURL(imageBase(), name, ".", code);
		}
		return readURL(imageBase(), name, code);
	}

	public static BufferedImage readPNGImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			Show.showCriticalErrorMessageDialog(path, "は開けません");
			return null;
		}
	}

	private static File readURL(String... url) {
		StringBuilder sb = new StringBuilder();
		for (String str : url) {
			sb.append(str);
		}
		return readURL(sb.toString());
	}

	public static File readURL(String name) {
		// File f = new File(name);
		// if (f.exists())
		// return f;
		name = name.replace(".png", ".dat");
		return new File(name);
	}

	/**
	 * 
	 * @param url
	 * @param code
	 *            ドット(.)はあってもなくてもいい
	 * @return
	 */
	public static InputStreamReader readUTF8(String url)
			throws UnsupportedEncodingException, FileNotFoundException {
		return readUTF8File(url);
	}

	/**
	 * 
	 * @param url
	 * @param code
	 *            ドット(.)はあってもなくてもいい
	 * @return
	 */
	public static InputStreamReader readUTF8(String url, String code)
			throws UnsupportedEncodingException, FileNotFoundException {
		if (!code.contains(".")) {
			return readUTF8File(url, ".", code);

		}
		return readUTF8File(url, code);
	}

	private static InputStreamReader readUTF8File(String... url)
			throws UnsupportedEncodingException, FileNotFoundException {
		StringBuilder sb = new StringBuilder();
		for (String str : url) {
			sb.append(str);
		}
		try {
			return new InputStreamReader(new FileInputStream(sb.toString()),
					"UTF-8");
			// return new InputStreamReader(new readURL(sb.toString()),
			// "UTF-8");
		} catch (IOException e) {
			Show.showCriticalErrorMessageDialog(e);
			return null;
		}
	}

	public static Reader readUTF8Map() throws UnsupportedEncodingException,
			FileNotFoundException {
		return readUTF8File(PresentField.get().getFileName());
	}

}
