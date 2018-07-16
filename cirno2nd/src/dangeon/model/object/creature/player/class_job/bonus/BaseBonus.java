package dangeon.model.object.creature.player.class_job.bonus;

import java.io.Serializable;
import java.util.ArrayList;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

public abstract class BaseBonus implements Serializable {
	private static final long serialVersionUID = 1L;

	public abstract void excute(ArrayList<Base_Artifact> list1,
			ArrayList<ENCHANT_SIMBOL> list2);

	public abstract String getEffectExn();

}
