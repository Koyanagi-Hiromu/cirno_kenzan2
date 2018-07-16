package dangeon.controller.listener.menu;

import main.Listener.ACTION;
import dangeon.model.object.artifact.item.enchantSpecial.Composition;
import dangeon.model.object.creature.player.Belongings;

public class Gousei {
	private static int selected_first = -1, selected_second = -1;

	public static void actionKeyPressed(ACTION action) {
		switch (action) {
		case ENTER:
			pressEnter();
			break;
		case CANCEL:
			resetGousei();
			break;
		}
	}

	public static int getSelected_first() {
		return selected_first;
	}

	public static int getSelected_second() {
		return selected_second;
	}

	private static void isYesNoPhase() {
		if (selected_second != -1) {
			try {
				if (main.util.Show.showConfirmDialog("選択した２つのアイテムを合成しますか？\n"
						+ Belongings.get(selected_first).getColoredName()
						+ "\n"
						+ Belongings.get(selected_second).getColoredName()) == 0) {
					Composition.setComposition(Belongings.get(selected_first),
							Belongings.get(selected_second));
					main.util.Show.showInformationMessageDialog("合成しちゃったよ");
					Composition.isSpecialCard(Belongings.get(selected_second),
							selected_second);
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				main.util.Show
						.showInformationMessageDialog("NullPointerError !!!");
			}
			resetGousei();
			return;
		}
	}

	private static void pressEnter() {
		if (selected_first == -1)
			selected_first = ITEM.getIndex();
		else
			selected_second = ITEM.getIndex();
		isYesNoPhase();
	}

	private static void resetGousei() {
		Conducter.resetGousei();
		selected_first = -1;
		selected_second = -1;
	}
}
