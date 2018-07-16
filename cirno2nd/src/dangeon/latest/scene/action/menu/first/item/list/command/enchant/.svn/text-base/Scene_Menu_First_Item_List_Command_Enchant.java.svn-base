package dangeon.latest.scene.action.menu.first.item.list.command.enchant;

import java.util.ArrayList;

import dangeon.controller.TurnSystemController;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.system.KeyHolder;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Enchant;

public class Scene_Menu_First_Item_List_Command_Enchant extends Base_Scene_Menu {
	private class MyBook extends Book {
		private final Enchant E;

		public MyBook(Enchant e) {
			E = e;
		}

		@Override
		protected void work() {
			boolean success = false;
			if (E.getEnchant() == SELECTED_ARTIFACT) {
				success = Enchant.tryToRemove(SELECTED_ARTIFACT);
			} else {
				success = E.tryToSet(SELECTED_ARTIFACT);
			}
			setNextScene(Scene_Action.getMe());
			if (success) {
				TurnSystemController.callMeToStartEnemyTurn();
			}
		}

	}

	public final Base_Artifact SELECTED_ARTIFACT;

	public Scene_Menu_First_Item_List_Command_Enchant(KeyHolder kh,
			Base_View bv, Base_Artifact a) {
		super(kh, new Scene_Menu_First_Item_List_Command_Enchant_View(bv));
		SELECTED_ARTIFACT = a;
	}

	@Override
	protected void action_else() {

	}

	@Override
	protected void initializeContents(ArrayList<MenuContent> list) {
		CONTENTS.clear();
		setContentsAtk();
		setContentsDef();
		setContentsAny1();
		setContentsAny2();
	}

	@Override
	protected UnderMenuWindow initializeSubWindow() {
		return ((Scene_Menu_First_Item_List_Command_Enchant_View) CURRENT_VIEW)
				.createSubWindow();
	}

	@Override
	protected void initialX_Y() {
		super.initialX_Y();
		Base_Artifact a = SELECTED_ARTIFACT;
		if (a.isEnchantedNow()) {
			y = Enchant.getEnchantedPlace(a).y;
		} else if (a instanceof SpellCard) {
			y = 0;
		} else {
			y = 2;
		}
	}

	private void setContents(Enchant e) {
		setContents(e.toColoredString(), "", new MyBook(e));
	}

	private void setContentsAny1() {
		setContents(Enchant.ANY1);
	}

	private void setContentsAny2() {
		setContents(Enchant.ANY2);
	}

	private void setContentsAtk() {
		if (SELECTED_ARTIFACT instanceof SpellCard) {
			setContents(Enchant.ATK);
		} else {
			setDeprecatedPerfetedlyContents(Enchant.ATK.toString());
		}
	}

	private void setContentsDef() {
		if (SELECTED_ARTIFACT instanceof SpellCard) {
			setContents(Enchant.DEF);
		} else {
			setDeprecatedPerfetedlyContents(Enchant.DEF.toString());
		}
	}

	@Override
	protected void setExplain() {
		StringBuilder sb = new StringBuilder();
		Enchant e = Enchant.getEnchantedPlace(SELECTED_ARTIFACT);
		Enchant old = ((MyBook) getSelectedContent().BOOK).E;
		if (old.isAbleToExchange(old.getEnchant(), e)) {
			sb.append(SELECTED_ARTIFACT.getColoredName());
			sb.append("と");
			sb.append(old.getEnchant().getColoredName());
			sb.append("を持ち替えます");
		} else {
			sb.append(SELECTED_ARTIFACT.getColoredName());
			sb.append("を");
			sb.append(Enchant.get(y).toColoredString());
			if (SELECTED_ARTIFACT.equals(old.getEnchant())) {
				sb.append("から外します");
			} else {
				sb.append("に装備します");
			}
		}
		if (SUB_WINDOW != null && getSelectedContent() != null)
			SUB_WINDOW.setExplain(sb.toString());
	}

}