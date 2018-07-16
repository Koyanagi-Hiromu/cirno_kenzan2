package dangeon.model.object.creature.npc.dungeonNpc;

import java.awt.Color;
import java.awt.Point;

import main.res.CHARA_IMAGE;
import main.util.BlackOut;
import main.util.DIRECTION;
import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.map.MassCreater;
import dangeon.model.map.PresentField;
import dangeon.model.map.field.random.逆ヶ島;
import dangeon.model.map.field.town.map.FairyPlace;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Player;

public class NPC逆ヶ島 extends Base_DungeonNPC {

	private static final long serialVersionUID = 1L;

	public NPC逆ヶ島(Point p) {
		super(p, "大妖精", CHARA_IMAGE.大妖精, "逆ヶ島へ向かうの？", "また逆ヶ島に行くの？", new 逆ヶ島());
	}

	@Override
	protected void ask() {
		new Conversation(IMLC, ASK, new ConvEvent() {
			@Override
			protected String getCancel() {
				return getMsg_cancel();
			}

			@Override
			protected Book getContent1() {
				return new Book("うん") {

					@Override
					protected void work() {
						if (MAP.ITEM_MAX != null
								&& Belongings.getSize() > MAP.ITEM_MAX) {
							Message.set(getMsg_overItems());
						} else {
							intoDungeon();
						}
					}
				};
			}

			@Override
			protected Book getContent2() {
				if (PresentField.get() instanceof FairyPlace) {
					return new Book("いいえ") {

						@Override
						protected void work() {

						}
					};
				} else {
					return new Book("帰還する") {

						@Override
						protected void work() {
							confirm();
						}

					};
				}
			}

			@Override
			protected String[] getExn() {
				return MAP.getExn();
			}
		});
	}

	private void confirm() {
		String msg = "本当に妖精の踊り場に戻るの？$" + Color.GRAY.toString()
				+ "（アイテムを持ったまま帰還します）";
		new Conversation(IMLC, msg, new ConvEvent() {
			@Override
			protected Book getYes() {
				return new Book() {

					@Override
					protected void work() {
						Message.set("それじゃあ行っくよ～～");
						final FairyPlace bm = new FairyPlace();
						new BlackOut(bm, new Task() {
							private static final long serialVersionUID = 1L;

							@Override
							public void work() {
								// FairyPlace到着時にfalseにしてくれるのここでの記述は省略
								// Player.me.flag_no_item_daichan = false;
								TaskOnMapObject.setNewMap(new MassCreater(bm,
										bm.getEntrancePoint(), false));
								Player.me.setDirection(DIRECTION.DOWN);
							}
						});
					}
				};
			}
		});
	}

	@Override
	protected String[] getMsg_overItems() {
		return new String[] { "アイテムが多すぎて逆ヶ島まで運べないよ" };
	}

	@Override
	protected void preCreaterFirstMap() {
		Message.set("無理する前に私のカードを使ってね");
	}

	@Override
	protected void sayBrief() {
		talks("大妖精のカードをもらえるようになったぞ");
		talks("持ち込み前提ダンジョンだ！");
		talks(true, "しっかり準備してから挑もう");
	}

	@Override
	protected void sayFirst() {
		say("うう、チルノちゃん");
		say("本当に行くの・・・？");
		say("・・・うん");
		say("ごめんね、私が行っても足手まといになるだけだから・・・");
		say("せめてカードだけ渡すね");
		say("私はチルノちゃんを頼ることしかできないけど・・・");
		say("お願い・・・！");
		say("私達の宝物を取り返してきて・・・！");
		say("・・・");
		say(true, "でも絶対に無理しないでね・・・？");
	}
}
