package dangeon.controller.listener.menu;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import main.Listener.ACTION;
import main.Second_Firster;
import main.util.DIRECTION;
import main.util.Show;
import dangeon.controller.DangeonScene;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.view.constant.NormalFont;
import dangeon.view.constant.SCREEN;
import dangeon.view.util.Window;

public abstract class Base_MenuItem {
	private static Base_MenuItem now = null;

	public static Base_MenuItem getNow() {
		return now;
	}

	protected int page, y;
	public static final int Y_LENGTH = 10;
	private ArrayList<Base_Artifact> list = new ArrayList<Base_Artifact>();
	public final BufferedImage TITLE_IMAGE;
	public final String TITLE;

	private DangeonScene DS;

	public final boolean IS_EMPTY;

	public Base_MenuItem(String tit, Base_Artifact... as) {
		if (now != null) {
			Show.showCriticalErrorMessageDialog("SeleceMenu表示中に新たなSelectMenu表示命令が呼ばれました。");
		}
		TITLE_IMAGE = initTitle(tit);
		TITLE = tit;
		IS_EMPTY = as.length == 0;
		for (Base_Artifact a : as) {
			list.add(a);
		}
		now = this;
	}

	public Base_MenuItem(String tit, Collection<Base_Artifact> as) {
		TITLE_IMAGE = initTitle(tit);
		TITLE = tit;
		IS_EMPTY = as.isEmpty();
		for (Base_Artifact a : as) {
			list.add(a);
		}
		now = this;
	}

	public Base_MenuItem(String tit, HashMap<Integer, Base_Artifact> as) {
		TITLE_IMAGE = initTitle(tit);
		TITLE = tit;
		IS_EMPTY = as.isEmpty();
		for (int i = 0; i < as.size(); i++) {
			list.add(as.get(i));
		}
		now = this;
	}

	void actionKeyPressed(ACTION action) {
		switch (action) {
		case ENTER:
			if (IS_EMPTY) {
				pressedChancel();
			} else {
				pressedEnter(get());
			}
			break;
		case CANCEL:
			pressedChancel();
			break;
		}
	}

	void arrowKeyPressed(DIRECTION direction) {
		if (list.size() > Y_LENGTH) {
			arrowKeyPressed_ExistPage(direction);
		} else {
			arrowKeyPressed_ExistNoPage(direction);
		}
	}

	private void arrowKeyPressed_ExistNoPage(DIRECTION direction) {
		int _y = y;
		switch (direction) {
		case UP:
			_y--;
			break;
		case DOWN:
			_y++;
			break;
		case LEFT:
		case RIGHT:
			return;
		}
		if (_y < 0)
			_y = list.size() - 1;
		if (_y > list.size() - 1) {
			_y = 0;
		}
		y = _y;
	}

	private void arrowKeyPressed_ExistPage(DIRECTION direction) {
		int _x = page, _y = y;
		switch (direction) {
		case UP:
			_y--;
			break;
		case DOWN:
			_y++;
			break;
		case LEFT:
			_x--;
			break;
		case RIGHT:
			_x++;
			break;
		}
		while (true) {
			if (_x < 0 || _x > getMaxPage() - 1) {
				if (_x < 0) {
					_x = getMaxPage() - 1;
				} else {
					_x = 0;
				}
			}
			int MAX_Y = list.size() - _x * Y_LENGTH;
			if (MAX_Y < 1) {
				_x--;
				continue;
			}
			if (MAX_Y > Y_LENGTH)
				MAX_Y = Y_LENGTH;
			if (_y < 0)
				_y = MAX_Y - 1;
			if (_y > MAX_Y - 1) {
				if (_x == page)
					_y = 0;
				else
					_y = MAX_Y - 1;
			}
			break;
		}
		page = _x;
		y = _y;
	}

	protected final void end() {
		DangeonScene.setScene(DS);
	}

	public Base_Artifact get() {
		return list.get(page * Y_LENGTH + y);
	}

	public Base_Artifact get(int index) {
		return list.get(index);
	}

	public int getMaxPage() {
		return (list.size() - 1) / Y_LENGTH + 1;
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return list.size();
	}

	public int getY() {
		return y;
	}

	private BufferedImage initTitle(String tit) {
		if (tit == null) {
			return null;
		}
		int w = Second_Firster.ME.getFontMetrics(NormalFont.NORMALFONT)
				.stringWidth(tit) + SCREEN.X_MARGINE * 2;
		int h = Window.getNarrowWindowH_SmallFont(1);
		BufferedImage im = Window.get(w, h);
		return im;
	}

	protected void pressedChancel() {
		end();
	}

	protected abstract void pressedEnter(Base_Artifact a);

	public void work() {
		DS = DangeonScene.getPresentScene();
		DangeonScene.setScene(DangeonScene.SELECT_ITEM);
	}
}
