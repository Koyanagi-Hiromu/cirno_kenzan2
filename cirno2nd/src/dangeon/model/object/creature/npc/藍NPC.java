package dangeon.model.object.creature.npc;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.util.半角全角コンバーター;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.object.creature.player.strage.RAN_Strage;

public class 藍NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public 藍NPC(Point p) {
		super(p, "藍", CHARA_IMAGE.八雲藍, false);
		RAN_Strage.setMe();
	}

	private String getMsg() {
		if (RAN_Strage.me.isMax()) {
			return "悪いけどもう倉庫は一杯だよ@";
		} else {
			return "アイテムをあと"
					+ 半角全角コンバーター.半角To全角数字(RAN_Strage.me.getEmptyNumber())
					+ "コ預かれるぞ";
		}
	}

	@Override
	public void message() {
		new Conversation(Image_LargeCharacter.八雲藍, getMsg(), new ConvEvent() {
			@Override
			protected Book getContent1() {
				return new Book("預ける") {
					@Override
					protected void work() {
						RAN_Strage.pushEnter(0);
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("引き出す") {
					@Override
					protected void work() {
						RAN_Strage.pushEnter(1);
					}
				};
			}

			@Override
			protected Book getContent3() {
				return new Book("全部預ける") {
					@Override
					protected void work() {
						RAN_Strage.pushEnter(2);
					}
				};
			}

			@Override
			protected Book getContent4() {
				return new Book("やめる") {
					@Override
					protected void work() {
						pushCancelAction();
					}
				};
			}
		});
	}

	public void save() {
		RAN_Strage.me.save();
	}

}
