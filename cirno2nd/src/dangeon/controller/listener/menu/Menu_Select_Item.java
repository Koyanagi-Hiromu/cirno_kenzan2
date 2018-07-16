package dangeon.controller.listener.menu;

import java.util.List;

import dangeon.model.object.artifact.Base_Artifact;

public abstract class Menu_Select_Item extends Base_MenuItem {

	public Menu_Select_Item(String tit, Base_Artifact[] as) {
		super(tit, as);
	}

	public Menu_Select_Item(String tit, List<Base_Artifact> as) {
		super(tit, as);
	}

}
