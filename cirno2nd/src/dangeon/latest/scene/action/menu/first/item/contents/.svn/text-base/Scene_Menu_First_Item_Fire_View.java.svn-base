package dangeon.latest.scene.action.menu.first.item.contents;

import java.awt.Graphics2D;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.arrow.Arrow;
import dangeon.model.object.creature.player.Belongings;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Scene_Menu_First_Item_Fire_View extends Base_Scene_Menu_View {

	public Scene_Menu_First_Item_Fire_View(Base_View bv) {
		super(null, 133, Image_Window_Frame.LINE, bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		return new MenuWindow(this, 200) {
			@Override
			protected void drawStringFilter(Graphics2D g, int i, int x, int y) {
				Object o = SCENE.getContentSize(i).OBJECT;
				if (o instanceof Base_Artifact) {
					Base_Artifact a = (Base_Artifact) o;
					x = getX(x, i);
					y = getY(g, y, i);
					StringFilter.drawArtifactName(g, a, x, y);
					g.setFont(NormalFont.NORMALFONT.deriveFont(9f));
					int rest = 0;
					for (Arrow arrow : Belongings.getArrows(a.getClass())) {
						rest += arrow.getArrowRest();
					}
					StringFilter.drawString(g, String.valueOf(rest), x + 14, y);
					g.setFont(NormalFont.NORMALFONT);
				}
			}
		};
	}
}
