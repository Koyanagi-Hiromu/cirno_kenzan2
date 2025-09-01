package dangeon.model.object.creature.npc.second;

import java.awt.Point;

import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.Config;
import dangeon.model.config.StoryManager;
import dangeon.model.map.field.random.second.七曜クエスト;
import dangeon.model.object.creature.npc.dungeonNpc.Base_DungeonNPC;
import dangeon.util.Switch;
import main.res.CHARA_IMAGE;
import main.res.SE;

public class NPC小悪魔 extends Base_DungeonNPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NPC小悪魔(Point p) {
		super(p, "小悪魔", CHARA_IMAGE.小悪魔, "危険な場所だと伺っていますが、それでも向かうのですね？",
				new 七曜クエスト());
	}
	
	@Override
	protected String getMsg_overItems() {
		return "アイテムは持っていけませんよ";
	}

	private void dataMove() {
		String msg = "どの処理をお望みですか？";
		ConvEvent CnE = new ConvEvent() {
			@Override
			protected Book getContent1() {
				return new Book("アイテム図鑑のソート") {
					@Override
					protected void work() {
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("旧データから新データへの移行処理") {
					@Override
					protected void work() {
						dataMove();
					}
				};
			}

			@Override
			protected Book getContent3() {
				return new Book("なにか教えて") {
					@Override
					protected void work() {
						say("うーん、それではわたしのカードの使用効果ですけど");
						say("「ランダムで書を使う」と言っても悪い効果は起こらないんですよ");
						say("安心してお使いくださいね");
					}
				};
			}
		};
		new Conversation(IMLC, msg, CnE);
	}

	@Override
	protected SE getSE() {
		return SE.SYSTEM_STAIR_STEP;
	}

	@Override
	protected void intoDungeon() {
		if (Config.getFate() == 2 && !StoryManager.運命のワルツclear.hasFinished()) {
			SE.SYSTEM_CURSE.play();
			Message.set("*この難度の運命にはまだ挑めない*");
		} else {
			super.intoDungeon();
		}
	}

	@Override
	public void message() {
		if (StoryManager.七曜クエストok.hasFinished()) {
			ask();
			return;
		}
		String msg = "パチュリー様はご不在ですよ$私に何かお手伝いできることはありませんか？";
		ConvEvent CnE = new ConvEvent() {
//			@Override
//			protected Book getContent1() {
//				return new Book("最新パッチのチェック") {
//					@Override
//					protected void work() {
//						new ConvEvent("インターネットに接続しますがよろしいですか？") {
//							@Override
//							protected Book getYes() {
//								return new Book() {
//									@Override
//									protected void work() {
//										try {
//											String[] arr = new PatchChecker()
//													.get();
//											patchSay(arr);
//										} catch (Exception e) {
//											e.printStackTrace();
//											say("なにかしらのエラーが起きてしまいました");
//											say("ネット環境をお確かめ下さい");
//										}
//									}
//								};
//							}
//						};
//					}
//				};
//			}

			@Override
			protected Book getContent2() {
				return new Book("図書館への入室") {
					@Override
					protected void work() {
						if (StoryManager.七曜クエストok.hasFinished()) {
							ask();
						} else {
							say("ごめんなさい、パチュリー様が戻ってくるまでお待ちください");
							if (StoryManager.逆ヶ島clear.hasFinished())
								say("地霊殿へ向かうとかおっしゃってました");
						}

					}
				};
			}

			@Override
			protected Book getContent3() {
				return new Book("なにか教えて") {
					@Override
					protected void work() {
						say("うーん、それではわたしのカードの使用効果ですけど");
						say("「ランダムで書を使う」と言っても悪い効果は起こらないんですよ");
						say("安心してお使いくださいね");
					}
				};
			}

		};
		new Conversation(IMLC, msg, CnE);
	}

	//
	// private void sortItemBook() {
	// PropertySupporter pr = PR_INDIV_PRE[getSaveIndex() - 1];
	// int category;
	// if (a instanceof SpellCard)
	// category = 0;
	// else if (a instanceof Base_Grass)
	// category = 1;
	// else if (a instanceof Ring)
	// category = 2;
	// else if (a instanceof Scrool)
	// category = 3;
	// else if (a instanceof Staff)
	// category = 4;
	// else if (a instanceof Food)
	// category = 5;
	// else if (a instanceof Arrow)
	// category = 6;
	// else if (a instanceof Base_Pot)
	// category = 7;
	// else
	// category = 8;
	// String str = getItemData(category);
	// String val = a.getClass().getSimpleName();
	// if (str.contains(val))
	// return;
	// StringBuilder sb = new StringBuilder(str);
	// if (!str.isEmpty())
	// sb.append(",");
	// sb.append(val);
	// pr.saveProperty(getItemDataKey(category), sb.toString());
	// }

	private void patchSay(String[] arr) {
		say("分かりました");
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				if (arr[i].matches(Switch.big_ver + Switch.small_ver)) {
					say("現在のバージョンは最新のようです");
					say("引き続き冒険をお楽しみください");
					return;
				} else {
					say("現在のバージョンは古いようです");
					String ver = arr[i].substring(0, 1) + "."
							+ arr[i].substring(1);
					say("最新のバージョンは、", ver, "です");
				}
			} else {
				say(arr[i]);
			}
		}
	}

	@Override
	protected void sayBrief() {
		talks("最難関未識別９９Ｆダンジョンだ");
		talks("時々レベルが１つ高い敵が出現するよ");
		talks("しかもボスも特定階層にいるから気をつけてね");
		talks(true, "まずは倉庫にカードを置きに戻ろう");

	}

	@Override
	protected void sayFirst() {
		// TODO 自動生成されたメソッド・スタブ
	}
}
