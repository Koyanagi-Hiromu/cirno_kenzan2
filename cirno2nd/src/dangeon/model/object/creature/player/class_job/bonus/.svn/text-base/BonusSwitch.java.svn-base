package dangeon.model.object.creature.player.class_job.bonus;

import java.util.ArrayList;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.class_job.BaseClassJob;

public class BonusSwitch extends BaseBonus {
	private static final long serialVersionUID = 1L;
	public final BaseClassJob parent;
	public final int child;
	public final String exn;

	public BonusSwitch(BaseClassJob class_job, int c, String exn) {
		parent = class_job;
		child = c;
		this.exn = exn;
	}

	public BonusSwitch(BaseClassJob class_job, String exn) {
		this(class_job, 0, exn);
	}

	@Override
	public void excute(ArrayList<Base_Artifact> list1,
			ArrayList<ENCHANT_SIMBOL> list2) {
		parent.setSwichON(child);
	}

	@Override
	public String getEffectExn() {
		return exn;
	}
}
