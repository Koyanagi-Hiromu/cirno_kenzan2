package dangeon.latest.scene.action.strage;

import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.itemlist.Item_List_View;
import dangeon.latest.util.view_window.ItemExplainWindow;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.StackCountMenuWindow;
import dangeon.view.constant.NormalFont;

/**
 * 橙の倉庫リスト用View。行の右側に在庫数（×個数）を表示するウィンドウを使う。
 */
public class ChenStorage_List_View extends Item_List_View {

	public ChenStorage_List_View(Base_View bv) {
		super(bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		Item_List smfil = (Item_List) PARENT_SCENE;
		BOTTOM_WINDOW = new ItemExplainWindow(smfil);
		TOP_WINDOW = createTop();
		return new StackCountMenuWindow(this, MAIN_W,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE));
	}
}
