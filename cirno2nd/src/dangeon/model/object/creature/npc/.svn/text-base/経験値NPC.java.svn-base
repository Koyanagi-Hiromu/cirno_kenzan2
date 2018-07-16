package dangeon.model.object.creature.npc;

import java.awt.Point;

import dangeon.model.object.creature.player.Player;

public class 経験値NPC extends Base_NPC {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String name = "経験値Npc";

	public 経験値NPC(Point p) {
		super(p, name, false);
	}

	@Override
	public void message() {
		Player.me.setExpCash(100000);
	}
}
