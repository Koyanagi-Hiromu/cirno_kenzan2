package dangeon.latest.util.view_window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.menu.Base_Scene_Menu.MenuContent;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.view.util.StringFilter;

public class ItemSelectMenuWindow extends MenuWindow {

	public ItemSelectMenuWindow(Base_Scene_Menu_View view, int w, Font font) {
		super(view, w, font);
	}

	@Override
	protected void drawBackLight(Graphics2D g, boolean current, int index) {
		Item_List scene = (Item_List) SCENE;
		if (current || (scene.getMulti().isEmpty()))
			super.drawBackLight(g, current, index);
	}

	private void drawFirstSelection(Graphics2D g, int x, int y, Item_List scene) {
		for (int index : scene.getMulti()) {
			Integer i = scene.getIndex(index);
			if (i != null)
				super.drawBackLight(g, false, i);
		}
	}

	private void drawSecondSelection(Graphics2D g, int x, int y, Item_List scene) {
		if (scene.MULTI_SELECTION) {
			int times = 0;
			for (int index : scene.getMulti()) {
				String s = String.valueOf(++times);
				Integer i = scene.getIndex(index);
				if (i != null) {
					int margine = 1;
					if (times < 10 && times > 0)
						margine += 4;
					StringFilter.drawEdgedString(g, s, getX(x, i) + margine,
							getY(g, y, i));
				}
			}
		}
	}

	@Override
	protected void drawString(Graphics2D g, int x, int y) {
		Item_List scene = (Item_List) SCENE;
		drawFirstSelection(g, x, y, scene);
		g.setFont(FONT);
		int selected = SCENE.getIndex();
		for (int i = 0; i < SCENE.getContentSize(); i++) {
			if (isSelectedGlow() && i == selected)
				continue;
			MenuContent c = SCENE.getContentSize(i);
			if (c.OBJECT instanceof Base_Artifact) {
				Base_Artifact a = (Base_Artifact) c.OBJECT;
				StringFilter.drawArtifactName(g, a, getX(x, i), getY(g, y, i),
						scene.isException(a) ? Color.GRAY : null);
			}
		}
		drawSecondSelection(g, x, y, scene);
	}
}
