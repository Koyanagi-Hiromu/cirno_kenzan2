package connection.sv_cl;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public Server(ConnectionSubFrame connectionSubFrame, int port)
			throws Exception {
		// サーバソケットの作成
		ServerSocket svsock = new ServerSocket(port);
		try {
			// accept() のタイムアウトが必要なときに設定する。
			svsock.setSoTimeout(getTimeOut());
			// クライアントからの接続を受け付ける。
			SocketHolder.createInstance(connectionSubFrame, svsock.accept(),
					true);
		} catch (Exception e) {
			if (svsock != null)
				svsock.close();
			throw e;
		}
	}

	public Server(ConnectionSubFrame connectionSubFrame, String hostname,
			int port) throws Exception {
		// ソケットを作成してサーバに接続する。
		Socket sock = new Socket();
		try {
			sock.setSoTimeout(getTimeOut());
			sock.connect(new InetSocketAddress(hostname, port));
			SocketHolder.createInstance(connectionSubFrame, sock, false);
		} catch (Exception e) {
			if (sock != null)
				sock.close();
			throw e;
		}
	}

	private int getTimeOut() {
		int sec = 30;
		int milli = 1000;
		return sec * milli;
	}

}
