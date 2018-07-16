package connection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerSide {
	public ServerSide() {
		int port; // ポート番号
		int timeout_msec = 10000; // accept() のタイムアウトは10秒にしてみる。

		port = 50000; // 例えばポート番号は 50000 番にする。
		try {
			// サーバソケットの作成
			ServerSocket svsock = new ServerSocket(port);
			svsock.setSoTimeout(timeout_msec); // ※必要があるときのみ設定する。
			// クライアントからの接続を受け付ける。
			// ※クライアントからの接続がくるまでここでブロックする。
			Socket sock = svsock.accept();

			// クライアントと接続されたソケットを利用して処理を行う。
			// ソケットに出力する。
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());
			out.writeBytes("Hello!\n");
			// 後始末する。
			sock.close();
		} catch (SocketTimeoutException e) {
			// accept() タイムアウト時の処理をする。
			// ...
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
