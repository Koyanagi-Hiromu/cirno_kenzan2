package dangeon.model.object.creature.player.class_job;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.artifact.item.spellcard.チルノのカード;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;

public class ClassDefault extends BaseClassJob {

	private static final long serialVersionUID = 1L;

	@Override
	protected void bonusLv1(ArrayList<BaseBonus> l, Point p) {
	}

	@Override
	protected void bonusLv2(ArrayList<BaseBonus> l, Point p) {
	}

	@Override
	protected void bonusLv3(ArrayList<BaseBonus> l, Point p) {
	}

	@Override
	protected void firstDemerite(ArrayList<BaseBonus> l, Point p) {
	}

	@Override
	protected void firstMerite(ArrayList<BaseBonus> l, Point p) {
	}

	@Override
	public SpellCard getRep() {
		return new チルノのカード(new Point());
	}

	@Override
	public String getSwitchExp() {
		return "";
	}

}
