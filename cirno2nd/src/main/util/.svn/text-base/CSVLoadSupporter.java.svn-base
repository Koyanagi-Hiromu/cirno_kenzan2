package main.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import dangeon.latest.scene.action.menu.first.adventure.wiki.Wiki_Enemy;

public class CSVLoadSupporter<E> extends ArrayList<E> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static HashMap<String, Object> getWiki(String orinal_name) {
		ArrayList<CSVLoadSupporter<String>> l = CSVLoadSupporter.loadCSV(
				Wiki_Enemy.class, "\t");
		int cols = 0;
		int index = 0;
		{
			CSVLoadSupporter<String> name_serch = l.get(cols++);
			while (++index < name_serch.size()) {
				String name = name_serch.get(index);
				if (name == null || name.isEmpty()) {
					return null;
				} else if (name.matches(orinal_name)) {
					break;
				}
			}
		}
		String dotter = l.get(cols++).get(index);
		String illust = l.get(cols++).get(index);
		int ex_cols = 7;
		String[][] exs = new String[4][ex_cols];
		for (int i = 0; i < exs.length; i++) {
			for (int j = 0; j < ex_cols; j++) {
				exs[i][j] = l.get(cols++).get(index);
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dot", dotter);
		map.put("illust", illust);
		for (int i = 0; i < 4; i++) {
			map.put("LV" + (i + 1), exs[i]);
		}
		return map;
	}

	/**
	 * 
	 * @param clazz
	 *            __table__.csvと同じ階層にあるクラスを指定すること
	 * @param child
	 *            区切り文字 \tとか
	 * @return 全行読み込み
	 */
	public static ArrayList<CSVLoadSupporter<String>> loadCSV(Class<?> clazz,
			String child) {
		ArrayList<CSVLoadSupporter<String>> array_list = new ArrayList<CSVLoadSupporter<String>>();
		try {
			URL url = clazz.getResource("__table__.csv");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream(), "UTF-8")) {
				@Override
				public String readLine() throws IOException {
					return readLine(1);
				}

				private String readLine(int zero_one) throws IOException {
					String s = super.readLine();
					if (s == null)
						return null;
					int count = 0;
					int i = -1;
					while (++i < s.length() && (i = s.indexOf("\"", i)) != -1) {
						count++;
					}
					if (count % 2 == zero_one) {
						s = s.concat("\n").concat(readLine(0));
					}
					return s.replaceAll("\"", "");
				}
			};
			String line;
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, child, true);
				if (st.hasMoreTokens()) {
					boolean flag = false;
					CSVLoadSupporter<String> list = new CSVLoadSupporter<String>();
					while (st.hasMoreElements()) {
						String s = st.nextToken();
						if (s.matches(child)) {
							if (flag)
								list.add("");
							flag = true;
						} else {
							list.add(s.replaceAll(child, ""));
							flag = false;
						}
					}
					array_list.add(list);
				}
			}
			br.close();
		} catch (Exception e) {
			Show.showCriticalErrorMessageDialog(e);
		}
		return array_list;
	}

	/**
	 * 
	 * @param name
	 *            第一カラム　識別の書、とか
	 * @param clazz
	 *            __table__.csvと同じ階層にあるクラスを指定すること
	 * @param child
	 *            区切り文字 \tとか
	 * @return 区切り文字で区切られた項目をもったarraylist 但し"はエスケープされます
	 */
	public static CSVLoadSupporter<String> loadCSV(String name, Class<?> clazz,
			String child) {
		try {
			URL url = clazz.getResource("__table__.csv");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream(), "UTF-8")) {
				@Override
				public String readLine() throws IOException {
					return readLine(1);
				}

				private String readLine(int zero_one) throws IOException {
					String s = super.readLine();
					if (s == null)
						return null;
					int count = 0;
					int i = -1;
					while (++i < s.length() && (i = s.indexOf("\"", i)) != -1) {
						count++;
					}
					if (count % 2 == zero_one) {
						s = s.concat("\n").concat(readLine(0));
					}
					return s.replaceAll("\"", "");
				}
			};
			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, child, true);
				if (st.hasMoreTokens()) {
					if (st.nextToken().matches(name)) {
						boolean flag = false;
						CSVLoadSupporter<String> list = new CSVLoadSupporter<String>();
						while (st.hasMoreElements()) {
							String s = st.nextToken();
							if (s.matches(child)) {
								if (flag)
									list.add("");
								flag = true;
							} else {
								list.add(s.replaceAll(child, ""));
								flag = false;
							}
						}
						br.close();
						return list;
					}
				}
			}
			br.close();
		} catch (Exception e) {
			Show.showCriticalErrorMessageDialog(e);
		}
		return null;
	}

	private int index = 0;

	/**
	 * if CSVLoadSupporter is needed, call "loadCSV"
	 */
	private CSVLoadSupporter() {
	}

	public E get() {
		return get(index++);
	}

	/**
	 * 
	 * @return true / false / null
	 */
	public Boolean is() {
		int index = this.index++;
		if (isTrue(index))
			return true;
		else if (isFalse(index))
			return false;
		return null;
	}

	public boolean isFalse() {
		return isFalse(index++);
	}

	public boolean isFalse(int index) {
		return get(index).equals("false") || get(index).equals("FALSE");
	}

	public boolean isMatch(int index, String str) {
		return get(index).equals("str");
	}

	public boolean isMatch(String str) {
		return isMatch(index++, str);
	}

	public boolean isTrue() {
		return isTrue(index++);
	}

	public boolean isTrue(int index) {
		return get(index).equals("true") || get(index).equals("TRUE");
	}

	/**
	 * @return キャスト出来ない場合は-1
	 */
	public int toI() {
		return toI(index++);
	}

	/**
	 * @param index
	 * @return キャスト出来ない場合は-1
	 */
	public int toI(int index) {
		try {
			return Integer.valueOf((String) get(index));
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * @return キャスト出来ない場合は-1
	 */
	public long toL() {
		return toL(index++);
	}

	/**
	 * @param index
	 * @return キャスト出来ない場合は-1
	 */
	public long toL(int index) {
		try {
			return Long.valueOf((String) get(index));
		} catch (Exception e) {
			return -1;
		}
	}

}
