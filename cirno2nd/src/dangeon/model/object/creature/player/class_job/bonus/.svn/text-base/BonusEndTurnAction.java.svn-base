package dangeon.model.object.creature.player.class_job.bonus;

import java.util.ArrayList;

import dangeon.controller.task.Task;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;
import dangeon.model.object.creature.player.class_job.BaseClassJob;

public class BonusEndTurnAction extends BaseBonus {
	private static final long serialVersionUID = 1L;

	private final Task task;
	private BaseClassJob job;
	private final String exn;

	public BonusEndTurnAction(BaseClassJob class_job, String exn, Task task) {
		this.task = task;
		job = class_job;
		this.exn = exn;
	}

	@Override
	public void excute(ArrayList<Base_Artifact> list1,
			ArrayList<ENCHANT_SIMBOL> list2) {
		job.addEndTask(task);
	}

	@Override
	public String getEffectExn() {
		return exn;
	}
}
