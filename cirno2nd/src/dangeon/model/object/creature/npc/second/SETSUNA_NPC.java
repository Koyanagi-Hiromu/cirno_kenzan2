package dangeon.model.object.creature.npc.second;

import java.awt.Point;

import main.res.BGM;
import main.res.CHARA_IMAGE;
import main.util.BlackOut;
import main.util.DIRECTION;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.ksg1.Scene_Ksg_Title;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.creature.npc.Abstract_NPC;

public class SETSUNA_NPC extends Abstract_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public SETSUNA_NPC(Point p, DIRECTION left) {
		super(CHARA_IMAGE.魂魄妖夢, "魂魄妖夢", p.x, p.y, false);
		direction = left;
	}

	@Override
	public void message() {
		new Conversation(IMLC, "仕分けを手伝ってくれませんか？", new ConvEvent() {
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
								new Scene_Ksg_Title(Scene_Action.getMe());
							}
						});
					}
				};
			}
		});
		// Scene_Action.getMe().setNextScene();
	}
}
