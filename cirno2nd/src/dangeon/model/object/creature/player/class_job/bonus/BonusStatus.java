package dangeon.model.object.creature.player.class_job.bonus;

import java.util.ArrayList;

import dangeon.model.condition.CONDITION;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.class_job.BaseClassJob;

public class BonusStatus extends BaseBonus {
	private static final long serialVersionUID = 1L;
	public final CONDITION condition;
	private BaseClassJob job;
	private int timer;

	public BonusStatus(BaseClassJob class_job, CONDITION c) {
		this(class_job, c, -2);
	}

	public BonusStatus(BaseClassJob class_job, CONDITION c, int timer) {
		job = class_job;
		condition = c;
		this.timer = timer;
	}

	@Override
	public void excute(ArrayList<Base_Artifact> list1,
			ArrayList<ENCHANT_SIMBOL> list2) {
		job.addCondition(condition, timer);
	}

	@Override
	public String getEffectExn() {
		return "階段を降りると：" + condition.NAME + "状態";
	}
}
