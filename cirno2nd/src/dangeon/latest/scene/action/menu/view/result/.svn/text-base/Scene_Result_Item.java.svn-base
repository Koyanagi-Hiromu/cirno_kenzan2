package dangeon.latest.scene.action.menu.view.result;

import main.res.SE;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.system.KeyHolder;

public class Scene_Result_Item extends Item_List {
	private final Scene_Result_Info INFO;
	private final boolean first;

	public Scene_Result_Item(KeyHolder kh, Base_View bv,
			Scene_Result_Info info, boolean first_or_end) {
		super("持ち物", null, Scene_Result_Info.result.ITEMS);
		INFO = info;
		first = first_or_end;
	}

	@Override
	protected void action_cancel() {
		SE.SYSTEM_CURSOR.play();
		setNextScene(INFO);
	}

	@Override
	protected void action_enter() {
		setNextScene(INFO);
		INFO.action_enter();
	}

	@Override
	protected void arrow_x_less_than_zero() {
		page--;
		if (page < 0) {
			page = getMaxPage() - 1;
			setNextScene(INFO);
		} else {
			setContents();
		}
	}

	@Override
	protected void arrow_x_more_than_max() {
		page++;
		if (page >= getMaxPage()) {
			page = 0;
			setNextScene(INFO);
		} else {
			setContents();
		}
	}

	@Override
	protected void initialX_Y() {
		if (!first)
			page = getMaxPage() - 1;
		super.initialX_Y();
	}

	@Override
	public void triger() {
	}

}