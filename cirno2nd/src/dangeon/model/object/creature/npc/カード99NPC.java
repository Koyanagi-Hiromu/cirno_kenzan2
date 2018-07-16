package dangeon.model.object.creature.npc;

import java.awt.Point;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Belongings;

public class カード99NPC extends Base_NPC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String name = "カード99Npc";

	public カード99NPC(Point p) {
		super(p, name, false);
	}

	@Override
	public void message() {
		for (Base_Artifact a : Belongings.getListItems()) {
			if (a instanceof SpellCard) {
				a.setForgeValue(90);
				if (a.isCurse()) {
					a.setCurse(false);
				}
				a.check();
			}
		}
	}
}
