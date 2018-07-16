package dangeon.util;

import java.util.ArrayList;
import java.util.List;

import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Belongings;

public class ItemSelecter {

	private Base_Artifact A;

	public void select() {
		System.out.println("check");
		List<Base_Artifact> list = new ArrayList<Base_Artifact>();
		for (Base_Artifact a : Belongings.getListItems()) {
			list.add(a);
		}
		list.remove(A);

	}
}
