package dangeon.latest.scene.action.menu;

import java.util.ArrayList;

import main.Listener.ACTION;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_Scene;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.first.Scene_Menu_First;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.latest.util.view_window.WindowFrame;

public abstract class Base_Scene_Menu extends Base_Scene {
	public class MenuContent {
		public final Book BOOK;
		public final String[] EXPLAIN;
		public final String NAME;
		public final Object OBJECT;

		private MenuContent(String name, Book book) {
			this(name, null, book, null);
		}

		public MenuContent(String name, String explain, Book book) {
			this(name, new String[] { explain }, book, null);
		}

		private MenuContent(String name, String[] explain, Book book) {
			this(name, explain, book, null);
		}

		private MenuContent(String name, String[] explain, Book book,
				Object object) {
			NAME = name == null ? "" : name;
			EXPLAIN = explain == null ? new String[] { "NULL(未実装)" } : explain;
			BOOK = book;
			OBJECT = object;
		}

	}

	protected final ArrayList<MenuContent> CONTENTS = new ArrayList<MenuContent>();

	public Integer ROW, COL;

	public final UnderMenuWindow SUB_WINDOW;

	protected int x, y;

	public Base_Scene_Menu(Integer cols, Integer row, KeyHolder kh,
			Base_Scene_Menu_View smv) {
		super(kh, smv);
		SUB_WINDOW = initializeSubWindow();
		COL = cols;
		ROW = row;
	}

	public Base_Scene_Menu(Integer cols, KeyHolder kh, Base_Scene_Menu_View smv) {
		this(cols, null, kh, smv);
	}

	public Base_Scene_Menu(KeyHolder kh, Base_Scene_Menu_View smv) {
		this(null, kh, smv);
	}

	/**
	 * これよりはaction_enterなどを優先してover-rideする
	 */
	@Override
	public boolean action(ACTION a) {
		try {
			switch (a) {
			case ENTER:
				SE.SYSTEM_ENTER.play();
				action_enter();
				return END;
			case CANCEL:
			case MENU:
				action_cancel();
				return END;
			default:
				action_else(a);
				return END;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return END;
		}
	}

	protected void action_cancel() {
		SE.SYSTEM_CANCEL.play();
		if (getPreviousScene() instanceof Scene_Menu_First) {
			setNextScene(Scene_Action.getMe());
		} else {
			Base_Scene scene = getPreviousScene();
			setNextScene(scene);
			if (scene instanceof Base_Scene_Menu) {
				((Base_Scene_Menu) scene).setExplain();
			}
		}
	}

	protected abstract void action_else();

	protected void action_else(ACTION a) {
		action_else();
	}

	protected void action_enter() {
		if (!CONTENTS.isEmpty())
			action_enter(getIndex());
		else
			setNextScene(getPreviousScene());
	}

	/**
	 * index番号でエンターを押されたことにする
	 * 
	 * @param index
	 */
	protected void action_enter(int index) {
		if (isContentDeprecated(index))
			((Book) CONTENTS.get(index).OBJECT).work();
		else
			CONTENTS.get(index).BOOK.work();
	}

	@Override
	public boolean arrow(DIRECTION d) {
		SE.SYSTEM_CURSOR.play();
		if (CONTENTS.isEmpty())
			return END;
		x += d.X;
		y += d.Y;
		if (x < 0) {
			x += getNumberOfRows();
			arrow_x_less_than_zero();
		} else if (x >= getNumberOfRows()) {
			x -= getNumberOfRows();
			arrow_x_more_than_max();
		}
		if (y < 0) {
			y += getNumberOfCols();
			arrow_y_less_than_zero();
		} else if (y >= getNumberOfCols()) {
			y -= getNumberOfCols();
			arrow_y_more_than_max();
		}
		int i = getIndex();
		if (isContentDeprecated_Perfectedly(i) || getContentName(i).isEmpty()) {
			if (isThisCurrentScene()) {
				arrow(d);
			}
		} else {
			setExplain();
		}
		return END;
	}

	protected void arrow_function_min_max() {
	}

	protected void arrow_x_less_than_zero() {
	}

	protected void arrow_x_more_than_max() {
	}

	protected void arrow_y_less_than_zero() {
	}

	protected void arrow_y_more_than_max() {
	}

	public void ascX() {
		x++;
		if (x >= getNumberOfRows()) {
			x -= getNumberOfRows();
			arrow_x_more_than_max();
		}
	}

	public void decX() {
		x--;
		if (x < 0) {
			x += getNumberOfRows();
			arrow_x_less_than_zero();
		}
	}

	public MenuContent getContent(int index) {
		return CONTENTS.get(index);
	}

	public String getContentName(int index) {
		return CONTENTS.get(index).NAME;
	}

	public int getContentSize() {
		return CONTENTS.size();
	}

	public MenuContent getContentSize(int index) {
		return CONTENTS.get(index);
	}

	public int getIndex() {
		return y * ROW + x;
	}

	protected int getNumberOfCols() {
		return COL;
	}

	protected int getNumberOfRows() {
		return ROW;
	}

	public MenuContent getSelectedContent() {
		if (CONTENTS.isEmpty())
			return null;
		else if (getIndex() < 0)
			return null;
		else
			return CONTENTS.get(getIndex());

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	protected final void initializeContents() {
		initializeContents(CONTENTS);
		if (COL == null) {
			COL = CONTENTS.size();
		}
		if (ROW == null) {
			int row = (int) Math.ceil((double) CONTENTS.size() / COL);
			ROW = row < 1 ? 1 : row;
		}
		x = y = 0;
		sort();
		initialX_Y();
		setExplain();
	}

	protected abstract void initializeContents(ArrayList<MenuContent> list);

	protected UnderMenuWindow initializeSubWindow() {
		Base_Scene parent_scene = CURRENT_VIEW.PREVIOUSE_VIEW.PARENT_SCENE;
		if (parent_scene == null) {
			return null;
		} else if (parent_scene instanceof Base_Scene_Menu) {
			if (((Base_Scene_Menu) parent_scene).SUB_WINDOW == null) {
				return null;
			}
			return ((Base_Scene_Menu) parent_scene).SUB_WINDOW;
		} else {
			return null;
		}
	}

	protected void initialX_Y() {
		for (int i = 0; i < CONTENTS.size(); i++) {
			if (isContentDeprecated_Perfectedly(i)
					|| getContentName(i).isEmpty()) {
				continue;
			} else {
				x = i % ROW;
				y = i / ROW;
				break;
			}
		}
	}

	public boolean isContentDeprecated(int index) {
		return CONTENTS.size() <= index || CONTENTS.get(index).BOOK == null;
	}

	public boolean isContentDeprecated_Perfectedly(int index) {
		if (index >= CONTENTS.size())
			return true;
		return CONTENTS.get(index).BOOK == null
				&& CONTENTS.get(index).OBJECT == null;
	}

	protected void setContents(Book book) {
		setContents(book.TITLE, "", book);
	}

	protected void setContents(String name) {
		CONTENTS.add(new MenuContent(name, new Book() {
			@Override
			protected void work() {
			}
		}));
	}

	protected void setContents(String name, Book book) {
		setContents(name, "", book);
	}

	protected void setContents(String name, String explain, Book book) {
		CONTENTS.add(new MenuContent(name, new String[] { explain }, book));
	}

	protected void setContents(String name, String[] explain, Book book) {
		CONTENTS.add(new MenuContent(name, explain, book));
	}

	protected void setContents(String name, String[] explain, Book book,
			Object obj) {
		CONTENTS.add(new MenuContent(name, explain, book, obj));
	}

	protected void setDeprecatedContents(String name, Book book) {
		CONTENTS.add(new MenuContent(name, null, null, book));
	}

	protected void setDeprecatedContents(String name, String explain) {
		CONTENTS.add(new MenuContent(name, new String[] { explain }, null));
	}

	protected void setDeprecatedContents(String name, String explain, Book book) {
		CONTENTS.add(new MenuContent(name, new String[] { explain }, null, book));
	}

	protected void setDeprecatedContents(String name, String[] explain,
			Book book) {
		CONTENTS.add(new MenuContent(name, explain, null, book));
	}

	protected void setDeprecatedContentsHorizon() {
		CONTENTS.add(new MenuContent(WindowFrame.HORIZON, null, null, null));
	}

	protected void setDeprecatedPerfetedlyContents() {
		CONTENTS.add(new MenuContent("", null, null, null));
	}

	protected void setDeprecatedPerfetedlyContents(int size) {
		for (int j = 0; j < size; j++) {
			setDeprecatedPerfetedlyContents();
		}
	}

	protected void setDeprecatedPerfetedlyContents(String name) {
		CONTENTS.add(new MenuContent(name, null, null, null));
	}

	protected void setExplain() {
		if (SUB_WINDOW != null && getSelectedContent() != null)
			SUB_WINDOW.setExplain(getSelectedContent().EXPLAIN);
	}

	@Override
	protected void setNextScene(Base_Scene base_scene) {
		super.setNextScene(base_scene);
		if (base_scene instanceof Base_Scene_Menu)
			((Base_Scene_Menu) base_scene).triger();
	}

	private void sort() {
		if (!vertical_sort())
			return;
		int SIZE = CONTENTS.size();
		int remainder = SIZE % ROW;
		if (remainder == 0)
			remainder = ROW;
		int COL = (SIZE + ROW - 1) / ROW;
		int[] heads = new int[ROW];
		for (int i = 0; i < heads.length; i++) {
			if (i == 0)
				heads[i] = 0;
			else {
				heads[i] = heads[i - 1] + COL;
				if (--remainder < 0)
					heads[i]--;
			}
		}
		ArrayList<MenuContent> list = new ArrayList<Base_Scene_Menu.MenuContent>(
				SIZE);
		l: for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				int index = heads[j] + i;
				if (index >= CONTENTS.size())
					break l;
				list.add(CONTENTS.get(index));
				if (list.size() == SIZE)
					break l;
			}
		}
		CONTENTS.clear();
		for (MenuContent menuContent : list) {
			CONTENTS.add(menuContent);
		}
	}

	protected void triger() {
	}

	protected boolean vertical_sort() {
		return false;
	}

	public void work(Book book) {
		book.work();
	}

}
