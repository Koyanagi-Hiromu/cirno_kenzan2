package dangeon.model.object.artifact.device;

import java.awt.Point;

import main.res.Image_Artifact;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.config.StoryManager;

public class MeteorIncidentNPC extends Stairs {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public MeteorIncidentNPC(Point p) {
		super(p, "階段", 1, 0, ITEM_CASE.TRAP, GROW_RATE.早熟, false);
		IM = Image_Artifact.STAIRS;
	}

	@Override
	protected String[] getExplan() {
		return null;
	}

	@Override
	public String[] getSlection() {
		if (!StoryManager.風穴旅行挑戦ok.hasFinished()) {
			Message.set("地霊殿への道は工事中です@");
			return new String[] { "ざんねん", "やめる" };
		} else {
			Message.set("地霊殿への道はこいしに案内してもらってください@");
			return new String[] { "了解" };
		}
	}

	@Override
	public void move() {
	}

	@Override
	public boolean walkOnAction() {
		if (!StoryManager.風穴旅行挑戦ok.hasFinished()) {
			Message.set("地霊殿への道は工事中です@");
		} else {
			Message.set("地霊殿への道はこいしに案内してもらってください@");
		}
		return false;
	}
}
