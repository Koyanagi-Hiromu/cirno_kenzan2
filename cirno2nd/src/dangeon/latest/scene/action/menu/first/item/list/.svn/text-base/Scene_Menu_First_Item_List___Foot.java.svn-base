package dangeon.latest.scene.action.menu.first.item.list;

import java.util.ArrayList;

import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.menu.first.item.list.command.Scene_Menu_First_Item_List_Command;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.system.KeyHolder;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;

public class Scene_Menu_First_Item_List___Foot extends
		Scene_Menu_First_Item_List {

	private final boolean flag_first;

	public Scene_Menu_First_Item_List___Foot(KeyHolder kh, Base_View bv) {
		this(kh, bv, false);
	}

	public Scene_Menu_First_Item_List___Foot(KeyHolder kh, Base_View bv,
			boolean first) {
		super(1, kh, bv);
		flag_first = first;
	}

	public Scene_Menu_First_Item_List___Foot(KeyHolder kh, Base_View bv,
			int page, int y) {
		this(kh, bv);
		this.page = page;
		this.y = y;
	}

	public Scene_Menu_First_Item_List___Foot(KeyHolder kH, Base_View v,
			Scene_Menu_First_Item_List scene_Menu_First_Item_List) {
		this(kH, v);
		Integer[] multi = scene_Menu_First_Item_List.getHoldingMultiSelection();
		for (int i = 0; i < multi.length; i++) {
			holding_mutli_selection.add(cord(multi[i]));
		}
	}

	@Override
	protected void action_else() {
	}

	@Override
	protected void arrow_x_less_than_zero() {
		if (!Belongings.isEmpty()) {
			setNextScene(new Scene_Menu_First_Item_List(KH,
					CURRENT_VIEW.PREVIOUSE_VIEW, -1, 0, this));
		}
	}

	@Override
	protected void arrow_x_more_than_max() {
		if (!Belongings.isEmpty()) {
			setNextScene(new Scene_Menu_First_Item_List(KH,
					CURRENT_VIEW.PREVIOUSE_VIEW, 0, 0, this));
		}
	}

	private int cord(int key) {
		return (key + 1) * -1;
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
	protected Base_Artifact[] getSelections() {
		Integer[] multi = this.getHoldingMultiSelection();
		this.holding_mutli_selection.clear();
		for (int i = 0; i < multi.length; i++) {
			this.holding_mutli_selection.add(cord(multi[i]));
		}
		return super.getSelections();
	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		setContents(Belongings.get(-1));
		if (flag_first) {
			action_enter(0);
		}
	}

	@Override
	public void triger() {
		if (Belongings.get(-1) == null) {
			Message.set("特に何もない");
			setNextScene(Scene_Action.getMe());
		}
	}

}