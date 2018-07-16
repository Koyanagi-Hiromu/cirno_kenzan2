package dangeon.latest.scene.action.menu.first;

import java.awt.Graphics2D;

import main.res.Image_Window_Frame;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.model.object.artifact.item.spellcard.SpellCard;
import dangeon.model.object.creature.player.Enchant;

public class Scene_Menu_First_View extends Base_Scene_Menu_View {

	public Scene_Menu_First_View(Base_View bv) {
		super(null, 20, Image_Window_Frame.GLOW, bv);
	}

	public Scene_Menu_First_View(Integer x, Integer y, Image_Window_Frame type,
			Base_View bv) {
		super(x, y, type, bv);
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBlack(g);
		drawIMLC(g);
		drawStatus(g, current);
		super.draw(g, current);
	}

	protected void drawIMLC(Graphics2D g) {
		if (Enchant.ATK.isEnchant()) {
			((SpellCard) Enchant.ATK.getEnchant()).IMLC.draw(g, true);
		}
		if (Enchant.DEF.isEnchant()) {
			((SpellCard) Enchant.DEF.getEnchant()).IMLC.draw(g, false);
		}
	}

	protected void drawStatus(Graphics2D g, boolean current) {
		if (current)
			((Scene_Menu_First) PARENT_SCENE).SUB_WINDOW.drawWindow(g);
	}

	public void super_draw(Graphics2D g, boolean current) {
		super.draw(g, current);
	}

}
