package dangeon.latest.util.view_window;

import java.awt.Graphics2D;

import dangeon.latest.scene.action.itemlist.Item_List;
import dangeon.latest.scene.action.menu.Base_Scene_Menu;
import dangeon.latest.scene.action.message.Message;
import dangeon.model.object.artifact.Base_Artifact;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.view.constant.NormalFont;

public class ItemExplainWindow extends WindowFrame {
	private final Item_List PARENT_ITEM_LIST;
	private Base_Scene_Menu next_scene;

	public ItemExplainWindow(Item_List scene) {
		super(Message.X, Message.Y, Message.W, 4, NormalFont.NORMALFONT
				.deriveFont(NormalFont.SMALL_SIZE));
		PARENT_ITEM_LIST = scene;
	}

	protected void drawString(Graphics2D g, int X, int Y) {
		if (PARENT_ITEM_LIST.getSelectedContent() == null) {
			return;
		}
		Base_Artifact a = ((Base_Artifact) PARENT_ITEM_LIST
				.getSelectedContent().OBJECT);
		String[] arr;
		if (next_scene == null) {
			arr = a.firstPageExplain();
			if (a instanceof SpellCard && a.isStaticCheked()) {
				int y_diff = 0;
				for (int i = 0; i < 4 && i < arr.length; i++) {
					if (i == 2) {
						drawLine(g, 1, false);
						y_diff += 5;
					}
					drawString(g, arr[i], i + 1, y_diff);
				}
				return;
			}
		} else
			arr = next_scene.getSelectedContent().EXPLAIN;
		for (int i = 0; i < 4 && i < arr.length; i++) {
			drawString(g, arr[i], i + 1, 0, 3);
		}
	}

	@Override
	public void drawWindow(Graphics2D g) {
		super.drawWindow(g);
		drawString(g, getViewX(), getViewY());
	}

	@Override
	protected int getBG() {
		return getBG(next_scene != null);
	}

	@Override
	public int getContentHeight() {
		return super.getContentHeight() - 3;
	}

	public void setNextScene(Base_Scene_Menu base_scene_maenu) {
		next_scene = base_scene_maenu;
	}

}
