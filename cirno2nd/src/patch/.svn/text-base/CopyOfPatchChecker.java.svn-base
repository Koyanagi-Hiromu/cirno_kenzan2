package patch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import dangeon.util.Switch;

public class CopyOfPatchChecker {
	/**
	 * サーバーのファイルパス
	 */
	static String patch_url = "http://www.usamimi.info/~crosslodge/patch/patch_check.txt";
	/**
	 * サーバーファイルのダウンロード先
	 */
	static File FILE;
	/**
	 * サーバーファイルのversion
	 */
	static String version;

	/**
	 * 処理部分
	 * 
	 * @param str
	 */
	private static void check(String str) {
		int new_version = Integer.parseInt(str);
		System.out.println(new_version);
		// if (new_version > Switch.version) {
		// Message.set("最新じゃないです");
		// } else {
		// Message.set("最新です");
		// }
	}

	/**
	 * サーバーに有る、patch_check.txtとチェックする message(String)で処理
	 * 
	 */
	public static void patchCheck() {
		patchFileRead();
		check(version);
	}

	/**
	 * 入力部分
	 */
	private static void patchFileRead() {
		URL url;
		try {
			url = new URL(patch_url);
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			File file = new File("./patch.txt"); // 保存先
			FILE = file;
			FileOutputStream out = new FileOutputStream(file, false);
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			out.close();
			in.close();
			versionCheck(FILE);
		} catch (MalformedURLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} // ダウンロードする URL
		catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * 出力部分
	 */
	private static void versionCheck(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str;
			try {
				boolean flag = false;
				while ((str = br.readLine()) != null) {
					System.out.println(str);
					System.out.println(Switch.current_game);
					if (flag) {
						version = str;
						break;
					}
					if (str.matches(Switch.current_game)) {
						flag = true;
						continue;
					}
				}
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
