package dangeon.model.object.creature.player.strage;

import java.awt.Color;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.itemlist.Book_Item;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.adventure.wiki_item.ItemWiki_AllItem;
import dangeon.latest.scene.action.message.ConvEvent;
import dangeon.latest.scene.action.message.Conversation;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.scene.action.strage.ChenStorage_Command;
import dangeon.latest.scene.action.strage.ChenStorage_List;
import dangeon.model.config.Config;
import dangeon.model.config.table.ItemTable;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.disc.Disc;
import dangeon.model.object.artifact.item.disc.DiscA;
import dangeon.model.object.artifact.item.disc.Disc_Detail;
import dangeon.model.object.artifact.item.food.Food;
import dangeon.model.object.artifact.item.grass.Base_Grass;
import dangeon.model.object.artifact.item.pot.Base_Pot;
import dangeon.model.object.artifact.item.ring.Ring;
import dangeon.model.object.artifact.item.scrool.Scrool;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.staff.Staff;
import dangeon.model.object.creature.player.Belongings;
import dangeon.util.R;
import main.res.Image_LargeCharacter;
import main.util.Show;
import main.util.半角全角コンバーター;

/**
 * 橙の倉庫（スタック倉庫）。<br>
 * アイテムの種類ごとに「個数」だけを記憶する。修正値・呪い・強化・瓶の中身などの
 * 個体情報は保持しない。引き出すときは素の状態・完全識別済みのアイテムを生成する （杖は残り回数4）。<br>
 * 実体を保存する既存の倉庫（藍/RAN_Strage）とは独立した別システム。
 */
public class CHEN_Strage implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 1種類あたりの最大スタック数 */
	public static final int MAX_STACK = 999;

	/** 引き出した杖の使用回数 */
	public static final int STAFF_USE_COUNT = 4;

	/** 「全部預ける」の対象外になる杖の残り回数（これ以上は預けない） */
	public static final int STAFF_KEEP_REST = 5;

	private static final String CLASS_PREFIX = "dangeon.model.object.artifact.";

	/** 倉庫内カテゴリの並び（図鑑のカテゴリから射撃を除き、DISCを足したもの） */
	private static final String[] CATEGORY_NAMES = { "spellcard", "grass",
			"ring", "scrool", "staff", "food", "pot", "disc" };

	/** カテゴリメニューの表示名（CATEGORY_NAMESと同じ並び） */
	private static final String[] CATEGORY_LABELS = { "カード", "草", "リボン", "書",
			"杖", "食べ物", "魔法瓶", "ＤＩＳＣ" };

	/** カテゴリ並びを図鑑（ItemWiki_AllItem/Config.getItemData）の番号に変換する。-1は図鑑になし */
	private static final int[] WIKI_INDEXES = { 0, 1, 2, 3, 4, 5, 7, -1 };

	public static final int CATEGORY_COUNT = CATEGORY_NAMES.length;

	public static int wikiIndex(int index) {
		return WIKI_INDEXES[index];
	}

	public static CHEN_Strage me = new CHEN_Strage();

	public static String categoryLabel(int index) {
		return CATEGORY_LABELS[index];
	}

	public static String categoryName(int index) {
		return CATEGORY_NAMES[index];
	}

	/**
	 * 素の状態・完全識別済みのアイテムを生成する（杖は残り回数4）<br>
	 * 生成時のランダム要素（瓶の容量・書のページ数など）は
	 * 「自然に発生しうる最大値」に固定し、強化値・呪いは打ち消す
	 */
	public static Base_Artifact createBasic(String key) {
		Base_Artifact a;
		R.setMaxMode(true);
		try {
			a = createInstance(key);
		} finally {
			R.setMaxMode(false);
		}
		if (a == null) {
			return null;
		}
		if (a instanceof Staff) {
			((Base_Item) a).setStaffUseCount(STAFF_USE_COUNT);
			a.createStaffRest(STAFF_USE_COUNT);
		}
		a.setItemNormalCondition();
		a.setForgeValue(-a.getForgeValue());
		a.check();
		return a;
	}

	/**
	 * 種類キーからアイテムの実体を生成する<br>
	 * DISCは"クラス名#曲情報"の拡張キーなので専用に組み立てる
	 */
	private static Base_Artifact createInstance(String key) {
		int sharp = key.indexOf('#');
		if (sharp < 0) {
			return ItemTable.returnBaseArtifactSetPoint(key, new Point());
		}
		String class_name = key.substring(0, sharp);
		String detail = key.substring(sharp + 1);
		if (class_name.equals("item.disc.DiscA")) {
			return new DiscA(new Point(), detail);
		}
		// 2曲構成のDISC（Disc・DiscA_Aなど）：クラスを生成してから曲を差し替える
		String[] s = detail.split("＆");
		if (s.length != 2) {
			return null;
		}
		Base_Artifact a = ItemTable.returnBaseArtifactSetPoint(class_name,
				new Point());
		if (a instanceof Disc) {
			((Disc) a).selectSetDetail(s[0], s[1]);
			return a;
		}
		return null;
	}

	/**
	 * このアイテムの種類がスタック倉庫に預けられるか（射撃・特殊アイテムは不可）
	 */
	public static boolean isDepositable(Base_Artifact a) {
		return a instanceof SpellCard || a instanceof Base_Grass
				|| a instanceof Ring || a instanceof Scrool
				|| a instanceof Staff || a instanceof Food
				|| a instanceof Base_Pot || a instanceof Disc;
	}

	/**
	 * アイテムの種類キー（例："item.spellcard.チルノのカード"）<br>
	 * DISCは曲の組み合わせも種類の一部なので"item.disc.Disc#妖々夢＆紅魔郷"の形になる<br>
	 * Discクラスで同タイトル2曲になった個体はDiscA_Aと同一種として正規化する
	 */
	public static String keyOf(Base_Artifact a) {
		String key = a.getClass().getName().substring(CLASS_PREFIX.length());
		if (a instanceof Disc) {
			String detail = ((Disc) a).getDetailKey();
			if (a.getClass() == Disc.class) {
				String[] s = detail.split("＆");
				if (s.length == 2 && s[0].equals(s[1])) {
					key = "item.disc.DiscA_A";
				}
			}
			key = key.concat("#").concat(detail);
		}
		return key;
	}

	/**
	 * 橙NPCの会話メニューから呼ばれる（RAN_Strage.pushEnterと同じ流儀）
	 *
	 * @param y
	 *            0:預ける 1:引き出す 2:全部預ける
	 */
	public static void pushEnter(int y) {
		if (y == 0) {
			if (Belongings.getSize() == 0) {
				say("何も持っていないみたいだよ");
				return;
			}
			ArrayList<Base_Artifact> items = Belongings.getListItems();
			ArrayList<Base_Artifact> escape = new ArrayList<Base_Artifact>();
			for (Base_Artifact a : items) {
				if (!isDepositable(a) || me.isFull(keyOf(a))) {
					escape.add(a);
				}
			}
			if (escape.size() == items.size()) {
				say("預かれるアイテムはないみたいだよ");
				return;
			}
			new Item_List("預ける", new Book_Item() {
				@Override
				public void work(Base_Artifact a) {
					depositWithConfirm(new Base_Artifact[] { a });
				}
			}, escape, items) {
				@Override
				protected Book getMultiBook(
						final Base_Artifact... selected_artifacts) {
					return new Book() {
						@Override
						protected void work() {
							depositWithConfirm(selected_artifacts);
							setNextScene(getPreviousScene());
						}
					};
				}

				@Override
				protected void setContents(final Base_Artifact a) {
					if (isException(a)) {
						// 預けられない理由を橙が説明する
						setContents(Color.BLACK.toString().concat(a.getName()),
								null, new Book() {
									@Override
									protected void work() {
										if (!isDepositable(a)) {
											say("射撃アイテムは預かれないよ。$藍様に預けてね");
										} else {
											say("その種類はもういっぱいで預かれないよ");
										}
										setNextScene(Scene_Action.getMe());
									}
								}, a);
					} else {
						super.setContents(a);
					}
				}
			};
		} else if (y == 1) {
			if (me.getTotalKinds() == 0) {
				say("まだ何も預かってないよ");
			} else {
				ChenStorage_List list = new ChenStorage_List(
						me.firstStockedCategory());
				Scene_Action.getMe()
						.setNextScene(new ChenStorage_Command(list));
			}
		} else if (y == 3) {
			// 見る：在庫がなくても全種類のカタログを開ける
			ChenStorage_List list = new ChenStorage_List(
					me.firstStockedCategory(), 0, true);
			Scene_Action.getMe().setNextScene(new ChenStorage_Command(list));
		} else if (y == 2) {
			if (Belongings.getSize() == 0) {
				say("何も持っていないみたいだよ");
				return;
			}
			// 「本当に全部」でも預けられるものが一つもなければ選択肢を出さず断る
			boolean any = false;
			for (Base_Artifact a : Belongings.getListItems()) {
				if (isDepositable(a) && !me.isFull(keyOf(a))) {
					any = true;
					break;
				}
			}
			if (!any) {
				say("預かれるアイテムはないみたいだよ");
				return;
			}
			new Conversation(Image_LargeCharacter.橙, "どうやって預かる？",
					new ConvEvent() {
						@Override
						protected Book getContent1() {
							return new Book("安全なものだけ") {
								@Override
								protected void work() {
									depositAllSafe();
								}
							};
						}

						@Override
						protected Book getContent2() {
							return new Book("本当に全部") {
								@Override
								protected void work() {
									depositAllWithConfirm();
								}
							};
						}

						@Override
						protected Book getContent3() {
							return new Book("やめる") {
								@Override
								protected void work() {
									pushCancelAction();
								}
							};
						}
					});
		}
	}

	/**
	 * 「全部預ける」→「安全なものだけ」<br>
	 * カード・中身入りの瓶・残り回数5以上の杖を対象外にして預ける
	 */
	private static void depositAllSafe() {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : Belongings.getListItems()) {
			if (isDangerous(a)) {
				continue;
			}
			list.add(a);
		}
		if (list.isEmpty()) {
			say("まとめて預かれるアイテムはないみたいだよ");
			return;
		}
		depositWithConfirm(list.toArray(new Base_Artifact[0]));
		Message.removeILC();
	}

	/**
	 * 「全部預ける」→「本当に全部」<br>
	 * 確認してからカード・中身入りの瓶・回数の多い杖も含めて預ける
	 */
	private static void depositAllWithConfirm() {
		new Conversation(Image_LargeCharacter.橙,
				"カードも瓶も杖も全部だね？$強化値や瓶の中身は消えちゃうけど本当にいい？", new ConvEvent() {
					@Override
					public boolean defaultYes() {
						return true;
					}

					@Override
					protected Book getYes() {
						return new Book() {
							@Override
							protected void work() {
								ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
								for (Base_Artifact a : Belongings
										.getListItems()) {
									list.add(a);
								}
								doDeposit(list.toArray(new Base_Artifact[0]));
								Message.removeILC();
							}
						};
					}
				});
	}

	/**
	 * 大事そうなアイテム（カード・中身入りの瓶・回数の多い杖）が
	 * 含まれていたら確認してから預かる
	 */
	private static void depositWithConfirm(final Base_Artifact[] as) {
		boolean has_dangerous = false;
		for (Base_Artifact a : as) {
			if (isDangerous(a) && isDepositable(a) && !me.isFull(keyOf(a))) {
				has_dangerous = true;
				break;
			}
		}
		if (has_dangerous) {
			String msg;
			if (as.length == 1) {
				msg = as[0].getColoredName()
						.concat("を本当に預けていいの？$強化値とか中身とかがなくなっちゃうよ");
			} else {
				msg = "カードや瓶みたいな大事そうなアイテムが混ざってるよ。$強化値や中身は消えちゃうけど本当にいい？";
			}
			new Conversation(Image_LargeCharacter.橙, msg,
					new ConvEvent() {
						@Override
						public boolean defaultYes() {
							return false;
						}

						@Override
						protected Book getYes() {
							return new Book() {
								@Override
								protected void work() {
									doDeposit(as);
								}
							};
						}
					});
		} else {
			doDeposit(as);
		}
	}

	/**
	 * 預けると損をしうる「大事そうな」アイテムかどうか<br>
	 * （カード＝強化が消える／中身入りの瓶＝中身が消える／残り回数5以上の杖＝回数が減る）
	 */
	private static boolean isDangerous(Base_Artifact a) {
		if (a instanceof SpellCard) {
			return true;
		}
		if (a instanceof Base_Pot && !((Base_Pot) a).isEmpty()) {
			return true;
		}
		if (a instanceof Staff && a.staff_rest >= STAFF_KEEP_REST) {
			return true;
		}
		return false;
	}

	private static void doDeposit(Base_Artifact[] as) {
		int number = 0;
		boolean full_hit = false, type_hit = false;
		Base_Artifact last = null;
		for (Base_Artifact a : as) {
			if (!isDepositable(a)) {
				type_hit = true;
				continue;
			}
			String key = keyOf(a);
			if (me.isFull(key)) {
				full_hit = true;
				continue;
			}
			me.increment(key);
			Belongings.remove(a);
			number++;
			last = a;
		}
		if (number == 0) {
			if (full_hit) {
				say("その種類はもうこれ以上預かれないよ");
			} else {
				say("それは預かれないよ");
			}
		} else if (full_hit || type_hit) {
			say("預かれる分だけ預かったよ$", "(", 半角全角コンバーター.半角To全角数字(number), "コ)");
		} else if (number == 1) {
			say(last.getColoredName(), "を預かったよ");
		} else {
			say(半角全角コンバーター.半角To全角数字(number), "コのアイテムを預かったよ");
		}
	}

	private static void say(String... strings) {
		new Conversation(Image_LargeCharacter.橙, strings);
	}

	public static void setMe() {
		me = (CHEN_Strage) me.load();
		if (me == null) {
			me = new CHEN_Strage();
		}
	}

	private final HashMap<String, Integer> map = new HashMap<String, Integer>();

	private CHEN_Strage() {
	}

	/**
	 * 指定カテゴリの在庫から、リスト表示用のサンプル実体を生成する（レア度→名前順）
	 */
	public ArrayList<Base_Artifact> createSampleList(int index) {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		String prefix = "item.".concat(categoryName(index)).concat(".");
		for (String key : map.keySet()) {
			if (getCount(key) > 0 && key.startsWith(prefix)) {
				Base_Artifact a = createBasic(key);
				if (a != null) {
					list.add(a);
				}
			}
		}
		Collections.sort(list, new Comparator<Base_Artifact>() {
			@Override
			public int compare(Base_Artifact o1, Base_Artifact o2) {
				return o1.getTrueName().compareTo(o2.getTrueName());
			}
		});
		Collections.sort(list, new Comparator<Base_Artifact>() {
			@Override
			public int compare(Base_Artifact o1, Base_Artifact o2) {
				return ItemTable.getRankForSort(o1)
						- ItemTable.getRankForSort(o2);
			}
		});
		return list;
	}

	/**
	 * 「見る」の表示専用サンプルを生成する<br>
	 * check()（識別）は行わない：未使用アイテムを識別したり
	 * 未識別名スロットを消費したりする副作用を避けるため（図鑑と同じ流儀）
	 */
	private static Base_Artifact createViewSample(String key) {
		Base_Artifact a;
		R.setMaxMode(true);
		try {
			a = createInstance(key);
		} finally {
			R.setMaxMode(false);
		}
		if (a == null) {
			return null;
		}
		a.setItemNormalCondition();
		a.setForgeValue(-a.getForgeValue());
		return a;
	}

	/**
	 * 「見る」用：カテゴリの全種類をリスト表示するサンプルを生成する<br>
	 * 図鑑（使用履歴）にないアイテムは？？？？表示（setSampleItem(true)）になる
	 */
	public ArrayList<Base_Artifact> createViewSampleList(int index) {
		ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
		int wiki = wikiIndex(index);
		if (wiki >= 0) {
			ArrayList<String> known = new ArrayList<String>();
			for (String s : Config.getItemData(wiki).split(",")) {
				known.add(s);
			}
			String prefix = "item.".concat(categoryName(index)).concat(".");
			for (String s : new ItemWiki_AllItem().get(wiki)) {
				if (s.isEmpty()) {
					continue;
				}
				Base_Artifact a = createViewSample(prefix.concat(s));
				if (a == null) {
					continue;
				}
				a.setSampleItem(!known.contains(s));
				list.add(a);
			}
			Collections.sort(list, new Comparator<Base_Artifact>() {
				@Override
				public int compare(Base_Artifact o1, Base_Artifact o2) {
					return o1.getTrueName().compareTo(o2.getTrueName());
				}
			});
			Collections.sort(list, new Comparator<Base_Artifact>() {
				@Override
				public int compare(Base_Artifact o1, Base_Artifact o2) {
					return ItemTable.getRankForSort(o1)
							- ItemTable.getRankForSort(o2);
				}
			});
		} else {
			// DISC：単曲→同曲2枚→異曲2枚の順に全種類を並べる（DISCに未識別はない）
			Disc_Detail[] ds = Disc_Detail.values();
			for (Disc_Detail d : ds) {
				addViewDisc(list, "item.disc.DiscA#".concat(d.name()));
			}
			for (Disc_Detail d : ds) {
				addViewDisc(list, "item.disc.DiscA_A#".concat(d.name())
						.concat("＆").concat(d.name()));
			}
			for (Disc_Detail d1 : ds) {
				for (Disc_Detail d2 : ds) {
					if (d1 == d2) {
						continue;
					}
					addViewDisc(list, "item.disc.Disc#".concat(d1.name())
							.concat("＆").concat(d2.name()));
				}
			}
		}
		return list;
	}

	private static void addViewDisc(ArrayList<Base_Artifact> list, String key) {
		Base_Artifact a = createViewSample(key);
		if (a != null) {
			list.add(a);
		}
	}

	/**
	 * カテゴリ内で在庫のある種類数
	 */
	public int getStockedKinds(int index) {
		String prefix = "item.".concat(categoryName(index)).concat(".");
		int kinds = 0;
		for (String key : map.keySet()) {
			if (getCount(key) > 0 && key.startsWith(prefix)) {
				kinds++;
			}
		}
		return kinds;
	}

	/**
	 * 在庫のあるカテゴリのうち最初のもの（なければ0）
	 */
	public int firstStockedCategory() {
		for (int i = 0; i < CATEGORY_COUNT; i++) {
			String prefix = "item.".concat(categoryName(i)).concat(".");
			for (String key : map.keySet()) {
				if (getCount(key) > 0 && key.startsWith(prefix)) {
					return i;
				}
			}
		}
		return 0;
	}

	public int getCount(String key) {
		Integer i = map.get(key);
		return i == null ? 0 : i.intValue();
	}

	private String getSaveURL() {
		StringBuilder sb = new StringBuilder();
		sb.append("save/");
		sb.append(Config.getSaveIndex());
		sb.append("/chen_strage.save");
		return sb.toString();
	}

	/**
	 * 預かっている種類数
	 */
	public int getTotalKinds() {
		int kinds = 0;
		for (String key : map.keySet()) {
			if (getCount(key) > 0) {
				kinds++;
			}
		}
		return kinds;
	}

	public void increment(String key) {
		map.put(key, getCount(key) + 1);
	}

	public boolean isFull(String key) {
		return getCount(key) >= MAX_STACK;
	}

	private Object load() {
		String url = getSaveURL();
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(url));
			Object obj = ois.readObject();
			ois.close();
			return obj;
		} catch (Exception e) {
		}
		return null;
	}

	public void save() {
		try {
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(new FileOutputStream(getSaveURL()));
			oos.writeObject(this);
			oos.close();
		} catch (Exception e) {
			Show.showCriticalErrorMessageDialog(e);
		}
	}

	/**
	 * 1個引き出して素の状態の実体を返す（在庫が無ければnull）
	 */
	public Base_Artifact withdraw(String key) {
		int count = getCount(key);
		if (count <= 0) {
			return null;
		}
		if (count == 1) {
			map.remove(key);
		} else {
			map.put(key, count - 1);
		}
		return createBasic(key);
	}

}
