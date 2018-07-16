package dangeon.latest.scene.action.menu.first.config;

import java.awt.Color;
import java.awt.Graphics2D;

import main.util.DIRECTION;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.first.Base_Scene_Menu_First____View;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.MenuWindow_SelfAdjusting;
import dangeon.view.util.StringFilter;

class Scene_Menu_First_Config_View extends Base_Scene_Menu_First____View {

	public Scene_Menu_First_Config_View(Base_View bv) {
		super(bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		return new MenuWindow_SelfAdjusting(this) {
			@Override
			protected void drawGlowString(Graphics2D g, int x, int y,
					boolean current) {
				int index = SCENE.getIndex();
				if (SCENE.isContentDeprecated(index)) {
					return;
				}
				Color origine = g.getColor();
				g.setColor(current ? COLOR_MAIN : COLOR_SUB);
				for (DIRECTION direction : DIRECTION.values_exceptNeatral()) {
					drawGlowString(g, x, y, direction, index, getTitle(index));
				}
				g.setColor(Color.WHITE);
				g.drawString(getTitle(index), getX(x, index), getY(g, y, index));
				g.setColor(origine);
				StringFilter.drawString(g, getSwitch(index), getX(x, index),
						getY(g, y, index));
			}

			@Override
			protected void drawGlowString(Graphics2D g, int x, int y,
					DIRECTION direction, int index, String plain) {
				int range = 2;
				int _x = getX(x, index) + direction.X * range;
				int _y = getY(g, y, index) + direction.Y * range;
				g.drawString(plain, _x, _y);
			}

			private String getSwitch(int index) {
				String line = SCENE.getContentName(index);
				int len = getTitle(index).length();
				String space = "";
				for (int i = 0; i < len; i++) {
					space = space.concat("　");
				}
				return line.replaceFirst(getTitle(index), space);
			}

			private String getTitle(int index) {
				String line = SCENE.getContentName(index);
				int i = line.indexOf("　");
				return i < 0 ? line : line.substring(0, i);
			}
		};
	}
}