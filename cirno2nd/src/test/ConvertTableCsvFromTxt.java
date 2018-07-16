package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import main.util.FileReadSupporter;

public class ConvertTableCsvFromTxt {
	static String[] dun_name_array = { "テストフィールド" };
	static boolean create = false;

	public static void main(String[] args) {
		if (!create) {
			return;
		}
		for (String dun_name : dun_name_array) {
			InputStreamReader input;
			try {
				// 書き込みファイル
				FileOutputStream file = new FileOutputStream(
						"res/".concat(dun_name.concat(".csv")), false);
				OutputStreamWriter output = new OutputStreamWriter(file,
						"UTF-8");
				// 読み込みファイル
				// エネミーから
				input = FileReadSupporter.readUTF8("res/enemyTable/".concat(
						dun_name).concat(".txt"));
				BufferedReader bf = new BufferedReader(input);

				String str;
				String _s = "ENEMY".concat("\n");
				StringBuffer sb = new StringBuffer(_s);
				int y = 0;
				while ((str = bf.readLine()) != null) {
					y++;
					sb.append(y);
					sb.append("F");
					sb.append("\t");
					sb.append(str.concat("\n"));
				}
				// アイテム開始
				input = FileReadSupporter.readUTF8("res/itemTable/".concat(
						dun_name).concat(".txt"));
				bf = new BufferedReader(input);
				sb.append("\n");
				sb.append("ITEM");
				sb.append("\n");
				while ((str = bf.readLine()) != null) {
					if (!str.startsWith("category")) {
						String[] _str = str.split(",");
						sb.append(_str[0]);
						sb.append("\t");
						sb.append(_str[1]);
						sb.append("\n");
					} else {
						sb.append(str.concat("\n"));
					}
				}
				// トラップ開始
				input = FileReadSupporter.readUTF8("res/trapTable/".concat(
						dun_name).concat(".txt"));
				bf = new BufferedReader(input);
				sb.append("\n");
				sb.append("TRAP");
				sb.append("\n");
				while ((str = bf.readLine()) != null) {
					String[] _str = str.split(",");
					sb.append(_str[0]);
					sb.append("\t");
					sb.append(_str[1]);
					sb.append("\n");
				}

				output.write(sb.toString());
				// 終了
				output.close();
				file.close();
				System.out.println("出力完了");
			} catch (UnsupportedEncodingException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
}
