package dangeon.model.object.artifact.item.pot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.Scene_Action;
import dangeon.latest.scene.action.itemlist.Book_Item;
import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.menu.Book;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.Base_Item;
import dangeon.model.object.artifact.item.SelectItem;
import dangeon.model.object.artifact.item.check.Checker;
import dangeon.model.object.creature.player.Belongings;
import dangeon.model.object.creature.player.Enchant;
import dangeon.util.STAGE;

public abstract class Base_Pot_Selective extends Base_Pot implements SelectItem {
	private static final long serialVersionUID = 1L;

	public Base_Pot_Selective(Point p, String item_name, int max,
			STAGE... stages) {
		super(p, item_name, max, stages);
	}

	@Override
	public ArrayList<Base_Artifact> getEscape(ArrayList<Base_Artifact> list) {
		for (Iterator<Base_Artifact> iterator = list.iterator(); iterator
				.hasNext();) {
			Base_Artifact a = iterator.next();
			if (isUnableToPutIn(a)) {
				continue;
			}
			iterator.remove();
		}
		return list;
	}

	@Override
	public StringBuilder getName(StringBuilder sb) {
		return super.getName(sb);
	}

	@Override
	protected boolean isItemUseThisAvailable() {
		if (!super.isItemUseThisAvailable()) {
			return false;
		}
		if (isMax()) {
			Message.set(getColoredName(), "は既にいっぱいだ");
			return false;
		} else {
			return true;
		}
	}

	@Override
	protected boolean isUnableToPutIn(Base_Artifact a) {
		if (Enchant.isEnchanted(a) && a.isCurse()) {
			return true;
		} else if (!a.isMobile()) {
			return true;
		} else if (a instanceof Base_Pot) {
			return true;
		}
		return false;
	}

	@Override
	public final boolean itemUse() {
		if (isUnableToPutIn(A)) {
			Message.set(A.getColoredName(), "は入れられない");
			return false;
		} else {
			Message.set(getColoredName(), "に", A.getColoredName(), "を入れた");
			LIST.add(A);
			A.remove();
			potUse();
			return true;
		}
	}

	protected abstract void potUse();

	@Override
	protected void selectItem(final Base_View view) {
		final SelectItem ME = this;
		ArrayList<Base_Artifact> list = null;
		if (isStaticCheked()) {
			list = Belongings.getDeepCopy(Belongings.getListItems());
			list = ME.getEscape(list);
		}
		if (list == null)
			list = new ArrayList<Base_Artifact>();
		list.add(this);
		final Base_Item me = this;
		new Item_List(new Book_Item() {
			@Override
			public void work(Base_Artifact a) {
				A = a;
				itemUseThis();
			}
		}, list, true) {
			@Override
			protected void action_cancel() {
				super.action_cancel();
				Checker.select(me);
			}

			@Override
			protected Book getMultiBook(
					final Base_Artifact... selected_artifacts) {
				return new Book() {

					@Override
					protected void work() {
						for (Base_Artifact a : selected_artifacts) {
							A = a;
							itemUseThis();
						}
						setNextScene(Scene_Action.getMe());
					}
				};
			}
		};
	}

}
