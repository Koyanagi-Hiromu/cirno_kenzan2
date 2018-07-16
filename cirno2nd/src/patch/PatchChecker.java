package patch;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import dangeon.util.Switch;

public class PatchChecker {
	/**
	 * サーバーのファイルパス
	 */
	String patch_url = "http://www.usamimi.info/~crosslodge/patch/patch_check_"
			+ Switch.big_ver + Switch.small_ver + ".txt";

	private String charset = "utf-8";
	private String[] arr;

	public PatchChecker() throws MalformedURLException {
		URL url = new URL(patch_url);
		// Webページを読み込む
		arr = new String[3];
		arr[0] = "すみません、サイトが見つかりません。";
		arr[1] = "プロキシ等のネット環境をお確かめ下さい。";
		arr[2] = "ホームページが移転・閉鎖しているかもしれません。";
		try {

			// 接続
			URLConnection uc = url.openConnection();
			uc.setReadTimeout(5000);
			uc.setConnectTimeout(5000);
			// HTMLを読み込む
			BufferedInputStream bis = new BufferedInputStream(
					uc.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(bis,
					charset));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line + "#end#");
			}
			arr = sb.toString().split("#end#");
		} catch (Exception ex) {
			ex.printStackTrace();
			arr = new String[3];
			arr[0] = "すみません、サイトが見つかりません。";
			arr[1] = "プロキシ等のネット環境をお確かめ下さい。";
			arr[2] = "ホームページが移転・閉鎖しているかもしれません。";
		}
	}

	public String[] get() {
		return arr;
	}
}
