package main.constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import main.util.Show;

public class PropertySupporter {
	private final Properties PROP = new Properties();
	private final String PASS;
	public boolean load_failed = false;

	public PropertySupporter(File root) {
		this(root.getPath().concat("/config.properties"));
	}

	public PropertySupporter(int i) {
		StringBuilder sb = new StringBuilder();
		sb.append("save/");
		if (i != -1) {
			sb.append(i);
			sb.append("/");
		}
		sb.append("config.properties");
		PASS = sb.toString();
		try {
			InputStream inputStream = new FileInputStream(new File(PASS));
			PROP.load(inputStream);
		} catch (IOException e) {
			Show.showErrorMessageDialog(e);
			e.printStackTrace();
		}
	}

	public PropertySupporter(int i, String s) {
		StringBuilder sb = new StringBuilder();
		sb.append("save/");
		sb.append(i);
		sb.append("/");
		sb.append(s);
		sb.append(".properties");
		PASS = sb.toString();
		try {
			InputStream inputStream = new FileInputStream(new File(PASS));
			PROP.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PropertySupporter(String pass) {
		PASS = pass;
		boolean failed = false;
		try {
			InputStream inputStream = new FileInputStream(new File(PASS));
			PROP.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			failed = true;
		} catch (IOException e) {
			e.printStackTrace();
			failed = true;
		}
		load_failed = failed;
	}

	public PropertySupporter(String pass, boolean throw_ex) throws IOException {
		PASS = pass;
		InputStream inputStream = new FileInputStream(new File(PASS));
		PROP.load(inputStream);
	}

	public int getProperty(String key) {
		try {
			return Integer.valueOf(PROP.getProperty(key));
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 
	 * @param key
	 * @return Object（キャストなし）
	 */
	public Object getProperty_Nature(String key) {
		try {
			return PROP.getProperty(key);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean isGotPropertyTrue(String key) {
		try {
			return Boolean.parseBoolean(PROP.getProperty(key));
		} catch (Exception e) {
			return false;
		}
	}

	public void saveProperty(String key, Object value) {
		if (value == null) {
			saveProperty(key, -1);
		} else {
			saveProperty(key, value.toString());
		}
	}

	public void saveProperty(String key, String value) {
		PROP.setProperty(key, value);
		try {
			FileOutputStream o = new FileOutputStream(PASS);
			PROP.store(o, null);
			o.close();
		} catch (Exception e) {
			Show.showErrorMessageDialog(e);
			e.printStackTrace();
		}
	}

	public void saveProperty_add(String key, long l) {
		long val = getProperty(key) + l;
		saveProperty(key, val);
	}
}
