package dangeon.model.object.creature.npc.second;

import java.awt.Color;
import java.awt.Point;

import main.res.CHARA_IMAGE;
import connection.sv_cl.ConnectionSubFrame;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.creature.npc.Base_NPC;

public class NPC神奈子 extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NPC神奈子(int x, int y) {
		super(new Point(x, y), "八坂神奈子", CHARA_IMAGE.八坂神奈子, false);
	}

	public NPC神奈子(Point point) {
		this(point.x, point.y);
	}

	private void confirmB() {
		String msg = "「ぱられるわーるどとどーき」するのか？$" + Color.GRAY.toString()
				+ "（シェアウィンドウを開きます）";
		new Conversation(IMLC, msg, new ConvEvent() {
			@Override
			protected Book getYes() {
				return new Book() {

					@Override
					protected void work() {
						Message.set("開いたぞ");
						ConnectionSubFrame.createInstance();
					}
				};
			}
		});
	}

	@Override
	public void message() {
		confirmB();
	}
}
