package dangeon.latest.scene.action.itemlist;

import java.util.ArrayList;

import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Book;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;

public class Item_List_Foot extends Item_List {
	public final Item_List ITEM_LIST;

	public Item_List_Foot(Item_List l) {
		super(1, l.KH, new Item_List_View(Scene_Action.getMe().CURRENT_VIEW),
				l.CURRENT_VIEW.PREVIOUSE_VIEW, l.TITLE, l.BOOK,
				l.LIST_EXCEPTION, l.FOOT, false);
		ITEM_LIST = l;
		page = 0;
		Integer[] multi = ITEM_LIST.getHoldingMultiSelection();
		for (int i = 0; i < multi.length; i++) {
			holding_mutli_selection.add(cord(multi[i]));
		}
	}

	@Override
	protected void arrow_x_less_than_zero() {
		goBack(ITEM_LIST.getMaxPage() - 1);
	}

	@Override
	protected void arrow_x_more_than_max() {
		goBack(0);
	}

	private int cord(int i) {
		return (i + 1) * -1;
	}

	private int decord(int i) {
		return (i + 1) * -1;
	}

	@Override
	protected Book getMultiBook(final Base_Artifact... selected_artifacts) {
		return ITEM_LIST.getMultiBook(selected_artifacts);
	}

	@Override
	protected Base_Artifact[] getSelections() {
		Integer[] multi = this.getHoldingMultiSelection();
		this.holding_mutli_selection.clear();
		for (int i = 0; i < multi.length; i++) {
			this.holding_mutli_selection.add(decord(multi[i]));
		}
		ArrayList<Base_Artifact> temp = LIST;
		LIST = Belongings.getListItems();
		Base_Artifact[] list = super.getSelections();
		LIST = temp;
		return list;
	}

	private void goBack(int page) {
		ITEM_LIST.page = page;
		ITEM_LIST.setContents();
		ITEM_LIST.holding_mutli_selection.clear();
		Integer[] multi = this.getHoldingMultiSelection();
		for (int i = 0; i < multi.length; i++) {
			ITEM_LIST.holding_mutli_selection.add(decord(multi[i]));
		}
		setNextScene(ITEM_LIST);
	}
}