package dangeon.model.object.creature.player.strage;

import java.util.ArrayList;
import java.util.HashMap;

import main.res.Image_LargeCharacter;
import main.util.半角全角コンバーター;
import dangeon.latest.scene.action.itemlist.Book_Item;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.Config;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;

public class RAN_Strage extends Base_Strage {
	public static RAN_Strage me = new RAN_Strage();

	private static final long serialVersionUID = 1L;

	public static void pushEnter(int y) {
		if (y == 0) {
			if (Belongings.getSize() == 0) {
				say("何も持っていないようだが・・・");
			} else if (me.isMax()) {
				say("倉庫が一杯でこれ以上預かれないな");
			} else {
				new Item_List("預ける", new Book_Item() {
					@Override
					public void work(Base_Artifact a) {
						me.addLast(a);
						Belongings.remove(a);
						say(a.getColoredName(), "を預かったぞ");
					}
				}, Belongings.getListItems()) {
					@Override
					protected Book getMultiBook(
							final Base_Artifact... selected_artifacts) {
						return new Book() {
							@Override
							protected void work() {
								ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
								for (Base_Artifact a : selected_artifacts) {
									list.add(a);
								}
								int number = 0;
								for (Base_Artifact a : list) {
									if (me.isMax()) {
										say("選んだアイテムを受け取れるだけ受け取ったぞ$", "(",
												半角全角コンバーター.半角To全角数字(number),
												"コ)");
										setNextScene(getPreviousScene());
										return;
									}
									me.addLast(a);
									number++;
									Belongings.remove(a);
								}
								say(半角全角コンバーター.半角To全角数字(number), "コのアイテムを預かったぞ");
								setNextScene(getPreviousScene());
							}
						};
					}
				};
			}
		} else if (y == 1) {
			if (Belongings.isMax()) {
				say("持ち物がいっぱいだな");
			} else {
				me.sort();
				HashMap<Integer, Base_Artifact> as = me.LIST_ITEM;
				ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
				for (int i = 0; i < as.size(); i++) {
					if (as.get(i) != null)
						list.add(as.get(i));
				}
				new Item_List("引き出す", new Book_Item() {
					@Override
					public void work(Base_Artifact a) {
						if (a == null) {
							return;
						}
						me.remove(a);
						Belongings.setItems(a);
						say(a.getColoredName(), "を渡したぞ");
					}
				}, list) {
					@Override
					protected Book getMultiBook(
							final Base_Artifact... selected_artifacts) {
						return new Book() {
							@Override
							protected void work() {
								ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
								for (Base_Artifact a : selected_artifacts) {
									list.add(a);
								}
								int number = 0;
								for (Base_Artifact a : list) {
									if (Belongings.isMax()) {
										say("選んだアイテムを渡せるだけ渡したぞ$", "(",
												半角全角コンバーター.半角To全角数字(number),
												"コ)");
										setNextScene(getPreviousScene());
										return;
									}
									me.remove(a);
									Belongings.setItems(a);
									number++;
								}
								say(半角全角コンバーター.半角To全角数字(number), "コのアイテムを渡したぞ");
								setNextScene(getPreviousScene());
							}
						};
					}
				};
			}
		} else if (y == 2) {
			if (Belongings.getSize() == 0) {
				say("何も持っていないようだが・・・");
			} else if (me.isMax()) {
				say("もう倉庫が一杯でこれ以上預かれないな");
			} else {
				ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
				for (Base_Artifact a : Belongings.getListItems()) {
					list.add(a);
				}
				for (Base_Artifact a : list) {
					if (me.isMax()) {
						say("持っているアイテムを預かれるだけ預かったぞ");
						return;
					}
					me.addLast(a);
					Belongings.remove(a);
				}
				say("持っているアイテムを全部預かったぞ");
				Message.removeILC();
			}
		}
	}

	private static void say(String... strings) {
		new Conversation(Image_LargeCharacter.八雲藍, strings);
	}

	public static void setMe() {
		me = (RAN_Strage) me.load();
		if (me == null) {
			me = new RAN_Strage();
		}
	}

	private RAN_Strage() {
		super(150, 1);
	}

	@Override
	public String getSaveURL() {
		StringBuilder sb = new StringBuilder();
		sb.append("save/");
		sb.append(Config.getSaveIndex());
		sb.append("/ran_strage.save");
		return sb.toString();
	}
}
