package connection;

import java.awt.Point;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import dangeon.model.object.artifact.item.scrool.バクスイの書;
import dangeon.model.object.artifact.item.scrool.混乱の書;

/**
 * <p>
 * タイトル : Sv
 * </p>
 * <p>
 * 説明 : 簡単サーバプログラム。
 * </p>
 * <p>
 * <dl>
 * <dt>更新履歴 :</dt>
 * <dd>
 * <table>
 * <tr>
 * <td>1.0</td>
 * <td>新規作成。</td>
 * </tr>
 * </table>
 * </dd>
 * </dl>
 * </p>
 * 
 * @author bono
 * @version 1.0
 * @since 1.0
 */

public class Sv implements Runnable {

	/**
	 * スタートアップ
	 */
	public static void main(String[] args) {

		// 引数をチェックする。
		if (args.length != 1) {
			System.err.println("usage: java Sv port");
			return;
		}

		try {
			// サーバソケットの作成
			ServerSocket svsock = new ServerSocket(Integer.parseInt(args[0]));
			// svsock.setSoTimeout(10000); // accept() のタイムアウトが必要なときに設定する。
			for (;;) {
				// クライアントからの接続を受け付ける。
				Socket sock = svsock.accept();

				// クライアントとの入出力部分はスレッドとして起動する。
				Sv sv = new Sv(sock);
				Thread tr = new Thread(sv);
				tr.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Socket sock_ = null;

	/**
	 * コンストラクタ
	 * 
	 * @param sock
	 *            クライアントと接続済みのソケットを渡す。
	 */
	public Sv(Socket sock) {
		this.sock_ = sock;
	}

	/**
	 * ファイナライザ 念のためソケットの破棄を行っている。
	 */
	@Override
	protected void finalize() {
		try {
			sock_.close();
		} catch (Exception e) {
		}
	}

	/**
	 * クライアントに対し文字列を送信する。
	 */
	@Override
	public void run() {
		try {

			// DataOutputStream out = new
			// DataOutputStream(sock_.getOutputStream());
			ObjectOutputStream oos = new ObjectOutputStream(
					sock_.getOutputStream());
			oos.writeObject(new 混乱の書(new Point()));
			oos.writeObject(new バクスイの書(new Point()));
			oos.writeObject("aaaa");
			ObjectInputStream ois = new ObjectInputStream(
					sock_.getInputStream());
			Object obj = ois.readObject();
			System.out.println(obj);
			obj = ois.readObject();
			System.out.println(obj);
			sock_.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}