package dangeon.model.object.creature.npc;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;

public class TestNpc extends Base_NPC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String name = "TestNpc";

	public TestNpc(Point p) {
		super(p, name, false);
	}

	// public Image getImage() {
	// return main.res.IMAGE.ANYBODY.getImage();
	// }

	@Override
	public void message() {
		Message.set(new String[] { "テストの部屋です。" });
	}

}
