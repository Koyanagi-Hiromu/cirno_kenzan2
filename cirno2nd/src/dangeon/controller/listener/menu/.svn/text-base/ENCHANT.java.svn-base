package dangeon.controller.listener.menu;

import main.Listener.ACTION;
import main.res.SE;
import main.util.DIRECTION;
import dangeon.controller.DangeonScene;
import dangeon.controller.TurnSystemController;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Enchant;

public class ENCHANT {

	private static Base_Artifact selected_artifact = null;

	private static int y;

	public static void actionKeyPressed(ACTION action) {
		switch (action) {
		case ENTER:
			pressEnter();
			break;
		case CANCEL:
			selected_artifact = null;
			DangeonScene.MENU.setPresentScene();
			break;
		}
	}

	static void arrowKeyPressed(DIRECTION direction) {
		int _y = y;
		switch (direction) {
		case UP:
			_y--;
			break;
		case DOWN:
			_y++;
			break;
		case LEFT:
		case RIGHT:
			return;
		}
		if (isSelectedArtifactSpellCard()) {
			if (_y < 0) {
				_y += Enchant.LENGTH;
			} else if (_y >= Enchant.LENGTH) {
				_y -= Enchant.LENGTH;
			}
		} else {
			if (_y < Enchant.NON_ANY_LENGTH) {
				_y += Enchant.ANY_LENGTH;
			} else if (_y >= Enchant.LENGTH) {
				_y -= Enchant.ANY_LENGTH;
			}
		}
		y = _y;
	}

	public static String getEXP() {
		StringBuilder sb = new StringBuilder();
		Enchant e = Enchant.getEnchantedPlace(selected_artifact), old = Enchant
				.get(y);
		if (old.isAbleToExchange(old.getEnchant(), e)) {
			sb.append(selected_artifact.getColoredName());
			sb.append("と");
			sb.append(old.getEnchant().getColoredName());
			sb.append("を持ち替えます");
		} else {
			sb.append(selected_artifact.getColoredName());
			sb.append("を");
			sb.append(Enchant.get(y).toColoredString());
			if (isSelectedArtifactEqualEnchant()) {
				sb.append("から外します");
			} else {
				sb.append("装備します");
			}
		}

		return sb.toString();
	}

	public static int getY() {
		return y;
	}

	public static void initENCHANT(Base_Artifact a) {
		selected_artifact = a;
		if (a.isEnchantedNow()) {
			y = Enchant.getEnchantedPlace(a).y;
		} else if (isSelectedArtifactSpellCard()) {
			y = 0;
		} else {
			y = Enchant.ANY1.y;
		}
		DangeonScene.ENCHANT.setPresentScene();
	}

	public static boolean isSelectedArtifactEqualEnchant() {
		return isSelectedArtifactEqualEnchant(Enchant.get(y));
	}

	public static boolean isSelectedArtifactEqualEnchant(Enchant e) {
		return e.getEnchant() == selected_artifact;
	}

	public static boolean isSelectedArtifactSpellCard() {
		if (selected_artifact == null) {
			return true;
		}
		return selected_artifact instanceof SpellCard;
	}

	private static void pressEnter() {
		if (isSelectedArtifactEqualEnchant()) {
			if (Enchant.tryToRemove(selected_artifact)) {
				SE.SYSTEM_ENCHANT_OFF.play();
				TurnSystemController.callMeToStartEnemyTurn();
			}
		} else {
			boolean success = Enchant.get(y).tryToSet(selected_artifact);
			if (success) {
				TurnSystemController.callMeToStartEnemyTurn();
			}
		}
		selected_artifact = null;
		ITEM.setMenuEnd();
	}
}
