package dangeon.controller.listener.menu;

import main.Listener.ACTION;
import main.util.DIRECTION;
import dangeon.controller.listener.menu.Conducter.PHASE;
import dangeon.model.object.creature.player.EnchantArrow;

public class ArrowSet {
	private static int y;

	public static void actionKeyPressed(ACTION action) {
		switch (action) {
		case ENTER:
			EnchantArrow.setArrow(y);
			break;
		case CANCEL:
			Conducter.phase = PHASE.FIRST;
			break;
		}
	}

	public static void actionKeyPressed(DIRECTION direction) {
		switch (direction) {
		case UP:
			y--;
			break;
		case DOWN:
			y++;
			break;
		}
		if (y < 0) {
			y += EnchantArrow.length();
		} else if (y >= EnchantArrow.length()) {
			y -= EnchantArrow.length();
		}
	}

	public static int getY() {
		return y;
	}

	public static void init() {
		if (EnchantArrow.getArrow() == null) {
			y = 0;
		} else {
			y = EnchantArrow.getArrow().Y;
		}
	}

}
