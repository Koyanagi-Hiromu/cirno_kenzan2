package connection.sv_cl;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import main.util.Show;
import dangeon.model.config.Config;
import dangeon.model.map.ItemFall;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.pot.受信の瓶;
import dangeon.model.object.artifact.item.pot.送信の瓶;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.view.detail.View_Sider;

public class _SocketHolder {
	public static boolean isConnected() {
		return me != NULL;
	}

	public static void submit(Serializable... objs) {
		if (isConnected())
			me.private_submit(objs);
	}

	public static void submit(String... msg) {
		if (isConnected())
			me.private_submit((Serializable[]) msg);
	}

	public static void submitFlagExchange() {
		if (Player.me.getSubmitJarSize() > 0) {
			me.private_submitFlagExchange();
		}
	}

	private final Socket sock;
	private static _SocketHolder NULL = new _SocketHolder();
	private static _SocketHolder me = NULL;

	static void createInstance(ConnectionSubFrame connectionSubFrame,
			Socket accept, boolean server) throws IOException {
		me = new _SocketHolder(connectionSubFrame, accept, server);
		me.submitFirstConnection(connectionSubFrame.getName(),
				server ? connectionSubFrame.getDungeon() : null);
	}

	final Reciever string_reciever;

	private final ObjectOutputStream oos;

	private final ObjectInputStream ois;

	private final CommunicationSubFrame communication_sub_frame;

	private boolean flag_submit_jar;

	private _SocketHolder() {
		oos = null;
		ois = null;
		communication_sub_frame = null;
		string_reciever = null;
		sock = null;
	}

	private _SocketHolder(ConnectionSubFrame connectionSubFrame, Socket accept,
			boolean server) throws IOException {
		sock = accept;
		oos = new ObjectOutputStream(sock.getOutputStream());
		ois = new ObjectInputStream(sock.getInputStream());
		communication_sub_frame = CommunicationSubFrame.create(
				connectionSubFrame.getX(), connectionSubFrame.getY());
		string_reciever = new Reciever() {
			@Override
			public void submitTo(Object recieve) {
				if (recieve instanceof String)
					communication_sub_frame.recieve((String) recieve);
			}
		};
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						recieveObject();
					} catch (SocketTimeoutException e) {
						continue;
					} catch (EOFException e) {
						Show.writeErrorText(e);
						e.printStackTrace();
						terminateConnection("相方が通信エラーを起こした");
						return;
					} catch (SocketException e) {
						Show.writeErrorText(e);
						e.printStackTrace();
						terminateConnection("相方が通信を終了した");
						return;
						// } catch (MyException e) {
						// Show.writeErrorText(e);
						// e.printStackTrace();
						// terminateConnection("入力ストリームが接続されていない");
					} catch (Exception e) {
						Show.writeErrorText(e);
						e.printStackTrace();
						terminateConnection("通信データの取得に失敗した");
						communication_sub_frame.warning(e.getMessage());
						return;
					}
				}

			};
		}.start();
	}

	private Base_Artifact createSubmitItem() {
		送信の瓶 jar = null;
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a instanceof 送信の瓶) {
				jar = (送信の瓶) a;
				break;
			}
		}
		if (jar.getListSize() > 1) {
			return new 受信の瓶(jar);
		} else if (jar.getListSize() == 1) {
			return jar.getFirlstItem();
		} else {
			return null;
		}
	}

	@Override
	protected void finalize() throws Throwable {
		terminateConnection();
		super.finalize();

	}

	private synchronized void private_submit(Serializable... objs) {
		try {
			for (Object string : objs) {
				oos.writeObject(string);
			}
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void private_submitFlagExchange() {
		private_submit(Simbol.create(Simbol.EXCHANGE_OK));
	}

	// void checkExchangeFlag() {
	// if (!flag_submit_jar) {
	// submitJar();
	// }
	// {
	// 送信の瓶 sub_jar = null;
	// for (Base_Artifact a : Belongings.getListItems()) {
	// if (a instanceof 送信の瓶) {
	// sub_jar = (送信の瓶) a;
	// break;
	// }
	// }
	// if (sub_jar == null) {
	// ItemFall.itemFall(Player.me.getMassPoint(), jar);
	// } else {
	// int i = Belongings.getListItems().indexOf(sub_jar);
	// Belongings.remove(sub_jar);
	// if (i != -1 && i < Belongings.getMax()) {
	// Belongings.setItems(jar, i);
	// if (Belongings.getSize() > Belongings.getMax()) {
	// Belongings.remove(jar);
	// ItemFall.itemFall(Player.me.getMassPoint(), jar);
	// }
	// } else {
	// ItemFall.itemFall(Player.me.getMassPoint(), jar);
	// }
	// }
	// View_Sider.setInformation(jar.getColoredName(), "を受け取りました");
	// jar = null;
	// flag_submit_jar = false;
	// }
	// }

	Object readObject() throws Exception {
		Object obj = ois.readObject();
		if (obj == null) {
			throw new Exception();
		}
		return obj;
	}

	private void recieveObject() throws Exception {
		while (true) {
			// 入力ストリームが接続されていない場合、切断処理を行う
			if (ois == null) {
				throw new Exception();
			}
			Object obj = readObject();
			if (obj instanceof Simbol) {
				String s = "";
				Simbol simbol = ((Simbol) obj);
				if (simbol.is(Simbol.FISRT)) {
					s = "FIRST";
					String version = readObject().toString();
					string_reciever.submitTo(readObject());
				} else if (simbol.is(Simbol.EXCHANGE_OK)) {
					s = "READY_TO_EXCHANGE";
					submitJar();
				} else if (simbol.is(Simbol.DUNGEON)) {
					s = "WHICH_DUNGON";
					new DungeonConverter(readObject().toString(),
							(Integer) readObject());
				}
				string_reciever.submitTo("Simbol(" + s + ") recieved.");
			} else if (obj instanceof String) {
				string_reciever.submitTo(obj.toString());
			} else if (obj instanceof Base_Artifact) {
				whenJarRecieved((Base_Artifact) obj);
				// checkExchangeFlag();
			} else if (obj instanceof ImagenPerso) {
				communication_sub_frame.setBackground(((ImagenPerso) obj)
						.getImage());
			}
		}

	}

	private void setInfo(Base_Artifact a, String msg_tail) {
		if (a instanceof 受信の瓶) {
			setInfo((受信の瓶) a, msg_tail);
		} else {
			View_Sider.setInformation(a.getColoredName(), msg_tail);
		}
	}

	private void setInfo(受信の瓶 rec_jar, String msg_tail) {
		for (String s : rec_jar.getListForNames()) {
			View_Sider.setInformation(s, msg_tail);
		}
	}

	private void submitFirstConnection(String name, String map_name) {
		private_submit(Simbol.create(Simbol.FISRT), Config.getValue("ver")
				.toString(), "connected to " + name);
		if (map_name != null) {
			private_submit(Simbol.create(Simbol.DUNGEON), map_name,
					Config.getFate());
			new DungeonConverter(map_name, Config.getFate());
		}

		// Player.me.connectionSuccessed();
	}

	/**
	 * rec_subの後にはOKが出ない
	 */
	private void submitJar() {
		if (!flag_submit_jar && Player.me.getSubmitJarSize() > 0) {
			flag_submit_jar = true;
			Base_Artifact rec_jar = createSubmitItem();
			送信の瓶 sub_jar = null;
			for (Base_Artifact a : Belongings.getListItems()) {
				if (a instanceof 送信の瓶) {
					sub_jar = (送信の瓶) a;
					break;
				}
			}
			Belongings.remove(sub_jar);
			private_submit(rec_jar);
			setInfo(rec_jar, "を送りました");
		}
	}

	private void terminateConnection() {
		terminateConnection("インスタンスが破棄された");
	}

	private void terminateConnection(String s) {
		communication_sub_frame.warning(s + "ため、接続を終了しました");
		try {
			if (sock != null)
				sock.close();
		} catch (Exception e) {
			Show.showErrorMessageDialog(e);
		}
		try {
			if (oos != null) {
				oos.close();
			}
		} catch (Exception e) {
			Show.showErrorMessageDialog(e);
		}
		try {
			if (ois != null)
				ois.close();
		} catch (Exception e) {
			Show.showErrorMessageDialog(e);
		}
		if (communication_sub_frame != null)
			communication_sub_frame.end();
		me = NULL;
	}

	private void whenJarRecieved(Base_Artifact a) {
		if (!flag_submit_jar) {
			submitJar();
		}
		if (Belongings.isMax()) {
			ItemFall.itemFall(Player.me.getMassPoint(), a);
			setInfo(a, "が足元に落ちました");
		} else {
			Belongings.setItems(a);
			setInfo(a, "を受け取りました");
		}
		flag_submit_jar = false;
	}
}
