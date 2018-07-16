package main.util;

public class 半角全角コンバーター {
	public static String toMaruMoji(int i) {
		switch (i) {
		case 0:
			return "０";
		case 1:
			return "①";
		case 2:
			return "②";
		case 3:
			return "③";
		case 4:
			return "④";
		case 5:
			return "⑤";
		case 6:
			return "⑥";
		case 7:
			return "⑦";
		case 8:
			return "⑧";
		case 9:
			return "⑨";
		}
		return "";
	}

	public static String 全角To半角数字(String s) {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c >= '０' && c <= '９') {
				sb.setCharAt(i, (char) (c - '０' + '0'));
			}
		}
		return sb.toString();
	}

	public static String 半角To全角英字(String value) {
		StringBuilder sb = new StringBuilder(value);
		for (int i = 0; i < sb.length(); i++) {
			int c = sb.charAt(i);
			if ((c >= 0x30 && c <= 0x39) || (c >= 0x41 && c <= 0x5A)
					|| (c >= 0x61 && c <= 0x7A)) {
				sb.setCharAt(i, (char) (c + 0xFEE0));
			}
		}
		value = sb.toString();
		return value;
	}

	public static String 半角To全角数字(int i) {
		return 半角To全角数字(String.valueOf(i));
	}

	public static String 半角To全角数字(long i) {
		return 半角To全角数字(String.valueOf(i));
	}

	public static String 半角To全角数字(String s) {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9') {
				sb.setCharAt(i, (char) (c - '0' + '０'));
			}
		}
		return sb.toString();
	}

}
