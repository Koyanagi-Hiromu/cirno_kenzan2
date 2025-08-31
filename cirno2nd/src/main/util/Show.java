package main.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.swing.JOptionPane;

import dangeon.model.config.Config;
import main.Second_Firster;

public class Show {
	private static void set(boolean b) {
		Second_Firster.ME.setEnabled(b);
		Second_Firster.ME.setVisible(b);
	}

	/**
	 * 　OK_CANCEL選択ダイアログを表示するメソッドです．
	 * 
	 * @param message
	 *            エラーメッセージ
	 * @return 0 : OK
	 */
	public static int showConfirmDialog(String message) {
		// エラーダイアログの表示
		set(false);
		int i = JOptionPane.showConfirmDialog(Second_Firster.ME, message,
				"Confirm", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE);
		set(true);
		return i;
	}

	public static void showCriticalErrorMessageDialog(Exception e) {
		writeErrorText(e);
		showCriticalErrorMessageDialog("Error", e.toString());
	}

	public static void showCriticalErrorMessageDialog(Exception e, String title) {
		writeErrorText(e);
		showCriticalErrorMessageDialog(title, e.toString());

	}

	/**
	 * 警告ダイアログを表示するメソッドです． 表示後フレームを強制終了します
	 * 
	 * @param string
	 */
	public static void showCriticalErrorMessageDialog(String message) {
		showWarningMessageDialog(message);
		writeErrorText(new Exception(message));
		System.exit(0);
	}

	public static void showCriticalErrorMessageDialog(String... message) {
		StringBuilder sb = new StringBuilder();
		for (String str : message) {
			sb.append(str);
			sb.append("\n");
		}
		showCriticalErrorMessageDialog(sb.toString());
		System.exit(0);
	}

	/**
	 * エラーダイアログを表示するメソッドです．
	 * 
	 * @param message
	 *            エラーメッセージ
	 */
	public static void showErrorMessageDialog(Exception e) {
		writeErrorText(e);
		// エラーダイアログの表示
		if (showConfirmDialog(e.toString().concat("\n\n大変申し訳ございません。エラーが発生しました。\n内容をerror.txtに出力しました\n左側のボタンを押すと続行できる可能性があります")) != 0) {
			System.exit(0);
		}
	}

	/**
	 * エラーダイアログを表示するメソッドです．
	 * 
	 * @param message
	 *            エラーメッセージ
	 */
	public static void showErrorMessageDialog(String message) {
		writeErrorText(new Exception(message));
		// エラーダイアログの表示
		if (showConfirmDialog(message.concat("\n\n大変申し訳ございません。エラーが発生しました。\n内容をerror.txtに出力しました\n左側のボタンを押すと続行できる可能性があります")) != 0) {
			System.exit(0);
		}
	}

	/**
	 * 情報ダイアログを表示するメソッドです．
	 * 
	 * @param message
	 *            メッセージ
	 */
	public static void showInformationMessageDialog(String message) {
		System.out.println(message);
		// 情報ダイアログの表示
		set(false);
		JOptionPane.showMessageDialog(Second_Firster.ME, (message),
				"Information...", JOptionPane.INFORMATION_MESSAGE);
		set(true);
	}

	/**
	 * 情報ダイアログを表示するメソッドです．
	 * 
	 * @param message
	 *            メッセージ
	 * @param title
	 *            タイトル
	 */
	public static void showInformationMessageDialog(String message, String title) {
		// SE.DRAW.play();
		// 情報ダイアログの表示
		set(false);
		JOptionPane.showMessageDialog(Second_Firster.ME, message, title,
				JOptionPane.INFORMATION_MESSAGE);
		set(true);

	}

	/**
	 * ファイルが開かないことをエラーダイアログで表示するメソッドです．
	 * 
	 * @param filename
	 *            開けなかったファイル名
	 */
	public static void showNotOpenErrorMessageDialog(String filename) {
		try {
			showErrorMessageDialog("ファイル".concat(filename).concat(
					"が開けません¥n削除されたか編集中でないか確認してください"));
		} catch (NullPointerException e) {
			showErrorMessageDialog("ファイルを開く指示ですが、そもそもファイルが指定されていません(null)¥nプログラム上のバグの可能性が高いです");
		}

	}

	/**
	 * 警告ダイアログを表示するメソッドです．
	 * 
	 * @param message
	 *            警告メッセージ
	 */
	public static void showWarningMessageDialog(String message) {
		showWarningMessageDialog(message, "Warning...");
	}

	/**
	 * 警告ダイアログを表示するメソッドです．
	 * 
	 * @param message
	 *            警告メッセージ
	 */
	public static void showWarningMessageDialog(String message, String title) {
		set(false);
		JOptionPane.showMessageDialog(Second_Firster.ME, (message), title,
				JOptionPane.WARNING_MESSAGE);
		set(true);
	}

	public static void writeErrorText(Error e) {
		e.printStackTrace();
		if (Config.isTest()) {
			return;
		}
		try {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			// これで、StringWriterクラスのtoString()メソッドで
			// スタックトレースを取得できます。
			File file = new File("error.txt");
			java.io.FileWriter filewriter = new java.io.FileWriter(file, true);
			filewriter.write("【");
			filewriter.write(new Date().toString());
			filewriter.write("】");
			filewriter.write("\r\n");
			filewriter.write(stringWriter.toString());
			filewriter.write("\r\n");
			filewriter.close();
		} catch (IOException e1) {
		}
	}

	public static void writeErrorText(Exception e) {
		e.printStackTrace();
		if (Config.isTest()) {
			return;
		}
		try {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			// これで、StringWriterクラスのtoString()メソッドで
			// スタックトレースを取得できます。
			File file = new File("error.txt");
			java.io.FileWriter filewriter = new java.io.FileWriter(file, true);
			filewriter.write("【");
			filewriter.write(new Date().toString());
			filewriter.write("】");
			filewriter.write("\r\n");
			filewriter.write(stringWriter.toString());
			filewriter.write("\r\n");
			filewriter.close();
		} catch (IOException e1) {
		}
	}

}
