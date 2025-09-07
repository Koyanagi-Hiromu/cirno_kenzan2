package dangeon.model.object.creature.player.class_job;

import java.awt.Point;
import java.util.ArrayList;

import dangeon.latest.scene.action.menu.first.adventure.medal.Medal;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.class_job.bonus.BaseBonus;

public class ClassDefault extends BaseClassJob {
	@Override
	public Medal getMedal_ミラクルクエスト() { return null; }
	@Override
	public Medal getMedal_七曜クエスト() { return null; }

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
		return null;
	}

	@Override
	public String getSwitchExp() {
		return "";
	}

}
