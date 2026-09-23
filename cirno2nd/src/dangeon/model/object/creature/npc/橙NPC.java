package dangeon.model.object.creature.npc;

import java.awt.Point;

import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.object.creature.player.strage.CHEN_Strage;
import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;

/**
 * スタック倉庫（種類ごとに999個まで）の倉庫番
 */
public class 橙NPC extends Base_NPC {

	private static final long serialVersionUID = 1L;

	public 橙NPC(Point p) {
		super(p, "橙", CHARA_IMAGE.橙, false);
		CHEN_Strage.setMe();
	}

	private String getMsg() {
		return "アイテムを種類ごとに預かるよ$" + "預けたアイテムは素の状態になっちゃうから気をつけて";
	}

	@Override
	public void message() {
		new Conversation(Image_LargeCharacter.橙, getMsg(), new ConvEvent() {
			@Override
			protected Book getContent1() {
				return new Book("預ける") {
					@Override
					protected void work() {
						CHEN_Strage.pushEnter(0);
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("引き出す") {
					@Override
					protected void work() {
						CHEN_Strage.pushEnter(1);
					}
				};
			}

			@Override
			protected Book getContent3() {
				return new Book("全部預ける") {
					@Override
					protected void work() {
						CHEN_Strage.pushEnter(2);
					}
				};
			}

			@Override
			protected Book getContent4() {
				return new Book("見る") {
					@Override
					protected void work() {
						CHEN_Strage.pushEnter(3);
					}
				};
			}

			@Override
			protected Book getContent5() {
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
		CHEN_Strage.me.save();
	}
}
