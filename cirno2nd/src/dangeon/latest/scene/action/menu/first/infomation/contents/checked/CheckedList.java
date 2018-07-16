package dangeon.latest.scene.action.menu.first.infomation.contents.checked;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import main.res.SE;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.scrool.Scrool;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.ScroolWindow;
import dangeon.model.map.PresentField;
import dangeon.model.object.artifact.item.check.Checker;

public class CheckedList extends Scrool {
	private static String[] get(int i) {
		if (i == 0 && PresentField.get().getRandomMap().UN_CHECKED_LEVEL == 1) {
			return new String[] { Color.GRAY.toString() + "このダンジョンでは",
					"カードは識別されています" };
		}
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		for (Checker c : Checker.get(i)) {
			if (c.isExist()) {
				if (c.isStaticChecked())
					list1.add(c.getCheckedName());
				else
					list2.add(Color.YELLOW + c.toString());
			} else
				break;
		}
		Collections.sort(list1);
		if (!list1.isEmpty())
			list1.add(0, "【識別済】");
		if (!list2.isEmpty())
			list2.add(0, Color.YELLOW.toString() + "【未識別】");
		for (String string : list2) {
			list1.add(string);
		}
		if (list1.isEmpty()) {
			list1.add("（まだ何も拾っていない）");
		} else {
			list1.add(0, Color.WHITE + list1.remove(0));
		}
		list1.add("");
		return list1.toArray(new String[0]);
	}

	public final int INDEX;

	public final Base_View BACK;

	public CheckedList(KeyHolder kh, Base_View bv, int i) {
		super(kh, Scene_Action.getMe().CURRENT_VIEW, get(i));
		BACK = bv;
		INDEX = i;
	}

	@Override
	protected void action_cancel() {
		SE.SYSTEM_CANCEL.play();
		setNextScene(new CheckedList_Command(this));
	}

	@Override
	protected ScroolWindow createScroolWindow(String... contents) {
		return new ScroolWindow(this, 247, contents);
	}

}