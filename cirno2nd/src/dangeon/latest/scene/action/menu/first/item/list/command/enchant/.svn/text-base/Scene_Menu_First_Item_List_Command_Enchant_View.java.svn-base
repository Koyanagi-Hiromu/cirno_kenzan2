package dangeon.latest.scene.action.menu.first.item.list.command.enchant;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.UnderMenuWindow;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.creature.player.Enchant;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class Scene_Menu_First_Item_List_Command_Enchant_View extends
		Base_Scene_Menu_View {
	public Scene_Menu_First_Item_List_Command_Enchant_View(Base_View bv) {
		super(Message.X, 118, Image_Window_Frame.LINE, bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		return new MenuWindow(this, Message.W,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE)) {
			@Override
			protected void drawString(Graphics2D g, int x, int y) {
				super.drawString(g, x, y);
				for (int i = 0; i < Enchant.values().length; i++) {
					Enchant e = Enchant.values()[i];
					Base_Artifact a = e.getEnchant();
					StringFilter.drawArtifactName(g, a, getX(x, i) + 80,
							getY(g, y, i));
				}
			}

			@Override
			public int getContentHeight() {
				return super.getContentHeight() - 3;
			}
		};
	}

	public UnderMenuWindow createSubWindow() {
		return new UnderMenuWindow(2, Y + 86,
				NormalFont.NORMALFONT.deriveFont(NormalFont.SMALL_SIZE)) {
			@Override
			protected void drawString(Graphics2D g, int X, int Y) {
				ArrayList<String> list = new ArrayList<String>();
				StringFilter
						.setList(list, getWidth_CenterArea(), true, explain);
				for (int j = 0; j < list.size(); j++) {
					drawString(g, list.get(j), j + 1);
				}
			}
		};
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBlack(g);
		drawBlack(g);
		super.draw(g, current);
		((Scene_Menu_First_Item_List_Command_Enchant) PARENT_SCENE).SUB_WINDOW
				.drawWindow(g);
	}

}
