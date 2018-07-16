package connection;

import java.awt.Point;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import dangeon.model.object.artifact.item.scrool.バクスイの書;
import dangeon.model.object.artifact.item.scrool.混乱の書;

/**
 * <p>
 * タイトル : Cl
 * </p>
 * <p>
 * 説明 : 簡単クライアントプログラム。
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

public class Cl {

	/**
	 * スタートアップ
	 */
	public static void main(String[] args) {

		for (String string : args) {
			System.err.println(string);
		}
		// 引数をチェックする。
		if (args.length != 2) {
			System.err.println("usage: java Cl host port");
			return;
		}

		try {
			// ソケットを作成してサーバに接続する。
			Socket sock = new Socket();
			sock.connect(new InetSocketAddress(args[0], Integer
					.parseInt(args[1])));
			ObjectOutputStream oos = new ObjectOutputStream(
					sock.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
			Object obj = ois.readObject();
			System.out.println(obj);
			obj = ois.readObject();
			System.out.println(obj);
			System.out.println(ois.readObject());
			oos.writeObject(new 混乱の書(new Point()));
			oos.writeObject(new バクスイの書(new Point()));
			sock.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}