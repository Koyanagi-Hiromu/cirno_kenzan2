package dangeon.model.object.creature.npc.second;

import java.awt.Point;

import main.res.BGM;
import main.res.CHARA_IMAGE;
import main.util.BlackOut;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.scene.action.otog.ready.Scene_Otog_Ready;
import dangeon.model.object.creature.npc.Base_NPC;

public class MUSIC_NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String name = "経験値Npc";

	public MUSIC_NPC(Point p) {
		super(p, "永江衣玖", CHARA_IMAGE.永江衣玖, false);
	}

	@Override
	public void message() {
		new Conversation(IMLC, "わたしとダンスを踊るつもりですね？", new ConvEvent() {
			@Override
			protected Book getNo() {
				return new Book() {

					@Override
					protected void work() {
						Message.set("そうですか・・・");
					}
				};
			}

			@Override
			protected Book getYes() {
				return new Book() {

					@Override
					protected void work() {
						BGM.waitUntilFadeOut();
						new BlackOut("", new Task() {
							/**
							 *
							 */
							private static final long serialVersionUID = 1L;

							@Override
							public void work() {
								new Scene_Otog_Ready(Scene_Action.getMe());
							}
						});
					}
				};
			}
		});
		// Scene_Action.getMe().setNextScene();
	}
}
