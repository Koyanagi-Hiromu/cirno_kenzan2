package dangeon.model.object.creature.npc.second;

import java.awt.Point;
import java.awt.event.KeyEvent;

import dangeon.controller.TaskOnMapObject;
import dangeon.controller.task.Task;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.system.SceneHolder_KeyAccepter;
import dangeon.model.config.StoryManager;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Player;
import main.Scene;
import main.res.CHARA_IMAGE;
import main.res.Image_LargeCharacter;
import main.res.SE;
import main.util.BlackOut;

public class NPC博麗霊夢 extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NPC博麗霊夢(int x, int y) {
		super(new Point(x, y), "博麗霊夢", CHARA_IMAGE.博麗霊夢, false);
	}

	public NPC博麗霊夢(Point point) {
		this(point.x, point.y);
	}

	@Override
	public void message() {
		SceneHolder_KeyAccepter key = (SceneHolder_KeyAccepter) (Scene.DANGEON.SYS.KEY);
		if (key.kh.hasKeyEvent(KeyEvent.VK_9)) {
			confirm();
		} else {
			if (StoryManager.七曜クエストok.hasFinished()) {
				say("平和でいいわね");
			} else if (StoryManager.大妖精救出clear.hasFinished()) {
				secretCommand();
			} else {
				say("大妖精がさらわれたって？");
				say("まぁもし異変まで発展したら解決しに向かうわ");
				rep("お茶でも飲んで待っててよ");
			}
		}
	}

	void confirm() {
		if (StoryManager.七曜クエストok.hasFinished()) {
			say("もうスキップできることはないわ");
			return;
		}
		String msg = "シナリオをスキップして全てのダンジョンを解放できるわ";
		new Conversation(IMLC, msg, new ConvEvent() {
			@Override
			protected Book getContent1() {
				return new Book("スキップする") {

					@Override
					protected void work() {
						confirm2();
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("スキップしない") {

					@Override
					protected void work() {
						say("平和でいいわね");
					}
				};
			}
		});
	}

	void confirm2() {
		String msg = "取り消せないわよ？";
		new Conversation(IMLC, msg, new ConvEvent() {
			@Override
			protected Book getContent1() {
				return new Book("解放する") {

					@Override
					protected void work() {
						say("３・・・");
						say("２・・・");
						say("１・・・");
						ConvEvent cne = new ConvEvent() {
							@Override
							public void workAfterPush() {
								new BlackOut(new Task() {
									private static final long serialVersionUID = 1L;

									@Override
									public void work() {
										Player.flag_clear = true;
										SE.FANFARE2.play();
										CreateSaveData();
										setTalks(Image_LargeCharacter.ANY);
										talks("すべてのダンジョンが解放された！");
										talks(true, "一度タイトルに戻ってみよう");
									}
								}, new Task() {
									private static final long serialVersionUID = 1L;

									@Override
									public void work() {
										StoryManager.日記帳.saveThisFinished();
										TaskOnMapObject.reCreateNewMap();
									}
								});
							}
						};
						new Conversation(IMLC, cne, "たくさん楽しんでね");
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("やっぱりやめる") {

					@Override
					protected void work() {
						say("平和でいいわね");
					}
				};
			}
		});
	}

	void secretCommand() {
		String msg = "平和でいいわね";
		new Conversation(IMLC, msg, new ConvEvent() {
			@Override
			protected Book getContent1() {
				return new Book("そうだね") {

					@Override
					protected void work() {
						say("悪いことしないでよ？");
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("強敵と戦いたい") {

					@Override
					protected void work() {
						say("妖精らしい無謀な願いね・・・");
						rep("お願い！");
						say("うーん、本当にそう願うなら");
						say("「9」を押しながら話しかけてみて");
						rep("なんのこと？？");
						say("キーボードの「9」よ");
						rep("？？？");
					}
				};
			}
		});
	}

	public static void CreateSaveData() {
		StoryManager.大妖精救出へ.saveThisFinished();
		StoryManager.救出大作戦挑戦ok.saveThisFinished();
		StoryManager.逆ヶ島挑戦ok.saveThisFinished();
		StoryManager.二撃必殺挑戦ok.saveThisFinished();
		StoryManager.緋想の地下挑戦ok.saveThisFinished();
		StoryManager.五色の神霊廟挑戦ok.saveThisFinished();
		StoryManager.風穴旅行挑戦ok.saveThisFinished();
		StoryManager.ミラクルクエスト挑戦ok.saveThisFinished();
		StoryManager.おりん車.saveThisFinished();
		StoryManager.日記帳.saveThisFinished();
		StoryManager.運命のワルツok.saveThisFinished();
		StoryManager.あとがき.saveThisFinished();
		StoryManager.シェアok.saveThisFinished();
		StoryManager.運命のワルツEvent_1.saveThisFinished();
		StoryManager.七曜クエストok.saveThisFinished();
		StoryManager.賢将裏の洞窟ok.saveThisFinished();
		StoryManager.トラップタワーok.saveThisFinished();
	}
}
