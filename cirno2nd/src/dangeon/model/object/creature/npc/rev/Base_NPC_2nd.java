package dangeon.model.object.creature.npc.rev;

import java.awt.Point;

import main.res.CHARA_IMAGE;
import dangeon.model.object.creature.npc.Base_NPC;

public abstract class Base_NPC_2nd extends Base_NPC {
	private static final long serialVersionUID = 1L;

	public Base_NPC_2nd(Point p, String name, boolean move) {
		super(p, name, move);
	}

	public Base_NPC_2nd(Point p, String name, CHARA_IMAGE c, boolean move) {
		super(p, name, move);
	}

	@Override
	public void message() {
	}

}
