package dangeon.model.object.creature.player.class_job.bonus;

import java.util.ArrayList;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.enchantSpecial.ENCHANT_SIMBOL;

public class BonusSimbol extends BaseBonus {
	private static final long serialVersionUID = 1L;
	public final ENCHANT_SIMBOL simbol;

	public BonusSimbol(ENCHANT_SIMBOL s) {
		simbol = s;
	}

	@Override
	public void excute(ArrayList<Base_Artifact> list1,
			ArrayList<ENCHANT_SIMBOL> list2) {
		list2.add(simbol);
	}

	@Override
	public String getEffectExn() {
		return "常時発揮：" + simbol.getName(true);
	}
}
