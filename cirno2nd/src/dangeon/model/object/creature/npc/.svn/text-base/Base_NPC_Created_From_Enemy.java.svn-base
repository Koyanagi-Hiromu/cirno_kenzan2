package dangeon.model.object.creature.npc;

import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.creature.enemy.Base_Enemy;

public class Base_NPC_Created_From_Enemy extends Base_NPC {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String[] MSG;

	public Base_NPC_Created_From_Enemy(Base_Enemy e, boolean move,
			String... msg) {
		super(e.getMassPoint(), e.getColoredName(), e.getCharacterImage(), move);
		MSG = msg;
	}

	@Override
	public void message() {
		Message.set(MSG);
	}
}
