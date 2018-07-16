package dangeon.model.object.artifact.item;

import java.util.ArrayList;

import dangeon.model.object.artifact.Base_Artifact;

public interface SelectItem {
	/**
	 * 
	 * @param list
	 *            足下を含む
	 * @return
	 */
	public ArrayList<Base_Artifact> getEscape(ArrayList<Base_Artifact> list);
}
