package dangeon.controller.listener.menu;

import java.util.ArrayList;

import main.Listener.ACTION;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.DangeonScene;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.Base_SelectBox;
import dangeon.view.detail.View_SelectStairs;
import dangeon.view.util.StringFilter;

public class Menu_Select {
	private static int y;
	private static int y_length;
	private static int key_unvalid_time;
	private final static int KEY_UNVALID_TIME_MAX = 7;

	private final static ArrayList<Object> list = new ArrayList<Object>();
	private static Base_SelectBox select_box;

	public static void actionKeyPressed(ACTION action) {
		if (key_unvalid_time > 0) {
			list.add(action);
			return;
		}
		switch (action) {
		case ENTER:
			SE.SYSTEM_ENTER.play();
			select_box.pushEnter(select_box.getY(y));
			break;
		case CANCEL:
			SE.SYSTEM_CANCEL.play();
			select_box.pushCancel();
			break;
		}
	}

	public static void arrowKeyPressed(DIRECTION direction) {
		if (key_unvalid_time > 0) {
			list.add(direction);
			return;
		}
		switch (direction) {
		case UP:
			SE.SYSTEM_CURSOR.play();
			y--;
			break;
		case DOWN:
			SE.SYSTEM_CURSOR.play();
			y++;
			break;
		}
		if (y < 0) {
			y = y_length - 1;
		} else if (y >= y_length) {
			y = 0;
		}
	}

	public static String[] getCommands() {
		return select_box.SELECT;
	}

	public static int getY() {
		return y;
	}

	public static void init(Base_SelectBox sb, boolean se_flag) {
		if (se_flag) {
			SE.SYSTEM_MENU.play();
		}
		y = 0;
		select_box = sb;
		y_length = sb.SELECT.length;
		int index = 0;
		int length = 0;
		for (int i = 0; i < y_length; i++) {
			if (length < StringFilter.getPlainString(sb.SELECT[i]).length()) {
				index = i;
				length = StringFilter.getPlainString(sb.SELECT[i]).length();
			}
		}
		key_unvalid_time = Message.isTaskEmpty() ? 0 : KEY_UNVALID_TIME_MAX;
		View_SelectStairs.setWndow(
				StringFilter.getPlainString(sb.SELECT[index]), y_length);
		DangeonScene.setScene(DangeonScene.SELECT);
	}

	public static void setY(int _y) {
		y = _y;
	}

	public static void upDate() {
		if (key_unvalid_time > 0) {
			key_unvalid_time--;
			if (key_unvalid_time == 0) {
				key_unvalid_time = -1;
				for (Object o : list) {
					if (o instanceof DIRECTION) {
						arrowKeyPressed((DIRECTION) o);
					} else if (o instanceof ACTION) {
						actionKeyPressed((ACTION) o);
					}
				}
				list.clear();
			}
		}
	}

}
