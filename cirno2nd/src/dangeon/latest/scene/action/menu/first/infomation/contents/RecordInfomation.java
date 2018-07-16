package dangeon.latest.scene.action.menu.first.infomation.contents;

import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.scrool.Scrool;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.ScroolWindow;
import dangeon.view.detail.View_Sider_Info;

public class RecordInfomation extends Scrool {
	public RecordInfomation(KeyHolder kh, Base_View bv) {
		super(kh, bv, View_Sider_Info.getRecord());
	}

	@Override
	protected ScroolWindow createScroolWindow(String... contents) {
		return new ScroolWindow(this, contents);
	}

	@Override
	protected int getFirstIndex() {
		int i = View_Sider_Info.getRecordSize() - COL - 1;
		if (i < 0)
			i = 0;
		return i;
	}

}