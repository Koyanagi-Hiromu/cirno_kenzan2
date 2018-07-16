package connection.sv_cl;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import main.res.SE;
import main.util.Show;
import dangeon.model.config.Config;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.pot.受信の瓶;
import dangeon.model.object.artifact.item.pot.送信の瓶;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;
import dangeon.view.detail.View_Sider;

public class SocketHolder {
	private static SocketHolder NULL = new SocketHolder();

	private static SocketHolder me = NULL;

	static void createInstance(ConnectionSubFrame connectionSubFrame,
			Socket accept, boolean server) throws IOException {
		me = new SocketHolder(connectionSubFrame, accept, server);
		me.submitFirstConnection(connectionSubFrame.getName(),
				server ? connectionSubFrame.getDungeon() : null);
	}

	public static void endPlayer() {
		if (Player.me.getSubmitJarSize() > 0) {
			me.submitItems();
		}
	}

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

	private final Socket sock;

	final Reciever string_reciever;

	private final ObjectOutputStream oos;

	private final ObjectInputStream ois;

	private final CommunicationSubFrame communication_sub_frame;

	private SocketHolder() {
		oos = null;
		ois = null;
		communication_sub_frame = null;
		string_reciever = null;
		sock = null;
	}

	private SocketHolder(ConnectionSubFrame connectionSubFrame, Socket accept,
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
		// if (obj == null) {
		// throw new Exception();
		// }
		return obj;
	}

	private void recieveItem(Base_Item a) {
		SE.SYSTEM_PICKUP.play();
		Belongings.setItems(a);
		setInfo(a, "を受け取りました（アイテム欄に追加されました）");
	}

	private void recieveObject() throws Exception {
		while (true) {
			// 入力ストリームが接続されていない場合、切断処理を行う
			if (ois == null) {
				throw new Exception();
			}
			Object obj = readObject();
			if (obj == null)
				;
			else if (obj instanceof Simbol) {
				String s = "";
				Simbol simbol = ((Simbol) obj);
				if (simbol.is(Simbol.FISRT)) {
					s = "FIRST";
					String version = readObject().toString();
					string_reciever.submitTo(readObject());
					if (!version.equals(Config.getValue("ver"))) {
						string_reciever.submitTo("*VERSION MISS MATCHED*");
						terminateConnection("お互いのバージョンが違う");
						return;
					}
				} else if (simbol.is(Simbol.DUNGEON)) {
					s = "WHICH_DUNGON";
					new DungeonConverter(readObject().toString(),
							(Integer) readObject());
				}
				string_reciever.submitTo("Simbol(" + s + ") recieved.");
			} else if (obj instanceof String) {
				string_reciever.submitTo(obj.toString());
			} else if (obj instanceof Base_Item) {
				recieveItem((Base_Item) obj);
				// checkExchangeFlag();
			} else if (obj instanceof ImagenPerso) {
				communication_sub_frame.setBackground(((ImagenPerso) obj)
						.getImage());
			} else {
				string_reciever.submitTo(obj.toString());
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

	private void submitItems() {
		送信の瓶 jar = null;
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a instanceof 送信の瓶) {
				jar = (送信の瓶) a;
				break;
			}
		}
		if (jar == null) {
			return;
		}
		for (Base_Artifact a : jar.getContentList()) {
			if (a instanceof Base_Item) {
				private_submit(a);
				setInfo(a, "を送りました");
			} else {
				setInfo(a, "は送れませんでした");
			}
		}
		Belongings.remove(jar);
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

}
