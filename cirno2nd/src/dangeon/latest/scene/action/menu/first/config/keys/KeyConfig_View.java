package dangeon.latest.scene.action.menu.first.config.keys;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.Listener;
import main.Listener.ACTION;
import main.pad.JInputWrapper;
import main.res.Image_Window_Frame;
import main.util.DIRECTION;
import dangeon.latest.scene.Base_View;
import dangeon.latest.scene.action.menu.Base_Scene_Menu.MenuContent;
import dangeon.latest.scene.action.menu.Base_Scene_Menu_View;
import dangeon.latest.scene.action.menu.first.item.list.Scene_Menu_First_Item_List_View;
import dangeon.latest.scene.action.message.Message;
import dangeon.latest.util.view_window.MenuWindow;
import dangeon.latest.util.view_window.MenuWindow_SelfAdjusting;
import dangeon.model.config.Config;
import dangeon.view.constant.NormalFont;
import dangeon.view.util.StringFilter;

public class KeyConfig_View extends Base_Scene_Menu_View {

	public KeyConfig_View(Base_View bv) {
		super(Scene_Menu_First_Item_List_View.X
		// + Scene_Menu_First_Item_List_View.MAIN_W
		// + Scene_Menu_First_Item_List_View.MARGINE
				, 36, Image_Window_Frame.LINE, bv);
	}

	@Override
	protected MenuWindow createMenuWindow() {
		return new MenuWindow_SelfAdjusting(this, Message.W,
				NormalFont.NORMALFONT.deriveFont(11f)) {
			private void drawKeyBord(Graphics2D g, int x, int y, String s) {
				x += W / 8;
				StringFilter.drawString(g, s, x, y);
			}

			private void drawPad(Graphics2D g, int x, int y, String s) {
				x += W / 8 + 63;
				StringFilter.drawString(g, s, x, y);
			}

			@Override
			protected void drawString(Graphics2D g, int x, int y) {
				int _x;
				int _y = getY(g, y, 0) + 3;
				for (int i = 0; i < 3; i++) {
					_x = getX(x, i);
					g.setColor(StringFilter.NUMBERS);
					drawTitle(g, _x, _y, "NAME");
					g.setColor(Color.ORANGE);
					drawKeyBord(g, _x, _y, "Key");
					if (i == 3)
						break;
					drawPad(g, _x, _y, "Pad");

				}
				super.drawString(g, x, y);
			}

			@Override
			protected void drawStringFilter(Graphics2D g, int i, int x, int y) {
				MenuContent c = SCENE.getContent(i);
				if (c.OBJECT instanceof ACTION) {
					ACTION a = (ACTION) c.OBJECT;
					x = getX(x, i);
					y = getY(g, y, i);
					g.setColor(StringFilter.NUMBERS);
					StringBuilder sb = new StringBuilder();
					boolean flag = false;
					if (a == ACTION.ENTER) {
						sb.append(Color.MAGENTA);
						sb.append("Ent ");
						sb.append(StringFilter.NUMBERS);
					} else if (a == ACTION.MENU) {
						sb.append(Color.MAGENTA);
						sb.append("Esc ");
						sb.append(StringFilter.NUMBERS);
					}
					for (int keys : Listener.getKey().keySet()) {
						if (a == Listener.getKey().get(keys)) {
							flag = true;
							String text = KeyEvent.getKeyText(keys);
							if (keys == KeyEvent.VK_ENTER
									|| keys == KeyEvent.VK_ESCAPE)
								continue;
							if (text.length() > 2)
								text = text.substring(0, 2);
							sb.append(text);
							sb.append(" ");
						}
					}
					drawKeyBord(g, x, y, sb.toString());
					if (!flag && a.IS_MAIN)
						g.setColor(Color.RED);
					else
						g.setColor(Color.WHITE);
					drawTitle(g, x, y, a.name());
					sb = new StringBuilder();
					for (int j = 0; j < JInputWrapper.buttonID.length; j++) {
						int pad = Config.getPadKey(j);
						if (pad == a.KEY_NUM) {
							sb.append(j);
							sb.append(" ");
						}
					}
					drawPad(g, x, y, sb.toString());
				} else if (c.OBJECT instanceof DIRECTION) {
					super.drawStringFilter(g, i, x, y);
					DIRECTION d = (DIRECTION) c.OBJECT;
					x = getX(x, i);
					y = getY(g, y, i);
					g.setColor(StringFilter.NUMBERS);
					StringBuilder sb = new StringBuilder();
					for (int keys : Listener.getAllow_map().keySet()) {
						if (keys == KeyEvent.VK_DOWN || keys == KeyEvent.VK_UP
								|| keys == KeyEvent.VK_RIGHT
								|| keys == KeyEvent.VK_LEFT)
							continue;
						if (d == Listener.getAllow_map().get(keys)) {
							String text = KeyEvent.getKeyText(keys);
							if (text.length() > 2)
								text = text.substring(0, 2);
							sb.append(text);
							sb.append(" ");
						}
					}
					drawKeyBord(g, x, y, sb.toString());
				} else
					super.drawStringFilter(g, i, x, y);
			}

			private void drawTitle(Graphics2D g, int x, int y, String s) {
				StringFilter.drawString(g, s, x, y);
			}

			@Override
			public void drawWindow(Graphics2D g, boolean current) {
				int y = getViewY() + 40;
				int h = 130;
				int y2 = y + h;
				for (int i = 0; i < 2; i++) {
					int x2 = getX(0, 1 + i);
					g.setPaint(new GradientPaint(x2, y, Color.LIGHT_GRAY, x2,
							(y + y2) / 2, Color.WHITE, true));
					g.setStroke(new BasicStroke(3f));
					g.drawLine(x2, y, x2, y2);
					g.setStroke(new BasicStroke());
					g.setColor(Color.BLACK);
					g.drawRect(x2 - 2, y - 2, 4, h + 4);
				}
				super.drawWindow(g, current);
			}

			@Override
			protected int getW_Inside() {
				CONTENTS_WIDTH = new int[SCENE.ROW];
				for (int i = 0; i < CONTENTS_WIDTH.length; i++) {
					CONTENTS_WIDTH[i] = W / CONTENTS_WIDTH.length;
				}
				return W;
			}

			@Override
			protected int getX(int x, int index) {
				int row = index % SCENE.ROW;
				int _x = x + INSETS.left + MARGINE + row * 140;
				return _x + left_padding;
			}
		};
	}

	@Override
	public void draw(Graphics2D g, boolean current) {
		drawBlack(g);
		drawBlack(g);
		if (current)
			((KeyConfig) PARENT_SCENE).SUB_WINDOW.drawWindow(g);
		super.draw(g, current);
	}
}
