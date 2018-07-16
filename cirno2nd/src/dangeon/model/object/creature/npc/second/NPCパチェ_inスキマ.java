package dangeon.model.object.creature.npc.second;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.res.CHARA_IMAGE;
import main.res.SE;
import main.util.半角全角コンバーター;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.itemlist.Book_Item;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.npc.Base_NPC;
import dangeon.model.object.creature.player.Belongings;

public class NPCパチェ_inスキマ extends Base_NPC {
	private static final long serialVersionUID = 1L;

	public NPCパチェ_inスキマ(int x, int y) {
		super(new Point(x, y), "パチュリー", CHARA_IMAGE.パチュリー, false);
	}

	public NPCパチェ_inスキマ(Point point) {
		this(point.x, point.y);
	}

	private void confirmA() {
		String msg = "何か用かしら？$" + Color.GRAY.toString() + "※印を消すには修正値が１以上必要です※";
		new Conversation(IMLC, msg, new ConvEvent() {
			@Override
			protected Book getContent1() {
				return new Book("印を消してほしい") {

					@Override
					protected void work() {
						removeA();
					}
				};
			}

			@Override
			protected Book getContent2() {
				return new Book("特に用はない") {

					@Override
					protected void work() {
					}
				};
			}
		});
	}

	@Override
	public void message() {
		confirmA();
	}

	private void removeA() {
		ArrayList<Base_Artifact> list_escape = Belongings
				.getDeepCopy(Belongings.getListItems());
		for (Iterator<Base_Artifact> iterator = list_escape.iterator(); iterator
				.hasNext();) {
			Base_Artifact artifact = iterator.next();
			if (artifact instanceof SpellCard
					&& ((SpellCard) artifact).flag_gousei
					&& artifact.getForgeValue() > 0) {
				iterator.remove();
			} else {
				continue;
			}
		}
		Scene_Action.getMe().setNextScene(
				new Item_List("どのカードの？", new Book_Item() {

					@Override
					public void work(Base_Artifact a) {
						Scene_Action.getMe().setNextScene(Scene_Action.getMe());
						removeB(a);
					}

				}, list_escape, Belongings.getListItems()) {

					@Override
					protected void setContents(final Base_Artifact a) {
						if (isException(a)) {
							setContents(
									Color.BLACK.toString().concat(a.getName()),
									null, new Book() {
										@Override
										protected void work() {
											say("それは選択できないわ");
											action_cancel();
										}
									}, a);
						} else {
							setContents(a.getColoredName(), null, new Book() {
								@Override
								protected void work() {
									if (BOOK != null) {
										BOOK.work(a);
									}
								}
							}, a);
						}
					}
				});
	}

	private void removeB(final Base_Artifact a) {
		String msg = a.getColoredName() + "の%どの印を消すの？";
		final List<ENCHANT_SIMBOL> list = a.getListComposition();
		Book[] books = new Book[list.size() + 1];
		for (int i = 0; i < list.size(); i++) {
			String title = 半角全角コンバーター.半角To全角数字(i + 1) + "："
					+ list.get(i).getName();
			final int INDEX = i;
			books[i] = new Book(title) {
				@Override
				protected void work() {
					removeC(a, list, INDEX);
				}

			};
		}
		books[books.length - 1] = new Book("キャンセル") {

			@Override
			protected void work() {
				Scene_Action.getMe().setNextScene(Scene_Action.getMe());
			}
		};
		new Conversation(IMLC, msg, new ConvEvent(books));
	}

	private void removeC(final Base_Artifact a,
			final List<ENCHANT_SIMBOL> list, final int index) {
		final ENCHANT_SIMBOL simbol = list.get(index);
		String msg = "修正値を１下げて" + a.getColoredName() + "から%" + simbol.getName()
				+ "の印を消すけど本当にいいかしら？";
		new Conversation(IMLC, msg, new ConvEvent() {
			@Override
			public boolean defaultYes() {
				return false;
			}

			@Override
			protected Book getYes() {
				return new Book() {

					@Override
					protected void work() {
						SE.BURN.play();
						say(a.getColoredName(), "から%", simbol.getName(),
								"の印を消したわ");
						a.setForgeValue(-1);
						list.remove(index);
						if (list.size() == 0) {
							((SpellCard) a).flag_gousei = false;
						}
					}
				};
			}
		});
	}
	//
	// public void remove() {
	// List<Base_Artifact> list = Belongings.getDeepCopy(Belongings
	// .getListItems_except());
	// for (Iterator<Base_Artifact> iterator = list.iterator(); iterator
	// .hasNext();) {
	// Base_Artifact artifact = iterator.next();
	// if (artifact instanceof SpellCard
	// && ((SpellCard) artifact).flag_gousei) {
	// continue;
	// } else {
	// iterator.remove();
	// }
	// }
	// TaskOnMapObject.setTaskMenuItem(new Base_MenuItem("印の削除", list) {
	// @Override
	// protected void pressedEnter(final Base_Artifact a) {
	// end();
	// final List<ENCHANT_SIMBOL> list = a.getListComposition();
	// int size = list.size() + 1;
	// String[] arr;
	// if (list.size() >= 9) {
	// size = list.size();
	// arr = new String[size];
	// } else {
	// size = list.size() + 1;
	// arr = new String[size];
	// arr[size - 1] = "キャンセル";
	// }
	// for (int i = 0; i < list.size(); i++) {
	// arr[i] = 半角全角コンバーター.半角To全角数字(i + 1) + "："
	// + list.get(i).getName();
	// }
	// sayImageLargeCharacter(Image_LargeCharacter.八雲紫);
	// say(a.getName(), "の%どの印を消すの？@");
	// Message.removeILC();
	// new Base_SelectBox(false, arr) {
	//
	// @Override
	// public void pushEnter(int y) {
	// end();
	// if (y == list.size()) {
	// pushCancel();
	// } else {
	// final int selected_index = y;
	// sayImageLargeCharacter(Image_LargeCharacter.八雲紫);
	// say(a.getColoredName(), "から%",
	// list.get(selected_index).getName(),
	// "の印を消すわよ$本当にいいかしら？@");
	// Message.removeILC();
	// new Base_SelectBox(false, "いいえ", "はい") {
	// @Override
	// public void pushEnter(int y) {
	// end();
	// if (y == 1) {
	// sayImageLargeCharacter(Image_LargeCharacter.八雲紫);
	// say(a.getColoredName(), "から%",
	// list.get(selected_index)
	// .getName(), "の印を消したわ@");
	// Message.removeILC();
	// list.remove(selected_index);
	// if (list.size() == 0) {
	// ((SpellCard) a).flag_gousei = false;
	// }
	// }
	// }
	// };
	// }
	//
	// }
	// };
	// }
	// });
	//
	// }
}
