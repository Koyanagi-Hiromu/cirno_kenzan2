package dangeon.model.object.creature.npc;

import java.awt.Point;

import dangeon.latest.scene.action.message.Message;

public class UsefulNpc extends Base_NPC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String name = "specialRoomNpc";
	private final String msg;

	public UsefulNpc(Point p, String msg) {
		super(p, name, false);
		this.msg = msg;
	}

	// public Image getImage() {
	// return main.res.IMAGE.ANYBODY.getImage();
	// }

	@Override
	public void message() {
		Message.set(msg);
	}

}
