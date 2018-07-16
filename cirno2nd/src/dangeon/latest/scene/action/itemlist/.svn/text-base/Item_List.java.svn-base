package dangeon.latest.scene.action.itemlist;

import java.awt.Color;
import java.util.ArrayList;

import main.Listener.ACTION;
import main.res.SE;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.ItemExplainWindow;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;

public class Item_List extends Base_Scene_Menu {
	public final String TITLE;
	protected final Book_Item BOOK;
	public final boolean MULTI_SELECTION;
	protected boolean flag_ready = false;
	protected int page;
	protected ArrayList<Base_Artifact> LIST;
	protected final ArrayList<Base_Artifact> LIST_EXCEPTION;
	protected final Base_Scene PREVIOUS_SCENE;
	protected ArrayList<Integer> holding_mutli_selection = new ArrayList<Integer>();

	public final ArrayList<Base_Artifact> FOOT;

	/**
	 * 通常シーンから
	 */
	public Item_List(Book_Item book, ArrayList<Base_Artifact> selections) {
		this(true, "どれを？", book, null, selections);
	}

	/**
	 * 通常シーンから
	 */
	public Item_List(Book_Item book, ArrayList<Base_Artifact> list_escape,
			ArrayList<Base_Artifact> selections) {
		this(true, "どれを？", book, list_escape, selections);
	}

	/**
	 * select_itemから
	 * 
	 * @param book
	 * @param list_escape
	 * @param foot
	 */
	public Item_List(Book_Item book, ArrayList<Base_Artifact> list_escape,
			boolean foot) {
		this(10, Scene_Action.getMe().KH, new Item_List_View(
				Scene_Action.getMe().CURRENT_VIEW),
				Scene_Action.getMe().CURRENT_VIEW, "どれを？", book, list_escape,
				Belongings.getListItems(), foot ? Belongings.get(-1) != null
						: false);
		setNextScene(this);
	}

	/**
	 * 通常シーンから
	 */
	private Item_List(boolean from_action_scene, String title, Book_Item book,
			ArrayList<Base_Artifact> list_escape,
			ArrayList<Base_Artifact> selections) {
		this(10, Scene_Action.getMe().KH, Scene_Action.getMe().CURRENT_VIEW,
				title, book, list_escape, selections);
		Scene_Action.getMe().setNextScene(this);
	}

	/**
	 * 全手持ちアイテム
	 * 
	 * @param cols
	 * @param kh
	 * @param smv
	 * @param bv
	 */
	public Item_List(int cols, KeyHolder kh, Base_Scene_Menu_View smv,
			Base_View bv) {
		this(cols, kh, smv, bv, null, null, null, Belongings.getListItems(),
				false);
	}

	/**
	 * 複数選択させるときはgetMultiBook()をover-ride
	 * 
	 * @param cols
	 * @param kh
	 * @param bv
	 * @param title
	 * @param book
	 * @param list_escape
	 *            除外(黒表示)するアイテム
	 * @param selections
	 */
	protected Item_List(int cols, KeyHolder kh, Base_Scene_Menu_View smv,
			Base_View bv, String title, Book_Item book,
			ArrayList<Base_Artifact> list_escape,
			ArrayList<Base_Artifact> selections, boolean foot) {
		super(cols, kh, smv);
		PREVIOUS_SCENE = bv.PARENT_SCENE;
		LIST = selections;
		LIST_EXCEPTION = list_escape == null ? new ArrayList<Base_Artifact>(0)
				: list_escape;
		BOOK = book;
		TITLE = title;
		FOOT = new ArrayList<Base_Artifact>();
		if (foot)
			FOOT.add(Belongings.get(-1));
		boolean flag;
		try {
			flag = getMultiBook() != null;
		} catch (Exception e) {
			flag = true;
		}
		MULTI_SELECTION = flag;
	}

	/**
	 * 複数選択させるときはgetMultiBook()をover-ride
	 * 
	 * @param cols
	 * @param kh
	 * @param bv
	 * @param title
	 * @param book
	 * @param list_escape
	 *            除外(黒表示)するアイテム
	 * @param selections
	 */
	private Item_List(int cols, KeyHolder kh, Base_View bv, String title,
			Book_Item book, ArrayList<Base_Artifact> list_escape,
			ArrayList<Base_Artifact> selections) {
		this(cols, kh, new Item_List_View(Scene_Action.getMe().CURRENT_VIEW),
				bv, title, book, list_escape, selections, false);
	}

	/**
	 * メニューシーンから<br />
	 * 除外なし全手持ちアイテム
	 * 
	 * @param kh
	 * @param bv
	 * @param title
	 * @param book
	 */
	public Item_List(KeyHolder kh, Base_View bv, String title, Book_Item book) {
		this(10, kh, bv, title, book, new ArrayList<Base_Artifact>(),
				Belongings.getListItems());
	}

	/**
	 * メニューシーンから<br />
	 * 除外あり
	 * 
	 * @param kh
	 * @param bv
	 * @param title
	 * @param book
	 */
	public Item_List(KeyHolder kh, Base_View bv, String title, Book_Item book,
			ArrayList<Base_Artifact> escape) {
		this(10, kh, bv, title, book, escape, Belongings.getListItems());
	}

	/**
	 * 通常シーンから
	 */
	public Item_List(String title, Book_Item book_Item,
			ArrayList<Base_Artifact> selections) {
		this(title, book_Item, null, selections);
	}

	/**
	 * 通常シーンから
	 */
	public Item_List(String title, Book_Item book,
			ArrayList<Base_Artifact> list_escape,
			ArrayList<Base_Artifact> selections) {
		this(true, title, book, list_escape, selections);
	}

	@Override
	protected void action_else() {
	}

	@Override
	protected void action_else(ACTION a) {
		if (a == ACTION.FIRE && MULTI_SELECTION) {
			action_multi();
		} else {
			super.action_else(a);
		}
	}

	@Override
	protected void action_enter() {
		if (MULTI_SELECTION && !holding_mutli_selection.isEmpty()) {
			work(getMultiBook(getSelections()));
		} else {
			holding_mutli_selection.clear();
			super.action_enter();
		}
	}

	@Override
	protected void action_enter(int index) {
		super.action_enter(index);
	}

	private void action_multi() {
		Integer index = getMultiItemIndex();
		if (holding_mutli_selection.contains(index)) {
			SE.SYSTEM_CANCEL.play();
			holding_mutli_selection.remove(index);
		} else {
			SE.SYSTEM_ENTER.play();
			holding_mutli_selection.add(index);
		}
	}

	@Override
	protected void arrow_x_less_than_zero() {
		page--;
		if (page < 0) {
			if (!FOOT.isEmpty()) {
				setNextScene(new Item_List_Foot(this));
				return;
			}
			page = getMaxPage() - 1;
		}
		setContents();

	}

	@Override
	protected void arrow_x_more_than_max() {
		page++;
		if (page >= getMaxPage()) {
			if (!FOOT.isEmpty()) {
				setNextScene(new Item_List_Foot(this));
				return;
			}
			page = 0;
		}
		setContents();
	}

	public Integer[] getHoldingMultiSelection() {
		Integer[] arr = new Integer[holding_mutli_selection.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = holding_mutli_selection.get(i);
		}
		return arr;
	}

	public Integer getIndex(int item_index) {
		item_index -= page * COL;
		if (item_index < 0 || item_index >= COL) {
			return null;
		} else {
			return item_index;
		}

	}

	public int getItemIndex() {
		return page * COL + getIndex();
	}

	public int getMaxPage() {
		return (int) Math.ceil((double) LIST.size() / COL);
	}

	public ArrayList<Integer> getMulti() {
		return holding_mutli_selection;
	}

	/**
	 * if this list forbid you from multi selecting, return null. otherwise,
	 * override this method.
	 * 
	 * @return
	 */
	protected Book getMultiBook(Base_Artifact... selected_artifacts) {
		return null;
	}

	protected int getMultiItemIndex() {
		return getItemIndex();
	}

	protected int getNextPageTopIndex() {
		return (page + 1) * COL;
	}

	@Override
	protected int getNumberOfCols() {
		int next_top_index = getNextPageTopIndex();
		if (LIST.size() > next_top_index) {
			return COL;
		} else {
			return LIST.size() - getPageTopIndex();
		}
	}

	public int getPage() {
		return page;
	}

	protected int getPageTopIndex() {
		return page * COL;
	}

	@Override
	public Base_Scene getPreviousScene() {
		return PREVIOUS_SCENE;
	}

	protected Base_Artifact[] getSelections() {
		Base_Artifact[] as = new Base_Artifact[holding_mutli_selection.size()];
		for (int i = 0; i < as.length; i++) {
			int index = holding_mutli_selection.get(i);
			if (index == -1) {
				as[i] = Belongings.get(-1);
			} else {
				as[i] = LIST.get(index);
			}
		}
		return as;
	}

	public String getTop() {
		StringBuilder sb = new StringBuilder(TITLE);
		int max = getMaxPage();
		if (max == 0 || max == 1) {
			if (this instanceof Item_List_Foot)
				sb.append("(足下)");
		} else {
			setPages(sb);
		}
		if (sb.length() == 0) {
			setPages(sb);
		}
		return sb.toString();
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		if (y >= getNumberOfCols()) {
			y = getNumberOfCols() - 1;
		}
		((Item_List_View) CURRENT_VIEW).createMenuWindow();
		CONTENTS.clear();
		for (int i = getPageTopIndex(); i < getNextPageTopIndex(); i++) {
			if (i < LIST.size()) {
				setContents(LIST.get(i));
			} else {
				break;
			}
		}
		((Item_List_View) CURRENT_VIEW).setTopWindow();
	}

	public boolean isException(Base_Artifact a) {
		return LIST_EXCEPTION.contains(a);
	}

	protected void setContents() {
		initializeContents(null);
	}

	protected void setContents(final Base_Artifact a) {
		if (isException(a)) {
			setContents(Color.BLACK.toString().concat(a.getName()), null,
					new Book() {
						@Override
						protected void work() {
							Message.set("それは選択できない");
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
					setNextScene(Scene_Action.getMe());
				}
			}, a);
		}
	}

	@Override
	protected void setExplain() {
		if (MULTI_SELECTION && KH.isContraining(ACTION.FIRE)) {
			action_multi();
		}
	}

	@Override
	protected void setNextScene(Base_Scene base_scene) {
		if (TITLE == null && base_scene instanceof Base_Scene_Menu) {
			ItemExplainWindow window = ((Item_List_View) CURRENT_VIEW).BOTTOM_WINDOW;
			if (window != null)
				window.setNextScene((Base_Scene_Menu) base_scene);
		}
		super.setNextScene(base_scene);
	}

	protected void setPages(StringBuilder sb) {
		sb.append("(");
		sb.append(page + 1);
		sb.append("/");
		sb.append(getMaxPage());
		sb.append(")");
	}

	@Override
	public void triger() {
		ItemExplainWindow window = ((Item_List_View) CURRENT_VIEW).BOTTOM_WINDOW;
		if (window != null)
			window.setNextScene(null);
	}

}