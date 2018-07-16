package dangeon.latest.scene.action.menu.first.item.list;

import main.res.SE;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.item.list.command.Scene_Menu_First_Item_List_Command;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;

public class Scene_Menu_First_Item_List extends Item_List {

	private int init_y;

	public Scene_Menu_First_Item_List(int cols, KeyHolder kh, Base_View bv) {
		super(cols, kh, new Scene_Menu_First_Item_List_View(
				Scene_Action.getMe().CURRENT_VIEW), bv);
	}

	public Scene_Menu_First_Item_List(KeyHolder kh, Base_View bv) {
		this(10, kh, bv);
	}

	public Scene_Menu_First_Item_List(KeyHolder kh, Base_View bv, int page,
			int y) {
		this(kh, bv);
		if (page == -1 || page > getMaxPage() - 1) {
			page = getMaxPage() - 1;
		}
		this.page = page;
		init_y = y;
		if (page >= 3)
			((Scene_Menu_First_Item_List_View) CURRENT_VIEW).setRed();
	}

	public Scene_Menu_First_Item_List(KeyHolder kH, Base_View v, int ii, int j,
			Scene_Menu_First_Item_List___Foot foot) {
		this(kH, v, ii, j);
		Integer[] multi = foot.getHoldingMultiSelection();
		for (int i = 0; i < multi.length; i++) {
			holding_mutli_selection.add(cord(multi[i]));
		}
	}

	@Override
	protected void action_else() {
		SE.SYSTEM_ARRANGEMENT.play();
		LIST = Belongings.sort();
		setContents();
	}

	@Override
	protected void arrow_x_less_than_zero() {
		if (page == 3)
			change(false);
		page--;
		if (page < 0) {
			if (Belongings.get(-1) != null) {
				page++;
				setNextScene(new Scene_Menu_First_Item_List___Foot(KH,
						CURRENT_VIEW.PREVIOUSE_VIEW, this));
				return;
			} else {
				page = getMaxPage() - 1;
				if (page >= 3)
					change(true);
			}
		}
		setContents();
	}

	@Override
	protected void arrow_x_more_than_max() {
		page++;
		if (page == 3)
			change(true);
		if (page >= getMaxPage()) {
			if (Belongings.get(-1) != null) {
				page--;
				setNextScene(new Scene_Menu_First_Item_List___Foot(KH,
						CURRENT_VIEW.PREVIOUSE_VIEW, this));
				return;
			} else {
				page = 0;
				change(false);
			}
		}
		setContents();
	}

	void change(boolean b) {
		Scene_Menu_First_Item_List_View u = ((Scene_Menu_First_Item_List_View) CURRENT_VIEW);
		if (b)
			u.MENU_WINDOW.change(Belongings.isOverMax() ? 10
					: Belongings.POCKET_MAX, true);
		else
			u.MENU_WINDOW.change(10, false);
	}

	private int cord(int i) {
		return (i + 1) * -1;
	}

	@Override
	protected Book getMultiBook(final Base_Artifact... selected_artifacts) {
		return new Book() {
			@Override
			protected void work() {
				setNextScene(new Scene_Menu_First_Item_List_Command(KH,
						CURRENT_VIEW, selected_artifacts));
			}
		};
	}

	@Override
	protected void initialX_Y() {
		super.initialX_Y();
		y = init_y;
	}

	@Override
	protected void setContents(final Base_Artifact a) {
		final boolean flag = this instanceof Scene_Menu_First_Item_List___Foot;
		setContents(a.getColoredName(), null, new Book() {
			@Override
			protected void work() {
				setNextScene(new Scene_Menu_First_Item_List_Command(KH,
						CURRENT_VIEW, a, flag));
			}
		}, a);
	}

	@Override
	public void triger() {
		if (Belongings.isEmpty()) {
			Message.set("何も持っていない");
			setNextScene(Scene_Action.getMe());
		}
		super.triger();
	}
}